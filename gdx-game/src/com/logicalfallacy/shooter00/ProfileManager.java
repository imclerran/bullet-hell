package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.files.*;

public class ProfileManager
{
	int _highScore;
	int _lastScore;
	int _money;
	
	public void saveProfile() {
		FileHandle file = Gdx.files.internal("profile.vh");
		
		file.writeString(Integer.toString(_highScore), false);
		file.writeString(Integer.toString(_money), false);
	}
}
