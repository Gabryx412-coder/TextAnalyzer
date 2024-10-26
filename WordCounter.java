import java.util.HashMap;
import java.util.Map;

/**
 * Classe per gestire il conteggio delle parole e delle statistiche del testo.
 */
public class WordCounter {
    private String text;

    public WordCounter(String text) {
        this.text = text;
    }

    /**
     * Conta il numero di parole nel testo.
     */
    public int countWords() {
        if (text == null || text.trim().isEmpty()) return 0;
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    /**
     * Conta il numero di caratteri nel testo.
     */
    public int countCharacters(boolean excludeSpaces) {
        if (text == null) return 0;
        if (excludeSpaces) {
            return text.replace(" ", "").length();
        } else {
            return text.length();
        }
    }

    /**
     * Conta il numero di frasi nel testo.
     */
    public int countSentences() {
        if (text == null || text.trim().isEmpty()) return 0;
        String[] sentences = text.split("[.!?]+");
        return sentences.length;
    }

    /**
     * Calcola la lunghezza media delle parole.
     */
    public double averageWordLength() {
        int totalWords = countWords();
        if (totalWords == 0) return 0;
        return (double) countCharacters(false) / totalWords;
    }

    /**
     * Analizza la frequenza delle parole nel testo.
     */
    public Map<String, Integer> wordFrequency() {
        Map<String, Integer> frequencyMap = new HashMap<>();
        String[] words = text.toLowerCase().split("\\W+"); // parole in minuscolo, split per caratteri non alfanumerici
        for (String word : words) {
            if (!word.isEmpty()) {
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        return frequencyMap;
    }
}
