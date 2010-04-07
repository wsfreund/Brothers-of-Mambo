import java.awt.Point;


public class PointDamage extends Point{
	public int damage = -1;
	PointDamage(int x, int y, int damage) {
		super(x,y);
	}
	public int getDamage() {
		return damage;
	}
}
