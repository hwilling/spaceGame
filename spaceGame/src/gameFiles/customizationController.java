package gameFiles;

public class customizationController 
{
	boolean player1;
	boolean player2;
	ship[] player1Ships;
	ship[] player2Ships;
	
	//Possible upgrades: health, defense, attack, speed, range
	int health, defense, attack, speed, range;		
	
	public customizationController(boolean player1, boolean player2, ship[] player1Ships, ship[] Player2Ships)
	{
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public int P1boostHealth(ship player1Ship)
	{
		health = player1Ship.getMaxHP();
		health++;
	
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
}
