package net.sourceforge.pmd.cache.internal;

import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

import net.sourceforge.pmd.lang.document.FileId;

import net.sourceforge.pmd.lang.document.FileLocation;
import net.sourceforge.pmd.reporting.RuleViolation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CachedRuleViolationDiffblueTest {
    /**
     * Method under test: {@link CachedRuleViolation#getRule()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetRule() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        DataInputStream stream = mock(DataInputStream.class);

        // Act
        CachedRuleViolation.loadFromStream(stream, FileId.STDIN, new CachedRuleMapper()).getRule();
    }

    /**
     * Method under test:
     * {@link CachedRuleViolation#loadFromStream(DataInputStream, FileId, CachedRuleMapper)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testLoadFromStream() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        DataInputStream stream = mock(DataInputStream.class);

        // Act
        CachedRuleViolation.loadFromStream(stream, FileId.STDIN, new CachedRuleMapper());
    }

    /**
     * Method under test:
     * {@link CachedRuleViolation#storeToStream(DataOutputStream, RuleViolation)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStoreToStream() throws IOException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        DataOutputStream stream = new DataOutputStream(new ByteArrayOutputStream(1));
        DataInputStream stream2 = mock(DataInputStream.class);

        // Act
        CachedRuleViolation.storeToStream(stream,
                CachedRuleViolation.loadFromStream(stream2, FileId.STDIN, new CachedRuleMapper()));
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link CachedRuleViolation#getAdditionalInfo()}
     *   <li>{@link CachedRuleViolation#getDescription()}
     *   <li>{@link CachedRuleViolation#getLocation()}
     * </ul>
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGettersAndSetters() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Exception in arrange section.
        //   Diffblue Cover was unable to construct an instance of the class under test using
        //   net.sourceforge.pmd.cache.internal.CachedRuleViolation.getAdditionalInfo().
        //   The arrange section threw
        //   java.lang.NullPointerException
        //   See https://diff.blue/R081 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        CachedRuleViolation cachedRuleViolation = null;

        // Act
        Map<String, String> actualAdditionalInfo = cachedRuleViolation.getAdditionalInfo();
        String actualDescription = cachedRuleViolation.getDescription();
        FileLocation actualLocation = cachedRuleViolation.getLocation();

        // Assert
        // TODO: Add assertions on result
    }
}
