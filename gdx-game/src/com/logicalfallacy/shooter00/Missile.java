package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.*;

class Missile extends Bullet {
    // data members:
    EnemyManager _enemies;
    Enemy _target;
    int _distToActivate;
    float _distTravelled;
    float _maxDeltaAngle;
    
    // default constructor
    public Missile() {
        _damage = 50;
		_angle = (float)Math.PI/2;
		_speed = 500;
        _distToActivate = 500;
        _distTravelled = 0;
		_target = null;
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		Texture temp = new Texture(Gdx.files.internal("data/missile.png"));
		_sprite = new Sprite(temp);
		temp.dispose();
    }
    
    
    // constructor with coords + direction
	public Missile(float x, float y, float angle, AssetManager assetManager, EnemyManager enemies)
	{
		_damage = 50;
		_angle = angle;
		_speed = 500;
        _distToActivate = 100;
        _distTravelled = 0;
		_target = null;
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = assetManager.get("data/missile.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(Gdx.graphics.getWidth()/200);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
        _enemies = enemies;
		_maxDeltaAngle = .03f*(float)Math.PI;
	}
    
    @Override
	public void update()
	{
        if(_distTravelled < _distToActivate)
            calcDistance();
            
        else {
            if(_target == null)
                findTarget();
            else {
                calcAngle();
                calcDxDy();
            }
        }
		_sprite.setX(_sprite.getX() + _dxdy.x * Gdx.graphics.getDeltaTime());
		_sprite.setY(_sprite.getY() + _dxdy.y * Gdx.graphics.getDeltaTime());
		
		if(_sprite.getX() < 0.0f || _sprite.getX() > Gdx.graphics.getWidth())
			_deleteMe = true;
			
		if(_sprite.getY() < 0.0f || _sprite.getY() > Gdx.graphics.getHeight())
			_deleteMe = true;
	}
    
    public void calcDxDy() {
		_dxdy.x = (float)Math.cos(_angle) * _speed;
		_dxdy.y = (float)Math.sin(_angle) * _speed;
	}
    
    public void calcDistance() {
        float deltaDist = (float)Math.sqrt((_dxdy.x*_dxdy.x) + (_dxdy.y*_dxdy.y));
        _distTravelled += deltaDist;
    }
    
    public void calcAngle() {
        float deltaX = _target.getX() - _sprite.getX();
        float deltaY = _target.getY() - _sprite.getY();
        float angleToTarget = (float)Math.tan(deltaY/deltaX);
        float adjustedMaxDeltaAngle = _maxDeltaAngle*Gdx.graphics.getDeltaTime();
        
        if(_angle > angleToTarget) {
            if((_angle - angleToTarget ) > adjustedMaxDeltaAngle)
                _angle -= adjustedMaxDeltaAngle;
            else
                _angle = angleToTarget;
        }
        else if(_angle < angleToTarget)
            if((angleToTarget - _angle) > adjustedMaxDeltaAngle)
                _angle += adjustedMaxDeltaAngle;
    }
    
    public void findTarget() {
        _target = _enemies.getEnemy(1);
    }
}
