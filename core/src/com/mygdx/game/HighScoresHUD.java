package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.GameScreen;
import com.mygdx.game.constants;

public class HighScoresHUD {

    Stage stage;
    Table displayTable;
    Label highscoretext;
    Label highScore;
    String highscore;

    Vector2 mouseScreenPosition;


    static boolean showHighScores;
    static boolean showMenuHUD = true;



    public HighScoresHUD(SpriteBatch batch) {
        mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        stage = new Stage(MyGdxGame.menuViewport, batch);

//        Display Table (score)
        displayTable = new Table();
        displayTable.setFillParent(true);
        displayTable.padTop(400);
        displayTable.top();

//        Labels take in: STRING, LabelStyle(Font,Color)
        highscoretext = new Label("High Scores", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        highScore = new Label("The High Score is 0", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));

        highscoretext.setFontScale(2F);
        highScore.setFontScale(1.5F);
        displayTable.add(highscoretext).expandX();

        displayTable.row();
        displayTable.center();
        displayTable.add(highScore).expand().spaceBottom(200).uniform();

        displayTable.padBottom(300);
//        displayTable.setHeight(500);


        stage.addActor(displayTable);

//        displayTable.setDebug(true);

    }


    public void updateHighScores(){
        try {
            highscore=Base64Coder.decodeString(GameScreen.prefs.getString("highscore"));
//            System.out.println("score updated");
        }catch (Exception e){
            highscore="0";
        }
        highScore.setText("The High Score is " +highscore);
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            MenuHUD.showHighScores=false;
            MenuHUD.showMenuHUD=true;
            System.out.println("yeat");
        }
    }
}