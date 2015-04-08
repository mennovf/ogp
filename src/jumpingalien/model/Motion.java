package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

public class Motion {
	
	private Vector<Double> position;
	private Vector<Double> speed;
	private Vector<Double> acceleration;
	
	private GameObject gameObject;
	
	Motion(@Raw GameObject gameObject, Vector<Double> position, Vector<Double> speed, Vector<Double> acceleration) {
		
		//TODO: gameObject setter
		this.gameObject = gameObject;
		this.position = position;
		this.speed = speed;
		this.acceleration = acceleration;
	}
	
	
	/**
	 * @return The position in 
	 */
	@Basic
	public Vector<Double> getPosition() {
		
		return this.position;
	}
	
	/**
	 * Sets the position of this motion object.
	 * 
	 * @param position
	 * 			The position to set.
	 * 
	 * @post The position of this motion object will be the given position.
	 * 			| new.getPosition() == position
	 */
	@Basic
	public void setPosition(Vector<Double> position) {
		
		this.position = position;
	}
	
	
	/**
	 * @return The speed of this motion object.
	 */
	@Basic
	public Vector<Double> getSpeed() {
		
		return this.speed;
	}
	
	/**
	 * Sets the speed of this motion object.
	 * 
	 * @param speed
	 * 			The speed to set.
	 * 
	 * @post The speed of this motion object will be the given speed.
	 * 			| new.getSpeed() == speed
	 */
	@Basic
	public void setSpeed(Vector<Double> speed) {
		
		this.speed = speed;
	}

	
	/**
	 * @return The acceleration of this motion object.
	 */
	@Basic
	public Vector<Double> getAcceleration() {
	
		return this.acceleration;
	}
	
	/**
	 * Sets the acceleration of this motion object.
	 * 
	 * @param acceleration
	 * 			The acceleration to set.
	 * 
	 * @post The acceleration of this motion object will be the given acceleration.
	 * 			| new.getAcceleration() == acceleration
	 */
	public void setAcceleration(Vector<Double> acceleration) {
		
		this.acceleration = acceleration;
	}
	
	
	/**
	 * Lets this motion object make one time step.
	 * 
	 * @return The time this motion object stepped.
	 */
	public Double step(Double totalTime) {
		
		Vector<Double> a = this.getAcceleration();
		Vector<Double> v = this.getSpeed();
		Vector<Double> r = this.getPosition();
		
		Double dt = totalTime;
		
		if (v.x != 0) {
			Double candidateDt = Math.abs(100 / v.x);
			dt = Math.min(dt, candidateDt);
		}
		if (v.y != 0) {
			Double candidateDt = Math.abs(100 / v.y);
			dt = Math.min(dt, candidateDt);
		}
		if (a.x != 0) {
			Double candidateDt = (100 * Math.sqrt(Math.abs(a.x)/50 + Math.pow(v.x/100, 2)) - Math.abs(v.x)) / Math.abs(a.x);
			dt = Math.min(dt, candidateDt);
		}
		if (a.y != 0) {
			Double candidateDt = (100 * Math.sqrt(Math.abs(a.y)/50 + Math.pow(v.y/100, 2)) - Math.abs(v.y)) / Math.abs(a.y);
			dt = Math.min(dt, candidateDt);
		}
		
		Vector<Double> newPosition = Vector.add(r, Vector.add(Vector.scale(v, dt), Vector.scale(a, dt*dt/2.0)));
		Vector<Double> newSpeed = Vector.add(v, Vector.scale(a, dt));
		
		//TODO: Finish this method
		
		this.gameObject.setPosition(Utilities.clipVectorInRange(new Vector<Double>(0.0, 0.0),
							this.gameObject.getWorld().getSizeInMeters(), newPosition));
		this.gameObject.setSpeed(newSpeed);
		
		return dt;
	}
}