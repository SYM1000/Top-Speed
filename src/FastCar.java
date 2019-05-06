//Clase para el carro rápido
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
		this.police = new ImageIcon("Police3.png").getImage();
		this.ancho = 38;
		this.alto = 70;
		this.handler = handler;
		velX = 0;
		velY = 6;
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,this.ancho,this.alto);
	}

	//Metodos
	public void tick() {
		x += velX;
		y += velY;

		x = (int) Juego.clamp(x, 120, (Juego.ANCHO - 140) - 50); //Ajustar el jugador en x
		y = (int) Juego.clamp(y, -100, (Juego.ALTO + 200)- 94);
		
		// Eliminar el objeto cuando sale de la pantalla
		if(y >= Juego.ANCHO) {
			this.handler.objeto.remove(this);
		}
	}

	public void render(Graphics g) {
		g.drawImage(this.police, (int)x, (int)y, this.ancho, this.alto, this);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
