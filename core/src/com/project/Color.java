package com.project;

public class Color {

    float r;
    float g;
    float b;

    public Color (float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public Color clone() {
        try {
            super.clone();
        }
        catch (CloneNotSupportedException e) {
            System.out.println();
        }
        return new Color(this.r, this.g, this.b);
    }

}