package aaapoubelle;

import java.util.ArrayList;

public class Sujet {
	private String nom;
	private String poste;
	private ArrayList<Aime> aimes;
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPoste() {
		return poste;
	}
	public void setPoste(String poste) {
		this.poste = poste;
	}
	public ArrayList<Aime> getAimes() {
		return aimes;
	}
	public void setAimes(ArrayList<Aime> aimes) {
		this.aimes = aimes;
	}
	
	
}
