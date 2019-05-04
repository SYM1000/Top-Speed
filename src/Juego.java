import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;

public class Juego extends Canvas implements Runnable{

	public static final int ANCHO = 500;
	public static final int ALTO = 500;
	private Thread hilo;
	private boolean corriendo = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Image calle;
	private Spawn spawner;	
	private Menu menu;
	private ThreadLocalRandom randomNum; //Para probar
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
		this.fondo = new ImageIcon("fondo13.gif").getImage();
		//this.fondo = new ImageIcon("fondo.jpeg").getImage();
		
		r = new Random();
		this.randomNum = ThreadLocalRandom.current(); //Para probar
		
		if(this.estadoJuego == ESTADO.Juego) {
			/*
			//Agregar los objetos al juego
			handler.addObject(new Jugador(ANCHO/2 - 40, ALTO - 120, ID.Jugador, handler)); //Jugador o Usuario
			//handler.addObject(new SlowCar(ANCHO/2 - 40, 0 + 50, ID.SlowCar, handler));
			//handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.SlowCar, handler));
			//handler.addObject(new HeavyCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -80) + 1), -200, ID.HeavyCar, handler));
			*/
		}
	}
	
	public synchronized void start() {
		this.hilo = new Thread(this);
		this.hilo.start();
		this.corriendo = true;
	}
	
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
        	if(this.corriendo) // ó == true
        		render();
        	frames++;
                            
        	if(System.currentTimeMillis() - timer > 1000){
        		timer += 1000;
        		//System.out.println("FPS: "+ frames);
        		frames = 0;
        	}
        }
        stop();
		
	}
	
	private void tick() {
		handler.tick();	
		if(this.estadoJuego == ESTADO.Juego) {
			hud.tick();
			spawner.tick();
			
			if(this.hud.SALUD <= 0) {
				this.hud.SALUD = 100;
				this.estadoJuego = ESTADO.GameOver;
				handler.clearCars();				
			}
			
		}else if(this.estadoJuego == ESTADO.Menu || this.estadoJuego == ESTADO.GameOver) {
			menu.tick();
		}
		
	}
	
	private void render() {
		//mecanismo para organizar gráficos complejos en una ventana o canvas
		BufferStrategy bs  = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();	
		
		//Para el fondo del juego
		g.setColor(Color.gray);
		//g.fillRect(0, 0, ANCHO, ALTO);
		
		if(this.estadoJuego == ESTADO.Juego) { //<-------------
			g.drawImage(this.calle, 0, 0, this.ANCHO, this.ALTO, this);
			
		} /*else if (this.estadoJuego == ESTADO.Menu ) {
			g.drawImage(this.fondo, 0, 0, Juego.ANCHO, Juego.ALTO, this);
			
		}*/

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
	
	/*
	public void Dormir(int milisegundos) {
		try {
			this.hilo.sleep(milisegundos);
		}catch(Exception e) {
			System.out.println(e);
			}
		}
	*/
	
	public static void main(String[] args) {
		new Juego();
	}

}
