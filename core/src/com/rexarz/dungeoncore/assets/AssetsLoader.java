package com.rexarz.dungeoncore.assets;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class AssetsLoader {
    public static TextureAtlas atlas;


    public AssetsLoader() {
        atlas = new TextureAtlas("m_man.atlas");
    }
}
