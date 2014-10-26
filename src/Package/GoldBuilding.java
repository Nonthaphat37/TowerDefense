package Package;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GoldBuilding extends Tower {

	private float x;
	private float y;
	private float[] gy = new float[2];

	// set Status tower
	public int GoldTimeRate = 0;
	public int GoldRate = 10;
	public int GoldRateLast = GoldRate;
	private int Element = 99;
	public int showGoldRate;

	private int GoldTimeRateCurrent = 6000 - GoldTimeRate * 10;

	private boolean[] goldTextUp = new boolean[] { false, false };
	private Image goldBuilding;

	public GoldBuilding(float x, float y) throws SlickException {
		super(x, y);
		this.x = x;
		this.y = y;
		this.gy[0] = y;
		this.gy[1] = y;

		goldBuilding = new Image("res/Towers/GoldBuilding.png");

		this.GoldRate = goldRate;
		this.GoldTimeRate = goldTimeRate;
		element = this.Element;
	}

	public void TextGold(Graphics g) {
		if (goldTextUp[0]) {
			g.setColor(new Color(100f, 5f, 10f, (float) (1 - (y - gy[0]) / 50)));
			g.drawString("+" + GoldRateLast, x + 25, gy[0] + 5);
		}
		if (goldTextUp[1]) {
			g.setColor(new Color(100f, 5f, 10f, (float) (1 - (y - gy[1]) / 50)));
			g.drawString("+" + GoldRateLast, x + 25, gy[1] + 5);
		}
	}

	@Override
	public void render(Graphics g) {
		goldBuilding.draw(x, y);
		TextGold(g);

	}

	@Override
	public void update(GameContainer container, int delta) {
		GoldTimeRateCurrent -= delta;
		if (GoldTimeRateCurrent <= 0) {
			GoldTimeRateCurrent = 5000 - GoldTimeRate * 10;
			TowerDefenseGame.Gold += GoldRate;
			GoldRateLast = GoldRate;
			if (goldTextUp[0] == false && goldTextUp[1] == false) {
				goldTextUp[0] = true;
			} else if (goldTextUp[0] == true && goldTextUp[1] == false) {
				goldTextUp[1] = true;
			} else if (goldTextUp[0] == false && goldTextUp[1] == true) {
				goldTextUp[0] = true;
			}
		}

		this.GoldRate = goldRate;
		this.GoldTimeRate = goldTimeRate;

		if (goldTextUp[0]) {
			gy[0] -= 0.8;
			if (gy[0] < y - 50) {
				goldTextUp[0] = false;
				gy[0] = y;
			}
		}
		if (goldTextUp[1]) {
			gy[1] -= 0.8;
			if (gy[1] < y - 50) {
				goldTextUp[1] = false;
				gy[1] = y;
			}
		}

	}
}
