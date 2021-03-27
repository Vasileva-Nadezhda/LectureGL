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

    private StringBuilder processTag(String line, int startX, String tag){
        Font font = null;
        int length;
        StringBuilder string = new StringBuilder();
        if (tag.equals("{{MAIN}}")) {
            font = InterfaceParameters.MAIN_FONT;
        }
        else if (tag.equals("{{HEADER}}")){
            font = InterfaceParameters.HEADER_FONT;
        }
        line = line.replaceFirst("\\{\\{"+tag.substring(2), "        ");
        this.layout = new GlyphLayout();
        font.layout.setText(font.font, "W");
        while (scanner.hasNextLine() || line.endsWith(tag + "\n")) {
            this.layout.setText(font.font, line);
            if(line.endsWith(tag + "\n")) {
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
            if(!line.endsWith(tag + "\n")) {
                string.append(line);
            }
            if (scanner.hasNextLine()) {
                line = scanner.nextLine() + "\n";
            }
        }
        this.layout.setText(font.font, string.toString());
        return string;
    }

    public void textLoad(String location, int startX, int startY, Workspace workspace){
        String str;
        int nowY = startY;
        try {
            this.scanner = new Scanner(new InputStreamReader(new FileInputStream(location), StandardCharsets.UTF_8));
            while (scanner.hasNextLine()) {
                str = this.scanner.nextLine() + "\n";
                if (str.contains("{{MAIN}}")) {
                    workspace.addItem(new SimpleText(startX, nowY, InterfaceParameters.MAIN_FONT,
                            processTag(str, startX, "{{MAIN}}").toString()));
                }
                else if (str.contains("{{HEADER}}")) {
                    workspace.addItem(new SimpleText(startX, nowY, InterfaceParameters.HEADER_FONT,
                            processTag(str, startX, "{{HEADER}}").toString()));
                }
                nowY -= this.layout.height;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

