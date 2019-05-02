import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Jugador extends GameObject implements ImageObserver{
	
	private Image carro;
	private int ancho;
	private int alto;
	
	Random r = new Random();
	Handler handler;

	public Jugador(int x, int y, ID id, Handler handler) {
		super(x, y, id);	
		this.carro = new ImageIcon("Sprites/ferrari_carro-removebg.png").getImage();
		this.ancho = 35;
		this.alto = 72;
		this.handler = handler;
	}

	public Rectangle getBounds(){
		return new Rectangle(x,y,this.ancho,this.alto);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = (int) Juego.clamp(x, 120, (Juego.ANCHO - 140) - 35); //Ajustar el jugador en x
		y = (int) Juego.clamp(y, 0, Juego.ALTO - 94);
		
		collision();
		
	}
	
	public void collision() {
		for(int i = 0; i< handler.objeto.size();i++) {
			GameObject tempObject = handler.objeto.get(i);
			
			if(tempObject.getId() == ID.SlowCar || tempObject.getId() == ID.FastCar ) { //El objeto temporal es el carro lento
				if(getBounds().intersects(tempObject.getBounds())) {
					//Lo que ocurre si coliciona con el carro lento
					if (tempObject.getId() == ID.SlowCar) HUD.SALUD -= 1;
					else if (tempObject.getId() == ID.FastCar) HUD.SALUD -= 3;
				}
			}

		}
	}

	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		/*
		 * Vizualizar la caja con la que hace contacto 
		g.setColor(Color.green);
		g2d.draw(getBounds());
		*/
		
		//Dibujar la imagen del objeto (carrito)
		g.drawImage(this.carro, x, y, this.ancho, this.alto, this);
		
	}

	
	//Para arreglar el añadir una imgan
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
