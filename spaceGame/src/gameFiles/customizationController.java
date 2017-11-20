package gameFiles;

public class customizationController 
{
	private boolean player1;
	private boolean player2;
	private ship[] player1Ships;
	private ship[] player2Ships;
	
	//Possible upgrades: health, defense, attack, speed, range
	//points is the total "currency" that can be spent upgrading the ships
	private int health, defense, attack, speed, range, points;
	
	public customizationController(boolean player1, boolean player2, ship[] player1Ships, ship[] Player2Ships)
	{
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int P1boostHealth(ship player1Ship)
	{
		//check if player can afford upgrade
		health = player1Ship.getMaxHP();
		if(points >= calcBoostCost(health)){
			points -= calcBoostCost(health);
			health++;
			player1Ship.setMaxHP(health);
		}
		return health;
	}
	
	public int P1reduceHealth(ship player1Ship){
		health = player1Ship.getMaxHP();
		points += calcReduceRefund(health);
		health--;
		player1Ship.setMaxHP(health);
		return health;
	}
	
	public int P1boostAttack(ship player1Ship)
	{
		attack = player1Ship.getDamageValue();
		attack++;
	
		return attack;
	}
	
	public int P1boostSpeed(ship player1Ship)
	{
		speed = player1Ship.getSpeed();
		speed++;
	
		return speed;
	}
	
	public int P1boostRange(ship player1Ship)
	{
		range = player1Ship.getRange();
		range++;

		return range;
	}	
	
	public int P2boostHealth(ship player2Ship)
	{
		health = player2Ship.getMaxHP();
		health++;

		return health;
	}
	
	public int P2boostAttack(ship player2Ship)
	{
		attack = player2Ship.getDamageValue();
		attack++;

		return attack;
	}
	
	public int P2boostSpeed(ship player2Ship)
	{
		speed = player2Ship.getSpeed();
		speed++;
			
		return speed;
	}
	
	public int P2boostRange(ship player2Ship)
	{
		range = player2Ship.getRange();
		range++;

		return range;
	}
	//how many points it will cost to upgrade a stat
	public int calcBoostCost(int boostVal){
		int cost = boostVal * 10;
		return cost;
	}
	//how many points a player will receive when downgrading a stat
	public int calcReduceRefund(int reduceVal){
		//minus one so the price of the refund is equal to the amount the player paid to get to this point
		int refund = (reduceVal -1) * 10;
		return refund;
	}
}
