package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.assets.*;

public class HealthBar extends Actor
{
	float _percent;
	int _lives;
	float _defenseModifier;
	Texture _full;
	Texture _mid;
	Texture _low;
	Texture _invincible;
	Texture _lifeTexture;
	Sprite _lifeSprite;
	
	public HealthBar(AssetManager assetManager) {
		_percent = 100f;
		
		_full = assetManager.get("data/health_full.png", Texture.class);
		_mid = assetManager.get("data/health_mid.png", Texture.class);
		_low = assetManager.get("data/health_low.png", Texture.class);
		_invincible = assetManager.get("data/health_invincible.png", Texture.class);
		_lifeTexture = assetManager.get("data/hero.png", Texture.class);
		
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
	
	public void setDefenseModifier(float defenseModifier) { _defenseModifier = defenseModifier; }

	@Override
	public void update() {
		_sprite.setScale(
			_percent*Gdx.graphics.getWidth()/_sprite.getWidth(),
			0.02f*Gdx.graphics.getHeight()/_sprite.getHeight());
		
		if(_defenseModifier == 0f)
			_sprite.setTexture(_invincible);
		else if(_percent < 0.33f)
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
