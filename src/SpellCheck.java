import java.util.*;
/**
 * Spell Check
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: William Beesley
 * */

// First, make a class for nodes, and create several functions to make it easy to find information later.
class Node {
    // These are the main two things we want to keep track of
    boolean isWord;
    Node[] next;
    public Node() {
        // For just inserting a node that is NOT the end of a word, set isWord to false.
        this.isWord = false;
        // Create the next ones under this node, and have 255 different options for each character (or 3 for TST)
        // this.next = new Node[255];
        this.next = new Node[3];
    }
    // These three functions are pretty straightforward
    public boolean isWord() {
        return isWord;
    }
    public void setWord() {
        this.isWord = true;
    }
    public Node[] getNext() {
        return next;
    }
}

// Now we will also make a class for the Trie, utilizing the Node class we just made
class Trie {
    Node root;
    // To start a new Trie, create the root node of the Trie
    public Trie() {
        this.root = new Node();
    }
    // This function inserts a new word into the Trie
    public void insert(String s) {
        // Start at the top with the root node
        Node current = root;
        // Iterate through each letter of the word
        for (int i = 0; i < s.length(); i++) {
            // Convert the character to an index from 0 to 255 (want to include all of extended ASCII)
            int index = s.charAt(i);
            // If the next letter doesn't exist in the Trie yet, make a new node for it
            if (current.next[index] == null) {
                current.next[index] = new Node();
            }
            // Now grab the next node for the next iteration of the characters in the word
            current = current.next[index];
        }
        // For the last letter in the word, set the isWord boolean to True.
        current.setWord();
    }
    public boolean lookup(String s) {
        // Again, start with the root node
        Node current = root;
        // Index through each character of the word, checking if the next character exists
        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i);
            // If the next node doesn't exist, we can stop the function and just return false
            if (current.next[index] == null) {
                return false;
            }
            // Now grab the next node for the next iteration of the characters in the word
            current = current.next[index];
        }
        // If it gets through every character, return the boolean stored under the last character in the word.
        // This makes sure that it is a word, not just the start of a word.
        return current.isWord();
    }
}


    public class SpellCheck {
        public String[] checkWords(String[] text, String[] dictionary) {
            // First, initialize our Trie and the Seen arraylist.
            Trie data = new Trie();
            ArrayList seen = new ArrayList();
            // Add all the words in the dictionary to the Trie
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
            String[] answer = new String[seen.size()];
            for (int i = 0; i < seen.size(); i++) {
                answer[i] = (String) seen.get(i);
            }
            return answer;
        }
    }
