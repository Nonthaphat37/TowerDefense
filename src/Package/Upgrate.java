package Package;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Upgrate {
	private int upgrateRangetower;
	private int upgrateSpeedtower;
	private int upgrateAttacktower;

	private int upgrateGoldRatetower;
	private int upgrateGoldTimeRatetower;

	public Upgrate(Tower t) {
		upgrateRangetower = t.rangeTower;
		upgrateSpeedtower = t.speedTower;
		upgrateAttacktower = t.attackTower;
	}

	public int upgrateAttack(int CountupgrateAttack, int attackTower) {
		upgrateAttacktower = (int) (7 + CountupgrateAttack * 1.5 + attackTower / 2);
		return upgrateAttacktower;
	}

	public int upgrateSpeed(int CountupgrateSpeed, int speedTower) {
		upgrateSpeedtower = (int) (1 + CountupgrateSpeed * 0.45 + speedTower / 50);
		return upgrateSpeedtower;
	}

	public int upgrateRange(int CountupgrateRange) {
		upgrateRangetower = 10 + CountupgrateRange * 3;
		return upgrateRangetower;
	}

	public int upgrateGoldRate() {
		upgrateGoldRatetower = 10;
		return upgrateGoldRatetower;
	}

	public int upgrateGoldTimeRate() {
		upgrateGoldTimeRatetower = 40;
		return upgrateGoldTimeRatetower;
	}
}
