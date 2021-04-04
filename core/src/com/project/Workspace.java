package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.util.ArrayList;

public class Workspace implements Drawable{

    ArrayList<SimpleText> strings;
    ArrayList<Picture> pictures;
    String contentLocation;
    WorkspaceLoader loader;
    GlyphLayout layout;
    int minX, minY;
    int width, height;
    int deltaY = 0;

    public Workspace(int minX, int minY, String contentLocation) {
        this.minX = minX;
        this.minY = minY;
        this.height = Gdx.graphics.getHeight();
        this.width = Gdx.graphics.getWidth() - minX;
        this.contentLocation = contentLocation;
    }

    public void addItem(Object item) {
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

    public void init() {
        Gdx.input.setInputProcessor(new InputAdapter(this));
        loader = new WorkspaceLoader();
        loader.contentLoad(this.contentLocation, 200, Gdx.graphics.getHeight(), this);
        this.layout = new GlyphLayout();
        for (Picture pic : this.pictures) {
            pic.init();
        }
    }

    public void draw() {
        for(SimpleText string : this.strings){
           this.layout.reset();
           this.layout.setText(string.font.font, string.text);
           if (((string.y + this.deltaY - this.layout.height) < Gdx.graphics.getHeight())
                && (string.y + this.deltaY) > 0) {
                string.draw(string.y+this.deltaY);
           }
        }
        for (Picture pic : this.pictures) {
            if (pic.maxY-pic.height+this.deltaY<Gdx.graphics.getHeight() && pic.maxY+this.deltaY>0){
                pic.draw();
            }
        }
    }

    public void resize() {
        this.strings.clear();
        for (Picture pic : this.pictures) {
            pic.resizeParameters();
        }
        this.loader.contentLoad(this.contentLocation, 200, Gdx.graphics.getHeight(), this);
        for (Picture pic : this.pictures) {
            pic.resize(this.deltaY);
        }
    }

    public void scrollPicture() {
        for (Picture pic : this.pictures) {
            pic.resize(this.deltaY);
        }
    }

    public void dispose() {
        for (Picture pic : this.pictures) {
            pic.dispose();
        }
        this.strings.clear();
        this.pictures.clear();
    }

}
