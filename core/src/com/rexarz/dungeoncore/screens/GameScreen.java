package com.rexarz.dungeoncore.screens;

import box2dLight.RayHandler;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexarz.dungeoncore.core.GameCore;
import com.rexarz.dungeoncore.gameobjects.Map;
import com.rexarz.dungeoncore.gameobjects.Player;
import com.rexarz.dungeoncore.gameobjects.OldTile;
import com.rexarz.dungeoncore.scenes.DebugHud;
import com.rexarz.dungeoncore.utils.Constants;
import com.rexarz.dungeoncore.utils.ContactsCore;
import com.rexarz.dungeoncore.utils.ScreenRenderer;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class GameScreen implements Screen {

    private GameCore game;
    private Player player;

    private World world;
    private ContactsCore contactsCore;

    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private DebugHud debugHud;
    private ScreenRenderer screenRenderer;

    private Map map;
    private OldTile oldTile;
    public static RayHandler rayHandler;

    private Box2DDebugRenderer renderer;


    public GameScreen(GameCore gameCore) {

        game = gameCore;
        batch = new SpriteBatch();

        world = new World(new Vector2(0, -9.8f), true);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new FillViewport(Constants.V_WIDTH / 40f, Constants.V_HEIGHT / 40f, camera);
        camera.position.set(viewport.getWorldWidth() / Constants.PPM, viewport.getWorldHeight() / Constants.PPM, 0);

        rayHandler = new RayHandler(world);
        rayHandler.setAmbientLight(0f, 0f, 0f, 0.01f);

        player = new Player(world);

        contactsCore = new ContactsCore();
        world.setContactListener(contactsCore);




//        MAP_DEBUG
        map = new Map(20000, 200, camera);


//        SCREEN_RENDERER
        screenRenderer = new ScreenRenderer(camera, world, map, batch);

//        DEBUG
        debugHud = new DebugHud(batch, world);




    }

    @Override
    public void show() {

    }

    public void update(float delta) {
        world.step(1 / 60f, 6, 2);
//        screenRenderer.update(delta);
        player.update(delta);
        camera.position.set(player.body.getPosition().x, player.body.getPosition().y + Constants.C_OFFSET, 0);
        camera.update();
        debugHud.update(delta);
        dbTouch();
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(delta);




//        renderer.render(world, camera.combined);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        screenRenderer.update(delta);
        player.draw(batch);
        batch.end();

        rayHandler.setCombinedMatrix(camera);
        rayHandler.updateAndRender();



        batch.setProjectionMatrix(debugHud.stage.getCamera().combined);
        debugHud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        debugHud.viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void dbTouch() {
        Vector3 touchPoint = new Vector3();
        if (Gdx.input.isTouched()) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);


            try {
                Map.grid.set((int) (touchPoint.x / 0.32f), (int) (touchPoint.y / 0.32f), 0);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPoint);
            try {
                Map.grid.set((int) (touchPoint.x / 0.32f), (int) (touchPoint.y / 0.32f), 1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
