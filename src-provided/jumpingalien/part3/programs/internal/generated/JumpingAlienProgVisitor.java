// Generated from JumpingAlienProg.g by ANTLR 4.5
package jumpingalien.part3.programs.internal.generated;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JumpingAlienProgParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JumpingAlienProgVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(JumpingAlienProgParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(JumpingAlienProgParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(JumpingAlienProgParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#singleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleStatement(JumpingAlienProgParser.SingleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(JumpingAlienProgParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(JumpingAlienProgParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#foreachStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForeachStatement(JumpingAlienProgParser.ForeachStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#breakStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakStatement(JumpingAlienProgParser.BreakStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(JumpingAlienProgParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#printStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStatement(JumpingAlienProgParser.PrintStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#actionStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActionStatement(JumpingAlienProgParser.ActionStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#startRunStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartRunStatement(JumpingAlienProgParser.StartRunStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#stopRunStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopRunStatement(JumpingAlienProgParser.StopRunStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#startJumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartJumpStatement(JumpingAlienProgParser.StartJumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#stopJumpStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopJumpStatement(JumpingAlienProgParser.StopJumpStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#startDuckStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStartDuckStatement(JumpingAlienProgParser.StartDuckStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#stopDuckStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStopDuckStatement(JumpingAlienProgParser.StopDuckStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#waitStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWaitStatement(JumpingAlienProgParser.WaitStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#skipStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkipStatement(JumpingAlienProgParser.SkipStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code randomExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRandomExpr(JumpingAlienProgParser.RandomExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sqrtExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSqrtExpr(JumpingAlienProgParser.SqrtExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrExpr(JumpingAlienProgParser.OrExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(JumpingAlienProgParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(JumpingAlienProgParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code cmpExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCmpExpr(JumpingAlienProgParser.CmpExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(JumpingAlienProgParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(JumpingAlienProgParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetExpr(JumpingAlienProgParser.GetExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code getTileExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGetTileExpr(JumpingAlienProgParser.GetTileExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(JumpingAlienProgParser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteralExpr(JumpingAlienProgParser.LiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isMoving}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsMoving(JumpingAlienProgParser.IsMovingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code searchObjExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearchObjExpr(JumpingAlienProgParser.SearchObjExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsExpr(JumpingAlienProgParser.IsExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndExpr(JumpingAlienProgParser.AndExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntLiteral(JumpingAlienProgParser.IntLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(JumpingAlienProgParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolLiteral(JumpingAlienProgParser.BoolLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code directionLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectionLiteral(JumpingAlienProgParser.DirectionLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNullLiteral(JumpingAlienProgParser.NullLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code selfLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelfLiteral(JumpingAlienProgParser.SelfLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(JumpingAlienProgParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#double_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDouble_type(JumpingAlienProgParser.Double_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#bool_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_type(JumpingAlienProgParser.Bool_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#object_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObject_type(JumpingAlienProgParser.Object_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#direction_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirection_type(JumpingAlienProgParser.Direction_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#kind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKind(JumpingAlienProgParser.KindContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#sortDirection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortDirection(JumpingAlienProgParser.SortDirectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JumpingAlienProgParser#direction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirection(JumpingAlienProgParser.DirectionContext ctx);
}
