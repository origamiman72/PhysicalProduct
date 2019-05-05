package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {

    int x;
    int y;
    int width;
    int height;
    Texture texture;

    public Background(int x, int y, int width, int height, Texture texture){
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.texture=texture;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, x, y, width, height);
    }

    public void update(int moveDownSpeed){
        if(y>-MyGdxGame.V_HEIGHT){
            y-=moveDownSpeed/2;
        }else {
            y=MyGdxGame.V_HEIGHT-moveDownSpeed/2;
        }
    }
}
