package net.sourceforge.pmd.benchmark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.function.Supplier;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class TimeTrackerDiffblueTest {
    /**
     * Method under test: {@link TimeTracker#startGlobalTracking()}
     */
    @Test
    void testStartGlobalTracking() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        TimeTracker.startGlobalTracking();
    }

    /**
     * Method under test: {@link TimeTracker#stopGlobalTracking()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testStopGlobalTracking() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException
        //       at java.base/java.util.LinkedList.removeFirst(LinkedList.java:274)
        //       at java.base/java.util.Collections$AsLIFOQueue.remove(Collections.java:5761)
        //       at net.sourceforge.pmd.benchmark.TimeTracker.finishOperation(TimeTracker.java:145)
        //       at net.sourceforge.pmd.benchmark.TimeTracker.finishThread(TimeTracker.java:101)
        //       at net.sourceforge.pmd.benchmark.TimeTracker.stopGlobalTracking(TimeTracker.java:70)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        TimeTracker.stopGlobalTracking();
    }

    /**
     * Method under test: {@link TimeTracker#initThread()}
     */
    @Test
    void testInitThread() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        TimeTracker.initThread();
    }

    /**
     * Method under test: {@link TimeTracker#finishThread()}
     */
    @Test
    void testFinishThread() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        TimeTracker.finishThread();
    }

    /**
     * Method under test: {@link TimeTracker#startOperation(TimedOperationCategory)}
     */
    @Test
    void testStartOperation() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TimedOperationImpl.closed

        // Arrange and Act
        TimeTracker.startOperation(TimedOperationCategory.RULE);
    }

    /**
     * Method under test:
     * {@link TimeTracker#startOperation(TimedOperationCategory, String)}
     */
    @Test
    void testStartOperation2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     TimedOperationImpl.closed

        // Arrange and Act
        TimeTracker.startOperation(TimedOperationCategory.RULE, "Label");
    }

    /**
     * Method under test: {@link TimeTracker#finishOperation(long)}
     */
    @Test
    void testFinishOperation() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange and Act
        TimeTracker.finishOperation(3L);
    }

    /**
     * Method under test: {@link TimeTracker#bench(String, Runnable)}
     */
    @Test
    void testBench() {
        // Arrange
        Runnable runnable = mock(Runnable.class);
        doNothing().when(runnable).run();

        // Act
        TimeTracker.bench("Label", runnable);

        // Assert that nothing has changed
        verify(runnable).run();
    }

    /**
     * Method under test: {@link TimeTracker#bench(String, Supplier)}
     */
    @Test
    void testBench2() {
        // Arrange
        Supplier<Object> runnable = mock(Supplier.class);
        when(runnable.get()).thenReturn("Get");

        // Act
        Object actualBenchResult = TimeTracker.bench("Label", runnable);

        // Assert
        verify(runnable).get();
        assertEquals("Get", actualBenchResult);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link TimeTracker.TimedOperationKey#equals(Object)}
     *   <li>{@link TimeTracker.TimedOperationKey#hashCode()}
     * </ul>
     */
    @Test
    void testTimedOperationKeyEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                "Label");
        TimeTracker.TimedOperationKey timedOperationKey2 = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                "Label");

        // Act and Assert
        assertEquals(timedOperationKey, timedOperationKey2);
        int expectedHashCodeResult = timedOperationKey.hashCode();
        assertEquals(expectedHashCodeResult, timedOperationKey2.hashCode());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link TimeTracker.TimedOperationKey#equals(Object)}
     *   <li>{@link TimeTracker.TimedOperationKey#hashCode()}
     * </ul>
     */
    @Test
    void testTimedOperationKeyEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                "Label");

        // Act and Assert
        assertEquals(timedOperationKey, timedOperationKey);
        int expectedHashCodeResult = timedOperationKey.hashCode();
        assertEquals(expectedHashCodeResult, timedOperationKey.hashCode());
    }

    /**
     * Method under test: {@link TimeTracker.TimedOperationKey#equals(Object)}
     */
    @Test
    void testTimedOperationKeyEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(null, "Label");

        // Act and Assert
        assertNotEquals(timedOperationKey, new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE, "Label"));
    }

    /**
     * Method under test: {@link TimeTracker.TimedOperationKey#equals(Object)}
     */
    @Test
    void testTimedOperationKeyEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
        // Arrange
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                null);

        // Act and Assert
        assertNotEquals(timedOperationKey, new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE, "Label"));
    }

    /**
     * Method under test: {@link TimeTracker.TimedOperationKey#equals(Object)}
     */
    @Test
    void testTimedOperationKeyEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange, Act and Assert
        assertNotEquals(new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE, "Label"), null);
    }

    /**
     * Method under test: {@link TimeTracker.TimedOperationKey#equals(Object)}
     */
    @Test
    void testTimedOperationKeyEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange, Act and Assert
        assertNotEquals(new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE, "Label"),
                "Different type to TimedOperationKey");
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>
     * {@link TimeTracker.TimedOperationKey#TimedOperationKey(TimedOperationCategory, String)}
     *   <li>{@link TimeTracker.TimedOperationKey#toString()}
     * </ul>
     */
    @Test
    void testTimedOperationKeyGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals("TimedOperationKey [category=RULE, label=Label]",
                (new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE, "Label")).toString());
    }

    /**
     * Method under test:
     * {@link TimeTracker.TimedResult#accumulate(TimeTracker.TimerEntry, long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTimedResultAccumulate() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot read field "start" because "timerEntry" is null
        //       at net.sourceforge.pmd.benchmark.TimeTracker$TimedResult.accumulate(TimeTracker.java:208)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new TimeTracker.TimedResult()).accumulate(null, 1L);
    }

    /**
     * Method under test:
     * {@link TimeTracker.TimedResult#mergeTimes(TimeTracker.TimedResult)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testTimedResultMergeTimes() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot read field "totalTimeNanos" because "timedResult" is null
        //       at net.sourceforge.pmd.benchmark.TimeTracker$TimedResult.mergeTimes(TimeTracker.java:223)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        (new TimeTracker.TimedResult()).mergeTimes(null);
    }

    /**
     * Method under test:
     * {@link TimeTracker.TimedResult#mergeTimes(TimeTracker.TimedResult)}
     */
    @Test
    void testTimedResultMergeTimes2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        TimeTracker.TimedResult timedResult = new TimeTracker.TimedResult();

        // Act
        timedResult.mergeTimes(new TimeTracker.TimedResult());
    }
}
