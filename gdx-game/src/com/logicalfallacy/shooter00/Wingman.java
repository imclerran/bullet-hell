package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class Wingman extends Ship
{
	public Wingman(Array<Bullet> bulletList, AssetManager assetManager, float x, float y)
	{
		_assetManager = assetManager;
		_texture = _assetManager.get("data/red_venom.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(0.135f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		//_sprite.setOrigin(0,0);

		_speed = 0.93f * 1.1f*Gdx.graphics.getWidth();
		_dxdy = new Vector2();
		_dest = new Vector2();

		_maxHP = 100f;
		_hp = _maxHP;
		_defaultFireRate = 0.22f;
		_fireRate = _defaultFireRate;
		_BulletList = bulletList;
		_weaponReady = true;

		_defenseModifier = 1;
		_weaponLevel = 1;
		_maxWeaponLevel = 2;
	}
	
	public void update(float x, float y)
	{

		if (updateDestination(x, y))
		{
			// calculate x,y speeds
			calcDxDy();

			// set move distance
			float movex = _dxdy.x * Gdx.graphics.getDeltaTime();
			float movey = + _dxdy.y * Gdx.graphics.getDeltaTime();

			// check for over travel, then set new x
			if (Math.abs(movex) < Math.abs(_sprite.getX() -  _dest.x))
				_sprite.setX(_sprite.getX() + movex);
			else
				_sprite.setX(_dest.x);

			// check for over travel, then set new y
			if (Math.abs(movey) < Math.abs(_sprite.getY() -  _dest.y))
				_sprite.setY(_sprite.getY()  + movey);
			else
				_sprite.setY(_dest.y);
		}

		fire();

	}

	@Override
	public void fire() {
		if(Gdx.input.isTouched() && _weaponReady) {
			fire(_weaponLevel)
			//_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY()+_sprite.getHeight(), .5f*(float)Math.PI, _assetManager));
			
			_weaponReady = false;

			_fireTimer.schedule(new Timer.Task(){
					@Override
					public void run() {
						_weaponReady = true;
					} // end run()
				}, _fireRate);
		}
	}
	
	public fire(int level) {
		if(level = 1)
		_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY()+_sprite.getHeight(), .5f*(float)Math.PI, _assetManager));
		
		if(level = 2) {
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .475f*(float)Math.PI, _assetManager));
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .525f*(float)Math.PI, _assetManager));
		}
	}

	public boolean isDead() { return (_hp <= 0) ? true : false; }

	public boolean updateDestination(float x, float y) {
		if(_sprite.getX() == x && _sprite.getY() == y)
			return false;
		
		_dest.x = x;
		_dest.y = y;
		return true;
	}
	
	@Override
	public void draw(Batch batch) {
		super.draw(batch);
	}
}
