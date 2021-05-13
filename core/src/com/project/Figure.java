package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.graphics.GL20.*;

public class Figure implements Drawable {

    static Shader FigureShader = new Shader ("./resources/Shaders/FigureVShader.vert",
                                           "./resources/Shaders/FigureFShader.frag");
    float[] vertices;
    float[] colors;
    int[]   indices;

    int vertVBO, colVBO, VAO, EBO;

    public Figure (float[] vertices, float[] colors, int[] indices) {
        this.vertices = vertices;
        this.colors = colors;
        this.indices = indices;
    }

    public void init() {
        IntBuffer vao = BufferUtils.newIntBuffer(1);
        vao.clear();
        Gdx.gl30.glGenVertexArrays(1, vao);
        this.VAO = vao.get();
        // Create VBO upload the vertex buffer
        this.vertVBO = Gdx.gl20.glGenBuffer();
        this.initVertices();
        this.colVBO = Gdx.gl.glGenBuffer();
        this.initColors();
        // Create the indices and upload
        Gdx.gl30.glBindVertexArray(this.VAO);
        this.EBO = Gdx.gl20.glGenBuffer();
        IntBuffer ind = BufferUtils.newIntBuffer(this.indices.length);
        ind.put(this.indices);
        ind.flip();
        Gdx.gl20.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.EBO);
        Gdx.gl20.glBufferData(GL_ELEMENT_ARRAY_BUFFER, Integer.SIZE * this.indices.length, ind, GL_STATIC_DRAW);
        // 4. Отвязываем VAO (НЕ EBO)
        Gdx.gl30.glBindVertexArray(0);
    }

    public void initVertices() {
        Gdx.gl30.glBindVertexArray(this.VAO);
        FloatBuffer vert = BufferUtils.newFloatBuffer(this.vertices.length);
        vert.put(this.vertices);
        vert.flip();
        Gdx.gl20.glBindBuffer(GL_ARRAY_BUFFER, this.vertVBO);
        Gdx.gl20.glBufferData(GL_ARRAY_BUFFER, Float.BYTES * this.vertices.length, vert, GL_STATIC_DRAW);
        Gdx.gl20.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        Gdx.gl20.glEnableVertexAttribArray(0);
        Gdx.gl30.glBindVertexArray(0);
    }

    public void initColors() {
        Gdx.gl30.glBindVertexArray(this.VAO);
        FloatBuffer col = BufferUtils.newFloatBuffer(this.colors.length);
        col.put(this.colors);
        col.flip();
        Gdx.gl.glBindBuffer(GL_ARRAY_BUFFER, this.colVBO);
        Gdx.gl20.glBufferData(GL_ARRAY_BUFFER, Float.BYTES * this.colors.length, col, GL_STATIC_DRAW);
        Gdx.gl20.glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
        Gdx.gl20. glEnableVertexAttribArray(1);
        Gdx.gl30.glBindVertexArray(0);
    }

    @Override
    public void draw() {
       FigureShader.bind();
       Gdx.gl30.glBindVertexArray(this.VAO);
       Gdx.gl20.glDrawElements(GL_TRIANGLES, this.indices.length, GL_UNSIGNED_INT, 0);
       Gdx.gl30.glBindVertexArray(0);
       FigureShader.unbind();
    }

    public void dispose(){
        IntBuffer vao = BufferUtils.newIntBuffer(1);
        vao.clear();
        vao.put(this.VAO);
        vao.flip();
        Gdx.gl30.glDeleteVertexArrays(1, vao);
        Gdx.gl20.glDeleteBuffer(this.colVBO);
        Gdx.gl20.glDeleteBuffer(this.vertVBO);
        Gdx.gl20.glDeleteBuffer(this.EBO);
    }

    public static void deleteShader() {
        Gdx.gl.glDeleteProgram(FigureShader.ProgramID);
    }

}