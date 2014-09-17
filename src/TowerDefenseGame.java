import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class TowerDefenseGame extends BasicGame{
	
	private static int Screen_Width = 1920;
	private static int Screen_Height = 1080;
	private Image background;

	public TowerDefenseGame(String title) throws SlickException {
		super(title);
		
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.draw(0,0);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Image("res/DarkStage.png");
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		
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
}
