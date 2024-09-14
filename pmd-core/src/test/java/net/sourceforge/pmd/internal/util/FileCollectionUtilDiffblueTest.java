package net.sourceforge.pmd.internal.util;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.AbstractConfiguration;
import net.sourceforge.pmd.PMDConfiguration;
import net.sourceforge.pmd.cpd.CPDConfiguration;

import net.sourceforge.pmd.lang.document.FileCollector;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class FileCollectionUtilDiffblueTest {
    /**
     * Method under test:
     * {@link FileCollectionUtil#collectFiles(AbstractConfiguration, FileCollector)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCollectFiles() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "net.sourceforge.pmd.lang.document.FileCollector.setCharset(java.nio.charset.Charset)" because "collector" is null
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.collectFiles(FileCollectionUtil.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        FileCollectionUtil.collectFiles(new PMDConfiguration(), null);
    }

    /**
     * Method under test:
     * {@link FileCollectionUtil#collectFiles(CPDConfiguration, FileCollector)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCollectFiles2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "net.sourceforge.pmd.lang.document.FileCollector.setCharset(java.nio.charset.Charset)" because "collector" is null
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.collectFiles(FileCollectionUtil.java:75)
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.collectFiles(FileCollectionUtil.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        FileCollectionUtil.collectFiles(new CPDConfiguration(), null);
    }

    /**
     * Method under test:
     * {@link FileCollectionUtil#collectFiles(FileCollector, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCollectFiles3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "net.sourceforge.pmd.lang.document.FileCollector.addFile(java.nio.file.Path)" because "collector" is null
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.addRoot(FileCollectionUtil.java:150)
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.collectFiles(FileCollectionUtil.java:112)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ArrayList<Path> filePaths = new ArrayList<>();
        filePaths.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

        // Act
        FileCollectionUtil.collectFiles(null, filePaths);
    }

    /**
     * Method under test:
     * {@link FileCollectionUtil#collectFiles(FileCollector, List)}
     */
    @Test
    void testCollectFiles4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        FileCollectionUtil.collectFiles(null, new ArrayList<>());
    }

    /**
     * Method under test:
     * {@link FileCollectionUtil#collectFileList(FileCollector, Path)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCollectFileList() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "net.sourceforge.pmd.lang.document.FileCollector.getReporter()" because "collector" is null
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.collectFileList(FileCollectionUtil.java:122)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        FileCollectionUtil.collectFileList(null, Paths.get(System.getProperty("java.io.tmpdir"), "Reading file list {}."));
    }

    /**
     * Method under test:
     * {@link FileCollectionUtil#collectFileList(FileCollector, Path)}
     */
    @Test
    void testCollectFileList2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        FileCollectionUtil.collectFileList(null, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
    }

    /**
     * Method under test: {@link FileCollectionUtil#collectDB(FileCollector, URI)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCollectDB() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "net.sourceforge.pmd.lang.document.FileCollector.getReporter()" because "collector" is null
        //       at net.sourceforge.pmd.internal.util.FileCollectionUtil.collectDB(FileCollectionUtil.java:180)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        FileCollectionUtil.collectDB(null, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());
    }
}
