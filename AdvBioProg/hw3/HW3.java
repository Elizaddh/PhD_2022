package hw3;
import java.io.*;
import java.util.Random;

public class HW3
{
	public static String generateRandomSequence(char[] alphabet, float[] weights, int mylength) throws Exception
	{
		if (alphabet.length!=weights.length | mylength <=0) {
			throw new Exception("Please provide the correct format.");
		}
		
		float cumweight=0; //initialize
		float [] corrweight = new float[weights.length];
		for(int j = 0; j < weights.length;j++) {
			cumweight += weights[j];
			corrweight[j]=cumweight;
		}
		if (corrweight[corrweight.length-1] >1.1 | corrweight[corrweight.length-1] < 0.9) {
			throw new Exception("Round off error");
		}

		char [] randresidue = new char[mylength]; //to generate random seq, char array are mutable
        for(int i = 0; i < mylength; i++){
    		Random random = new Random();
            float num1 = random.nextFloat(); 
            
            int	ourrandidx = 0;
            for (int k=0 ; k < corrweight.length;k++ ) {
            	if (num1>=corrweight[k] && num1<corrweight[k+1]) {
            		ourrandidx = k+1;
            	}	
            }
        	randresidue[i]=  alphabet[ourrandidx];
        	
        }
		return( String.valueOf(randresidue));
	}
	
	public static void main(String[] args) throws Exception
	{
		float[] dnaWeights = { .3f, .3f, .2f, .2f };
		char[] dnaChars = { 'A', 'C', 'G', 'T'  };
		
			System.out.println(generateRandomSequence(dnaChars, dnaWeights,80));
		
		float proteinBackground[] =
			{0.072658f, 0.024692f, 0.050007f, 0.061087f,
		        0.041774f, 0.071589f, 0.023392f, 0.052691f, 0.063923f,
		        0.089093f, 0.023150f, 0.042931f, 0.052228f, 0.039871f,
		        0.052012f, 0.073087f, 0.055606f, 0.063321f, 0.012720f,
		        0.032955f}; 
			
		char[] proteinResidues = 
				new char[] { 'A', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T',
							 'V', 'W', 'Y' };
		System.out.println(generateRandomSequence(proteinResidues, proteinBackground, 80));
		
	}
}
