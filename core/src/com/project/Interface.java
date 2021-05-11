package com.project;

import com.badlogic.gdx.Gdx;
import java.util.ArrayList;

public class Interface implements Drawable {

    Figure left_panel;
    Figure upper_panel;
    public ArrayList <Button> section_buttons = new ArrayList<>();
    Button back_button;
    int id_of_last_button = 4;

    public void init() {
        for (Button button : this.section_buttons) {
            button.init();
        }
        this.back_button.init();
        this.left_panel.init();
        this.upper_panel.init();
    }

    @Override
    public void draw() {
        for (Button button : this.section_buttons) {
            button.draw();
        }
        this.left_panel.draw();
        this.upper_panel.draw();
        if (Window.workspaces.size() > 1){
            this.back_button.draw();
        }
    }

    public void dispose() {
        for (Button button : this.section_buttons) {
            button.dispose();
        }
        this.back_button.dispose();
        this.section_buttons.clear();
        this.left_panel.dispose();
        this.upper_panel.dispose();
    }

    private void moveVertices (float[] vertices, int maxY) {
        for (int j = 1; j <= 10; j += 9) {
            vertices[j] = Drawable.setIntY(maxY);
        }
        for (int j = 4; j <= 7; j += 3) {
            vertices[j] = Drawable.setIntY(maxY- InterfaceParameters.section_button_height);
        }
    }

    public void resize() {
        int maxY = Gdx.graphics.getHeight() - InterfaceParameters.upper_panel_height;
        float[] vertices = {
                Drawable.setIntX(InterfaceParameters.section_button_width), Drawable.setIntY(maxY),                                              0.0f,
                Drawable.setIntX(InterfaceParameters.section_button_width), Drawable.setIntY(maxY - InterfaceParameters.section_button_height),  0.0f,
                -1.0f,                                                      Drawable.setIntY(maxY - InterfaceParameters.section_button_height),  0.0f,
                -1.0f,                                                      Drawable.setIntY(maxY),                                              0.0f
        };
        for (int i = 0; i < this.section_buttons.size(); ++i) {
            this.section_buttons.get(i).body.vertices = vertices;
            this.section_buttons.get(i).body.initVertices();
            this.section_buttons.get(i).name.y = maxY - InterfaceParameters.nameY;
            this.section_buttons.get(i).initPoses();
            if (i < this.section_buttons.size() - 1) {
                maxY -= InterfaceParameters.section_button_height;
                moveVertices(vertices, maxY);
            }
        }
        vertices = new float[] {
                Drawable.setIntX(InterfaceParameters.section_button_width), Drawable.setIntY(maxY- InterfaceParameters.section_button_height),  0.0f,
                Drawable.setIntX(InterfaceParameters.section_button_width), -1.0f,                                                              0.0f,
                -1.0f,                                                      -1.0f,                                                              0.0f,
                -1.0f,                                                      Drawable.setIntY(maxY- InterfaceParameters.section_button_height),  0.0f
        };
        this.left_panel.vertices = vertices;
        this.left_panel.initVertices();
        maxY = Gdx.graphics.getHeight();
        vertices = new float[] {
                1.0f,   1.0f,                                                           0.0f,
                1.0f,   Drawable.setIntY(maxY- InterfaceParameters.upper_panel_height), 0.0f,
                -1.0f,  Drawable.setIntY(maxY- InterfaceParameters.upper_panel_height), 0.0f,
                -1.0f,  1.0f,                                                           0.0f
        };
        this.upper_panel.vertices = vertices;
        this.upper_panel.initVertices();
        vertices = new float[] {
                Drawable.setIntX(InterfaceParameters.upper_panel_height),   1.0f,                                                           0.0f,
                Drawable.setIntX(InterfaceParameters.upper_panel_height),   Drawable.setIntY(maxY- InterfaceParameters.upper_panel_height), 0.0f,
                -1.0f,                                                      Drawable.setIntY(maxY- InterfaceParameters.upper_panel_height), 0.0f,
                -1.0f,                                                      1.0f,                                                           0.0f
        };
        this.back_button.body.vertices = vertices;
        this.back_button.body.initVertices();
        this.back_button.initPoses();
    }

