package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.rexarz.dungeoncore.utils.PerlinNoiseGenerator;

/**
 * Created by Serg on 10.05.2017.
 */
public class Map {
    public static float[][] map;
    public OldTile[][] oldTileMap;

    private float width;
    private float height;
    private PerlinNoiseGenerator noise;
    private OrthographicCamera camera;


    public Map(float width, float height, OrthographicCamera camera) {
        this.width = width;
        this.height = height;
        this.camera = camera;

        noise = new PerlinNoiseGenerator();
        oldTileMap = new OldTile[(int) width][(int) height];
        map = noise.generateWhiteNoise((int) width, (int) height);
        map = noise.generatePerlinNoise(map, 4);
    }

}
