import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/*
 * Clase para reproducir sonidos
 * Tutorial visto para reproducir sonido:
 * https://www.youtube.com/watch?v=HRaJXVuZjRM
 */

public class AudioPlayer{
	
	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();
	
	public static void load() {
		
		//Agregar musica al mapa de musica(nombre de la musica, de donde sacarla)
		try {
			soundMap.put("click", new Sound("recursos/click.wav"));
			soundMap.put("arrancar", new Sound("recursos/MotorArrancar.wav"));
			soundMap.put("acelerar", new Sound("recursos/Acelerar.wav"));
			soundMap.put("motor", new Sound("recursos/motor.wav"));
			soundMap.put("claxon", new Sound("recursos/claxon.wav"));
			soundMap.put("gameover", new Sound("recursos/gameover.wav"));
			
			//Agregar los demas sonidos y musica
			
			
			musicMap.put("music", new Music("recursos/intro.wav"));
			musicMap.put("music2", new Music("recursos/juego.wav"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key) {
		return soundMap.get(key);
	}
}