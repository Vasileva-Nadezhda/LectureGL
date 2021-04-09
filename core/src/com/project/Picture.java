package com.project;

import com.badlogic.gdx.Gdx;

public class Picture{

    Texture picture;
    String location;
    int minX, maxY;
    int width, height;

    public Picture (int minX, int maxY, int width, int height, String location) {
        this.minX = minX;
        this.maxY = maxY;
        this.width = width;
        this.height = height;
        this.location = location;
    }

    public void init() {
        if (this.picture != null) {
            this.picture.dispose();
        }
        this.picture = new Texture(new float[] {
                Drawable.setIntX(this.minX),              Drawable.setIntY(this.maxY),                0.0f,
                Drawable.setIntX(this.minX),              Drawable.setIntY(this.maxY - this.height),  0.0f,
                Drawable.setIntX(this.minX + this.width), Drawable.setIntY(this.maxY - this.height),  0.0f,
                Drawable.setIntX(this.minX + this.width), Drawable.setIntY(this.maxY),                0.0f
                },
                new float[] {
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f,
                        1.0f, 1.0f, 1.0f, 1.0f
                },
                new float[] {
                        0, 0,
                        0, 1,
                        1, 1,
                        1, 0
                },
                new int[] {
                        0, 1, 3,
                        1, 2, 3
                });
        this.picture.init(this.location);
    }

    public void draw() {
        this.picture.draw();
    }

    public void resizeParameters() {
        this.height = Drawable.resizeY(this.height);
        this.width = Drawable.resizeX(this.width);
        this.minX = (Gdx.graphics.getWidth() / 2) - (this.width / 2) + 50;
    }

    public void resize (int deltaY) {
        this.picture.vertices = new float[] {
                Drawable.setIntX(this.minX),              Drawable.setIntY(this.maxY + deltaY),                 0.0f,
                Drawable.setIntX(this.minX),              Drawable.setIntY(this.maxY + deltaY - this.height),   0.0f,
                Drawable.setIntX(this.minX + this.width), Drawable.setIntY(this.maxY + deltaY - this.height),   0.0f,
                Drawable.setIntX(this.minX + this.width), Drawable.setIntY(this.maxY + deltaY),                 0.0f
        };
        this.picture.initVertices();
    }

    public void dispose() {
        this.picture.dispose();
    }

}
