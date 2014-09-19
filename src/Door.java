import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Door implements Entity{
	
	
	private float dx;
	private float dy;
	private float hx;
	private float hy;
	private Image door;
	private Image heart;
	
	public Door(float dx, float dy, float hx, float hy) throws SlickException{
		door = new Image("res/Door.png");
		heart = new Image("res/HeartHp.png");
		this.dx = dx;
		this.dy = dy;
		this.hx = hx;
		this.hy = hy;
	}
	

	@Override
	public void render(Graphics g) {
		door.draw(dx,dy);
		heart.draw(hx,hy);
		
	}

	@Override
	public void update(GameContainer container, int delta) {
		// TODO Auto-generated method stub
		
	}
}
