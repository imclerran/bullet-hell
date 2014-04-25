package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.assets.*;

public class EnemyBullet extends Bullet
{	
	/*public EnemyBullet(float x, float y)
	{
		_damage = 10;
		_angle = (float)Math.PI/2;
		_speed = 0.4f*Gdx.graphics.getHeight();
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = new Texture(Gdx.files.internal("data/enemy_bullet.png"));
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(0.03f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
	}*/
	
	public EnemyBullet(float x, float y, float angle, AssetManager assetManager)
	{
		_damage = 10;
		_angle = angle;
		_speed = 0.42f*Gdx.graphics.getHeight(); // .3f*
		_dxdy = new Vector2();
		calcDxDy();
		_deleteMe = false;
		_texture = assetManager.get("data/enemy_bullet.png", Texture.class);
		_sprite = new Sprite(_texture);
		_sprite.setX(x);
		_sprite.setY(y);
		_sprite.setScale(0.03f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);

	}
}
