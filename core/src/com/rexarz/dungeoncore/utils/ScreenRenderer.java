package com.rexarz.dungeoncore.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.rexarz.dungeoncore.gameobjects.Map;
import com.rexarz.dungeoncore.gameobjects.Tile;
import com.rexarz.dungeoncore.scenes.DebugHud;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei.ivanishin on 5/16/2017.
 */
public class ScreenRenderer {

    private OrthographicCamera camera;
    private World world;
    private Map map;
    private SpriteBatch batch;

    public List<Tile> tiles_0;

    public ScreenRenderer(OrthographicCamera camera, World world, Map map, SpriteBatch batch) {
        this.camera = camera;
        this.world = world;
        this.map = map;
        this.batch = batch;

        tiles_0 = new ArrayList<Tile>();
        generateTilesPull();



    }

    public void update(float delta) {
        int pullIndex = 0;
        DebugHud.drawedObjectsCount = 0;
        float x = camera.position.x;
        float y = camera.position.y;
//        float leftBorderX = camera.position.x - (camera.viewportWidth / 2f);
//        float rightBorderX = camera.position.x + (camera.viewportWidth / 2f);
        float leftBorderX = camera.position.x - (camera.viewportWidth / 2f) - 0.96f;
        float rightBorderX = camera.position.x + (camera.viewportWidth / 2f) + 0.96f;
        float upBorderY = camera.position.y + (camera.viewportHeight / 2f) + 0.96f;
        float downBorderY = camera.position.y - (camera.viewportHeight / 2f) - 0.96f;
//        float upBorderY = camera.position.x + (camera.viewportWidth / 2f);
//        float downBorderY = camera.position.x - (camera.viewportWidth / 2f);
//
        for (float i = leftBorderX; i < rightBorderX; i += 0.32f) {
            for (float j = downBorderY; j < upBorderY; j += 0.32f) {
                int tempX = (int) (i / 0.32f);
                int tempY = (int) (j / 0.32f);
                if (i >= 0 && tempX < Map.map.length ) {
                    if (j >= 0 && tempY < Map.map[tempX].length) {
                        if (Map.map[tempX][tempY] > 0.5f) {
                            tiles_0.get(pullIndex).changePosition(tempX * 0.32f, tempY * 0.32f);
                            tiles_0.get(pullIndex).draw(batch);
                            pullIndex++;
                            DebugHud.drawedObjectsCount++;
                        }
                    }
                }
            }
        }



//        for (int i = 0; i < Map.map.length; i++) {
//            for (int j = 0; j < Map.map[i].length; j++) {
//                if ()
//
//            }
//        }

    }


    public void generateTilesPull() {
        for (int i = 0; i < 3750; i++) {
            tiles_0.add(new Tile(0, 1000, world, 1));
        }

    }


}
