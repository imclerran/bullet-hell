package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.assets.*;

public class Background
{
	public Background(AssetManager assetManager)
	{
		//_texture = assetManager.get("data/runway_tile_black.png", Texture.class);
		_texture = assetManager.get("data/space_background.png", Texture.class);
		_y = new float[9];
		//_dy = 0.08f*Gdx.graphics.getHeight(); // 0.2f*
		_dy = 0.1f*Gdx.graphics.getHeight();
		_offset = 0.0f;
		_tileHeight = Gdx.graphics.getHeight() / 8;
		_tileWidth = Gdx.graphics.getWidth();
	}
	
	// data members:
	Texture _texture;
	
	float _dy;
	float[] _y;
	float _offset;
	float _tileHeight;
	float _tileWidth;
	
	// methods:
	
	// update position of map on screen
	public void update()
	{
		if(_offset > _tileHeight)
			_offset = 0;
		else
			_offset = _offset + _dy * Gdx.graphics.getDeltaTime();
			
		for(int i = 0; i < 9; i++)
		{
			_y[i] = (_tileHeight * i) - _offset;
		}
	}
	
	// render the map
	public void draw(Batch batch)
	{
		for(int i = 0; i < 9; i++)
		{
			batch.draw(_texture, 0, _y[i], _tileWidth, _tileHeight);
		}
	}
}
