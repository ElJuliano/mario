package com.jmolas.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolas.mario.MarioBros;

/**
 * Created by Julien on 06/02/2017.
 */

public class Brick extends InteractiveTileObject {

    public Brick(World world, TiledMap map, Rectangle rect) {
        super(world, map, rect);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("brick.collision", "");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
    }
}
