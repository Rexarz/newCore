package com.rexarz.dungeoncore.assets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class AssetsLoader {
    public static TextureAtlas atlas;

    public static List<TextureRegion> dirtTiles;
    public static Texture dirtTexture;


    public AssetsLoader() {

        dirtTiles = new ArrayList<TextureRegion>();
        atlas = new TextureAtlas("m_man.atlas");
        dirtTexture = new Texture("Tiles_0.png");


        System.out.println(dirtTexture.getWidth());
        float offset = 2f;
        float offsetX = 0;
        float offsetY = 0f;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 5; j++) {


                dirtTiles.add(new TextureRegion(dirtTexture, offsetX, offsetY, i, j));
                offsetY += 2f;
            }
            offsetX += 2f;
            offsetY = 0;
        }
    }
}
