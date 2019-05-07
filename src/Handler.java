//Santiago Yeomans 
//A01251000
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
	
	//metodo para eliminar carros
	public void clearCars() {
		for(int i = 0; i < objeto.size(); i++ ) {
			GameObject tempObject = objeto.get(i);
			
			if(tempObject.getId() == ID.Jugador) {
				objeto.clear();
				if(Juego.estadoJuego != Juego.ESTADO.GameOver)
				addObject(new Jugador((int)tempObject.getVelX(), (int)tempObject.getY(), ID.Jugador, this));
			}
		}
	}
	
	//Añadir objetos al juego 
	public void addObject(GameObject objeto) {
		this.objeto.add(objeto);
	}
	
	//Remover objetos del juego
	public void removeObject(GameObject objeto) {
		this.objeto.remove(objeto);
	}
	
}

