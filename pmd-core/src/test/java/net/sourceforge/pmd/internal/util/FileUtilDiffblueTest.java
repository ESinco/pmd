package net.sourceforge.pmd.internal.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

class FileUtilDiffblueTest {
    /**
     * Method under test: {@link FileUtil#getFileNameWithoutExtension(String)}
     */
    @Test
    void testGetFileNameWithoutExtension() {
        // Arrange, Act and Assert
        assertEquals("foo", FileUtil.getFileNameWithoutExtension("foo.txt"));
        assertEquals("File Name", FileUtil.getFileNameWithoutExtension("File Name"));
    }

    /**
     * Method under test: {@link FileUtil#findPatternInFile(File, String)}
     */
    @Test
    void testFindPatternInFile() {
        // Arrange, Act and Assert
        assertFalse(
                FileUtil.findPatternInFile(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(), "Pattern"));
        assertThrows(RuntimeException.class, () -> FileUtil
                .findPatternInFile(Paths.get(System.getProperty("java.io.tmpdir"), "Pattern").toFile(), "Pattern"));
    }
}
