package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;

public class Font implements Drawable {

    final static String CALIBRI = "core/assets/Calibri.ttf";
    final static String CALIBRI_LIGHT = "core/assets/Calibri-Light.ttf";
    final static String CALIBRI_LIGHTITALIC = "core/assets/Calibri-LightItalic.ttf";
    final static String CALIBRI_ITALIC = "core/assets/Calibri-Italic.ttf";
    final static String CHARACTERS = "1234567890!@\"#№$;%^:&?*()-_=+\\|/'.><,{[]}`~qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMёйцукенгшщзхъфывапролджэячсмитьбюЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";

    int x;
    int y;
    float x0;
    float y0;
    String text = "Hello World";
    String location;
    int size;
    Color color;
    BitmapFont font;
    SpriteBatch batch;

    public Font(String location, int size, Color color, float x, float y) {
        this.location = location;
        this.size = size;
        this.color = color;
        this.x0 = x;
        this.y0 =y;
    }

    public Font(int size, Color color, float x, float y) {
        this(CALIBRI, size, color, x, y);
    }

    public Font(String location, int size, float x, float y) {
        this(location, size, Color.WHITE, x, y);
    }

    public Font(float x, float y) {
        this(CALIBRI, 14, Color.WHITE, x, y);
    }

    public void init() {
        this.batch = new SpriteBatch();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(new FileHandle(this.location));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = CHARACTERS;
        parameter.size = this.size;
        parameter.color = this.color;
        this.font = generator.generateFont(parameter);
        generator.dispose();
        setCoordinates();
    }

    public void draw() {
        this.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch.begin();
        this.font.draw(this.batch, this.text, this.x, this.y);
        this.batch.end();
    }

    public void setCoordinates() {
        if (x0 == -1.0f) {
            this.x = 0;
        }
        else if (x0 == 0.0f) {
            this.x = Gdx.graphics.getWidth() / 2;
        }
        else if (x0 == 1.0f) {
            this.x = Gdx.graphics.getWidth();
        }
        else {
            this.x = (this.x0>=0) ? (int)(x0 * (Gdx.graphics.getWidth()/2) + Gdx.graphics.getWidth()/2) : (int)(-x0 * (Gdx.graphics.getWidth()/2));
        }

        if (y0 == -1.0f) {
            this.y = 0;
        }
        else if (y0 == 0.0f) {
            this.y = Gdx.graphics.getHeight() / 2;
        }
        else if (y0 == 1.0f) {
            this.y = Gdx.graphics.getHeight();
        }
        else {
            this.y = (this.y0>=0) ? (int)(y0 * (Gdx.graphics.getHeight()/2) + Gdx.graphics.getHeight()/2) : (int)(-y0 * (Gdx.graphics.getHeight()/2));
        }
    }

    public void dispose() {
        this.batch.dispose();
        this.font.dispose();
    }

}
