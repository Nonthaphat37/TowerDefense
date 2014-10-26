package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class BulletFire extends Bullet {

	private int BULLET_SIZE = 5;

	public BulletFire(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 100, 0));
		g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}

}
