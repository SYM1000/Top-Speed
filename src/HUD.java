import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	
	public static int SALUD = 100;

	public void tick() {		
		SALUD = (int) Juego.clamp(SALUD, 0, 100);
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(Juego.ANCHO - 215,15, 200, 32);
		g.setColor(Color.green);
		g.fillRect(Juego.ANCHO - 215,15, SALUD * 2, 32);
		g.setColor(Color.black);
		g.drawRect(Juego.ANCHO - 215,15, 200, 32);
	}
}
