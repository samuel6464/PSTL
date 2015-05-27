package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;


//conflit un organisme est elle la voix enti�re ou bien une ligne de la partition...?
//ligne de la partition pour le moment...
public class Organisme extends RectangleDrawable {

	
	//liste de cellule (mesure)
	
	private ArrayList<Cellule> cellules;
	
	public ArrayList<Cellule> getCellules() {
		return cellules;
	}
	public void setCellules(ArrayList<Cellule> cellules) {
		this.cellules = cellules;
	}
	/**
	 * @param args
	 */
public Organisme(Color color, Point pos, Dimension dim,ArrayList<Cellule> cellules) {
		
		super(color, pos, dim);
		this.cellules =cellules;
		// TODO Auto-generated constructor stub
	}
	public Organisme( Point pos, Dimension dim,ArrayList<Cellule> cellules) {
		super( Color.decode("#1D7F8B"), pos, dim);
		//super(Color.orange, pos, dim);
		this.cellules =cellules;
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
		Cellule prec=null;
		int cpt =1;
		if(this.cellules != null)
		for (Cellule u: this.cellules)
		{
		    //check is conflit???----->iterator a que next
			//cr�ation de notre propre it�rator qui ce souvient du dernier?
			//variable atome qui check ! (moins de place ou egal...)
			//sauf pratique notre own iterator si besoin de checker le
			//pr�c�dent du pr�c�dent...besoin?
			
			
			if(prec!=null)
				
				//intersection minimal entre les mesure (coller...?)
				if(u.getRectangle().intersects(prec.getRectangle())){
					//Exception exception;
					//throw exception;
					System.out.println("conflit intersection entre deux cellule");
				}
			prec = u;
			System.out.println("I DRAW");
			u.draw(g);
			
			System.out.println("IR il y a "+cpt);
			cpt++;
			
		}
	}
	
	public String toString(){
		String s = "organisme";
		for( Cellule c:  cellules){
			s+=c.toString();
		}
		s+="\n";
		return s;
	}
	//pas utile
	public int changeSize(int nbPixel){
		this.rect.setSize(this.getRectangle().width+nbPixel,this.getRectangle().height);
		return nbPixel;
	}
	public int ajour(int px){
		
		
		return px;
		
	}
	public int zoomCellules(int nbPixel){
		int nb=0;
		Cellule prec = null;
		for(Cellule a : this.getCellules()){
			if(nb==0){
			a.changeZoomInPixel(nbPixel);
			}else{
				//il faut bouger tout les cellules et ce qu'elle contient de nbPixel...+sizeadd
				//int newXval = a.rect.x+nbPixel;
			//	a.getRectangle().setLocation(newXval,a.rect.y);
				//this.getCellules().get(nb).getRectangle().setLocation(newXval,a.rect.y);
				//a.rect.setSize(a.getRectangle().width,a.getRectangle().height+nbPixel);
				//System.out.println("old x:"+a.rect.x+" newX: "+newXval);
				if(a.rect.intersects(prec.rect)){
				a.move(-nbPixel);
				}
				a.move(nbPixel);
				a.changeZoomInPixel(nbPixel);
				System.out.println("je suis dans et nb:"+nb);
			}
			prec =a;
			nb++;
			
		}
		return nbPixel;
	}
	
	public int zoomOnlyOneCellules(int pixel,Cellule c){
		int index = this.getCellules().indexOf(c);
		int i=0;
		int size = this.getCellules().size();
		if(pixel<0){
			this.getCellules().get(index).changeZoomInPixel(pixel);
			for(i=index+1;i<size;++i){
				if(this.getCellules().get(index).rect.intersects(this.getCellules().get(i-1).getRectangle()))
					this.getCellules().get(i).move(-pixel);
				this.getCellules().get(i).move(pixel);
			}
		}else{
			//cas positif
			
			this.getCellules().get(index).changeZoomInPixel(pixel);
			for(i=index+1;i<size;++i){
				this.getCellules().get(i).move(pixel);
			}
		}
		return 0;
	}
	
	
	public void addBreaktoHMCel(HashMap<Integer, Integer> maMap){
		for(Cellule c : this.cellules){
			c.addBreaktoHMself(maMap);
		}	
	}
	
	public void addBreaktoHMG(HashMap<Integer, Integer> maMap){
		for(Cellule c : this.cellules){
			c.addBreaktoHM(maMap);
		}	
	}
	
	public void BreakLineMesure(Graphics g) {
	//montre uniquement les fin de mesure
		// TODO Auto-generated method stub
		
		//position =end,hauteur
		//dim (1px, 20px)...
		for(Cellule c : this.cellules){
			c.breaklineself(g);
		}
		
		
	}
	public void BreakLine(Graphics g) {
		//montre les fins de mesures , les debut de groupe et les fins de molecule
		// TODO Auto-generated method stub
		for(Cellule c : this.cellules){
			c.breaklineGen(g);
		}
	}

}
