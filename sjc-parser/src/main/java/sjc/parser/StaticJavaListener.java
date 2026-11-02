// Generated from sjc-parser/src/main/java/sjc/parser/StaticJava.g4 by ANTLR 4.13.1

package sjc.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link StaticJavaParser}.
 */
public interface StaticJavaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void enterCompilationUnit(StaticJavaParser.CompilationUnitContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#compilationUnit}.
	 * @param ctx the parse tree
	 */
	void exitCompilationUnit(StaticJavaParser.CompilationUnitContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#simpleClassDefinition}.
	 * @param ctx the parse tree
	 */
	void enterSimpleClassDefinition(StaticJavaParser.SimpleClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#simpleClassDefinition}.
	 * @param ctx the parse tree
	 */
	void exitSimpleClassDefinition(StaticJavaParser.SimpleClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#publicFieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterPublicFieldDeclaration(StaticJavaParser.PublicFieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#publicFieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitPublicFieldDeclaration(StaticJavaParser.PublicFieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(StaticJavaParser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#classDefinition}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(StaticJavaParser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMemberDeclaration(StaticJavaParser.MemberDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#memberDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMemberDeclaration(StaticJavaParser.MemberDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#mainMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMainMethodDeclaration(StaticJavaParser.MainMethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#mainMethodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMainMethodDeclaration(StaticJavaParser.MainMethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFieldDeclaration(StaticJavaParser.FieldDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#fieldDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFieldDeclaration(StaticJavaParser.FieldDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMethodDeclaration(StaticJavaParser.MethodDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#methodDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMethodDeclaration(StaticJavaParser.MethodDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(StaticJavaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(StaticJavaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BooleanType}
	 * labeled alternative in {@link StaticJavaParser#basicType}.
	 * @param ctx the parse tree
	 */
	void enterBooleanType(StaticJavaParser.BooleanTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BooleanType}
	 * labeled alternative in {@link StaticJavaParser#basicType}.
	 * @param ctx the parse tree
	 */
	void exitBooleanType(StaticJavaParser.BooleanTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link StaticJavaParser#basicType}.
	 * @param ctx the parse tree
	 */
	void enterIntType(StaticJavaParser.IntTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntType}
	 * labeled alternative in {@link StaticJavaParser#basicType}.
	 * @param ctx the parse tree
	 */
	void exitIntType(StaticJavaParser.IntTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VoidType}
	 * labeled alternative in {@link StaticJavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterVoidType(StaticJavaParser.VoidTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VoidType}
	 * labeled alternative in {@link StaticJavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitVoidType(StaticJavaParser.VoidTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NonVoidReturnType}
	 * labeled alternative in {@link StaticJavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void enterNonVoidReturnType(StaticJavaParser.NonVoidReturnTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NonVoidReturnType}
	 * labeled alternative in {@link StaticJavaParser#returnType}.
	 * @param ctx the parse tree
	 */
	void exitNonVoidReturnType(StaticJavaParser.NonVoidReturnTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(StaticJavaParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(StaticJavaParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(StaticJavaParser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(StaticJavaParser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void enterMethodBody(StaticJavaParser.MethodBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#methodBody}.
	 * @param ctx the parse tree
	 */
	void exitMethodBody(StaticJavaParser.MethodBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#localDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterLocalDeclaration(StaticJavaParser.LocalDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#localDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitLocalDeclaration(StaticJavaParser.LocalDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(StaticJavaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(StaticJavaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignStatement(StaticJavaParser.AssignStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#assignStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignStatement(StaticJavaParser.AssignStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#assign}.
	 * @param ctx the parse tree
	 */
	void enterAssign(StaticJavaParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#assign}.
	 * @param ctx the parse tree
	 */
	void exitAssign(StaticJavaParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdLHS}
	 * labeled alternative in {@link StaticJavaParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void enterIdLHS(StaticJavaParser.IdLHSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdLHS}
	 * labeled alternative in {@link StaticJavaParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void exitIdLHS(StaticJavaParser.IdLHSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FieldLHS}
	 * labeled alternative in {@link StaticJavaParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void enterFieldLHS(StaticJavaParser.FieldLHSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FieldLHS}
	 * labeled alternative in {@link StaticJavaParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void exitFieldLHS(StaticJavaParser.FieldLHSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArrayLHS}
	 * labeled alternative in {@link StaticJavaParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void enterArrayLHS(StaticJavaParser.ArrayLHSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArrayLHS}
	 * labeled alternative in {@link StaticJavaParser#leftHandSide}.
	 * @param ctx the parse tree
	 */
	void exitArrayLHS(StaticJavaParser.ArrayLHSContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(StaticJavaParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(StaticJavaParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(StaticJavaParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(StaticJavaParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(StaticJavaParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(StaticJavaParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#assigns}.
	 * @param ctx the parse tree
	 */
	void enterAssigns(StaticJavaParser.AssignsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#assigns}.
	 * @param ctx the parse tree
	 */
	void exitAssigns(StaticJavaParser.AssignsContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#incdecs}.
	 * @param ctx the parse tree
	 */
	void enterIncdecs(StaticJavaParser.IncdecsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#incdecs}.
	 * @param ctx the parse tree
	 */
	void exitIncdecs(StaticJavaParser.IncdecsContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#incdec}.
	 * @param ctx the parse tree
	 */
	void enterIncdec(StaticJavaParser.IncdecContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#incdec}.
	 * @param ctx the parse tree
	 */
	void exitIncdec(StaticJavaParser.IncdecContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileStatement(StaticJavaParser.DoWhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#doWhileStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileStatement(StaticJavaParser.DoWhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#incdecStatement}.
	 * @param ctx the parse tree
	 */
	void enterIncdecStatement(StaticJavaParser.IncdecStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#incdecStatement}.
	 * @param ctx the parse tree
	 */
	void exitIncdecStatement(StaticJavaParser.IncdecStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#invokeExpStatement}.
	 * @param ctx the parse tree
	 */
	void enterInvokeExpStatement(StaticJavaParser.InvokeExpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#invokeExpStatement}.
	 * @param ctx the parse tree
	 */
	void exitInvokeExpStatement(StaticJavaParser.InvokeExpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(StaticJavaParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(StaticJavaParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ShiftBinaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterShiftBinaryExp(StaticJavaParser.ShiftBinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ShiftBinaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitShiftBinaryExp(StaticJavaParser.ShiftBinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CondExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterCondExp(StaticJavaParser.CondExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CondExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitCondExp(StaticJavaParser.CondExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(StaticJavaParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(StaticJavaParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BinaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExp(StaticJavaParser.BinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BinaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExp(StaticJavaParser.BinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(StaticJavaParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryExp}
	 * labeled alternative in {@link StaticJavaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(StaticJavaParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LeftShiftOp}
	 * labeled alternative in {@link StaticJavaParser#shiftOp}.
	 * @param ctx the parse tree
	 */
	void enterLeftShiftOp(StaticJavaParser.LeftShiftOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LeftShiftOp}
	 * labeled alternative in {@link StaticJavaParser#shiftOp}.
	 * @param ctx the parse tree
	 */
	void exitLeftShiftOp(StaticJavaParser.LeftShiftOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RightShiftOp}
	 * labeled alternative in {@link StaticJavaParser#shiftOp}.
	 * @param ctx the parse tree
	 */
	void enterRightShiftOp(StaticJavaParser.RightShiftOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RightShiftOp}
	 * labeled alternative in {@link StaticJavaParser#shiftOp}.
	 * @param ctx the parse tree
	 */
	void exitRightShiftOp(StaticJavaParser.RightShiftOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code URightShiftOp}
	 * labeled alternative in {@link StaticJavaParser#shiftOp}.
	 * @param ctx the parse tree
	 */
	void enterURightShiftOp(StaticJavaParser.URightShiftOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code URightShiftOp}
	 * labeled alternative in {@link StaticJavaParser#shiftOp}.
	 * @param ctx the parse tree
	 */
	void exitURightShiftOp(StaticJavaParser.URightShiftOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void enterIntLiteral(StaticJavaParser.IntLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void exitIntLiteral(StaticJavaParser.IntLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code TrueLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void enterTrueLiteral(StaticJavaParser.TrueLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code TrueLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void exitTrueLiteral(StaticJavaParser.TrueLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FalseLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void enterFalseLiteral(StaticJavaParser.FalseLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FalseLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void exitFalseLiteral(StaticJavaParser.FalseLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void enterNullLiteral(StaticJavaParser.NullLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NullLiteral}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void exitNullLiteral(StaticJavaParser.NullLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrimaryPrefix}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryPrefix(StaticJavaParser.PrimaryPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrimaryPrefix}
	 * labeled alternative in {@link StaticJavaParser#pExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryPrefix(StaticJavaParser.PrimaryPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AccessSuffix}
	 * labeled alternative in {@link StaticJavaParser#pSuffix}.
	 * @param ctx the parse tree
	 */
	void enterAccessSuffix(StaticJavaParser.AccessSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AccessSuffix}
	 * labeled alternative in {@link StaticJavaParser#pSuffix}.
	 * @param ctx the parse tree
	 */
	void exitAccessSuffix(StaticJavaParser.AccessSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IndexingSuffix}
	 * labeled alternative in {@link StaticJavaParser#pSuffix}.
	 * @param ctx the parse tree
	 */
	void enterIndexingSuffix(StaticJavaParser.IndexingSuffixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IndexingSuffix}
	 * labeled alternative in {@link StaticJavaParser#pSuffix}.
	 * @param ctx the parse tree
	 */
	void exitIndexingSuffix(StaticJavaParser.IndexingSuffixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParenExp}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterParenExp(StaticJavaParser.ParenExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParenExp}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitParenExp(StaticJavaParser.ParenExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InvokeExp}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterInvokeExp(StaticJavaParser.InvokeExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InvokeExp}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitInvokeExp(StaticJavaParser.InvokeExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdExp}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterIdExp(StaticJavaParser.IdExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdExp}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitIdExp(StaticJavaParser.IdExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewArrayObject}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayObject(StaticJavaParser.NewArrayObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewArrayObject}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayObject(StaticJavaParser.NewArrayObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewArrayObjectInit}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayObjectInit(StaticJavaParser.NewArrayObjectInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewArrayObjectInit}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayObjectInit(StaticJavaParser.NewArrayObjectInitContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewObject}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterNewObject(StaticJavaParser.NewObjectContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewObject}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitNewObject(StaticJavaParser.NewObjectContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewArrayBasic}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayBasic(StaticJavaParser.NewArrayBasicContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewArrayBasic}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayBasic(StaticJavaParser.NewArrayBasicContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NewArrayBasicInit}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void enterNewArrayBasicInit(StaticJavaParser.NewArrayBasicInitContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NewArrayBasicInit}
	 * labeled alternative in {@link StaticJavaParser#pPrefix}.
	 * @param ctx the parse tree
	 */
	void exitNewArrayBasicInit(StaticJavaParser.NewArrayBasicInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#invoke}.
	 * @param ctx the parse tree
	 */
	void enterInvoke(StaticJavaParser.InvokeContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#invoke}.
	 * @param ctx the parse tree
	 */
	void exitInvoke(StaticJavaParser.InvokeContext ctx);
	/**
	 * Enter a parse tree produced by {@link StaticJavaParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(StaticJavaParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link StaticJavaParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(StaticJavaParser.ArgsContext ctx);
}