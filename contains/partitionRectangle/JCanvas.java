package partitionRectangle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import java.awt.Graphics;


public class JCanvas extends JPanel {

	private List drawables = new LinkedList();
	
	public void paint(Graphics g) {
		int i =0;
		for (Iterator iter = drawables.iterator(); iter.hasNext();) {
			System.out.println(i);
			IDrawable d = (IDrawable) iter.next();
			System.out.println("myd"+d);
			d.draw(g);	
			i++;
		}
	}

	public void addDrawable(IDrawable d) {
		System.out.println("ok");
		drawables.add(d);
		repaint();
	}

	public void removeDrawable(IDrawable d) {
		drawables.remove(d);
		repaint();
	}

	public void clear() {
		drawables.clear();
		repaint();
	}
	
}