package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;

public class Map
{
	public Map()
	{
		_texture = new Texture(Gdx.files.internal("data/runway_tile_p2.png"));
		_batch = new SpriteBatch();
		_y = new float[9];
		_dy = 200.0f;
		_offset = 0.0f;
		_tileHeight = Gdx.graphics.getHeight() / 8;
		_tileWidth = Gdx.graphics.getWidth();
	}
	
	// data members:
	Texture _texture;
	SpriteBatch _batch;
	
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
