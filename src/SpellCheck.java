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
        // First, initialize our TST and the Seen arraylist.
        // Trie data = new Trie();
        TST data = new TST();
        ArrayList seen = new ArrayList();
        // Add all the words in the dictionary to the TST
        for (int i = 0; i < dictionary.length; i++) {
            data.insert(dictionary[i]);
        }
        // Now, we go through each word in the text and check if it is the dictionary.
        for (String word : text) {
            if (!data.lookup(word)) {
                // Add the word that is not in the dictionary to our seen arraylist.
                seen.add(word);
                // Also add this word to the dictionary to avoid future repeats.
                data.insert(word);
            }
        }
        // Convert the arraylist to an array so we can return it
        String[] answer = new String[seen.size()];
        for (int i = 0; i < seen.size(); i++) {
            answer[i] = (String) seen.get(i);
        }
        return answer;
    }
}
