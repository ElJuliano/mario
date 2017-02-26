package com.jmolas.mario.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.screens.PlayScreen;

/**
 * Created by Julien on 05/02/2017.
 */

public class Mario extends Sprite {

    public World world;
    public Body b2body;
    private TextureRegion marioStand;

    public Mario (World w, PlayScreen screen) {
        super(screen.getAtlas().findRegion("NES - Super Mario Bros - Mario & Luigi"));
        this.world = w;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 80, 170 , 16, 16);
        setBounds(0, 0, 16/MarioBros.PPM, 16/MarioBros.PPM);
        setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
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
    }
}
