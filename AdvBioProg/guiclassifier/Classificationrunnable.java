package guiclassifier;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.RandomForest;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;



public class Classificationrunnable implements Runnable {

	private static double percentageaccuracyofmodel;
	//private String filetype;
	private String numthreads;
	private String classifiername;
	private File file;
	
	//TO-DO 
	// different constructor for different file type? or implement as interface and 
	//each filetype can be run as different class with method for file processing
	Classificationrunnable( String numthreads, String classifiername, File file){
		//this.filetype = filetype; 
		this.numthreads = numthreads;
		this.classifiername = classifiername;
		this.file = file; 
	}
	
	@Override
	public void run()
	{
		try {
			percentageaccuracyofmodel =runclassification( numthreads, classifiername, file );
		}
		catch(Exception ex) {
			ex.getMessage();
		}
	}
	
	public static double getpercentageaccuracy() {
		return percentageaccuracyofmodel; 
	}

	private double runclassification( String numthreads2, String classifiername, File file) throws Exception{
		
      Instances data =  DataSource.read(file.getAbsolutePath());
      data.setClassIndex(data.numAttributes() - 1);
      Classifier cl = null;
      // train classifier
      if(classifiername.equals("Random forest")) {
       cl = new RandomForest();
      }
      else if (classifiername.equals("Naive Bayes")) {
    	   cl = new NaiveBayes();
      }
      cl.buildClassifier(data);
      Evaluation eval = new Evaluation(data);
      eval.crossValidateModel(cl, data, 10, new Random(1));
      System.out.println("The accuracy is " + eval.pctCorrect());
      BufferedWriter writer = new BufferedWriter(new FileWriter(new File(
      		"Resultofclassification.txt")));
      
      writer.write("distribution\tweight\tactual\tpredicted\n");
      for( Prediction p : eval.predictions() )
      {
      	 NominalPrediction pred = (NominalPrediction) p;
      	
      	System.out.println(pred.actual() + "\t" + pred.predicted());
      	writer.write( pred.weight() + "\t" + pred.actual() + "\t" + pred.predicted() + "\n" );
      }
      writer.flush();  writer.close();
	return(eval.pctCorrect());
	}
}
