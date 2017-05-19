package com.rexarz.dungeoncore.gameobjects;

import box2dLight.PointLight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.rexarz.dungeoncore.assets.AssetsLoader;
import com.rexarz.dungeoncore.screens.GameScreen;
import com.rexarz.dungeoncore.utils.Constants;

/**
 * Created by sergei.ivanishin on 5/5/2017.
 */
public class Player extends Sprite {

    private TextureRegion playerStand;
    private Animation<TextureRegion> playerIdle;
    private Array<TextureRegion> frames;
    private World world;
    public Body body;
    private float stateTime;

    private State currentState;
    private State previousState;

    //    Constants
    private final float MAX_SPEED = 3f;
    private final float JUMP_FORCE = 6f;

    //    Boolean
    private boolean isRight;


    public enum State {
        FALLING,
        JUMPING,
        STANDING,
        RUNNING
    }

    private PointLight pointLight;

    public Player(World world) {
        super(AssetsLoader.atlas.findRegion("m_man"));
        this.world = world;

        playerStand = new TextureRegion(new Texture("pixelMan_atlas.png"), 0, 0, 15, 32);

        frames = new Array<TextureRegion>();

        for (int i = 0; i < 2; i++) {
            frames.add(new TextureRegion(new Texture("pixelMan_atlas.png"), i * 18, 0, 18, 36));
        }
        playerIdle = new Animation<TextureRegion>(1 / 4f, frames);
        stateTime = 0;

        setBounds(0, 0, 18f / Constants.PPM, 36f / Constants.PPM);
        setPosition(0, 0);



        definePlayer();
        createPlatform();

        pointLight = new PointLight(GameScreen.rayHandler, 200);

        isRight = true;
    }

    private void createPlatform() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(5, 0);
        bodyDef.type = BodyDef.BodyType.StaticBody;

        Body platform = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10f, 0.1f);
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
        shape.setRadius(getHeight() / 3);
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(getWidth() / 2, getHeight() / 2);

        fixtureDef.restitution = 0.1f;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);

    }


    public void update(float delta) {
        pointLight.setPosition(body.getPosition().x,body.getPosition().y);


        stateTime += Gdx.graphics.getDeltaTime();

        setPosition(body.getPosition().x - (getWidth() / 2), body.getPosition().y - (getHeight() / 4));
        setRegion(playerIdle.getKeyFrame(stateTime, true));

        if (body.getLinearVelocity().x < 0 || !isRight) {
            isRight = false;
            flip(true, false);
        }
        if (body.getLinearVelocity().x > 0 || isRight) {
            isRight = true;
            flip(true, false);
        }

        playerMovements();


    }

    private void playerMovements() {

        if ((Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) && body.getLinearVelocity().x < MAX_SPEED) {
            body.applyLinearImpulse(new Vector2(0.2f, 0), body.getWorldCenter(), true);

//            if (isFlipX() && !isRight){
//                isRight = true;
//                flip(true,false);
//            }

        }
        if ((Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) && body.getLinearVelocity().x > -MAX_SPEED) {
            body.applyLinearImpulse(new Vector2(-0.2f, 0), body.getWorldCenter(), true);

//            if (!isFlipX() && isRight){
//                isRight = false;
//                flip(true,false);
//            }

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            body.applyLinearImpulse(new Vector2(0, JUMP_FORCE), body.getWorldCenter(), true);
        }


    }
}
