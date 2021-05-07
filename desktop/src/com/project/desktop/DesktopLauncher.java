package com.project.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.project.Window;

public class DesktopLauncher {

	public static Window window;
	static LwjglApplicationConfiguration config;

	static void start() {
		config = new LwjglApplicationConfiguration();
		LwjglApplicationConfiguration.disableAudio = true;
		config.gles30ContextMinorVersion = 3;
		config.allowSoftwareMode=true;
		config.useGL30 = true;
		config.pauseWhenBackground = true;
		config.backgroundFPS = -1;
		config.title="Educational Platform";
		config.addIcon("./core/assets/icon32.png", Files.FileType.Local);
		window = new Window();
	}

	public static void main (String[] arg) {
		start();
		new LwjglApplication(window, config);
	}
}
