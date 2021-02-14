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
    final static String ITALIC = "core/assets/Italic.ttf";
    final static String BOLD_ITALIC = "core/assets/Bold-Italic.ttf";
    final static String CHARACTERS = "1234567890!@\"#№$;%^:&?*()-_=+\\|/'.><,{[]}`~qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMёйцукенгшщзхъфывапролджэячсмитьбюЁЙЦУКЕНГШЩЗХЪФЫВАПРОЛДЖЭЯЧСМИТЬБЮ";

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

    public Font(int size, Color color) {
        this(REGULAR, size, color);
    }

    public Font(String location, int size) {
        this(location, size, Color.WHITE);
    }

    public Font() {
        this(REGULAR, 14, Color.WHITE);
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
        this.layout = new GlyphLayout(this.font, "");
    }

    public void draw(int x, int y, String text) {
        this.batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        this.batch.begin();
        this.font.draw(this.batch, text, x, y + this.size/2);
        this.batch.end();
    }

    public void drawWithWrap(int x, int y, String text) {
        this.layout.setText(this.font, text);
        if(text.length()>2) {
            if(x + layout.width>Gdx.graphics.getWidth()) {
                for (int i = (int)((Gdx.graphics.getWidth()-x)/(layout.width/text.length())); i>0; i--) {
                    if (text.charAt(i)==' ') {
                        this.draw(x, y, text.substring(0,i));
                        text = text.substring(i+1);
                        this.drawWithWrap(x, y-5-this.size, text);
                        break;
                    }
                }
            } else this.draw(x, y, text);
        }
    }

    public void dispose() {
        this.batch.dispose();
        this.font.dispose();

    }

}
