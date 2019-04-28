package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
//import com.badlogic.gdx.utils.viewport.ExtendViewport;
//import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.File;

/**
 * Created by peter on 3/25/17.
 */
public class HUD {

    Stage stage;
    Label score;
    Label scoreWas;
    int scorenumber=0;
    Label restart;
    Label highscore;

    public HUD(SpriteBatch batch){

        stage = new Stage(MyGdxGame.menuViewport, batch);

        //Display Table (score)
        Table displayTable = new Table();
        displayTable.padTop(100);
        displayTable.top();
        displayTable.setFillParent(true);

        //Labels take in: STRING, LabelStyle(Font,Color)
        score = new Label("Score: " + scorenumber, new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        scoreWas = new Label("", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        restart = new Label("", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        highscore = new Label("", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        score.setFontScale(2F);
        scoreWas.setFontScale(1.5F);
        restart.setFontScale(1.5F);
        highscore.setFontScale(1.4F);

        displayTable.add(score);

        displayTable.row();
        displayTable.add(scoreWas).spaceTop(50);

        displayTable.row();
        displayTable.add(highscore).spaceTop(50);

        displayTable.row();
        displayTable.add(restart).spaceTop(200);

        stage.addActor(displayTable);

    }

    public void updateScore (String s, boolean gameOver, int highScore){
        if(!gameOver) {
            score.setText("Score: " + s);
            scoreWas.setText("");
            restart.setText("");
            highscore.setText("");
        }else{
            score.setText("Game Over");
            scoreWas.setText("Your score was " + s);
            highscore.setText("The High Score is " + highScore);
            restart.setText("Press Enter to Restart");
        }
    }

}
