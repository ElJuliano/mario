package com.jmolas.mario.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jmolas.mario.MarioBros;
import com.jmolas.mario.scenes.Hud;


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

    //Box 2d variables
    private World world;
    private Box2DDebugRenderer b2dr;

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

        //Box 2d settting
        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fDef = new FixtureDef();
        Body body;

        //Create gound bodies / fixture
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

        //Create pipe/bodies fixture
        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }


        //Create brick bodies /fixture
        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

        //Create GiftBox bodies / fixture
        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2);
            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2, rect.getHeight() / 2);
            fDef.shape = shape;
            body.createFixture(fDef);
        }

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

        //Render our b2dr
        b2dr.render(world, gameCam.combined);

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
