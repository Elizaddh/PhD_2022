package guiclassifier;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import weka.*;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.evaluation.Prediction;
import weka.classifiers.trees.RandomForest;
//import weka.classifiers.trees.RandomForest;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

class myguiclassifier extends JFrame implements ActionListener{
  
    private JMenuItem   RandomForestmodel;
    private JMenuItem   NaiveBayesModel;
//    private JMenuItem   Save;
//    private JMenuItem   SaveAs;
//    private JMenuItem   Exit;
    private JMenuItem   Thread1;
    private JMenuItem   Thread2;
    private JMenuItem   Thread4;
    private JTextField  jt;
    private JTextField  numthreadvar;
    private JButton runclassifierButton;
    private File chosenfile;
    
    private JMenuItem   txtfile;
    private JMenuItem   csvfile;
    private JMenuItem   arfffile;
    JFileChooser jfc = new JFileChooser();
    private JLabel resultdisplay ;
    

  
    myguiclassifier()
    {
        setTitle("Machine learning classifier GUI");
        setLayout(new FlowLayout());
        setJMenuBarAndMenuBarItems();
        setAction();
        setJTextField();
        setJButton();
        
        
        setSize(700, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
  
    void setJMenuBarAndMenuBarItems()
    {
        JMenuBar menuBar = new JMenuBar();
        //JFileChooser clicktochosefile = new JFileChooser();
      
        JMenu menu1    = new JMenu("Classifier");
        RandomForestmodel      = new JMenuItem("Random Forest");
        NaiveBayesModel     = new JMenuItem("Naive Bayes");

        menu1.add(RandomForestmodel);
        menu1.add(NaiveBayesModel);
     
        JMenu menu2    = new JMenu("No. of threads");
        Thread1      = new JMenuItem("1");
        Thread2     = new JMenuItem("2");
        Thread4    = new JMenuItem("4");
        menu2.add(Thread1);
        menu2.add(Thread2);
        menu2.add(Thread4);
       
        //menuBar.add(clicktochosefile);
        setJMenuBar(menuBar);
        
        JMenu menu3 = new JMenu("Choose a file");
        txtfile = new JMenuItem("txt file");
        csvfile = new JMenuItem("csvfile");
        arfffile = new JMenuItem("arfffile");
        menu3.add(txtfile);
        menu3.add(csvfile);
        menu3.add(arfffile);
        
        
        menuBar.add(menu1);
        menuBar.add(menu2);
        menuBar.add(menu3);
        
    }
  
    void setJTextField()
    {
        jt = new JTextField(10);
        add(jt);
        numthreadvar = new JTextField(15);
        add(numthreadvar );
       resultdisplay = new JLabel("Accuracy will be displayed here.");
       add(resultdisplay);
        
        
    }
    
    void setJButton() {
    	runclassifierButton = new JButton("RUN CLASSIFIER");
        add(runclassifierButton);
        runclassifierButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              // do stuff here  String numthreads, String classifiername, File fil
          	new Thread(new Classificationrunnable(numthreadvar.getText(), jt.getText(), jfc.getSelectedFile().getAbsoluteFile()  )).start();
          
          try {
        	  SwingUtilities.invokeAndWait(new Runnable(){
        		  public void run()
        		  {
        			  resultdisplay.setText("Percentage accuracy of model is " + Classificationrunnable.getpercentageaccuracy());
        		  }
        	  });
        	  }
          catch(Exception ex) {
        	  ex.printStackTrace();
          
          }
          
          }
      });
    }
  
    void setAction()
    {
        RandomForestmodel.addActionListener(this);
        NaiveBayesModel .addActionListener(this);
        
//        Save.addActionListener(this);
//        SaveAs.addActionListener(this);
//        Exit.addActionListener(this);
        Thread1.addActionListener(this);
        Thread2.addActionListener(this); 
        Thread4.addActionListener(this);
        
        txtfile.addActionListener(new ActionListenerforfileupload ("txt")  );
        csvfile.addActionListener(new ActionListenerforfileupload ("csv")  );
        arfffile.addActionListener(new ActionListenerforfileupload ("arff")  );
                    
        
    }

    
    public class ActionListenerforfileupload implements ActionListener{
    	private String filetype;
    	
		ActionListenerforfileupload(String filetype){
    		this.filetype = filetype;
    	}
    	@Override
		public void actionPerformed(ActionEvent e) {
    		openFile(filetype); 
    	}
    }
    
    protected void openFile(String string) {
    	String fileDescription = string + " files";
		String extensionfull = string.toUpperCase(); 
		
	
		FileNameExtensionFilter filter = new FileNameExtensionFilter(fileDescription, extensionfull);
		jfc.setFileFilter(filter);

		if(jfc.showSaveDialog(this)!= JFileChooser.APPROVE_OPTION)
			return;
		if (jfc.getSelectedFile()==null)
			return;
		File chosenFile = jfc.getSelectedFile();// 
    }
    
	public void actionPerformed(ActionEvent eve)
    {
        if(eve.getSource() == RandomForestmodel)
            jt.setText("RandomForestselected");
        else if(eve.getSource() == NaiveBayesModel)
            jt.setText("NaiveBayesSelected");
        else if(eve.getSource() ==  Thread1)
            numthreadvar.setText("1");
        else if(eve.getSource() ==  Thread2)
        	numthreadvar.setText("2");
        else if(eve.getSource() ==  Thread4)
        	numthreadvar.setText("3");
       
    }
	
    public static void main(String[] args) throws Exception {
    	
        myguiclassifier jm = new  myguiclassifier();
    }
    
}
