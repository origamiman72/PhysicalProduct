package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class constants {
    static int blockNumber = 5;
    static int blockwidth = 300;
    static int blockheight = 250;
    //Change blocksVisibleC
    static int blocksVisibleC = 3;
    static int blocksVisible = blockNumber - 1 - blocksVisibleC;
    static int difficulty = 3;

    static BitmapFont pixelFont = new BitmapFont(
            Gdx.files.internal("pixelOperatorHB.fnt"),
            false
    );
}
