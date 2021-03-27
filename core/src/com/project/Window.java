package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter{

    static Workspace workspace;
	static int oldHeight;
	static WorkspaceLoader loader;
	static Interface an_interface;

	@Override
	public void create () {
		InterfaceParameters.changeColors(InterfaceParameters.Colors.SKY_BLUE);
		an_interface = new Interface();
		an_interface.init();
		oldHeight = Gdx.graphics.getHeight();
		workspace = new Workspace(2, 20);
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		loader = new WorkspaceLoader();
		loader.textLoad("core/assets/text0.theory", 200, Gdx.graphics.getHeight()/2, workspace);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.97f, 0.97f, 0.97f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		workspace.draw();
        an_interface.draw();
		if (Gdx.input.isKeyPressed(ESCAPE)){
			Gdx.app.exit();
		}
	}

	@Override
    public void resize(int width, int height) {
		if((width < 320) || (height < 240)) {
			Gdx.graphics.setWindowedMode(640, 480);
		}
		an_interface.resize();
		workspace.strings = null;
		loader.textLoad("core/assets/text0.theory", 200, Gdx.graphics.getHeight()/2, workspace);
		oldHeight = Gdx.graphics.getHeight();
	}

	@Override
	public void dispose () {
		an_interface.dispose();
	}
}
