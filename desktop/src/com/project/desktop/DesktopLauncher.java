package com.project.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.project.Window;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.allowSoftwareMode=true;
		config.useGL30 = true;
		config.title="KakIHateYou";
		config.width = 800;
		config.height = 450;
		new LwjglApplication(new Window(), config);
	}
}
