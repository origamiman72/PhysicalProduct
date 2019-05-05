
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
    int active;

    int touchcounter = 0;

    int score;
    int fivesPrevious =0;
    int fivesPassed =0;
    static int highScore=0;
    Background background;

    RestartHUD restartHUD;

    static Preferences prefs = Gdx.app.getPreferences("game preferences");

    boolean restartInProgress=true;

    int counter2=0;

    Texture[] blocktextures = Skin.getBlocktextures();

    HUD hud;
    boolean renderWhite;
    boolean renderRestartWhite=false;

    Texture testTexture = new Texture("mojave_dynamic_12.jpeg");

    Block[] blocks = new Block[constants.blockNumber];

    static int moveDownSpeed = 5;
    int counter = 0;
    boolean ranOnce=false;
    Background background2;
    boolean gameOver=false;


    Texture white = new Texture("white.jpg");

    //Constructor
    public GameScreen(MyGdxGame game) {

        this.game = game;
        active = blocks.length-1;
        restartHUD = new RestartHUD(game.batch);
        hud = new HUD(game.batch);
        //Equates variable values to that declared in MyGdxGame class
        LEVEL_WIDTH = MyGdxGame.V_WIDTH;
        LEVEL_HEIGHT = MyGdxGame.V_HEIGHT;

        background=new Background(0, 0, LEVEL_WIDTH, LEVEL_HEIGHT, Skin.background);
        background2=new Background(0, LEVEL_HEIGHT, LEVEL_WIDTH, LEVEL_HEIGHT, Skin.background);


        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, LEVEL_WIDTH, LEVEL_HEIGHT);
        gameCam.update();
        gamePort = new FitViewport(LEVEL_WIDTH, LEVEL_HEIGHT,gameCam);
//        System.out.println(Gdx.graphics.getWidth()+" ,xy "+Gdx.graphics.getHeight());

        Block.xborder=LEVEL_WIDTH;


        for(int i = 0; i < blocks.length; i++){
            blocks[i] = new Block(constants.blockwidth, constants.blockheight, (LEVEL_WIDTH-constants.blockwidth)/2, (LEVEL_HEIGHT-constants.blockheight)/2, type.BLOCK, blocktextures[i], game.batch);
            blocks[i].y = (i-(constants.blocksVisible)) * constants.blockheight;
        }

        blocks[4].isActive=true;

        restart();

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

        gameCam.update();
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        //Rendering happens between begin and end
        background.render(game.batch);
        background2.render(game.batch);
        if(!restartInProgress){
            for(Block cube : blocks){
                cube.render();
            }
        }
        if(renderWhite){
            game.batch.draw(white, blocks[active].x, blocks[active].y, blocks[active].width, blocks[active].height);
        }
        if(restartInProgress){
            game.batch.draw(white, 0, 0, LEVEL_WIDTH, LEVEL_HEIGHT);
        }
        game.batch.end();
        if(!restartInProgress) {
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();
        }else{
            game.batch.setProjectionMatrix(restartHUD.stage.getCamera().combined);
            if(RestartHUD.renderHUD) {
                restartHUD.stage.draw();
            }
        }

    }

    //Extra methods provided by Screen implement
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

        if (!restartInProgress) {
//        Blocks update
            for (Block cube : blocks) {
                if (cube.isActive) {
                    cube.update();
                }
            }
            Sounds.playbg();
        }


