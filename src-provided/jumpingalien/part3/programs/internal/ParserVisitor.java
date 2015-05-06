package jumpingalien.part3.programs.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;

import jumpingalien.part3.programs.IProgramFactory;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.internal.generated.JumpingAlienProgBaseVisitor;
import jumpingalien.part3.programs.internal.generated.JumpingAlienProgLexer;
import jumpingalien.part3.programs.internal.generated.JumpingAlienProgParser.*;

public class ParserVisitor<E, S, T> extends JumpingAlienProgBaseVisitor<Void> {

	private class ExpressionVisitor extends JumpingAlienProgBaseVisitor<E> {

		@Override
		public E visitVarExpr(VarExprContext ctx) {
			String name = ctx.variableName.getText();
			T type = globals.getOrDefault(name, null);
			return factory
					.createReadVariable(name, type, toSourceLocation(ctx));
		}

		@Override
		public E visitDoubleLiteral(DoubleLiteralContext ctx) {
			return factory.createDoubleConstant(
					Double.parseDouble(ctx.getText()), toSourceLocation(ctx));
		}

		@Override
		public E visitIntLiteral(IntLiteralContext ctx) {
			return factory.createDoubleConstant(
					Integer.parseInt(ctx.getText()), toSourceLocation(ctx));
		}

		@Override
		public E visitParenExpr(ParenExprContext ctx) {
			return visit(ctx.expr);
		}

		@Override
		public E visitBoolLiteral(BoolLiteralContext ctx) {
			switch (ctx.getStart().getType()) {
			case JumpingAlienProgLexer.BOOL_TRUE:
				return factory.createTrue(toSourceLocation(ctx));
			case JumpingAlienProgLexer.BOOL_FALSE:
				return factory.createFalse(toSourceLocation(ctx));
			}
			throw new IllegalArgumentException("Error while visiting "
					+ ctx.getText());
		}

		@Override
		public E visitNullLiteral(NullLiteralContext ctx) {
			return factory.createNull(toSourceLocation(ctx));
		}

		@Override
		public E visitSelfLiteral(SelfLiteralContext ctx) {
			return factory.createSelf(toSourceLocation(ctx));
		}

		@Override
		public E visitDirectionLiteral(DirectionLiteralContext ctx) {
			Direction direction = toDirection(ctx.directionLiteral);
			return factory.createDirectionConstant(direction,
					toSourceLocation(ctx));
		}

		@Override
		public E visitAddExpr(AddExprContext ctx) {
			E left = visit(ctx.left);
			E right = visit(ctx.right);
			switch (ctx.op.getType()) {
			case JumpingAlienProgLexer.PLUS:
				return factory.createAddition(left, right,
						toSourceLocation(ctx));
			case JumpingAlienProgLexer.MINUS:
				return factory.createSubtraction(left, right,
						toSourceLocation(ctx));
			}
			throw new IllegalArgumentException("Error while visiting "
					+ ctx.getText());
		}

		@Override
		public E visitMultExpr(MultExprContext ctx) {
			E left = visit(ctx.left);
			E right = visit(ctx.right);
			switch (ctx.op.getType()) {
			case JumpingAlienProgLexer.TIMES:
				return factory.createMultiplication(left, right,
						toSourceLocation(ctx));
			case JumpingAlienProgLexer.DIV:
				return factory.createDivision(left, right,
						toSourceLocation(ctx));
			}
			throw new IllegalArgumentException("Error while visiting "
					+ ctx.getText());
		}

		@Override
		public E visitSqrtExpr(SqrtExprContext ctx) {
			E expr = visit(ctx.expr);
			return factory.createSqrt(expr, toSourceLocation(ctx));
		}

		@Override
		public E visitRandomExpr(RandomExprContext ctx) {
			E maxValue = visit(ctx.maxValue);
			return factory.createRandom(maxValue, toSourceLocation(ctx));
		}

		@Override
		public E visitAndExpr(AndExprContext ctx) {
			E left = visit(ctx.left);
			E right = visit(ctx.right);
			return factory.createAnd(left, right, toSourceLocation(ctx));
		}

