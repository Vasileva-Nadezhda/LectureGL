package com.project;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class Workspace implements Drawable{

    ArrayList<Drawable> items = new ArrayList<>();
    int minX, minY;
    int width, height;
    int line_spacing = 15;
    static int deltaY = 0;

    public void init(){
        Gdx.input.setInputProcessor(new InputAdapter(){

        });
    }

    public void draw(){

    }

    public void dispose(){

    }

}
