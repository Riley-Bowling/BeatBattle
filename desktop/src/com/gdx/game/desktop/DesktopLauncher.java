package com.gdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gdx.game.GdxAction;
import com.gdx.game.GdxGame;
import com.gdx.game.GdxMusic;
import com.gdx.game.Gdx_3DShow;
import com.gdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		//new LwjglApplication(new GdxGame(), config);
		 new LwjglApplication(new GdxMusic(), config);
		//new LwjglApplication(new GdxAction(), config);
		//new LwjglApplication(new Gdx_3DShow(), config);
	}
}
