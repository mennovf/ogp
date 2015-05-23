package jumpingalien.model.program;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import jumpingalien.model.Collidable;
import jumpingalien.model.Vector;
import jumpingalien.model.Utilities;
import jumpingalien.model.gameobject.GameObject;
import jumpingalien.model.gameobject.Mazub;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.gameobject.programmable.DuckProgrammable;
import jumpingalien.model.gameobject.programmable.JumpProgrammable;
import jumpingalien.model.gameobject.programmable.RunProgrammable;
import jumpingalien.model.program.expression.*;
import jumpingalien.model.program.statement.*;
import jumpingalien.model.world.Tile;
import jumpingalien.model.world.TileType;
import jumpingalien.model.world.World;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings("unchecked")
public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Object, LanguageProgram> {
	
	@Override
	public Variable<Object> createReadVariable(String variableName, Object variableType,
			SourceLocation sourceLocation) {
		return new Variable<Object>(variableName);
	}

	@Override
	public Value<Double> createDoubleConstant(double value, SourceLocation sourceLocation) {
		return new Value<>(value);
	}

	@Override
	public Value<Boolean> createTrue(SourceLocation sourceLocation) {
		return new Value<>(true);
	}

	@Override
	public Value<Boolean> createFalse(SourceLocation sourceLocation) {
		return new Value<>(false);
	}

	@Override
	public Value<GameObject> createNull(SourceLocation sourceLocation) {
		return new Value<>(null);
	}

	@Override
	public Variable<Object> createSelf(SourceLocation sourceLocation) {
		return new Variable<Object>("self");
	}

	@Override
	public Value<Direction> createDirectionConstant(
			Direction value,
			SourceLocation sourceLocation) {
		return new Value<>(value);
	}

	@Override
	public BinaryOperation<Double, Double, Double> createAddition(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a + (Double)b);
	}

	@Override
	public BinaryOperation<Double, Double, Double> createSubtraction(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a - (Double)b);
	}

	@Override
	public BinaryOperation<Double, Double, Double> createMultiplication(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a * (Double)b);
	}

	@Override
	public BinaryOperation<Double, Double, Double> createDivision(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a / (Double)b);
	}

	@Override
	public UnaryOperation<Double, Double> createSqrt(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Double, Double>((Expression<Double>)expr, Math::sqrt);
	}

	@Override
	public UnaryOperation<Double, Double> createRandom(Expression<?> maxValue, SourceLocation sourceLocation) {
		return new UnaryOperation<Double, Double>((Expression<Double>)maxValue, (a) -> Math.random() * a);
	}

	@Override
	public BinaryOperation<Boolean, Boolean, Boolean> createAnd(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Boolean, Boolean>((Expression<Boolean>)left, (Expression<Boolean>)right,
															  (a, b) -> a && b);
	}

	@Override
	public BinaryOperation<Boolean, Boolean, Boolean> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Boolean, Boolean>((Expression<Boolean>)left, (Expression<Boolean>)right,
															  (a, b) -> a || b);
	}

	@Override
	public UnaryOperation<Boolean, Boolean> createNot(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Boolean>((Expression<Boolean>)expr, (a) -> !a);
	}

	@Override
	public BinaryOperation<Boolean, Double, Double> createLessThan(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a < b);
	}

	@Override
	public BinaryOperation<Boolean, Double, Double> createLessThanOrEqualTo(Expression<?> left, Expression<?> right,
			SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a <= b);
	}

	@Override
	public BinaryOperation<Boolean, Double, Double> createGreaterThan(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a > b);
	}

	@Override
	public BinaryOperation<Boolean, Double, Double> createGreaterThanOrEqualTo(Expression<?> left, Expression<?> right,
			SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a >= b);
	}

	@Override
	public BinaryOperation<Boolean, Object, Object> createEquals(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Object, Object>((Expression<Object>)left, (Expression<Object>)right, (a, b) -> Objects.equals(a, b));
	}

	@Override
	public BinaryOperation<Boolean, Object, Object> createNotEquals(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Object, Object>((Expression<Object>)left, (Expression<Object>)right, (a, b) -> ! Objects.equals(a, b));
	}

	@Override
	public UnaryOperation<Double, Collidable> createGetX(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getPositionInPixels().x);
	}

	@Override
	public UnaryOperation<Double, Collidable> createGetY(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getPositionInPixels().y);
	}

	@Override
	public UnaryOperation<Double, Collidable> createGetWidth(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getSizeInPixels().x);
	}

	@Override
	public UnaryOperation<Double, Collidable> createGetHeight(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getSizeInPixels().y);
	}

	@Override
	public UnaryOperation<Double, GameObject> createGetHitPoints(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<GameObject>)expr, (a) -> (double) a.getHealth());
	}

	@Override
	public Expression<Tile> createGetTile(Expression<?> x, Expression<?> y, SourceLocation sourceLocation) {
		return (globals, callStack) -> {
			World world = callStack.getProgram().getGameObject().getWorld();
			Vector<Integer> pixelPosition = new Vector<>(((Expression<Double>) x).evaluate(globals, callStack).intValue(),
					((Expression<Double>) y).evaluate(globals, callStack).intValue());
			return new Tile(world.getTileContainingPixel(pixelPosition), world.getTileSize(), world.getTileTypeOfPixel(pixelPosition));
		};
	}

	@Override
	public Expression<Collidable> createSearchObject(Expression<?> direction, SourceLocation sourceLocation) {
		return (globals, callStack) -> {
			
			GameObject gameObject = callStack.getProgram().getGameObject();
			World world = gameObject.getWorld();
			
			Vector<Integer> bottomLeft = new Vector<>(0, 0);
			Vector<Integer> size = new Vector<>(0, 0);
			
			switch (((Expression<Direction>) direction).evaluate(globals, callStack)) {
			case LEFT:
				bottomLeft = new Vector<>(0, gameObject.getPositionInPixels().y);
				size = new Vector<>(gameObject.getPositionInPixels().x, gameObject.getSizeInPixels().y);
				break;
				
			case RIGHT:
				bottomLeft = new Vector<>(gameObject.getTopRightPixel().x, gameObject.getPositionInPixels().y);
				size = new Vector<>(world.getSizeInPixels().x - gameObject.getTopRightPixel().x, gameObject.getSizeInPixels().y);
				break;
				
			case UP:
				bottomLeft = new Vector<>(gameObject.getPositionInPixels().x, gameObject.getTopRightPixel().y);
				size = new Vector<>(gameObject.getSizeInPixels().x, world.getSizeInPixels().y - gameObject.getTopRightPixel().y);
				break;
				
			case DOWN:
				bottomLeft = new Vector<>(gameObject.getPositionInPixels().x, 0);
				size = new Vector<>(gameObject.getSizeInPixels().x, gameObject.getPositionInPixels().y);
				break;

			default:
				break;
			}
			
			Set<Collidable> collidables = world.getCollidablesInRectangle(bottomLeft, size);
			
			return collidables.stream().reduce(null, (a, b) -> {
				if (a == null) return b;
				if (Utilities.distanceBetween(a.getCenterInPixels(), gameObject.getCenterInPixels()) <
						Utilities.distanceBetween(b.getCenterInPixels(), gameObject.getCenterInPixels())) {
					return a;
				} else {
					return b;
				}
			});
		};
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsMazub(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Mazub);
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsShark(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Shark);
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsSlime(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Slime);
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsPlant(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Plant);
	}

	@Override
	public UnaryOperation<Boolean, GameObject> createIsDead(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, GameObject>((Expression<GameObject>)expr, (a) -> a.isAlive());
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsTerrain(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Tile);
	}

	@Override
	public UnaryOperation<Boolean, Collidable> createIsPassable(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Collidable>((Expression<Collidable>)expr, (a) -> a.isPassable());
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsWater(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> (a instanceof Tile) && ((Tile)a).getType() == TileType.WATER);
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsMagma(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> (a instanceof Tile) && ((Tile)a).getType() == TileType.MAGMA);
	}

	@Override
	public UnaryOperation<Boolean, Object> createIsAir(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> (a instanceof Tile) && ((Tile)a).getType() == TileType.AIR);
	}

	@Override
	public BinaryOperation<Boolean, GameObject, Direction> createIsMoving(Expression<?> expr, Expression<?> direction, SourceLocation sourceLocation) {
		return new BinaryOperation<>((Expression<GameObject>) expr, (Expression<Direction>) direction,
				(a, b) -> a.isMoving(b));
	}

	@Override
	public UnaryOperation<Boolean, Mazub> createIsDucking(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Mazub>) expr, (a) -> a.isDucking());
	}

	@Override
	public UnaryOperation<Boolean, GameObject> createIsJumping(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<GameObject>) expr, (a) -> !a.onGround());
	}

	@Override
	public Assignment createAssignment(String variableName, Object variableType, Expression<?> value,
			SourceLocation sourceLocation) {
		return new Assignment(variableName, (Expression<Object>)value);
	}

	@Override
	public WhileLoop createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileLoop((Expression<Boolean>) condition, body);
	}

	@Override
	public ForEachLoop createForEach(
			String variableName,
			Kind variableKind,
			Expression<?> where,
			Expression<?> sort,
			SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new ForEachLoop(variableKind, variableName, (Expression<Boolean>) where, (Expression<Double>) sort, sortDirection, body);
	}

	@Override
	public Break createBreak(SourceLocation sourceLocation) {
		return new Break();
	}

	@Override
	public If createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		return new If((Expression<Boolean>) condition, ifBody, elseBody);
	}

	@Override
	public SimpleStatement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				System.out.println(value.evaluate(globals, callStack));
			}
		};
	}

	@Override
	public SimpleStatement createStartRun(Expression<?> direction, SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				RunProgrammable object = (RunProgrammable) callStack.getProgram().getGameObject();
				object.startRun(((Expression<Direction>) direction).evaluate(globals, this.getOwnCallStack(callStack)).getVectorValue().x);
			}
		};
	}

	@Override
	public SimpleStatement createStopRun(Expression<?> direction, SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				RunProgrammable object = (RunProgrammable) callStack.getProgram().getGameObject();
				object.stopRun();
			}
		};
	}

	@Override
	public SimpleStatement createStartJump(SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				JumpProgrammable object = (JumpProgrammable) callStack.getProgram().getGameObject();
				object.startJump();
			}
		};
	}

	@Override
	public SimpleStatement createStopJump(SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				JumpProgrammable object = (JumpProgrammable) callStack.getProgram().getGameObject();
				object.stopJump();
			}
		};
	}

	@Override
	public SimpleStatement createStartDuck(SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				DuckProgrammable object = (DuckProgrammable) callStack.getProgram().getGameObject();
				object.startDuck();
			}
		};
	}

	@Override
	public SimpleStatement createStopDuck(SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				DuckProgrammable object = (DuckProgrammable) callStack.getProgram().getGameObject();
				object.stopDuck();
			}
		};
	}

	@Override
	public Wait createWait(Expression<?> duration, SourceLocation sourceLocation) {
		return new Wait((Expression<Double>) duration);
	}

	@Override
	public Wait createSkip(SourceLocation sourceLocation) {
		return new Wait(new Value<>(Statement.defaultTime));
	}

	@Override
	public Sequence createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		return new Sequence(statements);
	}

	@Override
	public Double getDoubleType() {
		return new Double(0);
	}

	@Override
	public Boolean getBoolType() {
		return new Boolean(true);
	}

	@Override
	public GameObject getGameObjectType() {
		return null;
	}

	@Override
	public Direction getDirectionType() {
		return Direction.DOWN;
	}

	@Override
	public LanguageProgram createProgram(Statement mainStatement, Map<String, Object> globalVariables) {
		return new LanguageProgram(mainStatement, globalVariables);
	}

}
