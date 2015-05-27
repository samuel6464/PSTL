package fenetre;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	
	public Fenetre() 
	{
		setTitle("interface rectangularScoreMusic");
		 getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		 //getContentPane().setLayout(new BorderLayout());
		 //getContentPane().add(new PanelPartition(),BorderLayout.CENTER);
		 add(new PanelPartition());
			validate();
			repaint();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
