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
public class WNData_AD {
    int scnt_ad;
    String wrds[] = new String[500];

    public WNData_AD() throws FileNotFoundException, JWNLException {
        JWNL.initialize(new FileInputStream("F:\\Saaransh\\src\\saaransh\\file_properties.xml"));

    }
    
        public String[] getSynsAD(String ar) {
        
        try{
            
            
            Dictionary wn= Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.ADVERB, ar);        
            
            Synset senses[] = word.getSenses();
            for(Synset sense: senses){
                
                for (Word word1: sense.getWords()) {
                    wrds[scnt_ad]=word1.getLemma().replace("_", " ");
                    scnt_ad++;
                    
                }
                
                
            }
        }
        catch(Exception npe){

//            System.out.println("No data found!");
        }
    return wrds;
    }
        
        
        
      
}
