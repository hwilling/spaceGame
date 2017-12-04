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
	int points = 500;
	
	//select ships or customization
	boolean p1_s1 = false;
	boolean p1_s2 = false;
	boolean p1_s3 = false;
	boolean p1_s4 = false;
	boolean p2_s1 = false;
	boolean p2_s2 = false;
	boolean p2_s3 = false;
	boolean p2_s4 = false;
	
	int shieldVal = 0;
	int attackVal = 0;
	int rangeVal = 0;
	int speedVal = 0;
	int healthVal = 0;
	
	//for extra menu (up or down grade)
	boolean selection = false;
	
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
		if (customizeScreenP1 == true)
		{
			if (p1Num > 0 && e.getX() > 265 && e.getX() < 365 && e.getY() > 100 && e.getY() < 200)
			{
				p1_s1 = true;
			}
			
			else if (p1Num > 1 && e.getX() > 165 && e.getX() < 265 && e.getY() > 250 && e.getY() < 350)
			{
				p1_s2 = true;
			}
			
			else if (p1Num > 2 && e.getX() > 365 && e.getX() < 465 && e.getY() > 250 && e.getY() < 350)
			{
				p1_s3 = true;
			}
			else if (p1Num > 3 && e.getX() > 365 && e.getX() < 465 && e.getY() > 400 && e.getY() < 500)
			{
				p1_s4 = true;
			}
		}
		
		else if (customizeScreenP2 == true)
		{
			if (p2Num > 0 && e.getX() > 265 && e.getX() < 365 && e.getY() > 100 && e.getY() < 200)
			{
				p2_s1 = true;
			}
			
			else if (p2Num > 1 && e.getX() > 165 && e.getX() < 265 && e.getY() > 250 && e.getY() < 350)
			{
				p2_s2 = true;
			}
			
			else if (p2Num > 2 && e.getX() > 365 && e.getX() < 465 && e.getY() > 250 && e.getY() < 350)
			{
				p2_s3 = true;
			}
			
			else if (p2Num > 3 && e.getX() > 365 && e.getX() < 465 && e.getY() > 400 && e.getY() < 500)
			{
				p2_s4 = true;
			}
		}
		
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
		
		//Player 1 Ships to move
		if(((Math.abs(e.getX() - (combat.getPlayer1Ships().get(p1ActShip).getXCoord()))) + (Math.abs(e.getY() - (combat.getPlayer1Ships().get(p1ActShip).getYCoord())))) <= 200 && speed > 0 && player1 == true) {
			if(e.getX() < 100) {
				combat.getPlayer1Ships().get(p1ActShip).setXCoord(25);
				if(e.getY() < 100) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 100 && e.getX() < 200) {
				combat.getPlayer1Ships().get(p1ActShip).setXCoord(125);
				if(e.getY() < 100) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 200 && e.getX() < 300) {
				combat.getPlayer1Ships().get(p1ActShip).setXCoord(225);
				if(e.getY() < 100) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 300 && e.getX() < 400) {
				combat.getPlayer1Ships().get(p1ActShip).setXCoord(325);
				if(e.getY() < 100) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 400 && e.getX() < 500) {
				combat.getPlayer1Ships().get(p1ActShip).setXCoord(425);
				if(e.getY() < 100) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(525);
					repaint();
				}
			}
			if(e.getX() > 500 && e.getX() < 600) {
				combat.getPlayer1Ships().get(p1ActShip).setXCoord(525);
				if(e.getY() < 100) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(25);
					repaint();
				}
				if(e.getY() > 100 && e.getY() < 200) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(125);
					repaint();
				}
				if(e.getY() > 200 && e.getY() < 300) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(225);
					repaint();
				}
				if(e.getY() > 300 && e.getY() < 400) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(325);
					repaint();
				}
				if(e.getY() > 400 && e.getY() < 500) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(425);
					repaint();
				}
				if(e.getY() > 500 && e.getY() < 600) {
					speed = speed - 100;
					combat.getPlayer1Ships().get(p1ActShip).setYCoord(525);
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
		int x = 100;
		
		if(customizeScreenP1 == true && startGame == false) 
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
			
			//Customization Events
			//Player 1 health
			if (p1_s1 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostHealth(player1Ships.get(0));
					shieldVal++;
					
					repaint();
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceHealth(player1Ships.get(0));
				}
			}
			
			else if (p1_s2 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostHealth(player1Ships.get(1));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceHealth(player1Ships.get(1));
				}
			}
			
			else if (p1_s3 == true && key == KeyEvent.VK_H)
			{
				selection = true; 
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostHealth(player1Ships.get(2));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceHealth(player1Ships.get(2));
				}
			}
			
			else if (p1_s4 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostHealth(player1Ships.get(3));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceHealth(player1Ships.get(3));
				}
			}
			
			//Player 2 Health
			if (p2_s1 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostHealth(player2Ships.get(0));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceHealth(player2Ships.get(0));
				}
			}
			
			else if (p2_s2 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostHealth(player2Ships.get(1));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceHealth(player2Ships.get(1));
				}
			}
			
			else if (p2_s3 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostHealth(player2Ships.get(2));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceHealth(player2Ships.get(2));
				}
			}
			
			else if (p2_s4 == true && key == KeyEvent.VK_H)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostHealth(player2Ships.get(3));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceHealth(player2Ships.get(3));
				}
			}
			
			//Player 1 Attack
			if (p1_s1 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostAttack(player1Ships.get(0));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceAttack(player1Ships.get(0));
				}
			}
			
			else if (p1_s2 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostAttack(player1Ships.get(1));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceAttack(player1Ships.get(1));
				}
			}
			
			else if (p1_s3 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostAttack(player1Ships.get(2));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceAttack(player1Ships.get(2));
				}
			}
			
			else if (p1_s4 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P1boostAttack(player1Ships.get(3));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P1reduceAttack(player1Ships.get(3));
				}
			}
			
			//Player 2 Attack
			
			if (p2_s1 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostAttack(player2Ships.get(0));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceAttack(player2Ships.get(0));
				}
			}
			
			else if (p2_s2 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostAttack(player2Ships.get(1));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceAttack(player2Ships.get(1));
				}
			}
			
			else if (p2_s3 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
				if (key == KeyEvent.VK_UP)
				{
					custom.P2boostAttack(player2Ships.get(2));
				}
				
				else if (key == KeyEvent.VK_DOWN)
				{
					custom.P2reduceAttack(player2Ships.get(2));
				}
			}
			
			else if (p2_s4 == true && key == KeyEvent.VK_A)
			{
				selection = true;
				
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
		
		if(customizeScreenP2 == true && startGame == false) {
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
			
			if (p1_s1 == true && p1Num > 0)
			{
				if (shieldVal == 1)
				{
					g.drawImage(shipSpriteS1, 265, 100, 100, 100, this);
				}
				
				g.drawOval(260, 100, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 100, 100);
				g.drawString("H to boost health", 115, 115);
				g.drawString("A to boost attack", 115, 130);
				g.drawString("S to boost speed", 115, 145);
				g.drawString("D to boost defense", 115, 160);
				g.drawString("R to boost range", 115, 175);
				
				if (selection == true)
				{
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Up arrow to Upgrade", 400, 100);
					g.drawString("Down arrow to Downgrade", 400, 115);
				}
			}
			
			else if (p1_s2 == true && p1Num > 1)
			{
				g.drawOval(160, 250, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 10, 300);
				g.drawString("H to boost health", 25, 315);
				g.drawString("A to boost attack", 25, 330);
				g.drawString("S to boost speed", 25, 345);
				g.drawString("D to boost defense", 25, 360);
				g.drawString("R to boost range", 25, 375);
				
				if (selection == true)
				{
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Up arrow to Upgrade", 25, 425);
					g.drawString("Down arrow to Downgrade", 25, 440);
				}
			}
			
			else if (p1_s3 == true && p1Num > 2)
			{
				g.drawOval(360, 250, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 410, 150);
				g.drawString("H to boost health", 425, 165);
				g.drawString("A to boost attack", 425, 180);
				g.drawString("S to boost speed", 425, 195);
				g.drawString("D to boost defense", 425, 210);
				g.drawString("R to boost range", 425, 225);
				
				if (selection == true)
				{
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Up arrow to Upgrade", 400, 400);
					g.drawString("Down arrow to Downgrade", 400, 415);
				}
			}
			
			else if (p1_s4 == true && p1Num > 3)
			{
				g.drawOval(360, 400, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 410, 250);
				g.drawString("H to boost health", 425, 165);
				g.drawString("A to boost attack", 425, 180);
				g.drawString("S to boost speed", 425, 195);
				g.drawString("D to boost defense", 425, 210);
				g.drawString("R to boost range", 425, 225);
			}
		}
		
		else if(customizeScreenP2 == true && startGame == false) {
			//background image
			g.drawImage(space, 0, 0, 600, 600, this);
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
			
			if (p2_s1 == true && p2Num > 0)
			{
				g.drawOval(260, 100, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 100, 100);
				g.drawString("H to boost health", 115, 115);
				g.drawString("A to boost attack", 115, 130);
				g.drawString("S to boost speed", 115, 145);
				g.drawString("D to boost defense", 115, 160);
				g.drawString("R to boost range", 115, 175);
				
				if (selection == true)
				{
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Up arrow to Upgrade", 400, 100);
					g.drawString("Down arrow to Downgrade", 400, 115);
				}
			}
			
			else if (p2_s2 == true && p2Num > 1)
			{
				g.drawOval(160, 250, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 10, 300);
				g.drawString("H to boost health", 25, 315);
				g.drawString("A to boost attack", 25, 330);
				g.drawString("S to boost speed", 25, 345);
				g.drawString("D to boost defense", 25, 360);
				g.drawString("R to boost range", 25, 375);
				
				if (selection == true)
				{
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Up arrow to Upgrade", 25, 425);
					g.drawString("Down arrow to Downgrade", 25, 440);
				}
			}
			
			else if (p2_s3 == true && p2Num > 2)
			{
				g.drawOval(360, 250, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 410, 150);
				g.drawString("H to boost health", 425, 165);
				g.drawString("A to boost attack", 425, 180);
				g.drawString("S to boost speed", 425, 195);
				g.drawString("D to boost defense", 425, 210);
				g.drawString("R to boost range", 425, 225);
				
				if (selection == true)
				{
					g.setFont(new Font(null, Font.PLAIN, 15));
					g.drawString("Up arrow to Upgrade", 400, 400);
					g.drawString("Down arrow to Downgrade", 400, 415);
				}
			}
			
			else if (p2_s4 == true && p2Num > 3)
			{
				g.drawOval(260, 400, 110, 110);
				g.setFont(new Font(null, Font.PLAIN, 15));
				g.drawString("Upgrades:", 410, 250);
				g.drawString("H to boost health", 425, 165);
				g.drawString("A to boost attack", 425, 180);
				g.drawString("S to boost speed", 425, 195);
				g.drawString("D to boost defense", 425, 210);
				g.drawString("R to boost range", 425, 225);
			}
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
					//if(p1ActShip == 0) {
						for(int i = 0; i < p1Num; i++) {
							combat.getPlayer1Ships().add(i, Ship);
							combat.getPlayer1Ships().get(i).setYCoord(greenShipY);
							if(p1Num == 1) {
								combat.getPlayer1Ships().get(i).setXCoord(greenShipX);
							}
							if(p1Num == 2) {
								combat.getPlayer1Ships().get(i).setXCoord(greenShipX - (100 * i));
							}
							if(p1Num == 3) {
								combat.getPlayer1Ships().get(i).setXCoord(greenShipX - (100 * i));
							}
							if(p1Num == 4) {
								combat.getPlayer1Ships().get(i).setXCoord(greenShipX + 100 - (100 * i));
							}
							
							if(p1ActShip > 0) {
								g.setColor(Color.GREEN);
								g.fillRect(combat.getPlayer1Ships().get(p1ActShip).getXCoord(), combat.getPlayer1Ships().get(p1ActShip).getYCoord(), 50, 50);
							}
							g.drawImage(shipSprite, combat.getPlayer1Ships().get(i).getXCoord(), combat.getPlayer1Ships().get(i).getYCoord(), 50, 50, this);
							
							//if(p1ActShip > 0) {
								//g.setColor(Color.GREEN);
								//g.fillRect(combat.getPlayer1Ships().get(p1ActShip).getXCoord(), combat.getPlayer1Ships().get(p1ActShip).getYCoord(), 50, 50);
							//}
							//for(int j = 0; j < p1Num; j++) {
									//combat.getPlayer1Ships().get(i);
									//combat.getPlayer1Ships().get(i).setYCoord(combat.getPlayer1Ships().get(i).getYCoord());
									//combat.getPlayer1Ships().get(i).setXCoord(combat.getPlayer1Ships().get(i).getXCoord());
									//g.drawImage(shipSprite, combat.getPlayer1Ships().get(j).getXCoord(), combat.getPlayer1Ships().get(j).getYCoord(), 50, 50, this);
							//}
						}
					//}
					
						//p1Num = 0;
						/*if(p1ActShip == 0) {
							g.drawImage(shipSprite, combat.getPlayer1Ships().get(p1ActShip).getXCoord(), combat.getPlayer1Ships().get(p1ActShip).getYCoord(), 50, 50, this);
							for(int i = 1; i < combat.getPlayer1Ships().size(); i++) {
								g.drawImage(shipSprite, combat.getPlayer1Ships().get(i).getXCoord(), combat.getPlayer1Ships().get(i).getYCoord(), 50, 50, this);

							}
						}*/

				}
				/*else {
					g.setColor(Color.YELLOW);
					g.drawRect(greenShipX, greenShipY, 50, 50);
					g.drawImage(shipSprite, greenShipX, greenShipY, 50, 50, this);
				}*/
			}
			
			if(p2Destroyed == false) {
				if(p2Targeted == false) {
					for(int i = 0; i < p2Num; i++) {
						combat.getPlayer2Ships().add(i, Ship);
						combat.getPlayer2Ships().get(i).setYCoord(redShipY);
						if(p2Num == 1) {
							combat.getPlayer2Ships().get(i).setXCoord(redShipX);
						}
						if(p2Num == 2) {
							combat.getPlayer2Ships().get(i).setXCoord(redShipX - (100 * i));
						}
						if(p2Num == 3) {
							combat.getPlayer2Ships().get(i).setXCoord(redShipX - (100 * i));
						}
						if(p2Num == 4) {
							combat.getPlayer2Ships().get(i).setXCoord(redShipX + 100 - (100 * i));
						}
						g.drawImage(shipSprite2, combat.getPlayer2Ships().get(i).getXCoord(), combat.getPlayer2Ships().get(i).getYCoord(), 50, 50, this);
					}
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
