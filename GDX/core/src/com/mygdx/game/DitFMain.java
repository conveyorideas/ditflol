package com.mygdx.game;

import com.badlogic.gdx.Game;



public class DitFMain extends Game {
	
	@Override
	public void create () {
		ScreenManager.getInstance().init(this);
		ScreenManager.getInstance().show(CustomScreen.LOAD_SCREEN);
	}
}
