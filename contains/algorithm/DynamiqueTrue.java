package algorithm;

import java.util.HashMap;

public class DynamiqueTrue {

	
	//permet de trouver le meilleur break dynamiquement où poids est la liste des poids  disponible
	//lineSize la taille de la ligne, factDzoom la facteur de dezoomMinimal pris en compte,
	//nbE le nombre de break total de la partition
	//p la position courante utilisé dans le tableau
	//s les poids optimal qui se mettent a jour automatiquement
	//position la hashmap qui associe la position en dans le tableau , a la position en pixel
	
	public static int findBreakOpt(int[] poids,int lineSize,float factDzoom,int nbE,int p,int[] s,HashMap<Integer,Integer> positions,int[] Q){
		System.out.println("BEGIN and p = "+p+" and size " +nbE);
		
		int w=0;
		if(p<nbE){
			 if(s[p]<=0){System.out.println("returned");return s[p];}
							
			
			int q = p+1;
			if(q==nbE)return s[p];
			System.out.println("fstprint: "+nbE+ " "+q+" "+p);
			System.out.println(factDzoom+ " "+positions.get(q)+" "+positions.get(p)+"\n");
		
			System.out.println("before while "+nbE +" coucou et q: "+ q);
			while(factDzoom*(positions.get(q)-positions.get(p))<=lineSize){
				if(positions.get(q)-positions.get(p)>= lineSize/2){
					w = poids[q];
				}else{
					w= -1;
				}
				System.out.println("\ncoucou"+w+" nbE "+nbE+"s[p]"+s[p]+" q "+q);
				System.out.println("dezoom:posiQ:posiP "+factDzoom+ " "+positions.get(q)+" "+positions.get(p)+"\n");
			
				int res = findBreakOpt(poids,lineSize,factDzoom,nbE,q,s, positions,Q)+w;
				if(res>s[p]){
					s[p] = res;
					Q[p] =positions.get(q) ;
					System.out.println("best for p="+ p +" res="+res+" is "+ positions.get(q)+" and "+ q);
				}
				if(q<nbE){	q++;} 
				if(q==nbE){ break;}
   			
   				
			}
			System.out.println("afterwhile : nbE"+nbE+" p "+p+" return "+s[p]);
		}
		if(p!=nbE){
		//System.out.println("nbE"+nbE+" p "+p+" return "+s[p]);
		return s[p];
		}else{
			
			return s[p-1];
		}
		
		
	}
	
	
	/*		try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
	
}
