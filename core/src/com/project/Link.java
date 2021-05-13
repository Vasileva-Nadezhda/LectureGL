package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

public class Link extends SimpleText implements Drawable {

    public static Font selected_font;
    String action;
    boolean was_selected = false;

    public Link (int x, int y, Font font, String text, String action) {
        super(x, y, font, text);
        this.action = action;
        this.init();
    }

    public void init() {
        if (selected_font == null) {
            float delta_color = -0.15f;
            if (this.font.color == Color.BLACK) {
                delta_color *= -1;
            }
            Color color = new Color(this.font.color.r + delta_color, this.font.color.g + delta_color, this.font.color.b + delta_color, 1.0f);
            selected_font = new Font(this.font.location, this.font.size, color);
            selected_font.init();
        }
    }

    @Override
    public void draw() {
        this.processInput();
        if (was_selected) {
            selected_font.draw(this.x, this.y + Window.workspaces.get(Window.workspaces.size() - 1).deltaY, this.text);
        }
        else {
            this.font.draw(this.x, this.y +Window.workspaces.get(Window.workspaces.size() - 1).deltaY, this.text);
        }
    }

    private void processInput() {
        if (     (Gdx.input.getX() <= (this.x + this.width))
              && (Gdx.input.getX() >= this.x)
              && (Gdx.graphics.getHeight() - Gdx.input.getY() <= this.y + Window.workspaces.get(Window.workspaces.size() - 1).deltaY)
              && (Gdx.graphics.getHeight() - Gdx.input.getY() >= this.y - this.height + Window.workspaces.get(Window.workspaces.size() - 1).deltaY)) {
                if (!this.was_selected) {
                    this.was_selected = true;
                }
                if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                    if (this.action.startsWith("load=")) {
                        Window.openSubsection(this.action.substring(5));
                    }
                }
        }
        else {
            if (this.was_selected) {
                this.was_selected = false;
            }
        }
    }

    public void dispose() {}

    public static void deleteFont() {
        if (selected_font != null)
        selected_font.dispose();
    }

}