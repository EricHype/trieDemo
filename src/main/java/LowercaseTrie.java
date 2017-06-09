import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ericheitmuller on 6/2/17.
 */
public class LowercaseTrie {
    Node[] topLevelNodes = new Node[26];

    public void addWord(String word){
        char[] letters = word.toCharArray();
        int index = letters[0] - 'a';
        if(topLevelNodes[index] == null){
            topLevelNodes[index] = new Node(letters);
            return;
        }

        topLevelNodes[index].addChars(letters);
    }

    public List<String> getAllWordsByPrefix(String prefix){

        ArrayList<String> retVal = new ArrayList<>();
        Node startingNode = getNodeAt(prefix.toCharArray());
        startingNode.getAllWordsFromChildren(retVal, prefix);

        return retVal;
    }

    public Node getNodeAt(char[] letters){

        int index = letters[0] - 'a';

        if(letters.length == 1){
            return topLevelNodes[index];
        }

        return topLevelNodes[index].navigateTo(letters);
    }

}
