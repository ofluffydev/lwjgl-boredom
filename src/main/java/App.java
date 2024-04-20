import static java.lang.System.out;

public class App {
    public static void main(String[] args) {
        try {
            new Game().run();
        } catch (Exception e) {
            out.println("An error occurred");
            throw new RuntimeException(e);
        }
    }
}
