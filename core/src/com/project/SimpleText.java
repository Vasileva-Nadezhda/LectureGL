package com.project;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class SimpleText {

    int x;
    int y;
    int width;
    int height;
    Font font;
    String text;

    public SimpleText (int x, int y, Font font, String text) {
        this.x = x;
        this.y = y;
        this.font = font;
        this.text = text;
        GlyphLayout layout = new GlyphLayout(font.font, text);
        this.width = (int) layout.width;
        this.height = (int) layout.height;
    }

    public void draw() {
        this.font.draw(this.x, this.y, this.text);
    }

    public void draw(int y) {
        font.draw(this.x, y, this.text);
    }

}