package com.project;

public class Picture{

    Texture picture;
    String location;
    int minX, minY;
    int width, height;

    public Picture(int minX, int minY, int width, int height, String location) {
        this.minX = minX;
        this.minY = minY;
        this.width = width;
        this.height = height;
        this.location = location;
    }

    public void init() {
        this.picture = new Texture(new float[] {
                Drawable.setIntX(this.minX),            Drawable.setIntY(this.minY),             0.0f,
                Drawable.setIntX(this.minX),            Drawable.setIntY(this.minY-this.height), 0.0f,
                Drawable.setIntX(this.minX+this.width), Drawable.setIntY(this.minY-this.height), 0.0f,
                Drawable.setIntX(this.minX+this.width), Drawable.setIntY(this.minY),             0.0f
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

    public void dispose() {

    }

}
