import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerDark extends Tower {

	private Image towerDark;
	
	public TowerDark(float x, float y) throws SlickException {
		super(x, y);
		towerDark = new Image("res/testTower.png");
	}
	
	@Override
	public void render(Graphics g) {
		towerDark.draw(x,y);
	}

}
