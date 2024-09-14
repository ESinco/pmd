package net.sourceforge.pmd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PMDVersionDiffblueTest {
    /**
     * Method under test: {@link PMDVersion#getNextMajorRelease()}
     */
    @Test
    void testGetNextMajorRelease() {
        // Arrange, Act and Assert
        assertEquals("8.0.0", PMDVersion.getNextMajorRelease());
    }

    /**
     * Method under test: {@link PMDVersion#isUnknown()}
     */
    @Test
    void testIsUnknown() {
        // Arrange, Act and Assert
        assertFalse(PMDVersion.isUnknown());
    }

    /**
     * Method under test: {@link PMDVersion#isSnapshot()}
     */
    @Test
    void testIsSnapshot() {
        // Arrange, Act and Assert
        assertTrue(PMDVersion.isSnapshot());
    }

    /**
     * Method under test: {@link PMDVersion#getFullVersionName()}
     */
    @Test
    void testGetFullVersionName() {
        // Arrange, Act and Assert
        assertEquals("PMD 7.4.0-SNAPSHOT (unknown, unknown)", PMDVersion.getFullVersionName());
    }
}
