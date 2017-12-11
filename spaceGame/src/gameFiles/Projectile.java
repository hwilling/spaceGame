package gameFiles;

public class Projectile {
	ProjectileType dmgType;
	int dmg;
	
	public Projectile(ProjectileType type, int dmg){
		dmgType = type;
		this.dmg = dmg;
	}
	
	public int getDmg(){
		return dmg;
	}
	public ProjectileType getdmgType(){
		return dmgType;
	}
	
	public void setProjType(ProjectileType type){
		dmgType = type;
	}
}