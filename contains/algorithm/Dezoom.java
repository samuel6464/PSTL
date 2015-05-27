package algorithm;

public class Dezoom {
	
	public static BreakLine[] dezoomAll(BreakLine[] breaks,float zoom){
		
		BreakLine[] newBreaks = new BreakLine[breaks.length];
		int distanceRetrieve =0;
		
	//	i>1 (prerequis et dezoom a partir du précédent)
		newBreaks[0]=breaks[0];
		for(int i=1;i<newBreaks.length;i++){
			int z =(int) (zoom*100);
			newBreaks[i]= new BreakLine(((int) breaks[i].positionAfterzoom(z, breaks[i-1]))-distanceRetrieve, breaks[i].getWeight(), breaks[i].getZoom() - zoom, i);
			distanceRetrieve+= breaks[i].getPosition() -newBreaks[i].getPosition();
		}
		
		return newBreaks;
	}
	
	public static BreakLine[] dezoomItoJ(BreakLine[] breaks,float zoom,int i,int j){
		
		BreakLine[] newBreaks = new BreakLine[breaks.length];
		int distanceRetrieve =0;
		//int i=0;
		
		for(BreakLine b: breaks){
			//newBreaks[i]=
					
		//	i++;
		}
		
		return breaks;
	}
}
