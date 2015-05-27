package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JFrame;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//a partir des position relatif des elements parent.
		
		JFrame f=new JFrame();
		
		JCanvas jc = new JCanvas();
		jc.setBackground(Color.WHITE);
		jc.setPreferredSize(new Dimension(400,300));
		
		Dimension dim  =new Dimension(10,10);
		Dimension dim2  =new Dimension(20,150); //hauteur , largeur
		Dimension dim3  =new Dimension(40,300);
		Dimension dim4  =new Dimension(80,1000);
		Dimension dim5  =new Dimension(800,1200);
		
		ArrayList<Organisme> organismes= new ArrayList<Organisme>();
		//getter setter des arrayList => ok. and needed
		SuperOrganisme sorg = new SuperOrganisme(new Point(20,30),dim5,organismes);
		
		ArrayList<Cellule> cellules= new ArrayList<Cellule>();
		
		
		Organisme org = new Organisme(new Point(30,60),dim4,cellules );
		Organisme org2 = new Organisme(new Point(30,200),dim4,null);
		
		ArrayList<RectangleDrawable> molAtomes1= new ArrayList<RectangleDrawable>();
		ArrayList<RectangleDrawable> molAtomes2= new ArrayList<RectangleDrawable>();
		 
		Cellule cel = new Cellule(new Point(org.getRectangle().x + 10,org.getRectangle().y + 10), dim3,molAtomes1 );
		//300 différence entre chaque cellule ici pour l'instant...2px pour separation des mesure...
		Cellule cel2 = new Cellule(new Point(org.getRectangle().x +380,org.getRectangle().y +10), dim3,molAtomes2 );
		//jc.addDrawable(rect);
		
		
		ArrayList<Atome> atomes= new ArrayList<Atome>();
	
		
		
		ArrayList<Atome> atomes2= new ArrayList<Atome>();
		

		Molecule mol =new Molecule( new Point(cel.getRectangle().x + 10,cel.getRectangle().y + 10), dim2, atomes);
		Molecule mol2 =new Molecule( new Point(cel2.getRectangle().x + 10,cel2.getRectangle().y + 10), dim2, atomes2);
		
		Atome atome = new Atome(new Point(mol.getRectangle().x + 10,mol.getRectangle().y + 10),dim);
		Atome atome1 = new Atome(new Point(mol.getRectangle().x + 40,mol.getRectangle().y + 10),dim);
		Atome atome2= new Atome(new Point(mol.getRectangle().x + 100,mol.getRectangle().y + 10),dim);
		Atome atome3 = new Atome(new Point(cel.getRectangle().x + 180,cel.getRectangle().y + 20),dim);
		Atome atome4 = new Atome(new Point(mol2.getRectangle().x + 10,mol2.getRectangle().y + 10),dim);
		Atome atome5 = new Atome(new Point(mol2.getRectangle().x + 50,mol2.getRectangle().y + 10),dim);
		Atome atome6 = new Atome(new Point(mol2.getRectangle().x + 100,mol2.getRectangle().y + 10),dim);
		
		
		Espace esp0 = new Espace(cel , atome);
		System.out.println("esp0  "+esp0.rect.width);
		Espace esp1 = new Espace(atome ,atome1);
		Espace esp2 = new Espace(atome1 ,atome2);
		Espace esp3 = new Espace(atome2 ,atome3);
		Espace esp4 = new Espace(atome3 ,atome4);
		Espace esp5 = new Espace(atome4 ,atome5);
		Espace esp6 = new Espace(atome5 ,atome6);
		Espace esp7 = new Espace(atome6 ,cel2);
		System.out.println("esp7  "+esp7.rect.width + "and" +cel2.rect.width);
		
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
		
		cel.changeZoomInPixel(-30);
jc.addDrawable(sorg);
	jc.addDrawable(esp1);
		jc.addDrawable(esp3);
		jc.addDrawable(esp4);
		jc.addDrawable(esp0);
		jc.addDrawable(esp7);
		jc.addDrawable(esp1);
		jc.addDrawable(esp2);
		jc.addDrawable(esp5);
		jc.addDrawable(esp6);
		

		f.setBounds(0,0,1000, 1000);
		f.add(jc);
		f.setVisible(true);
	}

}