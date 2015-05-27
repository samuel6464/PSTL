package parser;

import java.util.ArrayList;

public class GroupeNote {
	private int grpNoteid;
	private int zoom ;
	
	private ArrayList<Atomee> atomes;

	public int getGrpNoteid() {
		return grpNoteid;
	}

	public void setGrpNoteid(int grpNoteid) {
		this.grpNoteid = grpNoteid;
	}

	public int getZoom() {
		return zoom;
	}

	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	public ArrayList<Atomee> getAtomes() {
		return atomes;
	}

	public void setAtomes(ArrayList<Atomee> atomes) {
		this.atomes = atomes;
	}
	
	
	
}
