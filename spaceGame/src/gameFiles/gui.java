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
	
	int p1Speed1 = 0;
	int p1Speed2 = 0;
	int p1Speed3 = 0;
	int p1Speed4 = 0;
	
	int p2Speed1 = 0;
	int p2Speed2 = 0;
	int p2Speed3 = 0;
	int p2Speed4 = 0;
	
	boolean startTurn = false;
	
	boolean p1Target1 = false;
	boolean p1Target2 = false;
	boolean p1Target3 = false;
	boolean p1Target4 = false;
	
	boolean p2Target1 = false;
	boolean p2Target2 = false;
	boolean p2Target3 = false;
	boolean p2Target4 = false;
	
	boolean p1Destroy1 = false;
	boolean p1Destroy2 = false;
	boolean p1Destroy3 = false;
	boolean p1Destroy4 = false;
	
	boolean p2Destroy1 = false;
	boolean p2Destroy2 = false;
	boolean p2Destroy3 = false;
	boolean p2Destroy4 = false;
	
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
	private ship Ship2 = new ship(name);
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
		
	}
		
	// Event handler for mouse dragged events
	protected void handleMouseDragged(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model

	}

	// Event handler for mouse released events
	protected void handleMouseReleased(MouseEvent e) {
		// TODO: use controller to handle event and (if necessary) update model

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
						if (checkP1Ship[i] == true && key == KeyEvent.VK_H)
						{
							if (selection == 1 && healthValP1[0] < HP)
							{
								healthValP1[0]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 2 && healthValP1[1] < HP)
							{
								healthValP1[1]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 3 && healthValP1[2] < HP)
							{
								healthValP1[2]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 4 && healthValP1[3] < HP)
							{
								healthValP1[3]++;
								pointsP1 = pointsP1 - 50;
							}
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_A)
						{
							if (selection == 1 && attackValP1[0] < attack)
							{
								attackValP1[0]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 2 && attackValP1[1] < attack)
							{
								attackValP1[1]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 3 && attackValP1[2] < attack)
							{
								attackValP1[2]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 4 && attackValP1[3] < attack)
							{
								attackValP1[3]++;
								pointsP1 = pointsP1 - 50;
							}
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_S)
						{
							if (selection == 1 && speedValP1[0] < 4)
							{
								speedValP1[0]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 2 && speedValP1[1] < 4)
							{
								speedValP1[1]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 3 && speedValP1[2] < 4)
							{
								speedValP1[2]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 4 && speedValP1[3] < 4)
							{
								speedValP1[3]++;
								pointsP1 = pointsP1 - 50;
							}
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_R)
						{
							if (selection == 1 && rangeValP1[0] < 3)
							{
								rangeValP1[0]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 2 && rangeValP1[1] < 3)
							{
								rangeValP1[1]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 3 && rangeValP1[2] < 3)
							{
								rangeValP1[2]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 4 && rangeValP1[3] < 3)
							{
								rangeValP1[3]++;
								pointsP1 = pointsP1 - 50;
							}
						}
						
						else if (checkP1Ship[i] == true && key == KeyEvent.VK_D)
						{
							if (selection == 1 && shieldValP1[0] < shield)
							{
								shieldValP1[0]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 2 && shieldValP1[1] < shield)
							{
								shieldValP1[1]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 3 && shieldValP1[2] < shield)
							{
								shieldValP1[2]++;
								pointsP1 = pointsP1 - 50;
							}
							
							else if (selection == 4 && shieldValP1[3] < shield)
							{
								shieldValP1[3]++;
								pointsP1 = pointsP1 - 50;
							}
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
				player1Ships.get(i).setHP(player1Ships.get(i).getMaxHP());
			}
		
			
			for(int i = 0; i < p1Num; i++) {
				if(i == 0) {
					p1Speed1 = player1Ships.get(i).getSpeed();
				}
				if(i == 1) {
					p1Speed2 = player1Ships.get(i).getSpeed();
				}
				if(i == 2) {
					p1Speed3 = player1Ships.get(i).getSpeed();
				}
				if(i == 3) {
					p1Speed4 = player1Ships.get(i).getSpeed();
				}
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
						if (checkP2Ship[i] == true && key == KeyEvent.VK_H)
						{
							if (selection == 1 && healthValP2[0] < HP)
							{
								healthValP2[0]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 2 && healthValP2[1] < HP)
							{
								healthValP2[1]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 3 && healthValP2[2] < HP)
							{
								healthValP2[2]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 4 && healthValP2[3] < HP)
							{
								healthValP2[3]++;
								pointsP2 = pointsP2 - 50;
							}
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_A)
						{
							if (selection == 1 && attackValP2[0] < attack)
							{
								attackValP2[0]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 2 && attackValP2[1] < attack)
							{
								attackValP2[1]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 3 && attackValP2[2] < attack)
							{
								attackValP2[2]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 4 && attackValP2[3] < attack)
							{
								attackValP2[3]++;
								pointsP2 = pointsP2 - 50;
							}
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_S)
						{
							if (selection == 1 && speedValP2[0] < 4)
							{
								speedValP2[0]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 2 && speedValP2[1] < 4)
							{
								speedValP2[1]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 3 && speedValP2[2] < 4)
							{
								speedValP2[2]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 4 && speedValP2[3] < 4)
							{
								speedValP2[3]++;
								pointsP2 = pointsP2 - 50;
							}
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_R)
						{
							if (selection == 1 && rangeValP2[0] < 3)
							{
								rangeValP2[0]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 2 && rangeValP2[1] < 3)
							{
								rangeValP2[1]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 3 && rangeValP2[2] < 3)
							{
								rangeValP2[2]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 4 && rangeValP2[3] < 3)
							{
								rangeValP2[3]++;
								pointsP2 = pointsP2 - 50;
							}
						}
						
						if (checkP2Ship[i] == true && key == KeyEvent.VK_D)
						{
							if (selection == 1 && shieldValP2[0] < shield)
							{
								shieldValP2[0]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 2 && shieldValP2[1] < shield)
							{
								shieldValP2[1]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 3 && shieldValP2[2] < shield)
							{
								shieldValP2[2]++;
								pointsP2 = pointsP2 - 50;
							}
							
							else if (selection == 4 && shieldValP2[3] < shield)
							{
								shieldValP2[3]++;
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
				player2Ships.get(i).setHP(player2Ships.get(i).getMaxHP());
			}
		}
			
			for(int i = 0; i < p2Num; i++) {
				if(i == 0) {
					p2Speed1 = player2Ships.get(i).getSpeed();
				}
				if(i == 1) {
					p2Speed2 = player2Ships.get(i).getSpeed();
				}
				if(i == 2) {
					p2Speed3 = player2Ships.get(i).getSpeed();
				}
				if(i == 3) {
					p2Speed4 = player2Ships.get(i).getSpeed();
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
		
	//	f
		if(key == KeyEvent.VK_T && startGame == true) {
			
			//Targeting a ship
			if(p1Destroyed == false && p2Destroyed == false) {
				if(player1 == true) {
					for(int i = 0; i < p2Num; i++) {
						if(Math.abs((player1Ships.get(p1ActShip-1).getXCoord()) - player2Ships.get(i).getXCoord()) <= 100 && 
								Math.abs((player1Ships.get(p1ActShip-1).getYCoord()) - player2Ships.get(i).getYCoord()) <= 100){
					
							if(i == 0) {
								p2Target1 = true;
							}
							if(i == 1) {
								p2Target2 = true;
							}
							if(i == 2) {
								p2Target3 = true;
							}
							if(i == 3) {
								p2Target4 = true;
							}
							repaint();
						}
					}
				}
			
				if(player1 == false) {
					for(int i = 0; i < p1Num; i++) {
						if(Math.abs((player2Ships.get(p2ActShip-1).getXCoord()) - player1Ships.get(i).getXCoord()) <= 100 && 
								Math.abs((player2Ships.get(p2ActShip-1).getYCoord()) - player1Ships.get(i).getYCoord()) <= 100){
					
							if(i == 0) {
								p1Target1 = true;
							}
							if(i == 1) {
								p1Target2 = true;
							}
							if(i == 2) {
								p1Target3 = true;
							}
							if(i == 3) {
								p1Target4 = true;
							}
							repaint();
						}
					}
				}
			}
		}
			
		if(key == KeyEvent.VK_F && startGame == true) {
			
			if(player1 == true) {
				if(p2Target1 == true) {
					player2Ships.get(0).setHP(
							player2Ships.get(0).getHP() - (player1Ships.get(p1ActShip-1).getDamageValue() % player2Ships.get(0).getShield() + 4));
					repaint();
					if(player2Ships.get(0).getHP() <= 0) {
						p2Destroy1 = true;
						player2Ships.remove(0);
						p2Num = p2Num - 1;
					}
					p2Target1 = false;
				}
				if(p2Target2 == true) {
					player2Ships.get(1).setHP(
							player2Ships.get(1).getHP() - (player1Ships.get(p1ActShip-1).getDamageValue() % player2Ships.get(1).getShield() + 4));
					repaint();
					if(player2Ships.get(1).getHP() <= 0) {
						p2Destroy2 = true;
						player2Ships.remove(1);
						p2Num = p2Num - 1;
					}
					p2Target2 = false;
				}
				if(p2Target3 == true) {
					player2Ships.get(2).setHP(
							player2Ships.get(2).getHP() - (player1Ships.get(p1ActShip-1).getDamageValue() % player2Ships.get(2).getShield() + 4));
					repaint();
					if(player2Ships.get(2).getHP() <= 0) {
						p2Destroy3 = true;
						player2Ships.remove(2);
						p2Num = p2Num - 1;
					}
					p2Target3 = false;
				}
				if(p2Target4 == true) {
					player2Ships.get(3).setHP(
							player2Ships.get(3).getHP() - (player1Ships.get(p1ActShip-1).getDamageValue() % player2Ships.get(3).getShield() + 4));
					repaint();
					if(player2Ships.get(3).getHP() <= 0) {
						p2Destroy4 = true;
						player2Ships.remove(3);
						p2Num = p2Num - 1;
					}
					p2Target4 = false;
				}
			}
			if(player1 == false) {
				if(p1Target1 == true) {
					p1Destroy1 = true;
					player1Ships.remove(0);
					p1Num = p1Num - 1;
					p1Target1 = false;
				}
				if(p1Target2 == true) {
					p1Destroy2 = true;
					player1Ships.remove(1);
					p1Num = p1Num - 1;
					p1Target2 = false;
				}
				if(p1Target3 == true) {
					p1Destroy3 = true;
					player1Ships.remove(2);
					p1Num = p1Num - 1;
					p1Target3 = false;
				}
				if(p1Target4 == true) {
					p1Destroy4 = true;
					player1Ships.remove(3);
					p1Num = p1Num - 1;
					p1Target4 = false;
				}
				repaint();
			}
			if(player1Ships.isEmpty() || p1Num == 0) {
				p1Destroyed = true;
				repaint();
			}
			if(player2Ships.isEmpty() || p2Num == 0) {
				p2Destroyed = true;
				repaint();
			}
		}
		if(key == KeyEvent.VK_ENTER && startGame == true) {
			if(startTurn == false) {
				startTurn = true;
			}
			repaint();
		}
		
		if(key == KeyEvent.VK_SPACE) {
			if(player1 == true) {
				player1 = false;
				p1ActShip = 0;
				for(int i = 0; i < p1Num; i++) {
					if(i == 0) {
						player1Ships.get(i).setSpeed(p1Speed1);
					}
					if(i == 1) {
						player1Ships.get(i).setSpeed(p1Speed2);
					}
					if(i == 2) {
						player1Ships.get(i).setSpeed(p1Speed3);
					}
					if(i == 3) {
						player1Ships.get(i).setSpeed(p1Speed4);
					}
				}
			}
			else {
				player1 = true;
				p2ActShip = 0;
				for(int i = 0; i < p2Num; i++) {
					if(i == 0) {
						player2Ships.get(i).setSpeed(p2Speed1);
					}
					if(i == 1) {
						player2Ships.get(i).setSpeed(p2Speed2);
					}
					if(i == 2) {
						player2Ships.get(i).setSpeed(p2Speed3);
					}
					if(i == 3) {
						player2Ships.get(i).setSpeed(p2Speed4);
					}
				}
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
		
if(startGame == true) {
			
			//Move Player 1 Ships
			if(player1 == true) {
				if(key == KeyEvent.VK_DOWN && player1Ships.get(p1ActShip-1).getSpeed() > 0) {
					if(player1Ships.get(p1ActShip-1).getYCoord() + 100 <= 525) {
						player1Ships.get(p1ActShip-1).setYCoord(player1Ships.get(p1ActShip-1).getYCoord() + 100);
						player1Ships.get(p1ActShip-1).setSpeed(player1Ships.get(p1ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
				if(key == KeyEvent.VK_UP && player1Ships.get(p1ActShip-1).getSpeed() > 0) {
					if(player1Ships.get(p1ActShip-1).getYCoord() - 100 >= 25) {
						player1Ships.get(p1ActShip-1).setYCoord(player1Ships.get(p1ActShip-1).getYCoord() - 100);
						player1Ships.get(p1ActShip-1).setSpeed(player1Ships.get(p1ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
				if(key == KeyEvent.VK_LEFT && player1Ships.get(p1ActShip-1).getSpeed() > 0) {
					if(player1Ships.get(p1ActShip-1).getXCoord() - 100 >= 25) {
						player1Ships.get(p1ActShip-1).setXCoord(player1Ships.get(p1ActShip-1).getXCoord() - 100);
						player1Ships.get(p1ActShip-1).setSpeed(player1Ships.get(p1ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
				if(key == KeyEvent.VK_RIGHT && player1Ships.get(p1ActShip-1).getSpeed() > 0) {
					if(player1Ships.get(p1ActShip-1).getXCoord() + 100 <= 525) {
						player1Ships.get(p1ActShip-1).setXCoord(player1Ships.get(p1ActShip-1).getXCoord() + 100);
						player1Ships.get(p1ActShip-1).setSpeed(player1Ships.get(p1ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
			}
			
			//Move Player 2 Ships
			if(player1 == false) {
				if(key == KeyEvent.VK_DOWN && player2Ships.get(p2ActShip-1).getSpeed() > 0) {
					if(player2Ships.get(p2ActShip-1).getYCoord() + 100 <= 525) {
						player2Ships.get(p2ActShip-1).setYCoord(player2Ships.get(p2ActShip-1).getYCoord() + 100);
						player2Ships.get(p2ActShip-1).setSpeed(player2Ships.get(p2ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
				if(key == KeyEvent.VK_UP && player2Ships.get(p2ActShip-1).getSpeed() > 0) {
					if(player2Ships.get(p2ActShip-1).getYCoord() - 100 >= 25) {
						player2Ships.get(p2ActShip-1).setYCoord(player2Ships.get(p2ActShip-1).getYCoord() - 100);
						player2Ships.get(p2ActShip-1).setSpeed(player2Ships.get(p2ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
				if(key == KeyEvent.VK_LEFT && player2Ships.get(p2ActShip-1).getSpeed() > 0) {
					if(player2Ships.get(p2ActShip-1).getXCoord() - 100 >= 25) {
						player2Ships.get(p2ActShip-1).setXCoord(player2Ships.get(p2ActShip-1).getXCoord() - 100);
						player2Ships.get(p2ActShip-1).setSpeed(player2Ships.get(p2ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
				if(key == KeyEvent.VK_RIGHT && player2Ships.get(p2ActShip-1).getSpeed() > 0) {
					if(player2Ships.get(p2ActShip-1).getXCoord() + 100 <= 525) {
						player2Ships.get(p2ActShip-1).setXCoord(player2Ships.get(p2ActShip-1).getXCoord() + 100);
						player2Ships.get(p2ActShip-1).setSpeed(player2Ships.get(p2ActShip-1).getSpeed() - 1);
						repaint();
					}
				}
			}
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
			g.drawString("Controls: Arrows to Move Ship" , 270, 460);
			g.drawString("Spacebar to Change Players" , 355, 485);
			g.drawString("F to fire" , 355, 510);
		}
		
		else {
			// TODO: render the model
			//background
			g.drawImage(space, 0, 0, 800, 800, this);
			
			if(startTurn == false) {
				g.setColor(Color.WHITE);
				g.setFont(new Font(null, Font.BOLD, 30));
				g.drawString("Press Enter to Start Game" , 100, 300);
			}
			
			if (player1 == true)
			{
				g.setColor(Color.WHITE);
				g.setFont(new Font(null, Font.PLAIN, 40));
				g.drawString("Player 1", 635, 700);
				g.drawString("Turn", 660, 750);
				
				g.setColor(Color.GREEN);
				g.setFont(new Font(null, Font.PLAIN, 20));
				g.drawString("Player 1 Stats", 225, 640);
				
				if (p1Num > 0)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 1 Stats", 20, 680);
					g.drawString("Health: " + player1Ships.get(0).getHP(), 35-10, 680+15);
					g.drawString("Attack: " + player1Ships.get(0).getDamageValue(), 35-10, 680+30);
					g.drawString("Defense: " + player1Ships.get(0).getShield(), 35-10, 680+45);
					g.drawString("Speed: " + player1Ships.get(0).getSpeed(), 35-10, 680+60);
					g.drawString("Range: " + player1Ships.get(0).getRange(), 35-10, 680+75);
				}
				
				if (p1Num > 1)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 2 Stats", 175, 680);
					g.drawString("Health: " + player1Ships.get(1).getHP(), 200-10, 680+15);
					g.drawString("Attack: " + player1Ships.get(1).getDamageValue(), 200-10, 680+30);
					g.drawString("Defense: " + player1Ships.get(1).getShield(), 200-10, 680+45);
					g.drawString("Speed: " + player1Ships.get(1).getSpeed(), 200-10, 680+60);
					g.drawString("Range: " + player1Ships.get(1).getRange(), 200-10, 680+75);
				}
				
				if (p1Num > 2)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 3 Stats", 330, 680);
					g.drawString("Health: " + player1Ships.get(2).getHP(), 365-10, 680+15);
					g.drawString("Attack: " + player1Ships.get(2).getDamageValue(), 365-10, 680+30);
					g.drawString("Defense: " + player1Ships.get(2).getShield(), 365-10, 680+45);
					g.drawString("Speed: " + player1Ships.get(2).getSpeed(), 365-10, 680+60);
					g.drawString("Range: " + player1Ships.get(2).getRange(), 365-10, 680+75);
				}
				
				if (p1Num > 3)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 4 Stats", 495, 680);
					g.drawString("Health: " + player1Ships.get(3).getHP(), 530-10, 680+15);
					g.drawString("Attack: " + player1Ships.get(3).getDamageValue(), 530-10, 680+30);
					g.drawString("Defense: " + player1Ships.get(3).getShield(), 530-10, 680+45);
					g.drawString("Speed: " + player1Ships.get(3).getSpeed(), 530-10, 680+60);
					g.drawString("Range: " + player1Ships.get(3).getRange(), 530-10, 680+75);
				}
				
				g.setColor(Color.RED);
				g.setFont(new Font(null, Font.PLAIN, 20));
				g.drawString("Player 2 Stats", 635, 60);
				
				if (p2Num > 0)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 1 Stats", 625+20, 30+80);
					g.drawString("Health: " + player2Ships.get(0).getHP(), 650+20, 45+80);
					g.drawString("Attack: " + player2Ships.get(0).getDamageValue(), 650+20, 60+80);
					g.drawString("Defense: " + player2Ships.get(0).getShield(), 650+20, 75+80);
					g.drawString("Speed: " + player2Ships.get(0).getSpeed(), 650+20, 90+80);
					g.drawString("Range: " + player2Ships.get(0).getRange(), 650+20, 105+80);
				}
				
				if (p2Num > 1)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 2 Stats", 625+20, 150+80);
					g.drawString("Health: " + player2Ships.get(1).getHP(), 650+20, 165+80);
					g.drawString("Attack: " + player2Ships.get(1).getDamageValue(), 650+20, 180+80);
					g.drawString("Defense: " + player2Ships.get(1).getShield(), 650+20, 195+80);
					g.drawString("Speed: " + player2Ships.get(1).getSpeed(), 650+20, 210+80);
					g.drawString("Range: " + player2Ships.get(1).getRange(), 650+20, 225+80);
				}
				
				if (p2Num > 2)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 3 Stats", 625+20, 270+80);
					g.drawString("Health: " + player2Ships.get(2).getHP(), 650+20, 285+80);
					g.drawString("Attack: " + player2Ships.get(2).getDamageValue(), 650+20, 300+80);
					g.drawString("Defense: " + player2Ships.get(2).getShield(), 650+20, 315+80);
					g.drawString("Speed: " + player2Ships.get(2).getSpeed(), 650+20, 330+80);
					g.drawString("Range: " + player2Ships.get(2).getRange(), 650+20, 345+80);
				}
				
				if (p2Num > 3)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 4 Stats", 625+20, 385+80);
					g.drawString("Health: " + player2Ships.get(3).getHP(), 650+20, 400+80);
					g.drawString("Attack: " + player2Ships.get(3).getDamageValue(), 650+20, 415+80);
					g.drawString("Defense: " + player2Ships.get(3).getShield(), 650+20, 430+80);
					g.drawString("Speed: " + player2Ships.get(3).getSpeed(), 650+20, 445+80);
					g.drawString("Range: " + player2Ships.get(3).getRange(), 650+20, 460+80);
				}
			}
			
			if (player1 == false)
			{
				g.setColor(Color.WHITE);
				g.setFont(new Font(null, Font.PLAIN, 40));
				g.drawString("Player 2", 635, 700);
				g.drawString("Turn", 660, 750);
				
				g.setColor(Color.GREEN);
				g.setFont(new Font(null, Font.PLAIN, 20));
				g.drawString("Player 1 Stats", 635, 60);
				
				if (p1Num > 0)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 1 Stats", 625+20, 30+80);
					g.drawString("Health: " + player1Ships.get(0).getHP(), 650+20, 45+80);
					g.drawString("Attack: " + player1Ships.get(0).getDamageValue(), 650+20, 60+80);
					g.drawString("Defense: " + player1Ships.get(0).getShield(), 650+20, 75+80);
					g.drawString("Speed: " + player1Ships.get(0).getSpeed(), 650+20, 90+80);
					g.drawString("Range: " + player1Ships.get(0).getRange(), 650+20, 105+80);
				}
				
				if (p1Num > 1)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 2 Stats", 625+20, 150+80);
					g.drawString("Health: " + player1Ships.get(1).getHP(), 650+20, 165+80);
					g.drawString("Attack: " + player1Ships.get(1).getDamageValue(), 650+20, 180+80);
					g.drawString("Defense: " + player1Ships.get(1).getShield(), 650+20, 195+80);
					g.drawString("Speed: " + player1Ships.get(1).getSpeed(), 650+20, 210+80);
					g.drawString("Range: " + player1Ships.get(1).getRange(), 650+20, 225+80);
				}
				
				if (p1Num > 2)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 3 Stats", 625+20, 270+80);
					g.drawString("Health: " + player1Ships.get(2).getHP(), 650+20, 285+80);
					g.drawString("Attack: " + player1Ships.get(2).getDamageValue(), 650+20, 300+80);
					g.drawString("Defense: " + player1Ships.get(2).getShield(), 650+20, 315+80);
					g.drawString("Speed: " + player1Ships.get(2).getSpeed(), 650+20, 330+80);
					g.drawString("Range: " + player1Ships.get(2).getRange(), 650+20, 345+80);
				}
				
				if (p1Num > 3)
				{
					g.setColor(Color.GREEN);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 4 Stats", 625+20, 385+80);
					g.drawString("Health: " + player1Ships.get(3).getHP(), 650+20, 400+80);
					g.drawString("Attack: " + player1Ships.get(3).getDamageValue(), 650+20, 415+80);
					g.drawString("Defense: " + player1Ships.get(3).getShield(), 650+20, 430+80);
					g.drawString("Speed: " + player1Ships.get(3).getSpeed(), 650+20, 445+80);
					g.drawString("Range: " + player1Ships.get(3).getRange(), 650+20, 460+80);
				}
				
				g.setColor(Color.RED);
				g.setFont(new Font(null, Font.PLAIN, 20));
				g.drawString("Player 2 Stats", 225, 640);
				
				if (p2Num > 0)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 1 Stats", 20, 680);
					g.drawString("Health: " + player2Ships.get(0).getHP(), 35-10, 680+15);
					g.drawString("Attack: " + player2Ships.get(0).getDamageValue(), 35-10, 680+30);
					g.drawString("Defense: " + player2Ships.get(0).getShield(), 35-10, 680+45);
					g.drawString("Speed: " + player2Ships.get(0).getSpeed(), 35-10, 680+60);
					g.drawString("Range: " + player2Ships.get(0).getRange(), 35-10, 680+75);
				}
				
				if (p2Num > 1)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 2 Stats", 175, 680);
					g.drawString("Health: " + player2Ships.get(1).getHP(), 200-10, 680+15);
					g.drawString("Attack: " + player2Ships.get(1).getDamageValue(), 200-10, 680+30);
					g.drawString("Defense: " + player2Ships.get(1).getShield(), 200-10, 680+45);
					g.drawString("Speed: " + player2Ships.get(1).getSpeed(), 200-10, 680+60);
					g.drawString("Range: " + player2Ships.get(1).getRange(), 200-10, 680+75);
				}
				
				if (p2Num > 2)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 3 Stats", 330, 680);
					g.drawString("Health: " + player2Ships.get(2).getHP(), 365-10, 680+15);
					g.drawString("Attack: " + player2Ships.get(2).getDamageValue(), 365-10, 680+30);
					g.drawString("Defense: " + player2Ships.get(2).getShield(), 365-10, 680+45);
					g.drawString("Speed: " + player2Ships.get(2).getSpeed(), 365-10, 680+60);
					g.drawString("Range: " + player2Ships.get(2).getRange(), 365-10, 680+75);
				}
				
				if (p2Num > 3)
				{
					g.setColor(Color.RED);
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Ship 4 Stats", 495, 680);
					g.drawString("Health: " + player2Ships.get(3).getHP(), 530-10, 680+15);
					g.drawString("Attack: " + player2Ships.get(3).getDamageValue(), 530-10, 680+30);
					g.drawString("Defense: " + player2Ships.get(3).getShield(), 530-10, 680+45);
					g.drawString("Speed: " + player2Ships.get(3).getSpeed(), 530-10, 680+60);
					g.drawString("Range: " + player2Ships.get(3).getRange(), 530-10, 680+75);
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
								if(p1Num == 0) {
									p1Destroyed = true;
									repaint();
								}
								
								if(p1ActShip > 0) {
									g.setColor(Color.GREEN);
							 		g.drawRect(player1Ships.get(p1ActShip - 1).getXCoord(), player1Ships.get(p1ActShip - 1).getYCoord(), 50, 50);
								}
								g.setColor(Color.WHITE);
								g.setFont(new Font(null, Font.BOLD, 15));
								g.drawString(""+(i+1)+"", player1Ships.get(i).getXCoord(), player1Ships.get(i).getYCoord());
								g.drawImage(shipSprite, player1Ships.get(i).getXCoord(), player1Ships.get(i).getYCoord(), 50, 50, this);
							}
						}
					}
						
							
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
								
								if(p2Num == 0) {
									p2Destroyed = true;
									repaint();
								}
								
								if(p2Target1 == true) {
									g.setColor(Color.RED);
									g.drawRect(player2Ships.get(0).getXCoord(), player2Ships.get(0).getYCoord(), 50, 50);
								}
								if(p2Target2 == true) {
									g.setColor(Color.RED);
									g.drawRect(player2Ships.get(1).getXCoord(), player2Ships.get(1).getYCoord(), 50, 50);
								}
								if(p2Target3 == true) {
									g.setColor(Color.RED);
									g.drawRect(player2Ships.get(2).getXCoord(), player2Ships.get(2).getYCoord(), 50, 50);
								}
								if(p2Target4 == true) {
									g.setColor(Color.RED);
									g.drawRect(player2Ships.get(3).getXCoord(), player2Ships.get(3).getYCoord(), 50, 50);
								}
								
								if(p2ActShip > 0) {
									g.setColor(Color.GREEN);
							 		g.drawRect(player2Ships.get(p2ActShip - 1).getXCoord(), player2Ships.get(p2ActShip - 1).getYCoord(), 50, 50);
								}
								g.setColor(Color.WHITE);
								g.setFont(new Font(null, Font.BOLD, 15));
								g.drawString(""+(i+1)+"", player2Ships.get(i).getXCoord(), player2Ships.get(i).getYCoord());
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
				g.drawImage(space, 0, 0, 800, 800, this);
				g.setColor(Color.WHITE);
				g.setFont(new Font(null, Font.BOLD, 50));
				g.drawString("GAME OVER" , 250, 400);
				g.setFont(new Font(null, Font.BOLD, 25));
				g.drawString("Player " + player + " Won!", 325, 450);
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
