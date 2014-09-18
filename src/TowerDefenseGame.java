import java.util.LinkedList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


import org.newdawn.slick.Input; //import key if finish delete

public class TowerDefenseGame extends BasicGame{
	
	public static int Screen_Width = 1920;
	public static int Screen_Height = 1080;
	public static int Stage_Width = 1560;
	public static int Stage_Height = 780;
	public static int Stage_x = 0;
	public static int Stage_y = 0;
	public static int Store_Height = 300;
	
	private float time = 0;
	public float timer = 0;
	
	public int currentWave = 2;
	public static int wave = 0;
	private boolean checkWave = true;  // check to release monster in next wave
	
	 private LinkedList<Entity> entities;
	
	 private TerrainDarkStage darkterrain;
	 private Store cell;
	 private fieldBuild filedbuild;
	 
	 //monster
	 public static float monster_startX = -78;
	 public static float monster_startY = 156;
	 private boolean monster_checkTotal = false;
	
	
	
	private Image darkstage;
	private Image Shopbackground;
	private Image Upgratebackground;
	

	
	private static float mouseError = 21;
	
	private int checkField = 0;   // ****delete

	public TowerDefenseGame(String title) throws SlickException {
		super(title);
		entities = new LinkedList<Entity>();
	}
	
	
	
	//SetBackGround
	public void setBackgroundinit(GameContainer container) throws SlickException{
		Color background = new Color(0, 0, 0);
		container.getGraphics().setBackground(background);
		darkstage = new Image("res/DarkStage.png");
		Shopbackground = new Image("res/Shop.png");
		Upgratebackground = new Image("res/Upgrate.png");

	}
	
	public void setBackgroundRender(Graphics g){
		g.setColor(new Color(0, 0, 0));
		darkstage.draw(Stage_x, Stage_y);
		Shopbackground.draw(30,Stage_Height+10);
		Upgratebackground.draw(Stage_Width+3,0);
	}

	
	public void Timer(int delta){
		//time in Game
				time += delta;
				if(time>1000){
					time = 0;
					timer++;
					if(currentWave != 0){
						currentWave--;
					}
					if(checkWave && currentWave == 0){
						wave++;
					    checkWave = false;
					}
				}
				
	}
	
	@Override
	  public void keyPressed(int key, char c) {
		 if (key == Input.KEY_T) {
				checkField++;
		    }
	  }
	
	//Update Render and init
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		setBackgroundRender(g);
		
		g.drawString("Wave   " + wave,1300,850);
		g.drawString("Next Wave  " + currentWave,1300,870);
		for(Entity entity : entities) {
			entity.render(g);
		}
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		setBackgroundinit(container);
		
		darkterrain = new TerrainDarkStage(Stage_x, Stage_y);
		cell = new Store();
		filedbuild = new fieldBuild();
		 entities.add(darkterrain);
		 entities.add(cell);
		 entities.add(filedbuild);
	}

	
	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		if(wave == 1 && !monster_checkTotal){
			entities.add(new MonsterLv1(monster_startX,monster_startY));
			monster_checkTotal = true;   // check monster release
		}
		Timer(delta);
		for (Entity entity : entities) {
		      entity.update(container,delta);
		    }
	}
	
	public static void main(String[] args) {
		try {
		      TowerDefenseGame game = new TowerDefenseGame("TowerDefenseGame");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(Screen_Width, Screen_Height ,false);
		      appgc.start();
		    } catch (SlickException e) {
		      e.printStackTrace();
		    }
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		//mouse drag on cell
		Store.checkMouseInCell(newx,newy);
		
		//mouse drag on buildField
		if(checkField%2!=0){
				fieldBuild.checkCol_mouseXRectX = fieldBuild.checkMouseMoveX(newx-mouseError);
				fieldBuild.checkCol_mouseXRectY = fieldBuild.checkMouseMoveX(newy+mouseError/3);
		}
		else{
			fieldBuild.checkCol_mouseXRectX = -1;
			fieldBuild.checkCol_mouseXRectY = -1;
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y){
		if(checkField%2!=0 && button == 0){
			fieldBuild.checkCol_mouseXRectX = -1;
			fieldBuild.checkCol_mouseXRectY = -1;
			checkField++;
		}
	}
}
