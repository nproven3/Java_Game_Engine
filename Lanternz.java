import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Lanternz extends Canvas implements Runnable{
	
//ibhell@protonmail.com
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 750;
	public static final String TITLE = "Lantern Of Time";
	
	
					//THE executing METHOD IS BASICALLY THE HEART OF THE GAME LOOP
	
	
	
	// A THREAD IS A PATH FOLLOWED WHEN EXECUTING A PROGRAM THEY ARE VERY IMPORTANT FOR THE GAME LOOP
	boolean executing = false;
	public Thread thread;
	//____________________________________________
	
	BufferedImage image = new BufferedImage(HEIGHT, WIDTH, BufferedImage.TYPE_INT_ARGB);
	BufferedImage sprite;
	BufferedImage user;
	
	User userboi;
	
	MenuScreen menu;
	
	
	public BufferedImage MenuImage;
	
	
	public void init() {
		
		requestFocus(); //Makes it so that the window is focused right when it loads
		
		GraphicsLoader loader = new GraphicsLoader();
		

		try{
			
	sprite	= loader.loadImage("/SpriteSheet.png"); //This is the path to the image which is just in eclipse
	//MenuImage = ImageIO.read(getClass().getResourceAsStream("/LOL"));
	
		}catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		addKeyListener(new Input(this));
		
		
		userboi = new User(200,200,this);
		
		
		menu = new MenuScreen(MenuImage,this);
		
		//Sprites need to be initilized before being rendered
		Sprites SpriteBoi = new Sprites(sprite); //It's loading the sprite from above
		user = SpriteBoi.grab(1,1,64,64);
		
	}



	//BASICALLY SYNCHRONIZED MEANS THAT IT'S USING A THEAD IN MULTIPLE INSTANCES AT THE SAME TIME
	//ALSO IT'S THE ONLY WAY TO NOT GET ERRORS WHEN RUNNING THREADS
	public synchronized void start() {
		if(executing)
			return;
		//SINCE THIS IS WHAT START EVERTHING IT NEEDS TO BE CALLED IN THE MAIN METHOD
		
		executing = true;
		thread = new Thread(this);
		thread.start();
	}
	
	
	
	public synchronized void stop() {
		
		if(!executing)
			return;
		executing = false;
		
		
		//Just going to be real this was the first fix eclipse suggested it works and I'm not complaining
		try {
			thread.join();
		} catch (InterruptedException e) { //BASICALLY THIS METHOD OF JOINING THREADS CAN FAIL
			//BUT PROBABLY NEVER WILL SO WE'RE TELLING THE COMPUTER TO SHUT UP BASICALLY
			e.printStackTrace();
		}
		System.exit(1);
		
							
		
		
	}
	
	
	
	//What the game loop does is makes sure that no matter what computer it is running on the ticks per second
	//are always the same
	@Override
	public void run() {
		//makeWalls(); //IF IT DOESN'T WORK IT'S BECAUSE OF THIS
		init();
		long TimeLast = System.nanoTime();
		final double FPS = 60.0;
		double ss = 1000000000 / FPS;
		double catchUp = 0; //Makes sure that the FPS is running at the right time.
	
		//Checks Performance of Game____________________
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		//______________________________________________
		
		while(executing) {
			
			long now = System.nanoTime();
			catchUp += (now - TimeLast) / ss;
			TimeLast = now;
					
			if(catchUp >=1) {
				tick();
				catchUp--;
				updates++;
			}
			
			render();
			frames++;
			
			//REMOVE IN FINAL GAME JUST CHECKS PERFOMANCE____________
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
			//_________________________________________________________
			
			
		} 	
		stop(); //CALLS ON THE EXIT FUNCTION TO MAKE THE GAME STOP
		}
	
	
	
	//Everything in the game that updates
	public void tick() {
		
		//Allows player to be in the tick method
		//should also be used for enemies and some items
	
		if(state == States.Game) {
		userboi.tick();
		}
	}
	
	
	//eveything in the game that renders
	private void render() {
		
		
		BufferStrategy bufstrat = this.getBufferStrategy();
		
		if(bufstrat == null) {
			//Controls how many images are loaded at once before hand very important for the graphics
	//When it is at the number 3 it increases performance
			createBufferStrategy(2); //This allows the process to render 3 images before they are needed
			return;
		}
		
		Graphics g = bufstrat.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH*2, HEIGHT*2);
		
		//THIS IS WHERE IMAGES GO___________________________________________________________
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		if(state == States.Game) {
		userboi.render(g);
		}else if(state == States.Menu) {
			menu.render(g);
			
		}
		
		//THIS IS WHERE IMAGES GO___________________________________________________________
		g.dispose(); //makes it not null
		bufstrat.show();
		
		
	}
	

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyChar() == KeyEvent.VK_ENTER)Lanternz.state = Lanternz.state.Game;
		
		if(state == States.Game) {
		
		if(e.getKeyChar() == 'a') userboi.LeftKey = true;
		if(e.getKeyChar() == 'w') userboi.UpKey = true;
		if(e.getKeyChar() == 's') userboi.DownKey = true;
		if(e.getKeyChar() == 'd') userboi.RightKey = true;
		}else if(state == States.Menu) {
			
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'a') userboi.LeftKey = false;
		if(e.getKeyChar() == 'w') userboi.UpKey = false;
		if(e.getKeyChar() == 's') userboi.DownKey = false;
		if(e.getKeyChar() == 'd') userboi.RightKey = false;
	}
	
	
	
	public static void main(String[]args) {
	
		
		Lanternz lantern = new Lanternz();
		
		//ALL THIS DOES IS JUST ADJUST THE SIZE OF THE GAME IT'SELF
		lantern.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		lantern.setMinimumSize(new Dimension(WIDTH, HEIGHT));	//
		lantern.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		//ALL THE PROPERTIES OF THE FRAME
		JFrame frame = new JFrame(lantern.TITLE);
		frame.add(lantern);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//for later use dispose on close for future features
		frame.setResizable(false);
		frame.setLocationRelativeTo(null); //SETS IT TO THE MIDDLE OF THE SCREEN
		frame.setVisible(true);
		lantern.start();
	}



	
	public BufferedImage getSprite() {
		
		return sprite;
		
	}
	
		//////////////////Move this to the top possibly
public static enum States {
		
		Menu,
		Game
		
	};
	
public static States state = States.Menu;
	
	/////////////////
	
}










