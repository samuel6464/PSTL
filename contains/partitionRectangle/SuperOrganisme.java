package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import algorithm.ArbreGeneraux;
import algorithm.BreakLine;
import algorithm.DynamiqueSimple;
import algorithm.DynamiqueTrue;

import outils.Outils;

import view.SuperOrganismeObserveurSwing;

public class SuperOrganisme extends RectangleDrawable {

	
	//liste de cellule (mesure)
	private boolean space;
	private ArrayList<Organisme> organismes;
	/**
	 * @param args
	 */
public SuperOrganisme(Color color, Point pos, Dimension dim,ArrayList<Organisme> organismes) {
		
		super(color, pos, dim);
		setSpace(false);
		this.organismes =organismes;
		// TODO Auto-generated constructor stub
	}
public SuperOrganisme( Point pos, Dimension dim,ArrayList<Organisme> organismes) {
	
	super(Color.black, pos, dim);
	setSpace(false);
	this.organismes =organismes;
	// TODO Auto-generated constructor stub
}
	public void draw(Graphics g) {
		//Iterator<Atome> oki = this.atomes.iterator();
		
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		g.setColor(c);
		//permet pas de mettre de condition
		//on peut v�rifier si la position du rectangle n'est pas a l'int�rieur 
		//du rectangle
		
		//v�rifi� s'il ne s'intersecte pas avec le pr�c�dent ici?
		Organisme prec=null;
		for (Organisme u: this.organismes)
		{
		    //check is conflit???----->iterator a que next
			//cr�ation de notre propre it�rator qui ce souvient du dernier?
			//variable atome qui check ! (moins de place ou egal...)
			//sauf pratique notre own iterator si besoin de checker le
			//pr�c�dent du pr�c�dent...besoin?
			
			
			if(prec!=null){
				
				//intersection minimal entre les mesure (coller...?)
				if(u.getRectangle().intersects(prec.getRectangle())){
					//Exception exception;
					//throw exception;
					System.out.println("conflit intersection entre deux organismes");
				}
			}
			//System.out.println(prec + " "+ u);
			prec = u;
			
			u.draw(g);
		 	
		}
	}
	public ArrayList<Organisme> getOrganismes() {
		return organismes;
	}
	public void setOrganismes(ArrayList<Organisme> organismes) {
		this.organismes = organismes;
	}
	
	public String toString(){
		String s = null;
		for(Organisme e : organismes){
			 s += e.toString()+" ";
		}
		return s;
	}
	
	public void drawSpace(Graphics g){
		int cpt=0;
		setSpace(true);
		
		for (Organisme a : this.organismes){
			if(a.getCellules() !=null)
				for(Cellule b : a.getCellules()){
					for(RectangleDrawable c: b.getAtomes_molecules()){
					
						if(c instanceof Molecule){
							for(Atome d : ((Molecule) c).getAtomes()){
								if(cpt==0){
									
									d.getPrec().draw(g);
									cpt++;
								}
								d.getSuiv().draw(g);
								
							}
						}else{
							if(cpt==0){
								((Atome) c).getPrec().draw(g);
								cpt++;
							}else{
								((Atome) c).getSuiv().draw(g);
							}
						}
					}
				}
			}
		}
	
