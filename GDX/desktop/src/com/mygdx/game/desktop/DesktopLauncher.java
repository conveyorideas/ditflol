package com.mygdx.game.desktop;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.DitFMain;
import com.mygdx.game.Macros;

public class DesktopLauncher implements Macros {
	public static void main (String[] arg) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Rectangle rect = ge.getMaximumWindowBounds();
		Dimension sSize = Toolkit.getDefaultToolkit ().getScreenSize ();
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dragon in the Fog";
		config.resizable = true;
		if(fullscreen == false){
		config.height = rect.height - 30;
		config.width = rect.width - 10;
		config.x = 0;
		config.y = 0;
		}else{
			config.fullscreen = fullscreen;
		}
		new LwjglApplication(new DitFMain(), config);
	}
}
