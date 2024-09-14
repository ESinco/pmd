package net.sourceforge.pmd.cache.internal;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

import org.junit.jupiter.api.Test;

class NoopFingerprinterDiffblueTest {
    /**
     * Method under test: {@link NoopFingerprinter#appliesTo(String)}
     */
    @Test
    void testAppliesTo() {
        // Arrange, Act and Assert
        assertTrue((new NoopFingerprinter()).appliesTo("File Extension"));
    }

    /**
     * Method under test: {@link NoopFingerprinter#fingerprint(URL, Checksum)}
     */
    @Test
    void testFingerprint() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        NoopFingerprinter noopFingerprinter = new NoopFingerprinter();
        URL entry = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL();

        // Act
        noopFingerprinter.fingerprint(entry, new Adler32());
    }
}
