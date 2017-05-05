package com.rexarz.dungeoncore.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexarz.dungeoncore.core.GameCore;
import com.rexarz.dungeoncore.scenes.DebugHud;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class GameScreen implements Screen {

    private GameCore game;


    private Viewport viewport;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private DebugHud debugHud;


    //    DEBUG
    Texture img;


    public GameScreen(GameCore gameCore) {
        game = gameCore;

        camera = new OrthographicCamera();

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, camera);

        camera.position.set(Constants.V_WIDTH / 2, Constants.V_HEIGHT / 2, 0);

        batch = new SpriteBatch();


//        DEBUG
        debugHud = new DebugHud(batch);
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        debugHud.update(delta);
        inputHandler(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();


        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();


        batch.setProjectionMatrix(debugHud.stage.getCamera().combined);
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
            camera.position.x += 1;
            System.out.println("+");
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            System.out.println(camera.position.x);
            camera.position.x -= 1;
            System.out.println("+");
        }
    }
}
