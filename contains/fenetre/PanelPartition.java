package fenetre;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import algorithm.Dynamique;
import algorithm.Ligne;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import parser.Generer;
import partitionRectangle.Cellule;
import partitionRectangle.Organisme;
import partitionRectangle.SuperOrganisme;



import model.Model;

import controller.Controller;

import view.ObserveurSwing;
import view.SuperOrganismeObserveurSwing;
import view.View;


public class PanelPartition extends JPanel implements ActionListener,ItemListener,ChangeListener {

	
	/**
	 * 
	 */
	
	private boolean run;
	private static final long serialVersionUID = 1L;
	
	private JPanel controle;
	
	//private JScrollPane affichage ;
	
	private JSlider espace,espace2;
	private JFileChooser choix ;
	private JButton choixfic;
	private JButton breakline;
	private JButton computeButton, plusButton, minusButton, plusButton2, minusButton2;
	private File file;
	private JTextField champs,champs2;
	
	private JCheckBox checkbox;
	
	private JLabel label,label2,label3,label4,label5 ;
	private View ihm;
	private Controller controller;
	private Model model;
	private ArrayList<ObserveurSwing> listSwing ;
	private ArrayList<Organisme> organismes;
	private Thread t;
	private SuperOrganisme so;
	private SuperOrganismeObserveurSwing soswing;
	private JScrollPane s;
	
	private Cellule celClicked;
	
