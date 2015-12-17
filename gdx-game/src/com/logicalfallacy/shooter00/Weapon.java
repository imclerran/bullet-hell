package com.logicalfallacy.shooter00;

import java.lang.Math;
import java.util.ArrayList;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.files.*;

public class Weapon
{
	int 	_level;
	float 	_baseDamage;
	float 	_damageLevelModifier;
	float 	_baseFireRate;
	float 	_rofLevelModifier;
	String 	_bulletType;
	boolean _ready;
	Timer 	_fireTimer;
	
	public Weapon(String weaponType) {
		_level = 1;
		
		String fileName = weaponType + ".json";
		if(Gdx.files.internal(fileName).exists()) {
			FileHandle file = Gdx.files.internal(fileName);
			Json json = new Json();
			String temp;
			
			temp = file.readString();
			_baseDamage = json.fromJson(Weapon.class, temp).getBaseDamage();
			_damageLevelModifier = json.fromJson(Weapon.class, temp).getDamageLevelModifier();
			// TODO: finish construction from json
		}
		else {
			// TODO: default construction
		}
	}
	
	public void fire(Array bullets) {
		if(_ready)
			bullets.add(new Bullet(/*_bulletType*/));
		
		_fireTimer.schedule(new Timer.Task(){
				@Override
				public void run() {
					_ready = true;
				} // end run()
			}, _baseFireRate*_rofLevelModifier);
	}
	
	
	
	public int 	getLevel() { return _level; }
	public float getBaseDamage() { return _baseDamage; }
	public float getDamageLevelModifier() { return	_damageLevelModifier; }
	public float getBaseFireRate() { return _baseFireRate; }
	public float getRofLevelModifier() { return _rofLevelModifier; }
	public String getBulletType() { return _bulletType; }
	public boolean isReady() { return _ready; }
}
