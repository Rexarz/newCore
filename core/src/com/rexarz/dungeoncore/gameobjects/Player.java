package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.rexarz.dungeoncore.assets.AssetsLoader;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class Player extends Sprite {

    private TextureRegion playerStand;

    private World world;
    private Body body;


    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNING
    }

    public Player(World world) {
        super(AssetsLoader.atlas.findRegion("m_man"));
        this.world = world;


        playerStand = new TextureRegion(getTexture(), 160, 11, 34, 40);
        setBounds(0, 0, 0.1f,0.1f );
//        setBounds(0, 0, 34, 40);
        setPosition(0, 0);
        setRegion(playerStand);
        definePlayer();
        createPlatform();
    }

    private void createPlatform() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0,0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body platform = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.2f,0.1f);
        fixtureDef.shape = shape;
        platform.createFixture(fixtureDef);
    }


    public void definePlayer() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(0, 200 / Constants.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(0.1f);
        fixtureDef.restitution = 0.1f;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            body.applyLinearImpulse(new Vector2(0,10f),body.getWorldCenter(),true);
        }



        if (body.getPosition().y < 0) {
            body.getPosition().set(0,0);
            System.out.println("+");
        }
        System.out.println(body.getPosition());
    }
}
