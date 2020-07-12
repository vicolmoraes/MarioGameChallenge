package com.mygdx.game.Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Mario;

import java.awt.*;

public abstract class InteractiveTileObject {
    private World world;
    private TiledMap map;
    private TiledMapTile tile;
    private Rectangle bounds;
    private Body body;

    public InteractiveTileObject(World world, TiledMap map, com.badlogic.gdx.math.Rectangle rectangle) {
        this.world = world;
        this.map = map;
        this.bounds = rectangle;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / Mario.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / Mario.PPM);
        body = world.createBody(bdef);
        shape.setAsBox((rectangle.getWidth() / 2) / Mario.PPM, (rectangle.getHeight() / 2) / Mario.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

    }

}
