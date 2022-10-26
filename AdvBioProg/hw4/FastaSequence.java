package hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileWriter;

import java.util.ArrayList;

public class FastaSequence
{
	
	private  String header;
	private  String sequence;
	
	public FastaSequence (String header, String sequence) { //CONSTRUCTOR
		this.header= header;
		this.sequence=sequence;
	 }
	
	public String getHeader() {
		return header;
	}
	
	public String getSequence() {
		return sequence;
	}
	
	public double getGCRatio() {
		int count = 0;
		for(int i = 0; i < sequence.length(); i++) {
			if(sequence.charAt(i) == 'G'  | sequence.charAt(i) == 'C'){
				 count++;
			}
			
		}
		double GCration = (double)count/ sequence.length();
		return GCration;
	}
	
	
	
	private static List<FastaSequence> readFastaFile(String filename) throws IOException { //FACTORY  METHOD

		String header1 = null  ;
		StringBuffer sequence1 =new StringBuffer() ;
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
		List<FastaSequence>listOfFastaSequenceObjects=new ArrayList<FastaSequence>();
		int headercounter = 0; 
 		for (String nextLine = reader.readLine(); nextLine !=null ;nextLine = reader.readLine()) {
 			boolean found = false;
 			if( nextLine.charAt( 0 ) == '>' ){
 				if (headercounter >0) {
 					if (sequence1 != null &  nextLine.charAt( 0 ) == '>' ){
 						FastaSequence mynewfastasequence = new FastaSequence(header1, sequence1.toString());
 						listOfFastaSequenceObjects.add(mynewfastasequence);
 						sequence1.setLength(0);
 					}
 				}
				headercounter=  headercounter+1;
				header1 = nextLine;
 			}
 			else{
 				sequence1.append(nextLine);
 			} 
 	}
		FastaSequence mynewfastasequence = new FastaSequence(header1, sequence1.toString()); //to_collect the last fastasequence object
		listOfFastaSequenceObjects.add(mynewfastasequence);
		reader.close();
 		return listOfFastaSequenceObjects;
	}
	
	public int getcharcount(char mychar) {
		int count = 0;
		for(int i = 0; i < sequence.length(); i++) {
			if(sequence.charAt(i) == mychar ) {   
				 count++;
			}
		}
		return count;
	}
	
public static void writeTableSummary( List<FastaSequence> list, File outputFile) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
		writer.write("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
		
		for( FastaSequence fs : list)
        {	 
         
			writer.write(fs.getHeader() + "\t" + fs.getcharcount('A') + "\t"+ fs.getcharcount('C') + "\t"+fs.getcharcount('G') + "\t"+fs.getcharcount('T') + "\t"+fs.getSequence()+"\n");
        }
		writer.flush();
		writer.close();		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		List<FastaSequence> fastaList = 
	                FastaSequence.readFastaFile("/Users/edhungel/Documents/PhD_UNCC/fall2022/Courses/AdvBioProg/Lab/hw2/src/hw2/testfile.txt");
             for( FastaSequence fs : fastaList)
             {	 
                 System.out.println(fs.getHeader());
                 System.out.println(fs.getSequence());
                 System.out.println(fs.getGCRatio());
              }
             File myFile = new File("/Users/edhungel/Documents/PhD_UNCC/fall2022/Courses/AdvBioProg/out.txt");

             writeTableSummary( fastaList,  myFile);
	}	
}




	
		


	
