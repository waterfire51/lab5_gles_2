// TextureQuad.java
package com.example.lab5_gles_2;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class TextureQuad {
    private final FloatBuffer vertexBuffer, texBuffer;
    private final int mProgram;
    private final int textureId;
    private final float[] modelMatrix = new float[16];

    private final float[] quadCoords = {
            -0.7f,  0.7f, 0.0f,
            -0.7f, -0.7f, 0.0f,
            0.7f, -0.7f, 0.0f,
            0.7f,  0.7f, 0.0f
    };

    private final float[] texCoords = {
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
    };

    private final short[] drawOrder = { 0, 1, 2, 0, 2, 3 };
    private final ByteBuffer indexBuffer;

    public TextureQuad(Context context, int drawableId) {
        vertexBuffer = createFloatBuffer(quadCoords);
        texBuffer = createFloatBuffer(texCoords);
        indexBuffer = ByteBuffer.allocateDirect(drawOrder.length * 2);
        indexBuffer.order(ByteOrder.nativeOrder());
        for (short s : drawOrder) {
            indexBuffer.putShort(s);
        }
        indexBuffer.position(0);

        String vertexShaderCode =
                "uniform mat4 uMVPMatrix;" +
                        "attribute vec4 vPosition;" +
                        "attribute vec2 aTexCoord;" +
                        "varying vec2 vTexCoord;" +
                        "void main() {" +
                        "  gl_Position = uMVPMatrix * vPosition;" +
                        "  vTexCoord = aTexCoord;" +
                        "}";

        String fragmentShaderCode =
                "precision mediump float;" +
                        "uniform sampler2D uTexture;" +
                        "varying vec2 vTexCoord;" +
                        "void main() {" +
                        "  gl_FragColor = texture2D(uTexture, vTexCoord);" +
                        "}";

        int vertexShader = MyGLRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = MyGLRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(mProgram, vertexShader);
        GLES20.glAttachShader(mProgram, fragmentShader);
        GLES20.glLinkProgram(mProgram);

        textureId = TextureHelper.loadTexture(context, drawableId);
    }

    public void draw(float[] viewMatrix, float[] projectionMatrix) {
        GLES20.glUseProgram(mProgram);

        Matrix.setIdentityM(modelMatrix, 0); // No rotation or transformation applied

        float[] mvMatrix = new float[16];
        float[] mvpMatrix = new float[16];

        Matrix.multiplyMM(mvMatrix, 0, viewMatrix, 0, modelMatrix, 0);
        Matrix.multiplyMM(mvpMatrix, 0, projectionMatrix, 0, mvMatrix, 0);

        int mvpMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
        GLES20.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0);

        int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(positionHandle);
        GLES20.glVertexAttribPointer(positionHandle, 3, GLES20.GL_FLOAT, false, 0, vertexBuffer);

        int texCoordHandle = GLES20.glGetAttribLocation(mProgram, "aTexCoord");
        GLES20.glEnableVertexAttribArray(texCoordHandle);
        GLES20.glVertexAttribPointer(texCoordHandle, 2, GLES20.GL_FLOAT, false, 0, texBuffer);

        int textureHandle = GLES20.glGetUniformLocation(mProgram, "uTexture");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureId);
        GLES20.glUniform1i(textureHandle, 0);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, indexBuffer);

        GLES20.glDisableVertexAttribArray(positionHandle);
        GLES20.glDisableVertexAttribArray(texCoordHandle);
    }

    private FloatBuffer createFloatBuffer(float[] data) {
        ByteBuffer bb = ByteBuffer.allocateDirect(data.length * 4);
        bb.order(ByteOrder.nativeOrder());
        FloatBuffer fb = bb.asFloatBuffer();
        fb.put(data);
        fb.position(0);
        return fb;
    }
} // EOF
