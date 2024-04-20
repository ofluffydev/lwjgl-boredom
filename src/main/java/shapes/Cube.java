package shapes;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Cube {
    private final int vaoId;
    private final int vboId;
    private final int eboId;
    private final float[] vertices;
    private final int[] indices;

    /**
     * Creates a cube of given size at given location.
     * This location will be the center of the cube.
     *
     * @param x    X Coordinate
     * @param y    Y Coordinate
     * @param z    Z Coordinate
     * @param size The size of the cube to create
     */
    public Cube(float x, float y, float z, float size) {
        // Create the vertices based on the x, y, z, and size.
        vertices = new float[]{
                // Front face
                x - size, y + size, z + size, // 0
                x - size, y - size, z + size, // 1
                x + size, y - size, z + size, // 2
                x + size, y + size, z + size, // 3
                // Back face
                x - size, y + size, z - size, // 4
                x - size, y - size, z - size, // 5
                x + size, y - size, z - size, // 6
                x + size, y + size, z - size, // 7
        };
        // Create the indices for the cube. using the x, y, z, and size.
        indices = new int[] {
                0, 1, 2, 2, 3, 0,  // Front face
                4, 5, 6, 6, 7, 4,  // Back face
                0, 1, 5, 5, 4, 0,  // Bottom face
                2, 3, 7, 7, 6, 2,  // Top face
                0, 3, 7, 7, 4, 0,  // Left face
                1, 2, 6, 6, 5, 1   // Right face
        };

        // Use LWJGL
        vaoId = GL45.glCreateVertexArrays();
        GL45.glBindVertexArray(vaoId);

        vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer vertices_buffer = BufferUtils.createFloatBuffer(vertices.length);
        vertices_buffer.put(vertices).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices_buffer, GL15.GL_STATIC_DRAW);

        eboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, eboId);
        IntBuffer indicesBuffer = BufferUtils.createIntBuffer(indices.length);
        indicesBuffer.put(indices).flip();
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
    }

    public int getVboId() {
        return vboId;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getEboId() {
        return eboId;
    }

    public float[] getVertices() {
        return vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public void draw() {
        GL30.glBindVertexArray(vaoId);
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES, indices.length, GL11.GL_UNSIGNED_INT, 0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
}
