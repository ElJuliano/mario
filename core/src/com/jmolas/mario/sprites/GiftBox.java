package com.jmolas.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.scenes.Hud;
import com.jmolas.mario.screens.PlayScreen;

/**
 * Created by Julien on 06/02/2017.
 */

public class GiftBox extends InteractiveTileObject {

    private static TiledMapTileSet tileSet;
    private final int BLANK_GIFT = 28;

    public GiftBox(PlayScreen screen, Rectangle bounds, AssetManager m) {
        super(screen, bounds, m);
        tileSet = map.getTileSets().getTileSet("Mario");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.GIFT_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("gift.collision", "");
        if(getCell().getTile().getId() != BLANK_GIFT) {
            Hud.addScore(100);
            manager.get("audio/sounds/Coin.wav", Sound.class).play();
        }
        else {
            manager.get("audio/sounds/Bump.wav", Sound.class).play();
        }
        getCell().setTile(tileSet.getTile(BLANK_GIFT));
    }
}
