package com.mygdx.game;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CreatePScreen implements Screen {

	Skin skin;
	private String locationgame = DitFMain.class.getProtectionDomain().getCodeSource().getLocation().getPath();
	private String dirPathgame = new File(locationgame).getParent();
	DocumentBuilder builder;
	int a = 1;
	TextField name;
	Button button;
	Stage stage;
	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;
	Texture clothes[][];
	Texture playerTexture;
	Sprite playerSprite;
	Sprite chr[] = new Sprite[3];
	public CreatePScreen() {
		ParamLangXML();
	}
	
	private void WriteParamXML(int data, String name) throws TransformerException, IOException {
		Document doc=builder.newDocument();
		Element RootElement = doc.createElement("data");
		
			Element PlayerElementName = doc.createElement("name");
			PlayerElementName.appendChild(doc.createTextNode(name));
			RootElement.appendChild(PlayerElementName);
		doc.appendChild(RootElement);
		
		Transformer t = TransformerFactory.newInstance().newTransformer();
		t.setOutputProperty(OutputKeys.METHOD, "xml");
		t.setOutputProperty(OutputKeys.INDENT, "yes");
		t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(dirPathgame + "/assets/" + name + "/data.xml")));
		
		ScreenManager.getInstance().show(CustomScreen.GAME);
	}

	private void ParamLangXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try { builder = factory.newDocumentBuilder(); }
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(100 * (100 / 50), 50, camera);
		viewport.apply();
		camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		name = new TextField("Your Name", skin);
		name.setSize(300, 40);
		name.setPosition(Gdx.graphics.getWidth()/2-name.getWidth()/2, Gdx.graphics.getHeight()/2-name.getHeight()/2 - 270);
		button = new Button(skin);
		button.setName("PressMe");
		button.setSize(300, 40);
		button.setPosition(name.getX(),name.getY()-10-button.getHeight());
		button.add("CREATE PLAYER");
		clothes = new Texture[3][6];
		for(int i = 0; i <= 2; i++){
			for(int j = 0; j <= 5; j++){
				clothes[i][j] =  GameAssetManager.getInstance().get("sprites/player/" + (i+1) + "/" + (j+1) + ".png");
			}
		}
		playerTexture = GameAssetManager.getInstance().get("sprites/player/player.png");
		playerSprite = new Sprite(playerTexture);
		playerSprite.setSize(playerTexture.getWidth()/8, playerTexture.getHeight()/8);
		playerSprite.setPosition(Gdx.graphics.getWidth() /2 -(playerSprite.getWidth()/2), Gdx.graphics.getHeight() /2 -(playerSprite.getHeight()/2)+30);
		for(int i = 0;i<=2;i++)
		{
			chr[i] = new Sprite(clothes[i][i]);
			chr[i].setSize(clothes[i][i].getWidth()/8, clothes[i][i].getHeight()/8);
		}
		chr[0].setPosition(Gdx.graphics.getWidth() /2 -(chr[0].getWidth()/2), Gdx.graphics.getHeight() /2 -(chr[0].getHeight()/2)+36);
		chr[1].setPosition(Gdx.graphics.getWidth() /2 -(chr[1].getWidth()/2), Gdx.graphics.getHeight() /2 -(chr[1].getHeight()/2)+30);
		chr[2].setPosition(Gdx.graphics.getWidth() /2 -(chr[2].getWidth()/2), Gdx.graphics.getHeight() /2 -(chr[2].getHeight()/2)+26);
		
		stage.addActor(name);
		stage.addActor(button);
	}

	@Override
	public void render(float delta) {
		update(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		playerSprite.draw(batch);
		for(int i =0;i<=2;i++)
		{
		 chr[i].draw(batch);
		}
		batch.end();
		stage.act(delta);
		stage.draw();
	}

	private void update(float delta) {
		camera.update();
		stage.getBatch().setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);
		if(button.isPressed()) initPlayer(name.getText(), 0);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
 
	@Override
	public void hide() {}

	@Override
	public void dispose() {
		skin.dispose();
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 5; j++){
				clothes[i][j].dispose();;
			}
		}
		playerTexture.dispose();
		batch.dispose();
		stage.dispose();
	}
	
	public void initPlayer(String name, int data){
		System.out.println("Initialization player start...");
		System.out.println("Jar file: " + dirPathgame);
		new File(dirPathgame + "/assets/" + name + "/").mkdir();
		try {
			if(new File(dirPathgame + "/assets/" + name + "/data.xml").exists()){
				System.out.println("Player is exists...");
				ScreenManager.getInstance().show(CustomScreen.GAME);
			}else{
				System.err.println("Player is don't exists...");
				WriteParamXML(data, name);
			}
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
