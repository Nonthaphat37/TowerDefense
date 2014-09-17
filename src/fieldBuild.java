import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;


public class fieldBuild implements Entity{
	private static int x_size = 40;
	private static int y_size = 20;
	private static int sizeRect = 39;
	private static int[] x = new int[x_size];
	private static int[] y = new int[y_size];
	
	public static int checkCol_mouseXRectX;
	public static int checkCol_mouseXRectY;
	public static float mouseError = 20;
	
	Rectangle[][] build;
	
	public fieldBuild() throws SlickException{
		build = new Rectangle[x_size][y_size];
		for(int i=0;i<x_size;i++){
			for(int j=0;j<y_size;j++){
			x[i] = i*sizeRect;
			y[j] = j*sizeRect;
		}
	}
	}
	public static int checkMouseMoveX(float mouseX){
		for(int i=0;i<x_size;i++){
				if(mouseX+mouseError > x[i] && mouseX+mouseError < x[i]+sizeRect){
					return i; 
				}
		}
		return -1;
	}
	
	public static int checkMouseMoveY(float mouseY){
			for(int j=0;j<y_size;j++){
				if(mouseY-mouseError/2 > y[j] && mouseY-mouseError/2 < y[j]+sizeRect){
					return j; 
				}
			}
		return -1;
	}

	
	@Override
	public void render(Graphics g) {
		Color outRect = new Color(0f,0f,0f,0f);
		Color myAlphaColor = new Color(0f,150f,0f,0.5f);
		for(int i=0;i<x_size;i++){
			for(int j=0;j<y_size;j++){
				build[i][j] = new Rectangle(i*sizeRect,j*sizeRect,sizeRect,sizeRect);
				x[i] = i*sizeRect;
				y[j] = j*sizeRect;
				g.setColor(outRect);
			    g.draw(build[i][j]);
			    if(((i == checkCol_mouseXRectX && j == checkCol_mouseXRectY) ||
			       (i == checkCol_mouseXRectX-1 && j == checkCol_mouseXRectY)||	
			       (i == checkCol_mouseXRectX && j == checkCol_mouseXRectY-1)||	
			       (i == checkCol_mouseXRectX-1 && j == checkCol_mouseXRectY-1))
			       && checkCol_mouseXRectY!=20 && checkCol_mouseXRectX!= 0){
			    	g.setColor(myAlphaColor);
			    }
			   g.fill(build[i][j]);
			}
		}
		
	}

	@Override
	public void update() {
		
	}

}
