package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.assets.*;

public class Enemy extends Ship
{
	public Enemy(Array<Bullet> bulletList, String jsonText) {
		Json json = new Json();
		//this = json.fromJson(Enemy.class, jsonText);
		
		_BulletList = bulletList;
		
	}
	
	public Enemy(Array<Bullet> bulletList, AssetManager assetManager)
	{
		_assetManager = assetManager;
		_texture = _assetManager.get("data/hunter.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.flip(false, true);
		_sprite.setX((float)Math.random() * Gdx.graphics.getWidth());
		_sprite.setY(Gdx.graphics.getHeight()/8*9);
		_sprite.setScale(0.18f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);

		_speed = 0.33f*Gdx.graphics.getWidth();
		_dxdy = new Vector2();
		_dest = new Vector2(_sprite.getX(), _sprite.getY());

		_hp = 50f;
		_defenseModifier = 1;
		_pointValue = (int)_hp;
		_fireRate = 2.2f; //1.8f;
		_BulletList = bulletList;
		_weaponReady = false;
		
		_fireTimer.schedule(new Timer.Task(){
				@Override
				public void run() {
					_weaponReady = true;
				} // end run()
			}, (float)Math.random()*_fireRate);
	}

	@Override
	public boolean updateDestination()
	{
		if(_sprite.getX() == _dest.x)
			_dest.x = (float)Math.random() * Gdx.graphics.getWidth();
		if(_sprite.getY() == _dest.y)
			_dest.y = (float)(Math.random() * (Gdx.graphics.getHeight()/2))
				+ Gdx.graphics.getHeight()/2;
		return true;
	}
	
	
	@Override
	public void fire() {
		// fire!
		if(_weaponReady) {
			_BulletList.add(new EnemyBullet(_sprite.getX(), _sprite.getY(), (float)Math.PI/2*3, _assetManager));

			_weaponReady = false;

			_fireTimer.schedule(new Timer.Task(){
					@Override
					public void run() {
						_weaponReady = true;
					} // end run()
				}, _fireRate);
		}
	}
	
	@Override
	public int getPoints() {
		if(isDead()) {
			int temp = _pointValue;
			_pointValue = 0;
			return temp;
		}
		return 0;
	}
}
