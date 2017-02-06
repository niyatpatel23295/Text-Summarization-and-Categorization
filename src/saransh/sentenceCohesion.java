// His system uses five features: 
//1 an indication of whether or not the sentence length is below a specified threshold, 
//2 occurrence of cue words, 
//3 position of the sentence in the text and in the paragraph, 
//4 occurrence of frequent words, 
//5 and occurrence of words in capital letters, 
//  excluding common abbreviations. 
package saransh;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
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
public class sentenceCohesion {

    static String wrds_v[] = new String[500];
    static int scnt_v;

    static String wrds_n[] = new String[500];
    static int scnt_n;

    static String wrds_av[] = new String[500];
    static int scnt_av;

    static String wrds_ad[] = new String[500];
    static int scnt_ad;

    public static void main(String a[]) throws IOException, JWNLException {
        JWNL.initialize(new FileInputStream("/Users/purvank/Documents/ECLIPSE/saaransh/src/saransh/file_properties.xml"));
//        Scanner sc= new Scanner(System.in);
        String text = new String();
//        System.out.println(text);
//        
        Dictionary wn = Dictionary.getInstance();
//        IndexWord word = wn.getIndexWord(POS.NOUN, "apple");
//        
//        Synset senses[] = word.getSenses();
//        for(int sns=0; sns<senses.length; sns++){
//            
//            System.out.println(sns+" "+senses[sns].getGloss()+" ");
//        }

        System.out.println("Enter the text for cohesion : ");

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
        int MainWCount[] = new int[i];
        // This will count the NUMBER OF WORDS in the sentences, starting from
        // sc=0 to the sc=i where sc=SentenceCount.
//        WNData wnd = new WNData();
//        WNData_N wnd_n = new WNData_N();
//        WNData_AV wnd_av = new WNData_AV();
//        WNData_AD wnd_ad = new WNData_AD();

        for (int sc = 0; sc < i; sc++) {
            wordIn.setText(sent[sc]);
            int wStart = wordIn.first();
            int wCount = 0;
            int sCount =0;
            
//            System.out.println("From here!");
            for (int end = wordIn.next(); end != BreakIterator.DONE; wStart = end, end = wordIn.next()) {
               
                Main_Words[sc][wCount] = sent[sc].substring(wStart, end);
//                System.out.println("Count Loop!"+Main_Words[sc][wCount]);
                wOfSent[sc][sCount] = Main_Words[sc][wCount];
                String demo = Main_Words[sc][wCount];
                if (!Main_Words[sc][wCount].equalsIgnoreCase(" ") && !Main_Words[sc][wCount].equalsIgnoreCase(",")
                        && !Main_Words[sc][wCount].equalsIgnoreCase("!") && !Main_Words[sc][wCount].equalsIgnoreCase(".")
                        && !Main_Words[sc][wCount].equalsIgnoreCase("?")) {
//                    System.out.print(Main_Words[sc][wCount] + wCount + " -Main Word");
                    wCount++;
                    sCount++;

                    String wrds[] = getSynsV(demo);
                    int scnt = scnt_v;

                    for (int f = 0; f < scnt; f++) {
                        if (!wrds[f].equalsIgnoreCase(demo)) {
                            wOfSent[sc][sCount] = wrds[f];
//                            System.out.println(wOfSent[sc][sCount] + "V");
                            sCount++;
                        }
                    }

                    String wrds_n[] = getSynsN(demo);
                    int scnt2 = scnt_n;
//                    System.out.println(scnt2);
                    for (int f = 0; f < scnt2; f++) {
                        if (!wrds_n[f].equalsIgnoreCase(demo)) {
                            wOfSent[sc][sCount] = wrds_n[f];
//                            System.out.println(wOfSent[sc][sCount] + "n");
                            sCount++;
                        }
                    }

                    String wrds_av[] = getSynsAV(demo);
                    int scnt3 = scnt_av;
//                    System.out.println(scnt3);
                    
                    for (int f = 0; f < scnt3; f++) {
                        if (!wrds_av[f].equalsIgnoreCase(demo)) {
                            wOfSent[sc][sCount] = wrds_av[f];
//                            System.out.println(wOfSent[sc][sCount] + "av");
                            sCount++;
                        }
                    }
                    String wrds_ad[] = getSynsAD(demo);
                    int scnt4 = scnt_ad;
//                    System.out.println(scnt4);
                    
                    for (int f = 0; f < scnt4; f++) {
                        if (!wrds_ad[f].equalsIgnoreCase(demo)) {
                            wOfSent[sc][sCount] = wrds_ad[f];
//                            System.out.println(wOfSent[sc][sCount] + "ad");
                            sCount++;
                        }
                    }
                }

//                if (!wOfSent[sc][wCount-1].equalsIgnoreCase(" ") && !wOfSent[sc][wCount-1].equalsIgnoreCase(",")
//                        && !wOfSent[sc][wCount-1].equalsIgnoreCase("!") && !wOfSent[sc][wCount-1].equalsIgnoreCase(".")
//                        && !wOfSent[sc][wCount-1].equalsIgnoreCase("?")) {
//                    System.out.print(wOfSent[sc][wCount-1] + (wCount-1) + " ");
//                    int scnt = scnt_v;
//                    String wrds[] = getSynsV(wOfSent[sc][wCount-1]);
//                    for (int f = 0; f < scnt; f++) {
//                        if(!wrds[f].equalsIgnoreCase(wOfSent[sc][wCount])){
//                        wOfSent[sc][wCount] = wrds[f];
//                        System.out.println( wOfSent[sc][wCount]);
//                        wCount++;
//                        }
//                    }
//                }
//                int scnt_n = wnd_n.scnt_n;
//                String wrds_n[] = wnd_n.getSynsN(wOfSent[sc][wCount]);
//                for (int f = 0; f < scnt_n; f++) {
//                    wOfSent[sc][wCount] = wrds_n[f];
//                    System.out.println(" Syn:" + wOfSent[sc][wCount]);
//                    if (!wOfSent[sc][wCount].equalsIgnoreCase(" ") && !wOfSent[sc][wCount].equalsIgnoreCase(",")
//                            && !wOfSent[sc][wCount].equalsIgnoreCase("!") && !wOfSent[sc][wCount].equalsIgnoreCase(".")
//                            && !wOfSent[sc][wCount].equalsIgnoreCase("?")) {
//                        System.out.print(wOfSent[sc][wCount] + wCount + " ");
//                        wCount++;
//                    }
//                }
            }

//            int scnt_ad = wnd_ad.scnt_ad;
//            String wrds_ad[] = wnd_ad.getSynsAD(wOfSent[sc][wCount]);
//            for (int f = 0; f < scnt_ad; f++) {
//                wOfSent[sc][wCount] = wrds_ad[f];
//                System.out.println(" Syn:" + wOfSent[sc][wCount]);
//                if (!wOfSent[sc][wCount].equalsIgnoreCase(" ") && !wOfSent[sc][wCount].equalsIgnoreCase(",")
//                        && !wOfSent[sc][wCount].equalsIgnoreCase("!") && !wOfSent[sc][wCount].equalsIgnoreCase(".")
//                        && !wOfSent[sc][wCount].equalsIgnoreCase("?")) {
//                    System.out.print(wOfSent[sc][wCount] + wCount + " ");
//                    wCount++;
//                }
//            }
//
//            int scnt_av = wnd_av.scnt_av;
//            String wrds_av[] = wnd_av.getSynsAv(wOfSent[sc][wCount]);
//            for (int f = 0; f < scnt_av; f++) {
//                wOfSent[sc][wCount] = wrds_av[f];
//                System.out.println(" Syn:" + wOfSent[sc][wCount]);
//                if (!wOfSent[sc][wCount].equalsIgnoreCase(" ") && !wOfSent[sc][wCount].equalsIgnoreCase(",")
//                        && !wOfSent[sc][wCount].equalsIgnoreCase("!") && !wOfSent[sc][wCount].equalsIgnoreCase(".")
//                        && !wOfSent[sc][wCount].equalsIgnoreCase("?")) {
//                    System.out.print(wOfSent[sc][wCount] + wCount + " ");
//                    wCount++;
//                }
//            }

            System.out.println();
            MainWCount[sc]=wCount;
            WordCount[sc] = sCount;
            System.out.println(sc+"-"+MainWCount[sc]+" & "+WordCount[sc]);
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
                    } else {
                        Stemmer s = new Stemmer();
                        char[] wInChar = wOfSent[sc][wc].toCharArray();
                        int wlen = wInChar.length;
                        for (int c = 0; c < wlen; c++) {
                            s.add(Character.toLowerCase(wInChar[c]));
                        }
                        s.stem();
                        wOfSent[sc][wc] = s.toString();
                    }
                }

            }
            int pr;
            for (pr = 0; pr < WordCount[sc]; pr++) {

                System.out.print(wOfSent[sc][pr] + " ");
            }
            System.out.println(pr);
        }

