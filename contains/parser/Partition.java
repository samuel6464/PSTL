package parser;

public class Partition {

	private String format;
	private Header header;
	private Bordure bordure;
	private Polyphonie polyphonie;
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Bordure getBordure() {
		return bordure;
	}
	public void setBordure(Bordure bordure) {
		this.bordure = bordure;
	}
	public Polyphonie getPolyphonie() {
		return polyphonie;
	}
	public void setPolyphonie(Polyphonie polyphonie) {
		this.polyphonie = polyphonie;
	}

	public String toString(){
		String s = "format:"+getFormat()+"\n";
		
		s+="headerNom:"+getHeader().getNom()+" headerAuteur:"+getHeader().getAuteur()+"\n";
		s+="bordureMH:"+this.getBordure().getMarge_hauteur() +
				" bordureML:"+this.getBordure().getMarge_largeur()+"\n";
		
		Polyphonie P = this.getPolyphonie();
		//pour chaque lignes;
	/*	for (System_Interligne sy:P.getSystemes() ){
		s+="systemNum:"+sy.getSystemeID()+"\n";
			for(Ligne li : sy.getLigne()){
				s="numeroLigne"+li.getLigne();
				for(Mesure me : li.getMesure()){
					s+="mesureId "+me.getMesureId() +" zoom " +me.getZoom() + " tempo : "+  me.getTempo();
					//for()
				}
				
			}
				
		}
	*/	
		
		return s;
		
	}
	
	
}
