package parser;

import java.util.ArrayList;

public class Ligne {
	private int ligneID;
	private ArrayList<Mesure> mesures;
	public int getLigne() {
		return ligneID;
	}
	public void setLigne(int ligne) {
		this.ligneID = ligne;
	}
	public ArrayList<Mesure> getMesure() {
		return mesures;
	}
	public void setMesure(ArrayList<Mesure> mesure) {
		this.mesures = mesure;
	}
	
	
	
}
