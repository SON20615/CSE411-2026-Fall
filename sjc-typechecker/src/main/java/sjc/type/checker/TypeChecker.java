package sjc.type.checker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import sjc.annotation.NonNull;
import sjc.annotation.NonNullElements;
import sjc.annotation.ReadOnly;
import sjc.annotation.ReadOnlyElements;
import sjc.symboltable.SymbolTable;
import sjc.type.ArrayType;
import sjc.type.BaseType;
import sjc.type.ClassType;
import sjc.type.NonPrimitiveType;
import sjc.type.NullType;
import sjc.type.Type;
import sjc.type.TypeFactory;
import sjc.util.Pair;

/**
 * This class is used to type check a StaticJava {@link CompilationUnit} with a
 * given {@link SymbolTable}.
 *
 */
public class TypeChecker {
  public static class Error extends RuntimeException {
    private static final long serialVersionUID = 5322515711212690043L;
    public final @NonNull @ReadOnly ASTNode node;
    public Error(@NonNull final ASTNode node, @NonNull final String message) {
      super(message);
      this.node = node;
    }
  }

  /** Visitor for type checking an ExtendedStaticJava compilation unit. */
  protected static class Visitor extends ASTVisitor {
    protected Map<ASTNode, Object> symbolMap;
    public Map<ASTNode, Type> resultTypeMap = new HashMap<>();
    public Map<Object, Pair<Type, List<Type>>> resultMethodTypeMap =
        new HashMap<>();
    protected TypeFactory tf;
    protected ArrayType stringArrayType;
    protected String className;
    protected Type methodReturnType, resultingType;
    protected @NonNullElements
    @ReadOnlyElements
    Map<String, TypeDeclaration> classMap;
    protected @NonNullElements
    @ReadOnlyElements
    Map<Pair<String, String>, FieldDeclaration> fieldMap;
    protected Visitor(final TypeFactory tf, final SymbolTable st) {
      this.tf = tf;
      this.symbolMap = st.symbolMap;
      this.stringArrayType =
          tf.getArrayType(tf.getClassType("java.lang.String"));
      this.classMap = st.classMap;
      this.fieldMap = st.fieldMap;
      for (final Pair<String, String> p : st.fieldMap.keySet()) {
        final FieldDeclaration fd = st.fieldMap.get(p);
        final Type t = convertType(fd, fd.getType());
        final ClassType ct = tf.getClassType(p.first);
        ct.fieldTypeMap.put(p.second, t);
      }
    }
    protected Type getResult() {
      Type t = resultingType;
      resultingType = null;
      return t;
    }
    protected void setResult(Expression n, Type t) {
      resultTypeMap.put(n, t);
      resultingType = t;
    }
    protected Type convertType(ASTNode n, Class c) {
      if (c == void.class)
        return tf.Void;
      if (c == boolean.class)
        return tf.Boolean;
      if (c == int.class)
        return tf.Int;
      if (c == String[].class)
        return stringArrayType;
      if (!c.isArray())
        return tf.getClassType(c.getName());
      return tf.getArrayType((BaseType) convertType(n, c.getComponentType()));
    }
    protected Type convertType(ASTNode n, org.eclipse.jdt.core.dom.Type t) {
      if (t instanceof PrimitiveType) {
        PrimitiveType.Code c = ((PrimitiveType) t).getPrimitiveTypeCode();
        if (c == PrimitiveType.BOOLEAN)
          return tf.Boolean;
        if (c == PrimitiveType.INT)
          return tf.Int;
        if (c == PrimitiveType.VOID)
          return tf.Void;
      }
      if (t instanceof SimpleType) {
        String s = ((SimpleType) t).getName().getFullyQualifiedName();
        if (classMap.containsKey(s))
          return tf.getClassType(s);
        if ("String".equals(s) || "java.lang.String".equals(s))
          return stringArrayType.baseType;
      }
      if (t instanceof org.eclipse.jdt.core.dom.ArrayType)
        return tf.getArrayType((BaseType) convertType(
            n, ((org.eclipse.jdt.core.dom.ArrayType) t).getElementType()));
      throw new Error(n, "Unexpected Type: '" + t + "'");
    }
    protected void dispose() {
      symbolMap = null;
      resultTypeMap = null;
      resultMethodTypeMap = null;
      tf = null;
      stringArrayType = null;
      className = null;
      methodReturnType = resultingType = null;
      classMap = null;
      fieldMap = null;
    }
    protected Method resolveMethod(
        MethodInvocation n, String c, String m, Type[] a) {
      try {
        Class[] p = new Class[a.length];
        for (int i = 0; i < a.length; i++) p[i] = convertClass(n, a[i]);
        Method r = Class.forName(c).getMethod(m, p);
        symbolMap.put(n, r);
        return r;
      } catch (Exception e) {
        throw new Error(n, "Unresolvable method \"" + c + "." + m + "\"");
      }
    }
    protected Class convertClass(ASTNode n, Type t) {
      if (t == tf.Void)
        return void.class;
      if (t == tf.Boolean)
        return boolean.class;
      if (t == tf.Int)
        return int.class;
      if (t == stringArrayType)
        return String[].class;
      if (t instanceof ClassType)
        try {
          return Class.forName(((ClassType) t).name);
        } catch (Exception e) {
        }
      throw new Error(n, "Unresolvable Type: '" + t + "'");
    }
    protected void typeCheckMethodInvocation(
        MethodInvocation n, String c, String m, Type[] a, Method d) {
      if (a.length != d.getParameterTypes().length)
        throw new Error(n,
            "Wrong number of arguments to invoke method \"" + m + "\" in \"" + n
                + "\"");
      List<Type> p = new ArrayList<>();
      for (int i = 0; i < a.length; i++) {
        Type t = convertType(n, d.getParameterTypes()[i]);
        if (!((a[i] instanceof NullType) && (t instanceof NonPrimitiveType))
            && t != a[i])
          throw new Error(
              n, "Type mismatch the " + i + " argument in \"" + n + "\"");
        p.add(t);
      }
      Type r = convertType(n, d.getReturnType());
      if (!resultMethodTypeMap.containsKey(d))
        resultMethodTypeMap.put(d, new Pair<Type, List<Type>>(r, p));
      setResult(n, r);
    }
    protected void typeCheckMethodInvocation(
        MethodInvocation n, String c, String m, Type[] a, MethodDeclaration d) {
      if (a.length != d.parameters().size())
        throw new Error(n,
            "Wrong number of arguments to invoke method \"" + m + "\" in \"" + n
                + "\"");
      for (int i = 0; i < a.length; i++)
        if (!((a[i] instanceof NullType)
                && (convertType(n, ((SingleVariableDeclaration) d.parameters()
                    .get(i)).getType()) instanceof NonPrimitiveType))
            && convertType(n,
                ((SingleVariableDeclaration) d.parameters().get(i)).getType())
            != a[i])
          throw new Error(
              n, "Type mismatch the " + i + " argument in \"" + n + "\"");
      setResult(n, convertType(n, d.getReturnType2()));
    }
    @Override
    public boolean visit(BooleanLiteral n) {
      setResult(n, tf.Boolean);
      return false;
    }
    @Override
    public boolean visit(NumberLiteral n) {
      setResult(n, tf.Int);
      return false;
    }
    @Override
    public boolean visit(ParenthesizedExpression n) {
      // TODO
      n.getExpression().accept(this);
      final Type t = getResult();
      setResult(n, t);
      return false;
    }
    @Override
    public boolean visit(FieldDeclaration n) {
      // TODO
      resultTypeMap.put(n, convertType(n, n.getType()));
      return false;
    }
    @Override
    public boolean visit(MethodDeclaration n) {
      // TODO
      for (Object o: n.parameters()) {
        SingleVariableDeclaration p = (SingleVariableDeclaration) o;
        resultTypeMap.put(p, convertType(n, p.getType()));
      }
      methodReturnType = convertType(n, n.getReturnType2());
      n.getBody().accept(this);
      return false;
    }
    @Override
    public boolean visit(MethodInvocation n) {
      // TODO
      final String name = n.getName().getIdentifier();

      // Visit each argument and take its type right away.
      final Type[] a = new Type[n.arguments().size()];
      for (int i = 0; i < a.length; i++) {
        ((Expression) n.arguments().get(i)).accept(this);
        a[i] = getResult();
      }

      final Object decl = symbolMap.get(n);
      if (decl instanceof MethodDeclaration) {
        // A method declared in the program.
        typeCheckMethodInvocation(n, className, name, a,
            (MethodDeclaration) decl);
      } else {
        // A library method such as StaticJavaLib.println.
        final Expression e = n.getExpression();
        if (!(e instanceof SimpleName)) {
          throw new Error(n, "Unresolvable method \"" + name + "\"");
        }
        final String c = ((SimpleName) e).getIdentifier();
        final Method m = resolveMethod(n, c, name, a);
        typeCheckMethodInvocation(n, c, name, a, m);
      }
      return false;
    }
    @Override
    public boolean visit(SimpleName n) {
      // TODO
      final Object declaration = symbolMap.get(n);
      final org.eclipse.jdt.core.dom.Type delcared;

      if (declaration instanceof VariableDeclarationStatement) {
        delcared = ((VariableDeclarationStatement) declaration).getType();
      } 
      
      else if (declaration instanceof SingleVariableDeclaration) {
        delcared = ((SingleVariableDeclaration) declaration).getType();
      } 
      
      else {
        throw new Error(n, "Unresolvable name: " + n);
      }

      setResult(n, convertType(n, delcared));
      return false;
    }
    @Override
    public boolean visit(TypeDeclaration n) {
      // TODO
      className = n.getName().getIdentifier();
      resultTypeMap.put(n, tf.getClassType(className));
      for (FieldDeclaration f: n.getFields()) {
        f.accept(this);
      }

      for (MethodDeclaration m: n.getMethods()) {
        m.accept(this);
      }
      return false;
    }
    @Override
    public boolean visit(VariableDeclarationStatement n) {
      // TODO
      resultTypeMap.put(n, convertType(n, n.getType()));
      return false;
    }
    @Override
    public boolean visit(Assignment n) {
      // TODO
      n.getLeftHandSide().accept(this);
      final Type LeftType = getResult();
      n.getRightHandSide().accept(this);
      final Type RightType = getResult();
      if (LeftType != RightType && !((RightType instanceof NullType) && (LeftType instanceof NonPrimitiveType))) {
        throw new Error(n, "Type mismatch in \"" + n + "\": " + LeftType + " = " + RightType);
      }
      return false;
    }
    @Override
    public boolean visit(ExpressionStatement n) {
      // TODO
      n.getExpression().accept(this);
      getResult();
      return false;
    }
    @Override
    public boolean visit(InfixExpression n) {
      // TODO
      final InfixExpression.Operator op = n.getOperator();

      // Collect all operands (a + b + c keeps c in extendedOperands()).
      final List<Expression> operands = new ArrayList<>();
      operands.add(n.getLeftOperand());
      operands.add(n.getRightOperand());
      for (Object o : n.extendedOperands()) {
        operands.add((Expression) o);
      }

      // Visit each operand and take its type right away (one-slot result).
      final List<Type> types = new ArrayList<>();
      for (Expression e : operands) {
        e.accept(this);
        types.add(getResult());
      }

      if (op == InfixExpression.Operator.EQUALS
          || op == InfixExpression.Operator.NOT_EQUALS) {
        final Type lt = types.get(0);
        final Type rt = types.get(1);
        final boolean ok = (lt == rt)
            || ((lt instanceof NullType) && (rt instanceof NonPrimitiveType))
            || ((rt instanceof NullType) && (lt instanceof NonPrimitiveType));
        if (!ok) {
          throw new Error(n, "Type mismatch in \"" + n + "\": " + lt + " " + op
              + " " + rt);
        }
        setResult(n, tf.Boolean);
        return false;
      }

      final boolean logical = (op == InfixExpression.Operator.CONDITIONAL_AND
          || op == InfixExpression.Operator.CONDITIONAL_OR);
      final Type expected = logical ? tf.Boolean : tf.Int;
      final String kind = logical ? "a boolean" : "an int";
      for (int i = 0; i < types.size(); i++) {
        if (types.get(i) != expected) {
          throw new Error(n, "Expecting " + kind + " type expression as the "
              + (i == 0 ? "left" : "right") + "-hand operand of \"" + op
              + "\" in \"" + n + "\"");
        }
      }

      final boolean arithmetic = (op == InfixExpression.Operator.PLUS
          || op == InfixExpression.Operator.MINUS
          || op == InfixExpression.Operator.TIMES
          || op == InfixExpression.Operator.DIVIDE
          || op == InfixExpression.Operator.REMAINDER
          || op == InfixExpression.Operator.LEFT_SHIFT
          || op == InfixExpression.Operator.RIGHT_SHIFT_SIGNED
          || op == InfixExpression.Operator.RIGHT_SHIFT_UNSIGNED);
      setResult(n, arithmetic ? tf.Int : tf.Boolean);
      return false;
    }
    @Override
    public boolean visit(PrefixExpression n) {
      // TODO
      n.getOperand().accept(this);
      final Type t = getResult();
      if (n.getOperator() == PrefixExpression.Operator.NOT) {
        if (t != tf.Boolean) {
          throw new Error(n,
              "Expecting a boolean type expression as the operand of \""
                  + n.getOperator() + "\" in \"" + n + "\"");
        }
        setResult(n, tf.Boolean);
      } else {
        if (t != tf.Int) {
          throw new Error(n,
              "Expecting an int type expression as the operand of \""
                  + n.getOperator() + "\" in \"" + n + "\"");
        }
        setResult(n, tf.Int);
      }
      return false;
    }
    @Override
    public boolean visit(ReturnStatement n) {
      // TODO
      final Expression e = n.getExpression();
      if (methodReturnType == tf.Void) {
        if (e != null) {
          throw new Error(n, "Unexpected return's expression in \"" + n + "\"");
        }
        return false;
      }
      if (e == null) {
        throw new Error(n,
            "Expecting " + methodReturnType + " return expression in \"" + n
                + "\"");
      }
      e.accept(this);
      final Type t = getResult();
      if (t != methodReturnType
          && !((t instanceof NullType)
              && (methodReturnType instanceof NonPrimitiveType))) {
        throw new Error(n,
            "Expecting " + methodReturnType + " return expression in \"" + n
                + "\"");
      }
      return false;
    }
    @Override
    public boolean visit(IfStatement n) {
      // TODO
      n.getExpression().accept(this);
      if (getResult() != tf.Boolean) {
        throw new Error(n,
            "Expecting a boolean type expression as the condition of an if-statement: \""
                + n.getExpression() + "\"");
      }
      n.getThenStatement().accept(this);
      if (n.getElseStatement() != null) {
        n.getElseStatement().accept(this);
      }
      return false;
    }
    @Override
    public boolean visit(WhileStatement n) {
      // TODO
      n.getExpression().accept(this);
      if (getResult() != tf.Boolean) {
        throw new Error(n,
            "Expecting a boolean type expression as the condition of a while-statement: \""
                + n.getExpression() + "\"");
      }
      n.getBody().accept(this);
      return false;
    }