		@Override
		public E visitOrExpr(OrExprContext ctx) {
			E left = visit(ctx.left);
			E right = visit(ctx.right);
			return factory.createOr(left, right, toSourceLocation(ctx));
		}

		@Override
		public E visitNotExpr(NotExprContext ctx) {
			return factory.createNot(visit(ctx.expr), toSourceLocation(ctx));
		}

		@Override
		public E visitCmpExpr(CmpExprContext ctx) {
			E left = visit(ctx.left);
			E right = visit(ctx.right);
			switch (ctx.op.getType()) {
			case JumpingAlienProgLexer.LT:
				return factory.createLessThan(left, right,
						toSourceLocation(ctx));
			case JumpingAlienProgLexer.LTE:
				return factory.createLessThanOrEqualTo(left, right,
						toSourceLocation(ctx));
			case JumpingAlienProgLexer.GT:
				return factory.createGreaterThan(left, right,
						toSourceLocation(ctx));
			case JumpingAlienProgLexer.GTE:
				return factory.createGreaterThanOrEqualTo(left, right,
						toSourceLocation(ctx));
			case JumpingAlienProgLexer.EQ:
				return factory.createEquals(left, right, toSourceLocation(ctx));
			case JumpingAlienProgLexer.NEQ:
				return factory.createNotEquals(left, right,
						toSourceLocation(ctx));
			}
			throw new IllegalArgumentException("Error while visiting "
					+ ctx.getText());
		}

