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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.File;

/**
 * Created by peter on 3/25/17.
 */
public class MenuHUD {

    Stage stage;
    Viewport viewport;
    Table displayTable;
    Label title;
    Label title2;
    Label title3;

    int scorenumber=0;
    Vector2 mouseScreenPosition;
    Vector2 startmouseLocalPosition;



    public MenuHUD(SpriteBatch batch){
        mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());


        BitmapFont pixelFont = new BitmapFont(
                Gdx.files.internal("pixelOperatorHB.fnt"),
                false
        );

        viewport = new ScreenViewport(new OrthographicCamera());
        stage = new Stage(viewport, batch);

//        Display Table (score)
        displayTable = new Table();
        displayTable.setFillParent(true);
        displayTable.padTop(400);
        displayTable.top();

//        Labels take in: STRING, LabelStyle(Font,Color)
        title = new Label("CEP Tower Builder", new Label.LabelStyle(pixelFont, Color.WHITE));
        title2 = new Label("START", new Label.LabelStyle(pixelFont, Color.WHITE));
        title3 = new Label("Help", new Label.LabelStyle(pixelFont, Color.WHITE));

        title.setFontScale(2F);
        displayTable.add(title).expandX();

        displayTable.row();
        displayTable.center();
        displayTable.add(title2).expand().spaceTop(200).uniform();


        displayTable.row();
        displayTable.center();
        displayTable.add(title3).expand().uniform();
        displayTable.padBottom(300);
        displayTable.setHeight(500);


        stage.addActor(displayTable);

//        displayTable.setDebug(true);

    }

    public void updateMenu (){

        mouseScreenPosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
        startmouseLocalPosition = new Vector2(title2.screenToLocalCoordinates(mouseScreenPosition));

//        System.out.println(title2.hit(mouseLocalPosition.x, mouseLocalPosition.y, false));
        if(title2.hit(startmouseLocalPosition.x, startmouseLocalPosition.y, true) !=null){
            title2.setFontScale(2F);
            System.out.println("yeet");
            if(Gdx.input.justTouched()){
                MainMenu.game.setScreen(new GameScreen(MainMenu.game));
            }

        }else{
            title2.setFontScale(1F);
        }
    }

}
