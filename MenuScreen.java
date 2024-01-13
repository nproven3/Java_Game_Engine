import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class MenuScreen {
	
	
	
	 BufferedImage MenuImage;
	 
	 public MenuScreen() {
		 
		
	 }
	 
	 
	public MenuScreen(BufferedImage MenuImage, Lanternz lantern) {
		 try {
			 MenuImage = ImageIO.read(getClass().getResourceAsStream("/LOL.png"));
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		this.MenuImage = MenuImage;
		
	}
	public void render(Graphics g) {
		
		//Everything in this render method will be the visual apperance of the game
		
		g.drawImage(MenuImage, 250, 150, MenuImage.getWidth(), MenuImage.getHeight(), null);
		
		Lanternz lantern;
		g.setFont(new Font("yes", Font.PLAIN, 60));
		g.setColor(Color.RED);
		g.drawString("Lantern Of Time", 250, 300);
		
		//Instructions
		g.setFont(new Font("yee", Font.PLAIN, 40));
		g.setColor(Color.MAGENTA);
		g.drawString("Use W A S D to move around", 200, 400);
		g.drawString("PRESS ENTER TO START", 250, 500);
		
		
		
		

		
		
	}
	
}



