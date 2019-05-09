package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
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
public class GameOverHUD {

    Stage stage;
    Label score;
    Label scoreWas;
    int scorenumber=0;
    Label restart;
    Label highscore;
    Label menu;
    boolean returnPressed=false;

    Vector2 mouseScreenPosition;
    Vector2 returnLocal;

    int touchcounter=0;

    public GameOverHUD(SpriteBatch batch){
        stage = new Stage(MyGdxGame.menuViewport, batch);

        mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        Table displayTable = new Table();
        displayTable.padTop(100);
        displayTable.top();
        displayTable.setFillParent(true);

        //Labels take in: STRING, LabelStyle(Font,Color)
        score = new Label("Game Over", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        scoreWas = new Label("Your score was ", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        restart = new Label("Touch to Restart", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        highscore = new Label("The High Score is ", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        menu = new Label("Return to Menu", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));


        score.setFontScale(2F);
        scoreWas.setFontScale(1.5F);
        restart.setFontScale(1.5F);
        highscore.setFontScale(1.4F);
        menu.setFontScale(1.5F);

        displayTable.add(score);

        displayTable.row();
        displayTable.add(scoreWas).spaceTop(50);

        displayTable.row();
        displayTable.add(highscore).spaceTop(50);

        displayTable.row();
        displayTable.add(restart).spaceTop(200);
        displayTable.row();
        displayTable.add(menu).bottom().expand().uniform();
        displayTable.padBottom(300);

        stage.addActor(displayTable);

    }

    public void updateScore (String s, int highScore){
        mouseScreenPosition.set(Gdx.input.getX(), Gdx.input.getY());
        returnLocal = new Vector2(menu.screenToLocalCoordinates(mouseScreenPosition));

        scoreWas.setText("Your score was " + s);
        highscore.setText("The High Score is " + highScore);

        if(touchcounter>15){
            if(menu.hit(returnLocal.x, returnLocal.y, true) != null && Gdx.input.isTouched()){
                menu.setFontScale(2F);
                returnPressed=true;
            }else if(returnPressed){
                returnPressed=false;
                MainMenu.game.setScreen(new MainMenu(MainMenu.game));
                touchcounter = 0;
            }else {
                if (Gdx.input.justTouched()) {
                    GameScreen.restartInProgress = true;
                    touchcounter = 0;
                }
            }
        }else{
            touchcounter++;
        }

    }

}
