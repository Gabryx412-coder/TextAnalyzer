import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * Classe che gestisce l'interfaccia utente dell'applicazione WordCounter.
 */
public class WordCounterApp extends JFrame {
    private JTextArea textArea;
    private JTextArea resultArea;
    private JButton countButton;
    private JButton clearButton;
    private JButton loadButton;
    private JButton saveButton;
    private FileHandler fileHandler;

    public WordCounterApp() {
        setTitle("Word Counter");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        fileHandler = new FileHandler();

        // Layout
        setLayout(new BorderLayout());

        // Area di testo per l'input
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        // Pannello dei pulsanti
        JPanel buttonPanel = new JPanel();
        countButton = new JButton("Count Words");
        clearButton = new JButton("Clear");
        loadButton = new JButton("Load File");
        saveButton = new JButton("Save File");
        buttonPanel.add(countButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Area di testo per i risultati
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.EAST);

        // Aggiungi azioni ai pulsanti
        countButton.addActionListener(new CountAction());
        clearButton.addActionListener(e -> clearText());
        loadButton.addActionListener(e -> loadFile());
        saveButton.addActionListener(e -> saveFile());
    }

    /**
     * Azione per contare le parole e visualizzare i risultati.
     */
    private class CountAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String text = textArea.getText();
            if (text.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter some text.");
                return;
            }

            WordCounter wordCounter = new WordCounter(text);
            int wordCount = wordCounter.countWords();
            int charCount = wordCounter.countCharacters(false);
            int charCountNoSpaces = wordCounter.countCharacters(true);
            int sentenceCount = wordCounter.countSentences();
            double avgWordLength = wordCounter.averageWordLength();

            StringBuilder result = new StringBuilder();
            result.append("Words: ").append(wordCount).append("\n")
                  .append("Characters (with spaces): ").append(charCount).append("\n")
                  .append("Characters (without spaces): ").append(charCountNoSpaces).append("\n")
                  .append("Sentences: ").append(sentenceCount).append("\n")
                  .append("Average Word Length: ").append(String.format("%.2f", avgWordLength)).append("\n")
                  .append("\nWord Frequency:\n");

            Map<String, Integer> frequencyMap = wordCounter.wordFrequency();
            for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }

            resultArea.setText(result.toString());
        }
    }

    /**
     * Cancella il testo nell'area di input e nei risultati.
     */
    private void clearText() {
        textArea.setText("");
        resultArea.setText("");
    }

    /**
     * Carica un file di testo e lo mostra nell'area di input.
     */
    private void loadFile() {
        String content = fileHandler.loadFile();
        if (content != null) {
            textArea.setText(content);
        }
    }

    /**
     * Salva il contenuto dell'area di risultati in un file di testo.
     */
    private void saveFile() {
        String content = resultArea.getText();
        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No results to save.");
            return;
        }
        fileHandler.saveFile(content);
    }
}
