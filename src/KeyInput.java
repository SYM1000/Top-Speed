import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Clase para las entradas del usuario al usar las teclas
public class KeyInput extends KeyAdapter{

	private Handler handler;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		System.out.println(key);
	}
	
	public void keyReleased(KeyEvent e) {
		
	}
}
