package net.sourceforge.pmd.cache.internal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ZipFileFingerprinterDiffblueTest {
    /**
     * Method under test: {@link ZipFileFingerprinter#appliesTo(String)}
     */
    @Test
    void testAppliesTo() {
        // Arrange, Act and Assert
        assertFalse((new ZipFileFingerprinter()).appliesTo("File Extension"));
        assertTrue((new ZipFileFingerprinter()).appliesTo("jar"));
    }

    /**
     * Method under test: {@link ZipFileFingerprinter#fingerprint(URL, Checksum)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFingerprint() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.zip.ZipException: zip file is empty
        //       at java.base/java.util.zip.ZipFile$Source.zerror(ZipFile.java:1766)
        //       at java.base/java.util.zip.ZipFile$Source.findEND(ZipFile.java:1550)
        //       at java.base/java.util.zip.ZipFile$Source.initCEN(ZipFile.java:1645)
        //       at java.base/java.util.zip.ZipFile$Source.<init>(ZipFile.java:1483)
        //       at java.base/java.util.zip.ZipFile$Source.get(ZipFile.java:1445)
        //       at java.base/java.util.zip.ZipFile$CleanableResource.<init>(ZipFile.java:717)
        //       at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:251)
        //       at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:180)
        //       at java.base/java.util.zip.ZipFile.<init>(ZipFile.java:194)
        //       at net.sourceforge.pmd.cache.internal.ZipFileFingerprinter.fingerprint(ZipFileFingerprinter.java:64)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        ZipFileFingerprinter zipFileFingerprinter = new ZipFileFingerprinter();
        URL entry = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

        // Act
        zipFileFingerprinter.fingerprint(entry, new Adler32());
    }

    /**
     * Method under test: {@link ZipFileFingerprinter#fingerprint(URL, Checksum)}
     */
    @Test
    void testFingerprint2() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        ZipFileFingerprinter zipFileFingerprinter = new ZipFileFingerprinter();
        URL entry = Paths.get(System.getProperty("java.io.tmpdir"), "").toUri().toURL();

        // Act
        zipFileFingerprinter.fingerprint(entry, new Adler32());
    }
}
