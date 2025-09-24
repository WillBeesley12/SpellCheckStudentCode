import java.util.*;
/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: William Beesley
 * */

public class SpellCheck {
    public String[] checkWords(String[] text, String[] dictionary) {
        // Convert the dictionary to a set, create a set to store misspelled words.
        Set<String> Dictionary_Set = new HashSet<>(Arrays.asList(dictionary));
        Set<String> seen = new HashSet<>();
        // Need an arraylist as well to keep the same order of misspelled words.
        ArrayList<String> misspelled = new ArrayList<>();
        // Iterate through the text, looking for any misspelled words that haven't been seen yet.
        for (String word : text) {
            if (!Dictionary_Set.contains(word) && !seen.contains(word)) {
                seen.add(word);
                misspelled.add(word);
            }
        }
        // Convert the arraylist into an array, and return it.
        String[] answer = new String[misspelled.size()];
        for (int i = 0; i < misspelled.size(); i++) {
            answer[i] = misspelled.get(i);
        }
        return answer;
    }
}
