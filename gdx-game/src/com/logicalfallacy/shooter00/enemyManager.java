package com.logicalfallacy.shooter00;

import java.util.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.*;

public class EnemyManager
{
	AssetManager _assetManager;
	Array<Enemy> _enemies;
	Array<Bullet> _bullets;
	int _wave;
	int _waveModifier;
	int _maxWave;
	boolean _spawnWaves;
	Sound _explosion;
	
	public EnemyManager(AssetManager assetManager)
	{
		_assetManager = assetManager;
		_enemies = new Array();
		_bullets = new Array();
		_wave = 0;
		_waveModifier = 3;
		_maxWave = 4;
		_spawnWaves = false;
		
		_explosion = assetManager.get("data/exp_03.wav", Sound.class);
	}
	
	public void spawn(int count) {
		for(int i = 0; i < count; i++)
			_enemies.add(new Enemy(_bullets, _assetManager));
	}
	
	public Enemy getEnemy(int i) { return _enemies.get(i); }
	public int getEnemyCount() { return _enemies.size; }
	
	public Array<Bullet> getBullets() { return _bullets; }
	
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
		
		if(!_spawnWaves)
			continuousSpawn();
		
		if(_wave == _maxWave)
			semiWaveSpawn();
	}
	
	public void deleteBullets()
	{
		for(int i = 0; i < _bullets.size; i++)
		{
			if(_bullets.get(i)._deleteMe)
			{
				_bullets.removeIndex(i);
			}
		}
	}
	
	public void killEnemy(int i) { 
		_enemies.removeIndex(i);
		_explosion.play(0.01f);
	}
	
	public void kill(int i) {
		_enemies.removeIndex(i);
		_explosion.play(0.03f);
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
				_bullets.removeIndex(i);
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
			waveSpawn(++_wave);
	}
	
	public void waveSpawn(int wave) {
		spawn(wave * _waveModifier);
	}
	
	public void continuousSpawn()
	{
		if(_enemies.size < (_wave * _waveModifier))
			spawn(1);
	}
	
	public void semiWaveSpawn() {
		if(_enemies.size < (2*_wave * _waveModifier)/3)
			spawn((_wave * _waveModifier)/3);
	}
	
	public void spawnWaves(boolean spawnWaves) { _spawnWaves = spawnWaves; }
}	
