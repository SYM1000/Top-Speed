//Santiago Yeomans 
//A01251000
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//Clase para crear el menu del juego
public class Menu extends MouseAdapter implements ImageObserver {
	
	public Juego juego;
	public Handler handler;
	private HUD hud;
	private Image logo, teclas;
	private ThreadLocalRandom randomNum;
	
	public Menu(Juego juego, Handler handler, HUD hud) {
		this.juego = juego;
		this.handler = handler;
		this.hud = hud;
		this.logo = new ImageIcon("logo.png").getImage();
		this.teclas =  new ImageIcon("teclas.png").getImage();
		this.randomNum = ThreadLocalRandom.current();
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (juego.estadoJuego == Juego.ESTADO.Menu) {		
			//Boton: JUGAR
			if(this.mouseOver(mx, my, 150, 175, 200, 55)) {
				juego.estadoJuego = Juego.ESTADO.Juego;
				
				//Aquí inicia el juego
				//Agregar el jugador y unos enemigos al inico del juego		
				handler.addObject(new Jugador(Juego.ANCHO/2 - 40, Juego.ALTO - 120, ID.Jugador, handler)); //Jugador o Usuario	
				handler.addObject(new SlowCar(Juego.ANCHO/2 - 40, 0 + 50, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), -100, ID.SlowCar, handler));
			
				AudioPlayer.getSound("click").play();
				AudioPlayer.getSound("arrancar").play();
				
			}
			
			//Boton: AYUDA
			if(this.mouseOver(mx, my, 150, 250, 200, 55)){
				juego.estadoJuego = Juego.ESTADO.Ayuda;
				AudioPlayer.getSound("click").play();
				
			}
			
			
			//Boton: CRÉDITOS
			if(this.mouseOver(mx, my, 150, 325, 200, 55)){
				juego.estadoJuego = Juego.ESTADO.Creditos;
				AudioPlayer.getSound("click").play();
				
			}
			
			//Boton: SALIR
			if(this.mouseOver(mx, my, 150, 400, 200, 55)){
				System.exit(1);
			}
		}
		
		//Regresar en Ayuda
				if(juego.estadoJuego == Juego.ESTADO.Ayuda) {
					if(this.mouseOver(mx, my, 150, 400, 200, 55)) {
						AudioPlayer.getSound("click").play();
						juego.estadoJuego = Juego.ESTADO.Menu;
						return;
					}
				}
		
		//Regresar en creditos
		if(juego.estadoJuego == Juego.ESTADO.Creditos) {
			if(this.mouseOver(mx, my, 150, 400, 200, 55)) {
				AudioPlayer.getSound("click").play();
				juego.estadoJuego = Juego.ESTADO.Menu;
				return;
			}
		}
		
		//Regresar al menu
		if(juego.estadoJuego == Juego.ESTADO.GameOver) {
			if(this.mouseOver(mx, my, 150, 400, 200, 55)) {
				AudioPlayer.getSound("click").play();
				juego.estadoJuego = Juego.ESTADO.Menu;
				hud.setNivel(1);
				hud.setDistancia(0);
			}
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
			g.drawImage(this.logo, 155, 20, 180, 150, this);
			
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
			
			//Dibujar las teclas
			g.drawImage(this.teclas, 200, 120, 115, 80, this);
			
			//Escribir el texto
			g.setFont(fnt2);
			g.drawString("Utiliza las teclas W,S,D y A", 60, 255);
			g.drawString("para mover tu ferrari a los ", 65, 290);
			g.drawString("lados y evitar a los enemigos.", 45, 325);
			
			//Regresar
			g.setFont(fnt2);
			g.drawRect(150, 400, 200, 55);
			g.drawString("Regresar", 185, 437);
			
		}else if (juego.estadoJuego == Juego.ESTADO.Creditos) {
			Font fnt = new Font("arial", 1,50);
			Font fnt2 = new Font("arial", 1,30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("CRÉDITOS", 115, 75);
			
			//Texto
			g.setFont(fnt2);
			g.drawString("Este Juego fue creado por ", 60, 205);
			g.drawString("Santiago Yeomans,", 120, 240);
			g.drawString("con amor y usando JAVA.", 65, 275);
			g.drawString("Gracias por Jugar :)", 110, 340);
			
			//Regresar
			g.setFont(fnt2);
			g.drawRect(150, 400, 200, 55);
			g.drawString("Regresar", 185, 437);
			
		}else if(juego.estadoJuego == Juego.ESTADO.GameOver) {
			Font fnt = new Font("arial", 1,50);
			Font fnt2 = new Font("arial", 1,30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("GameOver", 125, 75);
			
			//Escribir el texto
			g.setFont(fnt2);
			g.drawString("Distancia Recorrida: ", 110, 215);
			g.drawString(hud.getDistancia() + " metros", 180, 250);
			
			//Regresar
			g.setFont(fnt2);
			g.drawRect(150, 400, 200, 55);
			g.drawString("Ir al Menu", 185, 437);
			
		}
		
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
