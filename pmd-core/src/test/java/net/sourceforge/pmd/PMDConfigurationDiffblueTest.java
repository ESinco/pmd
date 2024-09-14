package net.sourceforge.pmd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.management.loading.MLet;

import net.sourceforge.pmd.cache.internal.AnalysisCache;
import net.sourceforge.pmd.cache.internal.FileAnalysisCache;

import net.sourceforge.pmd.cache.internal.NoopAnalysisCache;
import net.sourceforge.pmd.internal.util.ClasspathClassLoader;
import net.sourceforge.pmd.lang.CpdOnlyDummyLanguage;
import net.sourceforge.pmd.lang.Language;
import net.sourceforge.pmd.lang.LanguageRegistry;

import net.sourceforge.pmd.lang.rule.RulePriority;
import net.sourceforge.pmd.util.log.PmdReporter;
import net.sourceforge.pmd.util.log.internal.SimpleMessageReporter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class PMDConfigurationDiffblueTest {
    /**
     * Method under test: {@link PMDConfiguration#setClassLoader(ClassLoader)}
     */
    @Test
    void testSetClassLoader() throws IOException {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        ArrayList<File> files = new ArrayList<>();
        ClasspathClassLoader classLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(files, new MLet()));

        // Act
        pmdConfiguration.setClassLoader(classLoader);

        // Assert
        assertSame(classLoader, pmdConfiguration.getClassLoader());
    }

    /**
     * Method under test: {@link PMDConfiguration#setClassLoader(ClassLoader)}
     */
    @Test
    void testSetClassLoader2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setClassLoader(null);

        // Assert
        assertNotNull(pmdConfiguration.getClassLoader());
    }

    /**
     * Method under test: {@link PMDConfiguration#setClassLoader(ClassLoader)}
     */
    @Test
    void testSetClassLoader3() throws IOException {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any())).thenReturn(null);
        URLClassLoader parent = new URLClassLoader(
                new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                new ClasspathClassLoader("Classpath", new MLet()), urlStreamHandlerFactory);

        ClasspathClassLoader classLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(new ArrayList<>(), parent));

        // Act
        pmdConfiguration.setClassLoader(classLoader);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertSame(classLoader, pmdConfiguration.getClassLoader());
    }

    /**
     * Method under test: {@link PMDConfiguration#prependAuxClasspath(String)}
     */
    @Test
    void testPrependAuxClasspath() throws IOException {
        // Arrange
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any())).thenReturn(null);
        URLClassLoader classLoader = new URLClassLoader(
                new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                new ClasspathClassLoader("Classpath", new MLet()), urlStreamHandlerFactory);

        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        pmdConfiguration.setClassLoader(classLoader);

        // Act
        pmdConfiguration.prependAuxClasspath(null);

        // Assert that nothing has changed
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    }

    /**
     * Method under test: {@link PMDConfiguration#setRuleSets(List)}
     */
    @Test
    void testSetRuleSets() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setRuleSets(new ArrayList<>());

        // Assert
        assertTrue(pmdConfiguration.getRuleSetPaths().isEmpty());
    }

    /**
     * Method under test: {@link PMDConfiguration#setRuleSets(List)}
     */
    @Test
    void testSetRuleSets2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        ArrayList<String> ruleSetPaths = new ArrayList<>();
        ruleSetPaths.add("Rule Set Paths");

        // Act
        pmdConfiguration.setRuleSets(ruleSetPaths);

        // Assert
        List<String> ruleSetPaths2 = pmdConfiguration.getRuleSetPaths();
        assertEquals(1, ruleSetPaths2.size());
        assertEquals("Rule Set Paths", ruleSetPaths2.get(0));
    }

    /**
     * Method under test: {@link PMDConfiguration#setRuleSets(List)}
     */
    @Test
    void testSetRuleSets3() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        ArrayList<String> ruleSetPaths = new ArrayList<>();
        ruleSetPaths.add("42");
        ruleSetPaths.add("ruleSetPaths");

        // Act
        pmdConfiguration.setRuleSets(ruleSetPaths);

        // Assert
        assertEquals(ruleSetPaths, pmdConfiguration.getRuleSetPaths());
    }

    /**
     * Method under test: {@link PMDConfiguration#addRuleSet(String)}
     */
    @Test
    void testAddRuleSet() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.addRuleSet("Ruleset Path");

        // Assert
        List<String> ruleSetPaths = pmdConfiguration.getRuleSetPaths();
        assertEquals(1, ruleSetPaths.size());
        assertEquals("Ruleset Path", ruleSetPaths.get(0));
    }

    /**
     * Method under test: {@link PMDConfiguration#addRuleSet(String)}
     */
    @Test
    void testAddRuleSet2() throws IOException {
        // Arrange
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any())).thenReturn(null);
        URLClassLoader parent = new URLClassLoader(
                new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                new ClasspathClassLoader("Classpath", new MLet()), urlStreamHandlerFactory);

        ClasspathClassLoader classLoader = new ClasspathClassLoader("rulesetPath",
                new ClasspathClassLoader(new ArrayList<>(), parent));

        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        pmdConfiguration.setClassLoader(classLoader);

        // Act
        pmdConfiguration.addRuleSet("Ruleset Path");

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        List<String> ruleSetPaths = pmdConfiguration.getRuleSetPaths();
        assertEquals(1, ruleSetPaths.size());
        assertEquals("Ruleset Path", ruleSetPaths.get(0));
    }

    /**
     * Method under test: {@link PMDConfiguration#createRenderer()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateRenderer() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Parameter reportFormat is null
        //       at net.sourceforge.pmd.util.AssertionUtil.requireParamNotNull(AssertionUtil.java:209)
        //       at net.sourceforge.pmd.renderers.RendererFactory.createRenderer(RendererFactory.java:75)
        //       at net.sourceforge.pmd.PMDConfiguration.createRenderer(PMDConfiguration.java:295)
        //       at net.sourceforge.pmd.PMDConfiguration.createRenderer(PMDConfiguration.java:282)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new PMDConfiguration()).createRenderer();
    }

    /**
     * Method under test: {@link PMDConfiguration#createRenderer(boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateRenderer2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Parameter reportFormat is null
        //       at net.sourceforge.pmd.util.AssertionUtil.requireParamNotNull(AssertionUtil.java:209)
        //       at net.sourceforge.pmd.renderers.RendererFactory.createRenderer(RendererFactory.java:75)
        //       at net.sourceforge.pmd.PMDConfiguration.createRenderer(PMDConfiguration.java:295)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new PMDConfiguration()).createRenderer(true);
    }

    /**
     * Method under test: {@link PMDConfiguration#getAnalysisCache()}
     */
    @Test
    void testGetAnalysisCache() {
        // Arrange and Act
        AnalysisCache actualAnalysisCache = (new PMDConfiguration()).getAnalysisCache();

        // Assert
        assertTrue(actualAnalysisCache instanceof NoopAnalysisCache);
        assertFalse(actualAnalysisCache.isUpToDate(null));
        assertTrue(actualAnalysisCache.getCachedViolations(null).isEmpty());
    }

    /**
     * Method under test: {@link PMDConfiguration#getAnalysisCache()}
     */
    @Test
    void testGetAnalysisCache2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        pmdConfiguration.setIgnoreIncrementalAnalysis(true);

        // Act
        AnalysisCache actualAnalysisCache = pmdConfiguration.getAnalysisCache();

        // Assert
        assertTrue(actualAnalysisCache instanceof NoopAnalysisCache);
        assertFalse(actualAnalysisCache.isUpToDate(null));
        assertTrue(actualAnalysisCache.getCachedViolations(null).isEmpty());
    }

    /**
     * Method under test: {@link PMDConfiguration#getAnalysisCache()}
     */
    @Test
    void testGetAnalysisCache3() throws IOException {
        // Arrange
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any())).thenReturn(null);
        URLClassLoader parent = new URLClassLoader(
                new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                new ClasspathClassLoader("Classpath", new MLet()), urlStreamHandlerFactory);

        ClasspathClassLoader classLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(new ArrayList<>(), parent));

        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        pmdConfiguration.setClassLoader(classLoader);

        // Act
        AnalysisCache actualAnalysisCache = pmdConfiguration.getAnalysisCache();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualAnalysisCache instanceof NoopAnalysisCache);
        assertFalse(actualAnalysisCache.isUpToDate(null));
        assertTrue(actualAnalysisCache.getCachedViolations(null).isEmpty());
    }

    /**
     * Method under test: {@link PMDConfiguration#setAnalysisCache(AnalysisCache)}
     */
    @Test
    void testSetAnalysisCache() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        NoopAnalysisCache cache = new NoopAnalysisCache();

        // Act
        pmdConfiguration.setAnalysisCache(cache);

        // Assert
        assertSame(cache, pmdConfiguration.getAnalysisCache());
    }

    /**
     * Method under test: {@link PMDConfiguration#setAnalysisCache(AnalysisCache)}
     */
    @Test
    void testSetAnalysisCache2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setAnalysisCache(null);

        // Assert
        assertTrue(pmdConfiguration.getAnalysisCache() instanceof NoopAnalysisCache);
    }

    /**
     * Method under test: {@link PMDConfiguration#setAnalysisCache(AnalysisCache)}
     */
    @Test
    void testSetAnalysisCache3() throws IOException {
        // Arrange
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any())).thenReturn(null);
        URLClassLoader parent = new URLClassLoader(
                new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                new ClasspathClassLoader("Classpath", new MLet()), urlStreamHandlerFactory);

        ClasspathClassLoader classLoader = new ClasspathClassLoader("Classpath",
                new ClasspathClassLoader(new ArrayList<>(), parent));

        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        pmdConfiguration.setClassLoader(classLoader);
        NoopAnalysisCache cache = new NoopAnalysisCache();

        // Act
        pmdConfiguration.setAnalysisCache(cache);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertSame(cache, pmdConfiguration.getAnalysisCache());
    }

    /**
     * Method under test: {@link PMDConfiguration#setAnalysisCacheLocation(String)}
     */
    @Test
    void testSetAnalysisCacheLocation() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setAnalysisCacheLocation("Cache Location");

        // Assert
        assertTrue(pmdConfiguration.getAnalysisCache() instanceof FileAnalysisCache);
    }

    /**
     * Method under test: {@link PMDConfiguration#setAnalysisCacheLocation(String)}
     */
    @Test
    void testSetAnalysisCacheLocation2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setAnalysisCacheLocation(null);

        // Assert
        assertTrue(pmdConfiguration.getAnalysisCache() instanceof NoopAnalysisCache);
    }

    /**
     * Method under test:
     * {@link PMDConfiguration#checkLanguageIsAcceptable(Language)}
     */
    @Test
    void testCheckLanguageIsAcceptable() throws UnsupportedOperationException {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act and Assert
        assertThrows(UnsupportedOperationException.class,
                () -> pmdConfiguration.checkLanguageIsAcceptable(new CpdOnlyDummyLanguage()));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link PMDConfiguration#setIgnoreIncrementalAnalysis(boolean)}
     *   <li>{@link PMDConfiguration#setMinimumPriority(RulePriority)}
     *   <li>{@link PMDConfiguration#setReportFile(Path)}
     *   <li>{@link PMDConfiguration#setReportFormat(String)}
     *   <li>{@link PMDConfiguration#setReportProperties(Properties)}
     *   <li>{@link PMDConfiguration#setShowSuppressedViolations(boolean)}
     *   <li>{@link PMDConfiguration#setSuppressMarker(String)}
     *   <li>{@link PMDConfiguration#setThreads(int)}
     *   <li>{@link PMDConfiguration#getClassLoader()}
     *   <li>{@link PMDConfiguration#getMinimumPriority()}
     *   <li>{@link PMDConfiguration#getReportFilePath()}
     *   <li>{@link PMDConfiguration#getReportFormat()}
     *   <li>{@link PMDConfiguration#getReportProperties()}
     *   <li>{@link PMDConfiguration#getRuleSetPaths()}
     *   <li>{@link PMDConfiguration#getSuppressMarker()}
     *   <li>{@link PMDConfiguration#getThreads()}
     *   <li>{@link PMDConfiguration#isIgnoreIncrementalAnalysis()}
     *   <li>{@link PMDConfiguration#isShowSuppressedViolations()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setIgnoreIncrementalAnalysis(true);
        pmdConfiguration.setMinimumPriority(RulePriority.HIGH);
        Path reportFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
        pmdConfiguration.setReportFile(reportFile);
        pmdConfiguration.setReportFormat("Report Format");
        Properties reportProperties = new Properties();
        pmdConfiguration.setReportProperties(reportProperties);
        pmdConfiguration.setShowSuppressedViolations(true);
        pmdConfiguration.setSuppressMarker("Suppress Marker");
        pmdConfiguration.setThreads(1);
        pmdConfiguration.getClassLoader();
        RulePriority actualMinimumPriority = pmdConfiguration.getMinimumPriority();
        Path actualReportFilePath = pmdConfiguration.getReportFilePath();
        String actualReportFormat = pmdConfiguration.getReportFormat();
        Properties actualReportProperties = pmdConfiguration.getReportProperties();
        List<String> actualRuleSetPaths = pmdConfiguration.getRuleSetPaths();
        String actualSuppressMarker = pmdConfiguration.getSuppressMarker();
        int actualThreads = pmdConfiguration.getThreads();
        boolean actualIsIgnoreIncrementalAnalysisResult = pmdConfiguration.isIgnoreIncrementalAnalysis();
        boolean actualIsShowSuppressedViolationsResult = pmdConfiguration.isShowSuppressedViolations();

        // Assert that nothing has changed
        assertEquals("Report Format", actualReportFormat);
        assertEquals("Suppress Marker", actualSuppressMarker);
        assertEquals(1, actualThreads);
        assertEquals(RulePriority.HIGH, actualMinimumPriority);
        assertTrue(actualRuleSetPaths.isEmpty());
        assertTrue(actualIsIgnoreIncrementalAnalysisResult);
        assertTrue(actualIsShowSuppressedViolationsResult);
        assertSame(reportProperties, actualReportProperties);
        assertSame(reportFile, actualReportFilePath);
    }

    /**
     * Method under test: {@link PMDConfiguration#PMDConfiguration()}
     */
    @Test
    void testNewPMDConfiguration() {
        // Arrange and Act
        PMDConfiguration actualPmdConfiguration = new PMDConfiguration();

        // Assert
        assertTrue(actualPmdConfiguration.getAnalysisCache() instanceof NoopAnalysisCache);
        PmdReporter reporter = actualPmdConfiguration.getReporter();
        assertTrue(reporter instanceof SimpleMessageReporter);
        assertEquals("windows-1252", actualPmdConfiguration.getSourceEncoding().name());
        assertNotNull(actualPmdConfiguration.getClassLoader());
        assertNull(actualPmdConfiguration.getReportFormat());
        assertNull(actualPmdConfiguration.getUri());
        assertNull(actualPmdConfiguration.getIgnoreFile());
        assertNull(actualPmdConfiguration.getInputFile());
        assertNull(actualPmdConfiguration.getReportFilePath());
        assertNull(actualPmdConfiguration.getForceLanguageVersion());
        assertNull(actualPmdConfiguration.getLanguageVersionDiscoverer().getForcedVersion());
        assertEquals(0, reporter.numErrors());
        assertEquals(2, actualPmdConfiguration.getLanguageRegistry().getLanguages().size());
        assertEquals(4, actualPmdConfiguration.getThreads());
        assertEquals(RulePriority.LOW, actualPmdConfiguration.getMinimumPriority());
        assertFalse(actualPmdConfiguration.isForceLanguageVersion());
        assertFalse(actualPmdConfiguration.isIgnoreIncrementalAnalysis());
        assertFalse(actualPmdConfiguration.isShowSuppressedViolations());
        assertTrue(actualPmdConfiguration.getExcludes().isEmpty());
        assertTrue(actualPmdConfiguration.getInputPathList().isEmpty());
        assertTrue(actualPmdConfiguration.getRelativizeRoots().isEmpty());
        assertTrue(actualPmdConfiguration.getRuleSetPaths().isEmpty());
        assertTrue(actualPmdConfiguration.getReportProperties().isEmpty());
        assertTrue(actualPmdConfiguration.isFailOnError());
        assertTrue(actualPmdConfiguration.isFailOnViolation());
        assertEquals(PMDConfiguration.DEFAULT_SUPPRESS_MARKER, actualPmdConfiguration.getSuppressMarker());
    }

    /**
     * Method under test:
     * {@link PMDConfiguration#PMDConfiguration(LanguageRegistry)}
     */
    @Test
    void testNewPMDConfiguration2() {
        // Arrange
        LanguageRegistry languageRegistry = LanguageRegistry.CPD;

        // Act
        PMDConfiguration actualPmdConfiguration = new PMDConfiguration(languageRegistry);

        // Assert
        assertTrue(actualPmdConfiguration.getAnalysisCache() instanceof NoopAnalysisCache);
        PmdReporter reporter = actualPmdConfiguration.getReporter();
        assertTrue(reporter instanceof SimpleMessageReporter);
        assertEquals("windows-1252", actualPmdConfiguration.getSourceEncoding().name());
        assertNotNull(actualPmdConfiguration.getClassLoader());
        assertNull(actualPmdConfiguration.getReportFormat());
        assertNull(actualPmdConfiguration.getUri());
        assertNull(actualPmdConfiguration.getIgnoreFile());
        assertNull(actualPmdConfiguration.getInputFile());
        assertNull(actualPmdConfiguration.getReportFilePath());
        assertNull(actualPmdConfiguration.getForceLanguageVersion());
        assertNull(actualPmdConfiguration.getLanguageVersionDiscoverer().getForcedVersion());
        assertEquals(0, reporter.numErrors());
        assertEquals(4, actualPmdConfiguration.getThreads());
        assertEquals(RulePriority.LOW, actualPmdConfiguration.getMinimumPriority());
        assertFalse(actualPmdConfiguration.isForceLanguageVersion());
        assertFalse(actualPmdConfiguration.isIgnoreIncrementalAnalysis());
        assertFalse(actualPmdConfiguration.isShowSuppressedViolations());
        assertTrue(actualPmdConfiguration.getExcludes().isEmpty());
        assertTrue(actualPmdConfiguration.getInputPathList().isEmpty());
        assertTrue(actualPmdConfiguration.getRelativizeRoots().isEmpty());
        assertTrue(actualPmdConfiguration.getRuleSetPaths().isEmpty());
        assertTrue(actualPmdConfiguration.getReportProperties().isEmpty());
        assertTrue(actualPmdConfiguration.isFailOnError());
        assertTrue(actualPmdConfiguration.isFailOnViolation());
        assertEquals(PMDConfiguration.DEFAULT_SUPPRESS_MARKER, actualPmdConfiguration.getSuppressMarker());
        LanguageRegistry expectedLanguageRegistry = languageRegistry.CPD;
        assertSame(expectedLanguageRegistry, actualPmdConfiguration.getLanguageRegistry());
    }

    /**
     * Method under test:
     * {@link PMDConfiguration#PMDConfiguration(LanguageRegistry)}
     */
    @Test
    void testNewPMDConfiguration3() throws IOException {
        // Arrange
        URLStreamHandlerFactory urlStreamHandlerFactory = mock(URLStreamHandlerFactory.class);
        when(urlStreamHandlerFactory.createURLStreamHandler(Mockito.<String>any())).thenReturn(null);
        URLClassLoader parent = new URLClassLoader(
                new URL[]{Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()},
                new ClasspathClassLoader("Classpath", new MLet()), urlStreamHandlerFactory);

        LanguageRegistry languageRegistry = LanguageRegistry
                .loadLanguages(new ClasspathClassLoader("file.encoding", new ClasspathClassLoader(new ArrayList<>(), parent)));

        // Act
        PMDConfiguration actualPmdConfiguration = new PMDConfiguration(languageRegistry);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualPmdConfiguration.getAnalysisCache() instanceof NoopAnalysisCache);
        PmdReporter reporter = actualPmdConfiguration.getReporter();
        assertTrue(reporter instanceof SimpleMessageReporter);
        assertEquals("windows-1252", actualPmdConfiguration.getSourceEncoding().name());
        assertNotNull(actualPmdConfiguration.getClassLoader());
        assertNull(actualPmdConfiguration.getReportFormat());
        assertNull(actualPmdConfiguration.getUri());
        assertNull(actualPmdConfiguration.getIgnoreFile());
        assertNull(actualPmdConfiguration.getInputFile());
        assertNull(actualPmdConfiguration.getReportFilePath());
        assertNull(actualPmdConfiguration.getForceLanguageVersion());
        assertNull(actualPmdConfiguration.getLanguageVersionDiscoverer().getForcedVersion());
        assertEquals(0, reporter.numErrors());
        assertEquals(4, actualPmdConfiguration.getThreads());
        assertEquals(RulePriority.LOW, actualPmdConfiguration.getMinimumPriority());
        assertFalse(actualPmdConfiguration.isForceLanguageVersion());
        assertFalse(actualPmdConfiguration.isIgnoreIncrementalAnalysis());
        assertFalse(actualPmdConfiguration.isShowSuppressedViolations());
        assertTrue(actualPmdConfiguration.getExcludes().isEmpty());
        assertTrue(actualPmdConfiguration.getInputPathList().isEmpty());
        assertTrue(actualPmdConfiguration.getRelativizeRoots().isEmpty());
        assertTrue(actualPmdConfiguration.getRuleSetPaths().isEmpty());
        assertTrue(actualPmdConfiguration.getReportProperties().isEmpty());
        LanguageRegistry languageRegistry2 = actualPmdConfiguration.getLanguageRegistry();
        assertTrue(languageRegistry2.getLanguages().isEmpty());
        assertTrue(actualPmdConfiguration.isFailOnError());
        assertTrue(actualPmdConfiguration.isFailOnViolation());
        assertEquals(PMDConfiguration.DEFAULT_SUPPRESS_MARKER, actualPmdConfiguration.getSuppressMarker());
        assertSame(languageRegistry, languageRegistry2);
    }
}
