import java.awt.Graphics;
import java.util.LinkedList;

//Clase handler para revisar cada objeto en nuestro juego e individualmente actualizarlos y hacer el render de cada objeto en la pantalla 
public class Handler {
	
	//Linekd List
	LinkedList<GameObject> objeto = new LinkedList<GameObject>();
	
	public void tick() {
		//Aqui se recorre cada objeto del juego
		for(int i=0; i<objeto.size(); i++) {
			GameObject tempObject = objeto.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i<objeto.size(); i++) {
			GameObject tempObject = objeto.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject objeto) {
		this.objeto.add(objeto);
	}
	
	public void removeObject(GameObject objeto) {
		this.objeto.remove(objeto);
	}
}

