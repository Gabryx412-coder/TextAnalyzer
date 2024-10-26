import javax.swing.SwingUtilities;

/**
 * Classe principale per avviare l'applicazione WordCounter.
 */
public class Main {
    public static void main(String[] args) {
        // Avvia l'applicazione nell'Swing Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            WordCounterApp app = new WordCounterApp();
            app.setVisible(true);
        });
    }
}
