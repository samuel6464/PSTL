package algorithm;

import java.util.ArrayList;

import partitionRectangle.Organisme;
import partitionRectangle.SuperOrganisme;

public class DynamiqueSimple {

	public static ArrayList<Integer> FindBreakGreedy(int sizeL , SuperOrganisme so){
		ArrayList<Integer> breaks = new ArrayList<Integer>();
		Organisme org = so.getOrganismes().get(0);
		int largeurDyn=0;
		int espacement = 0;
		
		int i=0;
		int nbLine =0;
		 while(i<so.getOrganismes().get(0).getCellules().size()){
			 System.out.println("YO NEWLINE");
			 
			 largeurDyn = 0;
			 espacement = 0;
			// nbLine++;
			 //pour chaque break chercher
			 while(largeurDyn<sizeL){
				 if(i==so.getOrganismes().get(0).getCellules().size())break;
				 largeurDyn+=so.getOrganismes().get(0).getCellules().get(i).getRectangle().width;
				 espacement+=so.getOrganismes().get(0).getCellules().get(i).getEspacement();
				 i++;
			 }
			 if(i==so.getOrganismes().get(0).getCellules().size()){
				 breaks.add(so.getOrganismes().get(0).getCellules().get(i-1).getRectangle().x + so.getOrganismes().get(0).getCellules().get(i-1).getRectangle().width);
				 break;
			 }
			 i--;
			 System.out.println(largeurDyn);
			 if(i==so.getOrganismes().get(0).getCellules().size()){
				// largeurDyn = so.getOrganismes().get(0).getCellules().get(i-1).getRectangle().x + so.getOrganismes().get(0).getCellules().get(i-1).getRectangle().width;
				 System.out.println("last i="+i);
				 breaks.add(so.getOrganismes().get(0).getCellules().get(i-1).getRectangle().x + so.getOrganismes().get(0).getCellules().get(i-1).getRectangle().width);
				 break;
			 }
			 //On continue en fonction de l'espacement et la taille des atomes suivant
			 //on suppose la taille du zoom possible est a 50% d'où espacement/2.
			 System.out.println("JUST BEFORE PART ZOOM "+ so.getOrganismes().get(0).getCellules().get(i).getRectangle().width);
			 boolean stop = false;
			 while(!stop){
				 i++;
				 System.out.println(so.getOrganismes().get(0).getCellules().size()+" et i: "+i + "et esp"+espacement);
				 if(i==so.getOrganismes().get(0).getCellules().size()){
					 System.out.println("JBREAK");
					 i--;
					 break;
				 }
				 System.out.println(so.getOrganismes().get(0).getCellules().get(i).getRectangle().width+" et i: "+i + "et esp"+espacement +"and Ldyn"+largeurDyn+" sizeL "+sizeL);
				 if((espacement) > so.getOrganismes().get(0).getCellules().get(i).getRectangle().width  && (largeurDyn-espacement/2)<sizeL){
					 System.out.println("espacement superieur");
					 try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 largeurDyn+=so.getOrganismes().get(0).getCellules().get(i).getRectangle().width;
					 espacement = espacement - so.getOrganismes().get(0).getCellules().get(i).getRectangle().width + so.getOrganismes().get(0).getCellules().get(i).getEspacement();
				 }else{
					// i--;
					 System.out.println("JBREAK2");
					 stop=true;
					 break;
				 } 
				 
			 }
			 largeurDyn = so.getOrganismes().get(0).getCellules().get(i).getRectangle().x + so.getOrganismes().get(0).getCellules().get(i).getRectangle().width;
			 breaks.add(so.getOrganismes().get(0).getCellules().get(i).getRectangle().x + so.getOrganismes().get(0).getCellules().get(i).getRectangle().width);
			 if(i==so.getOrganismes().get(0).getCellules().size())i++;
			 System.out.println("ICI: " +i);
		 }
		
		System.out.println("before return");
		return breaks;
	}
}
