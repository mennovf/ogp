package jumpingalien.model.program;

import java.util.List;
import java.util.Map;

import jumpingalien.model.Collidable;
import jumpingalien.model.gameobject.GameObject;
import jumpingalien.model.gameobject.Mazub;
import jumpingalien.model.gameobject.Plant;
import jumpingalien.model.gameobject.Shark;
import jumpingalien.model.gameobject.Slime;
import jumpingalien.model.program.expression.*;
import jumpingalien.model.program.statement.*;
import jumpingalien.model.world.Tile;
import jumpingalien.model.world.TileType;
import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.SourceLocation;

@SuppressWarnings("unchecked")
public class ProgramFactory implements IProgramFactory<Expression<?>, Statement, Object, Program> {
	
	@Override
	public Expression<Object> createReadVariable(String variableName, Object variableType,
			SourceLocation sourceLocation) {
		return new Variable<Object>(variableName);
	}

	@Override
	public Expression<Double> createDoubleConstant(double value, SourceLocation sourceLocation) {
		return new Value<>(value);
	}

	@Override
	public Expression<Boolean> createTrue(SourceLocation sourceLocation) {
		return new Value<>(true);
	}

	@Override
	public Expression<Boolean> createFalse(SourceLocation sourceLocation) {
		return new Value<>(false);
	}

	@Override
	public Expression<GameObject> createNull(SourceLocation sourceLocation) {
		return new Value<>(null);
	}

	@Override
	public Expression<Object> createSelf(SourceLocation sourceLocation) {
		return new Variable<Object>("self");
	}

	@Override
	public Expression<Direction> createDirectionConstant(
			Direction value,
			SourceLocation sourceLocation) {
		return new Value<>(value);
	}

