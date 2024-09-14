package net.sourceforge.pmd.cache.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.management.loading.MLet;

import net.sourceforge.pmd.internal.util.ClasspathClassLoader;
import net.sourceforge.pmd.lang.document.FileId;
import net.sourceforge.pmd.lang.document.TextDocument;
import net.sourceforge.pmd.lang.rule.internal.RuleSets;
import net.sourceforge.pmd.reporting.FileAnalysisListener;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class NoopAnalysisCacheDiffblueTest {
    /**
     * Method under test: {@link NoopAnalysisCache#isUpToDate(TextDocument)}
     */
    @Test
    void testIsUpToDate() {
        // Arrange, Act and Assert
        assertFalse((new NoopAnalysisCache()).isUpToDate(mock(TextDocument.class)));
    }

    /**
     * Method under test:
     * {@link NoopAnalysisCache#getCachedViolations(TextDocument)}
     */
    @Test
    void testGetCachedViolations() {
        // Arrange, Act and Assert
        assertTrue((new NoopAnalysisCache()).getCachedViolations(mock(TextDocument.class)).isEmpty());
    }

    /**
     * Method under test: {@link NoopAnalysisCache#startFileAnalysis(TextDocument)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStartFileAnalysis() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        FileAnalysisListener actualStartFileAnalysisResult = (new NoopAnalysisCache())
                .startFileAnalysis(mock(TextDocument.class));
        DataInputStream stream = mock(DataInputStream.class);
        actualStartFileAnalysisResult
                .onRuleViolation(CachedRuleViolation.loadFromStream(stream, FileId.STDIN, new CachedRuleMapper()));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link NoopAnalysisCache#analysisFailed(TextDocument)}
     *   <li>
     * {@link NoopAnalysisCache#checkValidity(RuleSets, ClassLoader, Collection)}
     *   <li>{@link NoopAnalysisCache#persist()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   There are no fields that could be asserted on.

        // Arrange
        NoopAnalysisCache noopAnalysisCache = new NoopAnalysisCache();

        // Act
        noopAnalysisCache.analysisFailed(mock(TextDocument.class));
        RuleSets ruleSets = new RuleSets(new ArrayList<>());
        ArrayList<File> files = new ArrayList<>();
        ClasspathClassLoader auxclassPathClassLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(files, new MLet()));

        noopAnalysisCache.checkValidity(ruleSets, auxclassPathClassLoader, new ArrayList<>());
        noopAnalysisCache.persist();
    }
}
