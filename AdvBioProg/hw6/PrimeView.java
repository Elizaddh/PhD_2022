package hw6;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;

public class PrimeView extends JFrame {
	private static final long serialVersionUID = 1L;


	
	private JLabel myLabel = new JLabel("Prime upto: Give number");
	private JTextField num1 = new JTextField(10);
	private JLabel mythreadLabel = new JLabel("No. of threads");
	private JTextField num2 = new JTextField(10);
	JButton calculateButton = new JButton("Calculate");
	JButton cancelButton = new JButton("Cancel");
	private JTextArea calcSolution = new JTextArea("Below are the prime no. \n");
	


	public PrimeView() {

		JPanel calcPanel = new JPanel();
		calcSolution.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(calcSolution);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(900, 400);
		calcPanel.add(myLabel);
		
		calcPanel.add(num1);
		calcPanel.add(mythreadLabel);
		calcPanel.add(num2);
		calcPanel.add(calculateButton);
		calcPanel.add(cancelButton);
		cancelButton.setEnabled(false);
		this.setLayout(new BorderLayout());
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(calcPanel, BorderLayout.EAST);
		
	}

	public int getnum1() {
		return Integer.parseInt(num1.getText());
	}

	public int getnum2() {
		return Integer.parseInt(num2.getText());
	}
	void displayErrorMessage(String errorMessage) {
	JOptionPane.showMessageDialog(this, errorMessage);
}
	public JTextArea getcalcSolution() {
		return calcSolution;
	}

	public void setCalcSolution(List<Integer>  calculationValue) {
		Collections.sort( calculationValue);
		for (int i = 0; i <  calculationValue.size(); i++) {
			calcSolution.append( calculationValue.get(i).toString());
			calcSolution.append("\n");
		}
		
	}
	
	
	public void addCalculationListener(ActionListener listenerForCalcButton) {
		calculateButton.addActionListener(listenerForCalcButton);
	}

	public void addCancelButtonListener(ActionListener listenerForCancelButton) {
		cancelButton.addActionListener(listenerForCancelButton);
	}
	
	
	
	

}

//	public void setCalcSolution(List<Integer> calculationValue) {
//	
//	System.out.println("We are setting up calculating solution");
//	// calcSolution.setText("This are the prime numbers");
//	calcSolution.setLineWrap(true);
//	calcSolution.setWrapStyleWord(true);
//
//	// calcSolution.append("found " + list.size() + "prime numbers" + "\n");
//
//	// print when the cancel = true or when calculation is complete
//	// if(cancel == true) {
//	Collections.sort(calculationValue);
//	for (int i = 0; i < calculationValue.size(); i++) {
//		calcSolution.append(calculationValue.get(i).toString());
//		calcSolution.append("\n");
//	}
//}