//        Checks if Active Block is on top of BlockUnder
        for(int i=0; i<blocks.length; i++){
            if(blocks[i].isActive){
                active = i;
                blockunder=i-1;
                if(blockunder==-1){
                    blockunder=blocks.length-1;
                }
//                blocks[blockunder].texture=testTexture;
                if(blocks[i].isCollide(blocks[blockunder])){
                    gameOver=false;
                }else{
                   gameOver=true;
                }
            }
        }
        if(Block.movetoNextTurn){
            if(!gameOver) {

//                Block Collision Handler
                if(!ranOnce) {
                    blocks[active].handleCollision(blocks[blockunder]);
                    ranOnce=true;
                }

//                Moves Blocks Down
                if (counter < constants.blockheight / moveDownSpeed) {
                    for (Block cube : blocks) {
                        cube.y -= moveDownSpeed;
                    }
                    background.update(moveDownSpeed);
                    background2.update(moveDownSpeed);
                    if(blocks[active].perfect) {
                        System.out.println(blocks[active].perfect);
                        if (counter < constants.blockheight / (4 * moveDownSpeed)) {
                            renderWhite = true;
                        } else if (counter < constants.blockheight / (2 * moveDownSpeed)) {
                            renderWhite = false;
                        } else if (counter <  (3*constants.blockheight) / (4 * moveDownSpeed)){
                            renderWhite=true;
                        }else{
                            renderWhite=false;
                            blocks[active].perfect=false;
                        }
                    }
                    counter++;
                } else {

                    score++;

                    hud.updateScore(Integer.toString(score), false, highScore);

                    if(score%5==0){
                        fivesPassed++;
                        if(fivesPassed > fivesPrevious){
                            Block.velx+=constants.difficulty;
                        }
                        fivesPrevious++;
                    }

//                    Starts next block moving
                    counter = 0;
                    Block.movetoNextTurn = false;
                    moveDownSpeed = 5;
                    blocks[minimum].y = (blocks.length - constants.blocksVisible - 1) * constants.blockheight;
                    blocks[minimum].isActive = true;
                    blocks[minimum].width=blocks[active].width;
                    blocks[minimum].texture = blocktextures[(int)Math.round(Math.random()*(blocktextures.length-1))];
                    blocks[minimum].directionChosen=false;
                    blocks[minimum].x = (int)Math.round((Math.random()*(LEVEL_WIDTH*.3))+(LEVEL_WIDTH*.35-(blocks[minimum].width/2)));
                    ranOnce=false;
//                    Reassigns which is the bottom block
                    if (minimum < blocks.length - 1) {
                        minimum++;
                    } else {
                        minimum = 0;
                    }
                }
            }
            else{
                System.out.println("gameOver");
                try {
                    highScore = Integer.parseInt(Base64Coder.decodeString(prefs.getString("highscore")));
                }catch (Exception e){
                    System.out.println("no previous high score");
                }
                if(score>highScore){
                    highScore=score;
                    prefs.putString("highscore", Base64Coder.encodeString(Integer.toString(highScore)));
                    prefs.flush();
                    System.out.println("hi");
                }
                highScore=Integer.parseInt(Base64Coder.decodeString(prefs.getString("highscore")));
                hud.updateScore(Integer.toString(score), true, highScore);

                if(touchcounter>0) {
                    if (Gdx.input.justTouched()) {
                        restartInProgress = true;
                        touchcounter=0;
                    }
                }else {
                    touchcounter++;
                }
            }

        }
        if (restartInProgress) {
            restart();
        }
    }

    public void restart() {
        Sounds.stopbg();

        Block.velx=5;
        Block.movetoNextTurn=false;
        for(int i = 0; i < blocks.length; i++){
            blocks[i] = new Block(constants.blockwidth, constants.blockheight, (LEVEL_WIDTH-constants.blockwidth)/2, (LEVEL_HEIGHT-constants.blockheight)/2, type.BLOCK, blocktextures[i], game.batch);
            blocks[i].y = (i-(constants.blocksVisible)) * constants.blockheight;
            blocks[i].isActive=false;
            blocks[i].xvel=Block.velx;
        }



        blocks[4].isActive=true;
        gameOver=false;
        score=0;
        fivesPassed=0;
        fivesPrevious=0;
        hud.updateScore(Integer.toString(score), gameOver, highScore);
        minimum=0;
        blockunder = minimum-1;

        if(counter<120) {
           counter++;
            System.out.println(counter);
            if(counter<30){
                RestartHUD.renderHUD=true;
            }else if(counter<60){
                RestartHUD.renderHUD=false;
            }else if(counter<90){
                RestartHUD.renderHUD=true;
            }else {
                RestartHUD.renderHUD=false;
            }
        }else{
            restartInProgress=false;
            counter=0;
            RestartHUD.renderHUD=false;
        }



    }
}

//        TODO: Tower size thinning animations, Textures, Improve Score HUD (?), Add Menu functions, Sounds
//        TODO: Support texture packs, generalize textures class, maybe skins
//        TODO: high score menu
