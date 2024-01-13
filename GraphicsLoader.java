import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class GraphicsLoader {

	//LITERALLY ALL THIS CLASS DOES IS LOAD IN BUFFERD IMAGES
	//7:29 ep 5
	private BufferedImage picture;
	
	public BufferedImage loadImage(String path) throws IOException {
		
		picture = ImageIO.read(getClass().getResource(path));
		return picture;
		
	}
	
	
}


