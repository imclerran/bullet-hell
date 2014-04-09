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
		_dy = 100.0f;
		offset = 0.0f;
	}
	
	// members:
	Texture _texture;
	SpriteBatch _batch;
	
	float _dy;
	float[] _y;
	float offset;
	
	// methods:
	public void update()
	{
		if(offset > Gdx.graphics.getHeight() / 8)
			offset = 0;
		else
			offset = _dy * Gdx.graphics.getDeltaTime();
			
	}
	
	public void draw(Batch batch)
	{
		//_batch.begin();
		batch.draw(_texture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
		batch.draw(_texture, 0, Gdx.graphics.getHeight()/8, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()/8);
		//_batch.end();
	}
}
