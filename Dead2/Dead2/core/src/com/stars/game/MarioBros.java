package com.stars.game;

import GamePlay.AllTanksData;
import GamePlay.Player;
import GamePlay.Tanks;
import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import Screens.MainMenu;

import java.util.HashMap;

import static com.badlogic.gdx.Gdx.graphics;

public class MarioBros extends Game {
	HashMap<Integer,User> UserList ;
	public SpriteBatch batch;
	public BitmapFont font;
	public AllTanksData tanks;
	public Sound ClickSound;
	public Player FirstPlayer;
	public Player SecondPlayer;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();

		tanks = new AllTanksData();
		ClickSound = Gdx.audio.newSound(Gdx.files.internal("ClickSound.wav"));
		ClickSound.setVolume(1,100);
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}
	public void dispose(){
		font.dispose();
		super.dispose();
		batch.dispose();
		ClickSound.dispose();
	}
}

class User extends MarioBros{
	private String Name;
	private String Email;
	private int coins;
	private HashMap<Integer,Tanks> TanksAvailable;

	public User(String Name,String Email){
		this.Name = Name;
		this.Email = Email;
	}
	public String getName(){
		return this.Name;
	}
	public void setName(String Name){
		this.Name = Name;
	}
	public String getEmail(){
		return this.Email;
	}
	public void setEmail(String Email){
		this.Email = Email;
	}
	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
	}
	public HashMap<Integer, Tanks> getTanksAvailable() {
		return TanksAvailable;
	}
	public void setTanksAvailable(HashMap<Integer, Tanks> tanksAvailable) {
		TanksAvailable = tanksAvailable;
	}
}
