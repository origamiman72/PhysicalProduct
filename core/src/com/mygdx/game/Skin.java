package com.mygdx.game;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.io.File;

public class Skin {

    static Texture[] blocktextures = new Texture[5];
    static Texture background = new Texture("background.jpg");
    static Texture backgroundground = new Texture("backgroundground.png");


    public static Texture[] getBlocktextures() {
        for(int i =1; i<=blocktextures.length; i++ ) {
            try {
                blocktextures[i-1] = new Texture(Gdx.files.internal("blocks"+ File.separator+"BlockTexture" + i + ".png"));
            }catch (Exception e){
                System.out.println("No external Texture pack");
                blocktextures[i-1] = new Texture(Gdx.files.internal("blocktextureb.jpeg"));
            }
        }
        return blocktextures;
    }

}
