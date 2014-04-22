package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;

import com.logicalfallacy.shooter00.screens.*;

public class MyGdxGame extends Game //implements ApplicationListener
{
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	LoadingScreen loadingScreen;
	boolean gameOver;

	@Override
	public void create()
	{
		//mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}

	@Override
	public void render()
	{
		if(loadingScreen.isFinished())
		{
			setScreen(gameScreen);
			loadingScreen.reset();
		}
		if(gameScreen.isGameOver())
		{
			gameScreen.dispose();
			gameScreen = new GameScreen(this);
			loadingScreen.dispose();
			loadingScreen = new LoadingScreen(this);
			setScreen(loadingScreen);
		}
		super.render();
	}
	

	@Override
	public void dispose()
	{
		loadingScreen.dispose();
		gameScreen.dispose();
		//mainMenuScreen.dispose();
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
