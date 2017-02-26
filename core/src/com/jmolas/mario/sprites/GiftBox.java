package com.jmolas.mario.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolas.mario.MarioBros;

/**
 * Created by Julien on 06/02/2017.
 */

public class GiftBox extends InteractiveTileObject {

    public GiftBox(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
    }


}