package hw2;

import java.util.Random;

public class HW2
{
        public static String[] SHORT_NAMES =
        { "A","R", "N", "D", "C", "Q", "E",
        "G",  "H", "I", "L", "K", "M", "F",
        "P", "S", "T", "W", "Y", "V" };
        public static String[] FULL_NAMES =
        {
        "alanine","arginine", "asparagine",
        "aspartic acid", "cysteine",
        "glutamine",  "glutamic acid",
        "glycine" ,"histidine","isoleucine",
        "leucine",  "lysine", "methionine",
        "phenylalanine", "proline",
        "serine","threonine","tryptophan",
        "tyrosine", "valine"};
        public static void main(String[] args)
        {
		int correct = 0;

                //ask time based or number of questions based
                System.out.println("Select 1 for time based and 2 for number of questions based");
                int modetype = Integer.parseInt(System.console().readLine());
                if (modetype==1)
                {
                    System.out.println("Please enter the test seconds");

                	int anInteger = Integer.parseInt(System.console().readLine());
                	long currentTime = System.currentTimeMillis();
                	long end = currentTime + anInteger * 1000;
                	System.out.println(currentTime);

                	while( System.currentTimeMillis() < end)
                	
			{
				long timerem = end- System.currentTimeMillis();	
                		System.out.println("Time remaining " + (float) timerem/1000 + " sec" );
				correct = runtest(correct);
                        
                	}
                }
                else {
                	System.out.println("Please enter the number of questions");
                	int numques = Integer.parseInt(System.console().readLine());
                	while (numques !=0) {
                		correct  = runtest(correct);
                		numques=numques-1;
                                System.out.println("Number of question remaining is " + numques );

                	}
                }
            	System.out.println("Your final score is " + correct);
                		

        }
        private static int runtest(int correctval){
        Random random = new Random();

        int num1 = random.nextInt(FULL_NAMES.length);
        System.out.println("Give one letter code for amino acid:" + FULL_NAMES[num1]);
        String aString = System.console().readLine().toUpperCase();
        String aChar = "" + aString.charAt(0);

        if(aChar.equals(SHORT_NAMES[num1]))
        {
                    correctval++;
                    System.out.println("Correct answer, Score is now: " + correctval   );
                  
        } else {
                    System.out.println("Sorry incorrect, Correct answer is " + SHORT_NAMES[num1]);
                    
        }
            
        	     return correctval;
        }
}
