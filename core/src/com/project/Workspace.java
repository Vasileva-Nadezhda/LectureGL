package com.project;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class Workspace implements Drawable{

    ArrayList<SimpleText> strings;
    ArrayList<Picture> pictures;
    int minX, minY;
    int width, height;
    int maxItemY;
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
        this.maxItemY = this.strings.get(0).y;
    }

    public void draw(){
        this.nowY = this.maxItemY;
        for (int i=0; i<this.strings.size()-1; ++i){
            if (this.nowY+this.deltaY-InterfaceParameters.MAIN_FONT.size>-10 && this.nowY+this.deltaY<Gdx.graphics.getHeight()+10) {
                this.strings.get(i).drawInWorkspace(InterfaceParameters.MAIN_FONT);
            }
            this.nowY-= this.strings.get(i).y - this.strings.get(i+1).y;
        }
    }

    public void resize(){
        this.deltaY = Drawable.resizeY(this.deltaY);
        this.maxItemY = Drawable.resizeY(this.maxItemY);
    }

    public void dispose(){
        this.strings.clear();
        this.pictures.clear();
    }

}
