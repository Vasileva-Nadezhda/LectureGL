package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter {

	@Override
	public void create () {
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		Texture figure = new Texture(new float[]{
				// координаты        // цвета            // текстурные координаты
				1.0f,  1.0f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f,    1, 0,   // верхняя правая
				1.0f, -1.0f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f,    1, 1,   // нижняя правая
				-1.0f, -1.0f, 0.0f,  0.0f, 0.0f, 1.0f, 1.0f,    0, 1,   // нижняя левая
				-1.0f,  1.0f, 0.0f,  1.0f, 1.0f, 0.0f, 1.0f,    0, 0   // верхняя левая
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				},
				"core/assets/tex1.png");
		figure.initBuffers();
		Texture figure1 = new Texture(new float[]{
				// координаты        // цвета            // текстурные координаты
				0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f, 1, 0,    // верхняя правая
				0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f, 1, 1,    // нижняя правая
				-0.5f, -0.5f, 0.0f,  0.0f, 0.0f, 1.0f, 1.0f, 0, 1,   // нижняя левая
				-0.5f,  0.5f, 0.0f,  1.0f, 1.0f, 0.0f, 1.0f,  0, 0// верхняя левая
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				}, "core/assets/tex.png");
		figure1.initBuffers();
		Figure figure2 = new Figure(new float[]{
				// координаты        // цвета            // текстурные координаты
				0.25f,  0.25f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f,      // верхняя правая
				0.25f, -0.25f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f,       // нижняя правая
				-0.25f, -0.25f, 0.0f,  0.0f, 0.0f, 1.0f, 1.0f,       // нижняя левая
				-0.25f,  0.25f, 0.0f,  1.0f, 1.0f, 0.0f, 1.0f   // верхняя левая
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure2.initBuffers();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(ESCAPE)){
			dispose();
			System.exit(0);
		}
	}

	@Override
	public void dispose () {

	}
}
