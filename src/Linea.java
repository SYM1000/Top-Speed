//Santiago Yeomans 
//A01251000
//Enemigo basico y el más fácil
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

public class Linea extends GameObject{
	//Atributos
	private int ancho;
	private int alto;
	private Handler handler;
	
	//Contructor
	public Linea(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.ancho = 13;
		this.alto = 100;
		this.handler = handler;		
		velX = 0;
		velY = 3;
		
	}
	
	//Metodos
	public void tick() {
		x += velX;
		y += velY;
		
		//x = (int) Juego.clamp(x, 120, (Juego.ANCHO - 140) - 50); //Ajustar el jugador en x
		//y = (int) Juego.clamp(y, -100, (Juego.ALTO + 200)- 94);
		
		// Eliminar el objeto cuando sale de la pantalla
		if(y >= Juego.ANCHO) {
			//handler.addObject(new Linea(Juego.ANCHO/2 - 40, -500, ID.Linea, handler)); //Lineas
			this.handler.objeto.remove(this);
			//this.handler.addObject(new Linea(Juego.ANCHO/2 - 40, 0, ID.Linea, handler));
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)(x), (int)(y), ancho, alto);
		
		g.fillRect((int)(x), 150 + (int)(y), ancho, alto);
		
		g.fillRect((int)(x), 300 + (int)(y), ancho, alto);
		
		g.fillRect((int)(x), 450 + (int)(y), ancho, alto);
		
		g.fillRect((int)(x), 600 + (int)(y), ancho, alto);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
