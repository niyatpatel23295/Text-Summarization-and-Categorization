/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package saransh;

/**
 *
 * @author Shubham
 */
public class CatJournalism {
    
    
    int score;
    String[] journalism = { "journalism", "backstory", "written", "newspaper", "blackout", "situation", 
                            "journalists", "journalist", "official", "officially", "chequebook", 
                            "people", "newspapers", "stories", "story", "information", "famous", "ciculation", 
                            "citizen", "recording", "reporting", "records", "record", "reports", "report", 
                            "magazines", "magazine", "discussion", "debate", "news", "events", "event", 
                            "public", "interest", "interesting", "breaking", "Times", "India", "Hindu",
                            "DivyaBhaskar", "Sandesh", "Gujarat", "Samachar", "Mirror", "Ahmedabad", "Nation",
                            "comtibute", "editorial", "article", "Latest", "Entertainment", "Top", "coverage", 
                            "cover", "articles", "edit", "editor", "embed", "publish", "government", "headlines", 
                            "headline", "human", "journalistic", "columnist", "correspondent", "court", "hit", 
                            "CNN" ,"reporter", "affairs", "front-page", "front", "page", "gossip", "hack", "hot", 
                            "newsworthy", "agency", "muckranking", "conference", "summit", "investigate", "lead", 
                            "press", "summit", "gallary", "box", "baron", "newshound", "scoop", "syndicate", 
                            "desk", "spoiler", "syndicated", "secretary", "release", "silly", "season", "Association", 
                            "voices", "voice" };
    
     
    
    int getJournalismScore(String word){
    
        for (String jnm1 : journalism) {
    
//            System.out.print(comp1 +" "+word);
            if(jnm1.equalsIgnoreCase(word)){
                score= 1;
                break;
            }else{
            
                score= 0;
            }
        }
        return score;
    }
    
}
