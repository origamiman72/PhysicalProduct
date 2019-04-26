//MyGDXGame Class
package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	SpriteBatch batch;

	//The values equate the width and height of the screen your code projects onto
	public static  int V_WIDTH = 1080;
	public static  int V_HEIGHT = 1920;

	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}
	@Override
	public void render () {super.render();}
}
