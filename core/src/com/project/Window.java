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
	String text="Должно разделяться примерно вот тут.";
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
		font = new Font(Font.ITALIC, 24, Color.LIME);
		font.init();
		figure = new Figure(new float[]{
				Drawable.setIntX(200), Drawable.setIntY(Gdx.graphics.getHeight()), 0.0f,
				Drawable.setIntX(200),  Drawable.setIntY(Gdx.graphics.getHeight()-50), 0.0f,
				Drawable.setIntX(0),  Drawable.setIntY(Gdx.graphics.getHeight()-50), 0.0f,
				Drawable.setIntX(0),  Drawable.setIntY(Gdx.graphics.getHeight()), 0.0f
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
		this.text = "Здесь еще вроде пишет. sssvsdf sdgsdgsdg sdgsdgsgewg wegwgwgweg wgewg.";
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
		 button1 = new Button(figure, texture, buttonFont, "button1", 80, 460, ()->this.text = "Здесь еще вроде пишет. sssvsdf sdgsdgsdg sdgsdgsgewg wegwgwgweg wgewg.");
		 button1.init();
		button2 = new Button(figure1, null, buttonFont, "button2", 80, 410, ()->this.text = button2.name);
		button2.init();
		button3 = new Button(figure2, null, buttonFont, "button3", 80, 360, ()->this.text = button3.name);
		button3.init();
		button4 = new Button(figure3, null, buttonFont, "button4", 80, 310, ()->this.text = button4.name);
		button4.init();
		button5 = new Button(figure4, null, buttonFont, "button5", 80, 260, ()->this.text = button5.name);
		button5.init();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		button1.draw();
		button2.draw();
		button3.draw();
		button4.draw();
		button5.draw();
		font.drawWithWrap(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, this.text);
		if (Gdx.input.isKeyPressed(ESCAPE)){
			dispose();
			System.exit(0);
		}
	}

	@Override
    public void resize(int width, int height) {
		System.out.println(Gdx.graphics.getWidth());
		this.button1.body.vertices = new float[]{
				Drawable.setIntX(200), Drawable.setIntY(Gdx.graphics.getHeight()), 0.0f,
				Drawable.setIntX(200),  Drawable.setIntY(Gdx.graphics.getHeight()-50), 0.0f,
				Drawable.setIntX(0),  Drawable.setIntY(Gdx.graphics.getHeight()-50), 0.0f,
				Drawable.setIntX(0),  Drawable.setIntY(Gdx.graphics.getHeight()), 0.0f
		};
		this.button1.body.initVertices();
		this.button1.init();
		System.out.println(this.button1.maxX);
		System.out.println(Drawable.setFloatX(this.button1.maxX));
		super.resize(width, height);
	}

	@Override
	public void dispose () {

	}
}
