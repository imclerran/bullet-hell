package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
//import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Timer;

public class HealthPowerup extends Powerup
{
	float _healthBonus;
	
	public HealthPowerup(float x, float y) {
		_texture = new Texture(Gdx.files.internal("data/health_pickup.png"));
		_sprite = new Sprite(_texture);
		_sprite.setOrigin(_sprite.getWidth()/2, _sprite.getHeight()/2);
		_sprite.setPosition(x, y);
		_sprite.setScale(0.1f*Gdx.graphics.getWidth()/_sprite.getWidth());
		_healthBonus = 50f;
		
		_dxdy = new Vector2();
		_dest = new Vector2();
		_dest.x = (Math.random() - .5f > 0) ? -1f : Gdx.graphics.getWidth() +1f;
		_dest.y = (float)Math.random() * Gdx.graphics.getHeight()/2f;
		_speed = 0.125f * Gdx.graphics.getHeight();
		
		_onScreen = 15f;
		_expired = false;
		_onScreenTimer.schedule(new Timer.Task(){
				@Override
				public void run() {
					_expired = true;
				} // end run()
			}, _onScreen);
	}

	@Override
	public void applyPickup(Player player)
	{
		// TODO: Implement this method
		player.getHero().addHP(_healthBonus);
	}
}
