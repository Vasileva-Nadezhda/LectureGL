package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Button implements Drawable {

    float maxX, maxY;
    float minX, minY;
    boolean wasSelected = false;
    Figure body;
    Texture icon;
    Font font;
    SimpleText name;
    Runnable action;

    public Button(Figure body, Texture icon, Font font, String name, int textX, int textY, Runnable action){
        this.body = body;
        if (icon != null) {
            this.icon = icon;
        }
        this.font = font;
        this.name = new SimpleText(textX, textY, this.font, name);
        this.action = action;
    }

    public void initPoses() {
        this.maxX = -10.0f;
        this.maxY = -10.0f;
        this.minX = 10.0f;
        this.minY = 10.0f;
        for (int i = 0; i < this.body.vertices.length; i += 3) {
            if(this.maxX < this.body.vertices[i]) this.maxX = this.body.vertices[i];
            if(this.minX > this.body.vertices[i]) this.minX = this.body.vertices[i];
        }
        for (int i = 1; i < this.body.vertices.length; i += 3) {
            if(this.maxY < this.body.vertices[i]) this.maxY = this.body.vertices[i];
            if(this.minY > this.body.vertices[i]) this.minY = this.body.vertices[i];
        }
    }

    public void init() {
        this.initPoses();
        this.body.init();
        if (this.icon != null) {
            this.icon.init(this.icon.location);
        }
    }

    @Override
    public void draw(){
        this.body.draw();
        if (this.icon != null) {
            this.icon.draw();
        }
        this.name.draw();
        processInput();
    }

    public void processInput() {
        if ((Gdx.input.getX()) > Drawable.setFloatX(this.minX)
             && (Gdx.input.getX()) < Drawable.setFloatX(this.maxX)
             && (Gdx.input.getY()) < Gdx.graphics.getHeight() - Drawable.setFloatY(this.minY)
             && (Gdx.input.getY()) > Gdx.graphics.getHeight() - Drawable.setFloatY(this.maxY)) {
            if (!this.wasSelected) {
                this.body.colors = new float[] {
                        InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                        InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                        InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                        InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f
                };
                this.body.initColors();
                this.wasSelected = true;
            }
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)
                && !Window.an_interface.name_of_last_button.equals(this.name.text)) {
                this.action.run();
                Window.an_interface.name_of_last_button = this.name.text;
            }
        }
        else if (!this.wasSelected && Window.an_interface.name_of_last_button.equals(this.name.text)) {
            this.body.colors = new float[] {
                    InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                    InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                    InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f,
                    InterfaceParameters.SELECTED_INTERFACE_BUTTON.r, InterfaceParameters.SELECTED_INTERFACE_BUTTON.g, InterfaceParameters.SELECTED_INTERFACE_BUTTON.b, 1.0f
            };
            this.body.initColors();
            this.wasSelected = true;
        }
        else if (this.wasSelected && !Window.an_interface.name_of_last_button.equals(this.name.text)) {
            this.body.colors = new float[] {
                    InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                    InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                    InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                    InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f
            };
            this.body.initColors();
            this.wasSelected = false;
        }
    }

    public void dispose() {
        this.body.dispose();
        if (this.icon != null) {
            this.icon.dispose();
        }
    }

}
