package com.logicalfallacy.shooter00;

import java.lang.Math;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.Disposable;

public class Actor
{
	// data members:
	Sprite _sprite;
	Texture _texture;
	public boolean _deleteMe;
	
	// TODO: Move boolean _deleteMe to actor

	// methods:

	public void update() {
	}

	public void draw(Batch batch) {
		_sprite.draw(batch);
	}
	
	public Sprite getSprite() { return _sprite; }
	
	public Rectangle getRectangle() {
		return _sprite.getBoundingRectangle();
	}
	
	/*public void dispose() {
		if(_texture != null)
			_texture.dispose();
	}*/
}
