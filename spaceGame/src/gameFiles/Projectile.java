package gameFiles;

public class Projectile {
	String dmgType;
	int dmg;
	
	public Projectile(String type, int dmg){
		dmgType = type;
		this.dmg = dmg;
	}
	
	int getDmg(){
		return dmg;
	}
	String getdmgType(){
		return dmgType;
	}
}