package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @author Rugen & Menno
 *
 * An abstract class representing an object in the game world.
 * This class provides common features of all the object such as health, etc.
 * 
 * @invar The health of the object is never higher than the max health.
 * 			| this.isValidHealth(this.getHealth())
 */
public abstract class GameObject {
	
	private final int maxHealth;
	private int health;
	
	
	protected GameObject(int health, int maxHealth) {
		this.setHealth(health);
		this.maxHealth = maxHealth;
	}
	
	
	@Basic
	public int getMaximumHealth(){
		return this.maxHealth;
	}

	@Basic
	public int getHealth(){
		return this.health;
	}

	/**
	 * @return Whether the object is alive or not.
	 * 			| this.getHealth() > 0
	 */
	public boolean isAlive() {
		return this.getHealth() > 0;
	}
	
	/**
	 * @param diff The amount with which to increase health.
	 * @effect
	 * 			| this.setHealth(this.getHealth() + diff)
	 */
	public void increaseHealth(int diff) {
		this.setHealth(this.getHealth() + diff);
	}
	
	/**
	 * @param health The health to check for validity.
	 * @return Whether the provided health does not exceed the maximum allowed.
	 * 			| health <= this.maxHealth
	 */
	public boolean isValidHealth(int health){
		return health <= this.maxHealth;
	}
	
	/**
	 * @param health The suggested health for this object.
	 * @post Set this object's health to health if health is smaller than the maximum allowed health
	 * 		 otherwise it sets it to the maximum allowed amount.
	 * 			| if (!this.isValidHealth(health))
	 * 			| then new.getHealth() == health
	 * 			| else new.getHealth() == this.getMaximumHealth()
	 */
	public void setHealth(int health){
		if (!this.isValidHealth(health)){
			health = this.maxHealth;
		}
		this.health = health;
	}
	

	public void advanceTime(double dt) {
		
		
	}
}
