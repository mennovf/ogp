package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @author Rugen Heidbuchel, Menno Vanfrachem
 * 
 * A class to manage all motion properties: position, speed and acceleration.
 * It also takes care of all time-related calculations using it's properties.
 */
class Motion {
	
	private Vector<Double> position;
	private Vector<Double> speed;
	private Vector<Double> acceleration;
	
	private GameObject gameObject;
	
	/**
	 * Creates a motion object with the given game object, position, speed and acceleration.
	 * 
	 * @param gameObject
	 * 			The game object to create the motion object with.
	 * 
	 * @param position
	 * 			The position in meters to create the motion object with.
	 * 
	 * @param speed
	 * 			The speed in m/s to create the motion object with.
	 * 
	 * @param acceleration
	 * 			The acceleration in m/(s^2) to create the motion object with.
	 * 
	 * @effect
	 * 			| setPosition(position)
	 * 
	 * @effect
	 * 			| setSpeed(speed)
	 * 
	 * @effect
	 * 			| setAcceleration(acceleration)
	 */
	public Motion(@Raw GameObject gameObject, Vector<Double> position, Vector<Double> speed, Vector<Double> acceleration) {
		this.gameObject = gameObject;
		this.position = position;
		this.speed = speed;
		this.acceleration = acceleration;
	}
	
	
	/**
	 * Returns the position of this motion object in meters.
	 * 
	 * @return The position of this motion object in meters.
	 */
	@Basic
	public Vector<Double> getPosition() {
		return this.position;
	}
	
	/**
	 * Sets the position of this motion object in meters.
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
	 * Returns the speed of this motion object in m/s.
	 * 
	 * @return The speed of this motion object in m/s.
	 */
	@Basic
	public Vector<Double> getSpeed() {
		return this.speed;
	}
	
	/**
	 * Sets the speed of this motion object in m/s.
	 * 
	 * @param speed
	 * 			The speed to set in m/s.
	 * 
	 * @post The speed of this motion object will be the given speed.
	 * 			| new.getSpeed() == speed
	 */
	@Basic
	public void setSpeed(Vector<Double> speed) {
		this.speed = speed;
	}

	
	/**
	 * Returns the acceleration of this motion object in m/(s^2)
	 * 
	 * @return The acceleration of this motion object in m/(s^2).
	 */
	@Basic
	public Vector<Double> getAcceleration() {
		return this.acceleration;
	}
	
	/**
	 * Sets the acceleration of this motion object in m/(s^2).
	 * 
	 * @param acceleration
	 * 			The acceleration to set in m/(s^2).
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
	 * @param maxStep
	 * 			The maximum amount of time step() may progress.
	 * 
	 * @return The time this motion object stepped.
	 * 
	 * @post The position and speed will be altered accordingly.
	 * 			| dt = time step for movement of max 1 pixel
	 * 			| r = this.getPosition()
	 * 			| v = this.getSpeed()
	 * 			| a = this.getAcceleration()
	 * 			| (new r) = (old r) + v*dt + a*(dt^2)/2
	 * 			| (new v) = (old v) + a*dt
	 */
	public Double step(double maxStep) {
		
		Vector<Double> a = this.getAcceleration();
		Vector<Double> v = this.getSpeed();
		Vector<Double> r = this.getPosition();
		
		double dt = maxStep;
		
		if (v.x != 0) {
			double candidateDt = Math.abs(0.01 / v.x);
			dt = Math.min(dt, candidateDt);
		}
		if (v.y != 0) {
			double candidateDt = Math.abs(0.01 / v.y);
			dt = Math.min(dt, candidateDt);
		}
		if (v.x != 0 && a.x != 0) {
			double candidateDt = (Math.sqrt(Math.pow(v.x, 2) + 0.02*Math.abs(a.x)) - Math.abs(v.x)) / Math.abs(a.x);
			dt = Math.min(dt, candidateDt);
		}
		if (v.y != 0 && a.y != 0) {
			double candidateDt = (Math.sqrt(Math.pow(v.y, 2) + 0.02*Math.abs(a.y)) - Math.abs(v.y)) / Math.abs(a.y);
			dt = Math.min(dt, candidateDt);
		}
		
		Vector<Double> newPosition = Vector.add(r, Vector.add(Vector.scale(v, dt), Vector.scale(a, dt*dt/2.0)));
		Vector<Double> newSpeed = Vector.add(v, Vector.scale(a, dt));
		
		this.gameObject.setPositionInMeters(newPosition);
		this.gameObject.setSpeed(newSpeed);
		
		return dt;
	}
}