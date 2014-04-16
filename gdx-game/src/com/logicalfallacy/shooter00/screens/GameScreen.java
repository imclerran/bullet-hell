package com.logicalfallacy.shooter00.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;
import com.logicalfallacy.shooter00.*;

public class GameScreen implements Screen
{
	MyGdxGame game;
	
	Texture texture;
	Background BG;
	Hero hero;
	enemyManager enemies;
	SpriteBatch batch;
	ArrayList<Bullet> friendlyBullets;
	
	public GameScreen(MyGdxGame mygame) {
		game = mygame;
	}
	
	@Override
	public void create() {
		BG = new Background();
		friendlyBullets = new ArrayList();
		hero = new Hero(friendlyBullets);
		enemies = new enemyManager();
		enemies.spawnWaves(true);

		batch = new SpriteBatch();	
	}

	@Override
	public void render(float p1)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
		batch.end();
	}
	
	public void drawBullets(Batch batch, ArrayList<Bullet> bullets)
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
	}

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
