import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

public class SimpleWindow {
    public static void main(String[] args) {
        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Create a window
        long window = GLFW.glfwCreateWindow(800, 600, "Purple Window", 0, 0);
        if (window == 0) {
            throw new IllegalStateException("Failed to create window");
        }

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(window);

        // Create capabilities
        GL.createCapabilities();

        // Set the clear color to purple
        GL11.glClearColor(0.5f, 0.0f, 0.5f, 1.0f);

        // Make the window visible
        GLFW.glfwShowWindow(window);

        // Run the rendering loop until the user has attempted to close the window
        while (!GLFW.glfwWindowShouldClose(window)) {
            // Clear the framebuffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            // Swap the color buffers
            GLFW.glfwSwapBuffers(window);

            // Poll for window events
            GLFW.glfwPollEvents();
        }

        // Terminate GLFW
        GLFW.glfwTerminate();
    }
}