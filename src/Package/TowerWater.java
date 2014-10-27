package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TowerWater extends Tower {

	// set Status tower
	private int Attack = 55;
	private int Speed = 17;
	private int Range = 320;
	private int Element = 1;

	enum State {
		Active, InActive
	};

	private Image cellSkill;

	private Image towerWater;
	State currentState = State.InActive;

	public TowerWater(float x, float y) throws SlickException {
		super(x, y);
		towerWater = new Image("res/Towers/testTower2.png");
		rangeTower = this.Range;
		attackTower = this.Attack;
		speedTower = this.Speed;
		element = this.Element;
		cellSkill = new Image("res/Towers/SkillTower/skillTowerWater.png");
	}

	public void showSkill(Graphics g) {
		if (currentState == State.Active) {
			if (skillMouseClick) {
				skillWaterOn = false;
				skillMouseClick = false;
				currentState = State.InActive;
			}
		} else if (currentState == State.InActive) {
			if (skillMouseClick) {
				skillWaterOn = true;
				skillMouseClick = false;
				currentState = State.Active;
			}
		}
		if (Mana == -1) {
			skillWaterOn = false;
			skillMouseClick = false;
			currentState = State.InActive;
		}
		if (skillShow) {
			if (!skillWaterOn) {
				if (!skillMouseOver) {
					cellSkill.setAlpha(1f);
					cellSkill.draw(1700, 400);
				} else {
					cellSkill.setAlpha(0.8f);
					cellSkill.draw(1700, 400, new Color(255, 255, 255));
				}
			} else {
				if (Mana == 0) {
					cellSkill.setAlpha(0.8f);
					cellSkill.draw(1700, 400, new Color(255, 255, 255));
				} else {
					cellSkill.draw(1700, 400, new Color(0, 150, 255));
				}
			}
			g.setColor(new Color(0, 0, 0));
			g.drawString("Skill ", 1620, 430);
			if (Mana == -1) {

				g.drawString("0", 1800, 430);
			} else {
				g.drawString("" + Mana, 1800, 430);
			}
		}
	}

	@Override
	public void render(Graphics g) {
		towerWater.draw(x, y);
		RenderRuneAttack();
		showSkill(g);
	}

	@Override
	public void update(GameContainer container, int delta) {
		towerWater.setRotation(dir);
		RegenMana(delta);
	}
}
