package partitionRectangle;

public class CoupleEspace {

	private Espace prec,suiv;

	public CoupleEspace(Espace prec, Espace suiv) {
		super();
		this.prec = prec;
		this.suiv = suiv;
	}

	public Espace getPrec() {
		return prec;
	}

	public void setPrec(Espace prec) {
		this.prec = prec;
	}

	public Espace getSuiv() {
		return suiv;
	}

	public void setSuiv(Espace suiv) {
		this.suiv = suiv;
	}
	
	
}