	@Override
	public Expression<Double> createAddition(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a + (Double)b);
	}

	@Override
	public Expression<Double> createSubtraction(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a - (Double)b);
	}

	@Override
	public Expression<Double> createMultiplication(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a * (Double)b);
	}

	@Override
	public Expression<Double> createDivision(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Double, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> (Double)a / (Double)b);
	}

	@Override
	public Expression<Double> createSqrt(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Double, Double>((Expression<Double>)expr, Math::sqrt);
	}

	@Override
	public Expression<Double> createRandom(Expression<?> maxValue, SourceLocation sourceLocation) {
		return new UnaryOperation<Double, Double>((Expression<Double>)maxValue, (a) -> Math.random() * a);
	}

	@Override
	public Expression<Boolean> createAnd(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Boolean, Boolean>((Expression<Boolean>)left, (Expression<Boolean>)right,
															  (a, b) -> a && b);
	}

	@Override
	public Expression<Boolean> createOr(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Boolean, Boolean>((Expression<Boolean>)left, (Expression<Boolean>)right,
															  (a, b) -> a || b);
	}

	@Override
	public Expression<Boolean> createNot(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Boolean>((Expression<Boolean>)expr, (a) -> !a);
	}

	@Override
	public Expression<Boolean> createLessThan(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a < b);
	}

	@Override
	public Expression<Boolean> createLessThanOrEqualTo(Expression<?> left, Expression<?> right,
			SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a <= b);
	}

	@Override
	public Expression<Boolean> createGreaterThan(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a > b);
	}

	@Override
	public Expression<Boolean> createGreaterThanOrEqualTo(Expression<?> left, Expression<?> right,
			SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Double, Double>((Expression<Double>)left, (Expression<Double>)right, (a, b) -> a >= b);
	}

	@Override
	public Expression<Boolean> createEquals(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Object, Object>((Expression<Object>)left, (Expression<Object>)right, (a, b) -> a.equals(b));
	}

	@Override
	public Expression<Boolean> createNotEquals(Expression<?> left, Expression<?> right, SourceLocation sourceLocation) {
		return new BinaryOperation<Boolean, Object, Object>((Expression<Object>)left, (Expression<Object>)right, (a, b) -> ! a.equals(b));
	}

	@Override
	public Expression<Double> createGetX(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getPositionInPixels().x);
	}

	@Override
	public Expression<Double> createGetY(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getPositionInPixels().y);
	}

	@Override
	public Expression<Double> createGetWidth(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getSizeInPixels().x);
	}

	@Override
	public Expression<Double> createGetHeight(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Collidable>) expr, (a) -> (double) a.getSizeInPixels().y);
	}

	@Override
	public Expression<Double> createGetHitPoints(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Double, GameObject>((Expression<GameObject>)expr, (a) -> (double) a.getHealth());
	}

	@Override
	public Expression<Tile> createGetTile(Expression<?> x, Expression<?> y, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Collidable> createSearchObject(Expression<?> direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression<Boolean> createIsMazub(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Mazub);
	}

	@Override
	public Expression<Boolean> createIsShark(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Shark);
	}

	@Override
	public Expression<Boolean> createIsSlime(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Slime);
	}

	@Override
	public Expression<Boolean> createIsPlant(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Plant);
	}

	@Override
	public Expression<Boolean> createIsDead(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, GameObject>((Expression<GameObject>)expr, (a) -> a.isAlive());
	}

	@Override
	public Expression<Boolean> createIsTerrain(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> a instanceof Tile);
	}

	@Override
	public Expression<Boolean> createIsPassable(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Collidable>((Expression<Collidable>)expr, (a) -> a.isPassable());
	}

	@Override
	public Expression<Boolean> createIsWater(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> (a instanceof Tile) && ((Tile)a).getType() == TileType.WATER);
	}

	@Override
	public Expression<Boolean> createIsMagma(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> (a instanceof Tile) && ((Tile)a).getType() == TileType.MAGMA);
	}

	@Override
	public Expression<Boolean> createIsAir(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<Boolean, Object>((Expression<Object>)expr, (a) -> (a instanceof Tile) && ((Tile)a).getType() == TileType.AIR);
	}

	@Override
	public Expression<Boolean> createIsMoving(Expression<?> expr, Expression<?> direction, SourceLocation sourceLocation) {
		return new BinaryOperation<>((Expression<GameObject>) expr, (Expression<Direction>) direction,
				(a, b) -> a.isMoving(b));
	}

	@Override
	public Expression<Boolean> createIsDucking(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<Mazub>) expr, (a) -> a.isDucking());
	}

	@Override
	public Expression<Boolean> createIsJumping(Expression<?> expr, SourceLocation sourceLocation) {
		return new UnaryOperation<>((Expression<GameObject>) expr, (a) -> !a.onGround());
	}

	@Override
	public Statement createAssignment(String variableName, Object variableType, Expression<?> value,
			SourceLocation sourceLocation) {
		return new Assignment(variableName, (Expression<Object>)value);
	}

	@Override
	public Statement createWhile(Expression<?> condition, Statement body, SourceLocation sourceLocation) {
		return new WhileLoop((Expression<Boolean>) condition, body);
	}

	@Override
	public Statement createForEach(
			String variableName,
			Kind variableKind,
			Expression<?> where,
			Expression<?> sort,
			SortDirection sortDirection,
			Statement body, SourceLocation sourceLocation) {
		return new ForEachLoop(variableKind, variableName, (Expression<Boolean>) where, (Expression<Double>) sort, sortDirection, body);
	}

	@Override
	public Statement createBreak(SourceLocation sourceLocation) {
		return new SimpleStatement() {
			
			@Override
			protected void run(Map<String, Object> globals, CallStack callStack) {
				callStack.pop().executeBreak(callStack);
			}
		};
	}

	@Override
	public Statement createIf(Expression<?> condition, Statement ifBody, Statement elseBody,
			SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createPrint(Expression<?> value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartRun(Expression<?> direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopRun(Expression<?> direction, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopJump(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStartDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createStopDuck(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createWait(Expression<?> duration, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSkip(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Statement createSequence(List<Statement> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
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
	public Program createProgram(Statement mainStatement, Map<String, Object> globalVariables) {
		return new Program(mainStatement, globalVariables);
	}

}
