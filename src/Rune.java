import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class Rune implements Entity{
	
	protected float x;
	protected float y;
	protected float velocity = (float)0.2;
	private Random random;
	protected float LengthY;
	protected int typeRune = 0;       //1=timing 2=attack 3=AddHpCastle
	
	private int TimeToDestroy = 3000;
	private boolean checkAlpha = true;
	
	protected Image runes;
	
	public Rune(float x, float y){
		this.x = x;
		this.y = y;
		random = new Random();
		LengthY = 200+random.nextInt(200);
	}

	@Override
	public void render(Graphics g) {
		
		
	}
	@Override
	public void update(GameContainer container, int delta) {
		updatePosition();
	}
	
	
	protected void updatePosition(){
		y+=velocity;
	}

	protected boolean getClick(int x, int y){
		if(x >= this.x && x <= this.x+78 && y >= this.y && y <= this.y+78){
			return true;
		}
		return false;
	}
	
	protected int getTypeRunes(){
		return typeRune;
	}
	
	protected void effectForDestroy(int delta){
		TimeToDestroy -= delta;
		if(TimeToDestroy <= 0 && !checkAlpha){
			runes.setAlpha((float) 0.6);
			TimeToDestroy = 150;
			checkAlpha = true;
		}
		else if(TimeToDestroy <= 0 && checkAlpha){
			runes.setAlpha((float) 0.2);
			TimeToDestroy = 150;
			checkAlpha = false;
		}
	}
	
}