    @Override
    public boolean visit(final ArrayAccess node) {
      // TODO
      node.getArray().accept(this);
      final Type arrayType = getResult();
      node.getIndex().accept(this);
      final Type indexType = getResult();
      if (!(arrayType instanceof ArrayType)) {
        throw new Error(node,
            "Expecting an array type expression for the array access in '"
                + node + "'");
      }
      if (indexType != tf.Int) {
        throw new Error(node,
            "Expecting an int type for the index of '" + node + "\"");
      }
      setResult(node, ((ArrayType) arrayType).baseType);
      return false;
    }

    @Override
    public boolean visit(final ArrayCreation node) {
      // TODO
      final Type arrayType = convertType(node, node.getType());
      for (final Object o : node.dimensions()) {
        ((Expression) o).accept(this);
        if (getResult() != tf.Int) {
          throw new Error(node,
              "Expecting an int type expression for the array length in '"
                  + node + "'");
        }
      }
      final ArrayInitializer init = node.getInitializer();
      if (init != null) {
        final Type elementType = ((ArrayType) arrayType).baseType;
        int i = 0;
        for (final Object o : init.expressions()) {
          ((Expression) o).accept(this);
          final Type t = getResult();
          if (t != elementType
              && !((t instanceof NullType)
                  && (elementType instanceof NonPrimitiveType))) {
            throw new Error(node, "Type mismatch in array initializer element "
                + i + " in '" + node + "': " + elementType + " : " + t);
          }
          i++;
        }
      }
      setResult(node, arrayType);
      return false;
    }

