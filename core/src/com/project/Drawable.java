package com.project;

import com.badlogic.gdx.Gdx;

public interface Drawable {

    void init();

    default void draw() {

    }

    void dispose();

    default int setX(float x) {
        return (int) ((x+1)*Gdx.graphics.getWidth() / 2);
    }

    default int setY(float y) {
        return (int) ((y+1)*Gdx.graphics.getHeight() / 2);
    }

}