//      Here we'll create the LINK-MATRIX etween all the sentences for the simple repetation only.
//      With COMLEX REPETATION, we can do lot better summarization.
//      In the COMPLEX REPETATION we count the links between the sentences by considering the 
//      morphological forms, synonyms,antonyms etc of the word. 
        for (int out_s = 0; out_s < i; out_s++) {

            for (int wc = 0; wc < WordCount[out_s]; wc++) {

                for (int in_s = out_s + 1; in_s < i; in_s++) {

                    for (int iwc = 0; iwc < WordCount[in_s]; iwc++) {

                        if (wOfSent[out_s][wc].equalsIgnoreCase(wOfSent[in_s][iwc])) {

                            link[out_s][in_s]++;
                            link[in_s][out_s]++;
                        }

                    }
                }
            }
        }

//      Now we'll count the another feature "TOPICOPENING" for the sentences.
        int TopicOpn[] = new int[i];
        int WCForTO[] = new int[i];
        String SentForTO[] = new String[i];
        String WordsForTO[][] = new String[i][5000]; // array index updated from 500 to 5000
        for (int asn = 0; asn < i; asn++) {

            SentForTO[asn] = sent[asn];
            WCForTO[asn] = WordCount[asn];
            for (int asn_w = 0; asn_w < WCForTO[asn]; asn_w++) {

                WordsForTO[asn][asn_w] = wOfSent[asn][asn_w];
            }
        }
        int check = 0;

        for (int out_s = 0; out_s < i; out_s++) {

            for (int wc = 0; wc < WCForTO[out_s]; wc++) {
                check = 0;
                for (int in_s = out_s + 1; in_s < i; in_s++) {

                    for (int iwc = 0; iwc < WCForTO[in_s]; iwc++) {

                        if (WordsForTO[out_s][wc].equalsIgnoreCase(WordsForTO[in_s][iwc])) {

                            check = 1;
                            System.out.print(WordsForTO[out_s][wc] + " ");
                            for (int del = iwc; del < WCForTO[in_s]; del++) {

                                WordsForTO[in_s][del] = WordsForTO[in_s][del + 1];
                            }
                            WCForTO[in_s]--;
                            iwc--;               //becoz we,ve deleted, we have to go baack to the previuos index.
                            break;

                        }

                    }
                }
                if (check == 1) {
                    TopicOpn[out_s]++;
                }

            }
        }

