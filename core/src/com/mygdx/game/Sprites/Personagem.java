package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Mario;

public class Personagem extends Sprite {
    public World world;
    public Body body;

    public Personagem(World world){
        this.world = world;
        defineMario();
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32/ Mario.PPM,32/ Mario.PPM);
        bdef.type= BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ Mario.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

    }

}
