package com.project;

import com.badlogic.gdx.Gdx;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import static com.badlogic.gdx.graphics.GL20.*;
import static com.project.PNGDecoder.RGBA;

public class Texture extends Figure {

    static Shader TextureShader = new Shader("core/assets/TextureVShader.vert",
            "core/assets//TextureFShader.frag");

    int textureID;

    public Texture (float[] vertices, int[] indices, String location) {
        super(vertices, indices);
        this.uvSize = 2;
        this.textureID = loadTexture(location);
    }

    @Override
    public void draw() {
        Gdx.gl30.glBindTexture(GL_TEXTURE_2D, this.textureID);
        TextureShader.bind();
        Gdx.gl30.glBindVertexArray(this.VAO);
        Gdx.gl30.glDrawElements(GL_TRIANGLES, this.indexCount, GL_UNSIGNED_INT, 0);
        Gdx.gl30.glBindVertexArray(0);
        TextureShader.unbind();
        Gdx.gl30. glBindTexture(GL_TEXTURE_2D, 0);
    }

    private static int loadTexture(String location) {
        ByteBuffer image_buffer = null;
        int textureWidth = 0;
        int textureHeight = 0;

        try(InputStream in = new FileInputStream(location)) {
            // Link the PNG decoder to this stream
            PNGDecoder decoder = new PNGDecoder(in);
            // Get the width and height of the texture
            textureWidth = decoder.getWidth();
            textureHeight = decoder.getHeight();
            // Decode the PNG file in a ByteBuffer
            image_buffer = ByteBuffer.allocateDirect(
                    4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(image_buffer, decoder.getWidth() * 4, RGBA);
            image_buffer.flip();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        // Create a new texture object in memory and bind it
        int textureID = Gdx.gl20.glGenTexture();
        Gdx.gl20.glActiveTexture(GL_TEXTURE0);
        Gdx.gl20.glBindTexture(GL_TEXTURE_2D, textureID);
        // All RGB bytes are aligned to each other and each component is 1 byte
        Gdx.gl20.glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        // Upload the texture data and generate mip maps (for scaling)
        Gdx.gl20.glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, textureWidth, textureHeight, 0,
                GL_RGBA, GL_UNSIGNED_BYTE, image_buffer);
        Gdx.gl20.glGenerateMipmap(GL_TEXTURE_2D);
        // Setup the ST coordinate system
        Gdx.gl20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        Gdx.gl20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        // Setup what to do when the texture has to be scaled
        Gdx.gl20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER,
                GL_LINEAR);
        Gdx.gl20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER,
                GL_LINEAR);
        Gdx.gl20.glBindTexture(GL_TEXTURE_2D, 0);
        return textureID;
    }

}