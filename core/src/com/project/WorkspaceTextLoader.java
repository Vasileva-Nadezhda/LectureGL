package com.project;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WorkspaceTextLoader {

    public void textLoad(String location, int startX, int startY, Workspace workspace){
        String str = "";
        StringBuilder string = new StringBuilder();
        int nowY = startY;
        int length;
        try(Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(location), "UTF-8"))){
            while(scanner.hasNextLine()) {
                str = scanner.nextLine() + "\n";
                if(str.startsWith("{{MAIN}}")){
                    str = str.replaceFirst("\\{\\{MAIN}}", "        ");
                    GlyphLayout layout = new GlyphLayout(InterfaceParameters.MAIN_FONT.font, str);
                    InterfaceParameters.MAIN_FONT.layout.setText(InterfaceParameters.MAIN_FONT.font, "W");
                    while(scanner.hasNextLine() || str.endsWith("{{MAIN}}\n")){
                        if(str.endsWith("{{MAIN}}\n")){
                            str = str.replace("{{MAIN}}\n", "\n");
                            layout.setText(InterfaceParameters.MAIN_FONT.font, str);
                            while(layout.width > Gdx.graphics.getWidth()-startX) {
                                length = (int)((layout.width - (Gdx.graphics.getWidth()-startX))/InterfaceParameters.MAIN_FONT.layout.width);
                                System.out.println(length);
                                System.out.println("BBBB");
                                string.append(str.substring(0, str.length()-length-1)+"\n");
                                str = str.substring(str.length()-length-1);
                                layout.setText(InterfaceParameters.MAIN_FONT.font, str);
                            }
                            string.append(str);
                            break;
                        }
                        while(layout.width > Gdx.graphics.getWidth()-startX) {
                            length = (int)((layout.width - (Gdx.graphics.getWidth()-startX))/InterfaceParameters.MAIN_FONT.layout.width);
                            System.out.println(layout.width);
                            System.out.println(Gdx.graphics.getWidth()-startX);
                            string.append(str.substring(0, str.length()-length-1)+"\n");
                            str = str.substring(str.length()-length-1);
                            layout.setText(InterfaceParameters.MAIN_FONT.font, str);
                        }
                        if(!str.endsWith("{{MAIN}}\n"))
                        string.append(str);
                        if (scanner.hasNextLine())
                        str = scanner.nextLine() + "\n";
                        layout.setText(InterfaceParameters.MAIN_FONT.font, str);
                    }
                    workspace.addItem(new SimpleText(startX, nowY, string.toString()));
                    layout.setText(InterfaceParameters.MAIN_FONT.font, string.toString());
                    nowY -= layout.height + 4;
                }
                string = new StringBuilder();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
