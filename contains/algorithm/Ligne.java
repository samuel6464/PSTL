package algorithm;

import partitionRectangle.Organisme;

public class Ligne {
//un organisme peut représenté aussi bien une voix qu'une ligne ! 
	private Organisme mesure;
	private int size;
	private int largeurContenue;
	public Ligne(Organisme mesure,int size) {
		super();
		this.mesure = mesure;
		this.setlargeurContenue(0);
	}
	public Organisme getMesure() {
		return mesure;
	}
	public void setMesure(Organisme mesure) {
		this.mesure = mesure;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public int getlargeurContenue() {
		return largeurContenue;
	}
	public void setlargeurContenue(int largeurContenue) {
		this.largeurContenue = largeurContenue;
	}
	
	
	
	 
	
}
