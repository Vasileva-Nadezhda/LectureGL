package com.project;

import com.badlogic.gdx.Gdx;

import java.awt.*;

public interface Drawable {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    void init();

    default void draw() {

    }

    void dispose();

    static float setIntX(int x) {
        return (float)((x*screenSize.getWidth()/Gdx.graphics.getWidth())/((screenSize.getWidth() / 2)))-1;
    }

    static float setIntY(int y) {
        return (float)((y*screenSize.getHeight()/Gdx.graphics.getHeight())/(screenSize.getHeight() / 2))-1;
    }

    static int setFloatX(float x) {
        return (int)(((x+1)*(screenSize.getWidth() / 2))*Gdx.graphics.getWidth()/screenSize.getWidth());
    }

    static int setFloatY(float y) {
        return (int)(((y+1)*(screenSize.getHeight() / 2))*Gdx.graphics.getHeight()/screenSize.getHeight());
    }

    static int resizeY(int y){
        return y*Gdx.graphics.getHeight()/Window.oldHeight;
    }
    static int resizeX(int x){
        return x*Gdx.graphics.getWidth()/Window.oldWidth;
    }
}
