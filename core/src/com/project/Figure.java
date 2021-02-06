package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.graphics.GL30.*;

public class Figure implements Drawable {

    static Shader FigureShader = new Shader("core/assets/FigureVShader.vert",
            "core/assets/FigureFShader.frag");
    float[] vertices;
    float[] colors;
    int[] indices;

    int vertVBO, colVBO, VAO, EBO;
    int uvSize = 0;

    public Figure(float[] vertices, float[] colors, int[] indices) {
        this.vertices = vertices;
        this.colors = colors;
        this.indices = indices;
    }

    public void init(){
        IntBuffer vao = BufferUtils.newIntBuffer(1);
        vao.clear();
        Gdx.gl30.glGenVertexArrays(1, vao);
        this.VAO = vao.get();
        Gdx.gl30.glBindVertexArray(this.VAO);
        // Create VBO upload the vertex buffer
        this.vertVBO = Gdx.gl30.glGenBuffer();
        FloatBuffer vert = BufferUtils.newFloatBuffer(this.vertices.length);
        vert.put(this.vertices);
        vert.flip();
        Gdx.gl30.glBindBuffer(GL_ARRAY_BUFFER, this.vertVBO);
        Gdx.gl30.glBufferData(GL_ARRAY_BUFFER, Float.BYTES*this.vertices.length, vert, GL_STATIC_DRAW);
        Gdx.gl30.glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        Gdx.gl30.glEnableVertexAttribArray(0);
        this.colVBO = Gdx.gl.glGenBuffer();
        FloatBuffer col = BufferUtils.newFloatBuffer(this.colors.length);
        col.put(this.colors);
        col.flip();
        Gdx.gl.glBindBuffer(GL_ARRAY_BUFFER, this.colVBO);
        Gdx.gl30.glBufferData(GL_ARRAY_BUFFER, Float.BYTES*this.colors.length, col, GL_STATIC_DRAW);
        Gdx.gl30.glVertexAttribPointer(1, 4, GL_FLOAT, false, 0, 0);
        Gdx.gl30. glEnableVertexAttribArray(1);
        // Create the indices and upload
        this.EBO = Gdx.gl30.glGenBuffer();
        IntBuffer ind = BufferUtils.newIntBuffer(this.indices.length);
        ind.put(this.indices);
        ind.flip();
        Gdx.gl30.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.EBO);
        Gdx.gl30.glBufferData(GL_ELEMENT_ARRAY_BUFFER, Integer.SIZE*this.indices.length, ind, GL_STATIC_DRAW);
        // 4. Отвязываем VAO (НЕ EBO)
        Gdx.gl30.glBindVertexArray(0);
    }

    public void draw() {
       FigureShader.bind();
       Gdx.gl30.glBindVertexArray(this.VAO);
       Gdx.gl30.glDrawElements(GL_TRIANGLES, this.indices.length, GL_UNSIGNED_INT, 0);
       Gdx.gl30.glBindVertexArray(0);
       FigureShader.unbind();

    }

    public void dispose(){
        IntBuffer vao = BufferUtils.newIntBuffer(1);
        vao.clear();
        vao.put(this.VAO);
        vao.flip();
        Gdx.gl30.glDeleteVertexArrays(1, vao);
        Gdx.gl30.glDeleteBuffer(this.colVBO);
        Gdx.gl30.glDeleteBuffer(this.vertVBO);
        Gdx.gl30.glDeleteBuffer(this.EBO);
    }

}
