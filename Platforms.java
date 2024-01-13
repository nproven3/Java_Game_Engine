import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//This is what the platforms will be in so that I can just copy and paste them basically
public class Platforms {

	int x;
	int y;
	int width;
	int height;
	
	static Rectangle HitBox1;
	
	public Platforms(int x, int y, int width, int height){
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		g.setColor(Color.ORANGE);
		g.fillRect(x+1, y+1, width-2, height-2);
		
	}
}


