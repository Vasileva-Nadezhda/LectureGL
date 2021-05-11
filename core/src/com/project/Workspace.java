package com.project;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Workspace implements Drawable{

    static InputAdapter input_adapter;
    ArrayList<SimpleText> strings;
    ArrayList<Picture> pictures;
    String contentLocation;
    WorkspaceLoader loader;
    int deltaY = 0;

    public Workspace (String contentLocation) {
        this.contentLocation = contentLocation;
        loader = new WorkspaceLoader();
        this.strings = new ArrayList<>();
        this.pictures = new ArrayList<>();
    }

    public void setInputAdapter() {
        input_adapter.changeWorkspace(this);
        Gdx.input.setInputProcessor(input_adapter);
    }

    public void addItem (Object item) {
        if (item instanceof SimpleText){
            this.strings.add((SimpleText) item);
        }
        else if (item instanceof Picture) {
            this.pictures.add((Picture) item);
        }
    }

    public void init() {
        if ((this.strings != null && !this.strings.isEmpty()) ||
           (this.pictures != null && !this.pictures.isEmpty())) {
            this.dispose();
        }
        loader.contentLoad(this.contentLocation, 115,
                     Gdx.graphics.getHeight() - InterfaceParameters.upper_panel_height - 15, this);
        for (Picture pic : this.pictures) {
            pic.init();
        }
        this.deltaY = 0;
    }

    public void draw() {
        for (SimpleText string : this.strings) {
           if (((string.y + this.deltaY - string.height) < Gdx.graphics.getHeight())
              && (string.y + this.deltaY) > 0) {
                if (string instanceof Link) {
                    string.draw();
                }
                else {
                    string.draw(string.y + this.deltaY);
                }
           }
        }
        for (Picture pic : this.pictures) {
            if ((pic.maxY - pic.height + this.deltaY < Gdx.graphics.getHeight()) && (pic.maxY + this.deltaY > 0)) {
                pic.draw();
            }
        }
    }

    public void resize() {
        for (SimpleText string : this.strings) {
            if (string instanceof Link) {
                ((Link) string).dispose();
            }
        }
        this.strings.clear();
        for (Picture pic : this.pictures) {
            pic.resizeParameters();
        }
        this.loader.contentLoad(this.contentLocation, 115,
                          Gdx.graphics.getHeight() - InterfaceParameters.upper_panel_height - 15, this);
        for (Picture pic : this.pictures) {
            pic.change(this.deltaY);
        }
    }

    public void scroll() {
        for (Picture pic : this.pictures) {
            pic.change(this.deltaY);
        }
    }

    public void dispose() {
        for (Picture pic : this.pictures) {
            pic.dispose();
        }
        for (SimpleText string : this.strings) {
            if (string instanceof Link) {
                ((Link) string).dispose();
            }
        }
        this.strings.clear();
        this.pictures.clear();
        System.gc();
    }

}