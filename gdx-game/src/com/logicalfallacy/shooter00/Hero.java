package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.assets.*;

public class Hero extends Ship
{

	float _defenseModifier;
	float _defaultFireRate;
	Timer _powerupTimer;
	
	public Hero(Array<Bullet> bulletList, AssetManager assetManager)
	{
		_assetManager = assetManager;
		//_texture = new Texture(Gdx.files.internal("data/hero3_views.png"));
		//_views = TextureRegion.split(_texture, 16, 16);
		//_sprite = new Sprite(_views[0][0]);
		_texture = _assetManager.get("data/vv.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(Gdx.graphics.getWidth()/2);
		_sprite.setY(Gdx.graphics.getWidth()/8);
		_sprite.setScale(0.2f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		
		_speed = 1.1f*Gdx.graphics.getWidth();
		_dxdy = new Vector2();
		_dest = new Vector2();
		
		_maxHP = 100f;
		_hp = _maxHP;
		_defaultFireRate = 0.22f;
		_fireRate = _defaultFireRate;
		_BulletList = bulletList;
		_weaponReady = true;
		
		_defenseModifier = 1;
	}
	
	// data members:
	TextureRegion[][] _views;

	// methods:
	
	@Override
	public void update()
	{
		if(!this.isDead())
		{
			// TODO: Fix texture changing (bank left/right)
			super.update();
		
			/*
			// bank right
			if(_dxdy.x > 0.0f + (_speed/2.0f))
				_sprite.setTexture(_views[0][2].getTexture());
			// bank left
			else if(_dxdy.x < 0.0f - (_speed/2.0f))
				_sprite.setTexture(_views[0][1].getTexture());
				
			else // forward
				_sprite.setTexture(_views[0][0].getTexture());*/
		}
		
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
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .5f*(float)Math.PI, _assetManager));
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), (float)Math.PI/9*4, _assetManager));
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), (float)Math.PI/9*5, _assetManager));
			
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
	public void hit(int damage) {
		_hp = _hp - damage*_defenseModifier;
	}

	@Override
	public void draw(Batch batch)
	{
		if(!this.isDead())
			super.draw(batch);
	}
	
	public void addHP(float bonus) {
		_hp += bonus;
		if(_hp > _maxHP)
			_hp = _maxHP;
	}
	
	public Timer getPowerupTimer() { return _powerupTimer; }
	
	public float getFireRate() { return _fireRate; }
	public void setFireRate(float fireRate) { _fireRate = fireRate; }
	public float getDefaultFireRate() { return _defaultFireRate; }
	public float getDefenseModifier() { return _defenseModifier; }
	public void setDefenseModifier(float defenceModifier) { _defenseModifier = defenceModifier; }
}