	public PanelPartition(){
		listSwing = new ArrayList<ObserveurSwing>();
		
		setLayout(new BorderLayout());
		//controle est un jpanel qui nous permettra de controle les
		//definirent slide
		
		
		
		Box Commande = Box.createVerticalBox();
		Box Commande2 = Box.createHorizontalBox();
		Box Commande3 = Box.createHorizontalBox();
		
		
		
		
		minusButton = new JButton("-");
		Commande2.add(minusButton);
		minusButton.addActionListener(this);
		
		champs=new JTextField("1");
		Commande2.add(champs);
		champs.addActionListener(this);
		
		plusButton = new JButton("+");
		Commande2.add(plusButton);
		plusButton.addActionListener(this);
		
		label4 = new JLabel("zoom global");
		Commande2.add(label4);
		
		
		computeButton = new JButton("paginer");
		Commande.add(computeButton);
		computeButton.addActionListener(this);
		
		breakline = new JButton("breaking");
		Commande.add(breakline);
		breakline.addActionListener(this);
		
		
		
		
		choixfic = new JButton("choisir fichier");
		Commande.add(choixfic);
		choixfic.addActionListener(this);
		
		Commande.add(Commande2);
		
		
		minusButton2 = new JButton("-");
		Commande3.add(minusButton2);
		minusButton2.addActionListener(this);
		
		champs2=new JTextField("1");
		Commande3.add(champs2);
		champs2.addActionListener(this);
		
		plusButton2 = new JButton("+");
		Commande3.add(plusButton2);
		plusButton2.addActionListener(this);

		label5 = new JLabel("zoomOnChoosenCel");
		Commande3.add(label5);
		
		Commande.add(Commande3);
		
		label = new JLabel("Afficher/Retirer les espaces");
		Commande.add(label);
		
		checkbox= new JCheckBox();
		Commande.add(checkbox);
		checkbox.addItemListener(this);
		//sliders
		label2 = new JLabel("espacement1");
		label3= new JLabel("espacement2");
		
		espace = new JSlider();
		espace2 = new JSlider();
		
		espace.setMaximum(100);
		espace.setMinimum(0);
		espace.setValue(30);
		espace.setPaintTicks(true);
		espace.setPaintLabels(true);
		espace.setMinorTickSpacing(10);
		espace.setMajorTickSpacing(20);
		
		espace2.setMaximum(100);
		espace2.setMinimum(0);
		espace2.setValue(30);
		espace2.setPaintTicks(true);
		espace2.setPaintLabels(true);
		espace2.setMinorTickSpacing(10);
		espace2.setMajorTickSpacing(20);
	    
		Commande.add(label2);
		Commande.add(espace);
		Commande.add(label3);
		Commande.add(espace2);
		
		espace.addChangeListener(this);
		espace2.addChangeListener(this);
		
		controle = new JPanel();
		
		controle.add(Commande);
		
		 add(controle, BorderLayout.EAST);
		 
		 
		 ///for IHM
	//	 Dimension dim5  =new Dimension(800,1200);
			
		//	organismes= new ArrayList<Organisme>();
			//getter setter des arrayList => ok. and needed
			
			 so = SuperOrganisme.exempleSuperOrganisme();
					//fonctionQuiGenereUnExemple de so;
			
			Model model = new Model(so);
			//view
			//View view = new View(model);
			ihm = new View(listSwing);
			s = new JScrollPane();
			
			
			Controller controller = new Controller(ihm,model);
			
			soswing =new SuperOrganismeObserveurSwing(so);
			ihm.add(soswing);
		 
			s.setViewportView(ihm);

			//s.addMouseListener(l)
			s.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					System.out.println("that's a click man!");
					//on fera que sur une ligne pour le moment...
			for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
			
			if(c.getClickedCel(e.getY(),e.getX())!=null){
				celClicked = c.getClickedCel(e.getY(),e.getX());
				System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
				break;
			}
			System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
			
			}
				}

				@Override
				public void mouseEntered(MouseEvent arg0){}
				@Override
				public void mouseExited(MouseEvent arg0){}
				@Override
				public void mousePressed(MouseEvent arg0){}
				@Override
				public void mouseReleased(MouseEvent arg0){}			

			});
			controller.add(ihm);
			
		ihm.setPreferredSize(new Dimension(1000,10000));
			//s.getHorizontalScrollBar().setPreferredSize(new Dimension(10000,Integer.MAX_VALUE));
		add(s , BorderLayout.CENTER);
		
		 
	}
	@Override
	public void stateChanged(ChangeEvent event) {
		// TODO Auto-generated method stub
		((JSlider)event.getSource()).getValue();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		 if(e.getStateChange() == ItemEvent.SELECTED) {
			 System.out.println("selected");
			 //on ajoute les espaces 
			 //redessin� avec espace
			 
			 //on mets a true dans la structure le fait d'avoir des espaces
			 getSo().setSpace(true);
			 
			 //on efface "ihm" et on le repaint
			 this.remove(s);
			 
			 ihm.add(soswing);
				
			 s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			 s.addMouseListener(new MouseListener(){
					
					public void mouseClicked(MouseEvent e){
						System.out.println("that's a click man!");
						//on fera que sur une ligne pour le moment...
				for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
				
				if(c.getClickedCel(e.getY(),e.getX())!=null){
					celClicked = c.getClickedCel(e.getY(),e.getX());
					System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
					break;
				}
				System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
				
				}
					}

					@Override
					public void mouseEntered(MouseEvent arg0){}
					@Override
					public void mouseExited(MouseEvent arg0){}
					@Override
					public void mousePressed(MouseEvent arg0){}
					@Override
					public void mouseReleased(MouseEvent arg0){}			

				});
			 s.setPreferredSize(new Dimension(900,10000));
			 this.add(s, BorderLayout.CENTER);
			 controle.revalidate();
			 this.revalidate();
			 this.repaint();
			// this.paintComponent(s);
			 
			 
             
         }
		 if(e.getStateChange() ==ItemEvent.DESELECTED){
			//on mets a true dans la structure le fait d'avoir des espaces
			 getSo().setSpace(false);
			 this.remove(getIhm());
			 this.remove(s);
			 
			 ihm.add(soswing);
				s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.addMouseListener(new MouseListener(){
					
					public void mouseClicked(MouseEvent e){
						System.out.println("that's a click man!");
						//on fera que sur une ligne pour le moment...
				for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
				
				if(c.getClickedCel(e.getY(),e.getX())!=null){
					celClicked = c.getClickedCel(e.getY(),e.getX());
					System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
					break;
				}
				System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
				
				}
					}

					@Override
					public void mouseEntered(MouseEvent arg0){}
					@Override
					public void mouseExited(MouseEvent arg0){}
					@Override
					public void mousePressed(MouseEvent arg0){}
					@Override
					public void mouseReleased(MouseEvent arg0){}			

				});
				s.setPreferredSize(new Dimension(900,10000));
			 this.add(s, BorderLayout.CENTER);
			 controle.revalidate();
			 this.revalidate();
			 this.repaint();
			 
		 }
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//si on appuie sur le bouton paginer alors
		if(e.getSource()==breakline){
			//on appelle sur chaque element : Les GetEND et (begin pour grpNote) via l'SUPERORGANISME
		//ICI1	//so.afficheEnd;
			//showPossibleBreakLine(Graphics g)
		 soswing.setAffBreak(true);
		 this.remove(s);
		 
		 ihm.add(soswing);
			
		 s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 s.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					System.out.println("that's a click man!");
					//on fera que sur une ligne pour le moment...
			for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
			
			if(c.getClickedCel(e.getY(),e.getX())!=null){
				celClicked = c.getClickedCel(e.getY(),e.getX());
				System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
				break;
			}
			System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
			
			}
				}

				@Override
				public void mouseEntered(MouseEvent arg0){}
				@Override
				public void mouseExited(MouseEvent arg0){}
				@Override
				public void mousePressed(MouseEvent arg0){}
				@Override
				public void mouseReleased(MouseEvent arg0){}			

			});
		 s.setPreferredSize(new Dimension(900,10000));
		 this.add(s, BorderLayout.CENTER);
		 controle.revalidate();
		 this.revalidate();
		 this.repaint();
		}
		
		
		//si on appuie sur le bouton compute alors:
		if(e.getSource()==computeButton){
		
			////afaire : affect� a superorganisme su et superorganisme observeur...
			//adapter a fenetrage le compute...
			//ici debut
			System.out.println("salut");
			Gson gson = new Gson() ;	
			
			//System.out.println("su0: "+su);
			Cellule test  = so.getOrganismes().get(0).getCellules().get(0);
			 System.out.println("su: "+so);
				Ligne l=Dynamique.fillDynamique(800 , so);
				System.out.println("ok et l:"+l.getlargeurContenue()+" "+l.getSize());
				 System.out.println(l.getlargeurContenue() + "et" + l.getSize());
				 try {
					Thread.sleep(500);
				} catch (InterruptedException exc) {
					// TODO Auto-generated catch block
					exc.printStackTrace();
				}
				 Ligne l2=Dynamique.Completion(l);
				 System.out.println(l2.getlargeurContenue() + "et" + l2.getSize());
			
				 this.remove(getIhm());	
				this.remove(s);
				 
				 this.add(s, BorderLayout.CENTER);
				 this.revalidate();
				 this.repaint();
		
		///////////////////////ICI AJOUT MAIN FIN////////////////////
			
			/*getSo().getOrganismes().get(0).getCellules().get(0).changeZoomInPixel(4);
        	this.remove(getIhm());
			 
			 //ihm = 
			 this.add(ihm, BorderLayout.CENTER);
			 this.repaint();*/
		}
		if(e.getSource()==minusButton){
			//getSo().getOrganismes().get(0).getCellules().get(0).changeZoomInPixel(-Integer.parseInt(this.champs.getText()));
			getSo().getOrganismes().get(0).zoomCellules(-Integer.parseInt(this.champs.getText()));
			this.remove(getIhm());	
			this.remove(s);
			 
			// ihm.add(soswing);
				s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.addMouseListener(new MouseListener(){
					
					public void mouseClicked(MouseEvent e){
						System.out.println("that's a click man!");
						//on fera que sur une ligne pour le moment...
				for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
				
				if(c.getClickedCel(e.getY(),e.getX())!=null){
					celClicked = c.getClickedCel(e.getY(),e.getX());
					System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
					break;
				}
				System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
				
				}
					}

					@Override
					public void mouseEntered(MouseEvent arg0){}
					@Override
					public void mouseExited(MouseEvent arg0){}
					@Override
					public void mousePressed(MouseEvent arg0){}
					@Override
					public void mouseReleased(MouseEvent arg0){}			

				});
				s.setPreferredSize(new Dimension(900,10000));
			 this.add(s, BorderLayout.CENTER);
			 this.revalidate();
			 this.repaint();
		}
		
		if(e.getSource()==plusButton){
			//getSo().getOrganismes().get(0).getCellules().get(0).changeZoomInPixel(Integer.parseInt(this.champs.getText()));
			getSo().getOrganismes().get(0).zoomCellules(Integer.parseInt(this.champs.getText()));
			this.remove(getIhm());
			this.remove(s);
			 
			// ihm.add(soswing);
			
			s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
s.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					System.out.println("that's a click man!");
					//on fera que sur une ligne pour le moment...
			for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
			
			if(c.getClickedCel(e.getY(),e.getX())!=null){
				celClicked = c.getClickedCel(e.getY(),e.getX());
				System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
				break;
			}
			System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
			
			}
				}

				@Override
				public void mouseEntered(MouseEvent arg0){}
				@Override
				public void mouseExited(MouseEvent arg0){}
				@Override
				public void mousePressed(MouseEvent arg0){}
				@Override
				public void mouseReleased(MouseEvent arg0){}			

			});
			s.setPreferredSize(new Dimension(900,10000));
			 this.add(s, BorderLayout.CENTER);
			 this.revalidate();
			 this.repaint();
		}
		
		if(e.getSource()==minusButton2){
			getSo().getOrganismes().get(0).zoomOnlyOneCellules(-Integer.parseInt(this.champs.getText()),celClicked);
			//celClicked.changeZoomInPixel(-Integer.parseInt(this.champs.getText()));
			
			this.remove(getIhm());	
			this.remove(s);
			 
			// ihm.add(soswing);
				s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				s.addMouseListener(new MouseListener(){
					
					public void mouseClicked(MouseEvent e){
						System.out.println("that's a click man!");
						//on fera que sur une ligne pour le moment...
				for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
				
				if(c.getClickedCel(e.getY(),e.getX())!=null){
					celClicked = c.getClickedCel(e.getY(),e.getX());
					System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
					break;
				}
				System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
				
				}
					}

					@Override
					public void mouseEntered(MouseEvent arg0){}
					@Override
					public void mouseExited(MouseEvent arg0){}
					@Override
					public void mousePressed(MouseEvent arg0){}
					@Override
					public void mouseReleased(MouseEvent arg0){}			

				});
				s.setPreferredSize(new Dimension(900,10000));
			 this.add(s, BorderLayout.CENTER);
			 this.revalidate();
			 this.repaint();
		}
		
		if(e.getSource()==plusButton2){
			getSo().getOrganismes().get(0).zoomOnlyOneCellules(Integer.parseInt(this.champs.getText()),celClicked);
			//celClicked.changeZoomInPixel(Integer.parseInt(this.champs.getText()));
			
			this.remove(getIhm());
			this.remove(s);
			 
			// ihm.add(soswing);
			s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);	
