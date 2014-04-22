package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;

public class Player
{
	Hero _hero;
	Array<Bullet> _bullets;
	int _score;
	int _scoreMultiplier;
	int _lives;
	boolean _gameOver;
	Timer _respawnTimer;
	boolean _canRespawn;
	float _respawnDelay;
	boolean _timerRunning;
	HealthBar _hpBar;
	
	BitmapFont font;
	TextureAtlas textureAtlas;

	public Player() {
		_bullets = new Array();
		_hero = new Hero(_bullets);
		_score = 0;
		_lives = 3;
		_gameOver = false;
		_respawnDelay = 1f;
		_respawnTimer = new Timer();
		_canRespawn = false;
		_timerRunning = false;
		
		font = new BitmapFont(Gdx.files.internal("data/sf_square.fnt"), false);
		_hpBar = new HealthBar();
	}
	
	public void update() {
		_hero.update();

		for(int i = 0; i < _bullets.size; i++) {
			_bullets.get(i).update();
		}

		deleteBullets();
		
		_hpBar.setPercent((float)_hero.getHP()/(float)_hero.getMaxHP());
		_hpBar.setLives(_lives);
		_hpBar.update();
		
		if(_hero.isDead() && _lives > 0) {
			if(!_timerRunning){
				_timerRunning = true;
				_respawnTimer.schedule(new Timer.Task(){
						@Override
						public void run() {
							_canRespawn = true;
							
						} // end run()
					}, _respawnDelay);
			}
			if(_canRespawn)
			{
				_timerRunning = false;
				_hero.dispose();
				_hero = new Hero(_bullets);
				_lives--;
				_canRespawn = false;
			}
		}
		else if(_hero.isDead() && _lives <= 0)
			_gameOver = true;
	}
	
	public void draw(Batch batch) {
		_hpBar.draw(batch);
		
		for(int i = 0; i < _bullets.size; i++) {
			_bullets.get(i).draw(batch);
		}

		_hero.draw(batch);
		
		font.draw(batch, "HP: " + Integer.toString(_hero.getHP()), 0, 0);
		font.draw(batch, "SCORE: " + Integer.toString(_score), 0, font.getLineHeight());
	}
	
	public void bulletHits(Ship target) {
		Rectangle intersection = new Rectangle();

		for(int i = 0; i < _bullets.size; i++) {
			if(Intersector.intersectRectangles(
				   getBulletRect(i), target.getRectangle(), intersection))
			{
				target.hit(_bullets.get(i).getDamage());
				Bullet item = _bullets.get(i);
				_bullets.removeIndex(i).dispose();
				item.dispose();
				_score += target.getPoints();
			}
		}
	}
	
	public void deleteBullets()
	{
		for(int i = 0; i < _bullets.size; i++) {
			if(_bullets.get(i)._deleteMe) {
				_bullets.removeIndex(i).dispose();
			}
		}
	}
	
	public Rectangle getBulletRect(int i) {
		return _bullets.get(i).getRectangle();
	}

	public Rectangle getShipRect(int i) {
		return _hero.getRectangle();
	}
	
	public Hero getHero() { return _hero; }
	
	public int getScore() { return _score; }
	
	public boolean isGameOver() { return _gameOver; }
	
	public void dispose() {
		font.dispose();
		_hero.dispose();
		
		for(int i = 0; i < _bullets.size; i++) {
			_bullets.removeIndex(i).dispose();
		}
	}
}
