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
	
	boolean p1Targeted = false;
	boolean p2Targeted = false;
	boolean p1Destroyed = false;
	boolean p2Destroyed = false;
	boolean player1 = true;
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
	
	int points = 500;
	
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
			healthValP1[i] = 12;
		}
	}
	
	int healthValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			healthValP2[i] = 12;
		}
	}
	
	//shield
	int shieldValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			shieldValP1[i] = 5;
		}
	}
	
	int shieldValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			shieldValP2[i] = 5;
		}
	}
	
	//attack
	int attackValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			attackValP1[i] = 8;
		}
	}
	
	int attackValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			attackValP2[i] = 8;
		}
	}
	
	//range
	int rangeValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			rangeValP1[i] = 9;
		}
	}
	
	int rangeValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			rangeValP2[i] = 9;
		}
	}
	
	//speed
	int speedValP1[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			speedValP1[i] = 11;
		}
	}
	
	int speedValP2[] = new int[4];
	{
		for (int i = 0; i < 4; i++)
		{
			speedValP2[i] = 11;
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

		//Player 2 moves ship 1
		if(p2ActShip == 1) {
		if(((Math.abs(e.getX() - p2Ship1X)) + (Math.abs(e.getY() - p2Ship1Y))) <= 200 && p2Ship1Speed > 0 && player1 == false) {
			p2Ship1Speed = p2Ship1Speed - 100;
			if(e.getX() < 100) {
				p2Ship1X = 25;
				if(e.getY() < 100) {
					p2Ship1Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p2Ship1Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p2Ship1Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p2Ship1Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p2Ship1Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p2Ship1Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 100 && e.getX() < 200) {
				p2Ship1X = 125;
				if(e.getY() < 100) {
					p2Ship1Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p2Ship1Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p2Ship1Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p2Ship1Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p2Ship1Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p2Ship1Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 200 && e.getX() < 300) {
				p2Ship1X = 225;
				if(e.getY() < 100) {
					p2Ship1Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p2Ship1Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p2Ship1Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p2Ship1Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p2Ship1Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p2Ship1Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 300 && e.getX() < 400) {
				p2Ship1X = 325;
				if(e.getY() < 100) {
					p2Ship1Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p2Ship1Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p2Ship1Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p2Ship1Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p2Ship1Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p2Ship1Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 400 && e.getX() < 500) {
				p2Ship1X = 425;
				if(e.getY() < 100) {
					p2Ship1Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p2Ship1Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p2Ship1Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p2Ship1Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p2Ship1Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p2Ship1Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 500 && e.getX() < 600) {
				p2Ship1X = 525;
				if(e.getY() < 100) {
					p1Ship1Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p2Ship1Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p2Ship1Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p2Ship1Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p2Ship1Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p2Ship1Y = 525;
					repaint();
				}
			}
		}
		}
		
	//Player 2 moves ship 2
	if(p2ActShip == 2) {
	if(((Math.abs(e.getX() - p2Ship2X)) + (Math.abs(e.getY() - p2Ship2Y))) <= 200 && p2Ship2Speed > 0 && player1 == false) {
		p2Ship2Speed = p2Ship2Speed - 100;
		if(e.getX() < 100) {
			p2Ship2X = 25;
			if(e.getY() < 100) {
				p2Ship2Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship2Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship2Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship2Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship2Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship2Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 100 && e.getX() < 200) {
			p2Ship2X = 125;
			if(e.getY() < 100) {
				p2Ship2Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship2Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship2Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship2Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship2Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship2Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 200 && e.getX() < 300) {
			p2Ship2X = 225;
			if(e.getY() < 100) {
				p2Ship2Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship2Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship2Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship2Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship2Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship2Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 300 && e.getX() < 400) {
			p2Ship2X = 325;
			if(e.getY() < 100) {
				p2Ship2Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship2Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship2Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship2Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship2Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship2Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 400 && e.getX() < 500) {
			p2Ship2X = 425;
			if(e.getY() < 100) {
				p2Ship2Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship2Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship2Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship2Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship2Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship2Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 500 && e.getX() < 600) {
			p2Ship2X = 525;
			if(e.getY() < 100) {
				p2Ship2Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship2Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship2Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship2Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship2Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship2Y = 525;
				repaint();
			}
		}
	}
	}
	
	//Player 2 moves ship 3
	if(p2ActShip == 3) {
	if(((Math.abs(e.getX() - p2Ship3X)) + (Math.abs(e.getY() - p2Ship3Y))) <= 200 && p2Ship3Speed > 0 && player1 == false) {
		p2Ship3Speed = p2Ship3Speed - 100;
		if(e.getX() < 100) {
			p2Ship3X = 25;
			if(e.getY() < 100) {
				p2Ship3Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship3Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship3Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship3Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship3Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship3Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 100 && e.getX() < 200) {
			p2Ship3X = 125;
			if(e.getY() < 100) {
				p2Ship3Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship3Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship3Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship3Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship3Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship3Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 200 && e.getX() < 300) {
			p2Ship3X = 225;
			if(e.getY() < 100) {
				p2Ship3Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship3Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship3Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship3Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship3Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship3Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 300 && e.getX() < 400) {
			p2Ship3X = 325;
			if(e.getY() < 100) {
				p2Ship3Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship3Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship3Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship3Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship3Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship3Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 400 && e.getX() < 500) {
			p2Ship3X = 425;
			if(e.getY() < 100) {
				p2Ship3Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship3Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship3Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship3Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship3Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship3Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 500 && e.getX() < 600) {
			p2Ship3X = 525;
			if(e.getY() < 100) {
				p2Ship3Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship3Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship3Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship3Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship3Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship3Y = 525;
				repaint();
			}
		}
	}
	}

	//Player 2 moves ship 4
	if(p2ActShip == 4) {
	if(((Math.abs(e.getX() - p2Ship4X)) + (Math.abs(e.getY() - p2Ship4Y))) <= 200 && p2Ship4Speed > 0 && player1 == false) {
		p2Ship4Speed = p2Ship4Speed - 100;
		if(e.getX() < 100) {
			p2Ship4X = 25;
			if(e.getY() < 100) {
				p2Ship4Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship4Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship4Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship4Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship4Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship4Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 100 && e.getX() < 200) {
			p2Ship4X = 125;
			if(e.getY() < 100) {
				p2Ship4Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship4Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship4Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship4Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship4Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship4Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 200 && e.getX() < 300) {
			p2Ship4X = 225;
			if(e.getY() < 100) {
				p2Ship4Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship4Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship4Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship4Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship4Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship4Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 300 && e.getX() < 400) {
			p2Ship4X = 325;
			if(e.getY() < 100) {
				p2Ship4Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship4Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship4Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship4Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship4Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship4Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 400 && e.getX() < 500) {
			p2Ship4X = 425;
			if(e.getY() < 100) {
				p2Ship4Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship4Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship4Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship4Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship4Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship4Y = 525;
				repaint();
			}
		}
		
		if(e.getX() > 500 && e.getX() < 600) {
			p2Ship4X = 525;
			if(e.getY() < 100) {
				p2Ship4Y = 25;
				repaint();
			}
			if(e.getY() > 100 && e.getY() < 200) {
				p2Ship4Y = 125;
				repaint();
			}
			if(e.getY() > 200 && e.getY() < 300) {
				p2Ship4Y = 225;
				repaint();
			}
			if(e.getY() > 300 && e.getY() < 400) {
				p2Ship4Y = 325;
				repaint();
			}
			if(e.getY() > 400 && e.getY() < 500) {
				p2Ship4Y = 425;
				repaint();
			}
			if(e.getY() > 500 && e.getY() < 600) {
				p2Ship4Y = 525;
				repaint();
			}
		}
	}
	}
		
		//Player 1 moves ship 1
				if(p1ActShip == 1) {
				if(((Math.abs(e.getX() - p1Ship1X)) + (Math.abs(e.getY() - p1Ship1Y))) <= 200 && p1Ship1Speed > 0 && player1 == true) {
					p1Ship1Speed = p1Ship1Speed - 100;
					if(e.getX() < 100) {
						p1Ship1X = 25;
						if(e.getY() < 100) {
							p1Ship1Y = 25;
							repaint();
						}
						if(e.getY() > 100 && e.getY() < 200) {
							p1Ship1Y = 125;
							repaint();
						}
						if(e.getY() > 200 && e.getY() < 300) {
							p1Ship1Y = 225;
							repaint();
						}
						if(e.getY() > 300 && e.getY() < 400) {
							p1Ship1Y = 325;
							repaint();
						}
						if(e.getY() > 400 && e.getY() < 500) {
							p1Ship1Y = 425;
							repaint();
						}
						if(e.getY() > 500 && e.getY() < 600) {
							p1Ship1Y = 525;
							repaint();
						}
					}
					
					if(e.getX() > 100 && e.getX() < 200) {
						p1Ship1X = 125;
						if(e.getY() < 100) {
							p1Ship1Y = 25;
							repaint();
						}
						if(e.getY() > 100 && e.getY() < 200) {
							p1Ship1Y = 125;
							repaint();
						}
						if(e.getY() > 200 && e.getY() < 300) {
							p1Ship1Y = 225;
							repaint();
						}
						if(e.getY() > 300 && e.getY() < 400) {
							p1Ship1Y = 325;
							repaint();
						}
						if(e.getY() > 400 && e.getY() < 500) {
							p1Ship1Y = 425;
							repaint();
						}
						if(e.getY() > 500 && e.getY() < 600) {
							p1Ship1Y = 525;
							repaint();
						}
					}
					
					if(e.getX() > 200 && e.getX() < 300) {
						p1Ship1X = 225;
						if(e.getY() < 100) {
							p1Ship1Y = 25;
							repaint();
						}
						if(e.getY() > 100 && e.getY() < 200) {
							p1Ship1Y = 125;
							repaint();
						}
						if(e.getY() > 200 && e.getY() < 300) {
							p1Ship1Y = 225;
							repaint();
						}
						if(e.getY() > 300 && e.getY() < 400) {
							p1Ship1Y = 325;
							repaint();
						}
						if(e.getY() > 400 && e.getY() < 500) {
							p1Ship1Y = 425;
							repaint();
						}
						if(e.getY() > 500 && e.getY() < 600) {
							p1Ship1Y = 525;
							repaint();
						}
					}
					
					if(e.getX() > 300 && e.getX() < 400) {
						p1Ship1X = 325;
						if(e.getY() < 100) {
							p1Ship1Y = 25;
							repaint();
						}
						if(e.getY() > 100 && e.getY() < 200) {
							p1Ship1Y = 125;
							repaint();
						}
						if(e.getY() > 200 && e.getY() < 300) {
							p1Ship1Y = 225;
							repaint();
						}
						if(e.getY() > 300 && e.getY() < 400) {
							p1Ship1Y = 325;
							repaint();
						}
						if(e.getY() > 400 && e.getY() < 500) {
							p1Ship1Y = 425;
							repaint();
						}
						if(e.getY() > 500 && e.getY() < 600) {
							p1Ship1Y = 525;
							repaint();
						}
					}
					
					if(e.getX() > 400 && e.getX() < 500) {
						p1Ship1X = 425;
						if(e.getY() < 100) {
							p1Ship1Y = 25;
							repaint();
						}
						if(e.getY() > 100 && e.getY() < 200) {
							p1Ship1Y = 125;
							repaint();
						}
						if(e.getY() > 200 && e.getY() < 300) {
							p1Ship1Y = 225;
							repaint();
						}
						if(e.getY() > 300 && e.getY() < 400) {
							p1Ship1Y = 325;
							repaint();
						}
						if(e.getY() > 400 && e.getY() < 500) {
							p1Ship1Y = 425;
							repaint();
						}
						if(e.getY() > 500 && e.getY() < 600) {
							p1Ship1Y = 525;
							repaint();
						}
					}
					
					if(e.getX() > 500 && e.getX() < 600) {
						p1Ship1X = 525;
						if(e.getY() < 100) {
							p1Ship1Y = 25;
							repaint();
						}
						if(e.getY() > 100 && e.getY() < 200) {
							p1Ship1Y = 125;
							repaint();
						}
						if(e.getY() > 200 && e.getY() < 300) {
							p1Ship1Y = 225;
							repaint();
						}
						if(e.getY() > 300 && e.getY() < 400) {
							p1Ship1Y = 325;
							repaint();
						}
						if(e.getY() > 400 && e.getY() < 500) {
							p1Ship1Y = 425;
							repaint();
						}
						if(e.getY() > 500 && e.getY() < 600) {
							p1Ship1Y = 525;
							repaint();
						}
					}
				}
				}
				
		//Player 1 moves ship 2
		if(p1ActShip == 2) {
		if(((Math.abs(e.getX() - p1Ship2X)) + (Math.abs(e.getY() - p1Ship2Y))) <= 200 && p1Ship2Speed > 0 && player1 == true) {
			p1Ship2Speed = p1Ship2Speed - 100;
			if(e.getX() < 100) {
				p1Ship2X = 25;
				if(e.getY() < 100) {
					p1Ship2Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship2Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship2Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship2Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship2Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship2Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 100 && e.getX() < 200) {
				p1Ship2X = 125;
				if(e.getY() < 100) {
					p1Ship2Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship2Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship2Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship2Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship2Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship2Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 200 && e.getX() < 300) {
				p1Ship2X = 225;
				if(e.getY() < 100) {
					p1Ship2Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship2Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship2Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship2Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship2Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship2Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 300 && e.getX() < 400) {
				p1Ship2X = 325;
				if(e.getY() < 100) {
					p1Ship2Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship2Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship2Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship2Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship2Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship2Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 400 && e.getX() < 500) {
				p1Ship2X = 425;
				if(e.getY() < 100) {
					p1Ship2Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship2Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship2Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship2Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship2Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship2Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 500 && e.getX() < 600) {
				p1Ship2X = 525;
				if(e.getY() < 100) {
					p1Ship2Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship2Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship2Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship2Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship2Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship2Y = 525;
					repaint();
				}
			}
		}
		}
		
		//Player 1 moves ship 3
		if(p1ActShip == 3) {
		if(((Math.abs(e.getX() - p1Ship3X)) + (Math.abs(e.getY() - p1Ship3Y))) <= 200 && p1Ship3Speed > 0 && player1 == true) {
			p1Ship3Speed = p1Ship3Speed - 100;
			if(e.getX() < 100) {
				p1Ship3X = 25;
				if(e.getY() < 100) {
					p1Ship3Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship3Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship3Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship3Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship3Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship3Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 100 && e.getX() < 200) {
				p1Ship3X = 125;
				if(e.getY() < 100) {
					p1Ship3Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship3Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship3Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship3Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship3Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship3Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 200 && e.getX() < 300) {
				p1Ship3X = 225;
				if(e.getY() < 100) {
					p1Ship3Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship3Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship3Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship3Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship3Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship3Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 300 && e.getX() < 400) {
				p1Ship3X = 325;
				if(e.getY() < 100) {
					p1Ship3Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship3Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship3Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship3Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship3Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship3Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 400 && e.getX() < 500) {
				p1Ship3X = 425;
				if(e.getY() < 100) {
					p1Ship3Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship3Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship3Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship3Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship3Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship3Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 500 && e.getX() < 600) {
				p1Ship3X = 525;
				if(e.getY() < 100) {
					p1Ship3Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship3Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship3Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship3Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship3Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship3Y = 525;
					repaint();
				}
			}
		}
		}
		
		//Player 1 moves ship 4
		if(p1ActShip == 4) {
		if(((Math.abs(e.getX() - p1Ship4X)) + (Math.abs(e.getY() - p1Ship4Y))) <= 200 && p1Ship4Speed > 0 && player1 == true) {
			p1Ship4Speed = p1Ship4Speed - 100;
			if(e.getX() < 100) {
				p1Ship4X = 25;
				if(e.getY() < 100) {
					p1Ship4Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship4Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship4Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship4Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship4Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship4Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 100 && e.getX() < 200) {
				p1Ship4X = 125;
				if(e.getY() < 100) {
					p1Ship4Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship4Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship4Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship4Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship4Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship4Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 200 && e.getX() < 300) {
				p1Ship4X = 225;
				if(e.getY() < 100) {
					p1Ship4Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship4Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship4Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship4Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship4Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship4Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 300 && e.getX() < 400) {
				p1Ship4X = 325;
				if(e.getY() < 100) {
					p1Ship4Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship4Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship4Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship4Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship4Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship4Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 400 && e.getX() < 500) {
				p1Ship4X = 425;
				if(e.getY() < 100) {
					p1Ship4Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship4Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship4Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship4Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship4Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship4Y = 525;
					repaint();
				}
			}
			
			if(e.getX() > 500 && e.getX() < 600) {
				p1Ship4X = 525;
				if(e.getY() < 100) {
					p1Ship4Y = 25;
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					p1Ship4Y = 125;
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					p1Ship4Y = 225;
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					p1Ship4Y = 325;
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					p1Ship4Y = 425;
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					p1Ship4Y = 525;
					repaint();
				}
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
		//int x = 100;
		
		if(customizeScreenP1 == true && startGame == false) 
		{
			if (startCustomize == false)
			{
				if(key == KeyEvent.VK_1) 
				{
					p1Num = 1;
				}
				
				else if(key == KeyEvent.VK_2) 
				{
					p1Num = 2;
				}
				else if(key == KeyEvent.VK_3) 
				{
					p1Num = 3;
				}
				else if(key == KeyEvent.VK_4) 
				{
					p1Num = 4;
				}

				repaint();
			}
			
			//Customization Events
			//Player 1 health
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
				
				if (checkP1Ship[0] == true && key == KeyEvent.VK_H)
				{
					selection = 1;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostHealth(player1Ships.get(0));
						healthValP1[0]++;
						
						repaint();
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceHealth(player1Ships.get(0));
					}
				}
				
				else if (checkP1Ship[1] == true && key == KeyEvent.VK_H)
				{
					selection = 2;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostHealth(player1Ships.get(1));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceHealth(player1Ships.get(1));
					}
				}
				
				else if (checkP1Ship[2] == true && key == KeyEvent.VK_H)
				{
					selection = 3; 
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostHealth(player1Ships.get(2));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceHealth(player1Ships.get(2));
					}
				}
				
				else if (checkP1Ship[3] == true && key == KeyEvent.VK_H)
				{
					selection = 4;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostHealth(player1Ships.get(3));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceHealth(player1Ships.get(3));
					}
				}
				
				
				
				//Player 1 Attack
				if (checkP1Ship[0] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostAttack(player1Ships.get(0));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceAttack(player1Ships.get(0));
					}
				}
				
				else if (checkP1Ship[1] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostAttack(player1Ships.get(1));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceAttack(player1Ships.get(1));
					}
				}
				
				else if (checkP1Ship[2] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostAttack(player1Ships.get(2));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceAttack(player1Ships.get(2));
					}
				}
				
				else if (checkP1Ship[3] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P1boostAttack(player1Ships.get(3));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P1reduceAttack(player1Ships.get(3));
					}
				}
				
				
			}
		}
		
		if(customizeScreenP2 == true && startGame == false) {
			if (startCustomize == false) 
			{
				if(key == KeyEvent.VK_1) {
					p2Num = 1;
				}
				else if(key == KeyEvent.VK_2) {
					p2Num = 2;
				}
				else if(key == KeyEvent.VK_3) {
					p2Num = 3;
				}
				else if(key == KeyEvent.VK_4) {
					p2Num = 4;
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
				
				
				//Player 2 Health
				if (checkP2Ship[0] == true && key == KeyEvent.VK_H)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostHealth(player2Ships.get(0));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceHealth(player2Ships.get(0));
					}
				}
				
				else if (checkP2Ship[1] == true && key == KeyEvent.VK_H)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostHealth(player2Ships.get(1));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceHealth(player2Ships.get(1));
					}
				}
				
				else if (checkP2Ship[2] == true && key == KeyEvent.VK_H)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostHealth(player2Ships.get(2));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceHealth(player2Ships.get(2));
					}
				}
				
				else if (checkP2Ship[3] == true && key == KeyEvent.VK_H)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostHealth(player2Ships.get(3));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceHealth(player2Ships.get(3));
					}
				}
				
				//Player 2 Attack				
				if (checkP2Ship[0] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostAttack(player2Ships.get(0));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceAttack(player2Ships.get(0));
					}
				}
				
				else if (checkP2Ship[1] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostAttack(player2Ships.get(1));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceAttack(player2Ships.get(1));
					}
				}
				
				else if (checkP2Ship[2] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostAttack(player2Ships.get(2));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceAttack(player2Ships.get(2));
					}
				}
				
				else if (checkP2Ship[3] == true && key == KeyEvent.VK_A)
				{
					//selection = true;
					
					if (key == KeyEvent.VK_UP)
					{
						custom.P2boostAttack(player2Ships.get(3));
					}
					
					else if (key == KeyEvent.VK_DOWN)
					{
						custom.P2reduceAttack(player2Ships.get(3));
					}
				}
			}
		}
				
		//Player 1
		if(key == KeyEvent.VK_1 && startGame == true && player1 == true) {
			p1ActShip = 1;
			repaint();
		}
		if(key == KeyEvent.VK_2 && startGame == true && player1 == true) {
			p1ActShip = 2;
			repaint();
		}
		if(key == KeyEvent.VK_3 && startGame == true && player1 == true) {
			p1ActShip = 3;
			repaint();
		}
		if(key == KeyEvent.VK_4 && startGame == true && player1 == true) {
			p1ActShip = 4;
			repaint();
		}
		
		//Player 2
		if(key == KeyEvent.VK_1 && startGame == true && player1 == false) {
			p2ActShip = 1;
			repaint();
		}
		if(key == KeyEvent.VK_2 && startGame == true && player1 == false) {
			p2ActShip = 2;
			repaint();
		}
		if(key == KeyEvent.VK_3 && startGame == true && player1 == false) {
			p2ActShip = 3;
			repaint();
		}
		if(key == KeyEvent.VK_4 && startGame == true && player1 == false) {
			p2ActShip = 4;
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
		
		if(key == KeyEvent.VK_SPACE) {
			if(player1 == true) {
				player1 = false;
				p1ActShip = 0;
				p1Ship1Speed = 200;
				p1Ship2Speed = 200;
				p1Ship3Speed = 200;
				p1Ship4Speed = 200;
			}
			else {
				player1 = true;
				p2ActShip = 0;
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
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("Player 1: Customize Your Ships" , 75, 50);
			
			g.setFont(new Font(null, Font.PLAIN, 20));
			g.drawString("Points: " + points, 25, 580);
			g.drawString("Number of Ships: " + p1Num, 325, 580);
			
			if (p1Num == 1)
			{
				g.drawImage(shipSprite, 265, 100, 100, 100, this);
			}
			
			else if (p1Num == 2)
			{
				g.drawImage(shipSprite, 265, 100, 100, 100, this);
				g.drawImage(shipSprite, 165, 250, 100, 100, this);
			}
			
			else if (p1Num == 3)
			{
				g.drawImage(shipSprite, 265, 100, 100, 100, this);
				g.drawImage(shipSprite, 165, 250, 100, 100, this);
				g.drawImage(shipSprite, 365, 250, 100, 100, this);
			}
			
			else if (p1Num == 4)
			{
				g.drawImage(shipSprite, 265, 100, 100, 100, this);
				g.drawImage(shipSprite, 165, 250, 100, 100, this);
				g.drawImage(shipSprite, 365, 250, 100, 100, this);
				g.drawImage(shipSprite, 265, 400, 100, 100, this);
			}
			
			if (startCustomize == true)
			{
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 10, 100);
				g.drawString("H to boost health", 15, 115);
				g.drawString("A to boost attack", 15, 130);
				g.drawString("S to boost speed", 15, 145);
				g.drawString("D to boost defense", 15, 160);
				g.drawString("R to boost range", 15, 175);
				g.drawString("Up arrow to Upgrade", 15, 190);
				g.drawString("Down arrow to Downgrade", 15, 205);
				
				if (checkP1Ship[0] == true && selection == 1 && p1Num > 0)
				{
					g.drawOval(260, 100, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 400, 100);
					g.drawString("Health: "+healthValP1[0], 415, 115);
					g.drawString("Attack: "+attackValP1[0], 415, 130);
					g.drawString("Speed: "+speedValP1[0], 415, 145);
					g.drawString("Defense: "+shieldValP1[0], 415, 160);
					g.drawString("Range: "+rangeValP1[0], 415, 175);
				}
			
				else if (checkP1Ship[1] == true && selection == 2 && p1Num > 1)
				{
					g.drawOval(160, 250, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 60, 300);
					g.drawString("Health: "+healthValP1[1], 75, 315);
					g.drawString("Attack: "+attackValP1[1], 75, 330);
					g.drawString("Speed: "+speedValP1[1], 75, 345);
					g.drawString("Defense: "+shieldValP1[1], 75, 360);
					g.drawString("Range: "+rangeValP1[1], 75, 375);
				}
				
				if (checkP1Ship[2] == true && selection == 3 && p1Num > 2)
				{
					g.drawOval(360, 250, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 490, 300);
					g.drawString("Health: "+healthValP1[2], 505, 315);
					g.drawString("Attack: "+attackValP1[2], 505, 330);
					g.drawString("Speed: "+speedValP1[2], 505, 345);
					g.drawString("Defense: "+shieldValP1[2], 505, 360);
					g.drawString("Range: "+rangeValP1[2], 505, 375);
				}
				
				if (checkP1Ship[3] == true && selection == 4 && p1Num > 3)
				{
					g.drawOval(260, 400, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 385, 440);
					g.drawString("Health: "+healthValP1[3], 400, 455);
					g.drawString("Attack: "+attackValP1[3], 400, 470);
					g.drawString("Speed: "+speedValP1[3], 400, 485);
					g.drawString("Defense: "+shieldValP1[3], 400, 500);
					g.drawString("Range: "+rangeValP1[3], 400, 515);
				}
			}
			
		}
		
		else if(customizeScreenP2 == true && startGame == false) {
			//background image
			g.drawImage(space, 0, 0, 800, 800, this);
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, Font.BOLD, 30));
			g.drawString("Player 2: Customize Your Ships" , 75, 50);
			
			g.setFont(new Font(null, Font.PLAIN, 20));
			g.drawString("Points: " + points, 25, 580);
			g.drawString("Number of Ships: " + p2Num, 325, 580);

			if (p2Num == 1)
			{
				g.drawImage(shipSprite2, 265, 100, 100, 100, this);
			}
			
			else if (p2Num == 2)
			{
				g.drawImage(shipSprite2, 265, 100, 100, 100, this);
				g.drawImage(shipSprite2, 165, 250, 100, 100, this);
			}
			
			else if (p2Num == 3)
			{
				g.drawImage(shipSprite2, 265, 100, 100, 100, this);
				g.drawImage(shipSprite2, 165, 250, 100, 100, this);
				g.drawImage(shipSprite2, 365, 250, 100, 100, this);
			}
			
			else if (p2Num == 4)
			{
				g.drawImage(shipSprite2, 265, 100, 100, 100, this);
				g.drawImage(shipSprite2, 165, 250, 100, 100, this);
				g.drawImage(shipSprite2, 365, 250, 100, 100, this);
				g.drawImage(shipSprite2, 265, 400, 100, 100, this);
			}
			
			if (startCustomize == true)
			{
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 10, 100);
				g.drawString("H to boost health", 15, 115);
				g.drawString("A to boost attack", 15, 130);
				g.drawString("S to boost speed", 15, 145);
				g.drawString("D to boost defense", 15, 160);
				g.drawString("R to boost range", 15, 175);
				g.drawString("Up arrow to Upgrade", 15, 190);
				g.drawString("Down arrow to Downgrade", 15, 205);
				
				if (checkP2Ship[0] == true && selection == 1 && p2Num > 0)
				{
					g.drawOval(260, 100, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 400, 100);
					g.drawString("Health: "+healthValP2[0], 415, 115);
					g.drawString("Attack: "+attackValP2[0], 415, 130);
					g.drawString("Speed: "+speedValP2[0], 415, 145);
					g.drawString("Defense: "+shieldValP2[0], 415, 160);
					g.drawString("Range: "+rangeValP2[0], 415, 175);
				}
				
				else if (checkP2Ship[1] == true && selection == 2 && p2Num > 1)
				{
					g.drawOval(160, 250, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 60, 300);
					g.drawString("Health: "+healthValP2[1], 75, 315);
					g.drawString("Attack: "+attackValP2[1], 75, 330);
					g.drawString("Speed: "+speedValP2[1], 75, 345);
					g.drawString("Defense: "+shieldValP2[1], 75, 360);
					g.drawString("Range: "+rangeValP2[1], 75, 375);
				}
				
				if (checkP2Ship[2] == true && selection == 3 && p2Num > 2)
				{
					g.drawOval(360, 250, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 490, 300);
					g.drawString("Health: "+healthValP2[2], 505, 315);
					g.drawString("Attack: "+attackValP2[2], 505, 330);
					g.drawString("Speed: "+speedValP2[2], 505, 345);
					g.drawString("Defense: "+shieldValP2[2], 505, 360);
					g.drawString("Range: "+rangeValP2[2], 505, 375);
				}
				
				if (checkP2Ship[3] == true && selection == 4 && p2Num > 3)
				{
					g.drawOval(260, 400, 110, 110);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Stats:", 385, 440);
					g.drawString("Health: "+healthValP2[3], 400, 455);
					g.drawString("Attack: "+attackValP2[3], 400, 470);
					g.drawString("Speed: "+speedValP2[3], 400, 485);
					g.drawString("Defense: "+shieldValP2[3], 400, 500);
					g.drawString("Range: "+rangeValP2[3], 400, 515);
				}
			}
		}
		
		else if (customizeScreenP2 == false && startGame == false) {
			//g.setColor(Color.BLACK);
			//g.fillRect(0, 0, 600, 600);
			g.drawImage(space, 0, 0, 800, 800, this);
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
			g.drawImage(space, 0, 0, 800, 800, this);
			
			if(p1Destroyed == false && p2Destroyed == false) {
				g.setColor(Color.WHITE);
				//Vertical Lines
				g.drawLine(100, 0, 100, 600);
				g.drawLine(200, 0, 200, 600);
				g.drawLine(300, 0, 300, 600);
				g.drawLine(400, 0, 400, 600);
				g.drawLine(500, 0, 500, 600);
				g.drawLine(600, 0, 600, 600);
				
				//Horizontal Lines
				g.drawLine(0, 100, 600, 100);
				g.drawLine(0, 200, 600, 200);
				g.drawLine(0, 300, 600, 300);
				g.drawLine(0, 400, 600, 400);
				g.drawLine(0, 500, 600, 500);
				g.drawLine(0, 600, 600, 600);

			}
			g.setFont(new Font(null, Font.BOLD, 15));
			g.setColor(Color.BLACK);
			g.fillRect(601, 0, 800, 800);
					if(p1Destroyed == false) {
						if(p1Targeted == false) {
							
							if(p1Num == 1) {
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
							}
							
						}
					}
			
					if(p2Destroyed == false) {
						if(p2Targeted == false) {
							
							if(p2Num == 1) {
								if(p2ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship1X, p2Ship1Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p2Ship1X), (p2Ship1Y));
								g.drawImage(shipSprite2, p2Ship1X, p2Ship1Y, 50, 50, this);
								
								if(player1 == false) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 2 SHIPS", 650, 75);
									g.drawString("1", 675, 300);
									g.drawImage(shipSprite2, 675, 300, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 350, 50, 5);
								}
							}
							
							if(p2Num == 2) {
								if(p2ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship1X, p2Ship1Y, 50, 50);
								}
								if(p2ActShip == 2) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship2X, p2Ship2Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p2Ship1X), (p2Ship1Y));
								g.drawString("2", (p2Ship2X), (p2Ship2Y));
								g.drawImage(shipSprite2, p2Ship1X, p2Ship1Y, 50, 50, this);
								g.drawImage(shipSprite2, p2Ship2X, p2Ship2Y, 50, 50, this);
								
								if(player1 == false) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 2 SHIPS", 650, 75);
									g.drawString("1", 675, 250);
									g.drawString("2", 675, 350);
									g.drawImage(shipSprite2, 675, 250, 50, 50, this);
									g.drawImage(shipSprite2, 675, 350, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 300, 50, 5);
									g.fillRect(675, 400, 50, 5);
								}
							}
							
							if(p2Num == 3) {
								if(p2ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship1X, p2Ship1Y, 50, 50);
								}
								if(p2ActShip == 2) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship2X, p2Ship2Y, 50, 50);
								}
								if(p2ActShip == 3) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship3X, p2Ship3Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p2Ship1X), (p2Ship1Y));
								g.drawString("2", (p2Ship2X), (p2Ship2Y));
								g.drawString("3", (p2Ship3X), (p2Ship3Y));
								g.drawImage(shipSprite2, p2Ship1X, p2Ship1Y, 50, 50, this);
								g.drawImage(shipSprite2, p2Ship2X, p2Ship2Y, 50, 50, this);
								g.drawImage(shipSprite2, p2Ship3X, p2Ship3Y, 50, 50, this);
								
								if(player1 == false) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 2 SHIPS", 650, 75);
									g.drawString("1", 675, 200);
									g.drawString("2", 675, 300);
									g.drawString("3", 675, 400);
									g.drawImage(shipSprite2, 675, 200, 50, 50, this);
									g.drawImage(shipSprite2, 675, 300, 50, 50, this);
									g.drawImage(shipSprite2, 675, 400, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 250, 50, 5);
									g.fillRect(675, 350, 50, 5);
									g.fillRect(675, 450, 50, 5);
								}
							}
							
							if(p2Num == 4) {
								if(p2ActShip == 1) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship1X, p2Ship1Y, 50, 50);
								}
								if(p2ActShip == 2) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship2X, p2Ship2Y, 50, 50);
								}
								if(p2ActShip == 3) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship3X, p2Ship3Y, 50, 50);
								}
								if(p2ActShip == 4) {
									g.setColor(Color.GREEN);
									g.fillRect(p2Ship4X, p2Ship4Y, 50, 50);
								}
								g.setColor(Color.WHITE);
								g.drawString("1", (p2Ship1X), (p2Ship1Y));
								g.drawString("2", (p2Ship2X), (p2Ship2Y));
								g.drawString("3", (p2Ship3X), (p2Ship3Y));
								g.drawString("4", (p2Ship4X), (p2Ship4Y));
								g.drawImage(shipSprite2, p2Ship1X, p2Ship1Y, 50, 50, this);
								g.drawImage(shipSprite2, p2Ship2X, p2Ship2Y, 50, 50, this);
								g.drawImage(shipSprite2, p2Ship3X, p2Ship3Y, 50, 50, this);
								g.drawImage(shipSprite2, p2Ship4X, p2Ship4Y, 50, 50, this);
								
								if(player1 == false) {
									g.setFont(new Font(null, Font.BOLD, 15));
									g.drawString("PLAYER 2 SHIPS", 650, 75);
									g.drawString("1", 675, 150);
									g.drawString("2", 675, 250);
									g.drawString("3", 675, 350);
									g.drawString("4", 675, 450);
									g.drawImage(shipSprite2, 675, 150, 50, 50, this);
									g.drawImage(shipSprite2, 675, 250, 50, 50, this);
									g.drawImage(shipSprite2, 675, 350, 50, 50, this);
									g.drawImage(shipSprite2, 675, 450, 50, 50, this);
									g.setColor(Color.GREEN);
									g.fillRect(675, 200, 50, 5);
									g.fillRect(675, 300, 50, 5);
									g.fillRect(675, 400, 50, 5);
									g.fillRect(675, 500, 50, 5);
								}
							}	
						}
					}
			/*
				else {
					g.setColor(Color.YELLOW);
					g.drawRect(redShipX, redShipY, 50, 50);
					g.drawImage(shipSprite2, redShipX, redShipY, 50, 50, this);
				}
			}*/
			
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
