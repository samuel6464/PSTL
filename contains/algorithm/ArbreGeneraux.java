package algorithm;

import java.util.ArrayList;
import java.util.Iterator;

public class ArbreGeneraux {

	//un noeud avec un poids et un  et une liste de sous arbres généraux.
	//le edge aura un poids aussi.
	
	private int noeud; //poids
	private ArbreGeneraux parent;
	private ArrayList<ArbreGeneraux> fils;
	private int myIndex;
	
	public ArbreGeneraux(int weight){
		this.noeud = weight;
		parent = null;
		fils = new ArrayList<ArbreGeneraux>();
		myIndex = -1;
	}
	
	public int getNoeud() {
		return noeud;
	}
	
	public void setNoeud(int noeud) {
		this.noeud = noeud;
	}

	 public boolean isRoot() {
	        return parent == null;
	  }
	 
	 public boolean isFeuille(){
		 return fils.isEmpty();
	 }
	 
	/* **
	     * Tests whether there are nodes "to the right" of this node.
	     * @return <code>true</code> if this node is not the last child.
	     *
	    public boolean hasNextSibling() {
	        return myIndex >= 0 &&
	               myIndex < parent.fils.size() - 1;
	    }
	    
	    public boolean hasPreviousSibling() {
	        return myIndex > 0;
	    }
	*/
	 public boolean hasChildren() {
	        return !fils.isEmpty();
	  }
	 
	 public ArbreGeneraux firstChild(){
		 if (fils.isEmpty()) return null;
	        else return fils.get(0);
	 }
	 
	 public ArbreGeneraux lastChild(){
		 if (fils.isEmpty()) return null;
	        else return fils.get(fils.size()-1);
	 }
	 
	 public ArbreGeneraux child(int index){
		 if (index < 0 || index >= fils.size()) return null;
	        else return fils.get(index);
	 }
	 
	 public ArrayList<ArbreGeneraux> children() {
	        return fils;
	    }
	 
	/* public ArbreGeneraux nextSibling() {
	        if (hasNextSibling()) {
	            return parent.fils.get(myIndex + 1);
	        }
	        else return null;
	    }
	 
	 public ArbreGeneraux previousSibling() {
	        if (hasPreviousSibling()) {
	            return parent.fils.get(myIndex - 1);
	        }
	        else return null;
	    }
	    */
	 
	  public int depth() {
	        if (parent == null) return 0;
	        else return 1 + parent.depth();
	    }
	   
	  
	 public boolean hasAncestor(ArbreGeneraux ancestor) { // including itself
		 ArbreGeneraux temp = this;
	        while (temp != ancestor) {
	            if (temp == null) return false;
	            temp = temp.parent;
	        }
	        return true;
	   }

	 //SETTER
	  public void addChild(ArbreGeneraux newChild) {
	        if (this.hasAncestor(newChild)) {
	            String message = this + " is already in " + newChild;
	            throw new IllegalArgumentException(message);
	        }
	        int count = fils.size();
	        fils.add(newChild);
	        newChild.parent = this;
	        newChild.myIndex = count;
	    }
	  
	  public void addChildren(ArrayList<ArbreGeneraux> newChildren) {
	        for (Iterator<ArbreGeneraux> iter = newChildren.iterator(); iter.hasNext();) {
	            addChild(iter.next());            
	        }        
	    }
	  
	  public void remove() {
	        if (parent == null) return;
	        int decrement = 0;
	        for (Iterator<ArbreGeneraux> iter = parent.fils.iterator(); iter.hasNext();) {
	        	ArbreGeneraux element = iter.next();
	            element.myIndex -= decrement;
	            if (element == this) {
	                iter.remove();
	                decrement = 1;
	            }
	        }
	    }
	
}
