package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;

public class MainSecond {

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
		
		Cellule cel = new Cellule(new Point(40,70), dim3,molAtomes1 );
		//300 différence entre chaque cellule ici pour l'instant...2px pour separation des mesure...
		Cellule cel2 = new Cellule(new Point(342,70), dim3,molAtomes2 );
		//jc.addDrawable(rect);
		
		
		ArrayList<Atome> atomes= new ArrayList<Atome>();
	
		
		
		ArrayList<Atome> atomes2= new ArrayList<Atome>();
		

		Molecule mol =new Molecule( new Point(40,90), dim2, atomes);
		Molecule mol2 =new Molecule( new Point(360,90), dim2, atomes2);
		
		Atome atome = new Atome(new Point(60,100),dim);
		Atome atome1 = new Atome(new Point(85,100),dim);
		Atome atome2= new Atome(new Point(120,100),dim);
		Atome atome3 = new Atome(new Point(300,100),dim);
		Atome atome4 = new Atome(new Point(400,100),dim);
		Atome atome5 = new Atome(new Point(430,100),dim);
		Atome atome6 = new Atome(new Point(450,100),dim);
		
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
		
		
		jc.addDrawable(sorg);
		

		f.setBounds(0,0,1000, 1000);
		f.add(jc);
		f.setVisible(true);
	}

}
