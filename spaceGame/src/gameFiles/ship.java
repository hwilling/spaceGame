package gameFiles;

public class ship {
	
	String name;
	int hpMax, hpCurrent, speed, movesRemaining, currentActions, maxActions, dmg;
	
	ship(String name){
		this.name = name;
	}
	
	int getHP(){
		return hpCurrent;
	}
	void modHP(int modAmount){
		hpCurrent += modAmount;
		//make sure hp does not go over max amount
		if(hpCurrent > hpMax){
			hpCurrent = hpMax;
		}
		//TODO function to destroy ship if hp is less than 0 (switch this to controller?)
	}
	int getMovesRemaining(){
		return movesRemaining;
	}
	void modMovesRemaining(int modAmount){
		movesRemaining += modAmount;
	}
	int getCurrentActions(){
		return currentActions;
	}
	void modCurrentActions(int modAmount){
		currentActions += modAmount;
	}
	void turnReset(){
		movesRemaining = speed;
		currentActions = maxActions;
	}
	int getDamageValue(){
		return dmg;
	}
}
