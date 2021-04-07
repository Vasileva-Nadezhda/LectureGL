package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

public class Interface implements Drawable{

    Figure panel;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    String name_of_last_button = "";
    Font buttonFont = new Font(Font.REGULAR, 10, Color.WHITE);

    public void init(){
        this.button1.init();
        this.button2.init();
        this.button3.init();
        this.button4.init();
        this.button5.init();
        this.buttonFont.init();
        this.panel.init();
    }

    @Override
    public void draw(){
        this.button1.draw();
        this.button2.draw();
        this.button3.draw();
        this.button4.draw();
        this.button5.draw();
        this.panel.draw();
    }

    public void dispose(){
        this.buttonFont.dispose();
        this.button1.dispose();
        this.button2.dispose();
        this.button3.dispose();
        this.button4.dispose();
        this.button5.dispose();
        this.panel.dispose();
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
        this.button1.body.vertices = vertices;
        this.button1.body.initVertices();
        this.button1.name.y = maxY-nameY;
        this.button1.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button2.body.vertices = vertices;
        this.button2.body.initVertices();
        this.button2.name.y = maxY-nameY;
        this.button2.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button3.body.vertices = vertices;
        this.button3.body.initVertices();
        this.button3.name.y = maxY-nameY;
        this.button3.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button4.body.vertices = vertices;
        this.button4.body.initVertices();
        this.button4.name.y = maxY-nameY;
        this.button4.initPoses();
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button5.body.vertices = vertices;
        this.button5.body.initVertices();
        this.button5.name.y = maxY-nameY;
        this.button5.initPoses();
        vertices = new float[]{
                Drawable.setIntX(buttonWidth), Drawable.setIntY(maxY-buttonHeight), 0.0f,
                Drawable.setIntX(buttonWidth), -1.0f, 0.0f,
                -1.0f, -1.0f, 0.0f,
                -1.0f, Drawable.setIntY(maxY-buttonHeight), 0.0f
        };
        this.panel.vertices = vertices;
        this.panel.initVertices();
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
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f
        };
        int[] indices = {
                0, 1, 2,
                0, 3, 2
        };
        this.button1 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "maths1", nameX, maxY-nameY,
                             ()-> { Window.workspace.dispose();
                                 Window.workspace.contentLocation="core/assets/maths1.theory";
                                 Window.workspace.init();});
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button2 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "maths2", nameX, maxY-nameY,
                ()-> { Window.workspace.dispose();
                    Window.workspace.contentLocation="core/assets/text0.theory";
                    Window.workspace.init();});
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button3 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "Physics", nameX, maxY-nameY,
                ()-> Window.workspace.strings.get(0).text = this.button3.name.text);
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button4 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "Chemistry", nameX, maxY-nameY,
                ()-> Window.workspace.strings.get(0).text = this.button4.name.text);
        maxY -= buttonHeight;
        moveVertices(vertices, maxY, buttonHeight);
        this.button5 = new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()), null, buttonFont, "Gallery", nameX, maxY-nameY,
                ()-> Window.workspace.strings.get(0).text = this.button5.name.text);
        vertices = new float[]{
                Drawable.setIntX(buttonWidth), Drawable.setIntY(maxY-buttonHeight), 0.0f,
                Drawable.setIntX(buttonWidth), -1.0f, 0.0f,
                -1.0f, -1.0f, 0.0f,
                -1.0f, Drawable.setIntY(maxY-buttonHeight), 0.0f
        };
        this.panel = new Figure(vertices.clone(), colors.clone(), indices);
    }

}
