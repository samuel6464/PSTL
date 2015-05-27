package model;

import java.util.ArrayList;



import partitionRectangle.SuperOrganisme;

import view.UpdateEventListener;
import view.UpdateEventSender;


//va envoyer les event au listener
public class Model implements UpdateEventSender {
	SuperOrganisme so;
	
	public Model(SuperOrganisme so){
		this.so = so;
	}
	
	
	private ArrayList<UpdateEventListener> ecouteurs;
	@Override
	public void add(UpdateEventListener listener) {
		// TODO Auto-generated method stub
		ecouteurs.add(listener);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		for (UpdateEventListener listener : ecouteurs)
			listener.manageUpdate();
	}
	
	public void removeall(){
		ecouteurs.clear();
	}

	public void compute() {
		update();
	}



}
