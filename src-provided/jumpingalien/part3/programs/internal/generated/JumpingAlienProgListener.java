// Generated from JumpingAlienProg.g by ANTLR 4.5
package jumpingalien.part3.programs.internal.generated;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JumpingAlienProgParser}.
 */
public interface JumpingAlienProgListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(JumpingAlienProgParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(JumpingAlienProgParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(JumpingAlienProgParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(JumpingAlienProgParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JumpingAlienProgParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JumpingAlienProgParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#singleStatement}.
	 * @param ctx the parse tree
	 */
	void enterSingleStatement(JumpingAlienProgParser.SingleStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#singleStatement}.
	 * @param ctx the parse tree
	 */
	void exitSingleStatement(JumpingAlienProgParser.SingleStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(JumpingAlienProgParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(JumpingAlienProgParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(JumpingAlienProgParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(JumpingAlienProgParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#foreachStatement}.
	 * @param ctx the parse tree
	 */
	void enterForeachStatement(JumpingAlienProgParser.ForeachStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#foreachStatement}.
	 * @param ctx the parse tree
	 */
	void exitForeachStatement(JumpingAlienProgParser.ForeachStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(JumpingAlienProgParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(JumpingAlienProgParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(JumpingAlienProgParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(JumpingAlienProgParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void enterPrintStatement(JumpingAlienProgParser.PrintStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#printStatement}.
	 * @param ctx the parse tree
	 */
	void exitPrintStatement(JumpingAlienProgParser.PrintStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void enterActionStatement(JumpingAlienProgParser.ActionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#actionStatement}.
	 * @param ctx the parse tree
	 */
	void exitActionStatement(JumpingAlienProgParser.ActionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#startRunStatement}.
	 * @param ctx the parse tree
	 */
	void enterStartRunStatement(JumpingAlienProgParser.StartRunStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#startRunStatement}.
	 * @param ctx the parse tree
	 */
	void exitStartRunStatement(JumpingAlienProgParser.StartRunStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#stopRunStatement}.
	 * @param ctx the parse tree
	 */
	void enterStopRunStatement(JumpingAlienProgParser.StopRunStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#stopRunStatement}.
	 * @param ctx the parse tree
	 */
	void exitStopRunStatement(JumpingAlienProgParser.StopRunStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#startJumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterStartJumpStatement(JumpingAlienProgParser.StartJumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#startJumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitStartJumpStatement(JumpingAlienProgParser.StartJumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#stopJumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterStopJumpStatement(JumpingAlienProgParser.StopJumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#stopJumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitStopJumpStatement(JumpingAlienProgParser.StopJumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#startDuckStatement}.
	 * @param ctx the parse tree
	 */
	void enterStartDuckStatement(JumpingAlienProgParser.StartDuckStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#startDuckStatement}.
	 * @param ctx the parse tree
	 */
	void exitStartDuckStatement(JumpingAlienProgParser.StartDuckStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#stopDuckStatement}.
	 * @param ctx the parse tree
	 */
	void enterStopDuckStatement(JumpingAlienProgParser.StopDuckStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#stopDuckStatement}.
	 * @param ctx the parse tree
	 */
	void exitStopDuckStatement(JumpingAlienProgParser.StopDuckStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#waitStatement}.
	 * @param ctx the parse tree
	 */
	void enterWaitStatement(JumpingAlienProgParser.WaitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#waitStatement}.
	 * @param ctx the parse tree
	 */
	void exitWaitStatement(JumpingAlienProgParser.WaitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#skipStatement}.
	 * @param ctx the parse tree
	 */
	void enterSkipStatement(JumpingAlienProgParser.SkipStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#skipStatement}.
	 * @param ctx the parse tree
	 */
	void exitSkipStatement(JumpingAlienProgParser.SkipStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code randomExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRandomExpr(JumpingAlienProgParser.RandomExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code randomExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRandomExpr(JumpingAlienProgParser.RandomExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sqrtExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSqrtExpr(JumpingAlienProgParser.SqrtExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sqrtExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSqrtExpr(JumpingAlienProgParser.SqrtExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOrExpr(JumpingAlienProgParser.OrExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code orExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOrExpr(JumpingAlienProgParser.OrExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMultExpr(JumpingAlienProgParser.MultExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMultExpr(JumpingAlienProgParser.MultExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(JumpingAlienProgParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(JumpingAlienProgParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code cmpExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterCmpExpr(JumpingAlienProgParser.CmpExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code cmpExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitCmpExpr(JumpingAlienProgParser.CmpExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(JumpingAlienProgParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(JumpingAlienProgParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNotExpr(JumpingAlienProgParser.NotExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNotExpr(JumpingAlienProgParser.NotExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetExpr(JumpingAlienProgParser.GetExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetExpr(JumpingAlienProgParser.GetExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code getTileExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterGetTileExpr(JumpingAlienProgParser.GetTileExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code getTileExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitGetTileExpr(JumpingAlienProgParser.GetTileExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(JumpingAlienProgParser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(JumpingAlienProgParser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterLiteralExpr(JumpingAlienProgParser.LiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literalExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitLiteralExpr(JumpingAlienProgParser.LiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isMoving}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsMoving(JumpingAlienProgParser.IsMovingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isMoving}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsMoving(JumpingAlienProgParser.IsMovingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code searchObjExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSearchObjExpr(JumpingAlienProgParser.SearchObjExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code searchObjExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSearchObjExpr(JumpingAlienProgParser.SearchObjExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code isExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIsExpr(JumpingAlienProgParser.IsExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code isExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIsExpr(JumpingAlienProgParser.IsExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAndExpr(JumpingAlienProgParser.AndExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code andExpr}
	 * labeled alternative in {@link JumpingAlienProgParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAndExpr(JumpingAlienProgParser.AndExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(JumpingAlienProgParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code intLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(JumpingAlienProgParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(JumpingAlienProgParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code doubleLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(JumpingAlienProgParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterBoolLiteral(JumpingAlienProgParser.BoolLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitBoolLiteral(JumpingAlienProgParser.BoolLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code directionLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterDirectionLiteral(JumpingAlienProgParser.DirectionLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directionLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitDirectionLiteral(JumpingAlienProgParser.DirectionLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(JumpingAlienProgParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code nullLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(JumpingAlienProgParser.NullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code selfLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterSelfLiteral(JumpingAlienProgParser.SelfLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code selfLiteral}
	 * labeled alternative in {@link JumpingAlienProgParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitSelfLiteral(JumpingAlienProgParser.SelfLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(JumpingAlienProgParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(JumpingAlienProgParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#double_type}.
	 * @param ctx the parse tree
	 */
	void enterDouble_type(JumpingAlienProgParser.Double_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#double_type}.
	 * @param ctx the parse tree
	 */
	void exitDouble_type(JumpingAlienProgParser.Double_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#bool_type}.
	 * @param ctx the parse tree
	 */
	void enterBool_type(JumpingAlienProgParser.Bool_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#bool_type}.
	 * @param ctx the parse tree
	 */
	void exitBool_type(JumpingAlienProgParser.Bool_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#object_type}.
	 * @param ctx the parse tree
	 */
	void enterObject_type(JumpingAlienProgParser.Object_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#object_type}.
	 * @param ctx the parse tree
	 */
	void exitObject_type(JumpingAlienProgParser.Object_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#direction_type}.
	 * @param ctx the parse tree
	 */
	void enterDirection_type(JumpingAlienProgParser.Direction_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#direction_type}.
	 * @param ctx the parse tree
	 */
	void exitDirection_type(JumpingAlienProgParser.Direction_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#kind}.
	 * @param ctx the parse tree
	 */
	void enterKind(JumpingAlienProgParser.KindContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#kind}.
	 * @param ctx the parse tree
	 */
	void exitKind(JumpingAlienProgParser.KindContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#sortDirection}.
	 * @param ctx the parse tree
	 */
	void enterSortDirection(JumpingAlienProgParser.SortDirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#sortDirection}.
	 * @param ctx the parse tree
	 */
	void exitSortDirection(JumpingAlienProgParser.SortDirectionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JumpingAlienProgParser#direction}.
	 * @param ctx the parse tree
	 */
	void enterDirection(JumpingAlienProgParser.DirectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JumpingAlienProgParser#direction}.
	 * @param ctx the parse tree
	 */
	void exitDirection(JumpingAlienProgParser.DirectionContext ctx);
}
