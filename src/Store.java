
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class Store implements Entity{
	
	
	private static int cellSpace = 50;
	private static int shopXFirst = 300;
	private static int shopWidth = 5;
	private static int buttonSize = 78;
	
	private static int[] x = new int[]{shopXFirst,shopXFirst+buttonSize + cellSpace,shopXFirst+2*buttonSize+2*cellSpace,shopXFirst+3*buttonSize+3*cellSpace,shopXFirst+4*buttonSize+4*cellSpace};
	private static int y = 880;

	private static Image[] cell = new Image[shopWidth];
	private static Image[] cellmouse = new Image[shopWidth];
	
	private static boolean checkmouse[] = new boolean[]{false,false,false,false,false};  // check mouse on cell
	
	//Tower
	
	private static Image towershop1;
	
	public Store() throws SlickException {
		
		towershop1 = new Image("res/testTower.png");
		
		
		for(int i=0; i<cell.length;i++){
			cell[i] = new Image("res/cell.png");
			cellmouse[i] = new Image("res/cellmouse.png");
		}
	}
	
	public static void checkMouseInCell(int mouseX, int mouseY){
		for(int i=0; i<Store.shopWidth;i++){
			if(mouseX > x[i] && mouseX < x[i] + Store.buttonSize && mouseY > y && mouseY < y+buttonSize){
				checkmouse[i] = true;
			}
			else{
				checkmouse[i] = false;
			}
		}
	}
	
	/*///////////////////////////////////////////////////////////////////////////////
	//tower*/
	public static boolean checkMouseTower(int mouseX, int mouseY){
		int i = 0;
		if(mouseX > x[i] && mouseX < x[i] + Store.buttonSize && mouseY > y && mouseY < y+buttonSize){
			return true;
		}
		else{
			return false;
		}
	}

	public void shopTower(){
		towershop1.draw(shopXFirst,y);	//render tower1
	}
	
	public void RenderShopTowerOnMouseMoved(){
		
	}
	////////////////////////////////////////////////////////////////////
	
	
	
	
	
	@Override
	public void render(Graphics g) {
		
		for(int i = 0;i<cell.length;i++) {
			if(!checkmouse[i]){
				cell[i].draw(x[i],y);
			}
			else{
				cellmouse[i].draw(x[i],y);
			}
		}
		shopTower();
	
	}
	@Override
	public void update(GameContainer container, int delta) {
		
		
	}

}
