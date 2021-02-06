package com.project;

import com.badlogic.gdx.Gdx;

import java.util.Scanner;

import static com.badlogic.gdx.graphics.GL30.GL_FRAGMENT_SHADER;
import static com.badlogic.gdx.graphics.GL30.GL_VERTEX_SHADER;

public class Shader {

    private final StringBuilder vertex_file_path;
    private final StringBuilder fragment_file_path;

    int ProgramID;

    public Shader(String vertex_file_path, String fragment_file_path) {
        StringBuilder vertex_file = new StringBuilder(vertex_file_path.length());
        StringBuilder fragment_file = new StringBuilder(fragment_file_path.length());
        vertex_file.append(vertex_file_path);
        this.vertex_file_path = vertex_file;
        fragment_file.append(fragment_file_path);
        this.fragment_file_path = fragment_file;
    }

    public void loadFromFile() {
        this.ProgramID = loadShaders(this.vertex_file_path, this.fragment_file_path);
    }

    public void bind(){
        Gdx.gl30.glUseProgram(this.ProgramID);
    }
    public void unbind(){
        Gdx.gl30.glUseProgram(0);
    }

    private void compileShader(StringBuilder shader_file_path, String shaderSourcePointer, int shaderID) {
        System.out.printf("Compiling shader : %s\n", shader_file_path);
        Gdx.gl30.glShaderSource(shaderID, shaderSourcePointer);
        Gdx.gl30.glCompileShader(shaderID);
        //Check Vertex Shader
//        if ((glGetShaderi(shaderID, GL_COMPILE_STATUS) != 1) && (glGetShaderi(shaderID, GL_INFO_LOG_LENGTH) > 0)){
//            System.out.println(glGetShaderInfoLog(shaderID, GL_INFO_LOG_LENGTH));
//            System.exit(-1);
//        }
    }

    int loadShaders(StringBuilder vertex_file_path, StringBuilder fragment_file_path) {
        // Create the shaders
        int vertexShaderID = Gdx.gl30.glCreateShader(GL_VERTEX_SHADER);
        int fragmentShaderID = Gdx.gl30.glCreateShader(GL_FRAGMENT_SHADER);
        // Read the Vertex Shader code from the file
        String VertexShaderCode = readShaderCode(vertex_file_path.toString());
        // Read the Fragment Shader code from the file
        String FragmentShaderCode = readShaderCode(fragment_file_path.toString());
        // Compile Vertex Shader
        compileShader(vertex_file_path, VertexShaderCode, vertexShaderID);
        // Compile Fragment Shader
        compileShader(fragment_file_path, FragmentShaderCode, fragmentShaderID);
        // Link the program
        System.out.print("Linking program\n");
        int ProgramID = Gdx.gl30.glCreateProgram();
        Gdx.gl30.glAttachShader(ProgramID, vertexShaderID);
        Gdx.gl30.glAttachShader(ProgramID, fragmentShaderID);
        Gdx.gl30.glLinkProgram(ProgramID);
        // Check the program
//        if ((glGetProgrami(ProgramID, GL_INFO_LOG_LENGTH) > 0) && (glGetProgrami(ProgramID, GL_LINK_STATUS) != 1)){
//            System.out.println(glGetProgramInfoLog(ProgramID, GL_INFO_LOG_LENGTH));
//            System.exit(-1);
//        }
        Gdx.gl30.glDetachShader(ProgramID, vertexShaderID);
        Gdx.gl30.glDetachShader(ProgramID, fragmentShaderID);
        Gdx.gl30.glDeleteShader(vertexShaderID);
        Gdx.gl30.glDeleteShader(fragmentShaderID);
        System.out.print("Done.\n");
        return ProgramID;
    }

    String readShaderCode(String shader_file_path){
        StringBuilder str = new StringBuilder();
        try (Scanner scanner = new Scanner(new java.io.File(shader_file_path))){
            while (scanner.hasNextLine()) {
                str.append(scanner.nextLine());
                str.append('\n');
            }
        }
        catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
        if (str.length() > 0) {
            return str.toString();
        }
        else {
            System.out.printf("Impossible to open or read %s.\n", shader_file_path);
            System.exit(-1);
            return null;
        }
    }

}
