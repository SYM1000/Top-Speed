import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class SlowCar extends GameObject implements ImageObserver{
	//Atributos
	private Image police;
	
	//Contructor
	public SlowCar(int x, int y, ID id) {
		super(x, y, id);
		this.police = new ImageIcon("police.jpg").getImage();
		
		velX = 0;
		velY = 2;
	}

	//Metodos
	public void tick() {
		x += velX;
		y += velY;

		if(y <= 0 || y >= Juego.ALTO) velY *=-1;
		if(y <= 0 || y >= Juego.ANCHO) velX *=-1;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(this.police, x, y, 40, 60, this);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
