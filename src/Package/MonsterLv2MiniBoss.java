package Package;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class MonsterLv2MiniBoss extends Monster {

	// monster
	private SpriteSheet Monster;
	private Animation MonsterAnimation;
	private int attack = 12; // set attck Hp
	private float v = (float) 3; // set velocity

	private int hpMonsterthis = 2500;

	public MonsterLv2MiniBoss(float x, float y) throws SlickException {
		super(x, y);
		Monster = new SpriteSheet(
				"res/Monsters/MonsterLv2MiniBoss/MonsterLv2MiniBoss.png", 78,
				78);
		MonsterAnimation = new Animation(Monster, 150);
		attackCastle = attack; // set attack Hp
		velocity = v;
		hpMonster = hpMonsterthis;
		hpMonsterMax = hpMonster;
		CalWidthHpBar = hpMonster / 50;
		typeMonster = "MonsterLv2MiniBoss";

		velocityOld = v;
		velocityGetSkill = v / 2;
		
		element = 3;
	}

	@Override
	public void render(Graphics g) {
		if (TowerDefenseGame.typeRunes != 1 && !checkrunes) {
			MonsterAnimation = new Animation(Monster, 150);
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
