import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class HeavyCar extends GameObject implements ImageObserver{
	//Atributos
	private Image tanque;
	private int ancho;
	private int alto;
	private Handler handler;
	
	//Contructor
	public HeavyCar(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.tanque = new ImageIcon("Sprites/tank.png").getImage();
		this.ancho = 80;
		this.alto = 120;
		this.handler = handler;
		
		velX = 0;
		velY = 1;
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,this.ancho,this.alto);
	}

	//Metodos
	public void tick() {
		x += velX;
		y += velY;
		
		x = (int) Juego.clamp(x, 120, (Juego.ANCHO - 140) - 35); //Ajustar el jugador en x
		y = (int) Juego.clamp(y, -200, (Juego.ALTO + 200)- 94);
		
		
		//if(y <= -200 || y >= Juego.ALTO) velY *=-1;
		//if (x <= 0 || x >= Juego.ANCHO - 80) velX *=-1;
		
		
		// Eliminar el objeto cuando sale de la pantalla
		if(y >= Juego.ANCHO) {
			this.handler.objeto.remove(this);
		}
		
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawImage(this.tanque, (int)x, (int)y, this.ancho, this.alto, this);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
