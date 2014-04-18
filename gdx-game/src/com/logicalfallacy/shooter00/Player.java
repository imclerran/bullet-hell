package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;

public class Player
{
	Hero _hero;
	ArrayList<Bullet> _bullets;
	int _score;
	int _lives;
	
	BitmapFont font;
	TextureAtlas textureAtlas;

	public void Player() {
		_hero = new Hero(_bullets);
		_bullets = new ArrayList();
		_score = 0;
		_lives = 3;
		
		textureAtlas = new TextureAtlas("data/main");
		font = new BitmapFont(Gdx.files.internal("data/SF Square Head.ttf.fnt"),
		textureAtlas.findRegion("calibri"), false);
	}
	
	public void update() {
		_hero.update();

		for(int i = 0; i < _bullets.size(); i++) {
			_bullets.get(i).update();
		}

		deleteBullets();
	}
	
	public void draw(Batch batch) {
		for(int i = 0; i < _bullets.size(); i++) {
			_bullets.get(i).draw(batch);
		}

		_hero.draw(batch);
		
		font.draw(batch, Integer.toString(_score), 0, 0);
	}
	
	public void bulletHits(Ship target) {
		Rectangle intersection = new Rectangle();

		for(int i = 0; i < _bullets.size(); i++) {
			if(Intersector.intersectRectangles(
				   getBulletRect(i), target.getRectangle(), intersection))
			{
				target.hit(_bullets.get(i).getDamage());
				_bullets.remove(i);
				_score += target.getPoints();
			}
		}
	}
	
	public void deleteBullets()
	{
		for(int i = 0; i < _bullets.size(); i++)
		{
			if(_bullets.get(i)._deleteMe)
				_bullets.remove(i);
		}
	}
	
	public Rectangle getBulletRect(int i) {
		return _bullets.get(i).getRectangle();
	}

	public Rectangle getShipRect(int i) {
		return _hero.getRectangle();
	}
}
