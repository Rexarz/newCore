package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.github.czyzby.noise4j.map.Grid;
import com.github.czyzby.noise4j.map.generator.cellular.CellularAutomataGenerator;
import com.github.czyzby.noise4j.map.generator.room.dungeon.DungeonGenerator;
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

    private CellularAutomataGenerator cellularGenerator;

    public static Grid grid;


    public Map(float width, float height, OrthographicCamera camera) {
        this.width = width;
        this.height = height;
        this.camera = camera;









        grid = new Grid(4096);


//        final DungeonGenerator dungeonGenerator = new DungeonGenerator();
//        dungeonGenerator.setRoomGenerationAttempts(200);
//        dungeonGenerator.setMaxRoomSize(25);
//        dungeonGenerator.setTolerance(6);
//        dungeonGenerator.setMinRoomSize(9);
//        dungeonGenerator.setWindingChance(0.5f); // More chaotic!
//        dungeonGenerator.setDeadEndRemovalIterations(5); // Introducing dead ends.
//        dungeonGenerator.setRandomConnectorChance(0f); // One way to solve the maze.
//        dungeonGenerator.setCorridorThreshold(0.2f);
//        dungeonGenerator.generate(grid);



        cellularGenerator = new CellularAutomataGenerator();
        cellularGenerator.setAliveChance(0.5f);
        cellularGenerator.setAliveChance(0.5f);
        cellularGenerator.setRadius(2);
        cellularGenerator.setBirthLimit(13);
        cellularGenerator.setDeathLimit(9);
        cellularGenerator.setIterationsAmount(6);
        cellularGenerator.generate(grid);
        Gdx.app.log("Map", "Map is generated");

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.println(grid.get(i,j));
            }

        }

    }

}
