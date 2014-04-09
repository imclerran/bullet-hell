package com.logicalfallacy.shooter00;

import java.lang.Math;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;

public class Actor
{
	public Actor()
	{
		Texture temp = new Texture(Gdx.files.internal("data/hero.png"));
		_sprite = new Sprite(temp);
		_sprite.setX(Gdx.graphics.getWidth()/2);
		_sprite.setY(Gdx.graphics.getWidth()/8);
		_sprite.setScale(Gdx.graphics.getWidth()/64);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		
		_speed = 2000.0f;
		_dxdy = new Vector2();
		_dest = new Vector2();
	}
	
	// data members:
	Sprite _sprite;
	float _speed;
	Vector2 _dxdy;
	Vector2 _dest;
	
	// methods:
	
	public void update()
	{
		if(Gdx.input.isTouched())
		{
			// set destinations
			_dest.x = Gdx.input.getX();
			_dest.y = Gdx.graphics.getHeight() - Gdx.input.getY()+ _sprite.getBoundingRectangle().getHeight()/3*2;
			
			// calculate x,y speeds
			calcDxDy();
			
			//set sprite location
			_sprite.setX(_sprite.getX() + _dxdy.x * Gdx.graphics.getDeltaTime());
			_sprite.setY(_sprite.getY() + _dxdy.y * Gdx.graphics.getDeltaTime());
		}
	}
	
	// TODO: fix inconsistent speeds
	public void calcDxDy()
	{
		// calculate x, y distance from current position to destination
		float distx = _dest.x - _sprite.getX();
		float disty = _dest.y - _sprite.getY();
		
		// pythagorean:
		float a2b2 = (float)Math.pow(distx, 2) + (float)Math.pow(disty, 2);
		float dist = (float)Math.sqrt(a2b2);
		
		// similar triangles
		_dxdy.x = (_speed/dist)*distx;
		_dxdy.y = (_speed/dist)*disty;
		
		// correct for over travel
		if(_dxdy.x > distx)
			_dxdy.x = distx;
		if(_dxdy.y > disty)
			_dxdy.y = disty;
	}
	
	public void draw(Batch batch)
	{
		_sprite.draw(batch);
	}
}
