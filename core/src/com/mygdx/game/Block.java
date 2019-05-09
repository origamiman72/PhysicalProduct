package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Block extends entity{

    boolean directionChosen;
    static int velx=5;
    boolean isActive=false;
    static int xborder;
    static boolean movetoNextTurn;

    boolean perfect = false;

    public Block(int width, int height, int x, int y, type typeID, Texture texture, SpriteBatch batch){
        super(width, height, x, y, velx, 0, typeID, texture, batch);
    }

    @Override
    public void handleCollision(entity e){

//        System.out.println("yeetboi");

//        Adds slight wiggle room for error
        if((x-e.x)==0){
            perfect=true;
        }
        if (Math.abs(x - e.x) < 40 && width >= 10) {
            x = e.x;
            perfect=true;
        } else {
            perfect=false;
            if (e.x > x) {
//                System.out.println("yo");
                width = x + width - e.x;
                x = e.x;
            }
            if (e.x < x) {
//                System.out.println("yo2");
                width = e.x + e.width - x;
                x = e.x + e.width - width;
            }
        }

        System.out.println(perfect);
    }

    @Override
    public void render(){
        batch.draw(texture, x, y, width, height);
    }

    public void update(){

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.justTouched()){
            isActive = false;
            Block.movetoNextTurn = true;
        }

        if(isActive){

//            Randomizes direction for each block
            if(!directionChosen){
                xvel=velx;
                if(Math.random()>0.5){
                    xvel=-xvel;
                }
                directionChosen=true;
            }

            System.out.println(xvel);

//            Makes Block switch between left and right
            if((x+width)<=(xborder-constants.blockwidth/2) && x >= constants.blockwidth/2) {
                x += xvel;
            }else{
                xvel = -xvel;
                x+=xvel;
            }
        }

    }

}
