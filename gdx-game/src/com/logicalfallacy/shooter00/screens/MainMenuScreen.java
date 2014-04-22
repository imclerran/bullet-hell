package com.logicalfallacy.shooter00.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import java.util.*;
//import android.text.style.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;

import com.logicalfallacy.shooter00.*;

public class MainMenuScreen implements Screen
{
	MyGdxGame game;
	Stage stage;
	Table table;
	Button startGameButton;
	
	public MainMenuScreen(MyGdxGame mygame) {
		game = mygame;
		create();
	}
	
	@Override
	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		
		//add widgets to table here.
		startGameButton = new Button();
		table.addActor(startGameButton);
	}

	@Override
	public void render(float p1)
	{
		// TODO: Implement this method
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
		Table.drawDebug(stage);
	}

	@Override
	public void hide()
	{
		// TODO: Implement this method
	}

	@Override
	public void show()
	{
		// TODO: Implement this method
	}

	@Override
	public void pause()
	{
		// TODO: Implement this method
	}

	@Override
	public void resume()
	{
		// TODO: Implement this method
	}

	@Override
	public void resize(int p1, int p2)
	{
		// TODO: Implement this method
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
		stage.dispose();
	}
}
