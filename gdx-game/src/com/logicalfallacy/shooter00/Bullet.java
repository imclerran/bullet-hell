package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.*;

public class Bullet extends Actor {
	// data members:
	int _damage;
	float _speed;
	Vector2 _dxdy;
	float _angle;
	public boolean _deleteMe;
	public Sound _sound;
	
	
	public Bullet() {
		_damage = 10;
		_angle = (float)Math.PI/2;
		_speed = 500;
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		Texture temp = new Texture(Gdx.files.internal("data/bullet.png"));
		_sprite = new Sprite(temp);
		temp.dispose();
	}
	
	public Bullet(float x, float y, AssetManager assetManager)
	{
		_damage = 10;
		_angle = (float)Math.PI/2;
		_speed = 1200;
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = assetManager.get("data/bullet.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(Gdx.graphics.getWidth()/72);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
	}
	
	public Bullet(float x, float y, float angle, AssetManager assetManager)
	{
		_damage = 10;
		_angle = angle;
		_speed = 1200;
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = assetManager.get("data/bullet.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(Gdx.graphics.getWidth()/72);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);

	}

	@Override
	public void update()
	{
		_sprite.setX(_sprite.getX() + _dxdy.x * Gdx.graphics.getDeltaTime());
		_sprite.setY(_sprite.getY() + _dxdy.y * Gdx.graphics.getDeltaTime());
		
		if(_sprite.getX() < 0.0f || _sprite.getX() > Gdx.graphics.getWidth())
			_deleteMe = true;
			
		if(_sprite.getY() < 0.0f || _sprite.getY() > Gdx.graphics.getHeight())
			_deleteMe = true;
	}
	
	// methods:
	
	public void calcDxDy() {
		_dxdy.x = (float)Math.cos(_angle) * _speed;
		_dxdy.y = (float)Math.sin(_angle) * _speed;
	}
	
	public int getDamage() { return _damage; }
}
