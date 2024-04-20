import static org.lwjgl.opengl.GL11.*;

public class PrintOpenGLVersion {
    public static void main(String[] args) {
        // Print OpenGL version using LWJGL
        String version = glGetString(GL_VERSION);
        if (version != null)
            System.out.println("OpenGL version: " + version);
        else
            System.out.println("OpenGL version not found");
    }
}
