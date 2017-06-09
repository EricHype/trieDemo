import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Created by ericheitmuller on 6/2/17.
 */

public class NodeTests {

    @Test
    public void nodeOfSingleLetterShouldTerminateAndHaveNoChildren(){
        char[] values = { 'a' };
        Node node = new Node(values);

        assertEquals(node.children.length, 26);
        assertEquals(node.children[0], null);
        assertEquals(node.isTerminationOfWord, true);
    }

    @Test
    public void shouldHaveChildWithSecondCharacter(){
        char[] values = { 'a', 'a' };
        Node node = new Node(values);

        assertEquals(node.children.length, 26);
        assertNotEquals(node.children[0], null);
        assertEquals(node.isTerminationOfWord, false);
        assertEquals(node.children[0].isTerminationOfWord, true);
    }

    @Test
    public void shouldAddSecondChildForCommonRoot(){
        char[] word1 = { 'a', 'a' };
        char[] word2 = { 'a', 'b' };

        Node node = new Node(word1);
        node.addChars(word2);

        assertNotEquals(node.children[0], null);
        assertNotEquals(node.children[1], null);
        assertEquals(node.isTerminationOfWord, false);
        assertEquals(node.children[0].value, 'a');
        assertEquals(node.children[1].value, 'b');
        assertEquals(node.children[0].isTerminationOfWord, true);
        assertEquals(node.children[1].isTerminationOfWord, true);
    }

    @Test
    public void shouldWordsWithoutOverwriting(){
        char[] word1 = "hobo".toCharArray();
        char[] word2 = "home".toCharArray();
        char[] word3 = "hotel".toCharArray();

        Node node = new Node(word1);
        node.addChars(word2);
        node.addChars(word3);

        int oIndex = 'o' - 'a';

        for(int i=0; i< 26; i++){
            if(i == oIndex ) {
                assertNotEquals(node.children[i], null);
                continue;
            }
            assertEquals(node.children[i], null);
        }

        Node oNode = node.children[oIndex];

        //b, m and t should be filled, others null
        int bIndex = 'b' - 'a';
        int mIndex = 'm' - 'a';
        int tIndex = 't' - 'a';

        for(int i=0; i< 26; i++){
            if(i == bIndex || i == mIndex || i == tIndex ) {
                assertNotEquals(oNode.children[i], null);
                continue;
            }
            assertEquals(oNode.children[i], null);
        }
    }

    @Test
    public void shouldNavigateToExistingNode(){
        char[] word1 = "hobo".toCharArray();
        char[] word2 = "home".toCharArray();
        char[] word3 = "hotel".toCharArray();

        Node node = new Node(word1);
        node.addChars(word2);
        node.addChars(word3);

        Node navigatedTo = node.navigateTo("ho".toCharArray());
        assertEquals(navigatedTo.value, 'o');
    }

    @Test
    public void shouldReturnNullForMissingSearchString(){
        char[] word1 = "hobo".toCharArray();
        char[] word2 = "home".toCharArray();
        char[] word3 = "hotel".toCharArray();

        Node node = new Node(word1);
        node.addChars(word2);
        node.addChars(word3);

        Node navigatedTo = node.navigateTo("abc".toCharArray());
        assertEquals(navigatedTo, null);
    }


    @Test
    public void shouldGetAllChildrenOfCommonRoot(){
        char[] word1 = "aaaa".toCharArray();
        char[] word2 = "aabb".toCharArray();
        char[] word3 = "acdd".toCharArray();

        Node node = new Node(word1);
        node.addChars(word2);
        node.addChars(word3);

        ArrayList<String> matchedWords = new ArrayList<>();
        assertEquals(3, matchedWords.size());
    }

}
