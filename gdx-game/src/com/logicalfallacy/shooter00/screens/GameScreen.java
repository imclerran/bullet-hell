package com.logicalfallacy.shooter00.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
//import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;
import com.logicalfallacy.shooter00.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.audio.Music;

public class GameScreen implements Screen
{
	MyGdxGame game;
	AssetManager assetManager;
	ProfileManager profile;
	
	Background BG;
	EnemyManager enemies;
	Player player;
	SpriteBatch batch;
	boolean gameOver;
	Timer timer;
	boolean timerRunning;
	Texture gameOverTexture;
	Sprite gameOverSprite;
	Music music;
	
	
	//BitmapFont font;
	//TextureAtlas textureAtlas;
	
	public GameScreen(MyGdxGame mygame, ProfileManager myProfile, AssetManager manager) {
		game = mygame;
		assetManager = manager;
		profile = myProfile;
		
		BG = new Background(assetManager);
		player = new Player(assetManager);
		enemies = new EnemyManager(assetManager);
        player.setEnemies(enemies);
		enemies.spawnWaves(true);

		gameOverTexture = assetManager.get("data/game_over.png", Texture.class);
		gameOverSprite = new Sprite(gameOverTexture);
		gameOverSprite.setOrigin(0,0);
		gameOverSprite.setScale(Gdx.graphics.getWidth()/gameOverSprite.getWidth());
		gameOverSprite.setX(0);
		gameOverSprite.setY(Gdx.graphics.getHeight()/2-gameOverSprite.getHeight()/2);
		timer = new Timer();
		timerRunning = false;
		batch = new SpriteBatch();
		
		music = assetManager.get("data/reflux.mp3", Music.class);
		music.setVolume(0.04f);
		music.setLooping(true);
	}

	@Override
	public void render(float p1)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		//music.play();
		if(player.isGameOver() && !timerRunning) {
			timerRunning = true;
			timer.schedule(new Timer.Task(){
					@Override
					public void run() {
						gameOver = true;
					} // end run()
				}, 5f);
		}
		
		BG.update();
		player.update();
		enemies.update();
		detectHits();
		
		// every 30 seconds, check if lives > 3
		// if so, increase difficulty
		// eg: max enemies
		//if(player.getLives() > 3)
			

		batch.begin();
		BG.draw(batch);
		enemies.draw(batch);
		player.draw(batch);
		if(player.isGameOver())
		{
			profile.newScore(player.getScore());
			gameOverSprite.draw(batch);
		}
		batch.end();
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
	}*/

	public void detectHits()
	{
		for(int i = 0; i < enemies.getEnemyCount(); i++) {
			player.bulletHits(enemies.getEnemy(i));
			if(enemies.getEnemy(i).isDead())
				enemies.kill(i);
		}

		if(!player.getHero().isDead()) {
			enemies.bulletHits(player.getHero());
			if(player.getHero().getLeftWingman() != null)
				enemies.bulletHits(player.getHero().getLeftWingman());
			if(player.getHero().getRightWingman() != null)
				enemies.bulletHits(player.getHero().getRightWingman());
		}
		//if(hero.isDead())
		// game over
			
	}
	
	public boolean isGameOver() { return gameOver; }

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
		player.dispose();
	}
}
