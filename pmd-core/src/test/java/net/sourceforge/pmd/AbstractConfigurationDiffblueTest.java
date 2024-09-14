package net.sourceforge.pmd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.management.loading.MLet;

import net.sourceforge.pmd.internal.util.ClasspathClassLoader;

import net.sourceforge.pmd.lang.CpdOnlyDummyLanguage;
import net.sourceforge.pmd.lang.Dummy2LanguageModule;
import net.sourceforge.pmd.lang.DummyLanguageModule;
import net.sourceforge.pmd.lang.Language;
import net.sourceforge.pmd.lang.LanguagePropertyBundle;
import net.sourceforge.pmd.lang.LanguageRegistry;
import net.sourceforge.pmd.lang.LanguageVersion;
import net.sourceforge.pmd.lang.LanguageVersionDiscoverer;
import net.sourceforge.pmd.lang.document.TestMessageReporter;
import net.sourceforge.pmd.properties.PropertyDescriptor;
import net.sourceforge.pmd.util.log.PmdReporter;
import net.sourceforge.pmd.util.log.internal.SimpleMessageReporter;
import net.sourceforge.pmd.util.treeexport.TreeExportConfiguration;
import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.event.SubstituteLoggingEvent;
import org.slf4j.helpers.SubstituteLogger;

