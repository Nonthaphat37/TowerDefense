import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public class Monster implements Entity{
	
	private float x;
	private float y;

    public Monster(float x,float y){
		this.x = x;
		this.y = y;
    }

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
	}
	protected void updateposition(){
		x+=1;
	}

}
