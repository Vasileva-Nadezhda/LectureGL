package com.project;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import static com.badlogic.gdx.Input.Keys.ESCAPE;

public class Window extends ApplicationAdapter{

	Texture figure;
	Texture figure1;
	Figure figure2;
	static BitmapFont font;
	SpriteBatch batch;
	int x = 800, y = 450;
	float sc =.2f;

	@Override
	public void create () {
		Texture.TextureShader.loadFromFile();
		Figure.FigureShader.loadFromFile();
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.BLACK);
		batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		font.getRegion().getTexture().setFilter(com.badlogic.gdx.graphics.Texture.TextureFilter.Linear, com.badlogic.gdx.graphics.Texture.TextureFilter.Linear);
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
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		figure.draw();
		figure1.draw();
		figure2.draw();
		batch.begin();
		font.getData().setScale(sc);
		font.draw(batch, "AXAXAXAXAXAX", x, y);
		batch.end();
		if (Gdx.input.isKeyPressed(ESCAPE)){
			dispose();
			System.exit(0);
		}
		x -= 3;
		y -= 1;
		sc +=0.01f;
	}

	@Override
	public void dispose () {

	}
}