    @Override
    public boolean visit(final ClassInstanceCreation node) {
      // TODO
      final Type t = convertType(node, node.getType());
      if (!(t instanceof ClassType)) {
        throw new Error(node, "Expecting a class type in '" + node + "'");
      }
      final TypeDeclaration decl = classMap.get(((ClassType) t).name);
      if (decl != null) {
        symbolMap.put(node, decl);
      }
      setResult(node, t);
      return false;
    }

    @Override
    public boolean visit(final ConditionalExpression node) {
      // TODO
      node.getExpression().accept(this);
      if (getResult() != tf.Boolean) {
        throw new Error(node,
            "Expecting a boolean type expression as the condition in '" + node
                + "\"");
      }
      node.getThenExpression().accept(this);
      final Type thenType = getResult();
      node.getElseExpression().accept(this);
      final Type elseType = getResult();

      final Type result;
      if (thenType == elseType) {
        result = thenType;
      } else if ((thenType instanceof NullType)
          && (elseType instanceof NonPrimitiveType)) {
        result = elseType;
      } else if ((elseType instanceof NullType)
          && (thenType instanceof NonPrimitiveType)) {
        result = thenType;
      } else {
        throw new Error(node, "Type mismatch for conditional expression '"
            + node + "\": " + thenType + " : " + elseType);
      }
      setResult(node, result);
      return false;
    }

