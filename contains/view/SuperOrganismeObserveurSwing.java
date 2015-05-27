package view;

import java.awt.Graphics;
import java.io.IOException;
import java.util.HashMap;

import partitionRectangle.SuperOrganisme;


public class SuperOrganismeObserveurSwing implements ObserveurSwing {

	private static SuperOrganisme so;
	private boolean affBreak = false;
	
	public SuperOrganismeObserveurSwing(SuperOrganisme so) {
		SuperOrganismeObserveurSwing.setSupOrg(so);
	}


	@Override
	public void print(Graphics g) throws IOException {
		// TODO Auto-generated method stub
		getSupOrg().draw(g);
		if(getSupOrg().getSpace() == true){
			printSpace(g);
		}
		if(affBreak){
			printBreak(g);
		}
		
	}
	
	public static void printSpace(Graphics g){
		getSupOrg().drawSpace(g);
		System.out.println("espacePrint");
	}
	
	public static void printBreak(Graphics g){
		if(getSupOrg().getOrganismes().size()>=2){
		//getSupOrg().showPossibleBreakLine(g);
		//getSupOrg().showOptimalBreakOnS( g);
	//	getSupOrg().showOptimalBreakEach(g,500);
		getSupOrg().showOptimalBreakDyn(g);
	//	getSupOrg().showOptimalBreakDynEditor(g);
		}else{
			//si il y en a qu'une voix
		getSupOrg().showOptimalBreakOnMonophonie(g,200);
		}
	}


	public static SuperOrganisme getSupOrg() {
		return so;
	}


	public static void setSupOrg(SuperOrganisme so) {
		SuperOrganismeObserveurSwing.so = so;
	}


	public boolean isAffBreak() {
		return affBreak;
	}


	public void setAffBreak(boolean affBreak) {
		this.affBreak = affBreak;
	}

	

}
