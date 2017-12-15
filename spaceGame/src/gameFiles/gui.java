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

	int p1Num = 0;
	int p2Num = 0;
	int p1ActShip = 0;
	int p2ActShip = 0;
	
	int speed = 200;
	boolean startTurn = false;
	
	boolean p1Targeted = false;
	boolean p2Targeted = false;
	boolean p1Destroyed = false;
	boolean p2Destroyed = false;
	boolean player1 = true;
	int x = 800;
	int y = 800;
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
	boolean startCustomize = false;
	
	int p1Ship1X = 125;
	int p1Ship2X = 225;
	int p1Ship3X = 325;
	int p1Ship4X = 425;
	
	int p1Ship1Y = 525;
	int p1Ship2Y = 525;
	int p1Ship3Y = 525;
	int p1Ship4Y = 525;
	
	int p2Ship1X = 125;
	int p2Ship2X = 225;
	int p2Ship3X = 325;
	int p2Ship4X = 425;
	
	int p2Ship1Y = 25;
	int p2Ship2Y = 25;
	int p2Ship3Y = 25;
	int p2Ship4Y = 25;
	
	int p1Ship1Speed = 200;
	int p1Ship2Speed = 200;
	int p1Ship3Speed = 200;
	int p1Ship4Speed = 200;
	
	int p2Ship1Speed = 200;
	int p2Ship2Speed = 200;
	int p2Ship3Speed = 200;
	int p2Ship4Speed = 200;
	
	int greenShipX = 125;
	int greenShipY = 525;
	int redShipX = 125;
	int redShipY = 25;
	
	int pointsP1 = 2500;
	int pointsP2 = 2500;
	int HP;
	int attack;
	int shield;
	
	//select ships for customization
	boolean checkP1Ship[] = new boolean[4];
	{
		for (int i = 0; i < 4; i++)
		{
			checkP1Ship[i] = false;
		}
	}
		
	boolean checkP2Ship[] = new boolean[4];
	{
		for (int i = 0; i < 4; i++)
		{
			checkP2Ship[i] = false;
		}
	}
	
	//health
	int healthValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			healthValP1[i] = 15;
		}
	}
	
	int healthValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			healthValP2[i] = 15;
		}
	}
	
	//shield
	int shieldValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			shieldValP1[i] = 2;
		}
	}
	
	int shieldValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			shieldValP2[i] = 2;
		}
	}
	
	//attack
	int attackValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			attackValP1[i] = 3;
		}
	}
	
	int attackValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			attackValP2[i] = 3;
		}
	}
	
	//range
	int rangeValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			rangeValP1[i] = 1;
		}
	}
	
	int rangeValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			rangeValP2[i] = 1;
		}
	}
	
	//speed
	int speedValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			speedValP1[i] = 2;
		}
	}
	
	int speedValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			speedValP2[i] = 2;
		}
	}
	
	//int shieldVal = 0;
	//int attackVal = 0;
	//int rangeVal = 0;
	//int speedVal = 0;
	//int healthVal = 0;
	
	//for extra menu (up or down grade)
	int selection = 0;
	
	//P1 Ship sprites
	private BufferedImage shipSprite;
	//P1 Shield sprites
	private BufferedImage shipSpriteS1;
	private BufferedImage shipSpriteS2;
	private BufferedImage shipSpriteS3;
	private BufferedImage shipSpriteS4;
	//P1 Speed sprites
	private BufferedImage shipSpriteSp1;
	private BufferedImage shipSpriteSp2;
	private BufferedImage shipSpriteSp3;
	private BufferedImage shipSpriteSp4;
	//P1 Attack sprites
	private BufferedImage shipSpriteA1;
	private BufferedImage shipSpriteA2;
	private BufferedImage shipSpriteA3;
	private BufferedImage shipSpriteA4;
	
	private BufferedImage shipSprite2;
	private BufferedImage space;
	
	private customizationController custom;

	
	public gui() {
		//setBackground(Color.BLACK);
		//PLAYER 1
		shipSprite = loadImage("gameFiles/Images/ship.png");
		//shield sprites
		shipSpriteS1 = loadImage("gameFiles/Images/shipShield1.png");
		shipSpriteS2 = loadImage("gameFiles/Images/shipShield2.png");
		shipSpriteS3 = loadImage("gameFiles/Images/shipShield3.png");
		shipSpriteS4 = loadImage("gameFiles/Images/shipShield4.png");
		
		//speed sprites
		shipSpriteSp1 = loadImage("gameFiles/Images/shipSpeed1.png");
		shipSpriteSp2 = loadImage("gameFiles/Images/shipSpeed2.png");
		shipSpriteSp3 = loadImage("gameFiles/Images/shipSpeed3.png");
		shipSpriteSp4 = loadImage("gameFiles/Images/shipSpeed4.png");
		
		//attack sprites
		shipSpriteA1 = loadImage("gameFiles/Images/shipAttack1.png");
		shipSpriteA2 = loadImage("gameFiles/Images/shipAttack2.png");
		shipSpriteA3 = loadImage("gameFiles/Images/shipAttack3.png");
		shipSpriteA4 = loadImage("gameFiles/Images/shipAttack4.png");
		
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
		//startTurn = true;
	//	repaint();
		
		
		/*if(p1Destroyed == false && p2Destroyed == false) {
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
		}*/
	}
		
	// Event handler for mouse dragged events
	protected void handleMouseDragged(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model
		
		repaint();
	}

	// Event handler for mouse released events
	protected void handleMouseReleased(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model

		//Player 2 Ships to move
		/*if(((Math.abs(e.getX() - (player2Ships.get(p2ActShip-1).getXCoord()))) + (Math.abs(e.getY() - (player2Ships.get(p2ActShip-1).getYCoord())))) <= 200 && speed > 0 && player1 == false) {
			if(e.getX() < 100) {
				player2Ships.get(p2ActShip-1).setXCoord(25);
				if(e.getY() < 100) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 100 && e.getX() < 200) {
				player2Ships.get(p2ActShip-1).setXCoord(125);
				if(e.getY() < 100) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 200 && e.getX() < 300) {
				player2Ships.get(p2ActShip-1).setXCoord(225);
				if(e.getY() < 100) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 300 && e.getX() < 400) {
				player2Ships.get(p2ActShip-1).setXCoord(325);
				if(e.getY() < 100) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 400 && e.getX() < 500) {
				player2Ships.get(p2ActShip-1).setXCoord(425);
				if(e.getY() < 100) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 500 && e.getX() < 600) {
				player2Ships.get(p2ActShip-1).setXCoord(525);
				if(e.getY() < 100) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					//player2Ships.get(p2ActShip-1).setMovesRemaining(speed);
					player2Ships.get(p2ActShip-1).setYCoord(525);
					repaint();
				}
			}
		}*/
		
	//Player 1 Ships to move
			if(((Math.abs(e.getX() - (player1Ships.get(p1ActShip-1).getXCoord()))) + (Math.abs(e.getY() - (player1Ships.get(p1ActShip-1).getYCoord())))) <= 200 && speed > 0 && player1 == true) {
				if(e.getX() < 100) {
					player1Ships.get(p1ActShip-1).setXCoord(25);
					if(e.getY() < 100) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(25);
						repaint();
					}
					if(e.getY() > 100 && e.getY() < 200) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(125);
						repaint();
					}
					if(e.getY() > 200 && e.getY() < 300) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(225);
						repaint();
					}
					if(e.getY() > 300 && e.getY() < 400) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(325);
						repaint();
					}
					if(e.getY() > 400 && e.getY() < 500) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(425);
						repaint();
					}
					if(e.getY() > 500 && e.getY() < 600) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(525);
						repaint();
					}
				}
				if(e.getX() > 100 && e.getX() < 200) {
					player1Ships.get(p1ActShip-1).setXCoord(125);
					if(e.getY() < 100) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(25);
						repaint();
					}
					if(e.getY() > 100 && e.getY() < 200) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(125);
						repaint();
					}
					if(e.getY() > 200 && e.getY() < 300) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(225);
						repaint();
					}
					if(e.getY() > 300 && e.getY() < 400) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(325);
						repaint();
					}
					if(e.getY() > 400 && e.getY() < 500) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(425);
						repaint();
					}
					if(e.getY() > 500 && e.getY() < 600) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(525);
						repaint();
					}
				}
				if(e.getX() > 200 && e.getX() < 300) {
					player1Ships.get(p1ActShip-1).setXCoord(225);
					if(e.getY() < 100) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(25);
						repaint();
					}
					if(e.getY() > 100 && e.getY() < 200) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(125);
						repaint();
					}
					if(e.getY() > 200 && e.getY() < 300) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(225);
						repaint();
					}
					if(e.getY() > 300 && e.getY() < 400) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(325);
						repaint();
					}
					if(e.getY() > 400 && e.getY() < 500) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(425);
						repaint();
					}
					if(e.getY() > 500 && e.getY() < 600) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(525);
						repaint();
					}
				}
				if(e.getX() > 300 && e.getX() < 400) {
					player1Ships.get(p1ActShip-1).setXCoord(325);
					if(e.getY() < 100) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(25);
						repaint();
					}
					if(e.getY() > 100 && e.getY() < 200) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(125);
						repaint();
					}
					if(e.getY() > 200 && e.getY() < 300) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(225);
						repaint();
					}
					if(e.getY() > 300 && e.getY() < 400) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(325);
						repaint();
					}
					if(e.getY() > 400 && e.getY() < 500) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(425);
						repaint();
					}
					if(e.getY() > 500 && e.getY() < 600) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(525);
						repaint();
					}
				}
				if(e.getX() > 400 && e.getX() < 500) {
					player1Ships.get(p1ActShip-1).setXCoord(425);
					if(e.getY() < 100) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(25);
						repaint();
					}
					if(e.getY() > 100 && e.getY() < 200) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(125);
						repaint();
					}
					if(e.getY() > 200 && e.getY() < 300) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(225);
						repaint();
					}
					if(e.getY() > 300 && e.getY() < 400) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(325);
						repaint();
					}
					if(e.getY() > 400 && e.getY() < 500) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(425);
						repaint();
					}
					if(e.getY() > 500 && e.getY() < 600) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(525);
						repaint();
					}
				}
				if(e.getX() > 500 && e.getX() < 600) {
					player1Ships.get(p1ActShip-1).setXCoord(525);
					if(e.getY() < 100) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(25);
						repaint();
					}
					if(e.getY() > 100 && e.getY() < 200) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(125);
						repaint();
					}
					if(e.getY() > 200 && e.getY() < 300) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(225);
						repaint();
					}
					if(e.getY() > 300 && e.getY() < 400) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(325);
						repaint();
					}
					if(e.getY() > 400 && e.getY() < 500) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(425);
						repaint();
					}
					if(e.getY() > 500 && e.getY() < 600) {
						speed = speed - 100;
						//player1Ships.get(p1ActShip-1).setMovesRemaining(speed);
						player1Ships.get(p1ActShip-1).setYCoord(525);
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
		
		if(customizeScreenP1 == true && startGame == false) 
		{
			if (startCustomize == false)
			{
				if(key == KeyEvent.VK_1) 
				{
					p1Num = 1;
					HP = 50;
					attack = 13;
					shield = 13;
				}
				
				else if(key == KeyEvent.VK_2) 
				{
					p1Num = 2;
					HP = 40;
					attack = 11;
					shield = 11;
				}
				else if(key == KeyEvent.VK_3) 
				{
					p1Num = 3;
					HP = 30;
					attack = 9;
					shield = 9;
				}
				else if(key == KeyEvent.VK_4) 
				{
					p1Num = 4;
					HP = 20;
					attack = 7;
					shield = 7;
				}
				
				for(int i = 0; i < p1Num; i++) 
				{
					ship Ship = new ship(null);
					//Ship.setMovesRemaining(200); //CHANGE TO VARIABLE
					player1Ships.add(Ship);
				}
				
				repaint();
			}
			
			//Customization Events
			else if (startCustomize == true)
			{
				//select ship
				if(key == KeyEvent.VK_1) 
				{
					checkP1Ship[0] = true;
					selection = 1;
				}
				
				else if(key == KeyEvent.VK_2) 
				{
					checkP1Ship[1] = true;
					selection = 2;
				}
				
				else if(key == KeyEvent.VK_3) 
				{
					checkP1Ship[2] = true;
					selection = 3;
				}
				
				else if(key == KeyEvent.VK_4) 
				{
					checkP1Ship[3] = true;
					selection = 4;
				}
				
				repaint();
				
				//Player 1 Customize
				for (int i = 0; i < p1Num; i++)
				{
					if (pointsP1 > 0)
					{			
						if (checkP1Ship[i] == true && key == KeyEvent.VK_H && healthValP1[i] < HP)
						{
							healthValP1[i]++;
							pointsP1 = pointsP1 - 50;
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_A && attackValP1[i] < attack)
						{
							attackValP1[i]++;
							pointsP1 = pointsP1 - 50;
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_S && speedValP1[i] < 4)
						{
							speedValP1[i]++;
							pointsP1 = pointsP1 - 50;
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_R && rangeValP1[i] < 3)
						{
							rangeValP1[i]++;
							pointsP1 = pointsP1 - 50;
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_D && shieldValP1[i] < shield)
						{
							shieldValP1[i]++;
							pointsP1 = pointsP1 - 50;
						}
					}
				}
			}
			
			for (int i = 0; i < p1Num; i++)
			{
				player1Ships.get(i).setMaxHP(healthValP1[i]);
				player1Ships.get(i).setDamageValue(attackValP1[i]);
				player1Ships.get(i).setSpeed(speedValP1[i]);
				player1Ships.get(i).setRange(rangeValP1[i]);
				player1Ships.get(i).setShield(shieldValP1[i]);
			}
		}
		
		if(customizeScreenP2 == true && startGame == false) {
			if (startCustomize == false) 
			{
				if(key == KeyEvent.VK_1) 
				{
					p2Num = 1;
					HP = 50;
					attack = 13;
					shield = 13;
				}
				
				else if(key == KeyEvent.VK_2) 
				{
					p2Num = 2;
					HP = 40;
					attack = 11;
					shield = 11;
				}
				
				else if(key == KeyEvent.VK_3) 
				{
					p2Num = 3;
					HP = 30;
					attack = 9;
					shield = 9;
				}
				
				else if(key == KeyEvent.VK_4) 
				{
					p2Num = 4;
					HP = 20;
					attack = 7;
					shield = 7;
				}
				
				for(int i = 0; i < p2Num; i++) 
				{
					ship Ship = new ship(null);
					//Ship.setMovesRemaining(200); //CHANGE TO VARIABLE
					player2Ships.add(Ship);
				}
				repaint();
			}
			
			else if (startCustomize == true)
			{
				//select ship
				if(key == KeyEvent.VK_1) 
				{
					checkP2Ship[0] = true;
					selection = 1;
				}
				
				else if(key == KeyEvent.VK_2) 
				{
					checkP2Ship[1] = true;
					selection = 2;
				}
				else if(key == KeyEvent.VK_3) 
				{
					checkP2Ship[2] = true;
					selection = 3;
				}
				else if(key == KeyEvent.VK_4) 
				{
					checkP2Ship[3] = true;
					selection = 4;
				}
				
				repaint();
				
				//Player 2 Customize
				for (int i = 0; i < p2Num; i++)
				{
					if (pointsP2 > 0)
					{
						if (checkP2Ship[i] == true && key == KeyEvent.VK_H && healthValP2[i] < HP)
						{
							healthValP2[i]++;
							pointsP2 = pointsP2 - 50;
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_A && attackValP2[i] < attack)
						{
							attackValP2[i]++;
							pointsP2 = pointsP2 - 50;
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_S && speedValP2[i] < 4)
						{
							speedValP2[i]++;
							pointsP2 = pointsP2 - 50;
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_R && rangeValP2[i] < 3)
						{
							rangeValP2[i]++;
							pointsP2 = pointsP2 - 50;
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_D && shieldValP2[i] < shield)
						{
							shieldValP2[i]++;
							pointsP2 = pointsP2 - 50;
						}
					}
				}
			}
			
			for (int i = 0; i < p2Num; i++)
			{
				player2Ships.get(i).setMaxHP(healthValP2[i]);
				player2Ships.get(i).setDamageValue(attackValP2[i]);
				player2Ships.get(i).setSpeed(speedValP2[i]);
				player2Ships.get(i).setRange(rangeValP2[i]);
				player2Ships.get(i).setShield(shieldValP2[i]);
			}
		}
				
		//Player 1
		if(key == KeyEvent.VK_1 && startGame == true && player1 == true) {
			p1ActShip = 1;
			speed = 200;
			repaint();
		}
		if(key == KeyEvent.VK_2 && startGame == true && player1 == true) {
			p1ActShip = 2;
			speed = 200;
			repaint();
		}
		if(key == KeyEvent.VK_3 && startGame == true && player1 == true) {
			p1ActShip = 3;
			speed = 200;
			repaint();
		}
		if(key == KeyEvent.VK_4 && startGame == true && player1 == true) {
			p1ActShip = 4;
			speed = 200;
			repaint();
		}
		
		//Player 2
		if(key == KeyEvent.VK_1 && startGame == true && player1 == false) {
			p2ActShip = 1;
			speed = 200;
			repaint();
		}
		if(key == KeyEvent.VK_2 && startGame == true && player1 == false) {
			p2ActShip = 2;
			speed = 200;
			repaint();
		}
		if(key == KeyEvent.VK_3 && startGame == true && player1 == false) {
			p2ActShip = 3;
			speed = 200;
			repaint();
		}
		if(key == KeyEvent.VK_4 && startGame == true && player1 == false) {
			p2ActShip = 4;
			speed = 200;
			repaint();
		}
		
		
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
		if(key == KeyEvent.VK_0) {
			if(startTurn == false) {
				startTurn = true;
			}
		}
		
		if(key == KeyEvent.VK_SPACE) {
			if(player1 == true) {
				player1 = false;
				p1ActShip = 0;
				speed = 200;
				p1Ship1Speed = 200;
				p1Ship2Speed = 200;
				p1Ship3Speed = 200;
				p1Ship4Speed = 200;
			}
			else {
				player1 = true;
				p2ActShip = 0;
				speed = 200;
				p2Ship1Speed = 200;
				p2Ship2Speed = 200;
				p2Ship3Speed = 200;
				p2Ship4Speed = 200;
			}
		}
		
		if(key == KeyEvent.VK_ENTER && customizeScreenP1 == false && customizeScreenP2 == false && startGame == false) {
			customizeScreenP1 = true;
			repaint();
		}
		
		else if(key == KeyEvent.VK_ENTER && customizeScreenP1 == true && customizeScreenP2 == false && startGame == false && startCustomize == true) {
			customizeScreenP1 = false;
			startCustomize = false;
			customizeScreenP2 = true;
			repaint();
		}
				
		else if(key == KeyEvent.VK_ENTER && customizeScreenP1 == false && customizeScreenP2 == true && startGame == false && startCustomize == true) {
			customizeScreenP2 = false;
			startCustomize = false;
			startGame = true;
			repaint();
			
		}
		
		else if(key == KeyEvent.VK_ENTER && customizeScreenP1 == true && customizeScreenP2 == false && startGame == false && startCustomize == false) {
			startCustomize = true;
			repaint();
		}
		
		else if(key == KeyEvent.VK_ENTER && customizeScreenP1 == false && customizeScreenP2 == true && startGame == false && startCustomize == false) {
			startCustomize = true;
			repaint();
		}
	}
	
	protected void handleKeyReleased(KeyEvent e){
		
		//int key = e.getKeyCode();
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
			g.drawImage(space, 0, 0, 800, 800, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 40));
			g.drawString("Player 1: Customize Your Ships" , 100, 55);
			
			g.setFont(new Font(null, Font.PLAIN, 25));
			g.drawString("Points: " + pointsP1, 25, 780);
			g.drawString("Number of Ships: " + p1Num, 550, 780);
			
			if (p1Num == 1)
			{
				g.drawImage(shipSprite, 350, 150, 150, 150, this);
			}
			
			else if (p1Num == 2)
			{
				g.drawImage(shipSprite, 350, 150, 150, 150, this);
				g.drawImage(shipSprite, 150, 325, 150, 150, this);
			}
			
			else if (p1Num == 3)
			{
				g.drawImage(shipSprite, 350, 150, 150, 150, this);
				g.drawImage(shipSprite, 150, 325, 150, 150, this);
				g.drawImage(shipSprite, 550, 325, 150, 150, this);
			}
			
			else if (p1Num == 4)
			{
				g.drawImage(shipSprite, 350, 150, 150, 150, this);
				g.drawImage(shipSprite, 150, 325, 150, 150, this);
				g.drawImage(shipSprite, 550, 325, 150, 150, this);
				g.drawImage(shipSprite, 350, 500, 150, 150, this);
			}
			
			if (startCustomize == true)
			{
				g.setFont(new Font(null, Font.PLAIN, 17));
				g.drawString("Upgrades:", 10, 100);
				g.drawString("H to boost health", 15, 120);
				g.drawString("A to boost attack", 15, 140);
				g.drawString("S to boost speed", 15, 160);
				g.drawString("D to boost defense", 15, 180);
				g.drawString("R to boost range", 15, 200);
				//g.drawString("Up arrow to Upgrade", 15, 190);
				//g.drawString("Down arrow to Downgrade", 15, 205);
				
				if (checkP1Ship[0] == true && selection == 1 && p1Num > 0)
				{
					g.drawOval(340, 145, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 500, 110);
					g.drawString("Health: "+healthValP1[0], 515, 125);
					g.drawString("Attack: "+attackValP1[0], 515, 140);
					g.drawString("Speed: "+speedValP1[0], 515, 155);
					g.drawString("Defense: "+shieldValP1[0], 515, 170);
					g.drawString("Range: "+rangeValP1[0], 515, 185);
				}
			
				else if (checkP1Ship[1] == true && selection == 2 && p1Num > 1)
				{
					g.drawOval(140, 320, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 40, 320);
					g.drawString("Health: "+healthValP1[1], 55, 335);
					g.drawString("Attack: "+attackValP1[1], 55, 350);
					g.drawString("Speed: "+speedValP1[1], 55, 365);
					g.drawString("Defense: "+shieldValP1[1], 55, 380);
					g.drawString("Range: "+rangeValP1[1], 55, 395);
				}
				
				if (checkP1Ship[2] == true && selection == 3 && p1Num > 2)
				{
					g.drawOval(540, 320, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 670, 500);
					g.drawString("Health: "+healthValP1[2], 685, 515);
					g.drawString("Attack: "+attackValP1[2], 685, 530);
					g.drawString("Speed: "+speedValP1[2], 685, 545);
					g.drawString("Defense: "+shieldValP1[2], 685, 560);
					g.drawString("Range: "+rangeValP1[2], 685, 575);
				}
				
				if (checkP1Ship[3] == true && selection == 4 && p1Num > 3)
				{
					g.drawOval(340, 495, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 265, 640);
					g.drawString("Health: "+healthValP1[3], 280, 655);
					g.drawString("Attack: "+attackValP1[3], 280, 670);
					g.drawString("Speed: "+speedValP1[3], 280, 685);
					g.drawString("Defense: "+shieldValP1[3], 280, 700);
					g.drawString("Range: "+rangeValP1[3], 280, 715);
				}
			}
		}
		
		else if(customizeScreenP2 == true && startGame == false) {
			//background image
			g.drawImage(space, 0, 0, 800, 800, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 40));
			g.drawString("Player 2: Customize Your Ships" , 100, 55);
			
			g.setFont(new Font(null, Font.PLAIN, 25));
			g.drawString("Points: " + pointsP2, 25, 780);
			g.drawString("Number of Ships: " + p2Num, 550, 780);

			if (p2Num == 1)
			{
				g.drawImage(shipSprite2, 350, 150, 150, 150, this);
			}
			
			else if (p2Num == 2)
			{
				g.drawImage(shipSprite2, 350, 150, 150, 150, this);
				g.drawImage(shipSprite2, 150, 325, 150, 150, this);
			}
			
			else if (p2Num == 3)
			{
				g.drawImage(shipSprite2, 350, 150, 150, 150, this);
				g.drawImage(shipSprite2, 150, 325, 150, 150, this);
				g.drawImage(shipSprite2, 550, 325, 150, 150, this);
			}
			
			else if (p2Num == 4)
			{
				g.drawImage(shipSprite2, 350, 150, 150, 150, this);
				g.drawImage(shipSprite2, 150, 325, 150, 150, this);
				g.drawImage(shipSprite2, 550, 325, 150, 150, this);
				g.drawImage(shipSprite2, 350, 500, 150, 150, this);
			}
			
			if (startCustomize == true)
			{
				g.setFont(new Font(null, Font.PLAIN, 17));
				g.drawString("Upgrades:", 10, 100);
				g.drawString("H to boost health", 15, 120);
				g.drawString("A to boost attack", 15, 140);
				g.drawString("S to boost speed", 15, 160);
				g.drawString("D to boost defense", 15, 180);
				g.drawString("R to boost range", 15, 200);
				//g.drawString("Up arrow to Upgrade", 15, 190);
				//g.drawString("Down arrow to Downgrade", 15, 205);
				
				if (checkP2Ship[0] == true && selection == 1 && p2Num > 0)
				{
					g.drawOval(340, 135, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 500, 110);
					g.drawString("Health: "+healthValP2[0], 515, 125);
					g.drawString("Attack: "+attackValP2[0], 515, 140);
					g.drawString("Speed: "+speedValP2[0], 515, 155);
					g.drawString("Defense: "+shieldValP2[0], 515, 170);
					g.drawString("Range: "+rangeValP2[0], 515, 185);
				}
				
				if (checkP2Ship[1] == true && selection == 2 && p2Num > 1)
				{
					g.drawOval(140, 310, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 40, 320);
					g.drawString("Health: "+healthValP2[1], 55, 335);
					g.drawString("Attack: "+attackValP2[1], 55, 350);
					g.drawString("Speed: "+speedValP2[1], 55, 365);
					g.drawString("Defense: "+shieldValP2[1], 55, 380);
					g.drawString("Range: "+rangeValP2[1], 55, 395);
				}
				
				if (checkP2Ship[2] == true && selection == 3 && p2Num > 2)
				{
					g.drawOval(540, 310, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 670, 500);
					g.drawString("Health: "+healthValP2[2], 685, 515);
					g.drawString("Attack: "+attackValP2[2], 685, 530);
					g.drawString("Speed: "+speedValP2[2], 685, 545);
					g.drawString("Defense: "+shieldValP2[2], 685, 560);
					g.drawString("Range: "+rangeValP2[2], 685, 575);
				}
				
				if (checkP2Ship[3] == true && selection == 4 && p2Num > 3)
				{
					g.drawOval(340, 485, 170, 170);
					g.setFont(new Font(null, Font.PLAIN, 17));
					g.drawString("Stats:", 265, 640);
					g.drawString("Health: "+healthValP2[3], 280, 655);
					g.drawString("Attack: "+attackValP2[3], 280, 670);
					g.drawString("Speed: "+speedValP2[3], 280, 685);
					g.drawString("Defense: "+shieldValP2[3], 280, 700);
					g.drawString("Range: "+rangeValP2[3], 280, 715);
				}
			}
		}
		
		else if (customizeScreenP2 == false && startGame == false) {
			//g.setColor(Color.BLACK);
			//g.fillRect(0, 0, 600, 600);
			g.drawImage(space, 0, 0, 800, 800, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 60));
			g.drawString("Press Enter to Start" , 125, 400);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.PLAIN, 20));
			g.drawString("Controls: Click to Move Ship" , 270, 460);
			g.drawString("Spacebar to Change Players" , 355, 485);
			g.drawString("F to fire" , 355, 510);
		}
		
		else {
			// TODO: render the model
			//background
			g.drawImage(space, 0, 0, 800, 800, this);
			
			if (player1 == true)
			{
				if (p2Num > 0)
				{
					g.setColor(Color.WHITE);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Player 2 Ship 1 Stats", 625, 30+20);
					g.drawString("Player 2 Health: " + player2Ships.get(0).getMaxHP(), 650, 45+20);
					g.drawString("Player 2 Attack: " + player2Ships.get(0).getDamageValue(), 650, 60+20);
					g.drawString("Player 2 Defense: " + player2Ships.get(0).getShield(), 650, 75+20);
					g.drawString("Player 2 Speed: " + player2Ships.get(0).getSpeed(), 650, 90+20);
					g.drawString("Player 2 Range: " + player2Ships.get(0).getRange(), 650, 105+20);
				}
				
				if (p2Num > 1)
				{
					g.setColor(Color.WHITE);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Player 2 Ship 2 Stats", 625, 150+20);
					g.drawString("Player 2 Health: " + player2Ships.get(1).getMaxHP(), 650, 165+20);
					g.drawString("Player 2 Attack: " + player2Ships.get(1).getDamageValue(), 650, 180+20);
					g.drawString("Player 2 Defense: " + player2Ships.get(1).getShield(), 650, 195+20);
					g.drawString("Player 2 Speed: " + player2Ships.get(1).getSpeed(), 650, 210+20);
					g.drawString("Player 2 Range: " + player2Ships.get(1).getRange(), 650, 225+20);
				}
				
				if (p2Num > 2)
				{
					g.setColor(Color.WHITE);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Player 2 Ship 2 Stats", 625, 270+20);
					g.drawString("Player 2 Health: " + player2Ships.get(2).getMaxHP(), 650, 285+20);
					g.drawString("Player 2 Attack: " + player2Ships.get(2).getDamageValue(), 650, 300+20);
					g.drawString("Player 2 Defense: " + player2Ships.get(2).getShield(), 650, 315+20);
					g.drawString("Player 2 Speed: " + player2Ships.get(2).getSpeed(), 650, 330+20);
					g.drawString("Player 2 Range: " + player2Ships.get(2).getRange(), 650, 345+20);
				}
				
				if (p2Num > 3)
				{
					g.setColor(Color.WHITE);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Player 2 Ship 2 Stats", 625, 385);
					g.drawString("Player 2 Health: " + player2Ships.get(3).getMaxHP(), 650, 400+20);
					g.drawString("Player 2 Attack: " + player2Ships.get(3).getDamageValue(), 650, 415+20);
					g.drawString("Player 2 Defense: " + player2Ships.get(3).getShield(), 650, 430+20);
					g.drawString("Player 2 Speed: " + player2Ships.get(3).getSpeed(), 650, 445+20);
					g.drawString("Player 2 Range: " + player2Ships.get(3).getRange(), 650, 460+20);
				}
			}
			
			if(p1Destroyed == false && p2Destroyed == false) {
				g.setColor(Color.WHITE);
				//Vertical Lines
				g.drawLine(100, 0, 100, 600);
				g.drawLine(200, 0, 200, 600);
				g.drawLine(300, 0, 300, 600);
				g.drawLine(400, 0, 400, 600);
				g.drawLine(500, 0, 500, 600);
				g.drawLine(600, 0, 600, 800);
				
				//Horizontal Lines
				g.drawLine(0, 100, 600, 100);
				g.drawLine(0, 200, 600, 200);
				g.drawLine(0, 300, 600, 300);
				g.drawLine(0, 400, 600, 400);
				g.drawLine(0, 500, 600, 500);
				g.drawLine(0, 600, 800, 600);

			}
			/*g.setFont(new Font(null, Font.BOLD, 15));
			g.setColor(Color.BLACK);
			g.fillRect(601, 0, 800, 800);*/
					if(p1Destroyed == false) {
						for(int i = 0; i < p1Num; i++) {
							if(startTurn == false) {
								player1Ships.get(i).setYCoord(greenShipY);
								if(i == 0) {
									player1Ships.get(0).setXCoord(greenShipX);
								}
								else if(i == 1) {
									player1Ships.get(1).setXCoord(greenShipX + 100);
								}
								else if(i == 2) {
									player1Ships.get(2).setXCoord(greenShipX + 200);
								}
								else if(i == 3) {
									player1Ships.get(3).setXCoord(greenShipX + 300);
								}
								g.drawImage(shipSprite, player1Ships.get(i).getXCoord(), player1Ships.get(i).getYCoord(), 50, 50, this);
							}
							
							if(startTurn == true){
								if(p1ActShip > 0) {
									g.setColor(Color.GREEN);
							 		g.drawRect(player1Ships.get(p1ActShip - 1).getXCoord(), player1Ships.get(p1ActShip - 1).getYCoord(), 50, 50);
							 		g.drawImage(shipSprite, player1Ships.get(p1ActShip - 1).getXCoord(), player1Ships.get(p1ActShip - 1).getYCoord(), 50, 50, this);
								}
								g.drawImage(shipSprite, player1Ships.get(i).getXCoord(), player1Ships.get(i).getYCoord(), 50, 50, this);
							}
						}
					}
						
							
							/*if(p1Num == 1) {
								if(p1ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship1X, p1Ship1Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p1Ship1X), (p1Ship1Y));
								g.drawImage(shipSprite, p1Ship1X, p1Ship1Y, 50, 50, this);
								
								if(player1 == true) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 1 SHIPS", 650, 75);
									g.drawString("1", 675, 300);
									g.drawImage(shipSprite, 675, 300, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 350, 50, 5);
								}
								
							}
							
							if(p1Num == 2) {
								if(p1ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship1X, p1Ship1Y, 50, 50);
								}
								if(p1ActShip == 2) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship2X, p1Ship2Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p1Ship1X), (p1Ship1Y));
								g.drawString("2", (p1Ship2X), (p1Ship2Y));
								g.drawImage(shipSprite, p1Ship1X, p1Ship1Y, 50, 50, this);
								g.drawImage(shipSprite, p1Ship2X, p1Ship2Y, 50, 50, this);
								
								if(player1 == true) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 1 SHIPS", 650, 75);
									g.drawString("1", 675, 250);
									g.drawString("2", 675, 350);
									g.drawImage(shipSprite, 675, 250, 50, 50, this);
									g.drawImage(shipSprite, 675, 350, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 300, 50, 5);
									g.fillRect(675, 400, 50, 5);
								}
							}
							
							if(p1Num == 3) {
								if(p1ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship1X, p1Ship1Y, 50, 50);
								}
								if(p1ActShip == 2) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship2X, p1Ship2Y, 50, 50);
								}
								if(p1ActShip == 3) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship3X, p1Ship3Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p1Ship1X), (p1Ship1Y));
								g.drawString("2", (p1Ship2X), (p1Ship2Y));
								g.drawString("3", (p1Ship3X), (p1Ship3Y));
								g.drawImage(shipSprite, p1Ship1X, p1Ship1Y, 50, 50, this);
								g.drawImage(shipSprite, p1Ship2X, p1Ship2Y, 50, 50, this);
								g.drawImage(shipSprite, p1Ship3X, p1Ship3Y, 50, 50, this);
								
								if(player1 == true) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 1 SHIPS", 650, 75);
									g.drawString("1", 675, 200);
									g.drawString("2", 675, 300);
									g.drawString("3", 675, 400);
									g.drawImage(shipSprite, 675, 200, 50, 50, this);
									g.drawImage(shipSprite, 675, 300, 50, 50, this);
									g.drawImage(shipSprite, 675, 400, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 250, 50, 5);
									g.fillRect(675, 350, 50, 5);
									g.fillRect(675, 450, 50, 5);
								}
							}
							
							if(p1Num == 4) {
								if(p1ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship1X, p1Ship1Y, 50, 50);
								}
								if(p1ActShip == 2) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship2X, p1Ship2Y, 50, 50);
								}
								if(p1ActShip == 3) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship3X, p1Ship3Y, 50, 50);
								}
								if(p1ActShip == 4) {
									g.setColor(Color.GREEN);
									g.fillRect(p1Ship4X, p1Ship4Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p1Ship1X), (p1Ship1Y));
								g.drawString("2", (p1Ship2X), (p1Ship2Y));
								g.drawString("3", (p1Ship3X), (p1Ship3Y));
								g.drawString("4", (p1Ship4X), (p1Ship4Y));
								g.drawImage(shipSprite, p1Ship1X, p1Ship1Y, 50, 50, this);
								g.drawImage(shipSprite, p1Ship2X, p1Ship2Y, 50, 50, this);
								g.drawImage(shipSprite, p1Ship3X, p1Ship3Y, 50, 50, this);
								g.drawImage(shipSprite, p1Ship4X, p1Ship4Y, 50, 50, this);
								
								if(player1 == true) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 1 SHIPS", 650, 75);
									g.drawString("1", 675, 150);
									g.drawString("2", 675, 250);
									g.drawString("3", 675, 350);
									g.drawString("4", 675, 450);
									g.drawImage(shipSprite, 675, 150, 50, 50, this);
									g.drawImage(shipSprite, 675, 250, 50, 50, this);
									g.drawImage(shipSprite, 675, 350, 50, 50, this);
									g.drawImage(shipSprite, 675, 450, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 200, 50, 5);
									g.fillRect(675, 300, 50, 5);
									g.fillRect(675, 400, 50, 5);
									g.fillRect(675, 500, 50, 5);
								}
							}*/
			
					if(p2Destroyed == false) {
						for(int i = 0; i < p2Num; i++) {
							if(startTurn == false) {
								player2Ships.get(i).setYCoord(redShipY);
								if(i == 0) {
									player2Ships.get(0).setXCoord(redShipX);
								}
								else if(i == 1) {
									player2Ships.get(1).setXCoord(redShipX + 100);
								}
								else if(i == 2) {
									player2Ships.get(2).setXCoord(redShipX + 200);
								}
								else if(i == 3) {
									player2Ships.get(3).setXCoord(redShipX + 300);
								}
								g.drawImage(shipSprite2, player2Ships.get(i).getXCoord(), player2Ships.get(i).getYCoord(), 50, 50, this);
							}
							
							if(startTurn == true){
								if(p2ActShip > 0) {
									g.setColor(Color.GREEN);
							 		g.drawRect(player2Ships.get(p2ActShip - 1).getXCoord(), player2Ships.get(p2ActShip - 1).getYCoord(), 50, 50);
							 		g.drawImage(shipSprite2, player2Ships.get(p2ActShip - 1).getXCoord(), player2Ships.get(p2ActShip - 1).getYCoord(), 50, 50, this);
								}
								g.drawImage(shipSprite2, player2Ships.get(i).getXCoord(), player2Ships.get(i).getYCoord(), 50, 50, this);
							}
						}
					}
			
			if(p2Destroyed == true || p1Destroyed == true) {
				if(p2Destroyed == true) {
					player = 1;
				}
				else {
					player = 2;
				}
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
	            	String name = null;
	            	ArrayList<ship> player1Ships = new ArrayList<>();
	            	ArrayList<ship> player2Ships = new ArrayList<>();
	            	board Board = new board(x, y);
	            	combatController combat = new combatController(numPlayers, player1Ships, player2Ships);
	            	//ship Ship = new ship(name);
	            	
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
	
	// Loads image from resource
		private BufferedImage loadImage(String path) 
		{
			InputStream in = loadResource(path);
			
			try 
			{
				return ImageIO.read(in);
			} 
			
			catch (IOException e) 
			{
				throw new IllegalStateException("Image does not exist. " + path, e);
			}
		}
		
		// Loads resource as an input
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
