//Santiago Yeomans 
//A01251000
import java.awt.Graphics;
import java.awt.Rectangle;

//El gameobjecto es a lo que nos referimos cuando queremos hacer operaciones en cualquier objecto del juego.
//Todos los objectos del juego son GameObjects

public abstract class GameObject {

	//Atributos
	protected float x, y; //Posicion del objeto
	protected ID id; //Identificador del Objeto
	protected float velX, velY; //Velocidad del Objeto
	
	//Metodos
	public GameObject(float x, float y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds(); //si dos rectangulos intersectan,  regresa true
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float getX() {
		return this.x;
	}
	
	public float getY() {
		return this.y;
	}
	
	public void setID(ID id) {
		this.id = id;
	}
	
	public ID getId() {
		return this.id;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public float getVelX() {
		return this.velX;
	}
	
	public float getVelY() {
		return this.velY;
	}
	
	
}
