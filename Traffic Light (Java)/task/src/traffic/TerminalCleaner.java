package traffic;

import java.io.IOException;

public class TerminalCleaner {
    public static void cleanTerminal() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        } catch (InterruptedException | IOException ignored) {}
    }
}
