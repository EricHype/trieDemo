import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ericheitmuller on 6/4/17.
 */
public class BisectionalSearchApplication {
    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();

        for(int i=0; i<10; i++){
            if(i % 2 == 0) {
                words.add("two" + i);
            }
            if(i % 3 == 0){
                words.add("three" + i);
            }
            if(i % 5 == 0){
                words.add("five" + i);
            }
        }

        //to do a bisectional search, data must be sorted first
        Collections.sort(words);

        System.out.println("looking words:");
        List<String> prefixedWords = findWordsWithPrefix("three", words);

        System.out.println("found words:");
        for(String word : prefixedWords) {
            System.out.println(word);
        }

        System.out.println("that's all the three words");

        prefixedWords = findWordsWithPrefix("five", words);

        System.out.println("found words:");
        for(String word : prefixedWords) {
            System.out.println(word);
        }

        System.out.println("that's all the five words");


        prefixedWords = findWordsWithPrefix("two", words);

        System.out.println("found words:");
        for(String word : prefixedWords) {
            System.out.println(word);
        }

        System.out.println("that's all the two words");

    }

    public static List<String> findWordsWithPrefix(String prefix, ArrayList<String> words){


        int start = 0;
        int end = words.size()-1;
        int middleIndex = (int)Math.ceil( (end - start) / 2.0);
        int finalIndex = -1;
        while(true){

            if(start == end){
                break;
            }

            if(start + 1 == end){
                if(words.get(start).startsWith(prefix)){
                    finalIndex = start;
                } else {
                    finalIndex = end;
                }
                break;
            }

            //if it's greater than prefix, move up, else move down
            if(prefix.compareTo(words.get(middleIndex)) == 0){ //got lucky
                finalIndex = middleIndex;
                break;
            }

            if(prefix.compareTo(words.get(middleIndex)) > 0){
                start = middleIndex;
            } else {
                end = middleIndex;
            }

            middleIndex = start + (int)Math.ceil((end - start) / 2.0);
        }


        ArrayList<String> retVal = new ArrayList<>();
        if(finalIndex < 0){
            return retVal;
        }


        //from where we found, go forwards until we no longer start with the prefix
        while(words.get(finalIndex).startsWith(prefix)){
            retVal.add(words.get(finalIndex));
            finalIndex++;

            if(finalIndex == words.size()){
                break;
            }
        }

        return retVal;
    }
}
