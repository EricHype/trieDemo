import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by ericheitmuller on 6/2/17.
 */
public class Node {
    boolean isTerminationOfWord;
    public char value;
    public Node[] children = new Node[26];

    public Node(char[] remainingChars){

        this.value = remainingChars[0];

        addChars(remainingChars);
    }

    public void addChars(char[] remainingChars){
        //here we'll assume text is in lower case so to get the index of the next char we'll do it minus a

        if(remainingChars.length == 1){
            isTerminationOfWord = true;
            return;
        }

        int index = remainingChars[1] - 'a';
        if(children[index] == null){
            children[index] = new Node(Arrays.copyOfRange(remainingChars, 1, remainingChars.length));
        } else {
            children[index].addChars(Arrays.copyOfRange(remainingChars, 1, remainingChars.length));
        }
    }


    public Node navigateTo(char[] letters){
        if(letters.length == 1){
            return this;
        }

        int index = letters[1] - 'a'; // index of the next letter
        if(children[index] == null){
            return null;
        }
        return children[index].navigateTo(Arrays.copyOfRange(letters, 1, letters.length));
    }

    public void getAllWordsFromChildren(ArrayList<String> retVal, String prefix) {

        if(isTerminationOfWord){
            retVal.add(prefix);
        }

        for(int i=0; i<children.length; i++){
            if(children[i] == null){
                continue;
            }

            String newPrefix = prefix + children[i].value;
            children[i].getAllWordsFromChildren(retVal, newPrefix);
        }
    }
}
