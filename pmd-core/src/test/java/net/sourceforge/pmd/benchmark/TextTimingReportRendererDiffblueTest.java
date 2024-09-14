package net.sourceforge.pmd.benchmark;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

class TextTimingReportRendererDiffblueTest {
    /**
     * Method under test:
     * {@link TextTimingReportRenderer#render(TimingReport, Writer)}
     */
    @Test
    void testRender() throws IOException {
        // Arrange
        TextTimingReportRenderer textTimingReportRenderer = new TextTimingReportRenderer();
        TimingReport report = new TimingReport(1L, new HashMap<>());

        StringWriter writer0 = new StringWriter();

        // Act
        textTimingReportRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "------------------------------------------<<< Summary  >>>------------------------------------------" + "\r\n"
                        + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "\r\n"
                        + "-------------------------------------------<<< Total  >>>-------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "Wall Clock Time                                         0,0010\r\n",
                writer0.toString());
    }

    /**
     * Method under test:
     * {@link TextTimingReportRenderer#render(TimingReport, Writer)}
     */
    @Test
    void testRender2() throws IOException {
        // Arrange
        TextTimingReportRenderer textTimingReportRenderer = new TextTimingReportRenderer();
        TimingReport report = new TimingReport(Long.MAX_VALUE, new HashMap<>());

        StringWriter writer0 = new StringWriter();

        // Act
        textTimingReportRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "------------------------------------------<<< Summary  >>>------------------------------------------" + "\r\n"
                        + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "\r\n"
                        + "-------------------------------------------<<< Total  >>>-------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "Wall Clock Time                                   9223372036854776,0000\r\n",
                writer0.toString());
    }

    /**
     * Method under test:
     * {@link TextTimingReportRenderer#render(TimingReport, Writer)}
     */
    @Test
    void testRender3() throws IOException {
        // Arrange
        TextTimingReportRenderer textTimingReportRenderer = new TextTimingReportRenderer();

        HashMap<TimeTracker.TimedOperationKey, TimeTracker.TimedResult> accumulatedResults = new HashMap<>();
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                "Summary");

        accumulatedResults.put(timedOperationKey, new TimeTracker.TimedResult());
        TimingReport report = new TimingReport(1L, accumulatedResults);

        StringWriter writer0 = new StringWriter();

        // Act
        textTimingReportRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "--------------------------------------------<<< Rule >>>--------------------------------------------" + "\r\n"
                        + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n"
                        + "Summary                                                 0,0000           0,0000\r\n" + "\r\n"
                        + "Total Rule                                              0,0000           0,0000\r\n" + "\r\n"
                        + "------------------------------------------<<< Summary  >>>------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "\r\n"
                        + "-------------------------------------------<<< Total  >>>-------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "Wall Clock Time                                         0,0010\r\n",
                writer0.toString());
    }

    /**
     * Method under test:
     * {@link TextTimingReportRenderer#render(TimingReport, Writer)}
     */
    @Test
    void testRender4() throws IOException {
        // Arrange
        TextTimingReportRenderer textTimingReportRenderer = new TextTimingReportRenderer();

        HashMap<TimeTracker.TimedOperationKey, TimeTracker.TimedResult> accumulatedResults = new HashMap<>();
        accumulatedResults.computeIfPresent(new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE, "Summary"),
                mock(BiFunction.class));
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                "Summary");

        accumulatedResults.put(timedOperationKey, new TimeTracker.TimedResult());
        TimingReport report = new TimingReport(1L, accumulatedResults);

        StringWriter writer0 = new StringWriter();

        // Act
        textTimingReportRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "--------------------------------------------<<< Rule >>>--------------------------------------------" + "\r\n"
                        + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n"
                        + "Summary                                                 0,0000           0,0000\r\n" + "\r\n"
                        + "Total Rule                                              0,0000           0,0000\r\n" + "\r\n"
                        + "------------------------------------------<<< Summary  >>>------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "\r\n"
                        + "-------------------------------------------<<< Total  >>>-------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "Wall Clock Time                                         0,0010\r\n",
                writer0.toString());
    }

    /**
     * Method under test:
     * {@link TextTimingReportRenderer#render(TimingReport, Writer)}
     */
    @Test
    void testRender5() throws IOException {
        // Arrange
        TextTimingReportRenderer textTimingReportRenderer = new TextTimingReportRenderer();

        HashMap<TimeTracker.TimedOperationKey, TimeTracker.TimedResult> accumulatedResults = new HashMap<>();
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                null);

        accumulatedResults.put(timedOperationKey, new TimeTracker.TimedResult());
        TimingReport report = new TimingReport(1L, accumulatedResults);

        StringWriter writer0 = new StringWriter();

        // Act
        textTimingReportRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "------------------------------------------<<< Summary  >>>------------------------------------------" + "\r\n"
                        + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n"
                        + "Rule                                                    0,0000           0,0000\r\n" + "\r\n"
                        + "-------------------------------------------<<< Total  >>>-------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "Wall Clock Time                                         0,0010\r\n",
                writer0.toString());
    }

    /**
     * Method under test:
     * {@link TextTimingReportRenderer#render(TimingReport, Writer)}
     */
    @Test
    void testRender6() throws IOException {
        // Arrange
        TextTimingReportRenderer textTimingReportRenderer = new TextTimingReportRenderer();

        HashMap<TimeTracker.TimedOperationKey, TimeTracker.TimedResult> accumulatedResults = new HashMap<>();
        TimeTracker.TimedOperationKey timedOperationKey = new TimeTracker.TimedOperationKey(TimedOperationCategory.RULE,
                "net.sourceforge.pmd.benchmark.TimeTracker$TimedOperationKey");

        accumulatedResults.put(timedOperationKey, new TimeTracker.TimedResult());
        TimingReport report = new TimingReport(1L, accumulatedResults);

        StringWriter writer0 = new StringWriter();

        // Act
        textTimingReportRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "--------------------------------------------<<< Rule >>>--------------------------------------------" + "\r\n"
                        + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n"
                        + "net.sourceforge.pmd.benchmark.TimeTracker$TimedOperationKey      0,0000           0,0000\r\n" + "\r\n"
                        + "Total Rule                                              0,0000           0,0000\r\n" + "\r\n"
                        + "------------------------------------------<<< Summary  >>>------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "\r\n"
                        + "-------------------------------------------<<< Total  >>>-------------------------------------------"
                        + "\r\n" + "Label                                              Time (secs) Self Time (secs)  # Calls    "
                        + " Counter\r\n" + "\r\n" + "Wall Clock Time                                         0,0010\r\n",
                writer0.toString());
    }
}
