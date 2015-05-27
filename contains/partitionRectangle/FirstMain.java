package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class FirstMain {
	
	public static void main(String[] args) {
		JFrame f=new JFrame();
		
		JCanvas jc = new JCanvas();
		jc.setBackground(Color.WHITE);
		jc.setPreferredSize(new Dimension(400,300));
		Dimension dim  =new Dimension(10,10);
		Dimension dim2  =new Dimension(20,150); //hauteur , largeur
		Dimension dim3  =new Dimension(40,300);
		Dimension dim4  =new Dimension(80,1000);
		Dimension dim5  =new Dimension(800,1200);
		//IDrawable rect = new RectangleDrawable(Color.BLUE,new Point(10,20),dim);
		Atome atome = new Atome(new Point(60,100),dim);
		Atome atome1 = new Atome(new Point(85,100),dim);
		Atome atome2= new Atome(new Point(120,100),dim);
		Atome atome3 = new Atome(new Point(300,100),dim);
		Atome atome4 = new Atome(new Point(400,100),dim);
		Atome atome5 = new Atome(new Point(430,100),dim);
		Atome atome6 = new Atome(new Point(450,100),dim);
		ArrayList<Atome> atomes= new ArrayList<Atome>();
		atomes.add(atome);
		atomes.add(atome1);
		atomes.add(atome2);
		
		
		ArrayList<Atome> atomes2= new ArrayList<Atome>();
		atomes2.add(atome4);
		atomes2.add(atome5);
		atomes2.add(atome6);
		
		
		
		ArrayList<RectangleDrawable> molAtomes1= new ArrayList<RectangleDrawable>();
		ArrayList<RectangleDrawable> molAtomes2= new ArrayList<RectangleDrawable>();
		Molecule mol =new Molecule( new Point(40,90), dim2, atomes);
		Molecule mol2 =new Molecule( new Point(360,90), dim2, atomes2);
		
		 molAtomes1.add(atome3);
		 molAtomes1.add(mol);
		
		 molAtomes2.add(mol2);
		 
		
		Cellule cel = new Cellule(new Point(40,70), dim3,molAtomes1 );
		//300 différence entre chaque cellule ici pour l'instant...2px pour separation des mesure...
		Cellule cel2 = new Cellule(new Point(342,70), dim3,molAtomes2 );
		//jc.addDrawable(rect);
		
		
		ArrayList<Cellule> cellules= new ArrayList<Cellule>();
		cellules.add(cel);
		cellules.add(cel2);
		
		Organisme org = new Organisme(new Point(30,60),dim4,cellules );
		Organisme org2 = new Organisme(new Point(30,200),dim4,null);
		
		ArrayList<Organisme> organismes= new ArrayList<Organisme>();
		organismes.add(org);
		organismes.add(org2);
		
		
		SuperOrganisme sorg = new SuperOrganisme(new Point(20,30),dim5,organismes);
		
		jc.addDrawable(sorg);
		
		
		f.setBounds(0,0,1000, 1000);
		f.add(jc);
		f.setVisible(true);
		//GUIHelper.showOnFrame(jc,"test JCanvas with IDrawables");
	}

	
}
