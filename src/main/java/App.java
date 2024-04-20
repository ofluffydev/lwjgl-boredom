import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.*;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

@SuppressWarnings("unused")
public class App {

    private int vaoId;
    private int vboId;
    private ShaderProgram shaderProgram;
    private Camera camera;

    public static void main(String[] args) {
        try {
            new App().run();
        } catch (Exception e) {
            System.out.println("An error occurred");
            throw new RuntimeException(e);
        }
    }

    private void run() {

        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        /*
         Create the window, this line creates a window that is 800 pixels wide and 600 pixels tall, with the title "Hello World!"
         The last two parameters are used to specify the monitor to open the window on, and the window to share resources with.
         If there is only one monitor, the first parameter should be 0, and the second parameter should be 0.
         Attempting to use a non-existent monitor will cause GLFW to throw an exception.
        */
        long window = GLFW.glfwCreateWindow(800, 600, "Hello World!", 0, 0);
        GLFW.glfwMakeContextCurrent(window);
        GL.createCapabilities();

        // Print OpenGL version
        System.out.println("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));

        shaderProgram = new ShaderProgram();
        camera = new Camera();

        float[] vertices = {-0.5f, 0.5f, 0.0f, -0.5f, -0.5f, 0.0f, 0.5f, -0.5f, 0.0f, 0.5f, 0.5f, 0.0f};

        vaoId = GL45.glCreateVertexArrays();
        GL30.glBindVertexArray(vaoId);
        vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        FloatBuffer vertices_buffer = BufferUtils.createFloatBuffer(vertices.length);
        vertices_buffer.put(vertices).flip();
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, vertices_buffer, GL15.GL_STATIC_DRAW);

        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);

        String vertexShaderCode;
        String fragmentShaderCode;
        try {
            vertexShaderCode = loadShaderSource("src/main/java/vertex_shader.glsl");
            fragmentShaderCode = loadShaderSource("src/main/java/fragment_shader.glsl");
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load shader source", e);
        }

        shaderProgram.attachVertexShader(vertexShaderCode);
        shaderProgram.attachFragmentShader(fragmentShaderCode);

        shaderProgram.link();

        // Configure GLFW
        GLFW.glfwDefaultWindowHints(); // Set default window hints
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE); // Set the window to be hidden
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE); // Set the window to be resizable



        /*
         If the window wasn't created right, it's value will be 0.

         This is because The glfwCreateWindow method returns a long value, which is a unique identifier for the
         created window. If the window creation is successful, this identifier will be a non-zero value.
         However, if the window creation fails for any reason (such as invalid parameters or insufficient resources),
         this method will return 0.

         TODO: Replace this with actual error handling.
        */
        if (window == 0) {
            throw new IllegalStateException("Failed to create window");
        }


        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Make the window visible
        GLFW.glfwShowWindow(window);


        /*
         Set the clear color
         In OpenGL, the glClearColor function is used to specify the color that the screen is cleared to before
         drawing the next frame. This is typically done at the start of each frame to clear any leftover pixels
         from the previous frame. The color is specified as four floating-point values representing the red, green,
         blue, and alpha (transparency) components of the color. These values are typically in the range of 0.0
         to 1.0, where 0.0 represents none of those components and 1.0 represents the maximum amount.
        */
        GL11.glClearColor(0.5f, 0.0f, 0.5f, 1.0f);

        // Run the rendering loop until the user has attempted to close the window
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Poll for window events. The key callback above will only be invoked during this call.
            GLFW.glfwPollEvents();

            // Clear the framebuffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            // Bind the shader program and vao, then draw the triangle
            GL20.glUseProgram(shaderProgram.getId());
            GL30.glBindVertexArray(vaoId);
            GL20.glEnableVertexAttribArray(0);
            GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, 3);

            // Unbind the vao and shader program
            GL20.glDisableVertexAttribArray(0);
            GL30.glBindVertexArray(0);
            GL20.glUseProgram(0);

            // Swap the color buffers
            GLFW.glfwSwapBuffers(window);
        }
        // Delete the VAO, VBO, and shader program
        GL30.glDeleteVertexArrays(vaoId);
        GL15.glDeleteBuffers(vboId);
        shaderProgram.delete();
    }

    public String loadShaderSource(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
