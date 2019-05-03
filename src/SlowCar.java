import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class SlowCar extends GameObject implements ImageObserver{
	//Atributos
	private Image police;
	private int ancho;
	private int alto;
	private Handler handler;
	
	//Contructor
	public SlowCar(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.police = new ImageIcon("Police2.png").getImage();
		this.ancho = 35;
		this.alto = 70;
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,this.ancho,this.alto);
	}

	//Metodos
	public void tick() {
		x += velX;
		y += velY;
		
		/*
		if(y <= 0 || y >= Juego.ALTO) velY *=-1;
		if(y <= 0 || y >= Juego.ANCHO) velX *=-1;
		*/
		
		// Eliminar el objeto cuando sale de la pantalla
		if(y >= Juego.ANCHO) {
			this.handler.objeto.remove(this);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(this.police, (int)x, (int)y, this.ancho, this.alto, this);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
