package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
public class MenuHUD {

    Stage stage;
    Table displayTable;
    Label title;
    Label title2;
    Label title3;
    Label options;

    boolean startPressed=false;
    boolean hsPressed=false;

    int selectedOption=0;
    int numberOfValues=2;

    Vector2 mouseScreenPosition;
    Vector2 startmouseLocalPosition;
    Vector2 helpmouseLocalPosition;
    Vector2 optionsLocalPosition;

    static boolean showHighScores;
    static boolean showMenuHUD=true;

    public MenuHUD(SpriteBatch batch){
        mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());

        stage = new Stage(MyGdxGame.menuViewport, batch);

//        Display Table (score)
        displayTable = new Table();
        displayTable.setFillParent(true);
        displayTable.padTop(400);
        displayTable.top();

//        Labels take in: STRING, LabelStyle(Font,Color)
        title = new Label("CEP Tower Builder", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        title2 = new Label("START", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        title3 = new Label("High Scores", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));
        options = new Label("options", new Label.LabelStyle(constants.pixelFontborder, Color.WHITE));

        title.setFontScale(2F);
        title2.setFontScale(1.5F);
        title3.setFontScale(1.5F);
        options.setFontScale(1.5F);

        displayTable.add(title).expandX();

        displayTable.row();
        displayTable.center();
        displayTable.add(title2).expand().spaceTop(200).uniform();


        displayTable.row();
        displayTable.center();
        displayTable.add(title3).expand().uniform();

        displayTable.row();
        displayTable.add(options).expand().uniform();
        displayTable.padBottom(300);
//        displayTable.setHeight(500);


        stage.addActor(displayTable);

//        displayTable.setDebug(true);

    }

    public void updateMenu (){

        mouseScreenPosition.set(Gdx.input.getX(), Gdx.input.getY());
        startmouseLocalPosition = new Vector2(title2.screenToLocalCoordinates(mouseScreenPosition));
        mouseScreenPosition.set(Gdx.input.getX(), Gdx.input.getY());
        helpmouseLocalPosition = new Vector2(title3.screenToLocalCoordinates(mouseScreenPosition));
        mouseScreenPosition.set(Gdx.input.getX(), Gdx.input.getY());
        optionsLocalPosition = new Vector2(options.screenToLocalCoordinates(mouseScreenPosition));

        if(showMenuHUD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && selectedOption != numberOfValues) {
                selectedOption++;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && selectedOption != 1) {
                selectedOption--;
                if (selectedOption == -1) {
                    selectedOption = numberOfValues;
                }
            }

            if (title2.hit(startmouseLocalPosition.x, startmouseLocalPosition.y, true) != null || title3.hit(helpmouseLocalPosition.x, helpmouseLocalPosition.y, true) != null) {
                selectedOption = 0;
            }

//        System.out.println(title2.hit(mouseLocalPosition.x, mouseLocalPosition.y, false));
            if (title2.hit(startmouseLocalPosition.x, startmouseLocalPosition.y, true) != null || selectedOption == 1) {
                if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                    title2.setFontScale(2F);
                    startPressed=true;
                }else {
                    if (startPressed) {
                        Sounds.stopbg();
                        title2.setFontScale(1.5F);
                        MainMenu.game.setScreen(new GameScreen(MainMenu.game));
                        startPressed = false;
                    }
                }
            }

            if (title3.hit(helpmouseLocalPosition.x, helpmouseLocalPosition.y, true) != null || selectedOption == 2) {
//            System.out.println("yeet");
                if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//                MainMenu.game.setScreen(new GameScreen(MainMenu.game));
                    title3.setFontScale(2F);
                    hsPressed=true;
                } else {
                    if(hsPressed) {
                        hsPressed=false;
                        title3.setFontScale(1.5F);
                        System.out.println("help selected");
                        showHighScores = true;
                        showMenuHUD = false;
                    }
                }
            }

            if (options.hit(optionsLocalPosition.x, optionsLocalPosition.y, true) != null || selectedOption == 2) {
//            System.out.println("yeet");
                if (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
//                MainMenu.game.setScreen(new GameScreen(MainMenu.game));
                    options.setFontScale(2F);
                    hsPressed=true;
                } else {
                    if(hsPressed) {
                        hsPressed=false;
                        options.setFontScale(1.5F);
                        System.out.println("help selected");
                        showHighScores = true;
                        showMenuHUD = false;
                    }
                }
            }
        }


    }

}
