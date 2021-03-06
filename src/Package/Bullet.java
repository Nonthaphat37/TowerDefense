package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Bullet implements Entity {
	private int BULLET_SIZE = 5;
	protected float x;
	protected float y;
	private float mx;
	private float my;
	protected float bx;
	protected float by;
	public int numMon = -1;

	public int setNumMon(int numMon) {
		this.numMon = numMon;
		return numMon;
	}

	public int getNumMon() {
		return numMon;
	}

	public void setMonster(Monster m) {
		this.mx = m.getX();
		this.my = m.getY();
	}

	public Bullet(float x, float y) {
		this.x = x;
		this.y = y;
		// this.numTower = numTower;

	}

	private void checksearchXY() {
		bx = SearchDirection.SearchX(mx + 39, my + 39, x, y, 15);
		by = SearchDirection.SearchY(mx + 39, my + 39, x, y, 15);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(255, 255, 200));
		g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}

	@Override
	public void update(GameContainer container, int delta) {
		checksearchXY();
		x += bx;
		y += by;
	}

	public boolean CollideMonster() {
		if (Math.abs(mx + 39 - x) < 25 && Math.abs(my + 39 - y) < 25) {
			return true;
		} else {
			return false;
		}
	}
}
