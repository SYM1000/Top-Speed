//Clase para crear un frame donde se dibujará el juego
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

public class Ventana extends Canvas{
	
	public Ventana(int ancho, int alto, String nombre_juego, Juego juego) {
		JFrame frame = new JFrame(nombre_juego);
		
		frame.setPreferredSize(new Dimension(ancho,alto));
		frame.setMaximumSize(new Dimension(ancho,alto));
		frame.setMinimumSize(new Dimension(ancho,alto));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(juego);
		frame.setVisible(true);
		juego.start(); 
	}
	

}
