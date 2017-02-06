/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saransh;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.IndexWord;
import net.didion.jwnl.data.POS;
import net.didion.jwnl.data.Synset;
import net.didion.jwnl.data.Word;
import net.didion.jwnl.dictionary.Dictionary;

/**
 *
 * @author Shubham
 */
public class jwnl {

    public static void main(String[] ar) {
        int scnt = 0;
        String wrds[] = new String[500];
        try {

            JWNL.initialize(new FileInputStream("F:\\Saaransh\\src\\saaransh\\file_properties.xml"));

            Dictionary wn = Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.NOUN, "have");

            Synset senses[] = word.getSenses();
            for (Synset sense : senses) {

                for (Word word1 : sense.getWords()) {
                    
                    System.out.println(scnt+word1.getLemma().replace("_", " "));
                    wrds[scnt] = word1.getLemma().replace("_", " ");
                    
                    System.out.println(wrds[scnt]);
                    scnt++;
                }

            }
        } catch (Exception npe) {

            System.out.println("No data found!"+npe);
        }

    }

}
