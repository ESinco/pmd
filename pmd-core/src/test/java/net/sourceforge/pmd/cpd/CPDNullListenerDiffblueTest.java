package net.sourceforge.pmd.cpd;

import org.junit.jupiter.api.Test;

class CPDNullListenerDiffblueTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link CPDNullListener#addedFile(int)}
     *   <li>{@link CPDNullListener#phaseUpdate(int)}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   There are no fields that could be asserted on.

        // Arrange
        CPDNullListener cpdNullListener = new CPDNullListener();

        // Act
        cpdNullListener.addedFile(3);
        cpdNullListener.phaseUpdate(1);
    }
}
