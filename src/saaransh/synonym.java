/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saaransh;

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
public class synonym {
    int scnt;
    /*
    int scnt1;
    int scnt2;
    int scnt3;
  */
    String wrds[] = new String[500];

    public synonym() throws FileNotFoundException, JWNLException {
        JWNL.initialize(new FileInputStream("/Users/purvank/Desktop/SAARANSH PROJECT/Software Packages/jwnl14-rc2/config/file_properties.xml"));

    }
    
        public String[] getSyns(String ar) 
        {
        	Dictionary wn= Dictionary.getInstance();
        	IndexWord word;
        	IndexWord wordav;
        	IndexWord wordad;
        	IndexWord wordn;
        try
        {
             word = wn.getIndexWord(POS.VERB, ar);  
             Synset senses[] = word.getSenses();
             for(Synset sense: senses)
             {
                 
                 for (Word word1: sense.getWords()) 
                 {
                     wrds[scnt]=word1.getLemma().replace("_", " ");
                    // System.out.println(wrds[scnt]);
                     scnt++;
                     
                 }             
             }
        }
        catch(Exception npe)
        {

            System.out.println("delete");
        }
        //System.out.println(scnt);
        try
        {
            wordn = wn.getIndexWord(POS.NOUN, ar);  
            Synset sensesn[] = wordn.getSenses();
            for(Synset sense: sensesn)
            { 
                    
                    for (Word word1: sense.getWords()) 
                    {
                        wrds[scnt]=word1.getLemma().replace("_", " ");
                       // System.out.println(wrds[scnt]);
                        scnt++;
                        
                    }
                    
                    
                }

        }
        catch(Exception npe)
        {

            System.out.println("No data found!");
        }
        //System.out.println(scnt);
        try
        {
            wordav = wn.getIndexWord(POS.ADJECTIVE, ar); 
            Synset sensesav[] = wordav.getSenses();
            for(Synset sense: sensesav)
            {
                    
                    for (Word word1: sense.getWords()) 
                    {
                        wrds[scnt]=word1.getLemma().replace("_", " ");
                        //System.out.println(wrds[scnt]);
                        scnt++;
                        
                    }
                    
                    
                }

        }
        catch(Exception npe)
        {

            System.out.println("No data found!");
        }
        //System.out.println(scnt);
        try
        {
        wordad = wn.getIndexWord(POS.ADVERB, ar);  
        Synset sensesad[] = wordad.getSenses();
        for(Synset sense: sensesad)
        {
                
                for (Word word1: sense.getWords()) 
                {
                    wrds[scnt]=word1.getLemma().replace("_", " ");
                    //System.out.println(wrds[scnt]);
                    scnt++;
                    
                }
                
          
            }

        }
        catch(Exception npe)
        {

            System.out.println("No data found!");
        }
        //System.out.println(scnt);
            /*
            Synset senses[] = word.getSenses();
            Synset sensesn[] = wordn.getSenses();
            Synset sensesav[] = wordav.getSenses();
            Synset sensesad[] = wordad.getSenses();
            */
           /* 
            for(Synset sense: senses)
            {
                
                for (Word word1: sense.getWords()) 
                {
                    wrds[scnt]=word1.getLemma().replace("_", " ");
                    System.out.println(wrds[scnt]);
                    scnt++;
                    
                }
                
                
            }
            */
        
        
                    
             return wrds;
        }
        
       /* catch(Exception npe){

            System.out.println("No data found!");
        }
        */
        public int getCnt(){return scnt;}
       /*public int getCnt1(){return scnt1;}
        public int getCnt2(){return scnt2;}
        public int getCnt3(){return scnt3;}
        */
        
        public static void main(String [] r) throws FileNotFoundException, JWNLException{
        
            synonym wn = new synonym();
            String w[] = new String[100];
            w = wn.getSyns("deluge");
            int cnt = wn.getCnt();
            //String words[] = new String[cnt];
            /* for(int h=0; h<cnt; h++){
                System.out.println(w[h]);
            }
            */
            for(int i=0;i<cnt;i++)
        	{
        		int k=0;
        		for(int j=0;j<cnt;j++)
        		{
        			if((w[i].equals(w[j])))
        			{
        				k++;
        				if(k>1)
        				{
        					w[j] = "delete";
        				}
        			}
        		}
        	}
        	int l=0;
        	//int indexes[]= new int[100];
        	for(int i=0;i<cnt;i++)
        	{
        		if(!(w[i].equals("delete")))
        		{
        			l++;
        		}
        	}
        	int indexes[] = new int[l];
        	/*
        	int g=0;
        	for(int i=0;i<words.length;i++)
        	{
        		if(!(words[i].equals("delete")))
        		{
        		 indexes[g] = 	
        		}
        	}
        	*/
        	int g=0;
        	String newword[] = new String[l];
        	/*for(int i=0;i<newword.length;i++)
        	{
        		for(int j=0;j<words.length;j++)
        		{
        			if(!(words[j].equals("delete")))
        			{
        				newword
        			}
        		}
        	}
        	*/
        	for(int i=0;i<cnt;i++)
        	{
        		if(!(w[i].equals("delete")))
        		{
        			newword[g]=w[i];
        			g++;
        		}
        	}
        	for(int i=0;i<newword.length;i++)
        	{
        		System.out.println(newword[i]);
        	}
          }
        }
        
        
        
      

