/*
 * (1) Write code to print to the console 1,000 randomly generated 
	DNA 3 mers (e.g. “ACA”, “TCG” )
	where the frequency of A,C,G and T is 25% and is uniformly sampled. 
	Have your code track how often it prints out the 3 mer (“AAA”) 
    How often would you expect to see this 3mer by chance?  Is Java’s number
    close to the number that you would expect?

	*/


package hw1;

import java.util.Random;

public class HW1 
{
	
	public static String generator( ) 
	{
	
		Random random = new Random();
		String code = "";
		for (int counter = 0; counter < 3 ; counter ++ ) 
		{

			int num1 = random.nextInt(4);
			String code1;
			if(num1 == 0) 
			{
				code1 = "A";
			}
			else if(num1 == 1) 
			{
				code1 = "C";
    	
			}
			else if (num1 == 2) 
			{
				code1 = "G";
			}
			else
			{
				code1 = "T";
			}
			
			code = code+code1;
			
			
		}
		System.out.println(code);
		return(code);
	}
	
	
	public static void main(String[] args) 

	{
		int matchcounter = 0;
		
		for (int tcounter =0; tcounter <1000; tcounter++ )
		{
			String	trimer = generator() ;
			if (trimer.equals("AAA"))
			{
				matchcounter=matchcounter+1;
			}
		}
		System.out.println("The number of times 'AAA' appears is " + matchcounter + " out of 1000 times");
		System.out.println("The number of times 'AAA' appears by chance is " + 0.25*0.25*0.25 );
	}
}

// .25*.25*.25
// 0.015625
// 14/1000 = 0.014
// 15/1000 = 0.015



