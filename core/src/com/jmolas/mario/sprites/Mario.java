package com.jmolas.mario.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.screens.PlayScreen;

/**
 * Created by Julien on 05/02/2017.
 */

public class Mario extends Sprite {

    public enum State {FALLING, RUNNING, JUMPING, STANDING};
    public State currentState;
    public State previousState;
    public Animation<TextureRegion> marioRun;
    public Animation<TextureRegion> marioJump;
    public boolean runningRight;
    public float stateTimer;

    public World world;
    public Body b2body;
    private TextureRegion marioStand;
    private TextureRegion marioFallOfEdges;

    public Mario (World w, PlayScreen screen) {
        super(screen.getAtlas().findRegion("NES - Super Mario Bros - Mario & Luigi"));
        this.world = w;

        //Initializing states
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i = 1; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), 80 + i * 15, 170, 15, 16));
        }
        marioRun = new Animation(0.1f, frames);
        frames.clear();

        for(int i = 4; i < 6; i++) {
            frames.add(new TextureRegion(getTexture(),80 + i * 15, 170, 15, 16));
        }
        marioJump = new Animation(0.1f, frames);

        marioStand = new TextureRegion(getTexture(), 80, 170 , 15, 16);
        marioFallOfEdges = new TextureRegion(getTexture(), 90, 170, 15, 16);

        defineMario();

        setBounds(0, 0, 16/MarioBros.PPM, 16/MarioBros.PPM);
        setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt){
        currentState = getState();

        TextureRegion region;
        switch(currentState) {
            case JUMPING:
                region = marioJump.getKeyFrame(stateTimer);
                break;
            case RUNNING:
                region = marioRun.getKeyFrame(stateTimer, true);
                break;
            case FALLING:
            case STANDING:
            default :
                region = marioStand;
                break;

        }

        if((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true, false);
            runningRight = false;
        }
        else if((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true, false);
            runningRight= true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;
        return region;
    }

    public State getState() {
        if(b2body.getLinearVelocity().y > 0 || (b2body.getLinearVelocity().y < 0 && previousState == State.JUMPING)) {
            return State.JUMPING;
        }
        else if(b2body.getLinearVelocity().y != 0){
            return State.FALLING;
        }
        else if(b2body.getLinearVelocity().x != 0){
            return State.RUNNING;
        }
        else {
            return State.STANDING;
        }
    }

    private void defineMario() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / MarioBros.PPM, 32 / MarioBros.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);
        FixtureDef fdef = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(6 / MarioBros.PPM);

        fdef.shape = circle;
        b2body.createFixture(fdef);

        EdgeShape head  =new EdgeShape();
        head.set(new Vector2(-2 / MarioBros.PPM, 6 / MarioBros.PPM), new Vector2(2 / MarioBros.PPM, 6 / MarioBros.PPM));
        fdef.shape = head;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("head");
    }
}
