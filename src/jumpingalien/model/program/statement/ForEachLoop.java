package jumpingalien.model.program.statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import jumpingalien.model.Collidable;
import jumpingalien.model.gameobject.*;
import jumpingalien.model.program.expression.Expression;
import jumpingalien.model.world.World;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;

/**
 * A class representing a for each loop.
 * 
 * @author Rugen Heidbuchel, Menno Vanfrachem
 */
public class ForEachLoop extends Loop {

	private static Map<Kind, Class<? extends GameObject>> kindToClass;
	
	static {
		kindToClass = new HashMap<Kind, Class<? extends GameObject>>();
		kindToClass.put(Kind.BUZAM, Buzam.class);
		kindToClass.put(Kind.MAZUB, Mazub.class);
		kindToClass.put(Kind.PLANT, Plant.class);
		kindToClass.put(Kind.SHARK, Shark.class);
		kindToClass.put(Kind.SLIME, Slime.class);
	}
	
	private final Kind kind;
	private final String variable;
	private final Expression<Boolean> whereExpression;
	private final Expression<Double> sortExpression;
	private final SortDirection sortDirection;
	private final Statement body;
	
	private List<? extends Collidable> objects;
	private int currentObjectIndex;

	public ForEachLoop(Kind kind, String variable, Expression<Boolean> whereExpression,
			Expression<Double> sortExpression, SortDirection sortDirection, Statement body) {
		this.kind = kind;
		this.variable = variable;
		this.whereExpression = whereExpression;
		this.sortExpression = sortExpression;
		this.sortDirection = sortDirection;

		this.body = body;
		this.reset();
	}
	
	
	
	@Override
	public double advanceTime(double dt, Map<String, Object> globals, CallStack callStack) {
		if (this.isFinished()) {
			return dt;
		}

		if (this.objects == null) {
			this.fillInObjects(globals, callStack);
		}

		assignCurrentObject(globals);
		double timeLeft = this.body.advanceTime(dt, globals, this.getOwnCallStack(callStack));
		if (this.body.isFinished()) {
			this.currentObjectIndex += 1;
			this.body.reset();
		}
		return timeLeft;
	}

	
	/**
	 * Assigns a list to this.objects using the filter of this.whereExpression and the sorting
	 * of this.sortExpression and this.sortDirection.
	 * 
	 * @param globals
	 * 			The map of globals variables.
	 * 
	 * @param callStack
	 * 			The CallStack passed down by the calling statement.
	 */
	private void fillInObjects(Map<String, Object> globals, CallStack callStack) {
		BiFunction<Double, Double, Double> comparer =
				this.sortDirection == SortDirection.ASCENDING ?
						(a, b)-> a - b : (a, b) -> b - a;

		World world = callStack.getProgram().getGameObject().getWorld();
		this.objects = new ArrayList<>(this.getObjects(world));

		this.objects = this.objects.stream()
		.filter((object)->{
			globals.put(this.variable, object);
			return this.whereExpression.evaluate(globals, callStack);
		})
		.sorted((o1, o2)->{
			globals.put(this.variable, o1);
			Double left = this.sortExpression.evaluate(globals, callStack);

			globals.put(this.variable, o2);
			Double right = this.sortExpression.evaluate(globals, callStack);
			
			return (int)Math.signum(comparer.apply(left, right));
		}).collect(Collectors.toList());

		this.currentObjectIndex = 0;
	}
	
	/**
	 * Assigns the current object to this.variable in globals.
	 * 
	 * @param globals
	 * 			The map of variables to assign to.
	 */
	private void assignCurrentObject(Map<String, Object> globals) {
		globals.put(this.variable, this.objects.get(this.currentObjectIndex));
	}
	
	
	/**
	 * Returns a set of collidables of Kind this.kind, extracted from world.
	 * 
	 * @param world
	 * 			The world of which to extract the objects.
	 * 
	 * @return a set of collidables of Kind this.kind.
	 */
	private Set<? extends Collidable> getObjects(World world) {
		switch (this.kind) {
		case TERRAIN:
			return world.getTiles();
		case ANY:
			return world.getCollidables();
		default:
			return world.getGameObjectsWithClass(ForEachLoop.kindToClass.get(this.kind));
		}
	}
	
	
	
	@Override
	public boolean isFinished() {
		return (this.objects != null)
			&& (this.currentObjectIndex >= this.objects.size());
	}

	
	@Override
	public void reset() {
		this.objects = null;
		this.currentObjectIndex = 0;
	}


	@Override
	public void forceFinish() {
		this.objects = new ArrayList<>();
		this.currentObjectIndex = this.objects.size();
	}



	@Override
	public boolean isWellFormed(CallStack callStack) {
		return this.body.isWellFormed(this.getOwnCallStack(callStack));
	}

	@Override
	public boolean isActionAllowed() {
		return false;
	}
}
