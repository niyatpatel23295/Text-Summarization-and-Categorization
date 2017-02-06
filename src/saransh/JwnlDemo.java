package saransh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.Scanner;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.dictionary.Dictionary;

/**
 *
 * @author Shubham
 */
public class JwnlDemo {

    public static void main(String a[]) throws IOException, JWNLException {
        JWNL.initialize(new FileInputStream("F:\\Saaransh\\src\\saaransh\\file_properties.xml"));
//        Scanner sc= new Scanner(System.in);
        String text = new String();
//        System.out.println(text);
//     
        
        Dictionary wn= Dictionary.getInstance();
//        IndexWord word = wn.getIndexWord(POS.NOUN, "apple");
//        Synset senses[] = word.getSenses();
        try{
        IndexWord w = wn.getIndexWord(POS.VERB, "game");
        String lemma = w.getLemma();
         System.out.println(lemma);
        }catch(NullPointerException npe){
            System.out.println("No wrod found!");
        }
//        for(int sns=0; sns<senses.length; sns++){
//            
//            System.out.println(sns+" "+senses[sns].getGloss()+" ");
//        }
       
        
        }
}