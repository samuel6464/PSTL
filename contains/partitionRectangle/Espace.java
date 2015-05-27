package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Espace extends RectangleDrawable  {

	/*
	 * 
	 * un espace est un espace entre deux atome
	 * il est défini par sa position x et (y) et sa longueur et surtout sa largeur (->x2)
	 */
	private Atome atome1; 
	private Atome atome2;
	
	public Espace( Point pos, Dimension dim) {
		super(Color.WHITE, pos, dim);
		// TODO Auto-generated constructor stub
	}
	
	public Espace(Atome atome1,Atome atome2){
		super(Color.WHITE,new Point(atome1.getRectangle().x +atome1.getRectangle().width,atome1.getRectangle().y) , getDimOf(atome1, atome2));
		//this.setAtome1(atome1);
		//this.setAtome2(atome2);
		this.atome1 = atome1;
		this.atome2 = atome2;
	}
	
	public Espace(Cellule cel,Atome atome2){
		//pour le debut
		
		super(Color.WHITE,new Point(cel.getRectangle().x ,atome2.getRectangle().y) , getDimOfByCelluleBegin(cel, atome2));
		//System.out.println("mon espace cel"+cel.getRectangle().x);
	}
	
	public Espace(Atome atome1,Cellule cel){
		//pour le debut
		super(Color.WHITE,new Point(atome1.getRectangle().x +atome1.getRectangle().width,atome1.getRectangle().y) , getDimOfByCelluleEnd(atome1, cel));
		
	}
	
	public int largeur (){
		return this.rect.width;
		
	}

	public static Dimension getDimOf(Atome at,Atome at2){
		//une taille de 10 (au pif)
		int coucou = at2.getRectangle().x - (at.getRectangle().x+at.getRectangle().width);
		//System.out.println(coucou);
		return new Dimension(coucou,10);
	
	}

	public static Dimension getDimOfByCelluleBegin(Cellule cel,Atome at2){
		//une taille de 10 (au pif)
		int coucou = at2.getRectangle().x - cel.getRectangle().x;
		//System.out.println("coucou1"+coucou);
		//System.out.println(coucou);
		return new Dimension(coucou,10);
	
	}
	public static Dimension getDimOfByCelluleEnd(Atome at1,Cellule cel){
		//une taille de 10 (au pif)
		int coucou = (cel.getRectangle().x+cel.getRectangle().height) - (at1.getRectangle().x+at1.getRectangle().width);
		//System.out.println(coucou);
	//	System.out.println("coucou2"+coucou+"n "+ cel.getRectangle().x+cel.getRectangle().height+ " and cel , rec"+ (cel.getRectangle().x+cel.getRectangle().width) +" n "+ (at1.getRectangle().x+at1.getRectangle().width));
		return new Dimension(coucou,10);
	
	}
	public Atome getAtome1() {
		return atome1;
	}

	public void setAtome1(Atome atome1) {
		this.atome1 = atome1;
	}

	public Atome getAtome2() {
		return atome2;
	}

	public void setAtome2(Atome atome2) {
		this.atome2 = atome2;
	}
	
	//on change la taille est on recupere le changement de taille.
	
	//si c'est l'espace précédent d'un atome
	public int moveSizePrec(int nbPixel){
		this.rect.setBounds(this.rect.x +nbPixel, this.rect.y, this.rect.width, this.rect.height);
		System.out.println("j'ai bougé de px "+ nbPixel);
		return nbPixel;
	}
	
	public int changeSizePrec(int nbPixel) {
		this.rect.setBounds(this.rect.x , this.rect.y, this.rect.width, this.rect.height+nbPixel);
		System.out.println("j'ai bougé de px "+ nbPixel);
		return nbPixel;
	}

	
	public void draw(Graphics g){
		super.draw(g);
	}
	
	//si c'est l'espace suivant d'un atome
	public int moveSizeSuivant(int nbPixel) {
		this.rect.setBounds(this.rect.x +nbPixel, this.rect.y, this.rect.width, this.rect.height);
		System.out.println("j'ai bougé de px "+ nbPixel);
		return nbPixel;
	}
	
	public int changeSizeSuivant(int nbPixel) {
		this.rect.setBounds(this.rect.x, this.rect.y, this.rect.width+nbPixel, this.rect.height);
		System.out.println("j'ai bougé de px "+ nbPixel);
		return nbPixel;
	}


}
