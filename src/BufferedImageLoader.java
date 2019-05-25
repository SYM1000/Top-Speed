import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

//cargar la imagen que se le pase como parametro

public class BufferedImageLoader {
	
	BufferedImage image;

	public BufferedImage loadImage(String path) {
		try {
			//image = ImageIO.read(getClass().getResource(path));
			//image = ImageIO.read(getClass().getResource(path));
			this.image = ImageIO.read(new FileInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
