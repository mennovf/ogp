package jumpingalien.model;

public abstract class GameObject {
	
	private final int maxHealth;
	private int health;
	
	
	protected GameObject(int health, int maxHealth) {
		
		this.health = health;
		this.maxHealth = maxHealth;
	}
	
	public boolean isAlive() {
		
		return health > 0;
	}
	
	public void increaseHealth(int diff) {
		
		this.health += diff;
	}
	
	

	public void advanceTime(double dt) {
		
		
	}
}
