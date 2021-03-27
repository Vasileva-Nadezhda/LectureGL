package com.project;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class Workspace implements Drawable{

    ArrayList<SimpleText> strings;
    ArrayList<Picture> pictures;
    int minX, minY;
    int width, height;
    int nowY;
    int deltaY = 0;
    int line_spacing = 5;

    public Workspace(int minX, int minY){
        this.minX = minX;
        this.minY = minY;
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth() - minX;
    }

    public void addItem(Object item){
        if(item instanceof SimpleText){
            if(this.strings==null){
                this.strings = new ArrayList<>();
            }
            this.strings.add((SimpleText)item);
        }
        else if (item instanceof Picture) {
            if (this.pictures == null) {
                this.pictures = new ArrayList<>();
            }
            this.pictures.add((Picture)item);
        }
    }

    public void init(){
        Gdx.input.setInputProcessor(new InputAdapter());
    }

    public void draw(){
        for(SimpleText string : this.strings){
            string.draw();
        }
    }

    public void resize(){
        this.deltaY = Drawable.resizeY(this.deltaY);
        this.strings.get(0).y = Drawable.resizeY(this.strings.get(0).y);
        for(int i=1; i<this.strings.size(); ++i) {
            this.strings.get(i).y = Drawable.resizeY(this.strings.get(i).y);
            this.strings.get(i).y -= (this.strings.get(i-1).y - this.strings.get(i).y)*Window.oldHeight/Gdx.graphics.getHeight();
        }
    }

    public void dispose(){
        this.strings.clear();
        this.pictures.clear();
    }

}
