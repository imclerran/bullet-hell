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

	
	Wingman _leftWingman;
	Wingman _rightWingman;
	boolean _spawnDestReached;
    boolean _missilesAvailable;
    float _missleFireRate;
    Timer _missileTimer;
    
	//Timer _powerupTimer;
	
	public Hero(Array<Bullet> bulletList, AssetManager assetManager)
	{
		_assetManager = assetManager;
		_texture = _assetManager.get("data/vv.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(Gdx.graphics.getWidth()/2);
		_sprite.setY(0);
		_sprite.setScale(0.2f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		//_sprite.setOrigin(0,0);
		
		_speed = 1.3f*Gdx.graphics.getWidth();
		_dxdy = new Vector2();
		_dest = new Vector2(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/4);
		_spawnDestReached = false;
		
		_maxHP = 100f;
		_hp = _maxHP;
		_defaultFireRate = 0.13f;
        _missleFireRate = 0.26f;
		_fireRate = _defaultFireRate;
		_BulletList = bulletList;
		_weaponReady = true;
        _missilesAvailable = true;
        _missilesReady = false;
        
        // TEST MISSILES
        _fireTimer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            _missilesReady = true;
                        } // end run()
                    }, _missleFireRate);
        // TEST MISSILES
		
		_defenseModifier = 1;
		_weaponLevel = 1;
		_maxWeaponLevel = 4;
	}
	
	// data members:
	TextureRegion[][] _views;

	// methods:
	
	@Override
	public void update()
	{
		if(!this.isDead())
		{
			super.update();
			if(_leftWingman != null) {
				if(_leftWingman.isDead())
					_leftWingman = null;
				else
					_leftWingman.update(_sprite.getX() - (3f * _sprite.getWidth()), _sprite.getY());
			}
			if(_rightWingman != null) {
				if(_rightWingman.isDead())
					_rightWingman = null;
				else
					_rightWingman.update(_sprite.getX() + (3f * _sprite.getWidth()), _sprite.getY());
			}
		}
	}

	@Override
	public boolean updateDestination()
	{
		if(!_spawnDestReached) {
			if(_sprite.getX() == _dest.x && _sprite.getY() == _dest.y)
				_spawnDestReached = true;
			return true;
		}
		if(Gdx.input.isTouched())
		{
			_spawnDestReached = true;
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
		if(Gdx.input.isTouched()) {
            if(_weaponReady) {
                fire(_weaponLevel);
                
                _weaponReady = false;
                
                _fireTimer.schedule(new Timer.Task(){
                        @Override
                        public void run() {
                            _weaponReady = true;
                        } // end run()
                    }, _fireRate);
            } // end if weapon ready
            
            if(_misslesAvailable && _missilesReady) {
                _BulletList.add(new Missile(_sprite.getX(), _sprite.getY(), .25f*(float)Math.PI, _assetManager));
                _BulletList.add(new Missile(_sprite.getX(), _sprite.getY(), .75f*(float)Math.PI, _assetManager));
                
                _missilesReady = false;
                
                _fireTimer.schedule(new Timer.Task() {
                        @Override
                        public void run() {
                            _missilesReady = true;
                        } // end run()
                    }, _missleFireRate);
            }
		}
	}
	
	public void fire(int level) {
		_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY()+_sprite.getHeight(), .5f*(float)Math.PI, _assetManager));
		
		if(level >= 2) {
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .45f*(float)Math.PI, _assetManager));
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .55f*(float)Math.PI, _assetManager));
		}
		if(level >= 3) {
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .4f*(float)Math.PI, _assetManager));
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), .6f*(float)Math.PI, _assetManager));
		}
		
		if(level >= 4) {
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), 0f, _assetManager));
			_BulletList.add(new HeroBullet(_sprite.getX(), _sprite.getY(), (float)Math.PI, _assetManager));
		}
	}

	@Override
	public void draw(Batch batch)
	{
		if(!this.isDead()) {
			super.draw(batch);
			if(_leftWingman != null)
				_leftWingman.draw(batch);
			if(_rightWingman != null)
				_rightWingman.draw(batch);
		}
	}
	
	
	
	
	
	
	public Ship getLeftWingman() { return _leftWingman; }
	public Ship getRightWingman() { return _rightWingman; }
	
	public void spawnLeftWingman() {
		if(_leftWingman == null)
			_leftWingman = new Wingman(_BulletList, _assetManager, 0, _sprite.getY());
	}
	public void spawnRightWingman() {
		if(_rightWingman == null)
			_rightWingman = new Wingman(_BulletList, _assetManager, Gdx.graphics.getWidth(), _sprite.getY());
	}
}
