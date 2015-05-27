package main;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import partitionRectangle.Organisme;
import partitionRectangle.SuperOrganisme;

import controller.Controller;

import view.ObserveurSwing;
import view.SuperOrganismeObserveurSwing;
import view.View;


import model.Model;

public class Main {

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
		//liste d'observateur;
		ArrayList<ObserveurSwing> listSwing ;
		listSwing = new ArrayList<ObserveurSwing>();
		//model
		Dimension dim5  =new Dimension(800,1200);
		
		ArrayList<Organisme> organismes= new ArrayList<Organisme>();
		//getter setter des arrayList => ok. and needed
		
		SuperOrganisme so =  new SuperOrganisme(new Point(20,30),dim5,organismes);
				//fonctionQuiGenereUnExemple de so;
		
		Model model = new Model(so);
		//view
		//View view = new View(model);
		View view = new View(listSwing);
		
		Controller controller = new Controller(view,model);
		
		
		//Lancement d'une fenetre
		JFrame fen = new JFrame();
		view.add(new SuperOrganismeObserveurSwing(so));
		
		controller.add(view);
		
		view.setPreferredSize(new Dimension(768,1024));
		fen.setContentPane(view);
		fen.setVisible(true);
		fen.pack();
		
		((Controller) controller).compute();
		
		
	}
	
}
