package gameFiles;

public class customizationController 
{
	private boolean player1;
	private boolean player2;
	private ship[] player1Ships;
	private ship[] player2Ships;
	
	//Possible upgrades: health, defense, attack, speed, range
	//points is the total "currency" that can be spent upgrading the ships
	private int health, shield, recharge, actions, defense, attack, speed, range = 0;
	private int points = 500;
	
	public customizationController(boolean player1, boolean player2, ship[] player1Ships, ship[] Player2Ships)
	{
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void changeProjType(ship selected){
		ProjectileType proj = selected.getWeapons().getProjectile().getdmgType();
		switch(proj){
			case LASER:
				proj = ProjectileType.KINETIC;
			case KINETIC:
				proj = ProjectileType.MISSLE;
			case MISSLE:
				proj = ProjectileType.ION;
			case ION:
				proj = ProjectileType.LASER;
		}
		selected.getWeapons().getProjectile().setProjType(proj);
	}
	
	/*public int boostHealth(ship modShip){
		int hp = modShip.getMaxHP();
		int cost = calcBoostCost(hp);
		if(points >= cost){
			points -= cost;
			
		}
	}*/
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
	
	public int P1boostShield(ship player1Ship)
	{
		//check if player can afford upgrade
		shield = player1Ship.getShieldGen().getShieldCharge();
		if(points >= calcBoostCost(shield)){
			points -= calcBoostCost(shield);
			shield++;
			player1Ship.getShieldGen().setShieldCharge(shield);
		}
		return shield;
	}
	
	public int P1reduceShield(ship player1Ship){
		shield = player1Ship.getShieldGen().getShieldCharge();
		points += calcReduceRefund(shield);
		shield--;
		player1Ship.getShieldGen().setShieldCharge(shield);;
		return health;
	}
	
	public int P1boostShieldRecharge(ship player1Ship)
	{
		//check if player can afford upgrade
		recharge = player1Ship.getShieldGen().getRechargeRate();
		if(points >= calcBoostCost(recharge)){
			points -= calcBoostCost(recharge);
			recharge++;
			player1Ship.getShieldGen().setRechargeRate(recharge);
		}
		return recharge;
	}
	
	public int P1reduceShieldRecharge(ship player1Ship){
		recharge = player1Ship.getShieldGen().getRechargeRate();
		points += calcReduceRefund(recharge);
		recharge--;
		player1Ship.getShieldGen().setRechargeRate(recharge);
		return recharge;
	}
	
	public int P1boostAction(ship player1Ship)
	{
		//check if player can afford upgrade
		actions = player1Ship.getReactor().getActions();
		if(points >= calcBoostCost(actions)){
			points -= calcBoostCost(actions);
			actions++;
			player1Ship.getReactor().setActions(actions);;
		}
		return actions;
	}
	
	public int P1reduceAction(ship player1Ship){
		actions = player1Ship.getReactor().getActions();
		points += calcReduceRefund(actions);
		actions--;
		player1Ship.getReactor().setActions(actions);;
		return actions;
	}
	
	public int P1boostAttack(ship player1Ship)
	{
		//check if player can afford upgrade
		attack = player1Ship.getDamageValue();
		if(points >= calcBoostCost(attack)){
			points -= calcBoostCost(attack);
			attack++;
			player1Ship.setDamageValue(attack);;
		}
		//attack = player1Ship.getDamageValue();
		//attack++;
	
		return attack;
	}
	
	public int P1reduceAttack(ship player1Ship){
		attack = player1Ship.getDamageValue();
		points += calcReduceRefund(attack);
		attack--;
		player1Ship.setDamageValue(attack);
		return attack;
	}
	
	public int P1boostSpeed(ship player1Ship)
	{
		speed = player1Ship.getSpeed();
		if(points >= calcBoostCost(speed)){
			points -= calcBoostCost(speed);
			speed++;
			player1Ship.setSpeed(speed);
		}
		//speed++;
	
		return speed;
	}
	
	public int P1reduceSpeed(ship player1Ship){
		speed = player1Ship.getSpeed();
		points += calcReduceRefund(speed);
		speed--;
		player1Ship.setSpeed(speed);
		return speed;
	}
	
	public int P1boostRange(ship player1Ship)
	{
		range = player1Ship.getRange();
		if(points >= calcBoostCost(range)){
			points -= calcBoostCost(range);
			range++;
			player1Ship.setRange(range);
		}
		//range++;

		return range;
	}
	
	public int P1reduceRange(ship player1Ship){
		range = player1Ship.getRange();
		points += calcReduceRefund(range);
		range--;
		player1Ship.setRange(range);
		return range;
	}
	
	public int P1boostArmor(ship player1Ship){
		defense = player1Ship.getArmor();
		if(points >= calcBoostCost(defense)){
			range -= calcBoostCost(defense);
			defense++;
			player1Ship.setArmor(defense);
		}
		return defense;
	}
	
	public int P1reduceArmor(ship player1Ship){
		defense = player1Ship.getArmor();
		points += calcReduceRefund(defense);
		defense--;
		player1Ship.setArmor(defense);
		return defense;
	}
	
	public int P2boostHealth(ship player2Ship)
	{
		//check if player can afford upgrade
		health = player2Ship.getMaxHP();
		if(points >= calcBoostCost(health)){
			points -= calcBoostCost(health);
			health++;
			player2Ship.setMaxHP(health);
		}
		return health;
	}
	
	public int P2reduceHealth(ship player2Ship){
		health = player2Ship.getMaxHP();
		points += calcReduceRefund(health);
		health--;
		player2Ship.setMaxHP(health);
		return health;
	}
	
	public int P2boostShield(ship player1Ship)
	{
		//check if player can afford upgrade
		shield = player1Ship.getShieldGen().getShieldCharge();
		if(points >= calcBoostCost(shield)){
			points -= calcBoostCost(shield);
			shield++;
			player1Ship.getShieldGen().setShieldCharge(shield);
		}
		return shield;
	}
	
	public int P2reduceShield(ship player1Ship){
		shield = player1Ship.getShieldGen().getShieldCharge();
		points += calcReduceRefund(shield);
		shield--;
		player1Ship.getShieldGen().setShieldCharge(shield);;
		return health;
	}
	
	public int P2boostShieldRecharge(ship player1Ship)
	{
		//check if player can afford upgrade
		recharge = player1Ship.getShieldGen().getRechargeRate();
		if(points >= calcBoostCost(recharge)){
			points -= calcBoostCost(recharge);
			recharge++;
			player1Ship.getShieldGen().setRechargeRate(recharge);
		}
		return recharge;
	}
	
	public int P2reduceShieldRecharge(ship player1Ship){
		recharge = player1Ship.getShieldGen().getRechargeRate();
		points += calcReduceRefund(recharge);
		recharge--;
		player1Ship.getShieldGen().setRechargeRate(recharge);
		return recharge;
	}
	
	public int P2boostAction(ship player1Ship)
	{
		//check if player can afford upgrade
		actions = player1Ship.getReactor().getActions();
		if(points >= calcBoostCost(actions)){
			points -= calcBoostCost(actions);
			actions++;
			player1Ship.getReactor().setActions(actions);;
		}
		return actions;
	}
	
	public int P2reduceAction(ship player1Ship){
		actions = player1Ship.getReactor().getActions();
		points += calcReduceRefund(actions);
		actions--;
		player1Ship.getReactor().setActions(actions);;
		return actions;
	}
	
	public int P2boostAttack(ship player2Ship)
	{
		//check if player can afford upgrade
		attack = player2Ship.getDamageValue();
		if(points >= calcBoostCost(attack)){
			points -= calcBoostCost(attack);
			attack++;
			player2Ship.setDamageValue(attack);;
		}
		//attack = player1Ship.getDamageValue();
		//attack++;
	
		return attack;
	}
	
	public int P2reduceAttack(ship player2Ship){
		attack = player2Ship.getDamageValue();
		points += calcReduceRefund(attack);
		attack--;
		player2Ship.setDamageValue(attack);
		return attack;
	}
	
	public int P2boostSpeed(ship player2Ship)
	{
		speed = player2Ship.getSpeed();
		if(points >= calcBoostCost(speed)){
			points -= calcBoostCost(speed);
			speed++;
			player2Ship.setSpeed(speed);
		}
		//speed++;
	
		return speed;
	}
	
	public int P2reduceSpeed(ship player2Ship){
		speed = player2Ship.getSpeed();
		points += calcReduceRefund(speed);
		speed--;
		player2Ship.setSpeed(speed);
		return speed;
	}
	
	public int P2boostRange(ship player2Ship)
	{
		range = player2Ship.getRange();
		if(points >= calcBoostCost(range)){
			points -= calcBoostCost(range);
			range++;
			player2Ship.setRange(range);
		}
		//range++;

		return range;
	}
	
	public int P2reduceRange(ship player2Ship){
		range = player2Ship.getRange();
		points += calcReduceRefund(range);
		range--;
		player2Ship.setRange(range);
		return range;
	}
	
	public int P2boostArmor(ship player2Ship){
		defense = player2Ship.getArmor();
		if(points >= calcBoostCost(defense)){
			points -= calcBoostCost(defense);
			defense++;
			player2Ship.setArmor(defense);
		}
		return defense;
	}
	
	public int P2reduceArmor(ship player2Ship){
		defense = player2Ship.getArmor();
		points += calcReduceRefund(defense);
		defense--;
		player2Ship.setArmor(defense);
		return defense;
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
