package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

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
		font = new Font(-1.0f, 0.0f);
		font.init();
		font.text = "ИП - самый бесполезный предмет.";
		figure = new Texture(new float[]{
				// координаты        // цвета            // текстурные координаты
				1.0f,  1.0f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f,    1, 0,   // верхняя правая
				1.0f, -1.0f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f,    1, 1,   // нижняя правая
				-1.0f, -1.0f, 0.0f,  0.0f, 0.0f, 1.0f, 1.0f,    0, 1,   // нижняя левая
				-1.0f,  1.0f, 0.0f,  1.0f, 1.0f, 0.0f, 1.0f,    0, 0   // верхняя левая
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure.init("core/assets/tex1.png");
		figure1 = new Texture(new float[]{
				// координаты        // цвета            // текстурные координаты
				0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f, 1.0f, 1, 0,    // верхняя правая
				0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f, 1.0f, 1, 1,    // нижняя правая
				-0.5f, -0.5f, 0.0f,  0.0f, 0.0f, 1.0f, 1.0f, 0, 1,   // нижняя левая
				-0.5f,  0.5f, 0.0f,  1.0f, 1.0f, 0.0f, 1.0f,  0, 0// верхняя левая
		},
				new int[] {
						0, 1, 2,
						0, 3, 2
				});
		figure1.init("core/assets/tex.png");
		figure2 = new Figure(new float[]{
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
		figure2.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
	public void resize(int width, int height){
		super.resize(width, height);
		font.setCoordinates(font.x0, font.y0);
	}
	@Override
	public void dispose () {

	}
}
