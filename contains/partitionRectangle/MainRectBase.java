package partitionRectangle;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

public class MainRectBase {
	
	public static void main(String[] args) {
		JFrame f=new JFrame();
		
		JCanvas jc = new JCanvas();
		jc.setBackground(Color.WHITE);
		jc.setPreferredSize(new Dimension(400,200));
		Dimension dim  =new Dimension(20,20);
		IDrawable rect = new RectangleDrawable(Color.RED,new Point(10,10),dim);
		
		jc.addDrawable(rect);
		
		
		
		f.add(jc);
		f.setVisible(true);
		//GUIHelper.showOnFrame(jc,"test JCanvas with IDrawables");
	}
}
