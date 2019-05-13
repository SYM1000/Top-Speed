
public class moverLinea extends Thread{
	//Clase interna que exitende de thread para crear un hilo en el que se creará las lineas
	//Para simular el movimiento de las lineas
	private Menu menu;
	private boolean detener =  false;
	
	public moverLinea(Menu menu) {
		this.menu = menu;
		
	}
	/*
	public moverLinea(Menu menu2) {
		// TODO Auto-generated constructor stub
	}*/

	public void run() {
		//handler.addObject(new Linea(Juego.ANCHO/2 - 40,0, ID.Linea, handler)); //Lineas
		try {
			
			while (true) {
				Thread.sleep(2500);
				menu.handler.addObject(new Linea(Juego.ANCHO/2 - 19, -400 , ID.Linea, menu.handler)); //Lineas
			}
			/*
			Thread.sleep(1000);
			handler.addObject(new Linea(Juego.ANCHO/2 - 40,0, ID.Linea, handler)); //Lineas
			Thread.sleep(1000);
			handler.addObject(new Linea(Juego.ANCHO/2 - 40,0, ID.Linea, handler)); //Lineas
			*/
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
		
	}
	