package sjc.symboltable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.Modifier.ModifierKeyword;

import sjc.annotation.NonNull;
import sjc.annotation.NonNullElements;
import sjc.annotation.ReadOnly;
import sjc.util.Pair;

/**
 * This class is used to build symbol table for an ExtendedStaticJava
 * {@link CompilationUnit}. Note that the algorithm assumes that the JDT AST
 * tree was built using the {@link esjc.ast.ExtendedStaticJavaASTBuilder}. That is, it
 * assumes certain structures on the AST, e.g., a class does not have an
 * instance method.
 *
 */
public class SymbolTableBuilder {
  /** Signals an error while building an extended symbol table. */
  public static class Error extends RuntimeException {
    private static final long serialVersionUID = 7425398360365890486L;

    public final @NonNull
    @ReadOnly
    ASTNode node;

    public Error(@NonNull final ASTNode node, @NonNull final String msg) {
      super(msg);
      assert (msg != null) && (node != null);
      this.node = node;
    }
  }

  /**
   * The visitor for {@link ASTNode} to resolve symbols.
   *
   */
  private static class Visitor extends ASTVisitor {
    public @NonNull
    Map<ASTNode, Object> result = new HashMap<>();

    protected @NonNull
    Map<String, ASTNode> nameMap = new HashMap<>();

    protected @NonNull
    Map<String, MethodDeclaration> methodMap = new HashMap<>();

    protected @NonNull
    Set<String> localNames = new HashSet<>();

    protected String className;

    private @NonNullElements
    Map<String, TypeDeclaration> classMap = new HashMap<>();

    private @NonNullElements
    Map<Pair<String, String>, FieldDeclaration> fieldMap = new HashMap<>();

    /**
     * Unlink references and clear maps/collections.
     */
    protected void dispose() {
      this.result = null;
      if (this.nameMap != null) {
        this.nameMap.clear();
        this.nameMap = null;
      }
      if (this.methodMap != null) {
        this.methodMap.clear();
        this.methodMap = null;
      }
      this.className = null;
    }

