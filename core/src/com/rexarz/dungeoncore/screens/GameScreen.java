package com.rexarz.dungeoncore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexarz.dungeoncore.core.GameCore;
import com.rexarz.dungeoncore.gameobjects.Player;
import com.rexarz.dungeoncore.scenes.DebugHud;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class GameScreen implements Screen {

    private GameCore game;
    private Player player;

    private World world;

    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private DebugHud debugHud;

    private Box2DDebugRenderer renderer;


    //    DEBUG
    Texture img;


    public GameScreen(GameCore gameCore) {

        world = new World(new Vector2(0,-9.8f),true);
        renderer = new Box2DDebugRenderer();

        game = gameCore;

        camera = new OrthographicCamera();

        viewport = new FillViewport(Constants.V_WIDTH / Constants.PPM, Constants.V_HEIGHT / Constants.PPM, camera);

//        camera.position.set(viewport.getWorldWidth() / Constants.PPM, viewport.getWorldHeight() / Constants.PPM, 0);

        batch = new SpriteBatch();

        player = new Player(world);


//        DEBUG
        debugHud = new DebugHud(batch);
        img = new Texture("white.png");
    }

    @Override
    public void show() {

    }

    public void update(float delta){
        world.step(1/60f,6,2);
        player.update(delta);
    }



    @Override
    public void render(float delta) {
        debugHud.update(delta);
        inputHandler(delta);
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render(world,camera.combined);

        camera.update();


        batch.setProjectionMatrix(camera.combined);

        batch.begin();
//        batch.draw(img, 0, 0);
        player.draw(batch);
        batch.end();


//        batch.setProjectionMatrix(debugHud.stage.getCamera().combined);
        debugHud.stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
        debugHud.viewport.update(width,height);
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


    public void inputHandler(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            System.out.println(camera.position.x);
            camera.position.x += 1 * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println(camera.position.x);
            camera.position.x -= 1 * delta;
        }
    }
}
