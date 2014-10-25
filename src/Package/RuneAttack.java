package Package;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class RuneAttack extends Rune {
	

	public RuneAttack(float x, float y) throws SlickException {
		super(x, y);
		runes = new Image("res/Runes/runeAttack.png");
		typeRune = 2;
	}
	
	@Override
	public void render(Graphics g) {
		runes.draw(x,y);
	}
	
	@Override
	public void update(GameContainer container, int delta) {
		updatePosition();
		effectForDestroy(delta);
	}
	
	protected boolean getClick(int x, int y){
		if(x >= this.x && x <= this.x+78 && y >= this.y && y <= this.y+78){
			return true;
		}
		return false;
	}
	
	protected void updatePosition(){
		if(y < LengthY){
			y+=velocity;
		}
	}
}
