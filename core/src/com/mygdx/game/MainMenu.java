
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainMenu implements Screen {

    //Fields
    static MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private final int LEVEL_WIDTH;
    private final int LEVEL_HEIGHT;

    Background background;
    Background background2;


    MenuHUD menu;
    HighScoresHUD highScoresHUD;

    //Constructor
    public MainMenu(MyGdxGame game) {
        this.game = game;

        //Equates variable values to that declared in MyGdxGame class
        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;
        background=new Background(0, 0, LEVEL_WIDTH, LEVEL_HEIGHT, Skin.background);
        background2=new Background(0, LEVEL_HEIGHT, LEVEL_WIDTH, LEVEL_HEIGHT, Skin.background);


        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

        menu = new MenuHUD(game.batch);
        highScoresHUD = new HighScoresHUD(game.batch);

    }

    //Methods
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update();

        //Clears Screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Allows images to be transparent
        game.batch.enableBlending();

        game.batch.begin();
        background.render(game.batch);
        background2.render(game.batch);
        //Rendering happens between begin and end

        game.batch.end();

        if(MenuHUD.showMenuHUD) {
            game.batch.setProjectionMatrix(menu.stage.getCamera().combined);
            menu.stage.draw();
        }
        if(MenuHUD.showHighScores){
            game.batch.setProjectionMatrix(highScoresHUD.stage.getCamera().combined);
            highScoresHUD.stage.draw();
        }
    }

    //Extra methods provided by Screen implement
    @Override
    public void resize(int width, int height) {
       MyGdxGame.menuViewport.update(width, height);
    }
    @Override
    public void pause() {
    }
    @Override
    public void resume() {
    }
    @Override
    public void hide() {
    }
    @Override
    public void dispose() {
    }

    //Updates game using update method in each class
    public void update() {
        background.update(4);
        background2.update(4);
        menu.updateMenu();
        highScoresHUD.updateHighScores();
    }
}
