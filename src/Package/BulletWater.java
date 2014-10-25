package Package;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;



public class BulletWater extends Bullet {
	
	private int BULLET_SIZE = 5;
	
	public BulletWater(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(0, 155, 255));
		 g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}
	
}
