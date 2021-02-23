package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Interface implements Drawable{

    static Button button1;
    static Button button2;
    static Button button3;
    static Button button4;
    static Button button5;
    static Font buttonFont = new Font(Font.REGULAR, 10, Color.WHITE);

    public void init(){
        button1.init();
        button2.init();
        button3.init();
        button4.init();
        button5.init();
        buttonFont.init();
    }

    @Override
    public void draw(){
        button1.draw();
        button2.draw();
        button3.draw();
        button4.draw();
        button5.draw();
    }

    public void dispose(){
        buttonFont.dispose();
        button1.dispose();
        button2.dispose();
        button3.dispose();
        button4.dispose();
        button5.dispose();
    }

    private void moveVertices(float[] vertices, int maxY, int buttonHeight) {
        for(int j=1; j<=10; j+=9){
            vertices[j]=Drawable.setIntY(maxY);
        }
        for(int j=4; j<=7; j+=3){
            vertices[j]=Drawable.setIntY(maxY-buttonHeight);
        }
    }

    public void resize(){
        int buttonWidth = 100;
        int buttonHeight = 50;
        int nameY = 25;
        int maxY = Gdx.graphics.getHeight();
        float[] vertices = {
                Drawable.setIntX(buttonWidth),  Drawable.setIntY(maxY),                                  0.0f,
                Drawable.setIntX(buttonWidth),  Drawable.setIntY(maxY-buttonHeight),                     0.0f,
                -1.0f,                          Drawable.setIntY(maxY-buttonHeight),                     0.0f,
                -1.0f,                          Drawable.setIntY(maxY),                                  0.0f
        };
        button1.body.vertices = vertices;
        button1.body.initVertices();
        button1.textY = maxY-nameY;
        button1.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button2.body.vertices = vertices;
        button2.body.initVertices();
        button2.textY = maxY-nameY;
        button2.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button3.body.vertices = vertices;
        button3.body.initVertices();
        button3.textY = maxY-nameY;
        button3.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button4.body.vertices = vertices;
        button4.body.initVertices();
        button4.textY = maxY-nameY;
        button4.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button5.body.vertices = vertices;
        button5.body.initVertices();
        button5.textY = maxY-nameY;
        button5.initPoses();
    }

    public Interface(){
        int buttonWidth = 100;
        int buttonHeight = 50;
        int nameX = 28;
        int nameY = 25;
        int maxY = Gdx.graphics.getHeight();
        float[] vertices = {
                Drawable.setIntX(buttonWidth),  Drawable.setIntY(maxY),                                  0.0f,
                Drawable.setIntX(buttonWidth),  Drawable.setIntY(maxY-buttonHeight),                     0.0f,
                -1.0f,                          Drawable.setIntY(maxY-buttonHeight),                     0.0f,
                -1.0f,                          Drawable.setIntY(maxY),                                  0.0f
        };
        float[] colors = {
                InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f,
                InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f,
                InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f,
                InterfaceColors.INTERFACE_BUTTON.r, InterfaceColors.INTERFACE_BUTTON.g, InterfaceColors.INTERFACE_BUTTON.b, 1.0f
        };
        int[] indices = {
                0, 1, 2,
                0, 3, 2
        };
        button1 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "button1", nameX, maxY-nameY,
                             ()-> Window.text = button1.name);
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button2 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "button2", nameX, maxY-nameY,
                ()-> Window.text = button2.name);
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button3 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "button3", nameX, maxY-nameY,
                ()-> Window.text = button3.name);
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button4 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "button4", nameX, maxY-nameY,
                ()-> Window.text = button4.name);
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        button5 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "button5", nameX, maxY-nameY,
                ()-> Window.text = button5.name);
    }

}
