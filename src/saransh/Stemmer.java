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
/*

   Porter stemmer in Java. The original paper is in

       Porter, 1980, An algorithm for suffix stripping, Program, Vol. 14,
       no. 3, pp 130-137,

   See also http://www.tartarus.org/~martin/PorterStemmer

   History:

   Release 1

   Bug 1 (reported by Gonzalo Parra 16/10/99) fixed as marked below.
   The words 'aed', 'eed', 'oed' leave k at 'a' for step 3, and b[k-1]
   is then out outside the bounds of b.

   Release 2

   Similarly,

   Bug 2 (reported by Steve Dyrdahl 22/2/00) fixed as marked below.
   'ion' by itself leaves j = -1 in the test for 'ion' in step 5, and
   b[j] is then outside the bounds of b.

   Release 3

   Considerably revised 4/9/00 in the light of many helpful suggestions
   from Brian Goetz of Quiotix Corporation (brian@quiotix.com).

   Release 4

*/

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
  * Stemmer, implementing the Porter Stemming Algorithm
  *
  * The Stemmer class transforms a word into its root form.  The input
  * word can be provided a character at time (by calling add()), or at once
  * by calling one of the various stem(something) methods.
  */

class Stemmer
{  private char[] b;
   private int i,     /* offset into b */
               i_end, /* offset to end of stemmed word */
               j, k;
   private static final int INC = 50;
                     /* unit of size whereby b is increased */
   public Stemmer()
   {  b = new char[INC];
      i = 0;
      i_end = 0;
   }

   /**
    * Add a character to the word being stemmed.  When you are finished
    * adding characters, you can call stem(void) to stem the word.
    */

   public void add(char ch)
   {  if (i == b.length)
      {  char[] new_b = new char[i+INC];
         for (int c = 0; c < i; c++) new_b[c] = b[c];
         b = new_b;  // b will be from the memory address of the new-b.
      }
      b[i++] = ch;
   }


   /** Adds wLen characters to the word being stemmed contained in a portion
    * of a char[] array. This is like repeated calls of add(char ch), but
    * faster.
    */

   public void add(char[] w, int wLen)
   {  if (i+wLen >= b.length)
      {  char[] new_b = new char[i+wLen+INC];
         for (int c = 0; c < i; c++) new_b[c] = b[c];
         b = new_b;
      }
      for (int c = 0; c < wLen; c++) b[i++] = w[c];
   }

   /**
    * After a word has been stemmed, it can be retrieved by toString(),
    * or a reference to the internal buffer can be retrieved by getResultBuffer
    * and getResultLength (which is generally more efficient.)
    */
   public String toString() { return new String(b,0,i_end); }

   /**
    * Returns the length of the word resulting from the stemming process.
    */
   public int getResultLength() { return i_end; }

   /**
    * Returns a reference to a character buffer containing the results of
    * the stemming process.  You also need to consult getResultLength()
    * to determine the length of the result.
    */
   public char[] getResultBuffer() { return b; }

   /* cons(i) is true <=> b[i] is a consonant. */

   private final boolean cons(int i)
   {  switch (b[i])
      {  case 'a': case 'e': case 'i': case 'o': case 'u': return false;
         case 'y': return (i==0) ? true : !cons(i-1);
         default: return true;
      }
   }

   /* m() measures the number of consonant sequences between 0 and j. if c is
      a consonant sequence and v a vowel sequence, and <..> indicates arbitrary
      presence,

         <c><v>       gives 0
         <c>vc<v>     gives 1
         <c>vcvc<v>   gives 2
         <c>vcvcvc<v> gives 3
         ....
   */

   private final int m()
   {  int n = 0;
      int i = 0;
      while(true)
      {  if (i > j) return n;
         if (! cons(i)) break; i++;
      }
      i++;
      while(true)
      {  while(true)
         {  if (i > j) return n;
               if (cons(i)) break;
               i++;
         }
         i++;
         n++;
         while(true)
         {  if (i > j) return n;
            if (! cons(i)) break;
            i++;
         }
         i++;
       }
   }

   /* vowelinstem() is true <=> 0,...j contains a vowel */

   private final boolean vowelinstem()
   {  int i; for (i = 0; i <= j; i++) if (! cons(i)) return true;
      return false;
   }

   /* doublec(j) is true <=> j,(j-1) contain a double consonant. */

   private final boolean doublec(int j)
   {  if (j < 1) return false;
      if (b[j] != b[j-1]) return false;
      return cons(j);
   }

   /* cvc(i) is true <=> i-2,i-1,i has the form consonant - vowel - consonant
      and also if the second c is not w,x or y. this is used when trying to
      restore an e at the end of a short word. e.g.

         cav(e), lov(e), hop(e), crim(e), but
         snow, box, tray.

   */

