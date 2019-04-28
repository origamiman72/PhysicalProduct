//MyGDXGame Class
package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {
	SpriteBatch batch;

	//The values equate the width and height of the screen your code projects onto
	public static  int V_WIDTH = 1080;
	public static  int V_HEIGHT = 1920;

	static OrthographicCamera screenCam;
	static Viewport menuViewport;

	@Override
	public void create () {
		screenCam = new OrthographicCamera();
		screenCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		menuViewport = new FitViewport(V_WIDTH, V_HEIGHT, screenCam);
		batch = new SpriteBatch();
		setScreen(new MainMenu(this));
	}
	@Override
	public void render () {super.render();}
}
