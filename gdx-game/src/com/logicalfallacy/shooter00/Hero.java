package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class Hero extends Ship
{
	public Hero(ArrayList<Bullet> bulletList)
	{
		Texture temp = new Texture(Gdx.files.internal("data/hero3_views.png"));
		_views = TextureRegion.split(temp, 16, 16);
		_sprite = new Sprite(_views[0][0]);
		_sprite.setX(Gdx.graphics.getWidth()/2);
		_sprite.setY(Gdx.graphics.getWidth()/8);
		_sprite.setScale(Gdx.graphics.getWidth()/64);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		
		_speed = 1000.0f;
		_dxdy = new Vector2();
		_dest = new Vector2();
		
		_hp = 100;
		_fireRate = 0.25f;
		_BulletList = bulletList;
		_weaponReady = true;
	}
	
	// data members:
	TextureRegion[][] _views;

	// methods:
	
	@Override
	public void update()
	{
		// TODO: Fix texture changing (bank left/right)
		super.update();
		
		// bank right
		if(_dxdy.x > 0 + (_speed/2))
			_sprite.setTexture(_views[0][2].getTexture());
		// bank left
		if(_dxdy.x < 0 - (_speed/2))
			_sprite.setTexture(_views[0][1].getTexture());
		// forward
		else
			_sprite.setTexture(_views[0][0].getTexture());
		
	}

	@Override
	public boolean updateDestination()
	{
		if(Gdx.input.isTouched())
		{
			// set destinations
			_dest.x = Gdx.input.getX();
			_dest.y = Gdx.graphics.getHeight() - Gdx.input.getY()
				+ _sprite.getBoundingRectangle().getHeight()/3*2;
			return true;
		}
		return false;
	}

	@Override
	public void fire()
	{
		// fire!
		if(Gdx.input.isTouched() && _weaponReady) {
			_BulletList.add(new Bullet(_sprite.getX(), _sprite.getY()));
			_BulletList.add(new Bullet(_sprite.getX(), _sprite.getY(), (float)Math.PI/9*4));
			_BulletList.add(new Bullet(_sprite.getX(), _sprite.getY(), (float)Math.PI/9*5));
			
			_weaponReady = false;
			
			_fireTimer.schedule(new Timer.Task(){
					@Override
					public void run() {
						_weaponReady = true;
					} // end run()
				}, _fireRate);
		}
	}
	
	
}