	public static SuperOrganisme exempleSuperOrganisme(){
		Dimension dim  =new Dimension(10,10);
		Dimension dim2  =new Dimension(100,20); // largeur,hauteur width/height
		Dimension dim3  =new Dimension(200,40);
		Dimension dim4  =new Dimension(700,80);
		Dimension dim5  =new Dimension(800,800);
		
		ArrayList<Organisme> organismes= new ArrayList<Organisme>();
		//getter setter des arrayList => ok. and needed
		SuperOrganisme sorg = new SuperOrganisme(new Point(20,30),dim5,organismes);
		
		ArrayList<Cellule> cellules= new ArrayList<Cellule>();
		
		
		Organisme org = new Organisme(new Point(30,60),dim4,cellules );
		Organisme org2 = new Organisme(new Point(30,200),dim4,null);
		
		ArrayList<RectangleDrawable> molAtomes1= new ArrayList<RectangleDrawable>();
		ArrayList<RectangleDrawable> molAtomes2= new ArrayList<RectangleDrawable>();
		 
		Cellule cel = new Cellule(new Point(org.getRectangle().x + 10,org.getRectangle().y + 10), dim3,molAtomes1 );
		//300 diff�rence entre chaque cellule ici pour l'instant...2px pour separation des mesure...
		Cellule cel2 = new Cellule(new Point(org.getRectangle().x +380,org.getRectangle().y +10), dim3,molAtomes2 );
		//jc.addDrawable(rect);
		
		
		ArrayList<Atome> atomes= new ArrayList<Atome>();
	
		
		
		ArrayList<Atome> atomes2= new ArrayList<Atome>();
		

		Molecule mol =new Molecule( new Point(cel.getRectangle().x + 10,cel.getRectangle().y + 10), dim2, atomes);
		Molecule mol2 =new Molecule( new Point(cel2.getRectangle().x + 10,cel2.getRectangle().y + 10), dim2, atomes2);
		
		Atome atome = new Atome(new Point(mol.getRectangle().x + 10,mol.getRectangle().y + 10),dim);
		Atome atome1 = new Atome(new Point(mol.getRectangle().x + 40,mol.getRectangle().y + 10),dim);
		Atome atome2= new Atome(new Point(mol.getRectangle().x + 80,mol.getRectangle().y + 10),dim);
		Atome atome3 = new Atome(new Point(cel.getRectangle().x + 180,cel.getRectangle().y + 20),dim);
		Atome atome4 = new Atome(new Point(mol2.getRectangle().x + 10,mol2.getRectangle().y + 10),dim);
		Atome atome5 = new Atome(new Point(mol2.getRectangle().x + 50,mol2.getRectangle().y + 10),dim);
		Atome atome6 = new Atome(new Point(mol2.getRectangle().x + 80,mol2.getRectangle().y + 10),dim);
		
		
		Espace esp0 = new Espace(cel , atome);
	
		Espace esp1 = new Espace(atome ,atome1);
		Espace esp2 = new Espace(atome1 ,atome2);
		Espace esp3 = new Espace(atome2 ,atome3);
		Espace esp4 = new Espace(atome3 ,atome4);
		Espace esp5 = new Espace(atome4 ,atome5);
		Espace esp6 = new Espace(atome5 ,atome6);
		Espace esp7 = new Espace(atome6 ,cel2);
		//System.out.println("esp7  "+esp7.rect.width + "and" +cel2.rect.width);
		
		atome.init(esp0, esp1);
		atome1.init(esp1, esp2);
		atome2.init(esp2, esp3);
		atome3.init(esp3, esp4);
		atome4.init(esp4, esp5);
		atome5.init(esp5, esp6);
		atome6.init(esp6, esp7);
		
		//CoupleEspace ces1 = new CoupleEspace(esp1,esp2);
		
		//sorted map de nos atomes1 avec leur espace coupler
		//SortedMap<Atome, CoupleEspace> test = new TreeMap<Atome, CoupleEspace>() ;
		//test.put(atome1, ces1);
		
		
		
		mol.getAtomes().add(atome);
		mol.getAtomes().add(atome1);
		mol.getAtomes().add(atome2);
		
		
		
		mol2.getAtomes().add(atome4);
		mol2.getAtomes().add(atome5);
		mol2.getAtomes().add(atome6);
		
		
		
		cel.getAtomes_molecules().add( mol);
		cel.getAtomes_molecules().add(atome3);
		
		cel2.getAtomes_molecules().add(mol2);
		
		org.getCellules().add(cel);
		org.getCellules().add(cel2);
		
		sorg.getOrganismes().add(org);
		sorg.getOrganismes().add(org2);
		
		return sorg;
		
	
	}
	
	public int getSizeMoleculeMN(int m,int n){
		//on r�cupere la taille cellule n de la voix m
		return this.getOrganismes().get(m).getCellules().get(n).getRectangle().width ;
	}
	
	public boolean getSpace() {
		return space;
	}
	public void setSpace(boolean space) {
		this.space = space;
	}
	
	public void addBreaktoHMCel(HashMap<Integer, Integer> maMap){
		for(Organisme o : this.organismes){
			o.addBreaktoHMCel(maMap);
		}	
	}
	
	public void addBreaktoHMG(HashMap<Integer, Integer> maMap){
		for(Organisme o : this.organismes){
			o.addBreaktoHMG(maMap);
		}	
	}
	
