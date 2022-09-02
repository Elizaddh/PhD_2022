

package hw1;

import java.util.Random;

public class hw1extra
{
	static int anum = 0 ;
	static int tnum = 0;
	static int gnum = 0;
	static int cnum = 0;
	private static float chisqr;
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
				anum++;
			}
			else if(num1 == 1) 
			{
				code1 = "C";
				cnum++;
    	
			}
			else if (num1 == 2) 
			{
				code1 = "G";
				gnum++;
			}
			else
			{
				code1 = "T";
				tnum++;
			}
			
			code = code+code1;
			
			
		}
		System.out.println(code);
		return(code);
	}
	
	
	public static void main(String[] args) 

	{
		int matchcounter = 0;
		for (int run = 0; run <10000 ; run++)
		{
			for (int tcounter =0; tcounter <10000; tcounter++ )
			{
				String	trimer = generator() ;
				if (trimer.equals("AAA"))
				{
					matchcounter=matchcounter+1;
				}
			}
		}
		
		System.out.println("The number of times 'AAA' appears is " + matchcounter + " out of 1000 times");
		System.out.println("The number of times 'AAA' appears by chance is " + 0.25*0.25*0.25 );
		System.out.println("The number of times  A is observed is  " +  anum );
		System.out.println("The number of times  T is observed is " +  tnum );
		System.out.println("The number of times  G is  observed is " +  gnum );
		System.out.println("The number of times  C is  observed is " +  cnum );
		float expected = 300/4;
		chisqr = ((anum- expected)* (anum- expected) + (tnum- expected)*(tnum- expected) + (gnum- expected)*(gnum- expected)+(cnum- expected)*(cnum- expected))/expected;
		System.out.println("This is the xi square value " + chisqr);
		System.out.println(chisqr);
		System.out.println(expected);
	}

}

// At degree of freedom (4-1)=3, and 5% level of significance,
//the critical value is 7.815 and we obtain the chisquare value of 0.7466. Our value is less, so null hypothesis cannot be rejected.
//so the frequency observed is not different than expected.

