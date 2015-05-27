package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import fenetre.Fenetre;

public class MainIHM {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			
			@Override
			public void run(){
				runApp();
			}
		
		
	});
		
	}

	public static void runApp(){
		
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
		//fen.setVisible(true);
		fen.pack();
		
		fen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

}
