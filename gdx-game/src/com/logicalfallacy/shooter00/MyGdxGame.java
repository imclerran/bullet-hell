package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;

import com.logicalfallacy.shooter00.screens.*;

public class MyGdxGame extends Game //implements ApplicationListener
{
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	//LoadingScreen loadingScreen;
	
	Texture texture;
	Background BG;
	Hero hero;
	enemyManager enemies;
	SpriteBatch batch;
	ArrayList<Bullet> friendlyBullets;

	@Override
	public void create()
	{
		mainMenuScreen = new MainMenuScreen(this);
		gameScreen = new GameScreen(this);
		//loadingScreen = new LoadingScreen(this);
		gameScreen.create();
		setScreen(gameScreen);
	}

	@Override
	public void render()
	{
		//if(loadingScreen.isFinished())
		//	setScreen(gameScreen);
		super.render();
	}
	

	@Override
	public void dispose()
	{
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
