package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class MonsterLv4 extends Monster {

	// monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 2; // set attck Hp
	private float v = (float) 2; // set velocity
	private int hpMonsterthis = 4000;

	public MonsterLv4(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet("res/Monsters/MonsterLv4/MonsterLv4.png", 78,
				78);
		MonsterAnimation = new Animation(Monster, 120);
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 50;
		typeMonster = "MonsterLv4";

		// ////
		velocityOld = v;
		velocityGetSkill = v / 2;
		
		element = 1;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation = new Animation(Monster, 120);
			checkrunes = true;

		} else if (TowerDefenseGame.typeRunes == 1 && checkrunes) {
			MonsterAnimation = new Animation(Monster, 100000);
			checkrunes = false;
		}
		if (getSkillFrost) {
			MonsterAnimation.draw(x, y, new Color(40, 160, 230));
		} else {
			MonsterAnimation.draw(x, y);
		}
		HpBar(g, 0, 0);
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		TimerSkill(delta);
	}
}
