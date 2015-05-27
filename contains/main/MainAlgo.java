package main;

import java.io.FileReader;
import java.io.IOException;

import parser.Generer;
import partitionRectangle.Cellule;
import partitionRectangle.SuperOrganisme;
import algorithm.Dynamique;
import algorithm.Ligne;

import com.google.gson.Gson;

public class MainAlgo {

	public static void main(String[] args){
		System.out.println("salut");
		Gson gson = new Gson() ;	
		
		try { 
		Generer obj = gson.fromJson(new FileReader("/Users/samuelbell-bell/Desktop/PSTL/exemplepart2.json"),Generer.class);
		 System.out.println(gson.toJson(obj));
		//System.out.println(obj);
		 SuperOrganisme su = obj.genererSuperOrg();
		 
		System.out.println("su0: "+su);
		Cellule test  = su.getOrganismes().get(0).getCellules().get(0);
		 System.out.println("su: "+su);
			Ligne l=Dynamique.fillDynamique(800 , su);
			System.out.println("ok et l:"+l.getlargeurContenue()+" "+l.getSize());
			 System.out.println(l.getlargeurContenue() + "et" + l.getSize());
			 try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 Ligne l2=Dynamique.Completion(l);
			 System.out.println(l2.getlargeurContenue() + "et" + l2.getSize());
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
}
