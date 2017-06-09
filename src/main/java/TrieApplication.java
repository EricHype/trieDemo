import java.util.List;

/**
 * Created by ericheitmuller on 6/2/17.
 */
public class TrieApplication {
    public static void main(String[] args) {
        LowercaseTrie trie = new LowercaseTrie();

        trie.addWord("april");
        trie.addWord("may");
        trie.addWord("june");
        trie.addWord("july");
        trie.addWord("home");
        trie.addWord("hobo");
        trie.addWord("holy");
        trie.addWord("heck");
        trie.addWord("hammer");
        trie.addWord("host");
        trie.addWord("hostel");

        List<String> hoWords = trie.getAllWordsByPrefix("ho");

        for(String word: hoWords){
            System.out.println(word);
        }
    }
}
