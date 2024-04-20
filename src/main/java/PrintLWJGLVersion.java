import org.lwjgl.Version;

public class PrintLWJGLVersion {
    public static void main(String[] args) {
        // Print the LWJGL version
        String version = Version.getVersion();
        if (version != null) {
            System.out.printf("LWJGL version: %s\n", version);
        } else {
            System.out.println("LWJGL version not found");
        }
    }
}
