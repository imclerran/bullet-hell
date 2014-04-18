package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class Enemy extends Ship
{
	public Enemy(ArrayList<Bullet> bulletList)
	{
		Texture temp = new Texture(Gdx.files.internal("data/enemy1.png"));
		_sprite = new Sprite(temp);
		_sprite.setX((float)Math.random() * Gdx.graphics.getWidth());
		_sprite.setY(Gdx.graphics.getHeight()/8*9);
		_sprite.setScale(Gdx.graphics.getWidth()/64);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);

		_speed = 500.0f;
		_dxdy = new Vector2();
		_dest = new Vector2(_sprite.getX(), _sprite.getY());

		_hp = 30;
		_pointValue = _hp;
		_fireRate = 1.0f;
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
		// TODO: Implement this method
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
			_BulletList.add(new Bullet(_sprite.getX(), _sprite.getY(), (float)Math.PI/2*3));

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
