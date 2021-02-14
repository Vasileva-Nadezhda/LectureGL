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
        return (float)((((double)x)/((screenSize.getWidth() / 2)))-1);
    }

    static float setIntY(int y) {
        return ((float)(y)/(float)(Gdx.graphics.getHeight() / 2))-1;
    }

    static int setFloatX(float x) {
        return (int)((x+1)*(Gdx.graphics.getWidth() / 2));
    }

    static int setFloatY(float y) {
        return (int)((y+1)*(Gdx.graphics.getHeight() / 2));
    }

}
