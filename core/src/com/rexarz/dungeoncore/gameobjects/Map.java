package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.rexarz.dungeoncore.scenes.DebugHud;
import com.rexarz.dungeoncore.utils.Constants;
import com.rexarz.dungeoncore.utils.Noise;
import com.rexarz.dungeoncore.utils.PerlinNoiseGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Serg on 10.05.2017.
 */
public class Map {
    public static float[][] map;
    public Tile[][] tileMap;

    private float width;
    private float height;
    private PerlinNoiseGenerator noise;
    private OrthographicCamera camera;


    public Map(float width, float height, OrthographicCamera camera) {
        this.width = width;
        this.height = height;
        this.camera = camera;

        noise = new PerlinNoiseGenerator();
        tileMap = new Tile[(int) width][(int) height];
        map = noise.generateWhiteNoise((int) width, (int) height);
        map = noise.generatePerlinNoise(map, 4);
    }

}
