package com.mygdx.game;

import java.io.File;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class LoadScreen implements Screen {
	
	private String locationgame = DitFMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private String dirPathgame = new File(locationgame).getParent();

	public LoadScreen() {}
	
	private void loadAssets() {
		GameAssetManager.getInstance().load("sprites/player/1/1.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/1/2.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/1/3.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/1/4.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/1/5.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/1/6.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/2/1.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/2/2.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/2/3.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/2/4.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/2/5.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/2/6.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/3/1.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/3/2.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/3/3.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/3/4.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/3/5.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/3/6.png", Texture.class);
		GameAssetManager.getInstance().load("sprites/player/player.png", Texture.class);

	}
	
	@Override
	public void show() {
		
		GameAssetManager.getInstance().init();
		loadAssets();
		createPath();

	}

	private void createPath() {
		if(new File(dirPathgame + "/assets/").exists()){
			 return;
		}else{
			new File(dirPathgame + "/assets/").mkdirs();
		}
	}

	@Override
	public void render(float delta) {
		if(GameAssetManager.getInstance().update()){
			ScreenManager.getInstance().show(CustomScreen.CREATE_PLAYER);
		}
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
