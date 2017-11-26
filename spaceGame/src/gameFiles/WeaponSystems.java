package gameFiles;

public class WeaponSystems {
	private int dmg, range;
	
	public WeaponSystems(int damage, int fireRange){
		dmg = damage;
		range = fireRange;
	}
	
	public int getDmg(){
		return dmg;
	}
	
	public void setDmg(int dmg){
		this.dmg = dmg;
	}
	
	public int getRange(){
		return range;
	}
	
	public void setRange(int range){
		this.range = range;
	}
}