//      Calculation for the TopicClosing Score of the sentence is done below.  
        int TopicCls[] = new int[i];
        int WCForTC[] = new int[i];
        String SentForTC[] = new String[i];
        String WordsForTC[][] = new String[i][5000]; // array index updated from 500 to 5000
        for (int asn = 0; asn < i; asn++) {

            SentForTC[asn] = sent[asn];
            WCForTC[asn] = WordCount[asn];
            for (int asn_w = 0; asn_w < WCForTC[asn]; asn_w++) {

                WordsForTC[asn][asn_w] = wOfSent[asn][asn_w];
            }
        }

        int cls_sent;
        int chkTC = 0;
        for (int out_s = 0; out_s < i; out_s++) {
            cls_sent = out_s;
            for (int wc = 0; wc < WCForTC[out_s]; wc++) {
                chkTC = 0;
                for (int in_s = out_s + 1; in_s < i; in_s++) {

                    for (int iwc = 0; iwc < WCForTC[in_s]; iwc++) {

                        if (WordsForTC[out_s][wc].equalsIgnoreCase(WordsForTC[in_s][iwc])) {

                            chkTC = 1;
                            cls_sent = in_s;
                            for (int del = iwc; del < WCForTC[in_s]; del++) {

                                WordsForTC[in_s][del] = WordsForTC[in_s][del + 1];
                            }
                            WCForTC[in_s]--;
                            iwc--;

                        }

                    }
                }
                if (chkTC == 1) {
                    System.out.println(WordsForTC[out_s][wc] + " ");
                    TopicCls[cls_sent]++;
                }
            }
        }

