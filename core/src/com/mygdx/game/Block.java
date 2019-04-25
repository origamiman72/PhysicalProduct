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
        if(e.x>x) {
            width=x+width-e.x;
            x=e.x;
        }
        if(e.x<x) {
            width=e.x+e.width-x;
            x=e.x+e.width-width;
        }
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

            if((x+width)<=(xborder-constants.blockwidth/2) && x >= constants.blockwidth/2) {
                x += xvel;
            }else{
                xvel = -xvel;
                x+=xvel;
            }
        }


    }

}
