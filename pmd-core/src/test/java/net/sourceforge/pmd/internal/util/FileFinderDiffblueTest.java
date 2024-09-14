package net.sourceforge.pmd.internal.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FileFinderDiffblueTest {
    /**
     * Method under test:
     * {@link FileFinder#findFilesFrom(File, FilenameFilter, boolean)}
     */
    @Test
    void testFindFilesFrom() {
        // Arrange
        FileFinder fileFinder = new FileFinder();

        // Act and Assert
        assertTrue(
                fileFinder
                        .findFilesFrom(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile(),
                                mock(FilenameFilter.class), true)
                        .isEmpty());
    }

    /**
     * Method under test:
     * {@link FileFinder#findFilesFrom(File, FilenameFilter, boolean)}
     */
    @Test
    void testFindFilesFrom2() {
        // Arrange
        FileFinder fileFinder = new FileFinder();
        File dir = Paths.get(System.getProperty("java.io.tmpdir"), "").toFile();
        FilenameFilter filter = mock(FilenameFilter.class);
        when(filter.accept(Mockito.<File>any(), Mockito.<String>any())).thenReturn(true);

        // Act
        List<File> actualFindFilesFromResult = fileFinder.findFilesFrom(dir, filter, true);

        // Assert
        verify(filter, atLeast(1)).accept(isA(File.class), Mockito.<String>any());
        assertEquals(375, actualFindFilesFromResult.size());
        File getResult = actualFindFilesFromResult.get(0);
        assertEquals("029a9c29-7e3d-4b91-8991-6bd3d75943e9.tmp", getResult.getName());
        File getResult2 = actualFindFilesFromResult.get(1);
        assertEquals("03a6a909-8aae-49c6-9295-4797343bde3f.tmp", getResult2.getName());
        File getResult3 = actualFindFilesFromResult.get(2);
        assertEquals("0a931db2-6c84-4152-9bf2-da6decacc3b4.tmp", getResult3.getName());
        File getResult4 = actualFindFilesFromResult.get(3);
        assertEquals("0d6d049d-d36d-4c93-891f-e7474e26c04a.tmp", getResult4.getName());
        File getResult5 = actualFindFilesFromResult.get(4);
        assertEquals("0de0821e-ee03-4afa-8359-646f07126336.tmp", getResult5.getName());
        File getResult6 = actualFindFilesFromResult.get(5);
        assertEquals("17dfff7c-1d81-4c38-9df3-43e11d572f1f.tmp", getResult6.getName());
        File getResult7 = actualFindFilesFromResult.get(373);
        assertEquals("Tilix.ico", getResult7.getName());
        File getResult8 = actualFindFilesFromResult.get(372);
        assertEquals("Variations", getResult8.getName());
        File getResult9 = actualFindFilesFromResult.get(369);
        assertEquals("edgeSettings_2.0-48b11410dc937a1723bf4c5ad33ecdb286d8ec69544241bc373f753e64b396c1",
                getResult9.getName());
        File getResult10 = actualFindFilesFromResult.get(370);
        assertEquals("topTraffic", getResult10.getName());
        File getResult11 = actualFindFilesFromResult.get(371);
        assertEquals("topTraffic_638004170464094982", getResult11.getName());
        File getResult12 = actualFindFilesFromResult.get(374);
        assertEquals("ubuntu-desktop-installer_ubuntu-desktop-installer.ico", getResult12.getName());
        assertTrue(getResult.isAbsolute());
        assertTrue(getResult2.isAbsolute());
        assertTrue(getResult3.isAbsolute());
        assertTrue(getResult4.isAbsolute());
        assertTrue(getResult9.isAbsolute());
        assertTrue(getResult10.isAbsolute());
        assertTrue(getResult11.isAbsolute());
        assertTrue(getResult8.isAbsolute());
        assertTrue(getResult7.isAbsolute());
        assertTrue(getResult12.isAbsolute());
        assertTrue(getResult5.isAbsolute());
        assertTrue(getResult6.isAbsolute());
    }

    /**
     * Method under test:
     * {@link FileFinder#findFilesFrom(File, FilenameFilter, boolean)}
     */
    @Test
    void testFindFilesFrom3() {
        // Arrange
        FileFinder fileFinder = new FileFinder();
        File dir = Paths.get(System.getProperty("java.io.tmpdir"), "").toFile();
        FilenameFilter filter = mock(FilenameFilter.class);
        when(filter.accept(Mockito.<File>any(), Mockito.<String>any())).thenReturn(true);

        // Act
        List<File> actualFindFilesFromResult = fileFinder.findFilesFrom(dir, filter, false);

        // Assert
        verify(filter, atLeast(1)).accept(isA(File.class), Mockito.<String>any());
        assertEquals(109, actualFindFilesFromResult.size());
        File getResult = actualFindFilesFromResult.get(0);
        assertEquals("029a9c29-7e3d-4b91-8991-6bd3d75943e9.tmp", getResult.getName());
        File getResult2 = actualFindFilesFromResult.get(1);
        assertEquals("03a6a909-8aae-49c6-9295-4797343bde3f.tmp", getResult2.getName());
        File getResult3 = actualFindFilesFromResult.get(2);
        assertEquals("0a931db2-6c84-4152-9bf2-da6decacc3b4.tmp", getResult3.getName());
        File getResult4 = actualFindFilesFromResult.get(3);
        assertEquals("0d6d049d-d36d-4c93-891f-e7474e26c04a.tmp", getResult4.getName());
        File getResult5 = actualFindFilesFromResult.get(4);
        assertEquals("0de0821e-ee03-4afa-8359-646f07126336.tmp", getResult5.getName());
        File getResult6 = actualFindFilesFromResult.get(5);
        assertEquals("17dfff7c-1d81-4c38-9df3-43e11d572f1f.tmp", getResult6.getName());
        File getResult7 = actualFindFilesFromResult.get(103);
        assertEquals("staticcheck565697888", getResult7.getName());
        File getResult8 = actualFindFilesFromResult.get(104);
        assertEquals("staticcheck650257154", getResult8.getName());
        File getResult9 = actualFindFilesFromResult.get(105);
        assertEquals("staticcheck80591882", getResult9.getName());
        File getResult10 = actualFindFilesFromResult.get(106);
        assertEquals("test.txt", getResult10.getName());
        File getResult11 = actualFindFilesFromResult.get(107);
        assertEquals("vscode-inno-updater-1725599031.log", getResult11.getName());
        File getResult12 = actualFindFilesFromResult.get(108);
        assertEquals("vscode-inno-updater-1726193603.log", getResult12.getName());
        assertTrue(getResult.isAbsolute());
        assertTrue(getResult2.isAbsolute());
        assertTrue(getResult7.isAbsolute());
        assertTrue(getResult8.isAbsolute());
        assertTrue(getResult9.isAbsolute());
        assertTrue(getResult10.isAbsolute());
        assertTrue(getResult11.isAbsolute());
        assertTrue(getResult12.isAbsolute());
        assertTrue(getResult3.isAbsolute());
        assertTrue(getResult4.isAbsolute());
        assertTrue(getResult5.isAbsolute());
        assertTrue(getResult6.isAbsolute());
    }
}
