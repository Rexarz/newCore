package com.rexarz.dungeoncore.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by Serg on 10.05.2017.
 */
public class Tile extends Sprite {
    private World world;
    private Texture texture;
    private float x;
    private float y;


    public Body body;
    public float value;


    public Tile(float x, float y, World world, float value) {
        this.x = x;
        this.y = y;
        this.world = world;
        this.value = value;

        if (value > 0.5f) {
            texture = new Texture("tiles/tile0.jpg");
            setRegion(texture);
            setBounds(0, 0, 16f / Constants.PPM, 16f / Constants.PPM);

            BodyDef bodyDef = new BodyDef();
//            bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
            bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
            bodyDef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(getWidth() / 2, getHeight() / 2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

            setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
        } else {
            setBounds(0, 0, 16f / Constants.PPM, 16f / Constants.PPM);

            BodyDef bodyDef = new BodyDef();
            bodyDef.position.set(x / Constants.PPM, y / Constants.PPM);
            bodyDef.type = BodyDef.BodyType.StaticBody;

            body = world.createBody(bodyDef);

            FixtureDef fixtureDef = new FixtureDef();
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(getWidth() / 2, getHeight() / 2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);

            setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
        }

    }

    public Vector2 getPosition() {
        return new Vector2(body.getPosition());
    }

    public void changePosition(float x, float y) {
//        body.getPosition().set(x,y);
//        body.getPosition().set(new Vector2(x,y));
        body.setTransform(x, y, body.getAngle());
//        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 2));
//        System.out.println(body.getPosition());
    }


}
