package com.jmolas.mario.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.scenes.Hud;

import java.io.File;

/**
 * Created by Julien on 30/01/2017.
 */

public class PlayScreen implements Screen {

    private MarioBros game;

    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Playscreen backgound
    private TmxMapLoader mapLoader;
    private OrthogonalTiledMapRenderer mapRenderer;
    private TiledMap map;


    public PlayScreen(MarioBros game){
        this.game = game;
        //Create a cam to follow Mario through the cam
        gameCam = new OrthographicCamera();

        //Create FitViewPort to maintain visual aspect ratio despite screen size
        gamePort = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, gameCam);

        //Create Hud to display score/time/level info
        hud = new Hud(game.batch);

        //Map var settings
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);

        //Setting the cam position
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }



    @Override
    public void show() {

    }


    public void update(float dt) {
        handleInput(dt);
        gameCam.update();
        mapRenderer.setView(gameCam);
    }

    public void handleInput(float dt) {
        if (Gdx.input.isTouched()) {
            gameCam.position.x  += 100 * dt;
        }

    }

    @Override
    public void render(float delta) {
        update(delta);
        //clearing color and screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mapRenderer.render();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
