package net.sourceforge.pmd.cache.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import javax.management.loading.MLet;

import net.sourceforge.pmd.internal.util.ClasspathClassLoader;
import net.sourceforge.pmd.lang.rule.internal.RuleSets;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class FileAnalysisCacheDiffblueTest {
    /**
     * Method under test:
     * {@link FileAnalysisCache#checkValidity(RuleSets, ClassLoader, Collection)}
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
     * {@link FileAnalysisCache#checkValidity(RuleSets, ClassLoader, Collection)}
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
     * {@link FileAnalysisCache#checkValidity(RuleSets, ClassLoader, Collection)}
     */
    @Test
    void testCheckValidity3() throws IOException {
        // Arrange
        FileAnalysisCache fileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "").toFile());
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
     * Method under test: {@link FileAnalysisCache#persist()}
     */
    @Test
    void testPersist() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        (new FileAnalysisCache(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile())).persist();
    }

    /**
     * Method under test: {@link FileAnalysisCache#persist()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testPersist2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Sandboxing policy violation.
        //   Diffblue Cover ran code in your project that tried
        //     to access temporary files created outside of the test (file 'C:\Users\Fil\AppData\Local\Temp\test.txt', permission 'write').
        //   Diffblue Cover's default sandboxing policy disallows this in order to prevent
        //   your code from damaging your system environment.
        //   See https://diff.blue/R011 to resolve this issue.

        // Arrange and Act
        (new FileAnalysisCache(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).persist();
    }

    /**
     * Method under test: {@link FileAnalysisCache#cacheExists()}
     */
    @Test
    void testCacheExists() {
        // Arrange, Act and Assert
        assertFalse(
                (new FileAnalysisCache(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile())).cacheExists());
        assertFalse((new FileAnalysisCache(Paths.get(System.getProperty("java.io.tmpdir"), "").toFile())).cacheExists());
        assertFalse(
                (new FileAnalysisCache(Paths.get(System.getProperty("java.io.tmpdir"), "foo", "42").toFile())).cacheExists());
    }

    /**
     * Method under test: {@link FileAnalysisCache#FileAnalysisCache(File)}
     */
    @Test
    void testNewFileAnalysisCache() {
        // Arrange and Act
        FileAnalysisCache actualFileAnalysisCache = new FileAnalysisCache(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

        // Assert
        assertEquals("7.4.0-SNAPSHOT", actualFileAnalysisCache.pmdVersion);
        assertEquals(0L, actualFileAnalysisCache.auxClassPathChecksum);
        assertEquals(0L, actualFileAnalysisCache.executionClassPathChecksum);
        assertEquals(0L, actualFileAnalysisCache.rulesetChecksum);
        assertTrue(actualFileAnalysisCache.fileResultsCache.isEmpty());
        assertTrue(actualFileAnalysisCache.updatedResultsCache.isEmpty());
    }
}
