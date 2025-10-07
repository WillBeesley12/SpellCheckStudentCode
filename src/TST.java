public class TST {
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
    TSTNode root;
    // To start a new TST, create the root node of the TST. I am using M to make the TST more balanced eventually
    public TST() {
        this.root = new TSTNode('m');
    }
    // This function inserts a new word into the TST
    public void insert(String s) {
        // Start at the top with the root node
        TSTNode current = root;
        // Iterate through each letter of the word UNTIL that letter exists within our word in the TST
        // So each letter needs to be a middle node somewhere so we can accept that letter when looking up the word
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            // If the letter is equal to the node, go straight down and place the next letter there
            // Also set the new current node to the one we just made
            if (current.letter == c) {
                if (current.middle == null) {
                    current.middle = new TSTNode(s.charAt(i));
                }
                i++;
                current = current.middle;
            }
            // This case makes us go right
            else if (current.letter < c) {
                if (current.right == null) {
                    current.right = new TSTNode(s.charAt(i));
                }
                current = current.right;
            }
            // This case makes us go left
            else {
                if (current.left == null) {
                    current.left = new TSTNode(s.charAt(i));
                }
                current = current.left;
            }
        }
        // For the last letter in the word, set the isWord boolean to True.
        current.setWord();
    }
    public boolean lookup(String s) {
        // Again, start with the root node
        TSTNode current = root;
        // Index through each character of the word (same way as last time), checking if the next character exists
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            // If the letter is equal to the node, go straight down and see if the letter is there
            // Also set the new current node to the one we just checked
            if (current.letter == c) {
                if (current.middle == null) {
                    return false;
                }
                i++;
                current = current.middle;
            }
            // This case makes us go right
            else if (current.letter < c) {
                if (current.right == null) {
                    return false;
                }
                current = current.right;
            }
            // This case makes us go left
            else {
                if (current.left == null) {
                    return false;
                }
                current = current.left;
            }
        }
        // If it gets through every character, return the boolean stored under the last character in the word.
        // This makes sure that it is a word, not just the start of a word.
        return current.isWord();
    }
}