//      Displaying the TopicOpening Score of the sentence.  
        for (int pr = 0; pr < i; pr++) {

            System.out.println(pr + "TOpn: " + TopicOpn[pr] + " TCls: " + TopicCls[pr]);

        }

//      Here we'll display the LINK-MATRIX between the sentences.
//      LINK-MATRIX will be like :
//         s2   s3   s4 ...
//      s1 01   02   03
//      s2      12   13
//      s3           23
//      ...
        int bond[][] = new int[i][i];
        System.out.println("Link-Matrix is as given below :");
        for (int out_s = 0; out_s < i; out_s++) {       // This will start from the very first sentence.      
            for (int in_s = 0; in_s < i; in_s++) {      //This will start from the second sentence.

                System.out.print(link[out_s][in_s] + " ");
                if (link[out_s][in_s] > 1) {

                    bond[out_s][in_s] = 1;
                    bond[in_s][out_s] = 1;
                } else {
                    bond[out_s][in_s] = 0;
                }
            }
            System.out.println();
        }

        System.out.println("Bond-Matrix is as given below :");
        for (int out_s = 0; out_s < i; out_s++) {       // This will start from the very first sentence.      

            for (int in_s = 0; in_s < i; in_s++) {      //This will start from the second sentence.

                System.out.print(bond[out_s][in_s] + " ");

            }
            System.out.println();
        }

//      Here we'll find the most bonded sentence i.e. CENTRAL SENTENCE.
//      For that, we'll do the summation of the bonds of the sentence with the other sentences.
        int SumBond[] = new int[i];
        for (int s = 0; s < i; s++) {

            for (int in_s = 0; in_s < i; in_s++) {

                SumBond[s] += bond[s][in_s];
            }
//            System.out.println(SumBond[s]);
        }
        
        int Total_Bond=1;
        int TotalCls = 1;
        int TotalOpn=1;
        for(int s=0; s<i; s++){
        
            Total_Bond += SumBond[s];
            TotalCls += TopicCls[s];
            TotalOpn += TopicOpn[s];
         }

//      This section will do the sorting according to the SCORE of all the sentences.
//      So this will store the sentence from most Scored sentence to least bonded sentence.   
        float score[] = new float[i];
        System.out.println();
        for (int pr = 0; pr < i; pr++) {

//            System.out.println(pr + " " + SumBond[pr] + " TO" + TopicOpn[pr] + " TC" + TopicCls[pr] + " " + sent[pr] + " ");
            score[pr] = ((float) (SumBond[pr])) + ((float) (TopicOpn[pr])) + ((float) (TopicCls[pr]));
//            System.out.println(score[pr]);
        }
        float sum_score =0 ;
        float [] f_score = new float[i];
        float max = score[0];
        for(int z=0;z<score.length;z++)
        {
        	if(score[z] > max)
        	{
        		max = score[z];
        	}
        }
        /*
        for(int sc=0; sc<i; sc++){
        
            sum_score += score[sc];
            System.out.println("For sum score: "+sc +" "+score[sc]);
        }
        System.out.println("Sum Score: "+ sum_score);
        */
        for(int sc=0; sc<i; sc++){
        
            f_score[sc] = score[sc]/max;
            System.out.println(f_score[sc]+",");
        
        }

        float temp;
        int k;
        String sentTemp = new String();
        for (int out = 0; out < i; out++) {
            temp = f_score[out];
            sentTemp = sent[out];
            k = out;
            for (int in = out; in < i; in++) {

                if (temp < f_score[in]) {
                    temp = f_score[in];
                    sentTemp = sent[in];
                    k = in;

                }
            }
            sent[k] = sent[out];
            sent[out] = sentTemp;
            f_score[k] = f_score[out];
            f_score[out] = temp;
        }
   
        
