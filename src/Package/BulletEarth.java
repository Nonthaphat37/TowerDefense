package Package;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;



public class BulletEarth extends Bullet {
	
	private int BULLET_SIZE = 5;
	
	public BulletEarth(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(50, 255, 100));
		 g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}

	
}
