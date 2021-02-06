package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter{

	Texture figure;
	Texture figure1;
	Figure figure2;
	Font font;

	@Override
	public void create () {
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		font = new Font(Font.CALIBRI_LIGHTITALIC, 30, Color.LIME, 0.0f, 0.0f);
		font.init();
		font.text = "ИП - самый бесполезный предмет.";
		figure = new Texture(new float[]{
				1.0f,  1.0f, 0.0f,
				1.0f, -1.0f, 0.0f,
				-1.0f, -1.0f, 0.0f,
				-1.0f,  1.0f, 0.0f
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
		figure.init("core/assets/tex1.png");
		figure1 = new Texture(new float[]{
				0.5f,  0.5f, 0.0f,
				0.5f, -0.5f, 0.0f,
				-0.5f, -0.5f, 0.0f,
				-0.5f,  0.5f, 0.0f
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
		figure1.init("core/assets/tex.png");
		figure2 = new Figure(new float[]{
				0.25f,  0.25f, 0.0f,
				0.25f, -0.25f, 0.0f,
				-0.25f, -0.25f, 0.0f,
				-0.25f,  0.25f, 0.0f
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
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		figure.draw();
		figure1.draw();
		figure2.draw();
		font.draw();
		if (Gdx.input.isKeyPressed(ESCAPE)){
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void dispose () {

	}
}
