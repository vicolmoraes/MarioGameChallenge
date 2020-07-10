package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Minerador;

import java.awt.*;

public class Hud {
    public Stage stage;
    public Viewport viewport;
    private Integer worldTime;
    private float timeCount;
    private Integer score;

    com.badlogic.gdx.scenes.scene2d.ui.Label countDownLabel;
    com.badlogic.gdx.scenes.scene2d.ui.Label scoreLabel;
    com.badlogic.gdx.scenes.scene2d.ui.Label levelLabel;
    com.badlogic.gdx.scenes.scene2d.ui.Label timeLabel;
    com.badlogic.gdx.scenes.scene2d.ui.Label worldLabel;
    com.badlogic.gdx.scenes.scene2d.ui.Label mineradorLabel;

    public Hud(SpriteBatch sb){
        worldTime = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(Minerador.V_WIDTH,Minerador.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countDownLabel = new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("%03d",worldTime), new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel= new com.badlogic.gdx.scenes.scene2d.ui.Label(String.format("%06d",score), new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel= new com.badlogic.gdx.scenes.scene2d.ui.Label("TIME", new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel= new com.badlogic.gdx.scenes.scene2d.ui.Label("1-1", new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel= new com.badlogic.gdx.scenes.scene2d.ui.Label("WORLD", new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mineradorLabel= new com.badlogic.gdx.scenes.scene2d.ui.Label("MARIO", new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(mineradorLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table);


    }

}
