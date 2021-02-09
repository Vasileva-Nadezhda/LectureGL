package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Button implements Drawable{
    float maxX = -10.0f; float maxY = -10.0f;
    float minX=10.0f; float minY = 10.0f;
    float textX, textY;
    Figure body;
    Texture icon;
    Font font;
    String name;
    Runnable action;

    public Button(Figure body, Texture icon, Font font, String name, float textX, float textY, Runnable action){
        this.body = body;
        if (icon!=null)
        this.icon = icon;
        this.font = font;
        this.name = name;
        this.textX = textX;
        this.textY = textY;
        this.action = action;
    }

    public void init(){
        for(int i=0; i<this.body.vertices.length; i+=3){
            if(this.maxX<this.body.vertices[i]) this.maxX = this.body.vertices[i];
            if(this.minX>this.body.vertices[i]) this.minX = this.body.vertices[i];
        }
        for(int i=1; i<this.body.vertices.length; i+=3){
            if(this.maxY<this.body.vertices[i]) this.maxY = this.body.vertices[i];
            if(this.minY>this.body.vertices[i]) this.minY = this.body.vertices[i];
        }
    }

    public void draw(){
        this.body.draw();
        if (icon!=null)
        this.icon.draw();
        this.font.draw(this.textX, this.textY, this.name);
        processInput();
    }

    public void processInput() {
        if ((Gdx.input.getX())>setX(this.minX)
                && (Gdx.input.getX())<setX(this.maxX)
                && (Gdx.input.getY())<Gdx.graphics.getHeight() - setY(this.minY)
                && (Gdx.input.getY())>Gdx.graphics.getHeight() - setY(this.maxY)){
            for(int i=4; i<this.body.colors.length; i+=4)
            this.body.colors[i] = 0.0f;
            this.body.initColors();
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                this.action.run();
            }
        } else  {for(int i=4; i<this.body.colors.length; i+=4)
            this.body.colors[i] = 1.0f;
        this.body.initColors();}
    }

    public void dispose(){
        this.body.dispose();
        this.icon.dispose();
        this.font.dispose();
    }

}