s.addMouseListener(new MouseListener(){
				
				public void mouseClicked(MouseEvent e){
					System.out.println("that's a click man!");
					//on fera que sur une ligne pour le moment...
			for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
			
			if(c.getClickedCel(e.getY(),e.getX())!=null){
				celClicked = c.getClickedCel(e.getY(),e.getX());
				System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
				break;
			}
			System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
			
			}
				}

				@Override
				public void mouseEntered(MouseEvent arg0){}
				@Override
				public void mouseExited(MouseEvent arg0){}
				@Override
				public void mousePressed(MouseEvent arg0){}
				@Override
				public void mouseReleased(MouseEvent arg0){}			

			});
			s.setPreferredSize(new Dimension(900,10000));
			 this.add(s, BorderLayout.CENTER);
			 this.revalidate();
			 this.repaint();
		}
		
		if(e.getSource()==choixfic){
			
			//File file;
			choix = new JFileChooser();
			   FileNameExtensionFilter filter = new FileNameExtensionFilter(
				        "JSON", "json");
				    choix.setFileFilter(filter);
				    choix.setPreferredSize( new Dimension(400, 400) );
				    //int returnVal = choix.showOpenDialog("parent");
				    if (choix.showOpenDialog(null)== 
				    	    JFileChooser.APPROVE_OPTION) {
				    	    file = choix.getSelectedFile();
				    }
				    ihm.remove(soswing);
				 //this.remove(getIhm());
					this.remove(s); 
					 //ihm = 
					 Gson gson = new Gson() ;
					 Generer obj = null;
					try {
						obj = gson.fromJson(new FileReader(file),Generer.class);
					} catch (JsonSyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JsonIOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					 
					//System.out.println(obj);
					
					//SuperOrganisme su = obj.genererSuperOrg();
					setSo(obj.genererSuperOrg());
					System.out.println("LA\n"+this.so.toString());
					soswing =new SuperOrganismeObserveurSwing(so);
					ihm.add(soswing);
					s = new JScrollPane(ihm,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
					s.setPreferredSize(new Dimension(900,900));
					
					//s.getViewport().add(panelImages); 
					s.getViewport().add(ihm);
					
					s.addMouseListener(new MouseListener(){
						
						public void mouseClicked(MouseEvent e){
							System.out.println("that's a click man!");
							//on fera que sur une ligne pour le moment...
					for(Cellule c: SuperOrganismeObserveurSwing.getSupOrg().getOrganismes().get(0).getCellules()){	
					
					if(c.getClickedCel(e.getY(),e.getX())!=null){
						celClicked = c.getClickedCel(e.getY(),e.getX());
						System.out.println("j'ai recupere : "+celClicked +" en x:"+e.getX()+"en y "+e.getY());
						break;
					}
					System.out.println("didn't find it man..."+" en x:"+e.getX()+"en y "+e.getY());
					
					}
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void mouseReleased(MouseEvent arg0) {
							// TODO Auto-generated method stub
							
						}
					}
					);
					
					s.revalidate();
					//s.paintComponents(ihm.getGraphics());
					//s.add(ihm);
					// this.add(s, BorderLayout.CENTER);
				//	 this.getJSP().repaint();
				//	this.revalidate();
					this.add(s, BorderLayout.CENTER);
					this.revalidate();
					 this.repaint();
					// this.paintComponent(s.getGraphics());
		}
		
		
	}
	
	public JScrollPane getJSP(){
		return s;
	}
	public View getIhm() {
		return ihm;
	}
	public void setIhm(View ihm) {
		this.ihm = ihm;
	} 
	
	  public JPanel findComponent(JComponent parent,String name){
		  
		  int i;
	        Component[] components = parent.getComponents();
	          for(i = 0; i < components.length; i++){
	              if(components[i] instanceof View){
	            	  System.out.println("findIt");
	                break;

	
	              }
	}
	          return (JPanel)components[i];
	  }
	  
	public SuperOrganisme getSo() {
		return so;
	}
	public void setSo(SuperOrganisme so) {
		this.so = so;
	}
	
	
	  
}



