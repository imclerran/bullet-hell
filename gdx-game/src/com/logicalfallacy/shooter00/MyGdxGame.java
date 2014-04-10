package com.logicalfallacy.shooter00;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;

public class MyGdxGame implements ApplicationListener
{
	Texture texture;
	Map BG;
	Hero hero;
	SpriteBatch batch;
	ArrayList<Bullet> bulletList;

	@Override
	public void create()
	{
		//texture = new Texture(Gdx.files.internal("android.jpg"));
		texture = new Texture(Gdx.files.internal("data/runway_tile_p2.png"));
		BG = new Map();
		bulletList = new ArrayList();
		hero = new Hero(bulletList);
		batch = new SpriteBatch();
		
	}

	@Override
	public void render()
	{        
	    Gdx.gl.glClearColor(1, 1, 1, 1);
	    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		BG.update();
		hero.update();
		batch.begin();
		//batch.draw(texture, Gdx.graphics.getWidth() / 4, 0, 
		//		   Gdx.graphics.getWidth() / 2, Gdx.graphics.getWidth() / 2);
		BG.draw(batch);
		for(Bullet b : bulletList)
		{
			b.update();
			b.draw(batch);
		}
		hero.draw(batch);
		batch.end();
		deleteBullets();
	}
	
	public void deleteBullets()
	{
		for(int i = 0; i < bulletList.size(); i++)
		{
			if(bulletList.get(i)._deleteMe)
				bulletList.remove(i);
		}
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}
}
