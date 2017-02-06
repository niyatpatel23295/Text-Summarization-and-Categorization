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
public class WNData {
    int scnt;
    String wrds[] = new String[500];

    public WNData() throws FileNotFoundException, JWNLException {
        JWNL.initialize(new FileInputStream("/Users/purvank/Desktop/SAARANSH PROJECT/Software Packages/jwnl14-rc2/config/file_properties.xml"));

    }
    
        public String[] getSyns(String ar) {
        
        try{
            
            
            Dictionary wn= Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.VERB, ar);        
            
            Synset senses[] = word.getSenses();
            for(Synset sense: senses){
                
                for (Word word1: sense.getWords()) {
                    wrds[scnt]=word1.getLemma().replace("_", " ");
                    System.out.println(wrds[scnt]);
                    scnt++;
                    
                }
                
                
            }
        }
        catch(Exception npe){

            System.out.println("No data found!");
        }
    return wrds;
    }
        public int getCnt(){return scnt;}
        
        public static void main(String [] r) throws FileNotFoundException, JWNLException{
        
            WNData wn = new WNData();
            
            String w[] = new String[100];
            w = wn.getSyns("deluge");
            int cnt = wn.getCnt();
            for(int h=0; h<cnt; h++){
                System.out.println(w[h]);
            }
        }
        
        
        
      
}
