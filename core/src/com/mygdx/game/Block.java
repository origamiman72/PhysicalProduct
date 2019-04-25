package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block extends entity{

    static int velx=5;
    boolean isActive=false;
    static int xborder;
    static boolean movetoNextTurn;

    public Block(int width, int height, int x, int y, type typeID, Texture texture, SpriteBatch batch){
        super(width, height, x, y, velx, 0, typeID, texture, batch);
    }

    @Override
    public void handleCollision(entity e){
        System.out.println("yeetboi");
    }

    @Override
    public void render(){
        batch.draw(texture, x, y, width, height);
    }

    public void update(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            isActive = false;
            Block.movetoNextTurn = true;
        }

        if(isActive){

//          NOTE: Decreased area in which active block can roam

            if((x+width)<=(xborder-width/2) && x >= width/2) {
                x += xvel;
            }else{
                xvel = -xvel;
                x+=xvel;
            }
        }


    }

}
