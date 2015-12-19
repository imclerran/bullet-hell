package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.*;

import com.logicalfallacy.shooter00.screens.*;
import com.badlogic.gdx.assets.*;

public class MyGdxGame extends Game
{
	AssetManager assetManager;
	MainMenuScreen mainMenuScreen;
	public GameScreen gameScreen;
	public LoadingScreen loadingScreen;
	public boolean gameOver;
	ProfileManager profile;

	@Override
	public void create()
	{
		assetManager = new AssetManager();
		loadAssets();
		
		profile = new ProfileManager();
		profile.loadProfile();
		
		mainMenuScreen = new MainMenuScreen(this, profile);
		gameScreen = new GameScreen(this, profile, assetManager);
		loadingScreen = new LoadingScreen(this);
		setScreen(loadingScreen);
	}
	
	public void loadAssets() {
		assetManager.load("data/game_over.png", Texture.class);
		assetManager.load("data/space_background.png", Texture.class);
		assetManager.load("data/runway_tile_black.png", Texture.class);
		assetManager.load("data/space_runway.png", Texture.class);
        assetManager.load("data/runway_transparent.png", Texture.class);
		assetManager.load("data/vv.png", Texture.class);
		assetManager.load("data/blue_bullet.png", Texture.class);
		assetManager.load("data/invincible_powerup.png", Texture.class);
		assetManager.load("data/1up_powerup.png", Texture.class);
		assetManager.load("data/hunter.png", Texture.class);
		assetManager.load("data/enemy_bullet.png", Texture.class);
		assetManager.load("data/fire_rate_powerup.png", Texture.class);
		assetManager.load("data/health_powerup.png", Texture.class);
		assetManager.load("data/wingman_powerup.png", Texture.class);
		assetManager.load("data/health_full.png", Texture.class);
		assetManager.load("data/health_mid.png", Texture.class);
		assetManager.load("data/health_low.png", Texture.class);
		assetManager.load("data/health_invincible.png", Texture.class);
		assetManager.load("data/hero.png", Texture.class);
		assetManager.load("data/red_venom.png", Texture.class);
		assetManager.load("data/weapon_powerup.png", Texture.class);
		
		assetManager.load("data/gun_silencer.mp3", Sound.class);
		assetManager.load("data/blaster-01.wav", Sound.class);
		assetManager.load("data/pulse_rifle.wav", Sound.class);
		assetManager.load("data/gun_zap2.wav", Sound.class);
		assetManager.load("data/exp_03.wav", Sound.class);
		assetManager.load("data/reflux.mp3", Music.class);
		assetManager.finishLoading();
		
	}

	@Override
	public void render()
	{
		if(assetManager.update() &&loadingScreen.isFinished())
		{
			setScreen(mainMenuScreen);
			loadingScreen.reset();
		}
		if(gameScreen.isGameOver())
		{
			gameScreen.dispose();
			gameScreen = new GameScreen(this, profile, assetManager);
			mainMenuScreen.dispose();
			mainMenuScreen = new MainMenuScreen(this, profile);
			setScreen(mainMenuScreen);
		}
		super.render();
	}
	

	@Override
	public void dispose()
	{
		profile.saveProfile();
		assetManager.dispose();
		loadingScreen.dispose();
		gameScreen.dispose();
		mainMenuScreen.dispose();
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
