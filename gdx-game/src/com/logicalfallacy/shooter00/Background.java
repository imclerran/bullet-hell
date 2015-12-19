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
		_texture_back = assetManager.get("data/space_background.png", Texture.class);
        _texture_fore = assetManager.get("runway_transparent.png", Texture.class);
		_y = new float[9];
		//_dy = 0.08f*Gdx.graphics.getHeight(); // 0.2f*
		_dy_back = 0.1f*Gdx.graphics.getHeight();
        _dy_fore = 0.15f*Gdx.graphics.getHeight();
		_offset_fore = 0.0f;
        _offset_back = 0.0f;
		_tileHeight = Gdx.graphics.getHeight() / 8;
		_tileWidth = Gdx.graphics.getWidth();
	}
	
	// data members:
	Texture _texture_back;
    Texture _texture_fore;
	
	float _dy_back;
    float _dy_fore;
	float[] _y_fore;
    float[] _y_back;
	float _offset_fore;
    float _offset_back
	float _tileHeight;
	float _tileWidth;
	
	// methods:
	
	// update position of map on screen
	public void update()
	{
		/*if(_offset > _tileHeight)
			_offset = 0;
		else
			_offset = _offset + _dy * Gdx.graphics.getDeltaTime();
			
		for(int i = 0; i < 9; i++)
		{
			_y[i] = (_tileHeight * i) - _offset;
		}*/
        updateBackground();
        updateForeground();
	}
    
    updateBackground() {
        if(_offset_back > _tileHeight)
            offset_back = _offset_back - tileHeight;
        else
            _offset_back = offset_back + _dy_back * Gdx.graphics.getDeltaTime();
        
        for(int i = 0; i < 9; i++) {
            _y_back[i] = (tileHeight * i) - _offset_back;
        }
        
    }
    
    updateForeground() {
        if(_offset_fore > _tileHeight)
            offset_fore = _offset_fore - tileHeight;
        else
            _offset_fore = offset_fore + _dy_fore * Gdx.graphics.getDeltaTime();
        
        for(int i = 0; i < 9; i++) {
            _y_fore[i] = (tileHeight * i) - _offset_fore;
        }
        
    }
	
	// render the map
	public void draw(Batch batch)
	{
		for(int i = 0; i < 9; i++)
		{
			batch.draw(_texture_back, 0, _y_back[i], _tileWidth, _tileHeight);
            batch.draw(_texture_fore, 0, _y_fore[i], _tileWidth, _tileHeight);
		}
	}
}
