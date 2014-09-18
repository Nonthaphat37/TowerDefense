import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;


public class fieldBuild implements Entity{
	public static int x_size = 40;
	public static int y_size = 20;
	
	private static int[] x = new int[x_size];
	private static int[] y = new int[y_size];
	public static int sizeRect = 39;
	
	public static int fieldTerrain[][] = {
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,0,0,0,0,0,0}			//0
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,9,9,0,0,0,0,0,0}			//1
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0}			//2
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,8,8,0,0,0,0,0,0}			//3
		   ,{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,0,0,0,0,0,0}			//4
		   ,{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,7,7,0,0,0,0,0,0}			//5
		   ,{0,0,0,0,0,0,2,2,0,0,0,0,0,0,5,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,0}			//6
		   ,{0,0,0,0,0,0,2,2,0,0,0,0,0,0,5,5,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,0}			//7
		   ,{0,0,0,0,0,0,3,3,0,0,0,0,0,0,4,4,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0}			//8
		   ,{0,0,0,0,0,0,3,3,0,0,0,0,0,0,4,4,0,0,0,0,2,2,0,0,0,0,0,0,0,0,0,0,5,5,0,0,0,0,0,0}			//9
		   ,{0,0,0,0,0,0,4,4,0,0,0,0,0,0,3,3,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0}			//10
		   ,{0,0,0,0,0,0,4,4,0,0,0,0,0,0,3,3,0,0,0,0,3,3,0,0,0,0,0,0,0,0,0,0,4,4,0,0,0,0,0,0}			//11
		   ,{0,0,0,0,0,0,5,5,0,0,0,0,0,0,2,2,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0}			//12
		   ,{0,0,0,0,0,0,5,5,0,0,0,0,0,0,2,2,0,0,0,0,4,4,0,0,0,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0}			//13
		   ,{0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0}			//14
		   ,{0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,0,0,0,0,5,5,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,0}			//15
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0}			//16
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0}			//17
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}			//18
		   ,{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}			//19
	};
	
	public static int checkCol_mouseXRectX;
	public static int checkCol_mouseXRectY;
	
	
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
				if(mouseX> x[i] && mouseX < x[i]+sizeRect){
					return i; 
				}
		}
		return -1;
	}
	
	public static int checkMouseMoveY(float mouseY){
			for(int j=0;j<y_size;j++){
				if(mouseY > y[j] && mouseY  < y[j]+sizeRect){
					return j; 
				}
			}
		return -1;
	}

	private void checkPositionMouseAndRect(Graphics g){
		Color outRect = new Color(0f,0f,0f,0f);
		Color myAlphaColor = new Color(0f,150f,0f,0.5f);
		Color notbuildColor = new Color(150f,0f,0f,0.3f);
		for(int i=0;i<x_size;i++){
			for(int j=0;j<y_size;j++){
				build[i][j] = new Rectangle(i*sizeRect,j*sizeRect,sizeRect,sizeRect);
				x[i] = i*sizeRect;
				y[j] = j*sizeRect;
				g.setColor(outRect);
			    g.draw(build[i][j]);
			    if(((i == checkCol_mouseXRectX && j == checkCol_mouseXRectY) ||
			       (i == checkCol_mouseXRectX+1 && j == checkCol_mouseXRectY)||	
			       (i == checkCol_mouseXRectX && j == checkCol_mouseXRectY-1)||	
			       (i == checkCol_mouseXRectX+1 && j == checkCol_mouseXRectY-1))
			       && checkCol_mouseXRectY!=20 && checkCol_mouseXRectY!= 0 && checkCol_mouseXRectX!= -1 && checkCol_mouseXRectX!= 39){
			    	
			         if( fieldTerrain[j][i] != 0){
				    	   g.setColor(notbuildColor);
				      }else{
						    	g.setColor(myAlphaColor);
					   } 
			    	 }
			  g.fill(build[i][j]);
			}
		}
	}
	
	
	@Override
	public void render(Graphics g) {
		checkPositionMouseAndRect(g);
	}

	@Override
	public void update(GameContainer container, int delta) {
		
	}

}
