package com.project;

public class SimpleText{

    int x;
    int y;
    Font font;
    String text;

    public SimpleText(int x, int y, Font font, String text){
        this.x = x;
        this.y = y;
        this.font = font;
        this.text = text;
    }

    public void draw(){
        this.font.draw(this.x, this.y, this.text);
    }

    public void draw(int y){
        font.draw(this.x, y, this.text);
    }

}
