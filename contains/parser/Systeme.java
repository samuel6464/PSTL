package parser;

import java.util.ArrayList;

public class Systeme {
	
	 //@SerializedName("empID")
	private int systemeID;
	ArrayList<Ligne> ligne;
	public int getSystemeID() {
		return systemeID;
	}
	
	public void setSystemeID(int systemeID) {
		this.systemeID = systemeID;
	}
	
	public ArrayList<Ligne> getLigne() {
		return ligne;
	}
	
	public void setLigne(ArrayList<Ligne> ligne) {
		this.ligne = ligne;
	}
	
	
}
