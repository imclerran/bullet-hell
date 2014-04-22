package com.logicalfallacy.shooter00.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;
import com.logicalfallacy.shooter00.*;

public class GameScreen implements Screen
{
	MyGdxGame game;
	
	Background BG;
	enemyManager enemies;
	Player player;
	SpriteBatch batch;
	
	//BitmapFont font;
	//TextureAtlas textureAtlas;
	
	public GameScreen(MyGdxGame mygame) {
		game = mygame;
		
		BG = new Background();
		player = new Player();
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
		player.update();
		enemies.update();
		detectHits();

		batch.begin();
		BG.draw(batch);
		enemies.draw(batch);
		player.draw(batch);
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
		for(int i = 0; i < enemies.getEnemyCount(); i++) {
			player.bulletHits(enemies.getEnemy(i));
			if(enemies.getEnemy(i).isDead())
				enemies.kill(i);
		}

		enemies.bulletHits(player.getHero());
		//if(hero.isDead())
		// game over
			
	}
	
	public boolean isGameOver() { return player.isGameOver(); }

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
		batch.dispose();
		enemies.dispose();
		player.dispose();
	}
}
