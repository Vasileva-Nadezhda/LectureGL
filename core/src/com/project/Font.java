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

    String location;
    int size;
    Color color;
    BitmapFont font;
    SpriteBatch batch;

    public Font(String location, int size, Color color) {
        this.location = location;
        this.size = size;
        this.color = color;
    }

    public Font(int size, Color color) {
        this(CALIBRI, size, color);
    }

    public Font(String location, int size) {
        this(location, size, Color.WHITE);
    }

    public Font() {
        this(CALIBRI, 14, Color.WHITE);
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
    }

    public void draw(float x, float y, String text) {
        this.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch.begin();
        this.font.draw(this.batch, text, setX(x), setY(y) + this.size/2);
        this.batch.end();
    }

    public void dispose() {
        this.batch.dispose();
        this.font.dispose();

    }

}
