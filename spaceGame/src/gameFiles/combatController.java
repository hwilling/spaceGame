package gameFiles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class combatController {
	private boolean players[];
	//ship[] player1Ships, player2Ships;
	public combatController(int numPlayers, ship[] player1Ships, ship[] player2Ships){
		players = new boolean[numPlayers];
		//player1Ships = new ship[player1Ships.length];
		//player2Ships = new ship[player2Ships.length];
		for(int i = 0; i < players.length; i++){
			if(i == 0){
				players[i] = true;
			}
			else{
				players[i] = false;
			}
		}
	}
	
	public void playGame(){
		boolean gameOver = false;
		//game
		while(!gameOver){
			//TODO add event listeners for the game
		}
	}
	
	//TODO functions for in game events
	public void moveShip(ship shipToMove, int dx, int dy){
		int shipx = shipToMove.getXCoord();
		int shipy = shipToMove.getYCoord();
		int movesUsed = getDist(shipx, shipy, dx, dy);
		if(shipToMove.getMovesRemaining() >= movesUsed){
			shipToMove.setXCoord(dx);
			shipToMove.setYCoord(dy);
			shipToMove.modMovesRemaining(-1 * movesUsed);
		}
	}
	public void shoot(ship attacker, ship target){
		int attackx = attacker.getXCoord();
		int attacky = attacker.getYCoord();
		int targetx = target.getXCoord();
		int targety = target.getYCoord();
		int distToTarget = getDist(attackx, attacky, targetx, targety);
		
		if(distToTarget <= attacker.getRange()){
			target.modHP(-1 * attacker.getDamageValue());
		}
	}
	//used to find distance between two points on the board
	public int getDist(int x, int y, int dx, int dy){
		int distToTarget = 0;
		double dist = Math.sqrt((Math.pow((dx - x), 2) + (Math.pow(dy - y, 2))));
		distToTarget = (int) Math.floor(dist);
		return distToTarget;
	}
	//
	
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
		
	protected void handleMousePressed(MouseEvent e) {
		
		//repaint();
	}
	
	// Event handler for mouse dragged events
	protected void handleMouseDragged(MouseEvent e) {
		
		//repaint();
	}

	// Event handler for mouse released events
	protected void handleMouseReleased(MouseEvent e) {
		
		//repaint();
	}
		

}