
public class moverLinea extends Thread{
	//Clase interna que exitende de thread para crear un hilo en el que se creará las lineas
	//Para simular el movimiento de las lineas
	private Menu menu;
	private boolean correr;
	
	public moverLinea(Menu menu) {
		this.menu = menu;
		this.correr = false;
		
	}

	public void run() {
		try {
				while (this.correr == true) {
					Thread.sleep(2500); //Original: 2500
					menu.handler.addObject(new Linea(Juego.ANCHO/2 - 19, -400 , ID.Linea, menu.handler)); //Lineas
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setCorrer(boolean valor) {
		this.correr = valor;
	}
	
	public boolean getCorrer() {
		return this.correr;
	}
	
	public synchronized void detener() {
		try {
			this.join();
			this.correr =  false;			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public synchronized void iniciar() {
		this.start();
		this.correr = true;
	}
		
	}
	