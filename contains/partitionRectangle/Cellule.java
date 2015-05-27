package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import outils.Outils;

import exception.AtomeConflitException;
import exception.LimitCelluleException;
import exception.LimitMoleculeException;

public class Cellule extends RectangleDrawable {
	
	//peut contenir des atomes et des cellules , des types fils de rectangles drawable 
	//comment faire pour limiter...?pas grave mais bon...
	
	private ArrayList<RectangleDrawable> atomes_molecules;
	boolean flagIsClicked ;
	int ExceptionAtomeLastAtome;
	
	
public Cellule(Color color, Point pos, Dimension dim,ArrayList< RectangleDrawable> atomes_molecules) {
		
		super(color, pos, dim);
		flagIsClicked = false;
		this.atomes_molecules =atomes_molecules;
		
		
		// TODO Auto-generated constructor stub
	}

	public Cellule( Point pos, Dimension dim,ArrayList< RectangleDrawable> atomes_molecules) {
		
		super(Color.decode("#1E5474"), pos, dim);
		//super(Color.decode("#111111"), pos, dim);
		flagIsClicked = false;
		this.atomes_molecules =atomes_molecules;
		//this.getRectangle().getFrame();
		// this.addMouseListener();
	     //   this.addMouseMotionListener(this);
		// TODO Auto-generated constructor stub
	}
	
	public void draw(Graphics g) {
		//Iterator<Atome> oki = this.atomes.iterator();
		System.out.println("YO");
		Color c = g.getColor();
		g.setColor(color);
		System.out.println("SALUT "+ rect.x + rect.y +"width: "+rect.width + " height"+ rect.height);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		g.setColor(c);
		System.out.println("YOO");
		
		Rectangle rect = new Rectangle(new Point(this.GetEndMesure(),this.rect.y),new Dimension(1,100));
		
		g.fillRect(rect.x,rect.y,Math.abs(rect.width),rect.height);
		g.setColor(Color.decode("#FFFFFF"));
		
		
		//draw END MESURE en noire :  
		
		
		//permet pas de mettre de condition
		//on peut v�rifier si la position du rectangle n'est pas a l'int�rieur 
		//du rectangle
		
		//v�rifi� s'il ne s'intersecte pas avec le pr�c�dent ici?
		RectangleDrawable prec=null;
		for (RectangleDrawable u: this.atomes_molecules)
		{
		    //check is conflit???----->iterator a que next
			//cr�ation de notre propre it�rator qui ce souvient du dernier?
			//variable atome qui check ! (moins de place ou egal...)
			//sauf pratique notre own iterator si besoin de checker le
			//pr�c�dent du pr�c�dent...besoin?
			
			if(prec==null)u.draw(g);
	        if(prec!=null){
	        	if(u instanceof Molecule){
	        		//((Molecule) u).drawOnly(g);
	        	}else {
	        		System.out.println("je suis un atomeeeeeeee!!!!");
	        	}
	        	if(u instanceof Atome && prec instanceof Molecule){
	        		boolean isExcept= false;
	        		try{
	        			//on regarde si l'atome n'est pas en conflit avec le dernier element de molecule
	        			Atome last = ((Molecule)prec).getAtomes().get(((Molecule)prec).getAtomes().size() -1);
	        			checkConflit((Atome) u,(Atome)last);
	        			
	        		}catch(AtomeConflitException e){
	        			isExcept=true;
	        			System.out.println("les atomes sont en conflit1 cellllulle\n\n\n\n");
	        		}
	        		//if(!isExcept){u.draw(g);
	        		//System.out.println("pasdeconflit");
	        		//}
	        		u.draw(g);
	        	}
	        	if(u instanceof Atome && prec instanceof Atome){
	        		boolean isExcept= false;
	        		try{
	        			checkConflit((Atome) u, (Atome)prec);
	        			
	        		}catch(AtomeConflitException e){
	        			isExcept =true;
	        			System.out.println("les atomes sont en conflit2 cel->atomesss");
	        		}
	        		if(!isExcept)u.draw(g);
	        	}
	        	if(u instanceof Molecule && prec instanceof Atome){
	        		Atome first = ((Molecule)prec).getAtomes().get(0);
	        		//deux couche de molecule...
	        		boolean isExcept = false;
	        		try{
	        			
	        			//on regarde si le premier atome de la molecule courante n'est pas en conflit avec l'atome
        			checkConflit((Atome)((Molecule)u).getAtomes().get(0), (Atome)first);
	        			
	        		}catch(AtomeConflitException e){
	        			isExcept = true;
	        			System.out.println("les atomes sont en conflit3");
	        		}
	        		//if(!isExcept)u.draw(g);
	        		u.draw(g);
	        	}
	        }
			prec = u;
			
			
		}
	}

