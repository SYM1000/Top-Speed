//Santiago Yeomans 
//A01251000
/*Clase para crear el Hud del jugador
 *Mostrar en pantalla la vida y la distancia recorrida
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	public static float SALUD;
	private int valorVerde;
	private int distancia; //Distancia recorrida
	private int nivel;
	
	public HUD() {
		this.SALUD = 100;
		this.valorVerde = 255;
		this.distancia = 0;
		this.nivel = 1;
	}

	public void tick() {
		
		SALUD = (int) Juego.clamp(SALUD, 0, 100);
		
		this.valorVerde = (int) Juego.clamp(this.valorVerde, 0, 255);	
		this.valorVerde = (int) (this.SALUD * 2);
		
		
		this.distancia ++;
	}
	
	public void render(Graphics g) {
		Font fnt2 = new Font("arial", 1,20);
		
		g.setColor(Color.white);
		g.fillRect(Juego.ANCHO - 215,15, 200, 32);
		g.setColor(new Color(100, this.valorVerde, 0));
		g.fillRect(Juego.ANCHO - 215,15, (int) (SALUD * 2), 32);
		g.setColor(Color.black);
		g.drawRect(Juego.ANCHO - 215,15, 200, 32);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawString("Distancia: ", Juego.ANCHO - 130, 70);
		g.drawString(this.distancia  + "m" , Juego.ANCHO - 130, 95);
	}
	
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
	
	public int getDistancia() {
		return this.distancia;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
}
