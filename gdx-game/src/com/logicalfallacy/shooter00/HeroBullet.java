package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Audio;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.assets.*;

public class HeroBullet extends Bullet
{
	public Sound _sound;
	/*public HeroBullet(float x, float y)
	{
		_damage = 10;
		_angle = (float)Math.PI/2;
		_speed = 0.8f*Gdx.graphics.getHeight();
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = new Texture(Gdx.files.internal("data/blue_bullet.png"));
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(0.03f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		//_sound = Gdx.audio.newSound(Gdx.files.internal("gun_silencer.wav"));
		//_sound.play();
	}*/
	
	public HeroBullet(float x, float y, float angle, AssetManager assetManager)
	{
		_damage = 10;
		_angle = angle;
		_speed = 0.6f*Gdx.graphics.getHeight();
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = assetManager.get("data/blue_bullet.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(0.03f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		_sprite.setRotation((_angle*180f/(float)Math.PI)-90f);
		_sound = assetManager.get("data/gun_zap2.wav", Sound.class);
		_sound.play(0.05f);
	}

	/*@Override
	public void dispose() {
		super.dispose();
		//_sound.dispose();
	}*/
	
	
}
