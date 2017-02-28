package com.jmolas.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.scenes.Hud;

/**
 * Created by Julien on 06/02/2017.
 */

public class GiftBox extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_GIFT = 28;

    public GiftBox(World world, TiledMap map, Rectangle bounds) {
        super(world, map, bounds);
        tileSet = map.getTileSets().getTileSet("Mario");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.GIFT_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("gift.collision", "");
        if(getCell().getTile().getId() != BLANK_GIFT) {
            Hud.addScore(100);
        }
        getCell().setTile(tileSet.getTile(BLANK_GIFT));
    }
}
