package jumpingalien.model;

public class Constants {
	
	public final static double maxTimeInterval = 0.2;
	public final static Vector2D<Double> screenSize = new Vector2D<>(1024.0, 768.0);
	
	// Speed
	public final static double vxMaxDucking = 1.0;
	public final static double vInitJump = 8.0;
	
	// Acceleration
	public final static Vector2D<Double> acceleration = new Vector2D<>(0.9, -10.0);
}
