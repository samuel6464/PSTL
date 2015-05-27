package exception;

public class AtomeConflitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AtomeConflitException(String s){
		super("Nous sommes en conflit , c'est interdit\n"+s);
	}
	
	

}
