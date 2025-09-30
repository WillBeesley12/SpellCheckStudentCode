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
        // Convert the dictionary to a set.
        Set<String> Dictionary_Set = new HashSet<>(Arrays.asList(dictionary));
        // Create a Linked set to store misspelled words AND keep the order of the words.
        LinkedHashSet<String> seen = new LinkedHashSet<>();
        // Iterate through the text, looking for any misspelled words that haven't been seen yet.
        for (String word : text) {
            if (!Dictionary_Set.contains(word) && !seen.contains(word)) {
                seen.add(word);
            }
        }
        // Convert the seen set into an array
        return (seen.toArray(new String[0]));
    }
}
