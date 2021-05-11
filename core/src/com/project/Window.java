package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.util.ArrayList;
import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter {

	static ArrayList<Workspace> workspaces = new ArrayList<>();
	static int oldHeight;
	static int oldWidth;
	static Interface an_interface;

	@Override
	public void create () {
		InterfaceParameters.changeColors(InterfaceParameters.Colors.SKY_BLUE);
		an_interface = new Interface();
		an_interface.init();
		oldHeight = Gdx.graphics.getHeight();
		oldWidth = Gdx.graphics.getWidth();
		workspaces.add(new Workspace("./core/assets/gallery.theory"));
		workspaces.get(workspaces.size() - 1).init();
		Workspace.input_adapter = new InputAdapter();
		workspaces.get(0).setInputAdapter();
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		Gdx.gl.glClearColor(1, 1, 1, 1);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		workspaces.get(workspaces.size() - 1).draw();
        an_interface.draw();
		if (Gdx.input.isKeyPressed(ESCAPE)) {
			Gdx.app.exit();
		}
	}

	public static void openSection (String location) {
		for (Workspace workspace : workspaces) {
			workspace.dispose();
		}
		workspaces.clear();
		workspaces.add(new Workspace(location));
		workspaces.get(0).init();
		workspaces.get(0).setInputAdapter();
		System.gc();
	}

	public static void openSubsection (String location) {
		workspaces.add(new Workspace(location));
		workspaces.get(workspaces.size() - 1).init();
		workspaces.get(workspaces.size() - 1).setInputAdapter();
	}

	public static void closeLastSubsection () {
		workspaces.get(workspaces.size() - 1).dispose();
		workspaces.remove(workspaces.size() - 1);
		workspaces.get(workspaces.size() - 1).setInputAdapter();
	}

	@Override
    public void resize (int width, int height) {
		if((width < 320) || (height < 240)) {
			Gdx.graphics.setWindowedMode(640, 480);
		}
		an_interface.resize();
		for (Workspace workspace : workspaces) {
			workspace.resize();
		}
		oldHeight = Gdx.graphics.getHeight();
		oldWidth = Gdx.graphics.getWidth();
		System.gc();
	}

	@Override
	public void dispose () {
		an_interface.dispose();
		for (Workspace workspace : workspaces) {
			workspace.dispose();
		}
		workspaces.clear();
		InterfaceParameters.dispose();
	}

}