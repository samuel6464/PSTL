package parser;

import java.util.ArrayList;

public class Mesure {
   private int mesureId;
   private boolean locked;
   private boolean breakline ;
   private int zoom;
   private int tempo;
   private ArrayList<GrpNotes_Note> grpNote_Note ;//liste pouvant être de type atome ou grpNote; 
public int getMesureId() {
	return mesureId;
}
public void setMesureId(int mesureId) {
	this.mesureId = mesureId;
}
public boolean isLocked() {
	return locked;
}
public void setLocked(boolean locked) {
	this.locked = locked;
}
public boolean isBreakline() {
	return breakline;
}
public void setBreakline(boolean breakline) {
	this.breakline = breakline;
}
public int getZoom() {
	return zoom;
}
public void setZoom(int zoom) {
	this.zoom = zoom;
}
public int getTempo() {
	return tempo;
}
public void setTempo(int tempo) {
	this.tempo = tempo;
}
public ArrayList<GrpNotes_Note> getgrpNote_Note() {
	return grpNote_Note;
}
public void setgrpNote_Note(ArrayList<GrpNotes_Note> grpNote_Note) {
	this.grpNote_Note = grpNote_Note;
}
   
   
   
}
