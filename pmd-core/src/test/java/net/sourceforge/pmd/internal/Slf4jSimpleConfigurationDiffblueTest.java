package net.sourceforge.pmd.internal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.event.Level;

class Slf4jSimpleConfigurationDiffblueTest {
    /**
     * Method under test:
     * {@link Slf4jSimpleConfiguration#reconfigureDefaultLogLevel(Level)}
     */
    @Test
    void testReconfigureDefaultLogLevel() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        Slf4jSimpleConfiguration.reconfigureDefaultLogLevel(Level.ERROR);
    }

    /**
     * Method under test: {@link Slf4jSimpleConfiguration#getDefaultLogLevel()}
     */
    @Test
    void testGetDefaultLogLevel() {
        // Arrange, Act and Assert
        assertEquals(Level.INFO, Slf4jSimpleConfiguration.getDefaultLogLevel());
    }

    /**
     * Method under test: {@link Slf4jSimpleConfiguration#disableLogging(Class)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDisableLogging() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Class.getName()" because "clazz" is null
        //       at net.sourceforge.pmd.internal.Slf4jSimpleConfiguration.disableLogging(Slf4jSimpleConfiguration.java:128)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        Slf4jSimpleConfiguration.disableLogging(null);
    }

    /**
     * Method under test: {@link Slf4jSimpleConfiguration#disableLogging(Class)}
     */
    @Test
    void testDisableLogging2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        Class<Object> clazz = Object.class;

        // Act
        Slf4jSimpleConfiguration.disableLogging(clazz);
    }

    /**
     * Method under test: {@link Slf4jSimpleConfiguration#isSimpleLogger()}
     */
    @Test
    void testIsSimpleLogger() {
        // Arrange, Act and Assert
        assertTrue(Slf4jSimpleConfiguration.isSimpleLogger());
    }

    /**
     * Method under test: {@link Slf4jSimpleConfiguration#installJulBridge()}
     */
    @Test
    void testInstallJulBridge() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        Slf4jSimpleConfiguration.installJulBridge();
    }
}
