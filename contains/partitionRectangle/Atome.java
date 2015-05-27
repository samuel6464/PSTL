package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;

import outils.Outils;

public class Atome extends RectangleDrawable{
//obviously red rouge
	//pos repr�sente le debut
	//private float zoom; //en pourcent
	//il est bas� sur la distance en pixel qui s�pare sont font et sa limite "physique"
	Espace suiv,prec;
	
	
	
	
	public Atome(Color color, Point pos, Dimension dim) {
		super(Color.decode("#1B2581"), pos, dim);
		// TODO Auto-generated constructor stub
	}
   
	public Atome(Point pos, Dimension dim) {
		super(Color.decode("#1B2581"), pos, dim);
		// TODO Auto-generated constructor stub
	}
	
	

	public Atome( Espace prec, Espace suiv) {
		super( Color.decode("#1B2581"),new Point(prec.getRectangle().x +prec.getRectangle().width,prec.getRectangle().y) , getDimOf(prec, suiv));
		//super(Color.RED,new Point(prec.getRectangle().x +prec.getRectangle().width,prec.getRectangle().y) , getDimOf(prec, suiv));
		this.suiv = suiv;
		this.prec = prec;
	}
	
	public void init(Espace e1,Espace e2){
		prec =e1;
		suiv=e2;
	}

	public static Dimension getDimOf(Espace at,Espace at2){
		//une taille de 10 (au pif)
		int coucou = at2.getRectangle().x - (at.getRectangle().x+at.getRectangle().width);
		//System.out.println(coucou);
		return new Dimension(10,coucou);
	
	}
	
	public Espace getSuiv() {
		return suiv;
	}

	public void setSuiv(Espace suiv) {
		this.suiv = suiv;
	}

	public Espace getPrec() {
		return prec;
	}

	public void setPrec(Espace prec) {
		this.prec = prec;
	}

	public void draw(Graphics g){
		super.draw(g);
	}
	
	public void moveFirst(int nbPixel){
		System.out.println("coucoucou");
		//if(nbPixel>0){
			if(getSuiv()!=null)
			this.getSuiv().changeSizeSuivant(nbPixel);
	//	}else{
		//	this.getPrec().changeSizePrec(nbPixel);
	//	}
	}
	
	public void move(int nbPixel){
		//if(nbPixel>0){
			if(getSuiv()!=null){
			this.getSuiv().moveSizeSuivant(nbPixel);
			this.rect.setLocation(this.rect.x + nbPixel, this.rect.y);
			}
		//}else{
			//this.getPrec().moveSizePrec(nbPixel);
			//this.rect.setLocation(this.rect.x + nbPixel, this.rect.y);
	//	}
	}
	
	public String toString(){
		String s = " "+this.positionInfo()+" ";
		return s;
	}
	
	
	public int GetEndNote(){
		return rect.x + rect.width;
	}
	public void addBreaktoHM(HashMap<Integer, Integer> maMap){
		if(maMap.containsKey(GetEndNote())){
			Outils.additionne(maMap, GetEndNote(),getPt());
		}else{
			maMap.put(GetEndNote(), getPt());
		}
		
	}
	public void breaklineself(Graphics g){
		//ne montre que sont breakline
		Rectangle rect = new Rectangle(new Point(this.GetEndNote(),this.rect.y),new Dimension(1,20));
		
		g.fillRect(rect.x,rect.y,rect.width,rect.height);
		g.setColor(Color.decode("#FF00000"));
		
		
	}
	
	
	
	public int getPt(){
		return 1;
	}

}
