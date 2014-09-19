import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Bullet implements Entity{
	private int BULLET_SIZE = 20;
	private float x;
	private float y;
	
	@Override
	public void render(Graphics g) {
		g.setColor(new Color(105, 55, 200));
		 g.fillOval(x, y, BULLET_SIZE, BULLET_SIZE);
	}

	@Override
	public void update(GameContainer container, int delta) {
		
	}

}
