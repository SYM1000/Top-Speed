import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class SmartCar extends GameObject implements ImageObserver{
	//Atributos
	private Image smart;
	private int ancho;
	private int alto;
	private Handler handler;
	private GameObject jugador;
	
	//Contructor
	public SmartCar(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.smart = new ImageIcon("smart_car.png").getImage();
		this.ancho = 30;
		this.alto = 50;
		this.handler = handler;
		
		for(int i = 0; i< handler.objeto.size(); i++) {
			if(handler.objeto.get(i).getId() == ID.Jugador){
				this.jugador = handler.objeto.get(i);
			}
		}
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,this.ancho,this.alto);
	}

	//Metodos
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - this.jugador.getX() - this.ancho;
		float diffY = y - this.jugador.getY() - this.alto;
		float distancia = (float) Math.sqrt((x - this.jugador.getX()) * (x - this.jugador.getX()) + (y - this.jugador.getY()) * (y - this.jugador.getY()));

		velX = (float) ((-1.0/distancia) * diffX ) - 1;
		velY = (float) ((-1.0/distancia) * diffY ) + 2;
		
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
		g.drawImage(this.smart, (int)x, (int)y, this.ancho, this.alto, this);
	}

	
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