   private final boolean cvc(int i)
   {  if (i < 2 || !cons(i) || cons(i-1) || !cons(i-2)) return false;
      {  int ch = b[i];
         if (ch == 'w' || ch == 'x' || ch == 'y') return false;
      }
      return true;
   }

   private final boolean ends(String s)
   {  int l = s.length();
      int o = k-l+1;
      if (o < 0) return false;
      for (int i = 0; i < l; i++) if (b[o+i] != s.charAt(i)) return false;
      j = k-l;
      return true;
   }

   /* setto(s) sets (j+1),...k to the characters in the string s, readjusting
      k. */

   private final void setto(String s)
   {  int l = s.length();
      int o = j+1;
      for (int i = 0; i < l; i++) b[o+i] = s.charAt(i);
      k = j+l;
   }

   /* r(s) is used further down. */

   private final void r(String s) { if (m() > 0) setto(s); }

   /* step1() gets rid of plurals and -ed or -ing. e.g.

          caresses  ->  caress
          ponies    ->  poni
          ties      ->  ti
          caress    ->  caress
          cats      ->  cat

          feed      ->  feed
          agreed    ->  agree
          disabled  ->  disable

          matting   ->  mat
          mating    ->  mate
          meeting   ->  meet
          milling   ->  mill
          messing   ->  mess

          meetings  ->  meet

   */

   private final void step1()
   {  if (b[k] == 's')
      {  if (ends("sses")) k -= 2; else
         if (ends("ies")) setto("i"); else
         if (b[k-1] != 's') k--;
      }
      if (ends("eed")) { if (m() > 0) k--; } else
      if ((ends("ed") || ends("ing")) && vowelinstem())
      {  k = j;
         if (ends("at")) setto("ate"); else
         if (ends("bl")) setto("ble"); else
         if (ends("iz")) setto("ize"); else
         if (doublec(k))
         {  k--;
            {  int ch = b[k];
               if (ch == 'l' || ch == 's' || ch == 'z') k++;
            }
         }
         else if (m() == 1 && cvc(k)) setto("e");
     }
   }

   /* step2() turns terminal y to i when there is another vowel in the stem. */

   private final void step2() { if (ends("y") && vowelinstem()) b[k] = 'i'; }

   /* step3() maps double suffices to single ones. so -ization ( = -ize plus
      -ation) maps to -ize etc. note that the string before the suffix must give
      m() > 0. */

   private final void step3() { if (k == 0) return; /* For Bug 1 */ switch (b[k-1])
   {
       case 'a': if (ends("ational")) { r("ate"); break; }
                 if (ends("tional")) { r("tion"); break; }
                 break;
       case 'c': if (ends("enci")) { r("ence"); break; }
                 if (ends("anci")) { r("ance"); break; }
                 break;
       case 'e': if (ends("izer")) { r("ize"); break; }
                 break;
       case 'l': if (ends("bli")) { r("ble"); break; }
                 if (ends("alli")) { r("al"); break; }
                 if (ends("entli")) { r("ent"); break; }
                 if (ends("eli")) { r("e"); break; }
                 if (ends("ousli")) { r("ous"); break; }
                 break;
       case 'o': if (ends("ization")) { r("ize"); break; }
                 if (ends("ation")) { r("ate"); break; }
                 if (ends("ator")) { r("ate"); break; }
                 break;
       case 's': if (ends("alism")) { r("al"); break; }
                 if (ends("iveness")) { r("ive"); break; }
                 if (ends("fulness")) { r("ful"); break; }
                 if (ends("ousness")) { r("ous"); break; }
                 break;
       case 't': if (ends("aliti")) { r("al"); break; }
                 if (ends("iviti")) { r("ive"); break; }
                 if (ends("biliti")) { r("ble"); break; }
                 break;
       case 'g': if (ends("logi")) { r("log"); break; }
   } }

   /* step4() deals with -ic-, -full, -ness etc. similar strategy to step3. */

   private final void step4() { switch (b[k])
   {
       case 'e': if (ends("icate")) { r("ic"); break; }
                 if (ends("ative")) { r(""); break; }
                 if (ends("alize")) { r("al"); break; }
                 break;
       case 'i': if (ends("iciti")) { r("ic"); break; }
                 break;
       case 'l': if (ends("ical")) { r("ic"); break; }
                 if (ends("ful")) { r(""); break; }
                 break;
       case 's': if (ends("ness")) { r(""); break; }
                 break;
   } }

   /* step5() takes off -ant, -ence etc., in context <c>vcvc<v>. */

