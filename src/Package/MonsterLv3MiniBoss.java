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

public class MonsterLv3MiniBoss extends Monster {

	// monster
	private SpriteSheet Monster1;
	private SpriteSheet Monster2;
	private Animation MonsterAnimation1;
	private Animation MonsterAnimation2;
	private int attack = 12; // set attck Hp
	private float v = (float) 3.5; // set velocity
	private int hpMonsterthis = 11000;

	public MonsterLv3MiniBoss(float x, float y) throws SlickException {
		super(x, y);
		Monster1 = new SpriteSheet(
				"res/Monsters/MonsterLv3MiniBoss/MonsterLv3MiniBoss_1.png",
				110, 110);
		Monster2 = new SpriteSheet(
				"res/Monsters/MonsterLv3MiniBoss/MonsterLv3MiniBoss_2.png",
				110, 110);
		MonsterAnimation1 = new Animation(Monster1, 100);
		MonsterAnimation2 = new Animation(Monster2, 100);
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 50;
		typeMonster = "MonsterLv3MiniBoss";

		velocityOld = v;
		velocityGetSkill = v / 2;
		
		element = 2;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation1 = new Animation(Monster1, 100);
			MonsterAnimation2 = new Animation(Monster2, 100);
			checkrunes = true;

		} else if (TowerDefenseGame.typeRunes == 1 && checkrunes) {
			MonsterAnimation1 = new Animation(Monster1, 100000);
			MonsterAnimation2 = new Animation(Monster2, 100000);
			checkrunes = false;
		}

		if (checkAnimation == 0 || checkAnimation == 1) {
			if (getSkillFrost) {
				MonsterAnimation1.draw(x - 22, y - 22, new Color(40, 160, 230));
			} else {
				MonsterAnimation1.draw(x - 22, y - 22);
			}
		} else {
			if (getSkillFrost) {
				MonsterAnimation2.draw(x - 22, y - 22, new Color(40, 160, 230));
			} else {
				MonsterAnimation2.draw(x - 22, y - 22);
			}
		}

		HpBar(g, 2, 9);
	}

	@Override
	public void update(GameContainer container, int delta) {
		updateposition();
		TimerSkill(delta);
	}

}
