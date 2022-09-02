package hw1;

import java.util.Random;

public class HW1part2 
{
	static int anum = 0 ;
	static int tnum = 0;
	static int gnum = 0;
	static int cnum = 0;
	public static String generator( ) 
	{
	
		Random random = new Random();
		String code = "";

		
		
		for (int counter = 0; counter < 3 ; counter ++ ) 
		{
			//System.out.println(counter);
			double pa = 0.12;
			double pc = 0.38;
			double pg = 0.39;
			//double pt = 0.11;

			float num1 = random.nextFloat();
			String code1;
			if(num1>= 0 && num1 <= pa) 
			{
				code1 = "A";
				anum++;
			}
			else if(num1 > pa && num1 <= pa+pc) 
			{
				code1 = "C";
				cnum++;
    	
			}
			else if (num1 > pa+pc && num1 <= pa+pc+pg ) 
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

		System.out.println(anum);


		System.out.println(cnum);


		System.out.println(gnum);

		System.out.println(tnum);
		return(code);
	}
	
	
	public static void main(String[] args) 

	{
		int matchcounter = 0;
		//String test_trimer = "AAA";
		for (int tcounter =0; tcounter <1000; tcounter++ )
		{
			String	trimer = generator() ;
			if (trimer.equals("AAA"))
			{
				matchcounter=matchcounter+1;
			}
		}
		System.out.println("The number of times 'AAA' appears is " + matchcounter + " out of 1000 times");
		System.out.println("The number of times 'AAA' appears by chance is " + 0.12*0.12*0.12 );
		
	}
}

// .12*.12*.12
//0.001728
// 0/1000 = 0.001
// 3/1000 = 0.003



