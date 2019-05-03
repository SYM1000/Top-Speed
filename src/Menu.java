import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Menu extends MouseAdapter implements ImageObserver {
	
	public Juego juego;
	public Handler handler;
	private Random r;
	
	public Menu(Juego juego, Handler handler) {
		this.juego = juego;
		this.handler = handler;
		this.r = new Random();
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//Boton: JUGAR
		if(this.mouseOver(mx, my, 150, 175, 200, 55)) {
			juego.estadoJuego = Juego.ESTADO.Juego;
			//Agregar los objetos al juego
			//Aquí inicia el juego
			handler.addObject(new Jugador(Juego.ANCHO/2 - 40, Juego.ALTO - 120, ID.Jugador, handler)); //Jugador o Usuario
			//handler.addObject(new SlowCar(ANCHO/2 - 40, 0 + 50, ID.SlowCar, handler));
			//handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.SlowCar, handler));
			//handler.addObject(new HeavyCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -80) + 1), -200, ID.HeavyCar, handler));
		}
		
		//Boton: AYUDA
		if(this.mouseOver(mx, my, 150, 250, 200, 55)){
			juego.estadoJuego = Juego.ESTADO.Ayuda;
		}
		
		//Boton: CRÉDITOS
		if(this.mouseOver(mx, my, 150, 325, 200, 55)){
			juego.estadoJuego = Juego.ESTADO.Creditos;
		}
		
		//Boton: SALIR
		if(this.mouseOver(mx, my, 150, 400, 200, 55)){
			System.exit(1);
		}
				
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	//Checar si el mouse está sobre un valor
	private boolean mouseOver(int mx, int my, int x, int y, int ancho, int alto) {
		if(mx > x && mx < x + ancho) {
			if(my > y && my < y + alto) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}

	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(juego.estadoJuego == Juego.ESTADO.Menu) {	
			Font fnt = new Font("arial", 1,50);
			Font fnt2 = new Font("arial", 1,30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Top Speed", 125, 75);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(150, 175, 200, 55);
			g.drawString("JUGAR", 200, 215);
			

			g.drawRect(150, 250, 200, 55);
			g.drawString("AYUDA", 200, 290);
			
			
			g.drawRect(150, 325, 200, 55);
			g.drawString("CRÉDITOS", 170, 365);

			
			g.drawRect(150, 400, 200, 55);
			g.drawString("SALIR", 205, 440);
			
		}else if(juego.estadoJuego == Juego.ESTADO.Ayuda) {
			Font fnt = new Font("arial", 1,50);
			Font fnt2 = new Font("arial", 1,30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("AYUDA", 160, 75);
			
		}else if (juego.estadoJuego == Juego.ESTADO.Creditos) {
			Font fnt = new Font("arial", 1,50);
			Font fnt2 = new Font("arial", 1,30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("CRÉDITOS", 115, 75);
		}
		
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
