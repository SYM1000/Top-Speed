//Para la elaboración de este juego se tomó como referencia esta guía:
//https://www.youtube.com/watch?v=1gir2R7G9ws&list=PLWms45O3n--6TvZmtFHaCWRZwEqnz2MHa
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.image.BufferStrategy;

import javax.swing.ImageIcon;

public class Juego extends Canvas implements Runnable{

	public static final int ANCHO = 500;
	public static final int ALTO = 500;
	private Thread hilo;
	private boolean corriendo = false;
	private Handler handler;
	private HUD hud;
	private Image calle;
	private Spawn spawner;	
	private Menu menu;
	private Image fondo;
	
	public enum ESTADO{
		Menu,
		Juego,
		Ayuda,
		Creditos,
		GameOver,
		
	};
	
	//El estado en el que inicia el juego por default
	public static ESTADO estadoJuego = ESTADO.Menu;
	
	public Juego() {
		this.handler = new Handler();
		this.hud = new HUD();
		this.menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		new Ventana(ANCHO, ALTO, "Top Speed", this);
		this.hilo = new Thread(this);
		this.spawner =  new Spawn(handler, hud, this);
		this.calle = new ImageIcon("Calle2.png").getImage();
		this.fondo = new ImageIcon("fondo7.gif").getImage();		
		AudioPlayer.load(); //Cargar la musica
		AudioPlayer.getMusic("music").loop(); //Reproducir la musica en un loop cuando inicia el juego	
	}
	
	//iniciar el juego
	public synchronized void start() {
		this.hilo = new Thread(this);
		this.hilo.start();
		this.corriendo = true;
	}
	//Detener el juego
	public synchronized void stop() {
		try {
			this.hilo.join();
			this.corriendo =  false;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		//Game Loop
		this.requestFocus(); //Para no hacer click en la pantalla 
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(this.corriendo){
        	long now = System.nanoTime();
        	delta += (now - lastTime) / ns;
        	lastTime = now;
        	while(delta >=1){
        		tick();
        		delta--;
        	}
        	if(this.corriendo)
        		render();
        	frames++;
                            
        	if(System.currentTimeMillis() - timer > 1000){
        		timer += 1000;
        		System.out.println("FPS: "+ frames);
        		frames = 0;
        	}
        }
        stop();
		
	}
	
	//la lógica del juego
	private void tick() {
		handler.tick();	
		if(this.estadoJuego == ESTADO.Juego) {
			hud.tick();
			spawner.tick();
			
			if(this.hud.SALUD <= 0) {
				this.hud.SALUD = 100;
				this.estadoJuego = ESTADO.GameOver;
				handler.clearCars();	
				AudioPlayer.getSound("gameover").play();												
			}
			
		}else if(this.estadoJuego == ESTADO.Menu || this.estadoJuego == ESTADO.GameOver) {
			menu.tick();
		}
		
	}
	
	//Evitar el efecto parpadeo o Flickr
	@Override
	public void update(Graphics g) {
		render();
	}
	
	private void render() {
		//mecanismo para organizar gráficos complejos en una ventana o canvas
		BufferStrategy bs  = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(2);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();	
		
		//Para el fondo del juego
		if(this.estadoJuego == ESTADO.Juego) { //<-------------
			g.drawImage(this.calle, 0, 0, this.ANCHO, this.ALTO, this);	
			}

		handler.render(g);
		
		if(this.estadoJuego == ESTADO.Juego) {			
			hud.render(g);
		}else if(estadoJuego == ESTADO.Menu || estadoJuego == ESTADO.Ayuda || estadoJuego == ESTADO.Creditos || this.estadoJuego == ESTADO.GameOver) {
			g.drawImage(this.fondo, 0, 0, Juego.ANCHO, Juego.ALTO, this);
			menu.render(g);		
		}
		
		g.dispose();
		bs.show();
	}	
	
	//Metodo para poder mantener un objeto dentro de un espacio
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else 
			return var;
	}
	
	public static void main(String[] args) {
		new Juego();
	}

}
