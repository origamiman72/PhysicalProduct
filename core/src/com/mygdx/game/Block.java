package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Block extends entity{


    boolean isActive=false;

    public Block(int width, int height, int x, int y, int xvel, type typeID, Texture texture, SpriteBatch batch){
        super(width, height, x, y, xvel, 0, typeID, texture, batch);
    }

    @Override
    public void handleCollision(entity e){
    }

    @Override
    public void render(){
        batch.draw(texture, x, y, width, height);
    }

    public void update(){
        if(isActive){
            x+=xvel;
        }
    }

}
