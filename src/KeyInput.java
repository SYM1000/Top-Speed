import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Clase para las entradas del teclado
public class KeyInput extends KeyAdapter{

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i =0; i<handler.objeto.size();i++) { //tempObject es cualquier objecto en nuestro juego
			GameObject tempObject = handler.objeto.get(i);
			
			if(tempObject.getId() == ID.Jugador) {
				//Eventos del Teclado para el usuario
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_S) tempObject.setVelY(5);
				if(key == KeyEvent.VK_D) tempObject.setVelX(5);
				if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			//salir del juego al presionar tecla escape
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i =0; i<handler.objeto.size();i++) { //tempObject es cualquier objecto en nuestro juego
			GameObject tempObject = handler.objeto.get(i);
			
			if(tempObject.getId() == ID.Jugador) {
				//Eventos del Teclado para el usuario
				
				if(key == KeyEvent.VK_W) tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) tempObject.setVelY(0);
				if(key == KeyEvent.VK_D) tempObject.setVelX(0);
				if(key == KeyEvent.VK_A) tempObject.setVelX(0);
				
			}
		}
	}
}
