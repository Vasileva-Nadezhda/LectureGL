package com.project;

public class InterfaceColors {

    public static Color INTERFACE_BUTTON;
    public static Color SELECTED_INTERFACE_BUTTON;

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
            break;
            case WHITE:
                INTERFACE_BUTTON = new Color(0.5f, 0.5f, 0.5f);
                SELECTED_INTERFACE_BUTTON = new Color(0.375f, 0.375f, 0.375f);
            break;
            case BLUE:
                INTERFACE_BUTTON = new Color(0.0f, 0.0f, 0.8f);
                SELECTED_INTERFACE_BUTTON = new Color(0.0f, 0.0f, 0.675f);
                break;
            case GREEN:
                INTERFACE_BUTTON = new Color(0.0f, 0.8f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.0f, 0.675f, 0.0f);
                break;
            case RED:
                INTERFACE_BUTTON = new Color(0.8f, 0.0f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.675f, 0.0f, 0.0f);
                break;
            case YELLOW:
                INTERFACE_BUTTON = new Color(0.9f, 0.9f, 0.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.775f, 0.775f, 0.0f);
                break;
            case VIOLET:
                INTERFACE_BUTTON = new Color(0.5f, 0.0f, 1.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.375f, 0.0f, 0.875f);
                break;
            case SKY_BLUE:
                INTERFACE_BUTTON = new Color(0.25f, 0.6f, 1.0f);
                SELECTED_INTERFACE_BUTTON = new Color(0.125f, 0.475f, 0.875f);
                break;
        }
    }

}
