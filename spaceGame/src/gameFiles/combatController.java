package gameFiles;

import java.util.ArrayList;
import java.util.Random;

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
	public ArrayList<ship> getPlayer1Ships(){
		return player1Ships;
	}
	public ArrayList<ship> getPlayer2Ships(){
		return player2Ships;
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
	public void shoot(int attackerIndex, int targetIndex){
		ship attacker;
		ship target;
		if(players[0]){
			attacker = player1Ships.get(attackerIndex);
			target = player2Ships.get(targetIndex);
		}
		else{
			attacker = player2Ships.get(attackerIndex);
			target = player1Ships.get(targetIndex);
		}
		int attackx = attacker.getXCoord();
		int attacky = attacker.getYCoord();
		int targetx = target.getXCoord();
		int targety = target.getYCoord();
		int distToTarget = getDist(attackx, attacky, targetx, targety);
		
		if(distToTarget <= attacker.getRange() && hit(attacker, target)){
			
			target.modHP(-1 * attacker.getDamageValue());
			//TODO destroy ship
			if(target.getHP() <= 0){
				if(players[0]){
					player2Ships.remove(targetIndex);
				}
				else{
					player1Ships.remove(targetIndex);
				}
			}
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
			//System.out.println(players[i]);
			if(players[i] == true) {
				int next = i + 1;
				players[i] = false;
				if(next >= players.length) {
					next = 0;
				}
				players[next] = true;
				break;
			}
		}
	}
	
	public int whosTurn(){
		int playerUp = 99;
		for(int i = 0; i< players.length; i++){
			if(players[i]){
				return i;
			}
		}
		return playerUp;
		
	}
	//ends game if only one player has ships in the array
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
		//player 2 wins if all of player 1's ships are destroyed
		if(player1Ships.isEmpty()){
			return 2;
		}
		//player 1 wins if all of player 2's ships are destroyed
		else if(player2Ships.isEmpty()){
			return 1;
		}
		//game is still going on 
		else{
			return 0;
		}
	}
	
	public boolean hit(ship attacker, ship target){
		//get distance between each ship
		int distToTarget = getDist(attacker.getXCoord(), attacker.getYCoord(), target.getXCoord(), target.getYCoord());
		int rangeDif = attacker.getRange() - distToTarget;
		int targetSpeed = target.getSpeed();
		Random generator = new Random();
		int rand = generator.nextInt(99) + 1;
		//chances based on situation of distance and target speed
		int missChance = targetSpeed * 5;
		int hitChance = 50 + (rangeDif * 10);
		hitChance -= missChance;
		//results
		if(hitChance >= rand){
			return true;
		}
		return false;
	}
	
	public void damageCalc(ship attacker, ship target){
		ProjectileType attackProjType = attacker.getShipAttackType();
		int damageOverflow = 0;
		//determine what to do with damage based on projectile type
		switch(attackProjType){
			case LASER:
				damageOverflow = target.modShield(-1 * attacker.getDamageValue());
				damageOverflow += target.getArmor();
				if(damageOverflow < 0){
					target.modHP(damageOverflow);
				}
			case KINETIC:
				damageOverflow = target.modShield(-1 * attacker.getDamageValue());
				damageOverflow += target.getArmor();
				if(damageOverflow < 0){
					target.modHP(damageOverflow);
				}
			case MISSLE:
				damageOverflow = target.modShield(-1 * attacker.getDamageValue());
				damageOverflow += target.getArmor();
				if(damageOverflow < 0){
					target.modHP(damageOverflow);
				}
			case ION:
				damageOverflow = target.modShield(-1 * attacker.getDamageValue());
				damageOverflow += target.getArmor();
				if(damageOverflow < 0){
					target.modHP(damageOverflow);
				}
		}
	}
}