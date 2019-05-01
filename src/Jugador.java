import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;

public class Jugador extends GameObject implements ImageObserver{
	
	private Image carro;
	
	Random r = new Random();

	public Jugador(int x, int y, ID id) {
		super(x, y, id);	
		this.carro = new ImageIcon("carro.jpg").getImage();
		
		
	}

	public void tick() {
		x += velX;
		y += velY;
		
	}

	public void render(Graphics g) {
		//Dibujar la imagen del objeto (carrito)
		g.setColor(Color.white);
		//g.fillRect(x,y,32,32);
		g.drawImage(this.carro, x, y, 40, 60, this);
		
	}

	
	//Para arreglar el añadir una imgan
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
