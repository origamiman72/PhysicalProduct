
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {

    //Fields
    private MyGdxGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private final int LEVEL_WIDTH;
    private final int LEVEL_HEIGHT;
    float scrw=320;

    float scrh=480;



    Texture blockTexture = new Texture("mojave_dynamic_6.jpeg");
    Block[] blocks = new Block[constants.blockNumber];

    //Constructor
    public GameScreen(MyGdxGame game) {
        this.game = game;

        //Equates variable values to that declared in MyGdxGame class
        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;

        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, scrw, scrh);
        gameCam.update();
        gamePort = new FitViewport(scrw, scrh, gameCam);


        for(int i = 0; i < blocks.length; i++){
            blocks[i] = new Block(constants.blockwidth, constants.blockheight, (LEVEL_WIDTH-500)/2, (LEVEL_HEIGHT-200)/2, 1, type.BLOCK, blockTexture, game.batch);
        }

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
        //game.batch.enableBlending();

        game.batch.begin();
        //Rendering happens between begin and end
        blocks[1].render();
        game.batch.end();
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
        blocks[1].isActive=true;
        blocks[1].update();

    }
}
