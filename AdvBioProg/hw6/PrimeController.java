package hw6;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.SwingWorker;


public class PrimeController {// 

	private PrimeView theView;
	private PrimeModel theModel;
	public volatile boolean cancel;
	MySwingWorker Worker = new MySwingWorker();

	public PrimeController(PrimeView theView, PrimeModel theModel) {// 
		// , boolean cancel) {
		this.theView = theView;
		this.theModel = theModel;
		// this.cancel = cancel;

		this.theView.addCalculationListener(new CalculateListener());
		this.theView.addCancelButtonListener(new CancelListener());
	}

	public class MySwingWorker extends SwingWorker<List<Integer>, Integer>  {
		
		
		@Override
		public List<Integer> doInBackground() throws Exception
		{
			//new CalculateActionRunnable().run();
			
				int limit, threadPoolSize = 0; 
			
		
		
				System.out.println("Current Thread Name to call isPrime: "+ Thread.currentThread().getName());

				System.out.println("new thread is set up for calulation");
				//List<Integer> numbers = new ArrayList<Integer>();
				
				List<Integer> numbers = null;
				try {
					try {
						limit = theView.getnum1(); // num1 is maximum number upto which prime no. is found
						threadPoolSize = theView.getnum2(); 
						System.out.println("we got num of threads" + threadPoolSize);
						System.out.println("find prime num is called in model");
						numbers =theModel.findPrimenum(limit, threadPoolSize, cancel);//, cancel);//, cancel);
						//publish(numbers);
						 System.out.println("Current Thread Name: "+ Thread.currentThread().getName());
					}
					
					catch (NumberFormatException ex) {
						theView.displayErrorMessage("Please enter two integers");
					} catch (InterruptedException e1) {
					
						e1.printStackTrace();
					}			
							
			}
			catch(Exception ex)
			{
				System.out.println("Exception has occured");
				ex.printStackTrace();
			}
				return numbers;
		}
		
		
		
		
		@Override
	       protected void done() {
	           try {
	               theView.setCalcSolution(theModel.getCalculationValue()) ; 
	               //theView.settimer((System.currentTimeMillis() - theModel.getstarttime())  / 1000f );
	           } catch (Exception ignore) {
	        	   
	           }
		}
	}
	           
	class CalculateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		
			theView.cancelButton.setEnabled(true);
			System.out.println("calulation is being done");
			cancel = false;
			Worker.execute();	
			
			theView.calculateButton.setEnabled(false);
		}
		
		
	}
			
	class CancelListener implements ActionListener {
		@Override
		
		public void actionPerformed(ActionEvent e)  {
		
			cancel = true;
			Worker.cancel(cancel);
			System.out.println("cancel button is clicked");
			theView.displayErrorMessage("User cancelled at" + ((System.currentTimeMillis() - theModel.getstarttime())  / 1000f ) + "sec"
					+ "\n" + "Close this box to see gained result");
		}

		
	}
	
	public class CustomInterruptedException extends Exception {
	    private static final long serialVersionUID = 1L;

		CustomInterruptedException(String message) {
	        super(message);
	    }
	}
}
	
	
