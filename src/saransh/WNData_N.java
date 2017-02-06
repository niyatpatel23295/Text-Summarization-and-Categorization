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
public class WNData_N {
    int scnt_n;
    String wrds[] = new String[500];

    public WNData_N() throws FileNotFoundException, JWNLException {
        JWNL.initialize(new FileInputStream("F:\\Saaransh\\src\\saaransh\\file_properties.xml"));

    }
    
        public String[] getSynsN(String ar) {
        
        try{
            
            
            Dictionary wn= Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.NOUN, ar);        
            
            Synset senses[] = word.getSenses();
            for(Synset sense: senses){
                
                for (Word word1: sense.getWords()) {
                    wrds[scnt_n]=word1.getLemma().replace("_", " ");
                    scnt_n++;
                }
                
                
            }
        }
        catch(Exception npe){

//            System.out.println("No data found!");
        }
    return wrds;
    }
        
        
        
      
}
