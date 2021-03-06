package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Mario;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Sprites.Personagem;
import com.mygdx.game.Tools.B2WorldCreator;

public class PlayScreen implements Screen {
    private Mario game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private World world;
    private Box2DDebugRenderer b2dr;
    private Personagem player;
    private TextureAtlas atlas;

    public PlayScreen(Mario game) {
        atlas = new TextureAtlas("mario_sprite.pack");
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(Mario.V_WIDTH / Mario.PPM, Mario.V_HEIGHT / Mario.PPM, gameCam);

        hud = new Hud(game.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Mario.PPM);
        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, -10), true);

        b2dr = new Box2DDebugRenderer();
        new B2WorldCreator(world, map);
        player = new Personagem(world, this);


    }
public TextureAtlas getAtlas(){
        return atlas;
}
    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player.body.applyLinearImpulse(new Vector2(0, 4f), player.body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && (player.body.getLinearVelocity().x <= 2)) {
            player.body.applyLinearImpulse(new Vector2(0.2f, 0), player.body.getWorldCenter(), true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && (player.body.getLinearVelocity().x >= -2)) {
            player.body.applyLinearImpulse(new Vector2(-0.2f, 0), player.body.getWorldCenter(), true);
        }
    }

    public void update(float dt) {
        handleInput(dt);

        world.step(1 / 60f, 6, 2);

        player.update(dt);

        gameCam.position.x = player.body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //renderiza o mapa do jogo
        renderer.render();

        //renderiza o limite dos objetos
        b2dr.render(world, gameCam.combined);

        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        player.draw(game.batch);
        game.batch.end();

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }
}
