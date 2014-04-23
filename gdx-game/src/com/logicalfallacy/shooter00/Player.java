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
	Array<Powerup> _powerups;
	
	BitmapFont font;
	TextureAtlas textureAtlas;

	public Player() {
		_bullets = new Array();
		_powerups = new Array();
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
		
		for(int i = 0; i < _powerups.size; i++) {
			_powerups.get(i).update();
		}
		powerupAcquire();
		deletePowerups();
		
		_hpBar.setPercent(_hero.getHP()/_hero.getMaxHP());
		_hpBar.setLives(_lives);
		_hpBar.setDefenseModifier(_hero.getDefenseModifier());
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
		for(int i = 0; i < _bullets.size; i++) {
			_bullets.get(i).draw(batch);
		}
		
		for(int i = 0; i < _powerups.size; i++) {
			_powerups.get(i).draw(batch);
		}

		_hero.draw(batch);
		
		_hpBar.draw(batch);
		font.draw(batch, "SCORE: " + Integer.toString(_score), 0, font.getLineHeight());
	}
	
	public void powerupAcquire() {
		Rectangle intersection = new Rectangle();
		for(int i = 0; i < _powerups.size; i++) {
			if(Intersector.intersectRectangles(
				   getPowerupRect(i), _hero.getRectangle(), intersection)) 
			{
				_powerups.get(i).applyPickup(this);
				_powerups.removeIndex(i).dispose();
			}
		}
	}
	
	public void addRandomPowerup(float x, float y) {
		float rand = (float)Math.random();
		
		if(rand < 0.5f)
			_powerups.add(new HealthPowerup(x, y));
		else if(rand < 0.75f)
			_powerups.add(new ExtraLifePowerup(x, y));
		else
			_powerups.add(new FireRatePowerup(x, y));
			
	}
	
	public void bulletHits(Ship target) {
		Rectangle intersection = new Rectangle();

		for(int i = 0; i < _bullets.size; i++) {
			if(Intersector.intersectRectangles(
				   getBulletRect(i), target.getRectangle(), intersection))
			{
				target.hit(_bullets.get(i).getDamage());
				_bullets.removeIndex(i).dispose();
				_score += target.getPoints();
				if(target.isDead() && Math.random() < 0.02f)
					addRandomPowerup(target.getSprite().getX(), target.getSprite().getY());
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
	
	public void deletePowerups()
	{
		for(int i = 0; i < _powerups.size; i++) {
			if(_powerups.get(i)._deleteMe) {
				_powerups.removeIndex(i).dispose();
			}
		}
	}
	
	public Rectangle getBulletRect(int i) {
		return _bullets.get(i).getRectangle();
	}

	public Rectangle getShipRect(int i) {
		return _hero.getRectangle();
	}
	
	public Rectangle getPowerupRect(int i) {
		return _powerups.get(i).getRectangle();
	}
	
	public Hero getHero() { return _hero; }
	
	public int getScore() { return _score; }
	
	public void addLives(int lives) { _lives += lives; }
	
	public boolean isGameOver() { return _gameOver; }
	
	public void dispose() {
		font.dispose();
		_hero.dispose();
		
		for(int i = 0; i < _bullets.size; i++) {
			_bullets.removeIndex(i).dispose();
		}
		
		for(int i = 0; i < _powerups.size; i++) {
			_powerups.removeIndex(i).dispose();
		}
	}
}
