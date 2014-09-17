
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Store implements Entity{
	
	public static int shopWidth = 5;
	public static int buttonSize = 78;
	public static int cellSpace = 50;
	public static int shopXFirst = 300;
	
	public static int[] x = new int[]{shopXFirst,shopXFirst+buttonSize + cellSpace,shopXFirst+2*buttonSize+2*cellSpace,shopXFirst+3*buttonSize+3*cellSpace,shopXFirst+4*buttonSize+4*cellSpace};
	public static int y = 880;

	public static Image[] cell = new Image[shopWidth];
	public static Image[] cellmouse = new Image[shopWidth];
	
	public static int checkmouse[] = new int[]{0,0,0,0,0};  // check mouse on cell
	
	public Store() throws SlickException {
		for(int i=0;i<cell.length;i++){
			cell[i] = new Image("res/cell.png");
			cellmouse[i] = new Image("res/cellmouse.png");
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		
		for(int i = 0;i<cell.length;i++) {
			if(checkmouse[i] == 0){
				cell[i].draw(x[i],y);
			}
			else{
				cellmouse[i].draw(x[i],y);
			}
			
		}
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
