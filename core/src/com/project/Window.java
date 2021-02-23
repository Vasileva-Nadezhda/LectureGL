package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter{

	static Font font;
	static String text="Должно разделяться примерно вот тут.";
	static Interface an_interface;

	@Override
	public void create () {
		InterfaceColors.changeColors(InterfaceColors.Colors.SKY_BLUE);
		an_interface = new Interface();
		an_interface.init();
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		font = new Font(Font.ITALIC, 24, Color.LIME);
		font.init();
		text = "Здесь еще вроде пишет. sssvsdf sdgsdgsdg sdgsdgsgewg wegwgwgweg wgewg.";
		Gdx.input.setInputProcessor(new InputAdapter());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		an_interface.draw();
		font.drawWithWrap(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, text);
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
	}

	@Override
	public void dispose () {
		an_interface.dispose();
	}
}
