package saransh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;

/**
 *
 * @author Shubham
 */
public class SentenceCategory {

    static int comp_sc=0;
    static int sports_sc = 0;
    static int space_sc = 0;
    static int rec_sc=0;
    static int baseball_sc = 0;
    static int hocky_sc = 0;
    static int motorcycle_sc = 0;
    public static void main(String a[]) throws IOException {
        //        Scanner sc= new Scanner(System.in);
        String text = new String();
//        System.out.println(text);
//        

        System.out.println("Enter the text for category : ");

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String line1;
        text = "";
//        line2 = stdin.readLine();
        while ((line1 = stdin.readLine()) != null && line1.length() != 0) {
            text = text.concat(line1);
//           System.out.println(text);
        }
//        System.out.println(text + "\nDone!");

        BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(text);
        String sent[] = new String[500];
        int start = bi.first();

//        int end = boundary.next();
//          end != BreakIterator.DONE;
//          start = end, end = boundary.next()) 
        int i = 0; //It is the number of sentences in the text.
        for (int end = bi.next(); end != BreakIterator.DONE; start = end, end = bi.next()) {

            sent[i] = text.substring(start, end);
            System.out.println((i + 1) + "-" + sent[i]);  //It will store the all the SENTENCES.
            i++;
        }

        BreakIterator wordIn = BreakIterator.getWordInstance();
        String Main_Words[][] = new String[i][500];
        String wOfSent[][] = new String[i][1000]; //It will store the WORDS of the sentence(i).
        int WordCount[] = new int[i];

        for (int sc = 0; sc < i; sc++) {
            wordIn.setText(sent[sc]);
            int wStart = wordIn.first();
            int wCount = 0;

//            System.out.println("From here!");
            for (int end = wordIn.next(); end != BreakIterator.DONE; wStart = end, end = wordIn.next()) {

                wOfSent[sc][wCount] = sent[sc].substring(wStart, end);
//                System.out.println("Count Loop!"+Main_Words[sc][wCount]);

                if (!wOfSent[sc][wCount].equalsIgnoreCase(" ") && !wOfSent[sc][wCount].equalsIgnoreCase(",")
                        && !wOfSent[sc][wCount].equalsIgnoreCase("!") && !wOfSent[sc][wCount].equalsIgnoreCase(".")
                        && !wOfSent[sc][wCount].equalsIgnoreCase("?")) {
//                    System.out.print(Main_Words[sc][wCount] + wCount + " -Main Word");
                    wCount++;

                }

            }

            System.out.println();

            WordCount[sc] = wCount;
            System.out.println(sc + "-" + WordCount[sc]);
        }
        for (int e = 0; e < i; e++) {
            System.out.println("WordCount: " + WordCount[e]);
        }
        String[] stopwords = {"a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost",
            "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "amoungst", "amount", "an", "and",
            "another", "any", "anyhow", "anyone", "anything", "anyway", "anywhere", "are", "around", "as", "at", "back", "be",
            "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside",
            "besides", "between", "beyond", "bill", "both", "bottom", "but", "by", "call", "can", "cannot", "cant", "co", "con",
            "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight",
            "either", "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything",
            "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly",
            "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "goes", "had", "has", "hasnt", "have",
            "he", "his", "her", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him",
            "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its",
            "itself", "keep", "last", "latter", "unless", "latterly", "least", "less", "ltd", "made", "many", "may", "me",
            "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself",
            "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not",
            "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others",
            "otherwise", "our", "ours", "ourselves", "out", "over", "own", "part", "per", "perhaps", "please", "put", "rather", "re",
            "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere",
            "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still",
            "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there",
            "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those",
            "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve",
            "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever",
            "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether",
            "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would",
            "yet", "you", "your", "yours", "yourself", "yourselves", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "1.", "2.", "3.",
            "are", "many", "the", "4.", "5.", "6.", "11", "7.", "8.", "9.", "12", "13", "14", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "terms", "CONDITIONS", "conditions", "values",
            "interested.", "care", "sure", ".", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "{", "}", "[", "]", ":", ";", ",",
            "<", ".", ";", ">", "/", "?", "_", "-", "+", "=", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
            "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "contact", "grounds", "buyers", "tried", "said,", "plan",
            "value", "principle.", "forces", "sent:", "is,", "was", "like", "discussion", "tmus", "diffrent.", "got", "layout", "area.",
            "thanks", "thankyou", "hello", "bye", "rise", "fell", "fall", "psqft.", "http://", "km", "miles"};

        int link[][] = new int[i][i];

        for (int sc = 0; sc < i; sc++) {

            System.out.println("Sentence :" + sc);
            for (int wc = 0; wc < WordCount[sc]; wc++) {

                for (String stopword : stopwords) {
                    if (wOfSent[sc][wc].equalsIgnoreCase(stopword)) {
                        int del;
                        for (del = wc; del < WordCount[sc]; del++) {

                            wOfSent[sc][del] = wOfSent[sc][del + 1];
                        }
                        WordCount[sc]--;
                        wc--;               //becoz we,ve deleted, we have to go baack to the previuos index.
                        break;
                    }
                }
                
            }
        }
            for (int sc = 0; sc < i; sc++) {

            System.out.println("Sentence :" + sc);
            for (int wc = 0; wc < WordCount[sc]; wc++) {
                
                
                
                CatComputer cc = new CatComputer();
                comp_sc += cc.getCompScore(wOfSent[sc][wc]);
                
                CatSpace cs = new CatSpace();
                space_sc += cs.getSpaceScore(wOfSent[sc][wc]);
                
                CatSportsBaseball csb = new CatSportsBaseball();
                baseball_sc += csb.getBaseballScore(wOfSent[sc][wc]);
                
                CatSportsHocky csh = new CatSportsHocky();
                hocky_sc += csh.getHockyScore(wOfSent[sc][wc]);
            
                CatSportsMotorCycle csm = new CatSportsMotorCycle();
                motorcycle_sc += csm.getMotorCycleScore(wOfSent[sc][wc]);
            }
                
            
            int pr;
            for (pr = 0; pr < WordCount[sc]; pr++) {

                System.out.print(wOfSent[sc][pr] + " ");
            }
            System.out.println(pr);
            System.out.println("category score"+comp_sc);
                System.out.println("space "+space_sc);
            }
        }
    }
