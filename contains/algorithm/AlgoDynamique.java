package algorithm;

import java.util.HashMap;

public class AlgoDynamique {
	
	
	public static int findBreakOpt(int[] poids,int lineSize,float factDzoom,int nbE,int p,int[] s,HashMap<Integer,Integer> positions,int[] Q){
		//System.out.println("BEGIN and p = "+p+" and size " +nbE);
		
		int w=0;
		if(p<nbE){
			 if(s[p]>0){
			//System.out.println("returned");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 return s[p];}
							
			
			int q = p+1;
			if(q==nbE)return s[p];
		//	System.out.println("fstprint: "+nbE+ " "+q+" "+p);
			//System.out.println(factDzoom+ " "+positions.get(q)+" "+positions.get(p)+"\n");
		
			
			//System.out.println("before while "+nbE +" coucou et q: "+ q);
			while(factDzoom*(positions.get(q)-positions.get(p))<=lineSize){
				if(positions.get(q)-positions.get(p)>= lineSize/2){
					w = poids[q];
				}else{
					w= -1;
				}
				//System.out.println("\ncoucou"+w+" nbE "+nbE+"s[p]"+s[p]+" q "+q);
				//System.out.println("dezoom:posiQ:posiP "+factDzoom+ " "+positions.get(q)+" "+positions.get(p)+"\n");
				//int res=-poids.length;
				 int res = findBreakOpt(poids,lineSize,factDzoom,nbE,q,s, positions,Q)+w;
				if(res>s[p]){
					s[p] = res;
					Q[p] =positions.get(q) ;
					System.out.println("best for p="+ p +" res="+res+" is "+ positions.get(q)+" and q"+ q);
				}
				if(q<nbE){	q++;} 
				if(q==nbE){ break;}
   			
   				
			}
			//System.out.println("afterwhile : nbE"+nbE+" p "+p+" return "+s[p]);
		}
		if(p!=nbE){
		System.out.println("nbE  "+nbE+" p "+p+" return "+s[p]);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s[p];
		}else{
			
			return s[p-1];
		}
		
		
	}
	
	
	//100  200 900 1700  1800 2600 2700 3400 3500
	//2     3   2    2    3     2    3    2   3
	
	
	/*
	 La réponse devrait être :
	 900,1800,2700,3500
	
	 */
	
	public static void main(String[]args){
		
		
		 int[] listeClef = {100,200,1200,1700,1800,2600,2700,3400,3600};
		//int[] listeClef = {100,200,300,430,450,500,1100,1200,1900};
		
			
		 
			 int poids[]= {3,2,3,3,5,4,3,2,4};
			 //int poids[]= {2,3,2,3,2,3,2,2,3};
			 
				//on ordonne le tableau obtenu
			 //Arrays.sort(listeClef);
			 //on créé une ArrayList de Breaks
			
			 HashMap<Integer, Integer> positionMap = new HashMap<Integer, Integer>();
			 
			 for(int i=0;i<listeClef.length ;i++){
					int position = listeClef[i];
					
					positionMap.put(i, position);
			}
			 
			
			int[] s=new int[poids.length]; //initialise tout les max compute a -2
			for(int i=0 ;i<poids.length ;i++){
				s[i]=0;
				if(i==poids.length-1)s[i]=0; //le dernier element est a 0
			}
			int[] Q=new int[poids.length]; //initialise tout la reponse décidé  a 0
			int sizeLine = 1000;
			System.out.println("before fonct");
			findBreakOpt(poids,sizeLine,(float)1.0,poids.length,0,s, positionMap,Q);
			System.out.println("after fonct");
			for(int i=0;i<s.length ;i++){
				
					System.out.println(s[i]);
				
				
			}
			 System.out.println("Q");
			for(int i=0;i<Q.length ;i++){
				//transformer en set.
				 if(Q[i]!=0){
					System.out.println(Q[i]);
				 }
				 //System.out.println("I PASS");
			}
			//afficher le dnr break qui est a zero
			System.out.println("last = 0");
		
		
	}
}
