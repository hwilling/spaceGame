package gameFiles;

public class combatController {
	boolean players[];
	//ship[] player1Ships, player2Ships;
	combatController(int numPlayers, ship[] player1Ships, ship[] player2Ships){
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
	public void moveShip(){
		
	}
}