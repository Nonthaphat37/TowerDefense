import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class GoldBuilding implements Entity{
	
	private float x;
	private float y;
	
	private Image goldBuilding;

	public GoldBuilding(float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		goldBuilding = new Image("res/GoldBuilding.png");
	}
	
	
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		goldBuilding.draw(x, y);
	}

	@Override
	public void update(GameContainer container, int delta) {
		// TODO Auto-generated method stub
		
	}
}
