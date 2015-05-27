package parser;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import partitionRectangle.Atome;
import partitionRectangle.Cellule;
import partitionRectangle.Espace;
import partitionRectangle.Molecule;
import partitionRectangle.Organisme;
import partitionRectangle.RectangleDrawable;
import partitionRectangle.SuperOrganisme;

public class Generer {

	private Partition partition;

	public Partition getPartition() {
		return partition;
	}

	public void setPartition(Partition partition) {
		this.partition = partition;
	}
	
	public SuperOrganisme genererSuperOrg(){
		
		SuperOrganisme su = null;
		//tant qu'il existe des atomes...?
		//for(){}
		//if(instanceof Ligne)
		//for nbMesure
		//pour un atome seul dans une mesure
		//((Atomee)((Ligne)this.getPartition().getPolyphonie().getSystemes().get(index)).getMesure().get(index)).getPositionDebut()
		int nbligne=0 ;
		int i=0;
		int atomeDebHauteur=100;
		//arraylist de variable obligatoire en java pas de nommage dynamique
		List<Atome> mesAtomes = new ArrayList<Atome>();
		List<Molecule> mesMolecules = new ArrayList<Molecule>();
		List<Cellule> mesCellules = new ArrayList<Cellule>();
		ArrayList<Organisme> mesOrganisme = new ArrayList<Organisme>();
		int largeurTotal =0;
		int largeurOrg =0;
		for(System_Interligne s : this.getPartition().getPolyphonie().getSystemes() ) {
			if(s.getSystem()!=null){
				nbligne++;
				System.out.println("je suis un system !");
				for(Ligne l : s.getSystem().getLigne()){
					//nbLigne sera notre facteur multiplicatif pour construire nos "voix"
					
					ArrayList<Cellule> cel1 =new ArrayList<Cellule>();
					for(Mesure m : l.getMesure()){
						ArrayList<RectangleDrawable> mol_at = new ArrayList<RectangleDrawable>();
						int largeurCel=0;
						 System.out.println("PASSmolat in generer");
					   for(GrpNotes_Note grn_n : m.getgrpNote_Note()){
						   
						   
						   if(grn_n.getGrpn()!=null){
							   ArrayList<Atome> atomesMolaire = new ArrayList<Atome>();
							   for(Atomee at: grn_n.getGrpn().getAtomes()){
								 //recupere Atome a partir d'un grp_note 
								   int largeur = at.getPositionFin()-at.getPositionDebut();
								   Dimension dimension= new Dimension(largeur,10);
								   Point point = new Point(at.getPositionDebut(),atomeDebHauteur*nbligne);
								   Atome atm =new Atome(point,dimension);
								   mesAtomes.add(atm);
								   atomesMolaire.add(atm);
								  
							   }
							   System.out.println("ICI0_0 "+largeurCel);
							   int largeurMol=0;
							   for(int i1=0;i1<atomesMolaire.size();i1++){
								   //calcul la largeur d'une mol�cule
								   if(i1<atomesMolaire.size()-1){
									   Espace e=new Espace(atomesMolaire.get(i1),atomesMolaire.get(i1+1));
									   largeurMol+=(int)atomesMolaire.get(i1).getRectangle().getWidth()+e.getRectangle().getWidth();
								   }
								   else{
									   //le dernier element de ma molecule n'a pas d'espace apres 
									   largeurMol+=(int)atomesMolaire.get(i1).getRectangle().getWidth();
								   }
								   
								   //on retrouve le num a partir d'atomeMolaire dans la liste mesAtomes representant
								   //l'ensemble de nos atomes...
								   int num = mesAtomes.indexOf(atomesMolaire.get(i1));
								   if(num>0){
									  
								   Espace e1=new Espace(mesAtomes.get(num-1),mesAtomes.get(num));
								   if((int)e1.getRectangle().getWidth()>0){
								   //System.out.println("ICI 0_1 le num"+num+ "et" +(int)atomesMolaire.get(i1).getRectangle().getWidth()+" and "+(int)e1.getRectangle().getWidth());
								   largeurCel +=(int)atomesMolaire.get(i1).getRectangle().getWidth()+(int)e1.getRectangle().getWidth();
								   //System.out.println("ICI0 "+largeurCel);
								   }else{largeurCel +=(int)atomesMolaire.get(i1).getRectangle().getWidth();}
								   }else{
									   largeurCel +=(int)atomesMolaire.get(i1).getRectangle().getWidth();
									   System.out.println("ICI1 "+largeurCel);
								   }
								   
								  }
							   
							  Molecule mol1= new Molecule(new Point((int) atomesMolaire.get(0).getRectangle().getX(),atomeDebHauteur*nbligne-10),
									   new Dimension(largeurMol,25) ,
									   atomesMolaire);
							   mesMolecules.add(mol1);
							   
							   mol_at.add(mol1);
						   }
						   if(grn_n.getAtm()!=null){
							   //si dans une mesure ce n'est pas un groupe de note mais juste une note
							   Atomee a = grn_n.getAtm();
							   int largeur = a.getPositionFin()-a.getPositionDebut();
							   Dimension dimension= new Dimension(largeur,10);
							   Point point = new Point(a.getPositionDebut(),atomeDebHauteur*nbligne);
							   Atome atm= new Atome(point,dimension);
							   mesAtomes.add(atm);
							   mol_at.add(atm);
							   //on recuper dans l'index de tout les atome notre atome
							   int num = mesAtomes.indexOf(atm);
							   Espace e1=new Espace(mesAtomes.get(num-1),mesAtomes.get(num));
							   largeurCel +=(int)atm.getRectangle().getWidth()+(int)e1.getRectangle().getWidth();
							   System.out.println("ICI2 "+largeurCel);
						   }
					   }
					   Cellule m1 = new Cellule(new Point((int)mol_at.get(0).getRectangle().getX(),atomeDebHauteur*nbligne-20), 
							   new Dimension(largeurCel,50),mol_at);
					//   System.out.println("MA MESURE EST CREE");
					   mesCellules.add(m1);
					   cel1.add(m1);
					   largeurOrg +=largeurCel; 
					   
					}
					//construire organisme
					Organisme org = new Organisme(new Point((int)cel1.get(0).getRectangle().getX(),atomeDebHauteur*nbligne-40),new Dimension(largeurOrg+1000,80),cel1);
					mesOrganisme.add(org);
				}
				//construire les espace entre les atomes...?
				int nb=0;
				for(Atome At : mesAtomes){
					//monophonie is provisoire !
				
					if(nb>=mesAtomes.size()-1){
						//monophonie is provisoire !
					
						Espace esp1 = new Espace(mesAtomes.get(nb-1),mesAtomes.get(nb));
						
						//recuperer la cellule qui contient l'atome qui nous interesse
						// ou provisoire ne marche pas pour les poliphonie
						Espace esp2 = new Espace(mesAtomes.get(nb),mesCellules.get(mesCellules.size()-1));
						mesAtomes.get(nb).init(esp1, esp2);
						break;
					}
					if(nb==0){//construire le premier espace entre le premier et dernier
						Espace esp2 = new Espace(mesAtomes.get(nb),mesAtomes.get(nb+1));
						Espace espo = new Espace(mesCellules.get(0),mesAtomes.get(nb));
						System.out.println("espo "+espo+"\n\n\n");
							mesAtomes.get(nb).init(espo, esp2);
							nb++;
					}
					Espace esp1 = new Espace(mesAtomes.get(nb-1),mesAtomes.get(nb));
					Espace esp2 = new Espace(mesAtomes.get(nb),mesAtomes.get(nb+1));
				
						mesAtomes.get(nb).init(esp1, esp2);
						nb++;
					
					
				}
				//construire superOrganisme...
				 su= new SuperOrganisme(new Point((int)mesOrganisme.get(0).getRectangle().getX(),0),new Dimension(largeurOrg+4000,2000),mesOrganisme);
				 System.out.println("\n\n coucou \n"+su+"\n\n");
			}
			if(s.getInterligne()!=null){
				//rien a faire
				System.out.println("je suis une interligne !");
			}
			
		}
		System.out.println(su.toString()+"end");
		
		return su;
	
	}
	
	
}
