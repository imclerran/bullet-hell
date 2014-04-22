package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
//import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.Game;
import com.logicalfallacy.shooter00.*;

public class LoadingScreen implements Screen
{
	MyGdxGame game;
	//Spinner spinner;
	SpriteBatch batch;
	boolean finished;
	Sprite splash;
	Timer pause;
	
	public LoadingScreen(MyGdxGame mygame) {
		game = mygame;
		
		Texture temp = new Texture(Gdx.files.internal("data/LibGDX.png"));
		splash = new Sprite(temp);
		//splash.setOrigin(splash.getWidth()/2, splash.getHeight()/2);
		splash.setOrigin(0,0);
		splash.setScale(Gdx.graphics.getWidth()/splash.getWidth());
		//splash.setX(Gdx.graphics.getWidth()/2);
		//splash.setY(Gdx.graphics.getHeight()/2);
		splash.setPosition(0,(Gdx.graphics.getHeight()/2 - splash.getHeight()/2*splash.getScaleY()));
		batch = new SpriteBatch();
		finished = false;
		
		pause = new Timer();
		pause.schedule(new Timer.Task() {
				@Override
				public void run() {
					finished = true;
				} // end run()
			}, 1f);
	}
	
	@Override
	public void create() {
	
		
	}

	@Override
	public void render(float p1)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		splash.draw(batch);
		batch.end();
	}
	
	public boolean isFinished() { return finished; }
	
	public void reset() { finished = false; }

	
	@Override
	public void hide()
	{
		// TODO: Implement this method
	}

	@Override
	public void show()
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void resize(int p1, int p2)
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
	}
}
