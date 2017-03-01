package com.jmolas.mario;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jmolas.mario.screens.PlayScreen;

public class MarioBros extends Game {
	public static final int V_WIDTH = 400;
	public static final int V_HEIGHT = 208;
	//Pixel per meter
	public static final float PPM = 100;
	public SpriteBatch batch;

	//filters
	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short GIFT_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;

	/* The asset Manager should never be used in a static way*/
	public AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		//Loading main music
		manager.load("audio/music/mainTheme.mp3", Music.class);
		//Loading sounds
		manager.load("audio/sounds/1up.wav", Sound.class);
		manager.load("audio/sounds/Beep.wav", Sound.class);
		manager.load("audio/sounds/Big_Jump.wav", Sound.class);
		//manager.load("audio/sounds/Browser_Die.wav", Sound.class);
		manager.load("audio/sounds/Break.wav", Sound.class);
		manager.load("audio/sounds/Bump.wav", Sound.class);
		manager.load("audio/sounds/Coin.wav", Sound.class);
		manager.load("audio/sounds/Die.wav", Sound.class);
		manager.load("audio/sounds/Fire_Ball.wav", Sound.class);
		manager.load("audio/sounds/Flagpole.wav", Sound.class);
		manager.load("audio/sounds/Game_Over.wav", Sound.class);
		manager.load("audio/sounds/Item.wav", Sound.class);
		manager.load("audio/sounds/Jump.wav", Sound.class);
		manager.load("audio/sounds/Kick.wav", Sound.class);
		manager.load("audio/sounds/Pause.wav", Sound.class);
		manager.load("audio/sounds/Powerup.wav", Sound.class);
		manager.load("audio/sounds/Skid.wav", Sound.class);
		manager.load("audio/sounds/Squish.wav", Sound.class);
		manager.load("audio/sounds/Thwomp.wav", Sound.class);
		manager.load("audio/sounds/Vine.wav", Sound.class);
		manager.load("audio/sounds/Warp.wav", Sound.class);

		manager.finishLoading();
		setScreen(new PlayScreen(this, manager));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