   private final void step5()
   {   if (k == 0) return; /* for Bug 1 */ switch (b[k-1])
       {  case 'a': if (ends("al")) break; return;
          case 'c': if (ends("ance")) break;
                    if (ends("ence")) break; return;
          case 'e': if (ends("er")) break; return;
          case 'i': if (ends("ic")) break; return;
          case 'l': if (ends("able")) break;
                    if (ends("ible")) break; return;
          case 'n': if (ends("ant")) break;
                    if (ends("ement")) break;
                    if (ends("ment")) break;
                    /* element etc. not stripped before the m */
                    if (ends("ent")) break; return;
          case 'o': if (ends("ion") && j >= 0 && (b[j] == 's' || b[j] == 't')) break;
                                    /* j >= 0 fixes Bug 2 */
                    if (ends("ou")) break; return;
                    /* takes care of -ous */
          case 's': if (ends("ism")) break; return;
          case 't': if (ends("ate")) break;
                    if (ends("iti")) break; return;
          case 'u': if (ends("ous")) break; return;
          case 'v': if (ends("ive")) break; return;
          case 'z': if (ends("ize")) break; return;
          default: return;
       }
       if (m() > 1) k = j;
   }

   /* step6() removes a final -e if m() > 1. */

   private final void step6()
   {  j = k;
      if (b[k] == 'e')
      {  int a = m();
         if (a > 1 || a == 1 && !cvc(k-1)) k--;
      }
      if (b[k] == 'l' && doublec(k) && m() > 1) k--;
   }

   /** Stem the word placed into the Stemmer buffer through calls to add().
    * Returns true if the stemming process resulted in a word different
    * from the input.  You can retrieve the result with
    * getResultLength()/getResultBuffer() or toString().
    */
   public void stem()
   {  k = i - 1;
      if (k > 1) { step1(); step2(); step3(); step4(); step5(); step6(); }
      i_end = k+1; i = 0;
   }

