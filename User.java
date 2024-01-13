 import java.awt.Graphics;
import java.awt.image.BufferedImage;


//All of the player charcteristics go to this class. It determines where the player is, how fast he is ect.


public class User{

	//Player Position
	
	
	
	//Player Speed
	double accelerationX;
	double accelerationY;
	
	
	//Player booleans (for buttons
	
	
	boolean LeftKey;
	boolean RightKey;
	boolean UpKey;
	boolean DownKey;
	
	
	
	BufferedImage user;
	
	//I was wondering why this constructor wasn't working then realized that user wasn't capitilized lol
	//30 minutes wasted
	public User(float x, float y, Lanternz lantern) {
		
		super(x, y);
		
		Sprites spriteBoi = new Sprites(lantern.getSprite());
		
		
		user = spriteBoi.grab(1,1,64,64);
	}
	
	
	
	public void tick() {
		
		
		
		
		if(LeftKey && RightKey || !LeftKey && !RightKey) accelerationX *= 0.8;
		//Increases speed when eitehr left of right is held down.
		else if (LeftKey && !RightKey) accelerationX --;//decreases speed when let go
		else if (RightKey && !LeftKey) accelerationX ++;
		
		if(accelerationX >0 && accelerationX <0.75) accelerationX = 0;
		if (accelerationX <0 && accelerationX > -0.75) accelerationX = 0;
		
		if (accelerationX > 7) accelerationX = 7;
		if(accelerationX < -7) accelerationX = -7;
		
		
			
			x += accelerationX;
			y += accelerationY;
			//ADD THE REST OF THIS METHOD IN PLAYER Page 3 of 4 IF AVAILABLE
			
			if(timedBoolean()) {
				
				accelerationY = -6;
				
			}
			
			if(DownKey) {
				
				accelerationY += 0.8;
			}
			
			
			if(y >= 600)
				y = 600;
			//This is all gravity is right here
			accelerationY += 0.2;
			
			
			
			
			
	}
	//Timer Controls how long you have to wait before jump
	long lastTrueTime;
	public boolean timedBoolean() {
	 long now= System.currentTimeMillis();
	 if(UpKey == true && lastTrueTime<now){
	 lastTrueTime=now + 1000; //This number controls how lonog the wait is before the jump
	 return true;
	 }

	 if (lastTrueTime<now) {
	 return false;
	 }
	 return false;
	}
	
	public void render(Graphics g) {
		
		
		g.drawImage(user, (int)x, (int)y, null);
		
	}
	
	
	//TAKE NOTE OF HOW GETTERS AND SETTERS ARE USED WTIH KEYPRESSED
	//YOU WILL NEED TO DO INITIALIZATION IN KEYPRESSED AND THE MAIN GAME LOOP IN THIS SAME WAY FOR ANY OTHER
	//TYPE OF INPUT
	
	public double getPlayerX() {
		return x;
	}
	
	public double getPlayerY() {
		return y;
	}
	
	public void setPlayerX(float x) {
		this.x = x;
	}
	
	public void setPlayerY(float y) {
		this.y = y;
	}
	
	
	//Only setters are used for this but they are used for the acceleration since it is directly changing
	//the player coordinates (x, and y)
	
	
	//______________________________________________________________________________________
	//Accelerators
	
	public void setAccelerationX(double accelerationX) {
		this.accelerationX = accelerationX;
	}
	
	public void setAccelerationY(double accelerationY) {
		this.accelerationY = accelerationY;
	}
	
	
	//player = new User(100,100);
}




