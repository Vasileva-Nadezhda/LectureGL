package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter{

	Figure figure;
	Figure figure1;
	Figure figure2;
	Figure figure3;
	Figure figure4;
	Texture texture;
	static Font font;
	String text="a";
	Font buttonFont;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;

	@Override
	public void create () {
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		font = new Font(Font.CALIBRI_LIGHTITALIC, 30, Color.LIME);
		font.init();
		figure = new Figure(new float[]{
				-0.6f,  1.0f, 0.0f,
				-0.6f,  0.8f, 0.0f,
				-1.0f,  0.8f, 0.0f,
				-1.0f,  1.0f, 0.0f
		}, new float[]{
			1.0f, 0.0f, 0.0f, 1.0f,
			0.0f, 1.0f, 0.0f, 1.0f,
			0.0f, 0.0f, 1.0f, 1.0f,
			1.0f, 1.0f, 0.0f, 1.0f
			},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure.init();
		figure1 = new Figure(new float[]{
				-0.6f,  0.8f, 0.0f,
				-0.6f,  0.6f, 0.0f,
				-1.0f, 0.6f, 0.0f,
				-1.0f, 0.8f, 0.0f
		}, new float[]{
				1.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure1.init();
		figure2 = new Figure(new float[]{
				-0.6f,  0.6f, 0.0f,
				-0.6f,  0.4f, 0.0f,
				-1.0f, 0.4f, 0.0f,
				-1.0f, 0.6f, 0.0f
		}, new float[]{
				1.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure2.init();
		figure3 = new Figure(new float[]{
				-0.6f,  0.4f, 0.0f,
				-0.6f,  0.2f, 0.0f,
				-1.0f, 0.2f, 0.0f,
				-1.0f, 0.4f, 0.0f
		}, new float[]{
				1.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure3.init();
		figure4 = new Figure(new float[]{
				-0.6f,  0.2f, 0.0f,
				-0.6f,  0.0f, 0.0f,
				-1.0f, 0.0f, 0.0f,
				-1.0f, 0.2f, 0.0f
		}, new float[]{
				1.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure4.init();
		texture = new Texture(new float[]{
				-0.8f,  0.95f, 0.0f,
				-0.8f,  0.85f, 0.0f,
				-0.9f,  0.85f, 0.0f,
				-0.9f,  0.95f, 0.0f
			}, new float[]{
				1.0f, 0.0f, 0.0f, 1.0f,
				0.0f, 1.0f, 0.0f, 1.0f,
				0.0f, 0.0f, 1.0f, 1.0f,
				1.0f, 1.0f, 0.0f, 1.0f
			},
				new float[]{
						1.0f, 0.0f,
						1.0f, 1.0f,
						0.0f, 1.0f,
						0.0f, 0.0f
				},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		buttonFont= new Font();
		buttonFont.size = 14;
		buttonFont.init();
		texture.init("core/assets/tex1.png");
		 button1 = new Button(figure, texture, buttonFont, "button1", -0.75f, 0.9f, ()->this.text = button1.name);
		 button1.init();
		button2 = new Button(figure1, null, buttonFont, "button2", -0.75f, 0.7f, ()->this.text = button2.name);
		button2.init();
		button3 = new Button(figure2, null, buttonFont, "button3", -0.75f, 0.5f, ()->this.text = button3.name);
		button3.init();
		button4 = new Button(figure3, null, buttonFont, "button4", -0.75f, 0.3f, ()->this.text = button4.name);
		button4.init();
		button5 = new Button(figure4, null, buttonFont, "button5", -0.75f, 0.1f, ()->this.text = button5.name);
		button5.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		button1.draw();
		button2.draw();
		button3.draw();
		button4.draw();
		button5.draw();
		font.draw(0.0f, 0.0f, this.text);
		if (Gdx.input.isKeyPressed(ESCAPE)){
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void dispose () {

	}
}
