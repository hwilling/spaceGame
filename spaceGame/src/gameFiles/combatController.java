package gameFiles;


import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class combatController {
	private boolean players[];
	private ArrayList<ship> player1Ships, player2Ships;
	//ship[] player1Ships, player2Ships;
	public combatController(int numPlayers, ArrayList<ship> player1Ships, ArrayList<ship> player2Ships){
		players = new boolean[numPlayers];
		//player1Ships = new ship[player1Ships.length];
		//player2Ships = new ship[player2Ships.length];
		this.player1Ships = new ArrayList<ship>(player1Ships);
		this.player2Ships = new ArrayList<ship>(player2Ships);
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
	
	//used to switch player turn
	public void switchPlayer() {
		for(int i = 0; i < players.length; i++) {
			int next = i + 1;
			if(players[i] == true) {
				players[i] = false;
				if(next > players.length) {
					next = 0;
				}
				players[next] = true;
			}
		}
	}
	
	public boolean endGame(){
		int whoWinsVal = whoWins();
		if(whoWinsVal != 0){
			return true;
		}
		else{
			return false;
		}
	}
	
	public int whoWins(){
		if(player1Ships.isEmpty()){
			return 1;
		}
		else if(player2Ships.isEmpty()){
			return 2;
		}
		else{
			return 0;
		}
	}
	
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