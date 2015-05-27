package parser;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import partitionRectangle.IDrawable;
import partitionRectangle.JCanvas;
import partitionRectangle.RectangleDrawable;
import partitionRectangle.SuperOrganisme;

import com.google.gson.Gson;

public class mainParser {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson() ;
		
		 
		try {
	 
		//	BufferedReader br = new BufferedReader(
			//	new FileReader("/Users/samuelbell-bell/Desktop/PSTL/essai.json"));
			//br.
			//convert the json string back to object
			Generer obj = gson.fromJson(new FileReader("/Users/samuelbell-bell/Desktop/PSTL/essai.json"),Generer.class);
			 System.out.println(gson.toJson(obj));
			//System.out.println(obj);
			SuperOrganisme su = obj.genererSuperOrg();
			System.out.println(su.getOrganismes().get(0).getCellules().get(0).getAtomes_molecules().get(0).getRectangle().getWidth());
			
			JFrame f=new JFrame();
			
			JCanvas jc = new JCanvas();
			jc.setBackground(Color.WHITE);
			jc.setPreferredSize(new Dimension(1000,1000));
			//Dimension dim  =new Dimension(20,20);
			
			
			jc.addDrawable(su);
			
			
			
			f.add(jc);
			f.setVisible(true);
		//	System.out.println(obj.toString());
	 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
