package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by sergei.ivanishin on 5/17/2017.
 */
public abstract class Tile {
    private World world;
    private Texture texture;
    private float x;
    private float y;
    private float mapValue;


    public Tile(float x, float y, float mapValue, World world, Texture texture) {
        this.world = world;
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.mapValue = mapValue;
        defineTile();
    }

    protected void defineTile(){

    }


}