    @Override
    public boolean visit(final DoStatement node) {
      // TODO
      node.getBody().accept(this);
      node.getExpression().accept(this);
      if (getResult() != tf.Boolean) {
        throw new Error(node,
            "Expecting a boolean type expression as the condition of a while-statement: \""
                + node.getExpression() + "\"");
      }
      return false;
    }

    @Override
    public boolean visit(final FieldAccess node) {
      // TODO
      node.getExpression().accept(this);
      final Type t = getResult();
      if (!(t instanceof ClassType)) {
        throw new Error(node,
            "Expecting a class type expression for the field access in '"
                + node + "'");
      }
      final ClassType ct = (ClassType) t;
      final String field = node.getName().getIdentifier();
      final Type fieldType = ct.fieldTypeMap.get(field);
      if (fieldType == null) {
        throw new Error(node,
            "Unresolvable field \"" + ct.name + "." + field + "\"");
      }
      final FieldDeclaration decl =
          fieldMap.get(new Pair<String, String>(ct.name, field));
      if (decl != null) {
        symbolMap.put(node, decl);
      }
      setResult(node, fieldType);
      return false;
    }

    @Override
    public boolean visit(final ForStatement node) {
      // TODO
      for (final Object o : node.initializers()) {
        ((Expression) o).accept(this);
        getResult();
      }
      if (node.getExpression() != null) {
        node.getExpression().accept(this);
        if (getResult() != tf.Boolean) {
          throw new Error(node,
              "Expecting a boolean type expression as the condition of a for-statement: \""
                  + node.getExpression() + "\"");
        }
      }
      for (final Object o : node.updaters()) {
        ((Expression) o).accept(this);
        getResult();
      }
      node.getBody().accept(this);
      return false;
    }

