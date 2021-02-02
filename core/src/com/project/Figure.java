package com.project;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.graphics.GL20.*;

public class Figure implements Drawable {

    static Shader FigureShader = new Shader("core/assets/FigureVShader.vert",
            "core/assets/FigureFShader.frag");
    float[] vertices;
    int[] indices;

    int VBO, VAO, EBO;
    int uvSize = 0;

    public Figure(float[] vertices, int[] indices) {
        this.vertices = vertices;
        this.indices = indices;
    }

    public void init(){
        //Init buffers
        IntBuffer vao = BufferUtils.newIntBuffer(1);
        vao.clear();
        Gdx.gl30.glGenVertexArrays(1, vao);
        this.VAO = vao.get();
        Gdx.gl30.glBindVertexArray(this.VAO);
        // Create VBO upload the vertex buffer
        this.VBO = Gdx.gl20.glGenBuffer();
        FloatBuffer vert = BufferUtils.newFloatBuffer(this.vertices.length);
        vert.put(this.vertices);
        vert.flip();
        Gdx.gl20.glBindBuffer(GL_ARRAY_BUFFER, this.VBO);
        Gdx.gl20.glBufferData(GL_ARRAY_BUFFER, Float.BYTES*this.vertices.length, vert, GL_STATIC_DRAW);
        // Create the indices and upload
        this.EBO = Gdx.gl20.glGenBuffer();
        IntBuffer ind = BufferUtils.newIntBuffer(this.indices.length);
        ind.put(this.indices);
        ind.flip();
        Gdx.gl20.glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.EBO);
        Gdx.gl20.glBufferData(GL_ELEMENT_ARRAY_BUFFER, Integer.SIZE*this.indices.length, ind, GL_STATIC_DRAW);
        // Add the vertex attribute pointers
        int positionsSize = 3;
        int colorSize = 4;
        int vertexSizeBytes = (positionsSize + colorSize + uvSize) * Float.BYTES;
        Gdx.gl20.glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeBytes, 0);
        Gdx.gl20.glEnableVertexAttribArray(0);
        Gdx.gl20.glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionsSize * Float.BYTES);
        Gdx.gl20. glEnableVertexAttribArray(1);
        if(this.uvSize == 2) {
            Gdx.gl20.glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionsSize + colorSize) * Float.BYTES);
            Gdx.gl20.glEnableVertexAttribArray(2);
        }
        // 4. Отвязываем VAO (НЕ EBO)
        Gdx.gl30.glBindVertexArray(0);
    }

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
        Gdx.gl20.glDeleteBuffer(this.VBO);
        Gdx.gl20.glDeleteBuffer(this.EBO);
    }

}
