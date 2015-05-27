package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;

import outils.Outils;

import exception.AtomeConflitException;
import exception.LimitMoleculeException;


public class Molecule extends RectangleDrawable {
//blue
	//ArrayList peopo;
	
	//je compte les nombr d'exception recu, si a une boucle elle est egale
	//alors je stoppe
	boolean limiteAtteinte=false;
	public static int recuException=0;
	private ArrayList<Atome> atomes;
	public ArrayList<Atome> getAtomes() {
		return atomes;
	}
	public void setAtomes(ArrayList<Atome> atomes) {
		this.atomes = atomes;
	}
	//ArrayList<? extends RectangleDrawable>
	public Molecule(Color color, Point pos, Dimension dim,ArrayList<Atome> atomes) {
		
		super(color, pos, dim);
		this.atomes =atomes;
		// TODO Auto-generated constructor stub
	}
	public Molecule( Point pos, Dimension dim,ArrayList<Atome> atomes) {
		 //Color.decode("1E5474");
		super( Color.decode("#1D458D"), pos, dim);
		this.atomes =atomes;
		// TODO Auto-generated constructor stub
	}

	public void drawOnly(Graphics g){
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		g.setColor(c);
	}
	public void draw(Graphics g) {
		//Iterator<Atome> oki = this.atomes.iterator();
		
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		g.setColor(c);
		
		Atome prec=null;
		
		for (Atome u: this.atomes)
		{
		   
			
		//	System.out.println("2");
			
			if(prec==null)u.draw(g);
			if(prec!=null){
				
				boolean exception = false;
					//if(u.getRectangle().intersects(prec.getRectangle()));
					try{
	        			//on regarde si l'atome n'est pas en conflit avec le dernier element de molecule
	        			
	        			checkConflit((Atome) u,(Atome)prec);
	        			//u.draw(g);
	        		}catch(AtomeConflitException e){
	        			if(recuException==this.atomes.size()-1){
	        			limiteAtteinte = true;
	        			}
	        			exception = true;
	        			System.out.println("les atomes sont en conflit1\n\n\n\n");
	        		}
					if(!exception){
					u.draw(g);
					}
			
			prec = u;
			//u.draw(g);		//System.out.println("conflit intersection entre deux atome");
			}
		}
			
		
	}
	
	
	public void checkConflit(Atome actu,Atome prec) throws AtomeConflitException{
		boolean intersects = actu.getRectangle().intersects(prec.getRectangle());
		System.out.println("mol conf : "+intersects);
		if(intersects){

		throw new AtomeConflitException("mol:Il y a conflit entre moi est mon atome pr�c�dent");
		}
	}
	public void move(int nbPixel){
	//	try{
		this.rect.setLocation(this.rect.x + nbPixel, this.rect.y);
		
	//	}catch(LimitMoleculeException  e){
			System.out.println("limitMolExc");
	//	}
		for(Atome a : this.getAtomes()){
			a.move(nbPixel);
		}
	}
	
	
	public int changeZoomInPixel(int nbPixel) throws AtomeConflitException{
		//A faire : gerer les collision
		//gerer la repartition des pixel nbPixel/atomes.size()
		int i=0 ;
		Atome prec=null;
		for(Atome a : atomes){
			int nbExcept=0;
			//les espaces suivant les atomes change
			if(i==0) a.moveFirst(nbPixel);
			if(i>0){
				//a part le premier tout les atomes vont bouger de nbPixel
				//if(a.getRectangle().intersects(prec.getRectangle()));
				boolean ismoveException = false;
				try{
        			//on regarde si l'atome n'est pas en conflit avec le dernier element de molecule
					
        			checkConflit((Atome) a,(Atome)prec);
        		
        		}catch(AtomeConflitException e){
        			nbExcept++;
        			ismoveException = true;
        			System.out.println("les atomes sont en conflitchangezoom1\n\n\n\n");
        			//interessant mais...
        			a.move(-nbPixel+nbExcept);
        			
        			
        			//�throw e;
        		}
				if(!ismoveException)
				a.move(nbPixel);
				
			}
			prec=a;
			i++;
		
		}
		try{
		checkLastIn(this,this.getAtomes().get(this.getAtomes().size()-1));
		this.rect.setSize(this.getRectangle().width+nbPixel,this.getRectangle().height);
		}catch(LimitMoleculeException e){
			System.out.println("coucoucoucou");
			this.rect.setSize(this.getRectangle().width-(nbPixel*2),this.getRectangle().height);
		}
		return nbPixel;
	}
	
	private void checkLastIn(Molecule molecule, Atome atome) throws LimitMoleculeException {
		if(!molecule.rect.contains(atome.rect))
			if(molecule.getAtomes().get(molecule.getAtomes().size()-1).getRectangle().contains(atome.rect) ){
				throw new LimitMoleculeException("limit molaire");
			}
		
	}
	public String toString(){
		String s = "molecule < ";
		for( Atome c:  atomes){
			//selon les type
			s+=c.toString();
		}
		s+="> ";
		return s;
	}
	
	public int changeZoomInPixelProp(int nbPixel){ //quand on appelle que pour une molecule
		//A faire : gerer les collision
		//gerer la repartition des pixel nbPixel/atomes.size()
		int i=0 ;
		for(Atome a : atomes){
			//les espaces suivant les atomes change
			if(i==0) a.moveFirst(nbPixel/atomes.size()); //on fait bouger l'espace du premier dans cette fonction
			if(i>0)
				//a part le premier tout les atomes vont bouger de nbPixel
				a.move(nbPixel/atomes.size());
			i++;
			
		}
		this.rect.setSize(this.getRectangle().width,this.getRectangle().height+nbPixel);
		return nbPixel;
	}
	
	public int GetBeginGrp(){
		return rect.x;
	}
	
	public int getPt(){
		return 2;
	}
	
	public void addBreaktoHM(HashMap<Integer, Integer> maMap){
		if(maMap.containsKey(GetBeginGrp())){
			Outils.additionne(maMap, GetBeginGrp(),getPt());
		}else{
			maMap.put(GetBeginGrp(), getPt());
		}
		
		for(Atome a : atomes){
			
			a.addBreaktoHM(maMap);
			
		}
		
	}
	
	public void breaklineself(Graphics g){
		//ne montre que sont breakline
		Rectangle rect = new Rectangle(new Point(this.GetBeginGrp(),this.rect.y),new Dimension(1,20));
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		g.setColor(Color.decode("#FF00000"));
		
		
		for(Atome a : atomes){
			
			a.breaklineself(g);
			
		}
	}
	


}
	

   