    @Override
    public boolean visit(final NullLiteral node) {
      // TODO
      setResult(node, tf.Null);
      return false;
    }

    @Override
    public boolean visit(final PostfixExpression node) {
      node.getOperand().accept(this);
      final Type t = getResult();
      if (t != this.tf.Int) {
        throw new Error(node,
            "Expecting an int type expression as the operand of \'"
                + node.getOperator() + "\': \"" + node.getOperand() + "\"");
      }
      // no need to set the type result for postfix expressions since
      // postfix expressions in StaticJava are statements,
      // i.e., they are evaluated for their side-effects.
      return false;
    }

  }

  /**
   * Type checks an ExtendedStaticJava {@link CompilationUnit} with the given
   * {@link SymbolTable} and the given {@link TypeFactory}. It also
   * resolves {@link MethodInvocation} of library call (and put its mapping in
   * the {@link SymbolTable}).
   * 
   * @param tf
   *          The {@link TypeFactory}.
   * @param cu
   *          The StaticJava {@link CompilationUnit}.
   * @param symbolTable
   *          The {@link SymbolTable} of the {@link CompilationUnit}
   * @return The {@link TypeTable}.
   * @throws Error
   *           If the type checker encounter type error in the
   *           {@link CompilationUnit}.
   */
  public static @NonNull
  TypeTable check(@NonNull final TypeFactory tf,
      @NonNull final CompilationUnit cu,
      @NonNull final SymbolTable symbolTable) throws Error {
    assert (tf != null) && (cu != null) && (symbolTable != null);

    final Visitor v = new Visitor(tf, symbolTable);
    cu.accept(v);
    final TypeTable result = new TypeTable(v.resultTypeMap,
        v.resultMethodTypeMap);
    v.dispose();
    return result;
  }

  /**
   * Declared as protected to disallow creation of this object outside from the
   * methods of this class.
   */
  protected TypeChecker() {
  }
}