   /** Test program for demonstrating the Stemmer.  It reads text from a
    * a list of files, stems each word, and writes the result to standard
    * output. Note that the word stemmed is expected to be in lower case:
    * forcing lower case must be done outside the Stemmer class.
    * Usage: Stemmer file-name file-name ...
    */
   public static void main(String[] args)
   {
      String word[] = new String[500];
        String mword[] = new String[500];
        int fr=0;
      Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the text to tokenize: ");
        String text = sc.nextLine();
        
        
        String [] stopwords = { "a", "about", "above", "above", "across","after", "afterwards", "again", "against", "all", "almost",
				"alone", "along", "already", "also", "although", "always","am", "among", "amongst", "amoungst", "amount", "an", "and",
				"another", "any", "anyhow", "anyone", "anything", "anyway","anywhere", "are", "around", "as", "at", "back", "be",
				"became", "because", "become", "becomes", "becoming", "been","before", "beforehand", "behind", "being", "below", "beside",
				"besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con",
				"could", "couldnt", "cry", "de", "describe", "detail", "do","done", "down", "due", "during", "each", "eg", "eight",
				"either", "eleven", "else", "elsewhere", "empty", "enough","etc", "even", "ever", "every", "everyone", "everything",
				"everywhere", "except", "few", "fifteen", "fify", "fill","find", "fire", "first", "five", "for", "former", "formerly",
				"forty", "found", "four", "from", "front", "full", "further","get", "give", "go", "goes", "had", "has", "hasnt", "have",
				"he", "his", "her", "hence", "her", "here", "hereafter","hereby", "herein", "hereupon", "hers", "herself", "him",
				"himself", "his", "how", "however", "hundred", "ie", "if","in", "inc", "indeed", "interest", "into", "is", "it", "its",
				"itself", "keep", "last", "latter", "unless", "latterly","least", "less", "ltd", "made", "many", "may", "me",
				"meanwhile", "might", "mill", "mine", "more", "moreover","most", "mostly", "move", "much", "must", "my", "myself",
				"name", "namely", "neither", "never", "nevertheless", "next","nine", "no", "nobody", "none", "noone", "nor", "not",
				"nothing", "now", "nowhere", "of", "off", "often", "on","once", "one", "only", "onto", "or", "other", "others",
				"otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re",
				"same", "see", "seem", "seemed", "seeming", "seems", "serious","several", "she", "should", "show", "side", "since", "sincere",
				"six", "sixty", "so", "some", "somehow", "someone","something", "sometime", "sometimes", "somewhere", "still",
				"such", "system", "take", "ten", "than", "that", "the","their", "them", "themselves", "then", "thence", "there",
				"thereafter", "thereby", "therefore", "therein", "thereupon","these", "they", "thickv", "thin", "third", "this", "those",
				"though", "three", "through", "throughout", "thru", "thus","to", "together", "too", "top", "toward", "towards", "twelve",
				"twenty", "two", "un", "under", "until", "up", "upon", "us","very", "via", "was", "we", "well", "were", "what", "whatever",
				"when", "whence", "whenever", "where", "whereafter", "whereas","whereby", "wherein", "whereupon", "wherever", "whether",
				"which", "while", "whither", "who", "whoever", "whole", "whom","whose", "why", "will", "with", "within", "without", "would",
				"yet", "you", "your", "yours", "yourself", "yourselves", "1","2", "3", "4", "5", "6", "7", "8", "9", "10", "1.", "2.", "3.",
				"4.", "5.", "6.", "11", "7.", "8.", "9.", "12", "13", "14","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
				"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X","Y", "Z", "terms", "CONDITIONS", "conditions", "values",
				"interested.", "care", "sure", ".", "!", "@", "#", "$", "%","^", "&", "*", "(", ")", "{", "}", "[", "]", ":", ";", ",",
				"<", ".", ">", "/", "?", "_", "-", "+", "=", "a", "b", "c","d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
				"p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z","contact", "grounds", "buyers", "tried", "said,", "plan",
				"value", "principle.", "forces", "sent:", "is,", "was", "like","discussion", "tmus", "diffrent.", "got", "layout", "area.",
				"thanks", "thankyou", "hello", "bye", "rise", "fell", "fall","psqft.", "http://", "km", "miles" };

        
        StringTokenizer st = new StringTokenizer(text,",.!? []/\\:=");
       // String[] wrd = input.split(" ");
            
        int wcnt=0;   //wcnt is the wordcount of the text given input.
        int mcnt=-1;
        while(st.hasMoreTokens()){
            word[wcnt] = st.nextToken();
            int f = 0;
            
            
          for (String stopword : stopwords) {
              if (word[wcnt].equalsIgnoreCase(stopword)) {
                  f=1;
                  break;
              }
          }
          if(f==0){
                
              mcnt++;  
              mword[mcnt] = word[wcnt];  //mcnt = count for the mwords i.e. meaningfull words.
                                
                }
        wcnt++;
    }
    
    String stemd[] = new String[mcnt+1];
        
    for(int u=0;u<mcnt+1;u++){
         
        System.out.print(mword[u]+ " ");     
        char[] w = mword[u].toCharArray();
        Stemmer s = new Stemmer();
        int wlen = w.length;
//      System.out.println("error" + wlen);
//      for (int i = 0; i <wlen; i++)
//           while(true){  
//              
//              int ch = w[i];
//              if (Character.isLetter((char) ch)){
//                 
//                 int j = 0;
//                 while(true){
//
//                    ch = Character.toLowerCase((char) ch);
//                    w[j] = (char) ch;
//                    if (j < 500) j++;
//                    ch = w[i];
//                    System.out.println("ch");
//                    if (!Character.isLetter((char) ch)){
                          for (int c = 0; c < wlen; c++) {
                            s.add(Character.toLowerCase(w[c]));
                           }
//                        
//                        /* or, to test add(char[] w, int j) */
//                        /* s.add(w, j); */
//                        
                        s.stem();
                        
                            
                            
                            //System.out.println(stemd.length);
                            /* and now, to test toString() : */
                            
                            stemd[fr] = s.toString();
                            /* to test getResultBuffer(), getResultLength() : */
                            /* u = new String(s.getResultBuffer(), 0, s.getResultLength()); */
                            
                            System.out.println("Stemmed word: "+stemd[fr]+fr);
                            fr++;    
    }
    
    //To count the frequency of the words ...
    String wordWF[] = new String[mcnt+1]; //wordWF = word-with-frequency.
    int d1=0;
    Integer d2[] = new Integer[mcnt+1];
    
    for(int fw=0; fw<mcnt+1; fw++){       //fw = frequent-word.
        d2[fw]=1;
        int flag=0;
        int cmp=fw+1;
        while(cmp<mcnt+1){    
//        System.out.println(fw+"["+cmp+"\\"+d2[fw]+"-"+stemd[fw]+stemd[cmp]);
            if(stemd[fw].equals(stemd[cmp])){
                wordWF[d1]=stemd[cmp];
                //System.out.println("\""+wordWF[fw]+"\"");
                d2[fw]+=1;
                flag=1;
                //System.out.println(d2[fw]+"-");
                for(int del=cmp;del<mcnt;del++){
                
                    stemd[del]=stemd[del+1];
                    
                }
                mcnt--;
                 
            }else{ 
            cmp++;
        }
        }    
        if(flag==0){
            wordWF[fw]=stemd[fw];
        }
        System.out.println(wordWF[d1]+": "+d2[fw]);
        d1++;
        
    
    }
}
         
}

