package Package;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TerrainStage implements Entity {

	private float x;
	private float y;
	private int wave;
	fieldBuild terrain = new fieldBuild();
	private Image terrainDark;
	private Image terrainEarth;
	private Image terrainFire;
	private Image terrainWater;
	private Image terrainFinal;

	public TerrainStage(float x, float y, int wave) throws SlickException {
		terrainDark = new Image("res/Stages/terrainDark.png");
		terrainEarth = new Image("res/Stages/terrainEarth.png");
		terrainFire = new Image("res/Stages/terrainFire.png");
		terrainWater = new Image("res/Stages/terrainWater.png");
		terrainFinal = new Image("res/Stages/terrainFinal.png");
		this.x = x;
		this.y = y;
		this.wave = wave;
	}

	public void renderBlockTerrain() {
		for (int i = 0; i < terrain.x_size; i += 2) {
			for (int j = 0; j < terrain.y_size; j += 2) {
				if (terrain.fieldTerrain[j][i] > 0
						&& terrain.fieldTerrain[j][i] < 10) {
					if (wave == 0 || wave == 1) {
						terrainDark.draw(i * terrain.sizeRect, j
								* terrain.sizeRect);
					} else if (wave == 2) {
						terrainEarth.draw(i * terrain.sizeRect, j
								* terrain.sizeRect);
					} else if (wave == 3) {
						terrainFire.draw(i * terrain.sizeRect, j
								* terrain.sizeRect);
					} else if (wave == 4) {
						terrainWater.draw(i * terrain.sizeRect, j
								* terrain.sizeRect);
					} else if (wave == 5) {
						terrainFinal.draw(i * terrain.sizeRect, j
								* terrain.sizeRect);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		renderBlockTerrain();
	}

	public void getWave(int wave) {
		this.wave = wave;
	}

	@Override
	public void update(GameContainer container, int delta) {
	}
}
