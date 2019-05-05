package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Sounds {

    static Music bgmusic = Gdx.audio.newMusic(Gdx.files.internal("bgmusic.mp3"));
    public static void playbg(){
        if(!bgmusic.isPlaying()) {
            bgmusic.play();
        }
    }
    public static void stopbg(){
        bgmusic.stop();
    }

}
