//Santiago Yeomans 
//A01251000
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
		
		
		try {
			//Agregar los sonidos
			soundMap.put("click", new Sound("click.wav"));
			soundMap.put("arrancar", new Sound("MotorArrancar.wav"));
			soundMap.put("claxon", new Sound("claxon.wav"));
			soundMap.put("gameover", new Sound("gameover.wav"));
			
			//Agregar la musica
			musicMap.put("music", new Music("intro.wav"));
		} catch (SlickException e) {
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