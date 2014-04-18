package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;

public class Spinner extends Actor
{
	float _theta;
	float _deltaThetaDegrees;
	float _scaleHeight;
	float _scaleWidth;
	boolean _finished;
	Timer _pause;
	
	public Spinner() {
		Texture temp = new Texture(Gdx.files.internal("data/LibGDX.png"));
		
		_sprite = new Sprite(temp);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		_sprite.setX((float)Gdx.graphics.getWidth()/2);
		_sprite.setY(Gdx.graphics.getHeight()/2);
		_sprite.setScale(Gdx.graphics.getWidth()/64);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);

		_deltaThetaDegrees = 90.0f;
		_theta = -180.0f;
		_sprite.flip(true, false);
		_scaleWidth = (Gdx.graphics.getWidth()/_sprite.getWidth()/10*9);
		_scaleHeight = (Gdx.graphics.getWidth()/_sprite.getWidth()/10*9);
		_sprite.setScale(_scaleWidth, _scaleHeight);
		_finished = false;
	}

	@Override
	public void update()
	{
		if(0 >= _theta) {
			_theta += _deltaThetaDegrees *Gdx.graphics.getDeltaTime();
			_scaleWidth = calculateScale();
			if(-90f >= _theta)
				_sprite.flip(true, false);
			else
				_sprite.flip(false, false);
		}
		
		else{
			_pause.schedule(new Timer.Task() {
					@Override
					public void run() {
						_finished = true;
					} // end run()
				}, (float)Math.random());
		}
		
	}
	
	public boolean isFinished() { return _finished; }
	
	public float calculateScale() {
		return (float)Math.cos(degreesToRadians(_deltaThetaDegrees));
	}
	
	public float degreesToRadians(float degrees) {
		return  ((float) Math.PI * degrees / 180f);
	}
}
