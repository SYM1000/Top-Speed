import java.util.concurrent.ThreadLocalRandom;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int puntuacion = 0;
	private ThreadLocalRandom randomNum;
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.randomNum = ThreadLocalRandom.current();
	}
	
	public void tick() {
		this.puntuacion ++;
		
		if(this.puntuacion >= 250) { //Cada vez que se complete se suma un nivel
			puntuacion = 0;
			hud.setNivel(hud.getNivel() + 1); //Incrementar el nivel en 1 cada ves que se llegue a 1km
			
			if(hud.getNivel() == 1) {
					handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.SlowCar, handler));
			}else if(hud.getNivel() == 2) {
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.SlowCar, handler));
			}else if(hud.getNivel() == 3) {
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.SlowCar, handler));
				handler.addObject(new SlowCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.SlowCar, handler));
			}else if(hud.getNivel() == 4) {
				handler.addObject(new FastCar(this.randomNum.nextInt(120, ((Juego.ANCHO - 140) /* - el ancho del enemigo*/) + 1), 10, ID.FastCar, handler));
			}
			
			
			
			
		}
		
	}
}
