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
	private float timer = 0;
	
	 private LinkedList<Entity> entities;
	
	 private TerrainDarkStage darkterrain;
	 private Store cell;
	 private fieldBuild filedbuild;
	
	
	
	private Image darkstage;
	private Image Shopbackground;
	private Image Upgratebackground;
	
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
		//time in Game
		time += delta;
		if(time>1000){
			time = 0;
			timer++;
		}
	}
	
	public static void main(String[] args) {
		try {
		      TowerDefenseGame game = new TowerDefenseGame("TowerDefenseGame");
		      AppGameContainer appgc = new AppGameContainer(game);
		      appgc.setDisplayMode(Screen_Width, Screen_Height ,true);
		      appgc.start();
		    } catch (SlickException e) {
		      e.printStackTrace();
		    }
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		//mouse drag on cell
		for(int i=0; i<Store.shopWidth;i++){
			if(newx > Store.x[i] && newx < Store.x[i] + Store.buttonSize && newy > Store.y && newy <  Store.y+ Store.buttonSize){
				Store.checkmouse[i] = true;
			}
			else{
				Store.checkmouse[i] = false;
			}
		}
		
		
		//mouse drag on right cell
		if(checkField%2!=0){
				fieldBuild.checkCol_mouseXRectX = fieldBuild.checkMouseMoveX(newx);
				fieldBuild.checkCol_mouseXRectY = fieldBuild.checkMouseMoveX(newy);
		}
		else{
			fieldBuild.checkCol_mouseXRectX = -1;
			fieldBuild.checkCol_mouseXRectY = -1;
		}
	}
}
