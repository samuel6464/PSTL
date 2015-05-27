package algorithm;

public class BreakLine {
	private int position;
	private int weight;
	private float zoom;
	private int number;
	
	public BreakLine(int position, int weight, float zoom,int number) {
		super();
		this.position = position;
		this.weight = weight;
		this.zoom = zoom;
		this.number = number;
		
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public float getZoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}
	
	//on mets en argument le breakSuivant
	public int SizeEspace(BreakLine B){
		if(B == null)return 0;
		return this.getPosition()-  B.getPosition() ;
	}
	
	public float PixelParPourcentage(int SizeEspace){
		return (float) (SizeEspace/100.0);
		
	}
	
	//B est le breakLine suivant
	public void PixelPourcentage(int pourcentage,BreakLine prec){
		float res=  (float) (PixelParPourcentage(this.SizeEspace(prec)))*pourcentage;
		this.setPosition( borneInf(res));
		//return res;
		
	}
	
	
	public int borneInf(float a){
		return (int) Math.floor(a);
	}
	

	public int positionAfterzoom(int pourcentage,BreakLine prec){
		//int Localposition =line.getPosition();
		this.PixelPourcentage(pourcentage, prec);
		return this.getPosition();
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	//Nous allons avoir besoin d'une fenetre...
	
}
