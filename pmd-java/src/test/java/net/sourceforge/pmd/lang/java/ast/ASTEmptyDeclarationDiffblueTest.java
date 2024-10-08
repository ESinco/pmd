package net.sourceforge.pmd.lang.java.ast;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Iterator;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.ast.NodeStream;
import net.sourceforge.pmd.lang.rule.xpath.Attribute;
import net.sourceforge.pmd.lang.rule.xpath.impl.AttributeAxisIterator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ASTEmptyDeclarationDiffblueTest {
    /**
     * Method under test:
     * {@link ASTEmptyDeclaration#acceptVisitor(JavaVisitor, Object)}
     */
    @Test
    void testAcceptVisitor() {
        // Arrange
        ASTEmptyDeclaration astEmptyDeclaration = new ASTEmptyDeclaration(1);
        JavaVisitor<Object, Object> visitor = mock(JavaVisitor.class);
        when(visitor.visit(Mockito.<ASTEmptyDeclaration>any(), Mockito.<Object>any())).thenReturn("Visit");

        // Act
        Object actualAcceptVisitorResult = astEmptyDeclaration.acceptVisitor(visitor, "Data");

        // Assert
        verify(visitor).visit(isA(ASTEmptyDeclaration.class), isA(Object.class));
        assertEquals("Visit", actualAcceptVisitorResult);
    }

    /**
     * Method under test: {@link ASTEmptyDeclaration#ASTEmptyDeclaration(int)}
     */
    @Test
    void testNewASTEmptyDeclaration() {
        // Arrange and Act
        ASTEmptyDeclaration actualAstEmptyDeclaration = new ASTEmptyDeclaration(1);

        // Assert
        Iterator<Attribute> xPathAttributesIterator = actualAstEmptyDeclaration.getXPathAttributesIterator();
        assertTrue(xPathAttributesIterator instanceof AttributeAxisIterator);
        Attribute nextResult = xPathAttributesIterator.next();
        assertEquals("", nextResult.getStringValue());
        assertEquals("ClassBody", actualAstEmptyDeclaration.getXPathNodeName());
        assertEquals("Image", nextResult.getName());
        assertNull(nextResult.getValue());
        assertNull(actualAstEmptyDeclaration.getImage());
        assertNull(actualAstEmptyDeclaration.getFirstChild());
        assertNull(actualAstEmptyDeclaration.getLastChild());
        assertNull(actualAstEmptyDeclaration.getNextSibling());
        assertNull(actualAstEmptyDeclaration.getPreviousSibling());
        assertNull(actualAstEmptyDeclaration.getFirstToken());
        assertNull(actualAstEmptyDeclaration.getLastToken());
        assertNull(actualAstEmptyDeclaration.getEnclosingType());
        assertNull(actualAstEmptyDeclaration.symbolTable);
        assertEquals(0, actualAstEmptyDeclaration.getIndexInParent());
        assertEquals(0, actualAstEmptyDeclaration.getNumChildren());
        assertFalse(actualAstEmptyDeclaration.isFindBoundary());
        assertTrue(xPathAttributesIterator.hasNext());
        NodeStream<? extends Node> ancestorsResult = actualAstEmptyDeclaration.ancestors();
        assertTrue(ancestorsResult.toList().isEmpty());
        assertTrue(actualAstEmptyDeclaration.children().toList().isEmpty());
        assertSame(actualAstEmptyDeclaration, nextResult.getParent());
        assertSame(ancestorsResult, actualAstEmptyDeclaration.descendants());
    }
}
