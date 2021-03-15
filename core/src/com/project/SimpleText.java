package com.project;

public class SimpleText{

    int x;
    int y;
    String text;

    enum FontTypes {
        MAIN,
        HEADER
    }

    public SimpleText(int x, int y, String text){
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void draw(Font font){
        font.draw(this.x, this.y, this.text);
    }

    public void draw(Font font, int y){
        font.draw(this.x, this.y, this.text);
    }

    public void drawInWorkspace(Font font){
        font.drawInWorkspace(this.x, this.text);
    }
}
