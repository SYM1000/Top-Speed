import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class SpriteSheet {
	
	private BufferedImage sprite;
	
	public SpriteSheet(BufferedImage ss) {
		this.sprite = ss;		
	}
	
	//Metodo para obtener imagen de una hoja por columnas y filas
	public BufferedImage grabImage(int col, int row, int width, int height) {
		BufferedImage img = this.sprite.getSubimage((row * 32) - 32 , (col * 32) - 32, width, height);
		return img;
	}
	
	//Inteto: metodo para obtener imagen con el nombre del archivo
	public BufferedImage grabImage(String nombre) {
		BufferedImage img = (BufferedImage) new ImageIcon(nombre).getImage();
		return img;
	}
}
