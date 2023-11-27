package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.Constants;
import com.game.OpenWorldGame;
import com.game.Player;

public class GameScreen extends BaseScreen {

    Player player;

    private final TiledMap map;
    private final TiledMapTileLayer collisionLayer;
    private final MapLayer objectLayer;

    private final OrthogonalTiledMapRenderer mapRenderer;
    private final OrthographicCamera camera;

    SpriteBatch batch;

    public GameScreen(OpenWorldGame game) {
        super(game);

        map = new TmxMapLoader().load("maps/testLevel.tmx");
        collisionLayer = (TiledMapTileLayer) map.getLayers().get("collision");
        objectLayer = map.getLayers().get("objects");

        mapRenderer = new OrthogonalTiledMapRenderer(map, Constants.SCALE);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 30, 20);
        camera.update();

        float x = objectLayer.getObjects().get("Start").getProperties().get("x", Float.class) * Constants.SCALE;
        float y = objectLayer.getObjects().get("Start").getProperties().get("y", Float.class) * Constants.SCALE;
        player = new Player(x, y, input, collisionLayer);

        batch = new SpriteBatch();
    }

    @Override
    public void update() {
    }

    public void render(float delta) {
        if (delta == 0) return;

        if (delta > 0.1f) delta = 0.1f;

        player.update(delta);

        ScreenUtils.clear(0, 0, 0, 1);

        camera.position.x = player.position.x + Player.WIDTH / 2f;
        camera.position.y = player.position.y + Player.HEIGHT / 2f;
        camera.update();

        mapRenderer.setView(camera);
        mapRenderer.render();

        player.render(mapRenderer.getBatch());

        batch.begin();
        game.font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 10, 20);
        game.font.draw(batch, "velX: " + player.velocity.x + ", velY: " + player.velocity.y, 10, 40);
        game.font.draw(batch, "X: " + player.position.x + ", Y: " + player.position.y, 10, 60);
        batch.end();
    }

    public void resize(int width, int height) {
        camera.viewportWidth = width * Constants.SCALE;
        camera.viewportHeight = height * Constants.SCALE;
        camera.update();
    }

    public void dispose() {
        player.dispose();
        mapRenderer.dispose();
        map.dispose();
        batch.dispose();

        super.dispose();
    }
}
