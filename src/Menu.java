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
import javax.swing.JOptionPane;

import org.newdawn.slick.openal.Audio;


//Clase para crear el menu del juego
public class Menu extends MouseAdapter implements ImageObserver {
	
	public Juego juego;
	public Handler handler;
	private HUD hud;
	private Jugador jugador;
	private Image logo, teclas, ferrari, lamborghini, audi, bugatti, viper, lotus, koenis, mercedes;
	
	private moverLinea mvs;
	private ThreadLocalRandom randomNum;

	public Menu(Juego juego, Handler handler, HUD hud, Jugador jugador) {
		this.juego = juego;
		this.handler = handler;
		this.hud = hud;
		this.jugador = jugador;
		this.logo = new ImageIcon("logo.png").getImage();
		this.teclas =  new ImageIcon("teclas.png").getImage();
		this.ferrari =  new ImageIcon("ferrari_carro-removebg.png").getImage();
		this.lamborghini = new ImageIcon("lambo_carro.jpg").getImage();
		this.audi =  new ImageIcon("Audi.png").getImage();
		this.bugatti =  new ImageIcon("Bugatti.png").getImage();
		this.viper =  new ImageIcon("Viper.png").getImage();
		this.lotus =  new ImageIcon("Lotus.png").getImage();
		this.koenis =  new ImageIcon("Koenis.png").getImage();
		this.mercedes =  new ImageIcon("Mercedes.png").getImage();
		this.randomNum = ThreadLocalRandom.current();
		moverLinea mvs = new moverLinea(this);
		this.mvs = mvs;
		this.mvs.iniciar();
		
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (juego.estadoJuego == Juego.ESTADO.Menu) {
				
			//Boton: JUGAR
			if(this.mouseOver(mx, my, 150, 175, 200, 55)) {
				//Aquí inicia el juego
				juego.estadoJuego = Juego.ESTADO.Juego;
				//mvs.setCorrer(true);
				
				AudioPlayer.getSound("click").play();
				
				if(jugador.getBolFerrari() == false && jugador.getBolLamborghini() == false && jugador.getBolAudi() == false && jugador.getBolBugatti() == false && jugador.getBolViper() == false && jugador.getBolLotus() == false && jugador.getBolKoenis() == false && jugador.getBolMercedes() == false) {
					AudioPlayer.getSound("arrancar").play();
				}else if(jugador.getBolFerrari() == true) {
					AudioPlayer.getSound("arrancar").play();
				}else if(jugador.getBolLamborghini() == true) {
					AudioPlayer.getSound("lamboStart").play();
				}else if(jugador.getBolAudi() == true) {
					AudioPlayer.getSound("audiStart").play();
				}else if(jugador.getBolBugatti() == true) {
					AudioPlayer.getSound("bugattiStart").play();
				}else if(jugador.getBolViper() == true) {
					AudioPlayer.getSound("viperStart").play();
				}else if(jugador.getBolLotus() == true) {
					AudioPlayer.getSound("lotusStart").play();
				}else if(jugador.getBolKoenis() == true) {
					AudioPlayer.getSound("koenisStart").play();
				}else if(jugador.getBolMercedes() == true) {
					AudioPlayer.getSound("mercedesStart").play();
				}
				
				//handler.addObject(new Linea(Juego.ANCHO/2 - 19, -400 , ID.Linea, handler)); //Agregar una linea para debuggear
				
				//Agregar el jugador y unos enemigos al inico del juego	
				//Añadir el jugador en lo misma posicion siempre y no en movimiento
				jugador.x = Juego.ANCHO/2 - 40;
				jugador.y = Juego.ALTO - 120;
				jugador.velX = 0;
				jugador.velY = 0;
				handler.addObject(jugador);	
				
				//handler.addObject(new Jugador(Juego.ANCHO/2 - 40, Juego.ALTO - 120, ID.Jugador, handler)); //Jugador o Usuario
				handler.addObject(new SlowCar(Juego.ANCHO/2 - 40, 0 + 50, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), -100, ID.SlowCar, handler));
				
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
			
			
			//Boton: Skins
			if(this.mouseOver(mx, my, 380, 175, 100, 55)){
				juego.estadoJuego = Juego.ESTADO.Skins;
				AudioPlayer.getSound("click").play();
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
		
		//Regresar en creditos
			if(juego.estadoJuego == Juego.ESTADO.Skins) {
				if(this.mouseOver(mx, my, 150, 410, 200, 55)) {
					AudioPlayer.getSound("click").play();
					juego.estadoJuego = Juego.ESTADO.Menu;
					return;
				}
			}
		
		//Regresar al menu
		if(juego.estadoJuego == Juego.ESTADO.GameOver) {
			//Detener clase con hilo
			//this.mvs.detener();
			handler.removeObject(jugador);
			handler.clearCars();
			//se ha elimado el jugador
			if(this.mouseOver(mx, my, 150, 400, 200, 55)) {
				AudioPlayer.getSound("click").play();
				juego.estadoJuego = Juego.ESTADO.Menu;
				hud.setNivel(1);
				hud.setDistancia(0);
			}
		}
		
		//En prubas y mejoras continuas. No está terminado
		if(juego.estadoJuego == Juego.ESTADO.Skins) {
			
			/*
			 ********************
			//SELECCIONAR EL AUTO
			 * ******************
			*/
			
			//Ferrari
			if(this.mouseOver(mx, my, 50, 90, 70, 144)) {      //  <--------------------
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de motor de ferrari
				AudioPlayer.getSound("arrancar").play();
				//Camiar la imgagen del jugador a un ferrari
				jugador.setBolFerrari(true);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(false);
				jugador.setBolViper(false);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Ferrari seleccionado");
				
			//Lamborghini
			}else if(this.mouseOver(mx, my, 160, 90, 70, 144)) {     //<---------------------
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de motor de Lamborghini
				AudioPlayer.getSound("lamboStart").play(); 
				//Camiar la imgagen del jugador a un lamborghini		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(true);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(false);
				jugador.setBolViper(false);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Lamborghini seleccionado");	
			}
			
			//Audi
			else if(this.mouseOver(mx, my, 270, 90, 70, 144)) {
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de arranque del carro
				AudioPlayer.getSound("audiStart").play();
				//Camiar la imgagen del jugador a un audi		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(true);
				jugador.setBolBugatti(false);
				jugador.setBolViper(false);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Audi R8 seleccionado");
			}
			
			//Buggatti
			else if(this.mouseOver(mx, my, 380, 90, 70, 144)) {
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de arranque del carro
				AudioPlayer.getSound("bugattiStart").play();
				//Camiar la imgagen del jugador a un carro		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(true);
				jugador.setBolViper(false);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Bugatti Veyron seleccionado");
			}
			
			//Viper
			else if(this.mouseOver(mx, my, 50, 250, 70, 144)) {
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de arranque del carro
				AudioPlayer.getSound("viperStart").play();
				//Camiar la imgagen del jugador a un carro		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(false);
				jugador.setBolViper(true);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Dodge Viper seleccionado");
			}
	
			
			//Lotus
			else if(this.mouseOver(mx, my, 160, 250, 70, 144)) {
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de arranque del carro
				AudioPlayer.getSound("lotusStart").play();
				//Camiar la imgagen del jugador a un carro		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(false);
				jugador.setBolViper(false);
				jugador.setBolLotus(true);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Lotus seleccionado");
			}
			
			//Koenisseg
			else if(this.mouseOver(mx, my, 270, 250, 70, 144)) {
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de arranque del carro
				AudioPlayer.getSound("koenisStart").play();
				//Camiar la imgagen del jugador a un carro		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(false);
				jugador.setBolViper(false);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(true);
				jugador.setBolMercedes(false);
				//JOptionPane.showMessageDialog(null, "Koenigsegg seleccionado");
			}
			
			//Mercedes
			else if(this.mouseOver(mx, my, 380, 250, 70, 144)) {
				AudioPlayer.getSound("click").play();
				//Repdroducir sonido de arranque del carro
				AudioPlayer.getSound("mercedesStart").play();
				//Camiar la imgagen del jugador a un carro		
				jugador.setBolFerrari(false);
				jugador.setBolLamborghini(false);
				jugador.setBolAudi(false);
				jugador.setBolBugatti(false);
				jugador.setBolViper(false);
				jugador.setBolLotus(false);
				jugador.setBolKoenis(false);
				jugador.setBolMercedes(true);
				//JOptionPane.showMessageDialog(null, "Mercedes SLS seleccionado");
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
			Font fnt3 = new Font("arial", 1,25);
			
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
			
			g.setFont(fnt3);
			g.drawRect(380, 175, 100, 55);
			g.drawString("SKINS", 390, 210);
			
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
			
		} else if(juego.estadoJuego == Juego.ESTADO.Skins) {
			//this.mvs.detener();
			Font fnt = new Font("arial", 1,50);
			Font fnt2 = new Font("arial", 1,30);
			Font fnt3 = new Font("arial", 1,20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("SKINS", 180, 50);
			
			g.setFont(fnt3);
			g.drawString("Escoge el carro con el que quieras correr", 65, 75);
			
			//Escoger ferrari
			g.drawRect(50, 90, 70, 144);
			g.drawImage(this.ferrari, 55, 99, 59, 122, this); //Tamaño original ferrari: 35, 72 (ancho y alto)
			
			//Escoger lamborghini
			g.drawRect(160, 90, 70, 144);
			g.drawImage(this.lamborghini, 165, 99, 59, 122, this);
			
			//Escoger Audi
			g.drawRect(270, 90, 70, 144);
			g.drawImage(this.audi, 277, 99, 57, 122, this);
			
			//Escoger Bugatti
			g.drawRect(380, 90, 70, 144);
			g.drawImage(this.bugatti, 385, 99, 59, 122, this);
			
			//Escoger Viper
			g.drawRect(50, 250, 70, 144);
			g.drawImage(this.viper, 55, 260, 59, 122, this);
			
			//Escoger Lotus
			g.drawRect(160, 250, 70, 144);
			g.drawImage(this.lotus, 165, 260, 59, 122, this);
			
			//Escoger Koenisseg
			g.drawRect(270, 250, 70, 144);
			g.drawImage(this.koenis, 275, 260, 59, 122, this);
			
			//Escoger Mercedes
			g.drawRect(380, 250, 70, 144);
			g.drawImage(this.mercedes, 385, 260, 59, 122, this);
			
			
			//Regresar
			g.setFont(fnt2);
			g.drawRect(150, 410, 200, 55);
			g.drawString("Ir al Menu", 185, 447);
			
		}
		
	}

	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}

}
