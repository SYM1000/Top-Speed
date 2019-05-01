import java.awt.Graphics;

//El gameobjecto es a lo que nos referimos cuando queremos hacer operaciones en cualquier objecto del juego.
//Todos los objectos del juego son GameObjects

public abstract class GameObject {

	//Atributos
	protected int x, y; //Posicion del objeto
	protected ID id; //Identificador del Objeto
	protected int velX, velY; //Velocidad del Objeto
	
	//Metodos
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
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
	
	public int getVelX() {
		return this.velX;
	}
	
	public int getVelY() {
		return this.velY;
	}
	
	
}
