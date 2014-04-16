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
		gameScreen.create();
		setScreen(gameScreen);
		/*BG = new Background();
		friendlyBullets = new ArrayList();
		hero = new Hero(friendlyBullets);
		enemies = new enemyManager();
		enemies.spawnWaves(true);
		
		batch = new SpriteBatch();*/
	}

	@Override
	public void render()
	{
		super.render();
	    /*Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		BG.update();
		hero.update();
		enemies.update();
		detectHits();
		deleteBullets(friendlyBullets);
		
		batch.begin();
		BG.draw(batch);
		enemies.draw(batch);
		enemies.draw(batch);
		drawBullets(batch, friendlyBullets);
		hero.draw(batch);
		batch.end();*/
	}
	
	/*public void drawBullets(Batch batch, ArrayList<Bullet> bullets)
	{
		for(Bullet b : bullets)
		{
			b.update();
			b.draw(batch);
		}
	}
	
	public void deleteBullets(ArrayList<Bullet> bullets)
	{
		for(int i = 0; i < bullets.size(); i++)
		{
			if(bullets.get(i)._deleteMe)
				bullets.remove(i);
		}
	}
	
	public void detectHits()
	{
		Rectangle intersection = new Rectangle();
		
		for(int i = 0; i < enemies.getEnemyCount(); i++) {
			for(int j = 0; j < friendlyBullets.size(); j++)
				if(Intersector.intersectRectangles(
					friendlyBullets.get(j).getSprite().getBoundingRectangle(),
					enemies.getShipRect(i),
					intersection)) 
				{
					enemies.getEnemy(i).hit(friendlyBullets.get(j).getDamage());
					friendlyBullets.remove(j);
				}
			if(enemies.getEnemy(i).isDead())
				enemies.kill(i);
		}
		
		enemies.bulletHits(hero);
		//if(hero.isDead())
			// game over
	}*/

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