	public ArrayList< RectangleDrawable> getAtomes_molecules() {
		return atomes_molecules;
	}

	public void setAtomes_molecules(
			ArrayList< RectangleDrawable> atomes_molecules) {
		this.atomes_molecules = atomes_molecules;
	}
	
	public int changeSizeEndRect(int nbPixel){
		//diminuer par la droite
		this.rect.setSize(this.getRectangle().width,this.getRectangle().height+nbPixel);
		return nbPixel;
	}
	
	public void checkConflit(Atome actu,Atome prec) throws AtomeConflitException{
		boolean intersects = actu.getRectangle().intersects(prec.getRectangle());
		System.out.println("intersect is "+ intersects);
		if(intersects){
		int a=actu.rect.width+actu.rect.x;
		int o = prec.rect.width+prec.rect.x;
		System.out.println("actu"+"xdeb:"+actu.rect.x +"end"+a);
		System.out.println("prec"+"xdeb:"+prec.rect.x +"end"+o);
		throw new AtomeConflitException("Il y a conflit entre moi est mon atome pr�c�dent");
		}
	}
	

	
	public int nombreAtomeContain(){
		int cpt=0;
		for(RectangleDrawable a : this.atomes_molecules){
			if(a instanceof Molecule){
				cpt+=((Molecule)a).getAtomes().size();
			}else {
				cpt++;
			}
		}
		return cpt;
	}
	public int changeZoomInPixel(int nbPixel){
		//A faire : gerer les collision
		//gerer la repartition des pixel nbPixel/atomes.size()
		int size =this.nombreAtomeContain();
		Atome precAt=null;
		int i=0;
		RectangleDrawable prec=null;
		ExceptionAtomeLastAtome=0;
		for(RectangleDrawable a : this.atomes_molecules){
			
			//les espaces suivant les atomes change
			
			if(prec==null){
			if(a instanceof Molecule){
					//chaque sous liste (les molecules) appelle leur propre zoom
					
						try {
							((Molecule) a).changeZoomInPixel(nbPixel);
						} catch (AtomeConflitException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			 }else{
			((Atome)a).move(nbPixel);
			System.out.println("coucou1");
			 }
		 }	 
		  if(prec!=null){
			if(a instanceof Molecule){
				//chaque sous liste (les molecules) appelle leur propre zoom
				
					try {
						((Molecule) a).changeZoomInPixel(nbPixel);
					} catch (AtomeConflitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}else{
				//a est du type atome
				if(prec instanceof Atome){
					boolean ismoveExc=false;
					try{
						checkConflit((Atome) a, (Atome)prec);
					}catch(AtomeConflitException e3){
						ismoveExc=true;
					}
					if(!ismoveExc)
						((Atome)a).move(nbPixel);
					else{
						((Atome)a).move(-nbPixel*2);
					}
				}
				if(prec instanceof Molecule){
					
					Atome last=((Molecule)prec).getAtomes().get(((Molecule)prec).getAtomes().size() -1);
					boolean ismoveExc=false;
				try{
				//((Atome)a).move(nbPixel);
					
				checkConflit((Atome) a, (Atome)last);
				}catch(AtomeConflitException e3){
					ismoveExc=true;
					System.out.println("moveproblem////////\n\n\n\n\n");
				}
				if(!ismoveExc){
					int al=a.rect.width+a.rect.x;
					int o = prec.rect.width+prec.rect.x;
					System.out.println("actu"+"xdeb:"+a.rect.x +"end"+al+"yoyoyo");
					System.out.println("prec"+"xdeb:"+prec.rect.x +"end"+o+"oyoyo");
					((Atome)a).move(nbPixel);
				}
				}
			
				}
		
			}
		prec=a;		
		}
		
		RectangleDrawable s;
		Atome dnrAt;
		try {
			checkLastIn(this,this.getAtomes_molecules().get(this.getAtomes_molecules().size()-1));
			this.rect.setSize(this.getRectangle().width+nbPixel,this.getRectangle().height);
		} catch (LimitCelluleException e) {
			if(ExceptionAtomeLastAtome==1)
			this.rect.setSize(this.getRectangle().width-nbPixel*3,this.getRectangle().height);
		}
		
		return nbPixel;
	}
	
	private void checkLastIn(Cellule cel, RectangleDrawable atome) throws  LimitCelluleException {
		if(!this.rect.contains(atome.rect)){
			RectangleDrawable  RD = this.getAtomes_molecules().get(this.getAtomes_molecules().size()-1);
			RectangleDrawable Prec =  this.getAtomes_molecules().get(this.getAtomes_molecules().size()-2);
			if(RD instanceof Atome){
				if(Prec instanceof Atome){
					if(RD.rect.intersects(Prec.rect)){
						throw new LimitCelluleException("limit cel");
					}
				}else{
					//Prec est de type Molecule
					Atome last = ((Molecule)Prec).getAtomes().get(((Molecule)Prec).getAtomes().size()-1);
					if(RD.rect.intersects(last.rect)){
						throw new LimitCelluleException("limit cel");
					}
					
				}
			}
				//RD type Cellule
			//si notre element est une cellule , le checklastIn sera gerer 
			//dans molecule
			
		}
		
	}
	
	public void move(int nbPixel){
		this.rect.setLocation(this.rect.x + nbPixel, this.rect.y);
		Atome prec=null;
		for(RectangleDrawable a : this.atomes_molecules){
			if(a instanceof Molecule){
				((Molecule)a).move(nbPixel);
			}else {
				((Atome)a).move(nbPixel);
				
			}
		}
		//checkConflit();
		
	}
	
	public String toString(){
		String s = "cellule : ";
		for( RectangleDrawable  c:  atomes_molecules){
			//selon les type
			s+=c.toString();
		}
		s+="\n and y position is"+this.getRectangle().getCenterY()+"\n";
		
		return s;
	}
	
	
	
	public Cellule getClickedCel(int xClick,int yClick){
		//boolean click = false;
		if(this.getRectangle().contains( yClick,xClick)){
			return this;
		}else return null;
	}
	
	
	public int GetEndMesure(){
		int res =rect.x + rect.width;
		//System.out.println("SAM: "+res);
		
		return res;
	}
	
	public void addBreaktoHMself(HashMap<Integer, Integer> maMap){
		if(maMap.containsKey(GetEndMesure())){
			Outils.additionne(maMap, GetEndMesure(),getPt());
		}else{
			maMap.put(GetEndMesure(), getPt());
		}
		
	}
	public void addBreaktoHM(HashMap<Integer, Integer> maMap){
		addBreaktoHMself(maMap);
		for(RectangleDrawable AM : this.getAtomes_molecules()){
			 if(AM instanceof Molecule){
					((Molecule)AM).addBreaktoHM(maMap);
				}else {
					((Atome)AM).addBreaktoHM(maMap);
				}
		 }
	}

	
	public void breaklineself(Graphics g){
		//ne montre que sont breakline
		Rectangle rect = new Rectangle(new Point(this.GetEndMesure(),this.rect.y),new Dimension(1,20));
		
		g.fillRect(rect.x,rect.y,Math.abs(rect.width),rect.height);
		g.setColor(Color.decode("#FF6600"));
		
		
	}
	
	
	
	public void breaklineGen(Graphics g){
		//montre son breakline et ceux de ses élémen
		 breaklineself(g);
		 for(RectangleDrawable AM : this.getAtomes_molecules()){
			 if(AM instanceof Molecule){
					((Molecule)AM).breaklineself(g);
				}else {
					((Atome)AM).breaklineself(g);
				}
		 }
		 }
		
	

	public int getPt(){
		return 3;
	}
	
	public int getEspacement(){
		int espacement = 0 ;
		for(RectangleDrawable AM : this.getAtomes_molecules()){
			 if(AM instanceof Molecule){
				 for(Atome at : ((Molecule) AM).getAtomes()){
					 espacement+=at.getSuiv().getRectangle().width;
				 }
			}else {
					espacement+=((Atome)AM).getSuiv().getRectangle().width;
				}
		 }
		return espacement;
	}
}
