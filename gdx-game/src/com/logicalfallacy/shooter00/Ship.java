package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class Ship extends Actor
{
	// TODO: 
	// change fire to take an argument ArrayList<Bullet> instead of using a member
	float _speed;
	Vector2 _dxdy;
	Vector2 _dest;
	float _hp;
	float _maxHP;
	float _fireRate;
	Timer _fireTimer;
	Array<Bullet> _BulletList;
	boolean _weaponReady;
	int _pointValue;

	// methods:

	@Override
	public void update()
	{
		if(updateDestination())
		{
			// calculate x,y speeds
			calcDxDy();

			// set move distance
			float movex = _dxdy.x * Gdx.graphics.getDeltaTime();
			float movey = + _dxdy.y * Gdx.graphics.getDeltaTime();

			// check for over travel, then set new x
			if(Math.abs(movex) < Math.abs(_sprite.getX() -  _dest.x))
				_sprite.setX(_sprite.getX() + movex);
			else
				_sprite.setX(_dest.x);

			// check for over travel, then set new y
			if(Math.abs(movey) < Math.abs(_sprite.getY() -  _dest.y))
				_sprite.setY(_sprite.getY()  + movey);
			else
				_sprite.setY(_dest.y);
		}

		fire();
	}
	
	public void fire() {
		_fireTimer.schedule(new Timer.Task(){
				@Override
				public void run() {
					// fire!
					_BulletList.add(new Bullet(_sprite.getX(), _sprite.getY()));
				}
			}, _fireRate);
	}
	
	public void hit(int damage) { _hp = _hp - damage; }
	public boolean isDead() { return (_hp <= 0) ? true : false; }
	
	public boolean updateDestination() {
		return false;
	}

	public void calcDxDy() {
		// calculate x, y distance from current position to destination
		float distx = _dest.x - _sprite.getX();
		float disty = _dest.y - _sprite.getY();

		// pythagorean:
		float a2b2 = (float)Math.pow(distx, 2) + (float)Math.pow(disty, 2);
		float dist = (float)Math.sqrt(a2b2);

		// similar triangles
		_dxdy.x = (_speed/dist)*distx;
		_dxdy.y = (_speed/dist)*disty;
	}
	
	public int getPoints() { return 0; }
	public float getHP() { return _hp; }
	public float getMaxHP() { return _maxHP; }
}
