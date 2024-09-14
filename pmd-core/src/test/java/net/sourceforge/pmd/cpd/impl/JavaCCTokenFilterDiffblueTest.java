package net.sourceforge.pmd.cpd.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import net.sourceforge.pmd.lang.TokenManager;
import net.sourceforge.pmd.lang.ast.impl.javacc.JavaccToken;
import net.sourceforge.pmd.lang.ast.impl.javacc.JavaccTokenDocument;
import net.sourceforge.pmd.lang.document.TextDocument;
import org.junit.jupiter.api.Test;

class JavaCCTokenFilterDiffblueTest {
    /**
     * Method under test: {@link JavaCCTokenFilter#JavaCCTokenFilter(TokenManager)}
     */
    @Test
    void testNewJavaCCTokenFilter() {
        // Arrange
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getLength()).thenReturn(3);
        JavaccToken javaccToken = new JavaccToken(1, "Image", 1, 3,
                new JavaccTokenDocument(textDocument, new JavaccTokenDocument.TokenDocumentBehavior(new ArrayList<>())));

        TokenManager<JavaccToken> tokenManager = mock(TokenManager.class);
        when(tokenManager.getNextToken()).thenReturn(javaccToken);

        // Act
        JavaCCTokenFilter actualJavaCCTokenFilter = new JavaCCTokenFilter(tokenManager);
        JavaccToken actualNextToken = actualJavaCCTokenFilter.getNextToken();

        // Assert
        verify(tokenManager).getNextToken();
        verify(textDocument).getLength();
        assertFalse(actualJavaCCTokenFilter.isLanguageSpecificDiscarding());
        assertSame(javaccToken, actualNextToken);
    }

    /**
     * Method under test: {@link JavaCCTokenFilter#JavaCCTokenFilter(TokenManager)}
     */
    @Test
    void testNewJavaCCTokenFilter2() {
        // Arrange
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getLength()).thenReturn(3);
        TokenManager<JavaccToken> tokenManager = mock(TokenManager.class);
        when(tokenManager.getNextToken()).thenReturn(new JavaccToken(0, "Image", 1, 3,
                new JavaccTokenDocument(textDocument, new JavaccTokenDocument.TokenDocumentBehavior(new ArrayList<>()))));

        // Act
        JavaCCTokenFilter actualJavaCCTokenFilter = new JavaCCTokenFilter(tokenManager);
        JavaccToken actualNextToken = actualJavaCCTokenFilter.getNextToken();

        // Assert
        verify(tokenManager).getNextToken();
        verify(textDocument).getLength();
        assertNull(actualNextToken);
        assertFalse(actualJavaCCTokenFilter.isLanguageSpecificDiscarding());
    }
}
