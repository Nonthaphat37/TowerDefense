package Package;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

public class MonsterLv5 extends Monster {

	// monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 2; // set attck Hp
	private float v = (float) 2.5; // set velocity
	private int hpMonsterthis = 6000;

	public MonsterLv5(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet("res/Monsters/MonsterLv5/MonsterLv5.png",
				100, 100);
		MonsterAnimation = new Animation(Monster, 120);
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 50;
		typeMonster = "MonsterLv5";

		// ////
		velocityOld = v;
		velocityGetSkill = v / 2;
		
		element = 0;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation.start();
			checkrunes = true;
		} else if (TowerDefenseGame.typeRunes == 1 && checkrunes) {
			MonsterAnimation.stop();
			checkrunes = false;
		}
		if (getSkillFrost) {
			MonsterAnimation.draw(x - 12, y - 12, new Color(40, 160, 230));
		} else {
			MonsterAnimation.draw(x - 12, y - 12);
		}
		HpBar(g, 4, 8);
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		TimerSkill(delta);
	}

}
