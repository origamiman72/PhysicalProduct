
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
    int minimum;
    int blockunder = minimum-1;

    Texture testTexture = new Texture("mojave_dynamic_12.jpeg");


    Texture blockTexture = new Texture("mojave_dynamic_6.jpeg");
    Block[] blocks = new Block[constants.blockNumber];

    int moveDownSpeed = 5;
    int counter = 0;

    //Constructor
    public GameScreen(MyGdxGame game) {
        this.game = game;

        //Equates variable values to that declared in MyGdxGame class
        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;

        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, LEVEL_WIDTH, LEVEL_HEIGHT);
        gameCam.update();
        gamePort = new FitViewport(LEVEL_WIDTH, LEVEL_HEIGHT, gameCam);

        Block.xborder=LEVEL_WIDTH;


        for(int i = 0; i < blocks.length; i++){
            blocks[i] = new Block(constants.blockwidth, constants.blockheight, (LEVEL_WIDTH-constants.blockwidth)/2, (LEVEL_HEIGHT-constants.blockheight)/2, type.BLOCK, blockTexture, game.batch);
            blocks[i].y = (i-2) * constants.blockheight;
        }

        blocks[4].isActive=true;

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

        gameCam.update();
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        //Rendering happens between begin and end
        for(int i = 0; i<blocks.length; i++){
            blocks[i].render();
        }
        game.batch.end();
    }

    //Extra methods provided by Screen implement
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

        for(int i=0; i<blocks.length; i++) {
            if(blocks[i].isActive){
                blocks[i].update();
            }
        }
        for(int i=0; i<blocks.length; i++){
            if(blocks[i].isActive){
                blockunder=i-1;
                if(blockunder==-1){
                    blockunder=blocks.length-1;
                }
                blocks[blockunder].texture=testTexture;
                if(blocks[i].isCollide(blocks[blockunder])){
                    blocks[i].handleCollision(blocks[blockunder]);
                }else{
                    System.out.println("noboi");
                }
            }
        }
        if(Block.movetoNextTurn){
            if(counter<constants.blockheight/moveDownSpeed) {
                for (int i = 0; i < blocks.length; i++) {
                    blocks[i].y -= moveDownSpeed;
                }
                counter++;
            }else{
                counter=0;
                Block.movetoNextTurn=false;
                moveDownSpeed=5;
                blocks[minimum].y = (blocks.length-3) * constants.blockheight;
                blocks[minimum].isActive=true;

                if(minimum<blocks.length-1){
                    minimum++;
                }else{
                    minimum=0;
                }



            }

        }


//        TODO: Tower size thinning, make collision detection affect game, Textures

    }
}
