package gameFiles;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class gui extends JPanel{

	// Number of milliseconds between animation timer events
	private static final int TIMER_INTERVAL = 1000/30;

	boolean p1Targeted = false;
	boolean p2Targeted = false;
	boolean p1Destroyed = false;
	boolean p2Destroyed = false;
	boolean player1 = true;
	int speed = 200;
	int x = 600;
	int y = 600;
	int numPlayers = 2;
	String name = null;
	ArrayList<ship> player1Ships = new ArrayList<>();
	ArrayList<ship> player2Ships = new ArrayList<>();
	
	private ship Ship = new ship(name);
	private board Board = new board(x, y);
	private combatController combat = new combatController(numPlayers, player1Ships, player2Ships);
	private Timer timer;
	
	//test
	boolean startGame = false;
	boolean customizeScreenP1 = false;
	boolean customizeScreenP2 = false;
	int greenShipX = 325;
	int greenShipY = 525;
	int redShipX = 325;
	int redShipY = 25;
	
	private BufferedImage shipSprite;
	private BufferedImage shipSprite2;
	private BufferedImage space;

	
	public gui() {
		//setBackground(Color.BLACK);
		shipSprite = loadImage("gameFiles/Images/ship.png");
		shipSprite2 = loadImage("gameFiles/Images/ship2.png");
		space = loadImage("gameFiles/Images/spaceDrop.png");
		
		setPreferredSize(new Dimension((int) Board.getXSize(), (int) Board.getYSize()));
		
		MouseAdapter listener = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				handleMousePressed(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				handleMouseDragged(e);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				handleMouseReleased(e);
			}
		};
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		KeyAdapter listen = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				handleKeyPressed(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				handleKeyReleased(e);
			}
			
		};
		addKeyListener(listen);
		
		InputMap im = getInputMap(WHEN_FOCUSED);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "onSpace");

        am.put("onSpace", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Enter pressed
            	repaint();
            }
        });
		
		this.timer = new Timer(TIMER_INTERVAL, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleTimerTick();
			}
		});
	}
	
	// Start the animation timer.
	public void startTimer() {
		timer.start();
	}
	
	// Event handler for mouse pressed events
	protected void handleMousePressed(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model
		if(p1Destroyed == false && p2Destroyed == false) {
			if(player1 == true) {
				if(speed > 0) {
					if((Math.abs(e.getX() - greenShipX) <= 200) && (Math.abs(e.getY() - greenShipY) <= 200)){
						if(((Math.abs(e.getX() - greenShipX)) + (Math.abs(e.getY() - greenShipY))) <= 200) {
							//speed = speed-((Math.abs(e.getX() - greenShipX)) + (Math.abs(e.getY() - greenShipY)));
							//greenShipX = e.getX();
							//greenShipY = e.getY();
							repaint();
						}
					}
				}
				if(Math.abs(redShipX - greenShipX) == 100 && Math.abs(redShipY - greenShipY) == 100) {
					p2Targeted = true;
					repaint();
				}
				else {
					p1Targeted = false;
					p2Targeted = false;
					repaint();
				}
			}
			else if(player1 == false) {
				if(speed > 0) {
					if((Math.abs(e.getX() - redShipX) <= 200) && (Math.abs(e.getY() - redShipY) <= 200)){
						if(((Math.abs(e.getX() - redShipX)) + (Math.abs(e.getY() - redShipY))) <= 200) {
							//speed = speed-((Math.abs(e.getX() - redShipX)) + (Math.abs(e.getY() - redShipY)));
							//redShipX = e.getX();
							//redShipY = e.getY();
							//repaint();
						}	
					}
				}
				if(Math.abs(redShipX - greenShipX) == 100 && Math.abs(redShipY - greenShipY) == 100) {
					p1Targeted = true;
					repaint();
				}
				else {
					p1Targeted = false;
					p2Targeted = false;
					repaint();
				}
			}
		}
	}
		
	// Event handler for mouse dragged events
	protected void handleMouseDragged(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model
		
		repaint();
	}

	// Event handler for mouse released events
	protected void handleMouseReleased(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model

		//Move Red Ship
		if(((Math.abs(e.getX() - redShipX)) + (Math.abs(e.getY() - redShipY))) <= 200 && speed > 0 && player1 == false) {
			if(e.getX() < 100) {
				redShipX = 25;
				if(e.getY() < 100) {
					speed = speed - 100;
					redShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					redShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					redShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					redShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					redShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					redShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 100 && e.getX() < 200) {
				redShipX = 125;
				if(e.getY() < 100) {
					speed = speed - 100;
					redShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					redShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					redShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					redShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					redShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					redShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 200 && e.getX() < 300) {
				redShipX = 225;
				if(e.getY() < 100) {
					speed = speed - 100;
					redShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					redShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					redShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					redShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					redShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					redShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 300 && e.getX() < 400) {
				redShipX = 325;
				if(e.getY() < 100) {
					speed = speed - 100;
					redShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					redShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					redShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					redShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					redShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					redShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 400 && e.getX() < 500) {
				redShipX = 425;
				if(e.getY() < 100) {
					speed = speed - 100;
					redShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					redShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					redShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					redShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					redShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					redShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 500 && e.getX() < 600) {
				redShipX = 525;
				if(e.getY() < 100) {
					speed = speed - 100;
					redShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					redShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					redShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					redShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					redShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					redShipY = 525;
					repaint();
				}
			}
		}
		
		//Green Ship to move
		if(((Math.abs(e.getX() - greenShipX)) + (Math.abs(e.getY() - greenShipY))) <= 200 && speed > 0 && player1 == true) {
			if(e.getX() < 100) {
				greenShipX = 25;
				if(e.getY() < 100) {
					speed = speed - 100;
					greenShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					greenShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					greenShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					greenShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					greenShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					greenShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 100 && e.getX() < 200) {
				greenShipX = 125;
				if(e.getY() < 100) {
					speed = speed - 100;
					greenShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					greenShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					greenShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					greenShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					greenShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					greenShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 200 && e.getX() < 300) {
				greenShipX = 225;
				if(e.getY() < 100) {
					speed = speed - 100;
					greenShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					greenShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					greenShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					greenShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					greenShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					greenShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 300 && e.getX() < 400) {
				greenShipX = 325;
				if(e.getY() < 100) {
					speed = speed - 100;
					greenShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					greenShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					greenShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					greenShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					greenShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					greenShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 400 && e.getX() < 500) {
				greenShipX = 425;
				if(e.getY() < 100) {
					speed = speed - 100;
					greenShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					greenShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					greenShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					greenShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					greenShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					greenShipY = 525;
					repaint();
				}
			}
			if(e.getX() > 500 && e.getX() < 600) {
				greenShipX = 525;
				if(e.getY() < 100) {
					speed = speed - 100;
					greenShipY = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					greenShipY = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					greenShipY = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					greenShipY = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					greenShipY = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					greenShipY = 525;
					repaint();
				}
			}
		}
		
	}
		
	// Event handler for animation timer tick events
	protected void handleTimerTick() {
		// TODO: use controller to handle event and (if necessary) update model
			
		
	}
	
	//Testing keyboard input
	protected void handleKeyPressed(KeyEvent e){
			
		int key = e.getKeyCode();
				
		if(key == KeyEvent.VK_F) {
			if(p1Targeted == true && player1 == false) {
				p1Destroyed = true;
				repaint();
			}
			else if(p2Targeted == true && player1 == true) {
				p2Destroyed = true;
				repaint();
			}
		}
		
		if(key == KeyEvent.VK_SPACE) {
			if(player1 == true) {
				player1 = false;
				speed = 200;
			}
			else {
				player1 = true;
				speed = 200;
			}
		}
		
		if(key == KeyEvent.VK_ENTER && customizeScreenP1 == false && customizeScreenP2 == false && startGame == false) {
			customizeScreenP1 = true;
			repaint();
		}
		
		else if(key == KeyEvent.VK_ENTER && customizeScreenP1 == true && customizeScreenP2 == false && startGame == false) {
			customizeScreenP1 = false;
			customizeScreenP2 = true;
			repaint();
		}
		
		else if(key == KeyEvent.VK_ENTER && customizeScreenP1 == false && customizeScreenP2 == true && startGame == false) {
			customizeScreenP2 = false;
			startGame = true;
			repaint();
		}
	}
	
	protected void handleKeyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
				
		if(key == KeyEvent.VK_ENTER) {
			
			repaint();
		}
	}
	
	// Set the model
	public void setModel(board Board) {
		this.Board = Board;
	}
		
	// Set the controller
	public void setController(combatController combat) {
		this.combat = combat;
	}
		
	// Handle a paint event by drawing a visualization of the model data
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // paint background
		int player = 0;
		
		if(customizeScreenP1 == true && startGame == false) {
			//background image
			g.drawImage(space, 0, 0, 600, 600, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("Player 1: Customize Your Ships" , 75, 50);

			g.drawImage(shipSprite, 265, 200, 100, 100, this);
			g.drawImage(shipSprite, 165, 350, 100, 100, this);
			g.drawImage(shipSprite, 365, 350, 100, 100, this);
		}
		
		else if(customizeScreenP2 == true && startGame == false) {
			//background image
			g.drawImage(space, 0, 0, 600, 600, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("Player 2: Customize Your Ships" , 75, 50);

			g.drawImage(shipSprite2, 265, 200, 100, 100, this);
			g.drawImage(shipSprite2, 165, 350, 100, 100, this);
			g.drawImage(shipSprite2, 365, 350, 100, 100, this);
		}
		
		else if (customizeScreenP2 == false && startGame == false) {
			//g.setColor(Color.BLACK);
			//g.fillRect(0, 0, 600, 600);
			g.drawImage(space, 0, 0, 600, 600, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("Press Enter to Start" , 150, 300);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.PLAIN, 15));
			g.drawString("Controls: Click to Move" , 225, 510);
			g.drawString("Spacebar to Change Players" , 289, 530);
			g.drawString("F to fire" , 290, 550);
		}
		else {
			// TODO: render the model
			//background
			g.drawImage(space, 0, 0, 600, 600, this);
			
			if(p1Destroyed == false && p2Destroyed == false) {
				g.setColor(Color.WHITE);
				//Vertical Lines
				g.drawLine(100, 0, 100, 600);
				g.drawLine(200, 0, 200, 600);
				g.drawLine(300, 0, 300, 600);
				g.drawLine(400, 0, 400, 600);
				g.drawLine(500, 0, 500, 600);
				
				//Horizontal Lines
				g.drawLine(0, 100, 600, 100);
				g.drawLine(0, 200, 600, 200);
				g.drawLine(0, 300, 600, 300);
				g.drawLine(0, 400, 600, 400);
				g.drawLine(0, 500, 600, 500);

			}
			
			if(p1Destroyed == false) {
				if(p1Targeted == false) {
					g.setColor(Color.GREEN);
					g.drawImage(shipSprite, greenShipX, greenShipY, 50, 50, this);
				}
				else {
					g.setColor(Color.YELLOW);
					g.drawRect(greenShipX, greenShipY, 50, 50);
					g.drawImage(shipSprite, greenShipX, greenShipY, 50, 50, this);
				}	
			}
			
			if(p2Destroyed == false) {
				if(p2Targeted == false) {
					g.setColor(Color.RED);
					g.drawImage(shipSprite2, redShipX, redShipY, 50, 50, this);
				}
				else {
					g.setColor(Color.YELLOW);
					g.drawRect(redShipX, redShipY, 50, 50);
					g.drawImage(shipSprite2, redShipX, redShipY, 50, 50, this);
				}
			}
			
			if(p2Destroyed == true || p1Destroyed == true) {
				if(p2Destroyed == true) {
					player = 1;
				}
				else {
					player = 2;
				}
				//g.setColor(Color.BLACK);
				//g.fillRect(0, 0, 600, 600);
				g.drawImage(space, 0, 0, 600, 600, this);
				g.setColor(Color.WHITE);
				g.setFont(new Font(null, Font.BOLD, 50));
				g.drawString("GAME OVER" , 150, 300);
				g.setFont(new Font(null, Font.BOLD, 25));
				g.drawString("Player " + player + " Won!", 225, 350);
			}
		}
		
		
	}
	
	public static void main(String[] args) {
	      
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	
	            	int x = 0;
	            	int y = 0;
	            	int numPlayers = 0;
	            	ArrayList<ship> player1Ships = new ArrayList<>();
	            	ArrayList<ship> player2Ships = new ArrayList<>();
	            	board Board = new board(x, y);
	            	combatController combat = new combatController(numPlayers, player1Ships, player2Ships);
	            	
	            	//combat.initModel(Board);
	            	gui view = new gui();
	            	view.setModel(Board);
	            	view.setController(combat);

	                JFrame frame = new JFrame();
	                frame.setTitle("Space Game");
	                frame.setContentPane(view);
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.pack();
	                frame.setVisible(true);  
	                
	                view.startTimer();

	            }
	        });
	    }
	
	// Load an image from an embedded resource.
		private BufferedImage loadImage(String path) 
		{
			InputStream in = loadResource(path);
			try 
			{
				return ImageIO.read(in);
			} 
			
			catch (IOException e) 
			{
				throw new IllegalStateException("Couldn't load image " + path, e);
			}
		}
		
		// Load an embedded resource as an input stream.
		public InputStream loadResource(String path) 
		{
			InputStream in = getClass().getClassLoader().getResourceAsStream(path);
			if (in == null) 
			{
				throw new IllegalStateException("No such resource: " + path);
			}
			
			return in;
		}

}
