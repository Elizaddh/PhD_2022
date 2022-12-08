package hw6;

public class MVCPrime {

	public static void main(String[] args)  throws InterruptedException{
		PrimeView theView = new PrimeView();
		System.out.println("this ran fine");
		PrimeModel theModel = new PrimeModel();
		System.out.println("this ran fine2");
		PrimeController theController = new PrimeController(theView, theModel);
		theView.setVisible(true);

	}

}