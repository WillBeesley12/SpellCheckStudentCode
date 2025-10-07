public class Trie {
    // First, make a class for nodes, and create several functions to make it easy to find information later.
    class Node {
        // These are the main two things we want to keep track of
        boolean isWord;
        Node[] next;
        public Node() {
            // For just inserting a node that is NOT the end of a word, set isWord to false.
            this.isWord = false;
            // Create the next ones under this node, and have 255 different options for each character (extended ASCII)
            this.next = new Node[255];
        }
        // These three functions are pretty straightforward - I am now realizing I don't really need them
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
        // This makes sure that it is a full word, not just the starting letters of a word.
        return current.isWord();
    }
}
