
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

    MenuHUD menu;

    //Constructor
    public MainMenu(MyGdxGame game) {
        this.game = game;

        //Equates variable values to that declared in MyGdxGame class
        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;

        gameCam = new OrthographicCamera();
        gamePort = new ExtendViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

         menu = new MenuHUD(game.batch);

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
        //Rendering happens between begin and end

        game.batch.end();
        game.batch.setProjectionMatrix(menu.stage.getCamera().combined);
        menu.stage.draw();
    }

    //Extra methods provided by Screen implement
    @Override
    public void resize(int width, int height) {
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
        menu.updateMenu();
    }
}
