package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.rexarz.dungeoncore.scenes.DebugHud;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by Serg on 10.05.2017.
 */
public class Map {
    public Tile[][] map;

    private float width;
    private float height;

    protected World world;
    private OrthographicCamera camera;


    //    Object count
    private int objectCount;

    public Map(float width, float height, World world, OrthographicCamera camera) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.camera = camera;
        map = new Tile[(int) width][(int) height];
        generateMap();
    }

    private void generateMap() {
        for (float i = 0; i < width; i += 16) {
            for (float j = 0; j < height; j += 16) {
                Tile tile = new Tile(i,j,world);
                tile.body.setActive(false);
                map[(int) i][(int) j] = tile;
            }
        }
    }

    public void draw(SpriteBatch batch) {
        objectCount = 0;
        for (float i = 0; i < width; i += 16) {
            for (float j = 0; j < height; j += 16) {
//                map[i][j] = new Tile(i, j, world);
                if (inViewport(i, j)) {
                    map[(int) i][(int) j].draw(batch);
                    map[(int) i][(int) j].body.setActive(true);
                    objectCount++;
                }
                else {
                    map[(int) i][(int) j].body.setActive(false);
                }

            }
        }
        DebugHud.drawedObjectsCount = objectCount;
    }

    private boolean inViewport(float i, float j) {
        boolean result = false;
        if (i / Constants.PPM > camera.position.x - (camera.viewportWidth / 2) && i / Constants.PPM < camera.position.x + (camera.viewportWidth / 2)) {
            if (j / Constants.PPM > camera.position.y - (camera.viewportHeight / 2) && j / Constants.PPM < camera.position.y + (camera.viewportWidth / 2)) {
                result = true;
                return result;
            }
        }
        return result;
    }
}
