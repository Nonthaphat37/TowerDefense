import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TerrainDarkStage implements Entity{
	
	private float x;
	private float y;
	private Image darkterrain;
	
	public TerrainDarkStage(float x, float y) throws SlickException{
		darkterrain = new Image("res/TerrainDark.png");
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g) {
		darkterrain.draw(x, y);
	}

	@Override
	public void update() {
		
	}
}
