/*
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import java.util.Iterator;


/**
 * The parameter list of a {@linkplain ASTLambdaExpression lambda expression}.
 *
 * <pre class="grammar">
 *
 * LambdaParameterList ::= "(" ")"
 *                       | "(" {@link ASTLambdaParameter LambdaParameter} ("," {@link ASTLambdaParameter LambdaParameter})*")"
 *
 * </pre>
 */
public final class ASTLambdaParameterList extends AbstractJavaNode implements Iterable<ASTLambdaParameter> {

    ASTLambdaParameterList(int id) {
        super(id);
    }

    public int getParameterCount() {
        return getNumChildren();
    }


    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }


    @Override
    public ASTLambdaParameter getChild(int index) {
        return (ASTLambdaParameter) super.getChild(index);
    }


    @Override
    public Iterator<ASTLambdaParameter> iterator() {
        return children(ASTLambdaParameter.class).iterator();
    }
}
