package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WorkspaceLoader {

    Scanner scanner;
    GlyphLayout layout;
    String str;
    int nowY;

    private Picture loadPicture() {
        int x = 0, y = this.nowY, width = 0, height = 0;
        String location = "";
        this.str = this.scanner.nextLine();
        while (!this.str.contains("{{PIC}}")) {
            if (this.str.startsWith("width=")) {
                width = Integer.parseInt(this.str.substring(6)) * Gdx.graphics.getWidth() / 640;
            }
            else if (this.str.startsWith("height=")) {
                height = Integer.parseInt(this.str.substring(7)) * Gdx.graphics.getHeight() / 480;
            }
            else if (this.str.startsWith("location=")) {
                location = this.str.substring(9);
            }
            this.str = this.scanner.nextLine();
        }
        x = (Gdx.graphics.getWidth() / 2) - (width / 2) + 50;
        this.nowY -= height + 10;
        return new Picture(x, y, width, height, location);
    }

    private StringBuilder processTag (String line, int startX, String tag) {
        Font font = null;
        int length;
        StringBuilder string = new StringBuilder();
        if (tag.equals("{{MAIN}}")) {
            font = InterfaceParameters.MAIN_FONT;
        }
        else if (tag.equals("{{HEADER}}")) {
            font = InterfaceParameters.HEADER_FONT;
        }
        line = line.replaceFirst("\\{\\{" + tag.substring(2), "        ");
        this.layout = new GlyphLayout();
        font.layout.setText(font.font, "W");
        while (scanner.hasNextLine() || line.endsWith(tag + "\n")) {
            this.layout.setText(font.font, line);
            if (line.endsWith(tag + "\n")) {
                line = line.replace(tag + "\n", "\n");
                this.layout.setText(font.font, line);
                while (this.layout.width > Gdx.graphics.getWidth() - startX) {
                    length = (int)((this.layout.width - (Gdx.graphics.getWidth() - startX)) / font.layout.width);
                    int i = 0;
                    this.layout.setText(font.font, line.substring(0, line.length() - length + i));
                    if (this.layout.width > Gdx.graphics.getWidth() - startX) {
                        while (this.layout.width > Gdx.graphics.getWidth() - startX) {
                            --i;
                            this.layout.setText(font.font, line.substring(0, line.length() - length + i));
                        }
                    }
                    else if (this.layout.width < Gdx.graphics.getWidth() - startX) {
                        while (this.layout.width < Gdx.graphics.getWidth() - startX) {
                            ++i;
                            this.layout.setText(font.font, line.substring(0, line.length() - length + i));
                        }
                    }
                    string.append(line, 0, line.length() - length + i).append("\n");
                    line = line.substring(line.length() - length + i);
                    this.layout.setText(font.font, line);
                }
                string.append(line);
                break;
            }
            while (this.layout.width > Gdx.graphics.getWidth() - startX) {
                length = (int)((this.layout.width - (Gdx.graphics.getWidth() - startX)) / font.layout.width);
                int i = 0;
                this.layout.setText(font.font, line.substring(0, line.length() - length + i));
                if (this.layout.width > Gdx.graphics.getWidth() - startX) {
                    while (this.layout.width > Gdx.graphics.getWidth() - startX) {
                        --i;
                        this.layout.setText(font.font, line.substring(0, line.length() - length + i));
                    }
                }
                else if (this.layout.width < Gdx.graphics.getWidth() - startX) {
                    while (this.layout.width < Gdx.graphics.getWidth() - startX) {
                        ++i;
                        this.layout.setText(font.font, line.substring(0, line.length() - length + i));
                    }
                }
                string.append(line, 0, line.length() - length + i).append("\n");
                line = line.substring(line.length() - length + i);
                this.layout.setText(font.font, line);
            }
            if (!line.endsWith(tag + "\n")) {
                string.append(line);
            }
            if (scanner.hasNextLine()) {
                line = scanner.nextLine() + "\n";
            }
        }
        this.layout.setText(font.font, string.toString());
        this.nowY -= this.layout.height + 5;
        return string;
    }

    public void contentLoad (String location, int startX, int startY, Workspace workspace) {
        this.nowY = startY;
        int picture_count = 0;
        boolean load_picture = workspace.pictures == null || workspace.pictures.isEmpty();
        try {
            this.scanner = new Scanner(new InputStreamReader(new FileInputStream(location), StandardCharsets.UTF_8));
            while (scanner.hasNextLine()) {
                this.str = this.scanner.nextLine() + "\n";
                if (this.str.contains("{{MAIN}}")) {
                    workspace.addItem(new SimpleText(startX, nowY, InterfaceParameters.MAIN_FONT,
                                      processTag(this.str, startX, "{{MAIN}}").toString()));
                }
                else if (this.str.contains("{{HEADER}}")) {
                    workspace.addItem(new SimpleText(startX, nowY, InterfaceParameters.HEADER_FONT,
                                      processTag(this.str, startX, "{{HEADER}}").toString()));
                }
                else if (this.str.contains("{{PIC}}")) {
                    if (load_picture) {
                        workspace.addItem(loadPicture());
                    }
                    else if (workspace.pictures.size() - 1 >= picture_count){
                        workspace.pictures.get(picture_count).maxY = this.nowY;
                        this.nowY -= workspace.pictures.get(picture_count).height + 15;
                        picture_count++;
                        this.str = this.scanner.nextLine();
                        while (!this.str.contains("{{PIC}}")) {
                            this.str = this.scanner.nextLine();
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.scanner.close();
        }
    }

}

