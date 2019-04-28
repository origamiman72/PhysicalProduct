package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class RestartHUD {

    Stage stage;
    Label restart;
    Table displayTable;
    static boolean renderHUD=true;

    public RestartHUD(SpriteBatch batch){

        stage = new Stage(MyGdxGame.menuViewport, batch);
        restart = new Label("BUILD YOUR TOWER", new Label.LabelStyle(constants.pixelFont, Color.BLACK));

        restart.setFontScale(2F);
        displayTable = new Table();
        displayTable.setFillParent(true);
        displayTable.add(restart).center();
        stage.addActor(displayTable);

    }

}
