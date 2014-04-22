package com.logicalfallacy.shooter00;

import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.*;

public class enemyManager
{
	Array<Enemy> _enemies;
	Array<Bullet> _bullets;
	int _wave;
	int _waveModifier;
	int _maxWave;
	boolean _spawnWaves;
	
	public enemyManager()
	{
		_enemies = new Array();
		_bullets = new Array();
		_wave = 0;
		_waveModifier = 3;
		_maxWave = 7;
		_spawnWaves = false;
	}
	
	public void spawn(int count) {
		for(int i = 0; i < count; i++)
			_enemies.add(new Enemy(_bullets));
	}
	
	public Enemy getEnemy(int i) { return _enemies.get(i); }
	public int getEnemyCount() { return _enemies.size; }
	
	
	public void killEnemy(int i) { _enemies.removeIndex(i).dispose(); }
	
	//public ArrayList<Bullet> getBullets() { return _bullets; }
	public int getBulletCount() { return _bullets.size; }
	
	public void update() {
		for(int i = 0; i < _enemies.size; i++) {
			_enemies.get(i).update();
		}
		
		for(int i = 0; i < _bullets.size; i++) {
			_bullets.get(i).update();
		}
		
		deleteBullets();
		
		if(_spawnWaves && 0 == _enemies.size)
			waveSpawn();
	}
	
	public void deleteBullets()
	{
		for(int i = 0; i < _bullets.size; i++)
		{
			if(_bullets.get(i)._deleteMe)
			{
				//_bullets.get(i).dispose();
				_bullets.removeIndex(i).dispose();
			}
		}
	}
	
	public void kill(int i) {
		//_enemies.get(i).dispose();
		_enemies.removeIndex(i).dispose();
	}
	
	public void draw(Batch batch) {
		for(int i = 0; i < _bullets.size; i++) {
			_bullets.get(i).draw(batch);
		}
		
		for(int i = 0; i < _enemies.size; i++) {
			_enemies.get(i).draw(batch);
		}
	}
	
	public void bulletHits(Ship target) {
		Rectangle intersection = new Rectangle();
		
		for(int i = 0; i < _bullets.size; i++) {
			if(Intersector.intersectRectangles(
				getBulletRect(i), target.getRectangle(), intersection))
			{
				target.hit(_bullets.get(i).getDamage());
				//_bullets.get(i).dispose();
				_bullets.removeIndex(i).dispose();
			}
		}
	}
	
	public Rectangle getBulletRect(int i) {
		return _bullets.get(i).getRectangle();
	}

	public Rectangle getShipRect(int i) {
		return _enemies.get(i).getRectangle();
	}
	
	public void waveSpawn() {
		if(_wave < _maxWave)
			_wave++;
		waveSpawn(_wave);
	}
	
	public void waveSpawn(int wave) {
		spawn(wave * _waveModifier);
	}
	
	public void spawnWaves(boolean spawnWaves) { _spawnWaves = spawnWaves; }
	
	public void dispose() {
		for(int i = 0; i < _bullets.size; i++) {
			//_bullets.get(i).dispose();
			_bullets.removeIndex(i).dispose();
		}

		for(int i = 0; i < _enemies.size; i++) {
			//_enemies.get(i).dispose();
			_enemies.removeIndex(i).dispose();
		}
	}
}	
