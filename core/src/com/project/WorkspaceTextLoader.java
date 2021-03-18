package com.project;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WorkspaceTextLoader {

    Scanner scanner;
    GlyphLayout layout;

    private StringBuilder processTag(String tag, String line, int startX){
        Font font = null;
        int length;
        StringBuilder string = new StringBuilder();
        if(tag.equals("{{MAIN}}")){
            font = InterfaceParameters.MAIN_FONT;
        }
        else if(tag.equals("{{HEADER}}")){
            font = InterfaceParameters.HEADER_FONT;
        }
        line = line.replaceFirst("\\{\\{"+tag.substring(2), "        ");
        this.layout = new GlyphLayout(font.font, line);
        font.layout.setText(font.font, "W");
        while(scanner.hasNextLine() || line.endsWith(tag+"\n")){
            if(line.endsWith(tag+"\n")){
                line = line.replace(tag+"\n", "\n");
                this.layout.setText(font.font, line);
                while(this.layout.width > Gdx.graphics.getWidth()-startX) {
                    length = (int)((this.layout.width - (Gdx.graphics.getWidth()-startX))/font.layout.width);
                    string.append(line.substring(0, line.length()-length-1)+"\n");
                    line = line.substring(line.length()-length-1);
                    this.layout.setText(font.font, line);
                }
                string.append(line);
                break;
            }
            while(this.layout.width > Gdx.graphics.getWidth()-startX) {
                length = (int)((this.layout.width - (Gdx.graphics.getWidth()-startX))/font.layout.width);
                string.append(line.substring(0, line.length()-length-1)+"\n");
                line = line.substring(line.length()-length-1);
                this.layout.setText(font.font, line);
            }
            if(!line.endsWith(tag+"\n"))
                string.append(line);
            if (scanner.hasNextLine())
                line = scanner.nextLine() + "\n";
            this.layout.setText(font.font, line);
        }
        this.layout.setText(font.font, string.toString());
        return string;
    }

    public void textLoad(String location, int startX, int startY, Workspace workspace){
        String str = "";
        StringBuilder string = new StringBuilder();
        int nowY = startY;
        try{
            this.scanner = new Scanner(new InputStreamReader(new FileInputStream(location), "UTF-8"));
            while(scanner.hasNextLine()) {
                str = scanner.nextLine() + "\n";
                if (str.startsWith("{{MAIN}}")) {
                    string = processTag("{{MAIN}}", str, startX);
                    workspace.addItem(new SimpleText(startX, nowY, InterfaceParameters.MAIN_FONT, string.toString()));
                }
                else if (str.startsWith("{{HEADER}}")) {
                    string = processTag("{{HEADER}}", str, startX);
                    workspace.addItem(new SimpleText(startX, nowY, InterfaceParameters.HEADER_FONT, string.toString()));
                }
                nowY -= layout.height + 4;
                string = new StringBuilder();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
