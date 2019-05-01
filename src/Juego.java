import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.util.Random;

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
	
	
	public Juego() {
		this.handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Ventana(ANCHO, ALTO, "Top Speed", this);
		hud = new HUD();
		this.calle = new ImageIcon("Sprites/Calle2.png").getImage();
		r = new Random();
		
		//Agregar los objetos al juego
		handler.addObject(new Jugador(ANCHO/2 - 40, ALTO - 120, ID.Jugador, handler));
		handler.addObject(new SlowCar(ANCHO/2 - 40, 0 + 50, ID.SlowCar));
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
		this.requestFocus(); //Para no hacer click en la pantalla y hacer mas fluido el movimiento 
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
        		System.out.println("FPS: "+ frames);
        		frames = 0;
        	}
        }
        stop();
		
	}
	
	private void tick() {
		handler.tick();		
		hud.tick();
		
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
		g.setColor(Color.white);
		g.fillRect(0, 0, ANCHO, ALTO);
		g.drawImage(this.calle, 0, 0, this.ANCHO, this.ALTO, this);
		
		handler.render(g);
		hud.render(g);
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
