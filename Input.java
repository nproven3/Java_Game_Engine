import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Input extends KeyAdapter{
	
	//This class deals with input
	
	//Take note this is how to use properites from another class
	
	Lanternz lantern;
	MenuScreen menu;
	
	
	public Input(Lanternz lantern) {
		this.lantern = lantern;
		
	}
	
	public Input(MenuScreen menu) {
		this.menu = menu;
		
	}

	public void keyPressed(KeyEvent e) {
		lantern.keyPressed(e);
		
	}
	public void keyReleased(KeyEvent e) {
		lantern.keyReleased(e);
		
	}
	
	
}



