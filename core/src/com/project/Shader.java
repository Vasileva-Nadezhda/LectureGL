package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.IntBuffer;
import java.util.Scanner;

import static com.badlogic.gdx.graphics.GL20.*;

public class Shader {

    private final StringBuilder VERTEX_FILE_PATH;
    private final StringBuilder FRAGMENT_FILE_PATH;

    int ProgramID;

    public Shader (String vertex_file_path, String fragment_file_path) {
        StringBuilder vertex_file = new StringBuilder(vertex_file_path.length());
        StringBuilder fragment_file = new StringBuilder(fragment_file_path.length());
        vertex_file.append(vertex_file_path);
        this.VERTEX_FILE_PATH = vertex_file;
        fragment_file.append(fragment_file_path);
        this.FRAGMENT_FILE_PATH = fragment_file;
    }

    public void loadFromFile() {
        this.ProgramID = loadShaders(this.VERTEX_FILE_PATH, this.FRAGMENT_FILE_PATH);
    }

    public void bind() {
        Gdx.gl30.glUseProgram(this.ProgramID);
    }

    public void unbind() {
        Gdx.gl30.glUseProgram(0);
    }

    private void compileShader (StringBuilder shader_file_path, String shaderSourcePointer, int shaderID) {
        System.out.printf("Compiling shader : %s\n", shader_file_path);
        Gdx.gl20.glShaderSource(shaderID, shaderSourcePointer);
        Gdx.gl20.glCompileShader(shaderID);
        //Check Vertex Shader
        IntBuffer status = BufferUtils.newIntBuffer(1);
        status.clear();
        IntBuffer log_length = BufferUtils.newIntBuffer(1);
        log_length.clear();
        Gdx.gl.glGetShaderiv(shaderID, GL_INFO_LOG_LENGTH, log_length);
        Gdx.gl.glGetShaderiv(shaderID, GL_COMPILE_STATUS, status);
        if ((status.get() != 1) && (log_length.get() > 0)){
            System.out.println(Gdx.gl.glGetShaderInfoLog(shaderID));
            System.exit(-1);
        }
    }

    int loadShaders (StringBuilder vertex_file_path, StringBuilder fragment_file_path) {
        // Create the shaders
        int vertexShaderID = Gdx.gl20.glCreateShader(GL_VERTEX_SHADER);
        int fragmentShaderID = Gdx.gl20.glCreateShader(GL_FRAGMENT_SHADER);
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
        int ProgramID = Gdx.gl20.glCreateProgram();
        Gdx.gl20.glAttachShader(ProgramID, vertexShaderID);
        Gdx.gl20.glAttachShader(ProgramID, fragmentShaderID);
        Gdx.gl20.glLinkProgram(ProgramID);
        // Check the program
        IntBuffer status = BufferUtils.newIntBuffer(1);
        status.clear();
        IntBuffer log_length = BufferUtils.newIntBuffer(1);
        log_length.clear();
        Gdx.gl.glGetProgramiv(ProgramID, GL_INFO_LOG_LENGTH, log_length);
        Gdx.gl.glGetProgramiv(ProgramID, GL_LINK_STATUS, status);
        if ((log_length.get() > 0) && (status.get() != 1)){
            System.out.println(Gdx.gl.glGetProgramInfoLog(ProgramID));
            System.exit(-1);
        }
        Gdx.gl20.glDetachShader(ProgramID, vertexShaderID);
        Gdx.gl20.glDetachShader(ProgramID, fragmentShaderID);
        Gdx.gl20.glDeleteShader(vertexShaderID);
        Gdx.gl20.glDeleteShader(fragmentShaderID);
        System.out.print("Done.\n");
        return ProgramID;
    }

    String readShaderCode (String shader_file_path) {
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