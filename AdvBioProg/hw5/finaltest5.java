package hw5final;

import java.util.Random;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.SpringLayout;
import java.awt.*;
import java.lang.Math; 
import javax.swing.text.*;

public class finaltest5 extends JFrame {
	
	public int num1;
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
 
	volatile boolean programTimeFlag; 
	int correctval = 0;	
	volatile boolean newquestion = true;
	private static final long serialVersionUID = 3794059922116115530L;
	
	private JLabel textLabel = new JLabel("Just area for time");
	private JLabel textLabel1 = new JLabel("Give the short name for");
	private JTextField textField = new JTextField(10);
	
	private JButton startButton	= new JButton("Start AA quiz");
	private JButton cancelButton = new JButton("Cancel");
	private JLabel scoreLabel = new JLabel("Your score appears here");

	private boolean cancel = false;
	
	
	public finaltest5() 
	{  //Constructor
		setLocationRelativeTo(null);
		setSize(400,200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(getButtomPanel(), BorderLayout.SOUTH);
		
		getContentPane().add(textLabel, BorderLayout.NORTH);
		getContentPane().add(textField, BorderLayout.WEST);
		
		textField.addKeyListener(new mykeyListener() ); 
		textField.setEnabled(false);
		getContentPane().add(textLabel1, BorderLayout.CENTER);
		getContentPane().add(scoreLabel,  BorderLayout.EAST);
		setLocationRelativeTo(null);
		setVisible(true);			
	}
	
	public JPanel getButtomPanel()
	{
		JPanel panel = new JPanel();
		
		panel.setLayout(new GridLayout(0,2));

		startButton.addActionListener(new  StartActionListener()); 
		cancelButton.addActionListener(new  CancelActionListener()); 
		panel.add(startButton);
		panel.add(cancelButton);
	
		return panel;
	}


	public class StartQuizRunnable implements Runnable
	{
		@Override
		public void run()
		{
			try
			{
				//textLabel1.setText("Give the short name for");
				scoreLabel.setText("Your score appears here");
				correctval = 0; 
				long currentTime = System.currentTimeMillis();
				while (!cancel && !programTimeFlag )
				{
					while(newquestion) {
					Random random = new Random();
					num1 = random.nextInt(FULL_NAMES.length);
					System.out.println("This is the random number  " + num1); 
					textLabel1.setText("The amino acid at random is " + FULL_NAMES[num1]  + "\n");//
					newquestion = false;
					
					}
					
		
				}
			}
			
			catch(Exception ex)
            {
            
				textLabel1.setText(ex.getMessage());
                ex.printStackTrace();
                
            }
            
			try
            
			{
            
				SwingUtilities.invokeAndWait(new Runnable()
                
				{
                
					public void run()
                    
					{
                    
						startButton.setEnabled(true);
                        
						cancelButton.setEnabled(false);
                        
					}
                    
				});

				
			}

                        
			catch(Exception e)
            
			{
            
				e.printStackTrace();

			}

		}
	}
	
	public class StartActionRunnable implements Runnable 
	{

		@Override
		public void run() 
		{
			try 
			{
				long currentTime = System.currentTimeMillis();
		                long end  = currentTime + 10 * 1000; //10sec
				//int numSecRem = 0;
				long timerem;

				while (!cancel && System.currentTimeMillis()<currentTime + 10 * 1000) 
				{
					programTimeFlag = false; 
					//timerem = end - System.currentTimeMillis();	
					timerem = end - System.currentTimeMillis();	
					textLabel.setText("Time remaining " + Math.round((float) timerem/1000) + " sec\n");
					Thread.sleep(1000);
				}
			if(!cancel) // if cancel is by time up
			{
				textLabel.setText("Time up \n");
                        }
			textField.setEnabled(false);
	
			programTimeFlag = true;		
			}
			catch(Exception ex)
			{
				textLabel.setText(ex.getMessage());
				ex.printStackTrace();
			}
			
			try
			{
				SwingUtilities.invokeAndWait(new Runnable()
				{
					public void run()
					{
						startButton.setEnabled(true);
						cancelButton.setEnabled(true); 
					}
				});
				
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
					
			}
		}
	}
			
	
	public class StartActionListener implements ActionListener
	{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			
						cancel = false;
						startButton.setEnabled(false);
						cancelButton.setEnabled(true);
						textField.setEnabled(true);
						new Thread(new StartActionRunnable()).start(); // one thread does the time check in background
						new Thread(new StartQuizRunnable()).start(); // another thread runs the test
			}
	}
	
	
	public class mykeyListener implements KeyListener{
		@Override
		public void keyPressed(KeyEvent e) {
          if(e.getKeyCode() == KeyEvent.VK_ENTER){
        	newquestion = true;

			String  aString = textField.getText();
	
			String aChar = "" + aString.charAt(0);
			
			if(aChar.equals(SHORT_NAMES[num1]))
			{
				correctval++;
				System.out.println("Correct answer, Score is now: " + correctval   );
			} else {
				System.out.println("Sorry incorrect, Correct answer is " + SHORT_NAMES[num1]);
			}
			scoreLabel.setText("Your score is " +  correctval);
        	textField.setText("");
         }
          
		}
	
          public void keyReleased(KeyEvent e) {
        	  System.out.println("Some key is pressed");
          }
          public void keyTyped(KeyEvent e) {
          System.out.println("Some key is realized");
          }
          
	}

	public class CancelActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) 
		{
		
				textLabel.setText("User canceled \n");
				cancel = true;
				 textField.setEnabled(false);
		 }
		

	}

	public static void main(String[] args) 
	{
		new finaltest5();
	}

}






