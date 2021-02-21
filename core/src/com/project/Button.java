package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Button implements Drawable{
    float maxX, maxY;
    float minX, minY;
    int textX, textY;
    boolean wasSelected = false;
    Figure body;
    Texture icon;
    Font font;
    String name;
    Runnable action;

    public Button(Figure body, Texture icon, Font font, String name, int textX, int textY, Runnable action){
        this.body = body;
        if (icon!=null)
        this.icon = icon;
        this.font = font;
        this.name = name;
        this.textX = textX;
        this.textY = textY;
        this.action = action;
    }

    public void initPoses(){
        this.maxX = -10.0f;
        this.maxY = -10.0f;
        this.minX = 10.0f;
        this.minY = 10.0f;
        for(int i=0; i<this.body.vertices.length; i+=3){
            if(this.maxX<this.body.vertices[i]) this.maxX = this.body.vertices[i];
            if(this.minX>this.body.vertices[i]) this.minX = this.body.vertices[i];
        }
        for(int i=1; i<this.body.vertices.length; i+=3){
            if(this.maxY<this.body.vertices[i]) this.maxY = this.body.vertices[i];
            if(this.minY>this.body.vertices[i]) this.minY = this.body.vertices[i];
        }
    }
    public void init(){
        this.initPoses();
        this.body.init();
        if(this.icon != null){
            this.icon.init(this.icon.location);
        }
    }

    @Override
    public void draw(){
        this.body.draw();
        if (this.icon!=null)
        this.icon.draw();
        this.font.draw(this.textX, this.textY, this.name);
        processInput();
    }

    public void processInput() {
        if ((Gdx.input.getX())> Drawable.setFloatX(this.minX)
                && (Gdx.input.getX())<Drawable.setFloatX(this.maxX)
                && (Gdx.input.getY())<Gdx.graphics.getHeight() - Drawable.setFloatY(this.minY)
                && (Gdx.input.getY())>Gdx.graphics.getHeight() - Drawable.setFloatY(this.maxY)){
            if (!this.wasSelected) {
                this.body.colors = new float[]{
                        InterfaceColors.SELECTED_INTERFACE_BUTTON.r, InterfaceColors.SELECTED_INTERFACE_BUTTON.g, InterfaceColors.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                        InterfaceColors.SELECTED_INTERFACE_BUTTON.r, InterfaceColors.SELECTED_INTERFACE_BUTTON.g, InterfaceColors.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                        InterfaceColors.SELECTED_INTERFACE_BUTTON.r, InterfaceColors.SELECTED_INTERFACE_BUTTON.g, InterfaceColors.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                        InterfaceColors.SELECTED_INTERFACE_BUTTON.r, InterfaceColors.SELECTED_INTERFACE_BUTTON.g, InterfaceColors.SELECTED_INTERFACE_BUTTON.b, 1.0f
                };
                this.body.initColors();
                this.wasSelected = true;
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                this.action.run();
            }
        }
        else if(this.wasSelected)
        {
            this.body.colors = new float[]{
                    InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f,
                    InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f,
                    InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f,
                    InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f
            };
            this.body.initColors();
            this.wasSelected=false;
        }
    }

    public void dispose(){
        this.body.dispose();
        this.icon.dispose();
        this.font.dispose();
    }

}
