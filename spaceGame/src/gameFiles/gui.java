package gameFiles;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class gui {
	
	public static void main(String[] args) {
	        new gui();
	    }
	 
	 public gui() {
	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {


	                JFrame frame = new JFrame("Space Game");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setLayout(new GridLayout());
	                frame.add(new Pane());
	                frame.pack();
	                frame.setLocationRelativeTo(null);
	                frame.setVisible(true);  

	            }
	        });
	    }
	 
	 public class Pane extends JPanel {

	        public Pane() {
	            setLayout(new GridBagLayout());

	            GridBagConstraints gbc = new GridBagConstraints();
	            for (int row = 0; row < 10; row++) {
	                for (int col = 0; col < 10; col++) {
	                    gbc.gridx = col;
	                    gbc.gridy = row;

	                    CellPane cellPane = new CellPane();
	                    Border border = null;
	                    
	                    if (row < 4) {
	                        if (col < 4) {
	                            border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
	                        } else {
	                            border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
	                        }
	                    } else {
	                        if (col < 4) {
	                            border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
	                        } else {
	                            border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
	                        }
	                    }
	                    cellPane.setBorder(border);
	                    add(cellPane, gbc);
	                }
	            }
	        }
	    }

	    public class CellPane extends JPanel {

	        private Color defaultBackground;
	        
	        public CellPane() {
	            addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseEntered(MouseEvent e) {
	                    defaultBackground = getBackground();
	                    setBackground(Color.BLUE);
	                }
	                
	                @Override
	                public void mouseClicked(MouseEvent e) { 
	                    //Put the code you want here
	                	
	                }

	                @Override
	                public void mouseExited(MouseEvent e) {
	                    setBackground(defaultBackground);
	                }
	            });
	        }

	        //change cell size
	        @Override
	        public Dimension getPreferredSize() {
	            return new Dimension(50, 50);
	        }
	    }
	
	
	
	
	
	/*
	//Press Spacebar to initiate an attack
		public void keyPressed(KeyEvent e) {
		
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_SPACE) {
				//initializing to NULL until further development
				ship attacker = null;
				ship target = null;
				
				//call the Shoot function
				shoot(attacker, target);
			}
		}
		
		//TODO: Select ship(s) (use Mouse event probably)
		MouseAdapter listener = new MouseAdapter() {
			
			//use mousePressed to select enemy or friendly ships in order to do an action
			@Override
			public void mousePressed(MouseEvent e) {
				handleMousePressed(e);
			}

			//use mouseDragged to select more than one ship (enemy or friendly)
			@Override
			public void mouseDragged(MouseEvent e) {
				handleMouseDragged(e);
			}
			
			//not sure if mouseReleased is needed (here for reference)
			@Override
			public void mouseReleased(MouseEvent e) {
				handleMouseReleased(e);
			}
		};
		//listeners are throwing ERRORS
		/*addMouseListener(listener);
		addMouseMotionListener(listener);*/
			
		/*protected void handleMousePressed(MouseEvent e) {
			
			//repaint();
		}
		
		// Event handler for mouse dragged events
		protected void handleMouseDragged(MouseEvent e) {
			
			//repaint();
		}

		// Event handler for mouse released events
		protected void handleMouseReleased(MouseEvent e) {
			
			//repaint();
		}*/

}
