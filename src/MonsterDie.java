import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;




public class MonsterDie implements Entity{
	

	private SpriteSheet SpriteSheetDie;
	private Animation AnimationDie;
	private boolean checkAddDieMonster = false;
	private float x;
	private float y;
	private float TimeToDelete = 3000;
	public boolean checkEndTime = false;
	
	public MonsterDie(float x, float y, String typeMonster, int checkAnimation) throws SlickException{
		if(typeMonster == "MonsterLv1Boss"){
			if(checkAnimation == 0 || checkAnimation == 1){
				SpriteSheetDie = new SpriteSheet("res/Monster/MonsterLv1_BossDie01.png",78,78);
				AnimationDie = new Animation(SpriteSheetDie,100);
			}
			else{
				SpriteSheetDie = new SpriteSheet("res/Monster/MonsterLv1_BossDie2.png",78,78);
				AnimationDie = new Animation(SpriteSheetDie,100);
			}
			checkAddDieMonster = true;
		}
		this.x = x;
		this.y = y;
	}

	@Override
	public void render(Graphics g) {
		if(checkAddDieMonster){
			AnimationDie.draw(x, y, new Color(255f,255f,255f,(float)(TimeToDelete/3000)));
			AnimationDie.setLooping(false);
		}
	}

	@Override
	public void update(GameContainer container, int delta) {
		TimeToDelete-= delta;
		if(TimeToDelete <= 0){
			checkEndTime = true;
		}
	}
	

}
