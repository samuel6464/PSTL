package outils;

import java.util.HashMap;
import java.util.Map.Entry;

public class Outils {

	
	public static void additionne(HashMap<Integer, Integer> maMap,Integer cle, int val) {
		   Integer ancienneValeur = (Integer) maMap.get(cle);
		   Integer nouvelleValeur = new Integer(ancienneValeur.intValue() + val);
		  // System.out.println("XYZ: "+nouvelleValeur);
		   maMap.put(cle, nouvelleValeur);
		}
	
	//on prend le dnr max possible
	public static int  getKeyByValue(HashMap<Integer, Integer> map, int value) {
		int cpt=0;
		int max = 0;
	    for (Entry<Integer, Integer> entry : map.entrySet()) {
	        if (value == entry.getValue()) {
	        	cpt++;
	            max=entry.getKey();
	        }
	    }
	    return max;
	}
	
}
