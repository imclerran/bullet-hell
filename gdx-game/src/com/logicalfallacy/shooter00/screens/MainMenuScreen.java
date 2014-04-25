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
import com.badlogic.gdx.scenes.scene2d.utils.*;

public class MainMenuScreen implements Screen
{
	MyGdxGame game;
	Skin skin;
	Stage stage;
	Table table;
	TextButton startGameButton;
	
	public MainMenuScreen(MyGdxGame mygame) {
		game = mygame;
		
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		skin = new Skin(Gdx.files.internal("data/uiskin.json"));

		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		//add widgets to table here.
		startGameButton = new TextButton("Start", skin);
		table.addActor(startGameButton);
		startGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.gameScreen);
			}
		});
	}
	
	@Override
	public void create() {
		
	}

	@Override
	public void render(float p1)
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
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
	public void resize(int width, int height)
	{
		// TODO: Implement this method
		startGameButton.setHeight(height/12);
		startGameButton.setWidth(width/2);
		startGameButton.getLabel().setFontScale(startGameButton.getHeight()/24);
		startGameButton.setPosition(
			(width - startGameButton.getWidth())/2,
			(height - startGameButton.getHeight())/2);
			
		
	}

	@Override
	public void dispose()
	{
		// TODO: Implement this method
		stage.dispose();
	}
}