	public void showBreakMesure(Graphics g){
		this.organismes.get(0).BreakLineMesure(g);
	}
	public void showBreak(Graphics g){
		for(Organisme o : this.organismes){
			o.BreakLine(g);
		}
	}
	
	public void showPossibleBreakLine(Graphics g){
		if(this.organismes.size()<=1){
			showBreakMesure(g);
		}else{
			showBreak(g);
		}
	}
	//FONCTION CONSTRUISANT HASHMAP !
	public HashMap<Integer, Integer>  FillHM(HashMap<Integer, Integer> maMap){
		if(this.organismes.size()<=1){
			addBreaktoHMCel(maMap);
		}else{
			addBreaktoHMG(maMap);
		}
		return maMap;
	}
	
	public void showOptimalBreakOnS(Graphics g){
		HashMap<Integer, Integer> maMap = new HashMap<Integer, Integer>();
		FillHM(maMap);
		
		int maximun = Collections.max(maMap.values())  ;
		int realMax = Outils.getKeyByValue(maMap, maximun);
		Rectangle rect = new Rectangle(new Point(realMax,this.rect.y),new Dimension(1,600));
		g.setColor(Color.decode("#00FF000"));
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		
	}
	
	public void showOptimalBreakOnMonophonie(Graphics g,int sizeLine){
		
		
		ArrayList<Integer> test= DynamiqueSimple.FindBreakGreedy(sizeLine, this);
		int i =0;
		while(i<test.size()){
			test.get(i);
			Rectangle rect = new Rectangle(new Point(test.get(i),this.rect.y),new Dimension(1,600));
			g.setColor(Color.decode("#00FF000"));
			g.fillRect(rect.x,rect.y,rect.width,rect.height);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
		System.out.println("finish");
	}
	
	
	
	
	public void showOptimalBreakEach(Graphics g,int sizeLine){
		
		HashMap<Integer, Integer> maMap = new HashMap<Integer, Integer>();
		FillHM(maMap);
		 Object[] keyset=maMap.keySet().toArray();
		 int last = (Integer) keyset[keyset.length-1];
		 int previousBreak=0;
		 int actualBreak = 0;
		// int lastMesure = keyset;
		 int best = 0;
		 int posBest=0;
		 int i=0;
		 
		 int[] listeClef = new int[keyset.length];
		 System.out.println("LOL "+keyset.length);
		 //on transforme le tableau d'objet en tableau d'entier
		 for(i=0;i<keyset.length ;i++)
			 listeClef[i] = (Integer) keyset[i];
		 
		//on ordonne le tableau obtenu
		 Arrays.sort(listeClef);
		 
		for(i=0;i<listeClef.length ;i++)
			 System.out.println("THERE "+listeClef[i]);
		
		 
		 i=0;
		 //tant qu'on est pas supérieur au dernier break disponible
		 while(previousBreak < last){
			 //on prend le i-eme break courant
			 actualBreak =  listeClef[i];
			 best=0;
			 posBest=0;
			 //pour chaque break , le meilleur pour la ligne s'obtient :
			 while(actualBreak < previousBreak+sizeLine &&i<listeClef.length-1){
				
				 System.out.println("ZLATAN: " +actualBreak);
				 if( maMap.get(actualBreak)>best ){
					 best = maMap.get(actualBreak); //best et le score du meilleur
					 posBest = i;
				 }
				 i++;
				 actualBreak =  listeClef[i];
				 System.out.println("ZLATAN2 -> " +actualBreak);
			 }
			 if(i==listeClef.length-1){System.out.println("pass");break;}
			 System.out.println("AFTERWHILEINSIDE " +actualBreak +"AND "+ posBest);
			 i=posBest;
			 //on recupere le meilleur break disponible pour cette ligne puis on passe a la ligne suivant 
			 //a partir du break suivant
			 previousBreak= listeClef[i];
			 Rectangle rect = new Rectangle(new Point(previousBreak,this.rect.y),new Dimension(1,600));
			 g.setColor(Color.decode("#00FF000"));
			 g.fillRect(rect.x,rect.y,rect.width,rect.height);
			 //on commencera au break suivant dans la suite du while1
			 i++;
		 }
		 Rectangle rect = new Rectangle(new Point(listeClef[listeClef.length-1],this.rect.y),new Dimension(1,600));
		 g.setColor(Color.decode("#00FF000"));
		 g.fillRect(rect.x,rect.y,rect.width,rect.height);
		 System.out.println("et voilà !");
	}

//où begin est
	//la position du dernier breakLine choisis
public boolean estAccessible(BreakLine breakline,int sizeLine,int begin){
	boolean a = false;
	
	if(breakline.getPosition() < sizeLine+begin && breakline.getPosition() > sizeLine+sizeLine/2+begin){
		
		
	}else{
		for(int i=0;i<breakline.getNumber();i++){
			
		}
			 //allneedNecessary
	
	}
	return a;
}
	
public void showOptimalBreakEachDynamique(Graphics g,int sizeLine){
		
		HashMap<Integer, Integer> maMap = new HashMap<Integer, Integer>();
		FillHM(maMap);
		 Object[] keyset=maMap.keySet().toArray();
		 int last = (Integer) keyset[keyset.length-1];
		 int previousBreak=0;
		 int actualBreak = 0;
		// int lastMesure = keyset;
		 int best = 0;
		 int posBest=0;
		 int i=0;
		 
		 int[] listeClef = new int[keyset.length];
		 System.out.println("LOL "+keyset.length);
		 //on transforme le tableau d'objet en tableau d'entier
		 for(i=0;i<keyset.length ;i++)
			 listeClef[i] = (Integer) keyset[i];
		 
		//on ordonne le tableau obtenu
		 Arrays.sort(listeClef);
		 //on créé une ArrayList de Breaks
		 ArrayList<BreakLine> breaks = new ArrayList<BreakLine>();
		 
		 for(i=0;i<listeClef.length ;i++){
			int position = listeClef[i];
			int weight = maMap.get(position);
			float zoom= (float) 1.0;
			breaks.add(new BreakLine(position,weight,zoom,i));
		 }
		 //j'ai mon arrayList de Break
		 //je vais construire un arbre générale de solution manière dynamique...
		 ArbreGeneraux solution = new ArbreGeneraux(0);
		 
		 //je cherche a MAXIMISER LE SCORE DES BREAK
		 
		 //OBS//Au préalable je dois calculer, l'ensemble des appels possible 
		 //par itération du probleme...
		 
		 
		 
		 
		 
		 
		/*for(i=0;i<listeClef.length ;i++)
			 System.out.println("THERE "+listeClef[i]);
		*/
		 
		 i=0;
		 BreakLine actBreak;
		 //tant qu'on est pas supérieur au dernier break disponible
		 while(previousBreak < last){
			 //on prend le i-eme break courant
			 actBreak = breaks.get(0);
			 actualBreak =  breaks.get(0).getPosition();
			 //best=0;
			 //posBest=0;
			 //pour chaque break , le meilleur pour la ligne s'obtient :
			 
			 //on va construire l'arbre des possibilité de la premier itération
			 ArrayList<Integer> options =new ArrayList<Integer>();
			 //Puis on prendra le max parmis toutes les options...
			 while(actualBreak < previousBreak+sizeLine &&i<listeClef.length-1){
				
			 }
		 }
	
		
	}

public void showOptimalBreakDyn(Graphics g){
	HashMap<Integer, Integer> maMap = new HashMap<Integer, Integer>();
	FillHM(maMap); //position in coordonnee , W
	 Object[] keyset=maMap.keySet().toArray();

	 int[] listeClef = new int[keyset.length];
	
	 //on transforme le tableau d'objet en tableau d'entier
	 for(int i=0;i<keyset.length ;i++)
		 listeClef[i] = (Integer) keyset[i];
	 
	 int poids[]= new int[keyset.length];
	 
		//on ordonne le tableau obtenu
	 Arrays.sort(listeClef);
	 //on créé une ArrayList de Breaks
	 ArrayList<BreakLine> breaks = new ArrayList<BreakLine>();
	 HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
	 
	 for(int i=0;i<listeClef.length ;i++){
			int position = listeClef[i];
			poids[i] = maMap.get(position);
			float zoom= (float) 1.0;
			breaks.add(new BreakLine(position,poids[i],zoom,i));
			positionMap.put(i, position);
	}
	 
	
	int[] s=new int[poids.length]; //initialise tout les max compute a 0
	/*for(int i=0 ;i<poids.length ;i++){
		s[i]=-2;
		if(i==poids.length-1)s[i]=0; //le dernier element est a 0
	}*/
	int[] Q=new int[poids.length]; //initialise tout la reponse décidé  a 0
	int sizeLine = 300;
	DynamiqueTrue.findBreakOpt(poids,sizeLine,(float)0.8,poids.length,0,s, positionMap,Q);
	
	for(int i=0;i<Q.length ;i++){
		 if(Q[i]!=0){
			 Rectangle rect = new Rectangle(new Point(Q[Q.length-1],this.rect.y),new Dimension(1,600));
			 g.setColor(Color.decode("#00FF000"));
			 g.fillRect(rect.x,rect.y,rect.width,rect.height);
		 }
	}
	//afficher le dnr break qui est a zero
	Rectangle rect = new Rectangle(new Point(Q[Q.length-1],this.rect.y),new Dimension(1,600));
	 g.setColor(Color.decode("#00FF000"));
	 g.fillRect(rect.x,rect.y,rect.width,rect.height);
}

public void showOptimalBreakDynEditor(Graphics g){
	

	 int[] listeClef = {10,200,1100};
	
	
	 
	 int poids[]= {1,2,3};
	 
		//on ordonne le tableau obtenu
	 //Arrays.sort(listeClef);
	 //on créé une ArrayList de Breaks
	
	 HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
	 
	 for(int i=0;i<listeClef.length ;i++){
			int position = listeClef[i];
			
			positionMap.put(i, position);
	}
	 
	
	int[] s=new int[poids.length]; //initialise tout les max compute a -2
	for(int i=0 ;i<poids.length ;i++){
		s[i]=0;
		if(i==poids.length-1)s[i]=0; //le dernier element est a 0
	}
	int[] Q=new int[poids.length]; //initialise tout la reponse décidé  a 0
	int sizeLine = 1000;
	DynamiqueTrue.findBreakOpt(poids,sizeLine,(float)1.0,poids.length,0,s, positionMap,Q);
	
	for(int i=0;i<Q.length ;i++){
		 if(Q[i]!=0){
			 Rectangle rect = new Rectangle(new Point(Q[Q.length-1],1000),new Dimension(1,600));
			 g.setColor(Color.decode("#00FF000"));
			 g.fillRect(rect.x,rect.y,rect.width,rect.height);
		 }
	}
	//afficher le dnr break qui est a zero
	Rectangle rect = new Rectangle(new Point(Q[Q.length-1],1000),new Dimension(1,600));
	 g.setColor(Color.decode("#00FF000"));
	 g.fillRect(rect.x,rect.y,rect.width,rect.height);
}

public void showOptimalBreakDynEditorWithOutGraphism(){
	

	 int[] listeClef = {100,200,900,1700,1800,2600,2700,3400,3500};
	
	
	 
	// int poids[]= {1,2,3,3,5,2,3,2};
	 int poids[]= {2,2,2,2,3,2,2,2,3};
	 
		//on ordonne le tableau obtenu
	 //Arrays.sort(listeClef);
	 //on créé une ArrayList de Breaks
	
	 HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
	 
	 for(int i=0;i<listeClef.length ;i++){
			int position = listeClef[i];
			
			positionMap.put(i, position);
	}
	 
	
	int[] s=new int[poids.length]; //initialise tout les max compute a -2
	for(int i=0 ;i<poids.length ;i++){
		s[i]=-poids.length;
		if(i==poids.length-1)s[i]=0; //le dernier element est a 0
	}
	int[] Q=new int[poids.length]; //initialise tout la reponse décidé  a 0
	int sizeLine = 1000;
	System.out.println("before fonct");
	DynamiqueTrue.findBreakOpt(poids,sizeLine,(float)1.0,poids.length,0,s, positionMap,Q);
	System.out.println("after fonct");
	for(int i=0;i<s.length ;i++){
		
			System.out.println(s[i]);
		
		
	}
	 System.out.println("Q");
	for(int i=0;i<Q.length ;i++){
		 if(Q[i]!=0){
			System.out.println(Q[i]);
		 }
		 //System.out.println("I PASS");
	}
	//afficher le dnr break qui est a zero
	System.out.println("last = 0");
}

public static void main(String[]args){
	//Graphics g = ;
	SuperOrganisme s= new SuperOrganisme(Color.black, new Point(1,1), new Dimension(1000,1000),null);
	 s.showOptimalBreakDynEditorWithOutGraphism();
}
}


	



