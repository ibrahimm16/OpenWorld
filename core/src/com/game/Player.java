package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.game.util.InputMap;

import java.util.HashMap;

public class Player extends Sprite {

    public static final float WIDTH = 32f * Constants.SCALE;
    public static final float HEIGHT = 64F * Constants.SCALE;
    private static final float SPEED = 4f;
    private static final float DAMPING = .8F;

    private final InputMap input;
    public final Vector2 position;
    public final Vector2 velocity;

    //animations
    private float animationTime = 0;
    private HashMap<State, Animation<TextureRegion>> animations;
    private Animation<TextureRegion> animation;

    //collisions
    private final TiledMapTileLayer collisionLayer;
    private final Array<Rectangle> tiles;
    private final Pool<Rectangle> rectPool;

    //random debugging
    private Texture block;

    public Player(float x, float y, InputMap input, TiledMapTileLayer collisionLayer) {
        super(new Texture("sprites/player.png"));

        this.input = input;
        this.position = new Vector2(x, y);
        this.velocity = new Vector2();

        initAnimation();

        this.collisionLayer = collisionLayer;
        this.tiles = new Array<>();
        this.rectPool = new Pool<Rectangle>() {

            @Override
            protected Rectangle newObject() {
                return new Rectangle();
            }
        };

        block = new Texture("block.png");
    }

    public void update(float delta) {
        animationTime += delta;
        move(delta);
    }

    public void move(float delta) {
        if (input.pressed("w")) {
            velocity.y += SPEED;
        }
        if (input.pressed("a")) {
            velocity.x -= SPEED;
        }
        if (input.pressed("s")) {
            velocity.y -= SPEED;
        }
        if (input.pressed("d")) {
            velocity.x += SPEED;
        }

        velocity.x = MathUtils.clamp(velocity.x, -SPEED, SPEED);
        velocity.y = MathUtils.clamp(velocity.y, -SPEED, SPEED);

        velocity.scl(delta);

        checkCollision();

        position.add(velocity);
        velocity.scl(1 / delta);

        velocity.x = Math.abs(velocity.x) < .1f ? 0 : velocity.x * DAMPING;
        velocity.y = Math.abs(velocity.y) < .1f ? 0 : velocity.y * DAMPING;

        updateFrame();
    }

    void checkCollision() {
        Rectangle boundingBox = rectPool.obtain();
        boundingBox.set(position.x, position.y, WIDTH, 5 * Constants.SCALE);

        int startX, startY, endX, endY;
        if (velocity.x > 0) {
            startX = endX = (int) (position.x + WIDTH + velocity.x);
        } else {
            startX = endX = (int) (position.x + velocity.x);
        }

        startY = (int) (position.y);
        endY = (int) (position.y + HEIGHT / 2f);
        getTiles(startX, startY, endX, endY, tiles);

        boundingBox.x += velocity.x;
        tiles.forEach(t -> {
            if (boundingBox.overlaps(t)) {
                velocity.x = 0;
            }
        });

        if (velocity.y > 0) {
            startY = endY = (int) (position.y + WIDTH + velocity.y);
        } else {
            startY = endY = (int) (position.y + velocity.y);
        }

        startX = (int) (position.x);
        endX = (int) (position.x + WIDTH / 2f);
        getTiles(startX, startY, endX, endY, tiles);

        boundingBox.y += velocity.y;
        tiles.forEach(t -> {
            if (boundingBox.overlaps(t)) {
                velocity.y = 0;
            }
        });
    }

    private void getTiles(int startX, int startY, int endX, int endY, Array<Rectangle> tiles) {
        rectPool.freeAll(tiles);
        tiles.clear();
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
                if (cell != null) {
                    Rectangle rect = rectPool.obtain();
                    rect.set(x, y, 1, 1);
                    tiles.add(rect);
                }
            }
        }
    }

    void updateFrame() {
        Animation<TextureRegion> existing = animation;

        if (velocity.x == 0 && velocity.y == 0) {
            if (existing == animations.get(State.WALK_UP)) animation = animations.get(State.STAND_UP);
            else if (existing == animations.get(State.WALK_DOWN)) animation = animations.get(State.STAND_DOWN);
            else if (existing == animations.get(State.WALK_LEFT)) animation = animations.get(State.STAND_LEFT);
            else if (existing == animations.get(State.WALK_RIGHT)) animation = animations.get(State.STAND_RIGHT);
        } else if (velocity.x > 0) {
            animation = animations.get(State.WALK_RIGHT);
        } else if (velocity.x < 0) {
            animation = animations.get(State.WALK_LEFT);
        } else if (velocity.y > 0) {
            animation = animations.get(State.WALK_UP);
        } else if (velocity.y < 0) {
            animation = animations.get(State.WALK_DOWN);
        }

        if (existing != animation) {
            animationTime = 0;
        }
    }

    public void render(Batch batch) {
        batch.begin();
        batch.draw(animation.getKeyFrame(animationTime), position.x, position.y, WIDTH, HEIGHT);

//        tiles.forEach(t -> {
//            batch.draw(block, t.x, t.y, t.width, t.height);
//        });

        batch.end();
    }

    private void initAnimation() {
        animations = new HashMap<>();

        TextureRegion[][] regions = TextureRegion.split(getTexture(), 32, 64);

        TextureRegion[] region = regions[0];
        TextureRegion[] downReg = {region[1], region[2], region[3], region[2], region[1], region[4], region[5], region[4]};

        region = regions[1];
        TextureRegion[] upReg = {region[1], region[2], region[3], region[2], region[1], region[4], region[5], region[4]};

        region = regions[2];
        TextureRegion[] leftReg = {region[1], region[2], region[3], region[2], region[1], region[4], region[5], region[4]};

        region = regions[3];
        TextureRegion[] rightReg = {region[1], region[2], region[3], region[2], region[1], region[4], region[5], region[4]};

        animations.put(State.STAND_DOWN, new Animation<>(0, regions[0][0]));
        animations.put(State.STAND_UP, new Animation<>(0, regions[1][0]));
        animations.put(State.STAND_LEFT, new Animation<>(0, regions[2][0]));
        animations.put(State.STAND_RIGHT, new Animation<>(0, regions[3][0]));

        animations.put(State.WALK_DOWN, new Animation<>(.2f, downReg));
        animations.put(State.WALK_UP, new Animation<>(.2f, upReg));
        animations.put(State.WALK_LEFT, new Animation<>(.2f, leftReg));
        animations.put(State.WALK_RIGHT, new Animation<>(.2f, rightReg));

        animations.get(State.WALK_DOWN).setPlayMode(Animation.PlayMode.LOOP);
        animations.get(State.WALK_UP).setPlayMode(Animation.PlayMode.LOOP);
        animations.get(State.WALK_LEFT).setPlayMode(Animation.PlayMode.LOOP);
        animations.get(State.WALK_RIGHT).setPlayMode(Animation.PlayMode.LOOP);

        animation = animations.get(State.STAND_DOWN);
    }

    public void dispose() {
        getTexture().dispose();
        block.dispose();
    }

    enum State {
        STAND_DOWN,
        STAND_UP,
        STAND_LEFT,
        STAND_RIGHT,
        WALK_DOWN,
        WALK_UP,
        WALK_LEFT,
        WALK_RIGHT
    }
}
