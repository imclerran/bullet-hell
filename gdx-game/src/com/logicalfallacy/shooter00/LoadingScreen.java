package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;
import com.logicalfallacy.shooter00.*;

public class LoadingScreen implements Screen
{
	MyGdxGame game;
	Spinner spinner;
	SpriteBatch batch;
	boolean finished;
	
	public LoadingScreen(MyGdxGame mygame) {
		game = mygame;
		spinner = new Spinner();
		batch = new SpriteBatch();
		finished = false;
		
	}
	
	@Override
	public void create() {
	
		
	}

	@Override
	public void render(float p1)
	{
		finished = spinner.isFinished();
		batch.begin();
		spinner.draw(batch);
		batch.end();
	}
	
	public boolean isFinished() { return finished; }

	
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
