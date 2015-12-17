package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Timer;

public class Powerup extends Actor
{
	float _speed;
	Vector2 _dxdy;
	Vector2 _dest;
	float _onScreen;
	Timer _timer;
	boolean _expired;
	float _duration;
	boolean _applyToWingman;
	
	@Override
	public void update()
	{
		// TODO: Implement this method
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
	
	public boolean updateDestination() {
		if(_sprite.getY() <= 0 || _sprite.getY() >= Gdx.graphics.getHeight())
		{
			if(!_expired) {
				_dest.y = (float)Math.random() * Gdx.graphics.getHeight();
			
				if(_sprite.getX() >= (float)Gdx.graphics.getWidth()/2)
					_dest.x = -1f;
				else
					_dest.x = (float)Gdx.graphics.getWidth() + 1;
			}
			else
				_deleteMe = true;
		}
		
		else if(_sprite.getX() <= 0 || _sprite.getX() >= Gdx.graphics.getWidth())
		{
			if(!_expired)
			{
				_dest.x = (float)Math.random() * Gdx.graphics.getWidth();
			
				if(_sprite.getY() >= (float)Gdx.graphics.getHeight()/2)
					_dest.y = -1f;
				else
					_dest.y = (float)Gdx.graphics.getHeight() + 1;
			}
			else
				_deleteMe = true;
		}
		
		return true;
	}
	
	public void activate() {}
	
	public void applyPickup(Player player) {
	}
	
	public boolean isApplyToWingman() { return _applyToWingman; }
}
