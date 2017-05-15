package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rexarz.dungeoncore.scenes.DebugHud;
import com.rexarz.dungeoncore.utils.Constants;
import com.rexarz.dungeoncore.utils.Noise;
import com.rexarz.dungeoncore.utils.PerlinNoiseGenerator;

/**
 * Created by Serg on 10.05.2017.
 */
public class Map {
    public float[][] map;
    public Tile[][] tileMap;

    private float width;
    private float height;

    private PerlinNoiseGenerator noise;

    protected World world;
    private OrthographicCamera camera;

    //    DEBUG ONLY
    private boolean isDone = false;


    //    Object count
    private int objectCount;

    public Map(float width, float height, World world, OrthographicCamera camera) {
        this.world = world;
        this.width = width;
        this.height = height;
        this.camera = camera;

        noise = new PerlinNoiseGenerator();
        tileMap = new Tile[(int) width][(int) height];
        map = noise.generateWhiteNoise((int) width, (int) height);
        map = noise.generatePerlinNoise(map, 1);

//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }

//        noise.drawMap();

        generateMap();
    }

    private void generateMap() {
        float xOffset = 0;
        float yOffset = 0;
        for (float i = 0; i < map.length; i++) {
            for (float j = 0; j < map[(int) i].length; j++) {
                Tile tile = new Tile(xOffset, yOffset, world, map[(int) i][(int) j]);
                yOffset += 16f;
                if (tile.value > 0.9f) {
                    tile.body.setActive(false);
                    tileMap[(int) i][(int) j] = tile;
                }

            }
            xOffset += 16f;
            yOffset = 0;
        }
        isDone = true;
    }

    public void draw(SpriteBatch batch) {
        objectCount = 0;
        if (isDone) {
            for (int i = 0; i < tileMap.length; i++) {
                for (int j = 0; j < tileMap[i].length; j++) {
//                map[i][j] = new Tile(i, j, world);
                    if(tileMap[i][j].body == null){
                        System.out.println("null");
                    }else if (inViewport(tileMap[i][j].body) && tileMap[i][j].value > 0.9f) {
                        if (tileMap[i][j].value > 0.9f) {
                            tileMap[i][j].draw(batch);
                            tileMap[i][j].body.setActive(true);
                            objectCount++;
                        }
                    } else {
                        tileMap[i][j].body.setActive(false);
                    }

                }
            }
            DebugHud.drawedObjectsCount = objectCount;
        }
    }

    private boolean inViewport(Body body) {
        boolean result = false;
        if (body.getPosition().x > camera.position.x - (camera.viewportWidth / 2f) && body.getPosition().x < camera.position.x + (camera.viewportWidth / 2f)) {
            if (body.getPosition().y > camera.position.y - (camera.viewportHeight / 2f) && body.getPosition().y < camera.position.y + (camera.viewportWidth / 2f)) {
                result = true;
                return result;
            }
        }
        return result;
    }
}
