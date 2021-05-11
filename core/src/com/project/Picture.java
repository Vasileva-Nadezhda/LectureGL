package com.project;

import com.badlogic.gdx.Gdx;

public class Picture{

    Texture picture;
    String location;
    int minX, maxY;
    int width, height;
    float resize_coefficient;

    public Picture (int maxY, int width, int height, String location) {
        this.maxY = maxY;
        this.width = width;
        this.height = height;
        this.location = location;
    }

    public void init() {
        if (this.picture != null) {
            this.picture.dispose();
        }
        if ((Gdx.graphics.getWidth() != 640) && (Gdx.graphics.getHeight() == 480)) {
            this.resize_coefficient = Gdx.graphics.getWidth() / 640.0f;
        }
        else {
            this.resize_coefficient = Gdx.graphics.getHeight() / 480.0f;
        }
        this.width = (int) (this.width * this.resize_coefficient);
        this.height = (int) (this.height * this.resize_coefficient);
        this.minX = (Gdx.graphics.getWidth() / 2) - (width / 2) + 50;
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
                },
                this.location);
        this.picture.init();
    }

    public void draw() {
        this.picture.draw();
    }

    public void resizeParameters() {
        this.width = (int) (this.width / this.resize_coefficient);
        this.height = (int) (this.height / this.resize_coefficient);
        if ((Gdx.graphics.getWidth() != 640) && (Gdx.graphics.getHeight() == 480)) {
            this.resize_coefficient = Gdx.graphics.getWidth() / 640.0f;
        }
        else {
            this.resize_coefficient = Gdx.graphics.getHeight() / 480.0f;
        }
        this.width = (int) (this.width * this.resize_coefficient);
        this.height = (int) (this.height * this.resize_coefficient);
        this.minX = (Gdx.graphics.getWidth() / 2) - (this.width / 2) + 50;
    }

    public void change (int deltaY) {
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