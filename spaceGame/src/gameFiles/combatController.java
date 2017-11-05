package gameFiles;

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
}