class AbstractConfigurationDiffblueTest {
    /**
     * Method under test: {@link AbstractConfiguration#getSourceEncoding()}
     */
    @Test
    void testGetSourceEncoding() {
        // Arrange, Act and Assert
        assertEquals("windows-1252", (new PMDConfiguration()).getSourceEncoding().name());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getSourceEncoding()}
     */
    @Test
    void testGetSourceEncoding2() throws IOException {
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
        Charset actualSourceEncoding = pmdConfiguration.getSourceEncoding();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertEquals("windows-1252", actualSourceEncoding.name());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setSourceEncoding(Charset)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetSourceEncoding() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:209)
        //       at net.sourceforge.pmd.AbstractConfiguration.setSourceEncoding(AbstractConfiguration.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new PMDConfiguration()).setSourceEncoding(null);
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageProperties(Language)}
     */
    @Test
    void testGetLanguageProperties() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> pmdConfiguration.getLanguageProperties(new CpdOnlyDummyLanguage()));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageProperties(Language)}
     */
    @Test
    void testGetLanguageProperties2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        Dummy2LanguageModule language = new Dummy2LanguageModule();

        // Act
        LanguagePropertyBundle actualLanguageProperties = pmdConfiguration.getLanguageProperties(language);

        // Assert
        LanguageVersion languageVersion = actualLanguageProperties.getLanguageVersion();
        assertEquals("1.0", languageVersion.getVersion());
        assertEquals("Dummy2 1.0", languageVersion.getName());
        assertEquals("Dummy2 1.0", languageVersion.getShortName());
        assertEquals("Dummy2", actualLanguageProperties.getName());
        List<PropertyDescriptor<?>> propertyDescriptors = actualLanguageProperties.getPropertyDescriptors();
        assertEquals(2, propertyDescriptors.size());
        PropertyDescriptor<?> getResult = propertyDescriptors.get(1);
        assertEquals("Language version to use for this language. See the --use-version CLI switch as well.",
                getResult.description());
        assertEquals("dummy2 1.0", languageVersion.getTerseName());
        assertEquals("version", getResult.name());
        assertEquals(2, actualLanguageProperties.getPropertiesByPropertyDescriptor().size());
        assertFalse(getResult.isXPathAvailable());
        assertTrue(actualLanguageProperties.getOverriddenPropertyDescriptors().isEmpty());
        assertTrue(actualLanguageProperties.getOverriddenPropertiesByPropertyDescriptor().isEmpty());
        assertEquals(PMDConfiguration.DEFAULT_SUPPRESS_MARKER, actualLanguageProperties.getSuppressMarker());
        assertSame(language, actualLanguageProperties.getLanguage());
        assertSame(language, languageVersion.getLanguage());
        assertSame(languageVersion, getResult.defaultValue());
        assertSame(actualLanguageProperties.SUPPRESS_MARKER, propertyDescriptors.get(0));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageProperties(Language)}
     */
    @Test
    void testGetLanguageProperties3() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        DummyLanguageModule language = new DummyLanguageModule();

        // Act
        LanguagePropertyBundle actualLanguageProperties = pmdConfiguration.getLanguageProperties(language);

        // Assert
        LanguageVersion languageVersion = actualLanguageProperties.getLanguageVersion();
        assertEquals("1.7", languageVersion.getVersion());
        List<PropertyDescriptor<?>> propertyDescriptors = actualLanguageProperties.getPropertyDescriptors();
        assertEquals(4, propertyDescriptors.size());
        PropertyDescriptor<?> getResult = propertyDescriptors.get(3);
        assertEquals("Anonymize identifiers. They are still part of the token stream but all identifiers appear to have the"
                + " same value.", getResult.description());
        PropertyDescriptor<?> getResult2 = propertyDescriptors.get(2);
        assertEquals("Anonymize literals. They are still part of the token stream but all literals appear to have the"
                + " same value.", getResult2.description());
        assertEquals("Dummy 1.7", languageVersion.getName());
        assertEquals("Dummy 1.7", languageVersion.getShortName());
        assertEquals("Dummy", actualLanguageProperties.getName());
        PropertyDescriptor<?> getResult3 = propertyDescriptors.get(1);
        assertEquals("Language version to use for this language. See the --use-version CLI switch as well.",
                getResult3.description());
        assertEquals("cpdAnonymizeIdentifiers", getResult.name());
        assertEquals("cpdAnonymizeLiterals", getResult2.name());
        assertEquals("dummy 1.7", languageVersion.getTerseName());
        assertEquals("version", getResult3.name());
        assertEquals(4, actualLanguageProperties.getPropertiesByPropertyDescriptor().size());
        assertFalse(getResult3.isXPathAvailable());
        assertTrue(actualLanguageProperties.getOverriddenPropertyDescriptors().isEmpty());
        assertTrue(actualLanguageProperties.getOverriddenPropertiesByPropertyDescriptor().isEmpty());
        assertEquals(PMDConfiguration.DEFAULT_SUPPRESS_MARKER, actualLanguageProperties.getSuppressMarker());
        assertSame(language, actualLanguageProperties.getLanguage());
        assertSame(language, languageVersion.getLanguage());
        assertSame(languageVersion, getResult3.defaultValue());
        assertSame(actualLanguageProperties.SUPPRESS_MARKER, propertyDescriptors.get(0));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#checkLanguageIsRegistered(Language)}
     */
    @Test
    void testCheckLanguageIsRegistered() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> pmdConfiguration.checkLanguageIsRegistered(new CpdOnlyDummyLanguage()));
    }

    /**
     * Method under test: {@link AbstractConfiguration#getLanguageRegistry()}
     */
    @Test
    void testGetLanguageRegistry() {
        // Arrange and Act
        LanguageRegistry actualLanguageRegistry = (new PMDConfiguration()).getLanguageRegistry();

        // Assert
        assertSame(actualLanguageRegistry.PMD, actualLanguageRegistry);
    }

    /**
     * Method under test: {@link AbstractConfiguration#getLanguageRegistry()}
     */
    @Test
    void testGetLanguageRegistry2() throws IOException {
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
        LanguageRegistry actualLanguageRegistry = pmdConfiguration.getLanguageRegistry();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertSame(actualLanguageRegistry.PMD, actualLanguageRegistry);
    }

    /**
     * Method under test: {@link AbstractConfiguration#getReporter()}
     */
    @Test
    void testGetReporter() {
        // Arrange and Act
        PmdReporter actualReporter = (new PMDConfiguration()).getReporter();

        // Assert
        assertTrue(actualReporter instanceof SimpleMessageReporter);
        assertEquals(0, actualReporter.numErrors());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getReporter()}
     */
    @Test
    void testGetReporter2() throws IOException {
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
        PmdReporter actualReporter = pmdConfiguration.getReporter();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualReporter instanceof SimpleMessageReporter);
        assertEquals(0, actualReporter.numErrors());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setReporter(PmdReporter)}
     */
    @Test
    void testSetReporter() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        TestMessageReporter reporter = new TestMessageReporter();

        // Act
        pmdConfiguration.setReporter(reporter);

        // Assert
        assertSame(reporter, pmdConfiguration.getReporter());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setReporter(PmdReporter)}
     */
    @Test
    void testSetReporter2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        SubstituteLoggingEvent substituteLoggingEvent = new SubstituteLoggingEvent();
        substituteLoggingEvent.setArgumentArray(new Object[]{"Arg Array"});
        substituteLoggingEvent.setLevel(Level.ERROR);
        substituteLoggingEvent.setLogger(new SubstituteLogger("Name", new LinkedList<>(), true));
        substituteLoggingEvent.setLoggerName("Logger Name");
        substituteLoggingEvent.setMarker(mock(Marker.class));
        substituteLoggingEvent.setMessage("Not all who wander are lost");
        substituteLoggingEvent.setThreadName("Thread Name");
        substituteLoggingEvent.setThrowable(new Throwable());
        substituteLoggingEvent.setTimeStamp(10L);

        LinkedList<SubstituteLoggingEvent> eventQueue = new LinkedList<>();
        eventQueue.add(substituteLoggingEvent);
        SimpleMessageReporter reporter = new SimpleMessageReporter(new SubstituteLogger("reporter", eventQueue, true));

        // Act
        pmdConfiguration.setReporter(reporter);

        // Assert
        assertSame(reporter, pmdConfiguration.getReporter());
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionDiscoverer()}
     */
    @Test
    void testGetLanguageVersionDiscoverer() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getLanguageVersionDiscoverer().getForcedVersion());
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionDiscoverer()}
     */
    @Test
    void testGetLanguageVersionDiscoverer2() throws IOException {
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
        LanguageVersionDiscoverer actualLanguageVersionDiscoverer = pmdConfiguration.getLanguageVersionDiscoverer();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertNull(actualLanguageVersionDiscoverer.getForcedVersion());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getForceLanguageVersion()}
     */
    @Test
    void testGetForceLanguageVersion() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getForceLanguageVersion());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getForceLanguageVersion()}
     */
    @Test
    void testGetForceLanguageVersion2() throws IOException {
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
        LanguageVersion actualForceLanguageVersion = pmdConfiguration.getForceLanguageVersion();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertNull(actualForceLanguageVersion);
    }

    /**
     * Method under test: {@link AbstractConfiguration#isForceLanguageVersion()}
     */
    @Test
    void testIsForceLanguageVersion() {
        // Arrange, Act and Assert
        assertFalse((new PMDConfiguration()).isForceLanguageVersion());
    }

    /**
     * Method under test: {@link AbstractConfiguration#isForceLanguageVersion()}
     */
    @Test
    void testIsForceLanguageVersion2() throws IOException {
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
        boolean actualIsForceLanguageVersionResult = pmdConfiguration.isForceLanguageVersion();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertFalse(actualIsForceLanguageVersionResult);
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#setForceLanguageVersion(LanguageVersion)}
     */
    @Test
    void testSetForceLanguageVersion() throws IOException {
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
        pmdConfiguration.setForceLanguageVersion(null);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#setOnlyRecognizeLanguage(Language)}
     */
    @Test
    void testSetOnlyRecognizeLanguage() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> pmdConfiguration.setOnlyRecognizeLanguage(new CpdOnlyDummyLanguage()));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#setDefaultLanguageVersion(LanguageVersion)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSetDefaultLanguageVersion() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at java.base/java.util.Objects.requireNonNull(Objects.java:209)
        //       at net.sourceforge.pmd.AbstractConfiguration.setDefaultLanguageVersion(AbstractConfiguration.java:184)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new PMDConfiguration()).setDefaultLanguageVersion(null);
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#setDefaultLanguageVersions(List)}
     */
    @Test
    void testSetDefaultLanguageVersions() throws IOException {
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
        pmdConfiguration.setDefaultLanguageVersions(new ArrayList<>());

        // Assert that nothing has changed
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#checkLanguageIsAcceptable(Language)}
     */
    @Test
    void testCheckLanguageIsAcceptable() throws UnsupportedOperationException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        TreeExportConfiguration treeExportConfiguration = new TreeExportConfiguration();

        // Act
        treeExportConfiguration.checkLanguageIsAcceptable(new CpdOnlyDummyLanguage());
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionOfFile(String)}
     */
    @Test
    void testGetLanguageVersionOfFile() {
        // Arrange and Act
        LanguageVersion actualLanguageVersionOfFile = (new PMDConfiguration()).getLanguageVersionOfFile("foo.txt");

        // Assert
        Language language = actualLanguageVersionOfFile.getLanguage();
        assertTrue(language instanceof DummyLanguageModule);
        List<LanguageVersion> versions = language.getVersions();
        assertEquals(10, versions.size());
        LanguageVersion getResult = versions.get(0);
        assertEquals("1.0", getResult.getVersion());
        LanguageVersion getResult2 = versions.get(1);
        assertEquals("1.1", getResult2.getVersion());
        assertEquals("1.7", actualLanguageVersionOfFile.getVersion());
        LanguageVersion latestVersion = language.getLatestVersion();
        assertEquals("1.8", latestVersion.getVersion());
        assertEquals("Dummy 1.0", getResult.getName());
        assertEquals("Dummy 1.0", getResult.getShortName());
        assertEquals("Dummy 1.1", getResult2.getName());
        assertEquals("Dummy 1.1", getResult2.getShortName());
        assertEquals("Dummy 1.7", actualLanguageVersionOfFile.getName());
        assertEquals("Dummy 1.7", actualLanguageVersionOfFile.getShortName());
        assertEquals("Dummy 1.8", latestVersion.getName());
        assertEquals("Dummy 1.8", latestVersion.getShortName());
        LanguageVersion versionWhereParserThrows = ((DummyLanguageModule) language).getVersionWhereParserThrows();
        assertEquals("Dummy parserThrows", versionWhereParserThrows.getName());
        assertEquals("Dummy parserThrows", versionWhereParserThrows.getShortName());
        assertEquals("Dummy", language.getName());
        assertEquals("Dummy", language.getShortName());
        assertEquals("dummy 1.0", getResult.getTerseName());
        assertEquals("dummy 1.1", getResult2.getTerseName());
        assertEquals("dummy 1.7", actualLanguageVersionOfFile.getTerseName());
        assertEquals("dummy 1.8", latestVersion.getTerseName());
        assertEquals("dummy parserThrows", versionWhereParserThrows.getTerseName());
        List<String> extensions = language.getExtensions();
        assertEquals(2, extensions.size());
        assertEquals("dummy", extensions.get(0));
        assertEquals("dummy", language.getId());
        assertEquals("parserThrows", versionWhereParserThrows.getVersion());
        assertEquals("txt", extensions.get(1));
        Set<String> versionNamesAndAliases = language.getVersionNamesAndAliases();
        assertEquals(14, versionNamesAndAliases.size());
        assertTrue(versionNamesAndAliases.contains("1.0"));
        assertTrue(versionNamesAndAliases.contains("1.1"));
        assertTrue(versionNamesAndAliases.contains("1.2"));
        assertTrue(versionNamesAndAliases.contains("5"));
        assertTrue(language.getDependencies().isEmpty());
        assertSame(actualLanguageVersionOfFile, language.getDefaultVersion());
        assertSame(language, getResult.getLanguage());
        assertSame(language, getResult2.getLanguage());
        assertSame(language, versionWhereParserThrows.getLanguage());
        assertSame(language, latestVersion.getLanguage());
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionOfFile(String)}
     */
    @Test
    void testGetLanguageVersionOfFile2() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getLanguageVersionOfFile("."));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionOfFile(String)}
     */
    @Test
    void testGetLanguageVersionOfFile3() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getLanguageVersionOfFile("File Name"));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionOfFile(String)}
     */
    @Test
    void testGetLanguageVersionOfFile4() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getLanguageVersionOfFile(null));
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#getLanguageVersionOfFile(String)}
     */
    @Test
    void testGetLanguageVersionOfFile5() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getLanguageVersionOfFile(""));
    }

    /**
     * Method under test: {@link AbstractConfiguration#addRelativizeRoot(Path)}
     */
    @Test
    void testAddRelativizeRoot() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

        // Act
        pmdConfiguration.addRelativizeRoot(path);

        // Assert
        List<Path> relativizeRoots = pmdConfiguration.getRelativizeRoots();
        assertEquals(1, relativizeRoots.size());
        assertSame(path, relativizeRoots.get(0));
    }

    /**
     * Method under test: {@link AbstractConfiguration#addRelativizeRoots(List)}
     */
    @Test
    void testAddRelativizeRoots() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.addRelativizeRoots(new ArrayList<>());

        // Assert that nothing has changed
        assertTrue(pmdConfiguration.getRelativizeRoots().isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#addRelativizeRoots(List)}
     */
    @Test
    void testAddRelativizeRoots2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        ArrayList<Path> paths = new ArrayList<>();
        paths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

        // Act
        pmdConfiguration.addRelativizeRoots(paths);

        // Assert
        assertEquals(paths, pmdConfiguration.getRelativizeRoots());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getRelativizeRoots()}
     */
    @Test
    void testGetRelativizeRoots() {
        // Arrange, Act and Assert
        assertTrue((new PMDConfiguration()).getRelativizeRoots().isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getRelativizeRoots()}
     */
    @Test
    void testGetRelativizeRoots2() throws IOException {
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
        List<Path> actualRelativizeRoots = pmdConfiguration.getRelativizeRoots();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualRelativizeRoots.isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getUri()}
     */
    @Test
    void testGetUri() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getUri());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getUri()}
     */
    @Test
    void testGetUri2() throws IOException {
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
        URI actualUri = pmdConfiguration.getUri();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertNull(actualUri);
    }

    /**
     * Method under test: {@link AbstractConfiguration#setInputUri(URI)}
     */
    @Test
    void testSetInputUri() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        URI inputUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

        // Act
        pmdConfiguration.setInputUri(inputUri);

        // Assert
        assertSame(inputUri, pmdConfiguration.getUri());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setInputUri(URI)}
     */
    @Test
    void testSetInputUri2() throws IOException {
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
        URI inputUri = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

        // Act
        pmdConfiguration.setInputUri(inputUri);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertSame(inputUri, pmdConfiguration.getUri());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getInputPathList()}
     */
    @Test
    void testGetInputPathList() {
        // Arrange, Act and Assert
        assertTrue((new PMDConfiguration()).getInputPathList().isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getInputPathList()}
     */
    @Test
    void testGetInputPathList2() throws IOException {
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
        List<Path> actualInputPathList = pmdConfiguration.getInputPathList();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualInputPathList.isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setInputPathList(List)}
     */
    @Test
    void testSetInputPathList() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        // Act
        pmdConfiguration.setInputPathList(new ArrayList<>());

        // Assert
        assertTrue(pmdConfiguration.getInputPathList().isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setInputPathList(List)}
     */
    @Test
    void testSetInputPathList2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        ArrayList<Path> inputPaths = new ArrayList<>();
        inputPaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

        // Act
        pmdConfiguration.setInputPathList(inputPaths);

        // Assert
        assertEquals(inputPaths, pmdConfiguration.getInputPathList());
    }

    /**
     * Method under test: {@link AbstractConfiguration#addInputPath(Path)}
     */
    @Test
    void testAddInputPath() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        Path inputPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

        // Act
        pmdConfiguration.addInputPath(inputPath);

        // Assert
        List<Path> inputPathList = pmdConfiguration.getInputPathList();
        assertEquals(1, inputPathList.size());
        assertSame(inputPath, inputPathList.get(0));
    }

    /**
     * Method under test: {@link AbstractConfiguration#getInputFile()}
     */
    @Test
    void testGetInputFile() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getInputFile());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getInputFile()}
     */
    @Test
    void testGetInputFile2() throws IOException {
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
        Path actualInputFile = pmdConfiguration.getInputFile();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertNull(actualInputFile);
    }

    /**
     * Method under test: {@link AbstractConfiguration#getIgnoreFile()}
     */
    @Test
    void testGetIgnoreFile() {
        // Arrange, Act and Assert
        assertNull((new PMDConfiguration()).getIgnoreFile());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getIgnoreFile()}
     */
    @Test
    void testGetIgnoreFile2() throws IOException {
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
        Path actualIgnoreFile = pmdConfiguration.getIgnoreFile();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertNull(actualIgnoreFile);
    }

    /**
     * Method under test: {@link AbstractConfiguration#setInputFilePath(Path)}
     */
    @Test
    void testSetInputFilePath() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        Path inputFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

        // Act
        pmdConfiguration.setInputFilePath(inputFilePath);

        // Assert
        assertSame(inputFilePath, pmdConfiguration.getInputFile());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setInputFilePath(Path)}
     */
    @Test
    void testSetInputFilePath2() throws IOException {
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
        Path inputFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

        // Act
        pmdConfiguration.setInputFilePath(inputFilePath);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertSame(inputFilePath, pmdConfiguration.getInputFile());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setIgnoreFilePath(Path)}
     */
    @Test
    void testSetIgnoreFilePath() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        Path ignoreFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

        // Act
        pmdConfiguration.setIgnoreFilePath(ignoreFilePath);

        // Assert
        assertSame(ignoreFilePath, pmdConfiguration.getIgnoreFile());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setIgnoreFilePath(Path)}
     */
    @Test
    void testSetIgnoreFilePath2() throws IOException {
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
        Path ignoreFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

        // Act
        pmdConfiguration.setIgnoreFilePath(ignoreFilePath);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertSame(ignoreFilePath, pmdConfiguration.getIgnoreFile());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getExcludes()}
     */
    @Test
    void testGetExcludes() {
        // Arrange, Act and Assert
        assertTrue((new PMDConfiguration()).getExcludes().isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#getExcludes()}
     */
    @Test
    void testGetExcludes2() throws IOException {
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
        List<Path> actualExcludes = pmdConfiguration.getExcludes();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualExcludes.isEmpty());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setExcludes(List)}
     */
    @Test
    void testSetExcludes() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();
        ArrayList<Path> excludes = new ArrayList<>();

        // Act
        pmdConfiguration.setExcludes(excludes);

        // Assert
        assertTrue(pmdConfiguration.getRelativizeRoots().isEmpty());
        assertTrue(pmdConfiguration.getRuleSetPaths().isEmpty());
        assertSame(excludes, pmdConfiguration.getExcludes());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setExcludes(List)}
     */
    @Test
    void testSetExcludes2() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        ArrayList<Path> excludes = new ArrayList<>();
        excludes.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

        // Act
        pmdConfiguration.setExcludes(excludes);

        // Assert
        assertTrue(pmdConfiguration.getRelativizeRoots().isEmpty());
        assertTrue(pmdConfiguration.getRuleSetPaths().isEmpty());
        assertSame(excludes, pmdConfiguration.getExcludes());
    }

    /**
     * Method under test: {@link AbstractConfiguration#setExcludes(List)}
     */
    @Test
    void testSetExcludes3() {
        // Arrange
        PMDConfiguration pmdConfiguration = new PMDConfiguration();

        ArrayList<Path> excludes = new ArrayList<>();
        excludes.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
        excludes.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

        // Act
        pmdConfiguration.setExcludes(excludes);

        // Assert
        assertTrue(pmdConfiguration.getRelativizeRoots().isEmpty());
        assertTrue(pmdConfiguration.getRuleSetPaths().isEmpty());
        assertSame(excludes, pmdConfiguration.getExcludes());
    }

    /**
     * Method under test: {@link AbstractConfiguration#collectFilesRecursively()}
     */
    @Test
    void testCollectFilesRecursively() {
        // Arrange, Act and Assert
        assertTrue((new PMDConfiguration()).collectFilesRecursively());
    }

    /**
     * Method under test: {@link AbstractConfiguration#collectFilesRecursively()}
     */
    @Test
    void testCollectFilesRecursively2() throws IOException {
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
        boolean actualCollectFilesRecursivelyResult = pmdConfiguration.collectFilesRecursively();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualCollectFilesRecursivelyResult);
    }

    /**
     * Method under test:
     * {@link AbstractConfiguration#collectFilesRecursively(boolean)}
     */
    @Test
    void testCollectFilesRecursively3() throws IOException {
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
        pmdConfiguration.collectFilesRecursively(true);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    }

    /**
     * Method under test: {@link AbstractConfiguration#isFailOnViolation()}
     */
    @Test
    void testIsFailOnViolation() {
        // Arrange, Act and Assert
        assertTrue((new PMDConfiguration()).isFailOnViolation());
    }

    /**
     * Method under test: {@link AbstractConfiguration#isFailOnViolation()}
     */
    @Test
    void testIsFailOnViolation2() throws IOException {
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
        boolean actualIsFailOnViolationResult = pmdConfiguration.isFailOnViolation();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualIsFailOnViolationResult);
    }

    /**
     * Method under test: {@link AbstractConfiguration#setFailOnViolation(boolean)}
     */
    @Test
    void testSetFailOnViolation() throws IOException {
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
        pmdConfiguration.setFailOnViolation(true);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    }

    /**
     * Method under test: {@link AbstractConfiguration#isFailOnError()}
     */
    @Test
    void testIsFailOnError() {
        // Arrange, Act and Assert
        assertTrue((new PMDConfiguration()).isFailOnError());
    }

    /**
     * Method under test: {@link AbstractConfiguration#isFailOnError()}
     */
    @Test
    void testIsFailOnError2() throws IOException {
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
        boolean actualIsFailOnErrorResult = pmdConfiguration.isFailOnError();

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
        assertTrue(actualIsFailOnErrorResult);
    }

    /**
     * Method under test: {@link AbstractConfiguration#setFailOnError(boolean)}
     */
    @Test
    void testSetFailOnError() throws IOException {
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
        pmdConfiguration.setFailOnError(true);

        // Assert
        verify(urlStreamHandlerFactory).createURLStreamHandler(eq("jar"));
    }
}