    /**
     * Determines whether a {@link List} of {@link Modifier}s has a public
     * modifier.
     * 
     * @param modifiers
     *        the list of modifiers
     *
     * @return True, if the {@link List} contains a public modifier.
     */
    private boolean hasPublicModifier(final List modifiers) {
      for (final Object o : modifiers) {
        if ((o instanceof Modifier)
            && (((Modifier) o).getKeyword() == ModifierKeyword.PUBLIC_KEYWORD)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public boolean visit(final FieldDeclaration node) {
      // TODO
      // Fields are only accessed through FieldAccess, which is resolved in the
      // type checking phase, so they are recorded in fieldMap only.
      for (final Object o : node.fragments()) {
        final VariableDeclarationFragment f = (VariableDeclarationFragment) o;
        final Pair<String, String> key = new Pair<>(this.className, f
            .getName().getIdentifier());
        if (this.fieldMap.containsKey(key)) {
          throw new Error(f, "Duplicate field " + key.second + " in class "
              + this.className);
        }
        this.fieldMap.put(key, node);
      }
      return false;
    }

    @Override
    public boolean visit(final MethodDeclaration node) {
      // TODO
      // Each method starts with a fresh local scope.
      this.nameMap.clear();
      this.localNames.clear();

      for (final Object o : node.parameters()) {
        final SingleVariableDeclaration p = (SingleVariableDeclaration) o;
        final String name = p.getName().getIdentifier();
        if (!this.localNames.add(name)) {
          throw new Error(p, "Duplicate parameter " + name);
        }
        this.nameMap.put(name, p);
      }

      if (node.getBody() != null) {
        for (final Object o : node.getBody().statements()) {
          ((Statement) o).accept(this);
        }
      }
      return false;
    }

    @Override
    public boolean visit(final MethodInvocation node) {
      // TODO
      final Expression e = node.getExpression();
      final String name = node.getName().getIdentifier();
      // An invocation is a call to a method of the current class if it has
      // no receiver or if its receiver is the current class name. Any other
      // receiver (e.g., StaticJavaLib) is a library call and is not resolved.
      final boolean local = (e == null)
          || ((e instanceof SimpleName) && ((SimpleName) e).getIdentifier()
              .equals(this.className));
      if (local) {
        final MethodDeclaration decl = this.methodMap.get(name);
        if (decl == null) {
          throw new Error(node, "Undefined method " + name);
        }
        this.result.put(node, decl);
      } else if (!(e instanceof SimpleName)) {
        e.accept(this);
      }

      for (final Object o : node.arguments()) {
        ((ASTNode) o).accept(this);
      }
      return false;
    }

    @Override
    public boolean visit(final SimpleName node) {
      // TODO
      final ASTNode decl = this.nameMap.get(node.getIdentifier());
      if (decl == null) {
        throw new Error(node, "Undefined variable " + node.getIdentifier());
      }
      this.result.put(node, decl);
      return false;
    }

    @Override
    public boolean visit(final VariableDeclarationStatement node) {
      // TODO
      // The declared names are not references, so only the initializers are
      // visited. The variable is registered after its initializer is visited,
      // so that an initializer cannot refer to the variable being declared.
      for (final Object o : node.fragments()) {
        final VariableDeclarationFragment f = (VariableDeclarationFragment) o;
        final String name = f.getName().getIdentifier();
        if (f.getInitializer() != null) {
          f.getInitializer().accept(this);
        }
        if (!this.localNames.add(name)) {
          throw new Error(f, "Duplicate variable " + name);
        }
        this.nameMap.put(name, node);
      }
      return false;
    }

    @Override
    public boolean visit(final ArrayCreation node) {
      // Note that we don't visit the ArrayCreation's type
      // because we want visit(SimpleName) to resolve variable references
      // instead of method names.
      for (final Object o : node.dimensions()) {
        ((ASTNode) o).accept(this);
      }
      if (node.getInitializer() != null) {
        node.getInitializer().accept(this);
      }
      return false;
    }

    @Override
    public boolean visit(final ClassInstanceCreation node) {
      // Note that we don't visit the ClassInstanceCreation's name
      // because we want visit(SimpleName) to resolve variable references
      // instead of method names.
      return false;
    }

    @Override
    public boolean visit(final FieldAccess node) {
      // Note that we don't visit the FieldAccess's name
      // because we want visit(SimpleName) to resolve variable references
      // instead of method names.
      node.getExpression().accept(this);
      return false;
    }

    @Override
    public boolean visit(final TypeDeclaration node) {
      // TODO
      this.className = node.getName().getIdentifier();
      this.nameMap.clear();
      this.methodMap.clear();
      this.localNames.clear();

      // The public class (holding main) is not recorded in classMap.
      if (!this.hasPublicModifier(node.modifiers())) {
        this.classMap.put(this.className, node);
      }

      // Register all methods first so that forward references resolve.
      for (final MethodDeclaration m : node.getMethods()) {
        if (!m.isConstructor()) {
          this.methodMap.put(m.getName().getIdentifier(), m);
        }
      }

      for (final FieldDeclaration f : node.getFields()) {
        f.accept(this);
      }
      for (final MethodDeclaration m : node.getMethods()) {
        if (!m.isConstructor()) {
          m.accept(this);
        }
      }
      return false;
    }
  }

  /**
   * Builds a {@link SymbolTable} for the given ExtendedStaticJava
   * {@link CompilationUnit}.
   * 
   * @param cu
   *          The {@link CompilationUnit}.
   * @return The {@link SymbolTable} for the given
   *         {@link CompilationUnit}.
   * @throws Error
   *           If the builder encounter unresolvable symbol.
   */
  public static @NonNull
  SymbolTable build(@NonNull final CompilationUnit cu)
      throws Error {
    assert cu != null;

    final Visitor v = new Visitor();
    cu.accept(v);
    final SymbolTable result = new SymbolTable(v.result,
        v.classMap, v.fieldMap);
    v.dispose();
    return result;
  }

  /**
   * Declared as protected to disallow creation of this object outside from the
   * methods of this class.
   */
  protected SymbolTableBuilder() {
  }
}
