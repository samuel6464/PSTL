package algorithm;

import java.awt.Dimension;
import java.awt.Point;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import parser.Generer;
import partitionRectangle.Cellule;
import partitionRectangle.Organisme;
import partitionRectangle.SuperOrganisme;

public class Dynamique {
	//le but de cette algorithme est de permettre le ligne breaking lorsque la longueur des molecule choisis 
	//sont inf�rieur � la taille de la ligne.
	
	//nbCel est le nombre de cellule choisis.
	
	
	//retourne le nombre d'itération
	public static Ligne fillDynamique(int sizeL , SuperOrganisme so){
		//sans shrink pour le moment
		Organisme org = new Organisme(new Point(100,100), new Dimension(sizeL,300),new ArrayList<Cellule>());
		//private ArrayList<Cellule> cellules;
		Ligne l = new Ligne(org,sizeL);
		System.out.println(l.getSize());
		l.setSize(sizeL);
		int largeurDyn=0;
		//plut�t un do while...?
		int i=0;
		
		do{
			//on le fait s�re une ligne pour le moment
			System.out.println("hola"+i+" " +so.getOrganismes().get(0).getCellules().size());
			System.out.println(so.getOrganismes().get(0).getCellules().get(i));
			org.getCellules().add(so.getOrganismes().get(0).getCellules().get(i));
			System.out.println("debug:\n"+so.getOrganismes().get(0).getCellules().get(i).getRectangle().width);
			largeurDyn+=so.getOrganismes().get(0).getCellules().get(i).getRectangle().width;
			i++;
		}while(largeurDyn<sizeL && i<so.getOrganismes().get(0).getCellules().size());
		//
		if(i==1){//on est plus grand que la taille de la ligne
			//on coupera la mesure par cons�quent...
			System.out.println("salut!filldynamique");
			
		}
		System.out.println(largeurDyn+" "+l.getlargeurContenue());
		l.setlargeurContenue(largeurDyn) ;
		System.out.println(largeurDyn+" "+l.getlargeurContenue());

		
		//org.getRectangle().setSize(largeurDyn, 300)
		System.out.println("fhgfhgfhgfhgfhgf"+org+"      "+org.getRectangle().width);
		l.setMesure(org);
		System.out.println(org);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;	
		
		//a ce moment si la ligne a alors un espace restant
	}
	
	/*public static Ligne fillDynamique2(int sizeL , SuperOrganisme so){
		//sans shrink pour le moment
		Organisme org = new Organisme(new Point(100,100), new Dimension(sizeL,300),new ArrayList<Cellule>());
		//private ArrayList<Cellule> cellules;
		Ligne l = new Ligne(org,sizeL);
		System.out.println(l.getSize());
		l.setSize(sizeL);
		int largeurDyn=0;
		//plut�t un do while...?
		int i=0;
		
		do{
			//on le fait s�re une ligne pour le moment
			//System.out.println("hola"+i+" " +so.getOrganismes().get(0).getCellules().size());
			//System.out.println(so.getOrganismes().get(0).getCellules().get(i));
			org.getCellules().add(so.getOrganismes().get(0).getCellules().get(i));
			//System.out.println("debug:\n"+so.getOrganismes().get(0).getCellules().get(i).getRectangle().width);
			largeurDyn+=so.getOrganismes().get(0).getCellules().get(i).getRectangle().width;
			i++;
		}while(largeurDyn<sizeL && i<so.getOrganismes().get(0).getCellules().size());
		//
		int y=0;
		boolean limitMaxAtteinte=false;
		while(!limitMaxAtteinte){
			
			for(){
				dezoom
			}
			
		}
		
		if(i==1){//on est plus grand que la taille de la ligne
			//on coupera la mesure par cons�quent...
			System.out.println("salut!filldynamique");
			
		}
		System.out.println(largeurDyn+" "+l.getlargeurContenue());
		l.setlargeurContenue(largeurDyn) ;
		System.out.println(largeurDyn+" "+l.getlargeurContenue());

		
		//org.getRectangle().setSize(largeurDyn, 300)
		System.out.println("fhgfhgfhgfhgfhgf"+org+"      "+org.getRectangle().width);
		l.setMesure(org);
		System.out.println(org);
	
		return l;	
		
		//a ce moment si la ligne a alors un espace restant
	}
	*/
	
	//L'idée est de regardé une fois que l'on ne peut plus ajouter
	//de cellule, on regarde l'espace restant (en fonction du zoom max décidé)
	// puis on regarde si c'est possible.
	//les espaces on malheureusement des poids différent (en fonction d'où elle se trouve)
	//ces poids sont des coefficient (en fonction de l'espacement de départ 100%
	//l'espacementMax et l'espacementMin.
	//POIDS POUR LES ESPACEMENT SELON OU IL SE TROUVE
	public static Ligne fillDynamique3(int sizeL , SuperOrganisme so){
		//sans shrink pour le moment
		Organisme org = new Organisme(new Point(100,100), new Dimension(sizeL,300),new ArrayList<Cellule>());
		//private ArrayList<Cellule> cellules;
		Ligne l = new Ligne(org,sizeL);
		return l;
	}
	
	public static Ligne Completion(Ligne l){
		//on regarde la taille actuel de nos mesures
		System.out.println("salut");
		while(l.getlargeurContenue()<l.getSize()){
			//zoom...
			
			System.out.println("into the while");
		
			l.getMesure().changeSize(1);
			l.getMesure().zoomCellules(1);
			
			int agr =l.getlargeurContenue()+1;
			System.out.println("agr;"+agr);
			l.setlargeurContenue(agr);
	
			System.out.println(l.getlargeurContenue()+" "+l.getSize()+"et");
			
			
			//ajouter1...
		}
		
		return l;
		
	}
	
	
	
	public static void main(String[] args){
		System.out.println("salut");
		Gson gson = new Gson() ;	
		
		try { 
		Generer obj = gson.fromJson(new FileReader("/Users/samuelbell-bell/Desktop/PSTL/exemplepart2.json"),Generer.class);
		 System.out.println(gson.toJson(obj));
		//System.out.println(obj);
		 SuperOrganisme su = obj.genererSuperOrg();
		 
		System.out.println("su0: "+su);
		Cellule test  = su.getOrganismes().get(0).getCellules().get(0);
		 System.out.println("su: "+su);
			Ligne l=fillDynamique( 800 , su);
			System.out.println("ok et l:"+l.getlargeurContenue()+" "+l.getSize());
			 System.out.println(l.getlargeurContenue() + "et" + l.getSize());
			 try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Ligne l2=Completion(l);
			 System.out.println(l2.getlargeurContenue() + "et" + l2.getSize());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	/*public int algorithmCompletion(Ligne l , SuperOrganisme so, int nbCel){
		//tant qu'on trouve pas un satisfaisable
		while(true){
			//so.size la taille du nbCel choisis
			if(so.size(nbCel) < l.getSize()sizeL){
				SuperOrganisme representationso = so.enlarge;
				break;
			}
			else {
			nbCel-=1;
			}
		}
		
		
		return sizeL;
	}*/
	
	/*nous fairons aussi un algorithme qui permet de trait� le cas ou on a plus de ligne est qu'on peut rappetir.
	 * 
	 * 
	 */
	
}
