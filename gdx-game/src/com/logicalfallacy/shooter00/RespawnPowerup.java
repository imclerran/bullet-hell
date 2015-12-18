package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Timer;

public class RespawnPowerup extends Powerup
{
	public RespawnPowerup() {
		_duration = 1.5f;
		_expired = false;
		_timer.schedule(new Timer.Task(){
				@Override
				public void run() {
					_deleteMe = true;
				} // end run()
			}, _duration);
			
		_applyToWingman = false;
		_dropOdds = 1f;
	}
	
	@Override
	public void activate() {
		_timer.schedule(new Timer.Task(){
				@Override
				public void run() {
					_deleteMe = true;
				} // end run()
			}, _duration);
	}
	
	@Override
	public void applyPickup(Player player)
	{
		player.getHero().setDefenseModifier(0);
	}
}
