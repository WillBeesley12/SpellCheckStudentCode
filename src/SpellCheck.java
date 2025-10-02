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
// Class of Nodes specifically for TSTs. Main difference is storing of the previous node value.
class TSTNode {
    // These are the things I want to store: the letter, is it a word, and then the left middle and right nodes
    char letter;
    boolean isWord;
    TSTNode left;
    TSTNode middle;
    TSTNode right;
    // Initializes the first node
    public TSTNode(char c) {
        this.letter = c;
        this.isWord = false;
        this.left = null;
        this.middle = null;
        this.right = null;
    }
    // These two are self-explanatory
    public boolean isWord() {
        return isWord;
    }
    public void setWord() {
        this.isWord = true;
    }
}

// Class for TST
class TST {
    TSTNode root;
    // To start a new TST, create the root node of the TST. I am using M to make the TST more balanced eventually
    public TST() {
        this.root = new TSTNode('m');
    }
    // This function inserts a new word into the TST
    public void insert(String s) {
        // Start at the top with the root node
        TSTNode current = root;
        // Iterate through each letter of the word except the last, as we don't need to check it (just add it)
        for (int i = 0; i < s.length() - 1; i++) {
            // Convert the character to an index
            char c = s.charAt(i);
            // If the letter is equal to the node, go straight down and place the next letter there
            // Also set the new current node to the one we just made
            if (current.letter == c) {
                current.middle = new TSTNode(s.charAt(i + 1));
                current = current.middle;
            }
            // This case makes us go right
            else if (current.letter < c) {
                current.right = new TSTNode(s.charAt(i + 1));
                current = current.right;
            }
            // This case makes us go left
            else {
                current.left = new TSTNode(s.charAt(i + 1));
                current = current.left;

            }
        }
        // For the last letter in the word, set the isWord boolean to True.
        current.setWord();
    }
    public boolean lookup(String s) {
        // Again, start with the root node
        TSTNode current = root;
        // Index through each character of the word, checking if the next character exists
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
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
            TST data = new TST();
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
