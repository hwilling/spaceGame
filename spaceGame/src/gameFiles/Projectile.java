package gameFiles;

public class Projectile {
	ProjectileType dmgType;
	int dmg;
	
	public Projectile(ProjectileType type, int dmg){
		dmgType = type;
		this.dmg = dmg;
	}
	
	int getDmg(){
		return dmg;
	}
	ProjectileType getdmgType(){
		return dmgType;
	}
}