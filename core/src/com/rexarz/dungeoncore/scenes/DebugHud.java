package com.rexarz.dungeoncore.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexarz.dungeoncore.utils.Constants;
import javafx.scene.control.Tab;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class DebugHud implements Disposable {

    public Stage stage;
    public Viewport viewport;

    private SpriteBatch batch;

    private Label fpsLabel;
    public int fpsCount;


    public DebugHud(SpriteBatch batch) {
        fpsCount = 0;
        this.batch = batch;

        viewport = new FitViewport(Constants.H_WIDTH, Constants.H_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);


        Label.LabelStyle style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        fpsLabel = new Label(fpsCount + "", style);
        fpsLabel.setFontScale(1f, 1f);
        fpsLabel.setPosition(0, Constants.H_HEIGHT - fpsLabel.getMinHeight());

        stage.addActor(fpsLabel);
    }


    public void update(float delta) {
        fpsCount = (int) (1 / delta);
        fpsLabel.setText("FPS: " + fpsCount);
    }

    @Override
    public void dispose() {

    }
}
