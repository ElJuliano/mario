package com.jmolas.mario.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.scenes.Hud;

/**
 * Created by Julien on 06/02/2017.
 */

public class Brick extends InteractiveTileObject {

    public Brick(World world, TiledMap map, Rectangle rect, AssetManager m) {
        super(world, map, rect, m);

        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);
    }

    @Override
    public void onHeadHit() {
        Gdx.app.log("brick.collision", "");
        setCategoryFilter(MarioBros.DESTROYED_BIT);
        getCell().setTile(null);
        //incrementing the score
        Hud.addScore(200);
        //Playing the music
        manager.get("audio/sounds/Break.wav", Sound.class).play();
    }
}
