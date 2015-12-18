package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.assets.*;

public class Player
{
	AssetManager _assetManager;
	Hero _hero;
	Array<Bullet> _bullets;
	int _score;
	int _scoreMultiplier;
	int _lives;
	int _availableWingmen;
	boolean _gameOver;
	Timer _respawnTimer;
	boolean _canRespawn;
	float _respawnDelay;
	boolean _timerRunning;
	HealthBar _hpBar;
	Array<Powerup> _powerups;
	Array<Powerup> _activePowerups;
	
	BitmapFont font;
	TextureAtlas textureAtlas;

	public Player(AssetManager assetManager) {
		_assetManager = assetManager;
		
		_bullets = new Array();
		_powerups = new Array();
		_activePowerups = new Array();
		_availableWingmen = 0;
		_hero = new Hero(_bullets, _assetManager);
		_activePowerups.add(new RespawnPowerup());
		_score = 0;
		_lives = 3;
		_gameOver = false;
		_respawnDelay = 1f;
		_respawnTimer = new Timer();
		_canRespawn = false;
		_timerRunning = false;
		
		font = new BitmapFont(Gdx.files.internal("data/sf_square.fnt"), false);
		_hpBar = new HealthBar(_assetManager);
	}
	
	public void update() {
		_hero.update();

		for(int i = 0; i < _bullets.size; i++) {
			_bullets.get(i).update();
		}
		deleteBullets();
		
		updatePowerups();
		
		_hpBar.setPercent(_hero.getHP()/_hero.getMaxHP());
		_hpBar.setLives(_lives);
		_hpBar.setDefenseModifier(_hero.getDefenseModifier());
		_hpBar.update();
		
		if(_hero.isDead() && _lives > 0) {
			if(_hero.getWeaponLevel() > 1)
				_powerups.add(new WeaponPowerup(_hero.getSprite().getX(), _hero.getSprite().getY(), _assetManager));
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
				_hero = new Hero(_bullets, _assetManager);
				_activePowerups.add(new RespawnPowerup());
				_lives--;
				_canRespawn = false;
			}
		}
		else if(_hero.isDead() && _lives <= 0)
		{
			_gameOver = true;
		}
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
	
	public void updatePowerups() {
		
		for(int i = 0; i < _powerups.size; i++) {
			_powerups.get(i).update();
		}
		
		powerupAcquire();
		deletePowerups();
		
		_hero.setDefenseModifier(1);
		_hero.setFireRate(_hero.getDefaultFireRate());

		if(_hero.getLeftWingman() != null) {
			_hero.getLeftWingman().setDefenseModifier(1);
			_hero.getLeftWingman().setFireRate(_hero.getLeftWingman().getDefaultFireRate());
		}
		
		if(_hero.getRightWingman() != null) {
			_hero.getRightWingman().setDefenseModifier(1);
			_hero.getRightWingman().setFireRate(_hero.getRightWingman().getDefaultFireRate());
		}
		
		for(int i = 0; i < _activePowerups.size; i++) {
			_activePowerups.get(i).applyPickup(this);
			if(_activePowerups.get(i)._deleteMe)
				_activePowerups.removeIndex(i);
		}
		
		spawnWingmen();
	}
	
	public void spawnWingmen() {
		if(_hero.getLeftWingman() == null && _availableWingmen > 0) {
			_hero.spawnLeftWingman();
			_availableWingmen--;
		}
		
		if(_hero.getRightWingman() == null && _availableWingmen > 0) {
			_hero.spawnRightWingman();
			_availableWingmen--;
		}
	}
	
	public void powerupAcquire() {
		Rectangle intersection = new Rectangle();
		for(int i = 0; i < _powerups.size; i++) {
			if(Intersector.intersectRectangles(
				   getPowerupRect(i), _hero.getRectangle(), intersection)) 
			{
				_activePowerups.add(_powerups.removeIndex(i));
				_activePowerups.get(_activePowerups.size-1).activate();
			}
		}
	}
	
	public void addRandomPowerup(float x, float y) {
		float rand = 5.3f*(float)Math.random();
		
		
		if(rand < 1f)
			_powerups.add(new WeaponPowerup(x, y, _assetManager));
		else if(rand < 2f)
			_powerups.add(new InvinciblePowerup(x, y, _assetManager));
		else if(rand < 3f)
			_powerups.add(new WingmanPowerup(x, y, _assetManager));
		else if(rand < 4f)
			_powerups.add(new HealthPowerup(x, y, _assetManager));
		else if(rand < 5f)
			_powerups.add(new RapidFirePowerup(x, y, _assetManager));
		else if(rand < 5.3)
			_powerups.add(new OneUpPowerup(x, y, _assetManager));
		
		// TEST CODE:
		//_powerups.add(new WeaponPowerup(x, y, _assetManager));
			
	}
	
	public void bulletHits(Ship target) {
		Rectangle intersection = new Rectangle();

		for(int i = 0; i < _bullets.size; i++) {
			if(Intersector.intersectRectangles(
				   getBulletRect(i), target.getRectangle(), intersection))
			{
				target.hit(_bullets.get(i).getDamage());
				_bullets.removeIndex(i);
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
				_bullets.removeIndex(i);
			}
		}
	}
	
	public void deletePowerups()
	{
		for(int i = 0; i < _powerups.size; i++) {
			if(_powerups.get(i)._deleteMe) {
				_powerups.removeIndex(i);
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
	
	public void addWingmen(int wingmen) { _availableWingmen += wingmen; }
	
	public boolean isGameOver() { return _gameOver; }
	
	public void dispose() {
		font.dispose();
	}
}
