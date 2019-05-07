//Santiago Yeomans 
//A01251000
//Clase del jugador principal/carro/usuario
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
		this.carro = new ImageIcon("ferrari_carro-removebg.png").getImage();
		this.ancho = 35;
		this.alto = 72;
		this.handler = handler;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x,(int)y,this.ancho,this.alto);
	}
	
	public void tick() {
		x += velX;
		y += velY;		
		x = (int) Juego.clamp(x, 120, (Juego.ANCHO - 140) - 35); //Ajustar el jugador en x
		y = (int) Juego.clamp(y, 0, Juego.ALTO - 94);		
		collision();		
	}
	
	public void collision() {
		//detectar las colisiones con cada objeto del juego
		for(int i = 0; i< handler.objeto.size();i++) {
			GameObject tempObject = handler.objeto.get(i);
			
			if(tempObject.getId() == ID.SlowCar || tempObject.getId() == ID.FastCar || tempObject.getId() == ID.SmartCar || tempObject.getId() == ID.HeavyCar) { //El objeto temporal es el carro lento
				if(getBounds().intersects(tempObject.getBounds())) {
					//Lo que ocurre si coliciona con un carro
					if (tempObject.getId() == ID.SlowCar) {
						HUD.SALUD -= 1;			
					}else if (tempObject.getId() == ID.FastCar) {
						HUD.SALUD -= 3;
					}else if (tempObject.getId() == ID.SmartCar) {
						HUD.SALUD -= 2;
					}else if (tempObject.getId() == ID.HeavyCar) {
						HUD.SALUD -= 10;
					}
				}
			}

		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//Dibujar la imagen del objeto (carrito)
		g.drawImage(this.carro, (int)x, (int)y, this.ancho, this.alto, this);	
	}

	
	//Arreglar cunado se agrega una imagen
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
