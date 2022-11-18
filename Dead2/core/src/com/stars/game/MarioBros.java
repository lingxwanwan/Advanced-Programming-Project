package com.stars.game;

import GamePlay.AllTanksData;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import Screens.MainMenu;

import static com.badlogic.gdx.Gdx.graphics;

public class MarioBros extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public AllTanksData tanks;
	public Sound backGroundSound ;
	public Sound ClickSound;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		tanks = new AllTanksData();
		backGroundSound = Gdx.audio.newSound(Gdx.files.internal("BackGroundMusic.mp3"));
		ClickSound = Gdx.audio.newSound(Gdx.files.internal("ClickSound.mp3"));
		ClickSound.setVolume(1,100);
		graphics.setContinuousRendering(false);
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
		backGroundSound.play();
	}
	public void dispose(){
		font.dispose();
		super.dispose();
		batch.dispose();
		backGroundSound.dispose();
		ClickSound.dispose();
	}
}
