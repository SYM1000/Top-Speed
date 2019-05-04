import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Juego juego;
	private int puntuacion = 0;
	private ThreadLocalRandom randomNum;
	private Random r;
	
	public Spawn(Handler handler, HUD hud, Juego juego) {
		this.handler = handler;
		this.hud = hud;
		this.juego = juego;
		r = new Random();
		this.randomNum = ThreadLocalRandom.current();
	}
	
	public void tick() {
		this.puntuacion ++;
		
		if(this.puntuacion >= 100) { //Cada vez que se complete se suma un nivel
			puntuacion = 0;
			hud.setNivel(hud.getNivel() + 1); //Incrementar el nivel en 1 cada ves que se llegue a 1km
			
			int inicio = this.r.nextInt(((Juego.ANCHO - 140) - 120) + 1) + 120;
	
			
			if(hud.getNivel() == 1) {
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) - 35) + 1), 10, ID.SlowCar, handler));			
			}else if(hud.getNivel() == 2) {
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) - 35) + 1), 10, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) - 35) + 1), 10, ID.SlowCar, handler));
			}else if(hud.getNivel() == 3) {
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -35) + 1), 10, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -35) + 1), 10, ID.SlowCar, handler));
			}else if(hud.getNivel() == 4) {
				handler.addObject(new FastCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.FastCar, handler));
			}else if(hud.getNivel() == 5) {
				handler.addObject(new SmartCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -30 ) + 1), 10, ID.SmartCar, handler));
			}else if(hud.getNivel() == 6) {
				handler.addObject(new HeavyCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -80) + 1), -200, ID.HeavyCar, handler));
				handler.addObject(new FastCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.FastCar, handler));
			}else if(hud.getNivel() == 7) {
				handler.addObject(new FastCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.FastCar, handler));
			}else if(hud.getNivel() == 8) {
				handler.addObject(new FastCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.FastCar, handler));
				handler.addObject(new HeavyCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -80) + 1), -200, ID.HeavyCar, handler));	
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) - 35) + 1), 10, ID.SlowCar, handler));	
			}else if(hud.getNivel() == 9) {
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) - 35) + 1), 10, ID.SlowCar, handler));	
			}else if(hud.getNivel() == 10) {
				handler.addObject(new HeavyCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -80) + 1), -200, ID.HeavyCar, handler));
				handler.addObject(new FastCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -38) + 1), 10, ID.FastCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) - 35) + 1), 10, ID.SlowCar, handler));
				handler.addObject(new SmartCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) -30 ) + 1), 10, ID.SmartCar, handler));
				hud.setNivel(0);
				
			}
			//Metodo para eliminar todos los objetos del juego
			//handler.clearCars();
		}
		
	}
}
