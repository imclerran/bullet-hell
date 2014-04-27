package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.files.*;
import com.badlogic.gdx.utils.Json;

public class ProfileManager
{
	int _highScore;
	int _lastScore;
	int _money;
	
	public void saveProfile() {
		FileHandle file = Gdx.files.local("profile.json");
		String string = new String();
		Json json = new Json();
		
		string = json.toJson(this, ProfileManager.class);
		
		file.writeString(string, false);
		
		//file.writeString(Integer.toString(_highScore), false);
		//file.writeString(Integer.toString(_money), false);
	}
	
	public void loadProfile() {
		String string = new String();
		Json json = new Json();
		
		if(Gdx.files.local("profile.json").exists()){
			FileHandle file = Gdx.files.local("profile.json");
			string = file.readString();
			
			_highScore = json.fromJson(ProfileManager.class, string).getHighScore();
			_money = json.fromJson(ProfileManager.class, string).getMoney();
		}		
		else {
			_highScore = 0;
			_money = 0;
		}
		
		_lastScore = 0;
	}
	
	public void newScore(int score) {
		_lastScore = score;
		if(_lastScore > _highScore)
			_highScore = _lastScore;
	}
	
	public int getHighScore() { return _highScore;}
	public int getMoney() { return _money; }
}
