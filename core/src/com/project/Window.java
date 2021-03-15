package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter{

    static Workspace workspace;
	static Font font;
	static int oldHeight;
	static WorkspaceTextLoader loader;
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
		font = new Font(Font.REGULAR, 14, Color.BLACK);
		font.init();
		//workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight(), "ололлоло 1. jjjjjjjjjjjjjjjjjjjjjjjjjj jjjjjjjjjjjjjjjjjjjjj jjjjjjjj jjjjjjjjjjjjjjj jjjjjjjjjjj jjjjjjj jjjjjjjjj jjjjjjj"));
		//workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-workspace.line_spacing-InterfaceParameters.MAIN_FONT.size, "олололо 2\n sjflsjefliwjefijwflejiwlejfliwejfliwejflwiejiwlegjilghwilewghlwghlgehwifweifjlweghlih\nsdlsdlfhkjsdhgkjhsgkjhsdgjkhsdjkghk"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-2*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 3"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-3*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 4"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-4*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 5"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-5*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 666666666  666  66666666666666 66666666666 666  666  6666"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-6*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 7"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-8*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 8"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-10*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 9"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-11*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 100000000 0000  00001 0"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-12*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 11"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-15*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 12"));
//		workspace.addItem(new SimpleText(115, Gdx.graphics.getHeight()-30*(workspace.line_spacing+InterfaceParameters.MAIN_FONT.size), "ололололололо 13"));
		workspace.init();
		loader = new WorkspaceTextLoader();
		loader.textLoad("core/assets/text0.theory", 200, Gdx.graphics.getHeight()/2, workspace);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.97f, 0.97f, 0.97f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//workspace.draw();
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
