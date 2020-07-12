package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Mario;
import com.mygdx.game.Screens.PlayScreen;

public class Personagem extends Sprite {
    public World world;
    public Body body;
    private TextureRegion marioStand;

    public Personagem(World world, PlayScreen screen){
        super(screen.getAtlas().findRegion("mario"));
        this.world = world;
        defineMario();
        marioStand = new TextureRegion(getTexture(), 210,0,20,20);
    setBounds(0,0,20/Mario.PPM,20/Mario.PPM);
    setRegion(marioStand);
    }

    public void update(float dt){
        setPosition(body.getPosition().x-getWidth()/2, body.getPosition().y - getHeight()/2);
    }

    public void defineMario(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(40/ Mario.PPM,40/ Mario.PPM);
        bdef.type= BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/ Mario.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);

    }

}
