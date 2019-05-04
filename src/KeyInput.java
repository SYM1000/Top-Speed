import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Clase para las entradas del teclado
public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean[] keyDown = new boolean[5];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		this.keyDown[0] =  false;
		this.keyDown[1] =  false;
		this.keyDown[2] =  false;
		this.keyDown[3] =  false;
		this.keyDown[4] =  false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i =0; i<handler.objeto.size();i++) { //tempObject es cualquier objecto en nuestro juego
			GameObject tempObject = handler.objeto.get(i);
			
			if(tempObject.getId() == ID.Jugador) {
				//Eventos del Teclado para el usuario
				
				if(key == KeyEvent.VK_W) {
					//AudioPlayer.getSound("acelerar").play();
					tempObject.setVelY(-5); 
					this.keyDown[0] =  true;
				
				}
				if(key == KeyEvent.VK_S) { tempObject.setVelY(5); this.keyDown[1] =  true;}
				if(key == KeyEvent.VK_D) { tempObject.setVelX(3); this.keyDown[2] =  true;}
				if(key == KeyEvent.VK_A) { tempObject.setVelX(-3); this.keyDown[3] =  true;}
				
				if(key == KeyEvent.VK_SPACE) {
					AudioPlayer.getSound("claxon").play();
					this.keyDown[4] =  true;
					}
				
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
				
				if(key == KeyEvent.VK_W) {this.keyDown[0] =  false;}
				if(key == KeyEvent.VK_S) this.keyDown[1] =  false;
				if(key == KeyEvent.VK_D) this.keyDown[2] =  false;
				if(key == KeyEvent.VK_A) this.keyDown[3] =  false;
				if(key == KeyEvent.VK_SPACE) {this.keyDown[4] =  false;}
				
				//Movimiento vertical
				if(!keyDown[0] && !keyDown[1]) {
					tempObject.setVelY(0);
				} 
				//Movimiento horizontal
				if(!keyDown[2] && !keyDown[3]) {
					tempObject.setVelX(0);
				} 
			}
		}
	}
}
