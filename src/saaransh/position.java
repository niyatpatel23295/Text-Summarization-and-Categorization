package saaransh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.BreakIterator;
import java.util.Locale;
import java.util.Scanner;

public class position {
	public static void main (String[] args) throws IOException 
	{
		
		String stringToExamine = new String();
		BufferedReader text= new BufferedReader(new InputStreamReader(System.in));
		String line1;
		stringToExamine = "";
		while((((line1=text.readLine())!= null) && (line1.length()!=0)))
		{
		 stringToExamine = stringToExamine.concat(line1.replaceAll("\\n+", ""));
		}		
					
		BreakIterator boundary;
        boundary = BreakIterator.getSentenceInstance(Locale.US);
        boundary.setText(stringToExamine);
        test ts = new test();
        ts.printEachForward(boundary, stringToExamine);
		//print each word in order
	       //   BreakIterator boundary = BreakIterator.getWordInstance();
	         // boundary.setText(stringToExamine);
	           
	         // printEachForward(boundary, stringToExamine);
	          //print each sentence in reverse order
		
	          	          // printEachBackward(boundary, stringToExamine);
	    //  printFirst(boundary, stringToExamine);
	         // printLast(boundary, stringToExamine);	      
	}
/*	private static void printLast(BreakIterator boundary, String stringToExamine) {
		// TODO Auto-generated method stub
	     int end = boundary.last();
	     for (int start = boundary.previous();
	          start != BreakIterator.DONE;
	          end = start, start = boundary.previous()) {
	         System.out.println(stringToExamine.substring(start,end));
	         
	     }
	 	}
*/
/*	private static void printFirst(BreakIterator boundary, String stringToExamine) {
		// TODO Auto-generated method stub
	     int start = boundary.first();
	     int end = boundary.next();
	     System.out.println(stringToExamine.substring(start,end));
		
	}
	*/
		 
	 
	/* private static void printEachBackward(BreakIterator boundary, String stringToExamine) {
		// TODO Auto-generated method stub
	     int end = boundary.last();
	     for (int start = boundary.previous();
	          start != BreakIterator.DONE;
	          end = start, start = boundary.previous()) {
	         System.out.println(stringToExamine.substring(start,end));
	     }
	  }
	*/
	}

class test{
	private int count=0;
	 public void printEachForward(BreakIterator boundary, String stringToExamine) {
		// TODO Auto-generated method stub
		 
		int start = boundary.first(); 
	     for (int end = boundary.next();end != BreakIterator.DONE;start = end, end = boundary.next()) {
	          count++;
	       }
	     System.out.println(count);
		 int count1 = count/2;
	//	 System.out.println(count1);
		 int start1 = boundary.first();
	     String position[] = new String[count];
	     float sent_position[] = new float[count];
	     int count2  = count1;
	     int start2 = 0;
	     int i = 0;
	     for(int end= boundary.next(); end != BreakIterator.DONE; start1 = end, end = boundary.next()){
		   	  position[i] = stringToExamine.substring(start1,end);
		      sent_position[i] = (float)(count2)/(count1);
		      System.out.println(sent_position[i]+",");
		      start2 = end;
		      if(count%2!=0)
		      {
		      if(count2==0)
		      {
		    	  break;
		      }
		      }
		      else
		      {
		    	  if(count2==1)
		    		  break;
		      }
		      i++;
		      count2--;
	     }
	     int count3 = count/2;
	     int count4 = count3;
	     int j=i+1;
	     for(int end=boundary.next();end != BreakIterator.DONE; start2 = end,end = boundary.next())
	     {
	    	 	position[j] = stringToExamine.substring(start2,end);
	    	 	sent_position[j] = (float)((count/2)-(count4 - 1))/(count3);
	    	 	System.out.println(sent_position[j]+",");
	    	 	if(count%2!=0)
	    	 	{
	    	 	if(count4==0)
	    	 	{
	    	 		break;
	    	 	}
	    	 	}
	    	 	else
	    	 	{
	    	 		if(count4==1)
	    	 			break;
	    	 	}
	    	 	j++;
	    	 	count4--; 
	     }
	   /*
	     else if(count%2==0)
	     {
	    	// System.out.println(count);
			 int count1 = count/2;
			// System.out.println(count1);
			 int start1 = boundary.first();
		     String position[] = new String[count];
		     float sent_position[] = new float[count];
		     int count2  = count1;
		     int start2 = 0;
		     int i = 0;
		     for(int end= boundary.next(); end != BreakIterator.DONE; start1 = end, end = boundary.next()){
			   	  position[i] = stringToExamine.substring(start1,end);
			      sent_position[i] = (float)(count2)/(count1);
			      System.out.println(position[i] + sent_position[i]);
			      start2 = end;
			      if(count2==1)
			      {
			    	  break;
			      }
			      i++;
			      count2--;
		     }
		     int count3 = count/2;
		     int count4 = count3;
		     int j=i+1;
		     for(int end=boundary.next();end != BreakIterator.DONE; start2 = end,end = boundary.next())
		     {
		    	 	position[j] = stringToExamine.substring(start2,end);
		    	 	sent_position[j] = (float)((count/2)-(count4 - 1))/(count3);
		    	 	System.out.println(position[j] + sent_position[j]);
		    	 	if(count4==1)
		    	 	{
		    	 		break;
		    	 	}
		    	 	j++;
		    	 	count4--; 
		     }
	     }
	     */
	     
	/*   System.out.println(j);
	   System.out.println(start2);
	   int k=0;
	   for(int end=boundary.next();end != BreakIterator.DONE; start2= end,end = boundary.next())
	   {
		   
		   if(count3==0)
		   {
			   break;
		   }
		   position[j] = stringToExamine.substring(start2,end);
		      sent_position[j] = count4/count3;
		      System.out.println(position[j] + sent_position[j]);
		      j++;
		      count4--;
		    
		   if(count4==0)
		   {
			   break;
		   }
		   position[k] = stringToExamine.substring(start2,end);
		   sent_position[k]= (j-count4)/count3;
		   System.out.println(position[k] + sent_position[k]);
		   k++;
		   count4--;
	   }	
	 */    
	 }
}
