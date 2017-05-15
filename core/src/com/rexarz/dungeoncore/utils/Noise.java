package com.rexarz.dungeoncore.utils;

import java.util.Random;

/**
 * Created by sergei.ivanishin on 5/15/2017.
 */
public class Noise {

    private float[][] generatedNoise;

    public float[][] generateWhiteNoise(int width, int height) {
        Random random = new Random(0); //Seed to 0 for testing
        float[][] noise = getEmptyArray(width, height);
        generatedNoise = noise;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                noise[i][j] = (float) random.nextDouble() % 1;

            }
        }

        return noise;
    }

    private float[][] getEmptyArray(int width, int height) {
        float[][] noise = new float[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                noise[i][j] = 0;

            }
        }

        return noise;
    }

    public void drawMap() {
        for (int i = 0; i < generatedNoise.length; i++) {
            for (int j = 0; j < generatedNoise[i].length; j++) {
                if (generatedNoise[i][j] > 0.5)
                    System.out.print("1");
                else
                    System.out.print("0");
            }
            System.out.println();
        }
    }
}