/*
//      Here is your summary FELLA!
        int sum_sents = 0;
        if (i % 3 == 0) {
            sum_sents = i / 3;
        } else {
            sum_sents = (i / 3) + 1;
        }
        System.out.println("Summary :");
        for (int pr = 0; pr < (int) sum_sents; pr++) {
            System.out.println(pr + ":" + f_score[pr] + ":" + sent[pr] + " ");
        }
*/
    }
//    String wrds_v[] = new String[500];
//    int scnt_v;

    public static String[] getSynsV(String ar) {
        scnt_v = 0;

        try {

            Dictionary wn = Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.VERB, ar);
//            System.out.println(ar);
            Synset senses[] = word.getSenses();
//            System.out.println("Method");
            for (Synset sense : senses) {

                for (Word word1 : sense.getWords()) {
                    wrds_v[scnt_v] = word1.getLemma().replace("_", " ");
//                    System.out.println(wrds_v[scnt_v]);
                    scnt_v++;

                }

            }
        } catch (Exception npe) {

//            System.out.println("No data found!");
        }
        return wrds_v;
    }

    public static String[] getSynsN(String ar) {
        scnt_n = 0;

        try {

            Dictionary wn = Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.NOUN, ar);
//            System.out.println(ar);
            Synset senses[] = word.getSenses();
//            System.out.println("Method");
            for (Synset sense : senses) {

                for (Word word1 : sense.getWords()) {
                    wrds_n[scnt_n] = word1.getLemma().replace("_", " ");
                    if (!wrds_n[scnt_n].equalsIgnoreCase(ar)) {
//                    System.out.println(wrds_n[scnt_n]);
//                    System.out.println(wrds_v[scnt_v]);
                        scnt_n++;
                    }
//                    System.out.println(scnt_n);    
                }

            }
        } catch (Exception npe) {

//            System.out.println("No data found!");
        }
        return wrds_n;
    }

    public static String[] getSynsAV(String ar) {
        scnt_av = 0;

        try {

            Dictionary wn = Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.ADJECTIVE, ar);
//            System.out.println(ar);
            Synset senses[] = word.getSenses();
//            System.out.println("Method");
            for (Synset sense : senses) {

                for (Word word1 : sense.getWords()) {
                    wrds_av[scnt_av] = word1.getLemma().replace("_", " ");
//                    System.out.println(wrds_av[scnt_av]);
                    if (!wrds_av[scnt_av].equalsIgnoreCase(ar)) {
                        scnt_av++;
                    }

                }

            }
        } catch (Exception npe) {

//            System.out.println("No data found!");
        }
        return wrds_av;
    }

    public static String[] getSynsAD(String ar) {
        scnt_ad = 0;

        try {

            Dictionary wn = Dictionary.getInstance();

            IndexWord word = wn.getIndexWord(POS.ADVERB, ar);
//            System.out.println(ar);
            Synset senses[] = word.getSenses();
//            System.out.println("Method");
            for (Synset sense : senses) {

                for (Word word1 : sense.getWords()) {
                    wrds_ad[scnt_ad] = word1.getLemma().replace("_", " ");
//                    System.out.println(wrds_ad[scnt_ad]);
                    if (!wrds_ad[scnt_ad].equalsIgnoreCase(ar)) {
                        scnt_ad++;
                    }
                }

            }
        } catch (Exception npe) {

//            System.out.println("No data found!");
        }
        return wrds_ad;
    }
}



