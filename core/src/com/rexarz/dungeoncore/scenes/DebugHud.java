package com.rexarz.dungeoncore.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class DebugHud implements Disposable {

    public Stage stage;
    public Viewport viewport;

    private SpriteBatch batch;
    private World world;

    private Label fpsLabel;
    private Label worldBodyCount;

    public static Label drawedObjects;
    public static int drawedObjectsCount;

    public int fpsCount;



    public DebugHud(SpriteBatch batch, World world) {
        fpsCount = 0;
        drawedObjectsCount = 0;
        this.batch = batch;
        this.world = world;

        viewport = new FitViewport(Constants.V_WIDTH, Constants.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);


        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        fpsLabel = new Label(fpsCount + "", style);
        worldBodyCount = new Label(world.getBodyCount() + "", style);
        drawedObjects = new Label(drawedObjectsCount + "",style);
        fpsLabel.setFontScale(1f, 1f);
        fpsLabel.setPosition(0, Constants.V_HEIGHT - fpsLabel.getMinHeight());
        worldBodyCount.setFontScale(1f, 1f);
        worldBodyCount.setPosition(0, Constants.V_HEIGHT - fpsLabel.getMinHeight() - worldBodyCount.getMinHeight());
        drawedObjects.setFontScale(1f, 1f);
        drawedObjects.setPosition(0, Constants.V_HEIGHT - fpsLabel.getMinHeight() - worldBodyCount.getMinHeight() - drawedObjects.getMinHeight());

        stage.addActor(fpsLabel);
        stage.addActor(worldBodyCount);
        stage.addActor(drawedObjects);
    }


    public void update(float delta) {
        fpsCount = (int) (1 / delta);

        fpsLabel.setText("FPS: " + fpsCount);
        worldBodyCount.setText("Physical objects: " + world.getBodyCount());
        drawedObjects.setText("drawed objects: " + drawedObjectsCount);


    }

    @Override
    public void dispose() {

    }
}
