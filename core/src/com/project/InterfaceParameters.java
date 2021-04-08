package com.project;

public class InterfaceParameters {

    public static Color INTERFACE_BUTTON;
    public static Color SELECTED_INTERFACE_BUTTON;
    public static Font HEADER_FONT;
    public static Font MAIN_FONT;

    enum Colors{
        BLACK,
        WHITE,
        BLUE,
        GREEN,
        RED,
        YELLOW,
        VIOLET,
        SKY_BLUE
    }

    public static void changeColors(Colors color){
        switch (color){
            case BLACK:
                INTERFACE_BUTTON = new Color(0.0f, 0.0f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.125f, 0.125f, 0.125f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.WHITE);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.WHITE);
            break;
            case WHITE:
                INTERFACE_BUTTON = new Color(0.5f, 0.5f, 0.5f);
                SELECTED_INTERFACE_BUTTON = new Color(0.375f, 0.375f, 0.375f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.BLACK);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
            break;
            case BLUE:
                INTERFACE_BUTTON = new Color(0.0f, 0.0f, 0.8f);
                SELECTED_INTERFACE_BUTTON = new Color(0.0f, 0.0f, 0.675f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.BLUE);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
                break;
            case GREEN:
                INTERFACE_BUTTON = new Color(0.0f, 0.8f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.0f, 0.675f, 0.0f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.GREEN);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
                break;
            case RED:
                INTERFACE_BUTTON = new Color(0.8f, 0.0f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.675f, 0.0f, 0.0f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.RED);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
                break;
            case YELLOW:
                INTERFACE_BUTTON = new Color(0.9f, 0.9f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.775f, 0.775f, 0.0f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.YELLOW);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
                break;
            case VIOLET:
                INTERFACE_BUTTON = new Color(0.5f, 0.0f, 0.9f);
                SELECTED_INTERFACE_BUTTON = new Color(0.375f, 0.0f, 0.875f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.VIOLET);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
                break;
            case SKY_BLUE:
                INTERFACE_BUTTON = new Color(0.18f, 0.4f, 0.58f);
                SELECTED_INTERFACE_BUTTON = new Color(0.055f, 0.275f, 0.455f);
                HEADER_FONT = new Font(Font.BOLD, 20, com.badlogic.gdx.graphics.Color.SKY);
                MAIN_FONT = new Font(Font.REGULAR, 14, com.badlogic.gdx.graphics.Color.BLACK);
                break;
        }
        HEADER_FONT.init();
        MAIN_FONT.init();
    }

}
