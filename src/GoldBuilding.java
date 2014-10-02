import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class GoldBuilding implements Entity{
	
	private float x;
	private float y;
	private float gy;
	private float GoldTimeRate = 4000;
	private float GoldTimeRateCurrent = GoldTimeRate;
	private float GoldRate = 10;
	
	private boolean goldTextUp = false;
	private Image goldBuilding;

	public GoldBuilding(float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		this.gy = y;
		goldBuilding = new Image("res/GoldBuilding.png");
	}
	
	public void TextGold(Graphics g){
		if(goldTextUp){
			g.setColor(new Color(155,255,20));
			g.drawString("+10", x + 25, gy);
		}
	}
	
	@Override
	public void render(Graphics g) {
		goldBuilding.draw(x, y);
		TextGold(g);
		
	}

	@Override
	public void update(GameContainer container, int delta) {
		GoldTimeRateCurrent -= delta;
		if(GoldTimeRateCurrent <= 0){
			GoldTimeRateCurrent = GoldTimeRate;
			TowerDefenseGame.Gold += GoldRate;
			goldTextUp = true;
		}
		
		if(goldTextUp){
			gy-=0.1;
			if(gy < y-40){
				goldTextUp = false;
				gy = y;
			}
		}
	}
}
