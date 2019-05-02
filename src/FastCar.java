import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class FastCar extends GameObject implements ImageObserver{
	//Atributos
	private Image police;
	private int ancho;
	private int alto;
	private Handler handler;
	
	//Contructor
	public FastCar(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.police = new ImageIcon("Sprites/Police3.png").getImage();
		this.ancho = 38;
		this.alto = 70;
		this.handler = handler;
		
		velX = 0;
		velY = 6;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(x,y,this.ancho,this.alto);
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
		g.drawImage(this.police, x, y, this.ancho, this.alto, this);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