    public Interface() {
        int maxY = Gdx.graphics.getHeight() - InterfaceParameters.upper_panel_height;
        float[] vertices = {
                Drawable.setIntX(InterfaceParameters.section_button_width),  Drawable.setIntY(maxY),                                              0.0f,
                Drawable.setIntX(InterfaceParameters.section_button_width),  Drawable.setIntY(maxY - InterfaceParameters.section_button_height),  0.0f,
                -1.0f,                                                       Drawable.setIntY(maxY - InterfaceParameters.section_button_height),  0.0f,
                -1.0f,                                                       Drawable.setIntY(maxY),                                              0.0f
        };
        float[] colors = {
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f,
                InterfaceParameters.INTERFACE_BUTTON.r, InterfaceParameters.INTERFACE_BUTTON.g, InterfaceParameters.INTERFACE_BUTTON.b, 1.0f
        };
        int[] indices = {
                0, 1, 2,
                0, 3, 2
        };
        this.section_buttons.add(new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()),
                                      "maths1", InterfaceParameters.nameX, maxY - InterfaceParameters.nameY,
                                            ()-> {
                                                this.section_buttons.get(0).wasSelected = true;
                                                Window.openSection("./core/assets/maths1.theory");
                                            }));
        maxY -= InterfaceParameters.section_button_height;
        moveVertices(vertices, maxY);
        this.section_buttons.add(new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()),
                                      "maths2", InterfaceParameters.nameX, maxY - InterfaceParameters.nameY,
                                            ()-> {
                                                this.section_buttons.get(1).wasSelected = true;
                                                Window.openSection("./core/assets/maths2.theory");
                                            }));
        maxY -= InterfaceParameters.section_button_height;
        moveVertices(vertices, maxY);
        this.section_buttons.add(new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()),
                                      "Physics", InterfaceParameters.nameX, maxY - InterfaceParameters.nameY,
                                            ()-> {
                                                this.section_buttons.get(2).wasSelected = true;
                                                Window.openSection("./core/assets/physics.theory");
                                            }));
        maxY -= InterfaceParameters.section_button_height;
        moveVertices(vertices, maxY);
        this.section_buttons.add(new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()),
                                      "Chemistry", InterfaceParameters.nameX, maxY - InterfaceParameters.nameY,
                                            ()-> {
                                                this.section_buttons.get(3).wasSelected = true;
                                                Window.openSection("./core/assets/chemistry.theory");
                                            }));
        maxY -= InterfaceParameters.section_button_height;
        moveVertices(vertices, maxY);
        this.section_buttons.add(new Button(new Figure(vertices.clone(), colors.clone(), indices.clone()),
                                      "Gallery", InterfaceParameters.nameX, maxY - InterfaceParameters.nameY,
                                            ()-> {
                                                this.section_buttons.get(4).wasSelected = true;
                                                Window.openSection("./core/assets/gallery.theory");
                                            }));
        vertices = new float[] {
                Drawable.setIntX(InterfaceParameters.section_button_width), Drawable.setIntY(maxY - InterfaceParameters.section_button_height), 0.0f,
                Drawable.setIntX(InterfaceParameters.section_button_width), -1.0f,                                                              0.0f,
                -1.0f,                                                      -1.0f,                                                              0.0f,
                -1.0f,                                                      Drawable.setIntY(maxY - InterfaceParameters.section_button_height), 0.0f
        };
        this.left_panel = new Figure (vertices.clone(), colors.clone(), indices);
        maxY = Gdx.graphics.getHeight();
        vertices = new float[] {
                1.0f,    1.0f,                                                               0.0f,
                1.0f,    Drawable.setIntY(maxY - InterfaceParameters.upper_panel_height),    0.0f,
                -1.0f,   Drawable.setIntY(maxY - InterfaceParameters.upper_panel_height),    0.0f,
                -1.0f,   1.0f,                                                               0.0f
        };
        this.upper_panel = new Figure(vertices.clone(), colors, indices);
        vertices = new float[] {
                Drawable.setIntX(InterfaceParameters.upper_panel_height),   1.0f,                                                               0.0f,
                -1.0f,                                                      1.0f,                                                               0.0f,
                -1.0f,                                                      Drawable.setIntY(maxY - InterfaceParameters.upper_panel_height),    0.0f,
                Drawable.setIntX(InterfaceParameters.upper_panel_height),   Drawable.setIntY(maxY - InterfaceParameters.upper_panel_height),    0.0f
        };
        colors = new float[] {
                1.0f, 1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 1.0f, 0.0f,
                1.0f, 1.0f, 1.0f, 0.0f
        };
        this.back_button = new Button(new Texture(vertices, colors,
                                      new float[] {
                                              1, 0,
                                              1, 1,
                                              0, 1,
                                              0, 0
                                      },
                                      indices, "./core/assets/back.png"), Window::closeLastSubsection);
    }

}