		@Override
		public E visitGetExpr(GetExprContext ctx) {
			E obj = visit(ctx.object);
			switch (ctx.attr.getType()) {
			case JumpingAlienProgLexer.GETX:
				return factory.createGetX(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.GETY:
				return factory.createGetY(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.GETWIDTH:
				return factory.createGetWidth(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.GETHEIGHT:
				return factory.createGetHeight(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.GETHP:
				return factory.createGetHitPoints(obj, toSourceLocation(ctx));
			}
			throw new IllegalArgumentException("Error while visiting "
					+ ctx.getText());
		}

		@Override
		public E visitSearchObjExpr(SearchObjExprContext ctx) {
			E direction = visit(ctx.searchDirection);
			return factory.createSearchObject(direction, toSourceLocation(ctx));
		}

		@Override
		public E visitIsExpr(IsExprContext ctx) {
			E obj = visit(ctx.obj);
			switch (ctx.test.getType()) {
			case JumpingAlienProgLexer.ISMAZUB:
				return factory.createIsMazub(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISSHARK:
				return factory.createIsShark(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISSLIME:
				return factory.createIsSlime(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISPLANT:
				return factory.createIsPlant(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISDEAD:
				return factory.createIsDead(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISTERRAIN:
				return factory.createIsTerrain(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISPASSABLE:
				return factory.createIsPassable(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISWATER:
				return factory.createIsWater(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISMAGMA:
				return factory.createIsMagma(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISAIR:
				return factory.createIsAir(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISDUCKING:
				return factory.createIsDucking(obj, toSourceLocation(ctx));
			case JumpingAlienProgLexer.ISJUMPING:
				return factory.createIsJumping(obj, toSourceLocation(ctx));
			default:
				throw new IllegalArgumentException(
						"Unrecognized is* expression: " + ctx.getText());
			}
		}

		@Override
		public E visitIsMoving(IsMovingContext ctx) {
			E obj = visit(ctx.obj);
			E direction = visit(ctx.isMovingDirection);
			return factory
					.createIsMoving(obj, direction, toSourceLocation(ctx));
		}
		
		 @Override
		public E visitGetTileExpr(GetTileExprContext ctx) {
			 E x = visit(ctx.xpos);
			 E y = visit(ctx.ypos);
			 return factory.createGetTile(x, y, toSourceLocation(ctx));
		}

	}

	private class StatementVisitor extends JumpingAlienProgBaseVisitor<S> {

		@Override
		public S visitAssignStatement(AssignStatementContext ctx) {
			String variable = ctx.variableName.getText();
			T type = globals.getOrDefault(variable, null);
			E value = expressions.visit(ctx.value);
			return factory.createAssignment(variable, type, value,
					toSourceLocation(ctx));
		}

		@Override
		public S visitWhileStatement(WhileStatementContext ctx) {
			E condition = expressions.visit(ctx.condition);
			S body = visit(ctx.body);
			return factory.createWhile(condition, body, toSourceLocation(ctx));
		}

		@Override
		public S visitForeachStatement(ForeachStatementContext ctx) {
			Kind kind = toKind(ctx.variableKind);
			String variableName = ctx.variableName.getText();
			S body = visit(ctx.body);
			E where = null;
			if (ctx.whereExpr != null) {
				where = expressions.visit(ctx.whereExpr);
			}
			E sort = null;
			SortDirection sortDir = null;
			if (ctx.sortExpr != null) {
				sort = expressions.visit(ctx.sortExpr);
				sortDir = toSortDir(ctx.sortDir);
			}
			return factory.createForEach(variableName, kind, where, sort,
					sortDir, body, toSourceLocation(ctx));
		}

		@Override
		public S visitBreakStatement(BreakStatementContext ctx) {
			return factory.createBreak(toSourceLocation(ctx));
		}

		@Override
		public S visitIfStatement(IfStatementContext ctx) {
			E condition = expressions.visit(ctx.condition);
			S ifbody = visit(ctx.ifbody);
			S elsebody = null;
			if (ctx.elsebody != null) {
				elsebody = visit(ctx.elsebody);
			}
			return factory.createIf(condition, ifbody, elsebody,
					toSourceLocation(ctx));
		}

		@Override
		public S visitPrintStatement(PrintStatementContext ctx) {
			E value = expressions.visit(ctx.value);
			return factory.createPrint(value, toSourceLocation(ctx));
		}

		@Override
		public S visitStartRunStatement(StartRunStatementContext ctx) {
			E direction = expressions.visit(ctx.runDirection);
			return factory.createStartRun(direction, toSourceLocation(ctx));
		}

		@Override
		public S visitStopRunStatement(StopRunStatementContext ctx) {
			E direction = expressions.visit(ctx.runDirection);
			return factory.createStopRun(direction, toSourceLocation(ctx));
		}

		@Override
		public S visitStartJumpStatement(StartJumpStatementContext ctx) {
			return factory.createStartJump(toSourceLocation(ctx));
		}

		@Override
		public S visitStopJumpStatement(StopJumpStatementContext ctx) {
			return factory.createStopJump(toSourceLocation(ctx));
		}

		@Override
		public S visitStartDuckStatement(StartDuckStatementContext ctx) {
			return factory.createStartDuck(toSourceLocation(ctx));
		}

		@Override
		public S visitStopDuckStatement(StopDuckStatementContext ctx) {
			return factory.createStopDuck(toSourceLocation(ctx));
		}

		@Override
		public S visitWaitStatement(WaitStatementContext ctx) {
			E duration = expressions.visit(ctx.duration);
			return factory.createWait(duration, toSourceLocation(ctx));
		}

		@Override
		public S visitSkipStatement(SkipStatementContext ctx) {
			return factory.createSkip(toSourceLocation(ctx));
		}

		@Override
		public S visitStatement(StatementContext ctx) {
			S first = visit(ctx.first);

			if (ctx.rest.isEmpty()) {
				return first;
			}

			List<S> statements = new ArrayList<S>();
			statements.add(first);
			for (SingleStatementContext nextCtx : ctx.rest) {
				statements.add(visit(nextCtx));
			}

			return factory.createSequence(statements, toSourceLocation(ctx));
		}

	}

	private class TypeVisitor extends JumpingAlienProgBaseVisitor<T> {

		@Override
		public T visitDouble_type(Double_typeContext ctx) {
			return factory.getDoubleType();
		}

		@Override
		public T visitBool_type(Bool_typeContext ctx) {
			return factory.getBoolType();
		}

		@Override
		public T visitObject_type(Object_typeContext ctx) {
			return factory.getGameObjectType();
		}

		@Override
		public T visitDirection_type(Direction_typeContext ctx) {
			return factory.getDirectionType();
		}

	}

	private final IProgramFactory<E, S, T, ?> factory;

	private TypeVisitor types;
	private StatementVisitor statements;
	private ExpressionVisitor expressions;

	private List<S> variableInitialisationStatements = new ArrayList<>();

	private S mainStatement;
	private Map<String, T> globals = new HashMap<>();

	public ParserVisitor(IProgramFactory<E, S, T, ?> factory) {
		this.factory = factory;
		this.expressions = new ExpressionVisitor();
		this.statements = new StatementVisitor();
		this.types = new TypeVisitor();
	}

	public S getMainStatement() {
		return mainStatement;
	}

	public Map<String, T> getGlobals() {
		return globals;
	}

	@Override
	public Void visitProgram(ProgramContext ctx) {
		for (DeclarationContext decl : ctx.declaration()) {
			visit(decl);
		}
		S mainStatement = statements.visit(ctx.main);

		if (variableInitialisationStatements.isEmpty()) {
			this.mainStatement = mainStatement;
		} else {
			List<S> statements = new ArrayList<>(
					variableInitialisationStatements);
			statements.add(mainStatement);
			this.mainStatement = factory.createSequence(statements,
					toSourceLocation(ctx));
		}

		return null;
	}

	@Override
	public Void visitDeclaration(DeclarationContext ctx) {
		String name = ctx.variableName.getText();

		T type = types.visit(ctx.variableType);
		if (globals.containsKey(name)) {
			throw new IllegalStateException("Error on line "
					+ ctx.start.getLine() + ": a variable with name '" + name
					+ "' was already declared.");
		}
		globals.put(name, type);

		if (ctx.initialValue != null) {
			E value = expressions.visit(ctx.initialValue);
			variableInitialisationStatements.add(factory.createAssignment(name,
					type, value, toSourceLocation(ctx)));
		}
		return null;
	}

	private Kind toKind(KindContext kindCtx) {
		switch (kindCtx.getStart().getType()) {
		case JumpingAlienProgLexer.KIND_MAZUB:
			return Kind.MAZUB;
		case JumpingAlienProgLexer.KIND_BUZAM:
			return Kind.BUZAM;
		case JumpingAlienProgLexer.KIND_PLANT:
			return Kind.PLANT;
		case JumpingAlienProgLexer.KIND_SHARK:
			return Kind.SHARK;
		case JumpingAlienProgLexer.KIND_SLIME:
			return Kind.SLIME;
		case JumpingAlienProgLexer.KIND_TERRAIN:
			return Kind.TERRAIN;
		case JumpingAlienProgLexer.KIND_ANY:
			return Kind.ANY;
		default:
			throw new IllegalArgumentException("Unknown direction: "
					+ kindCtx.getText());
		}
	}

	private Direction toDirection(DirectionContext directionCtx) {
		switch (directionCtx.getStart().getType()) {
		case JumpingAlienProgLexer.DIRECTION_LEFT:
			return Direction.LEFT;
		case JumpingAlienProgLexer.DIRECTION_RIGHT:
			return Direction.RIGHT;
		case JumpingAlienProgLexer.DIRECTION_UP:
			return Direction.UP;
		case JumpingAlienProgLexer.DIRECTION_DOWN:
			return Direction.DOWN;
		default:
			throw new IllegalArgumentException("Unknown direction: "
					+ directionCtx.getText());
		}
	}

	private SourceLocation toSourceLocation(ParserRuleContext ctx) {
		int line = ctx.getStart().getLine();
		int column = ctx.getStart().getCharPositionInLine();
		return new SourceLocation(line, column);
	}

	private SortDirection toSortDir(SortDirectionContext sortDir) {
		switch (sortDir.start.getType()) {
		case JumpingAlienProgLexer.ASCENDING:
			return SortDirection.ASCENDING;
		case JumpingAlienProgLexer.DESCENDING:
			return SortDirection.DESCENDING;
		default:
			throw new IllegalArgumentException("Unknown sort direction: "
					+ sortDir.getText());
		}
	}

}
