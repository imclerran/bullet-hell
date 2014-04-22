package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class HealthBar extends Actor
{
	float _percent;
	int _lives;
	Texture _full;
	Texture _mid;
	Texture _low;
	Texture _lifeTexture;
	Sprite _lifeSprite;
	
	public HealthBar() {
		_percent = 100f;
		
		_full = new Texture(Gdx.files.internal("data/health_full.png"));
		_mid = new Texture(Gdx.files.internal("data/health_mid.png"));
		_low = new Texture(Gdx.files.internal("data/health_low.png"));
		_lifeTexture = new Texture(Gdx.files.internal("data/hero.png"));
		
		_sprite = new Sprite(_full);
		_sprite.setOrigin(0, _sprite.getHeight());
		_sprite.setPosition(0, Gdx.graphics.getHeight());
		_sprite.setScale(
			_percent*Gdx.graphics.getWidth()/_sprite.getWidth(),
			0.02f*Gdx.graphics.getHeight()/_sprite.getHeight());
			
		_lifeSprite = new Sprite(_lifeTexture);
		_lifeSprite.setOrigin(0, _lifeSprite.getHeight());
		_lifeSprite.setScale(0.03f*Gdx.graphics.getHeight()/_lifeSprite.getHeight());
		_lifeSprite.setY(Gdx.graphics.getHeight() - _sprite.getHeight()*_sprite.getScaleY());
	}
	
	public void setPercent(float percent) { _percent = percent; }
	
	public void setLives(int lives) { _lives = lives; }

	@Override
	public void update() {
		_sprite.setScale(
			_percent*Gdx.graphics.getWidth()/_sprite.getWidth(),
			0.02f*Gdx.graphics.getHeight()/_sprite.getHeight());
		
		if(_percent < 0.33f)
			_sprite.setTexture(_low);
			
		else if(_percent < 0.66f)
			_sprite.setTexture(_mid);
			
		else
			_sprite.setTexture(_full);
	}

	@Override
	public void draw(Batch batch)
	{
		super.draw(batch);
		
		for(int i = 0; i < _lives; i++) {
			_lifeSprite.setX(i*_lifeSprite.getWidth()*_lifeSprite.getScaleX());
			_lifeSprite.draw(batch);
		}
	}
	
	
}
