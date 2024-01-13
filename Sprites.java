import java.awt.image.BufferedImage;

public class Sprites {

	
	//image has to be private becuase I was uncreative when naming it
	private BufferedImage picture;
	
	
	
	public Sprites(BufferedImage picture) {
		
		this.picture = picture;
		
	}
	
	public BufferedImage grab(int col, int row, int width, int height) {
		
		BufferedImage imagebois = picture.getSubimage((col * 64) - 64, (row * 64) - 64, width, height);
		return imagebois;
	}

	
}


