package com.stars.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import Screens.MainMenu;

public class MarioBros extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
		//batch.setProjectionMatrix(camera.combined);

		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		/*batch.begin();
		mapSprite.draw(batch);
		batch.end();*/
	}
}
