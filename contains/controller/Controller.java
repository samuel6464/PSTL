package controller;


import java.util.ArrayList;

import model.Model;
import view.UpdateEventListener;
import view.UpdateEventSender;
import view.View;

public class Controller  implements UpdateEventSender {
	
	private ArrayList<UpdateEventListener> ecouteurs;
	private View view;
	private Model model;
	public Controller(View view, Model model) {
		this.view = view;
		this.model = model;
		ecouteurs = new ArrayList<UpdateEventListener>();
	}
	@Override
	public void add(UpdateEventListener listener) {
		ecouteurs.add(listener);
	}
	
	public void removeall(){
		ecouteurs.clear();
	}

	@Override
	public void update() {
		for (UpdateEventListener listener : ecouteurs)
			listener.manageUpdate();
		
	}

	public void compute() {
		// TODO Auto-generated method stub
		update();
		System.out.println("coucouGcompute");
	}


	
	

}
