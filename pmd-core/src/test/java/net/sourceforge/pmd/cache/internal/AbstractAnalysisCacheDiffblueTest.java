package net.sourceforge.pmd.cache.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.management.loading.MLet;

import net.sourceforge.pmd.internal.util.ClasspathClassLoader;

import net.sourceforge.pmd.lang.document.FileId;
import net.sourceforge.pmd.lang.document.TextDocument;
import net.sourceforge.pmd.lang.rule.internal.RuleSets;
import net.sourceforge.pmd.reporting.FileAnalysisListener;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AbstractAnalysisCacheDiffblueTest {
    /**
     * Method under test: {@link AbstractAnalysisCache#isUpToDate(TextDocument)}
     */
    @Test
    void testIsUpToDate() {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getCheckSum()).thenReturn(1L);
        when(textDocument.getFileId()).thenReturn(FileId.STDIN);

        // Act
        boolean actualIsUpToDateResult = fileAnalysisCache.isUpToDate(textDocument);

        // Assert
        verify(textDocument).getCheckSum();
        verify(textDocument, atLeast(1)).getFileId();
        assertEquals(1, fileAnalysisCache.updatedResultsCache.size());
        assertFalse(actualIsUpToDateResult);
    }

    /**
     * Method under test: {@link AbstractAnalysisCache#isUpToDate(TextDocument)}
     */
    @Test
    void testIsUpToDate2() {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getCheckSum()).thenThrow(new RuntimeException("up-to-date check"));
        when(textDocument.getFileId()).thenReturn(FileId.STDIN);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> fileAnalysisCache.isUpToDate(textDocument));
        verify(textDocument).getCheckSum();
        verify(textDocument).getFileId();
    }

    /**
     * Method under test:
     * {@link AbstractAnalysisCache#getCachedViolations(TextDocument)}
     */
    @Test
    void testGetCachedViolations() {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getFileId()).thenReturn(FileId.STDIN);

        // Act
        List actualCachedViolations = fileAnalysisCache.getCachedViolations(textDocument);

        // Assert
        verify(textDocument).getFileId();
        assertTrue(actualCachedViolations.isEmpty());
    }

    /**
     * Method under test:
     * {@link AbstractAnalysisCache#getCachedViolations(TextDocument)}
     */
    @Test
    void testGetCachedViolations2() {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getFileId()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> fileAnalysisCache.getCachedViolations(textDocument));
        verify(textDocument).getFileId();
    }

    /**
     * Method under test: {@link AbstractAnalysisCache#analysisFailed(TextDocument)}
     */
    @Test
    void testAnalysisFailed() {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getFileId()).thenReturn(FileId.STDIN);

        // Act
        fileAnalysisCache.analysisFailed(textDocument);

        // Assert
        verify(textDocument).getFileId();
    }

    /**
     * Method under test: {@link AbstractAnalysisCache#analysisFailed(TextDocument)}
     */
    @Test
    void testAnalysisFailed2() {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        TextDocument textDocument = mock(TextDocument.class);
        when(textDocument.getFileId()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> fileAnalysisCache.analysisFailed(textDocument));
        verify(textDocument).getFileId();
    }

    /**
     * Method under test:
     * {@link AbstractAnalysisCache#checkValidity(RuleSets, ClassLoader, Collection)}
     */
    @Test
    void testCheckValidity() throws IOException {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());
        RuleSets ruleSets = new RuleSets(new ArrayList<>());
        ArrayList<File> files = new ArrayList<>();
        ClasspathClassLoader auxclassPathClassLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(files, new MLet()));

        // Act
        fileAnalysisCache.checkValidity(ruleSets, auxclassPathClassLoader, new ArrayList<>());

        // Assert
        assertEquals(1L, fileAnalysisCache.auxClassPathChecksum);
        assertEquals(1L, fileAnalysisCache.rulesetChecksum);
        assertEquals(408227739L, fileAnalysisCache.executionClassPathChecksum);
    }

    /**
     * Method under test:
     * {@link AbstractAnalysisCache#checkValidity(RuleSets, ClassLoader, Collection)}
     */
    @Test
    void testCheckValidity2() throws IOException {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "load").toFile());
        RuleSets ruleSets = new RuleSets(new ArrayList<>());
        ArrayList<File> files = new ArrayList<>();
        ClasspathClassLoader auxclassPathClassLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(files, new MLet()));

        // Act
        fileAnalysisCache.checkValidity(ruleSets, auxclassPathClassLoader, new ArrayList<>());

        // Assert
        assertEquals(1L, fileAnalysisCache.auxClassPathChecksum);
        assertEquals(1L, fileAnalysisCache.rulesetChecksum);
        assertEquals(408227739L, fileAnalysisCache.executionClassPathChecksum);
    }

    /**
     * Method under test:
     * {@link AbstractAnalysisCache#startFileAnalysis(TextDocument)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartFileAnalysis() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.io.InputStream.read()" because "in" is null
        //       at java.base/java.io.DataInputStream.readUnsignedShort(DataInputStream.java:334)
        //       at java.base/java.io.DataInputStream.readUTF(DataInputStream.java:583)
        //       at java.base/java.io.DataInputStream.readUTF(DataInputStream.java:558)
        //       at net.sourceforge.pmd.cache.internal.CachedRuleViolation.loadFromStream(CachedRuleViolation.java:88)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        FileAnalysisListener actualStartFileAnalysisResult = (new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()))
                .startFileAnalysis(mock(TextDocument.class));
        DataInputStream stream = mock(DataInputStream.class);
        actualStartFileAnalysisResult
                .onRuleViolation(CachedRuleViolation.loadFromStream(stream, FileId.STDIN, new CachedRuleMapper()));
    }
}
