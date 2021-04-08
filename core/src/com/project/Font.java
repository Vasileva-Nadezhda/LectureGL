package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Matrix4;

public class Font implements Drawable {

    final static String BOLD = "core/assets/Bold.ttf";
    final static String REGULAR = "core/assets/Regular.ttf";
    final static String CHARACTERS = "1234567890!@\"#№$;%^:&?*()-∈_=+\\|/'.><,{[]}`~qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMёйцукенгшщзхъфывапролджэячсмитьбюЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";
    static Matrix4 matrix;
    String location;
    int size;
    Color color;
    BitmapFont font;
    SpriteBatch batch;
    GlyphLayout layout;

    public Font(String location, int size, Color color) {
        this.location = location;
        this.size = size;
        this.color = color;
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
        this.layout = new GlyphLayout(this.font, "W");
        matrix = new Matrix4();
    }

    public void draw(int x, int y, String text) {
        this.batch.setProjectionMatrix(matrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch.begin();
        this.font.draw(this.batch, text, x, y);
        this.batch.end();
    }

    public void dispose() {
        this.batch.dispose();
        this.font.dispose();

    }

}
