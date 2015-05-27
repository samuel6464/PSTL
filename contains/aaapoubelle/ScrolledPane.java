package aaapoubelle;

import java.awt.*;
import javax.swing.*;

class ScrolledPane
		extends 	JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private		JScrollPane scrollPane;

	public ScrolledPane()
	{
		setTitle( "Scrolling Pane Application" );
		setSize( 300, 200 );
		setBackground( Color.gray );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		Icon image = new ImageIcon( "main.gif" );
		JLabel label = new JLabel( image );

		// Create a tabbed pane
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add( label );
		topPanel.add( scrollPane, BorderLayout.CENTER );
	}


	public static void main( String args[] )
	{
		// Create an instance of the test application
		ScrolledPane mainFrame	= new ScrolledPane();
		mainFrame.setVisible( true );
	}
}