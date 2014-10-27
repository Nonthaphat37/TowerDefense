package Package;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import Package.TowerWater.State;

public class TowerFire extends Tower {

	// set Status tower
	private int Attack = 25;
	private int Speed = 35;
	private int Range = 300;
	private int Element = 2;

	private Random randomChanceSkill;

	private Image towerFire;
	private Image cellSkill;
	
	private boolean textUp = false;
	private float timeForTextUpConstant = 30;
	private float timeForTextUp = 0;

	public TowerFire(float x, float y) throws SlickException {
		super(x, y);
		towerFire = new Image("res/Towers/testTower3.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
		randomChanceSkill = new Random();
		cellSkill = new Image("res/Towers/SkillTower/skillTowerFire.png");
	}

	public void chanceskillFire() {
		if (skillFireOn) {
			if (randomChanceSkill.nextInt(3) == 0) {
				calSpeed = speedSecTower - 350;
				textUp = true;
			}
			skillFireOn = false;
		}
	}

	public void showSkill(Graphics g) {
		if (skillShow) {
			g.setColor(new Color(0, 0, 0));
			g.drawString("Skill ", 1620, 430);
			cellSkill.setAlpha(1f);
			cellSkill.draw(1700, 400);
		}
	}

	@Override
	public void render(Graphics g) {
		towerFire.draw(x, y);
		RenderRuneAttack();
		chanceskillFire();
		showSkill(g);
		textSkillUp(g);
	}

	@Override
	public void update(GameContainer container, int delta) {
		towerFire.setRotation(dir);
		if(textUp){
			timeForTextUp+=1.2;
			if(timeForTextUp >= timeForTextUpConstant){
				textUp = false;
				timeForTextUp = 0;
			}
		}
	}

	public void textSkillUp(Graphics g){
		if(textUp){
			g.setColor(new Color(255f, 0f, 0f,(1-(timeForTextUp/timeForTextUpConstant))));
			g.drawString("Double", x+13, y - timeForTextUp);
		}
	}
}
