package net.sourceforge.pmd.cpd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import net.sourceforge.pmd.lang.document.FileId;
import org.junit.jupiter.api.Test;

class CSVRendererDiffblueTest {
    /**
     * Method under test: {@link CSVRenderer#CSVRenderer()}
     */
    @Test
    void testNewCSVRenderer() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CSVRenderer.lineCountPerFile
        //     CSVRenderer.separator

        // Arrange and Act
        new CSVRenderer();
    }

    /**
     * Method under test: {@link CSVRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender() throws IOException {
        // Arrange
        CSVRenderer csvRenderer = new CSVRenderer('A');
        ArrayList<Match> matches = new ArrayList<>();
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer = new StringWriter();

        // Act
        csvRenderer.render(report, writer);

        // Assert
        assertEquals("linesAtokensAoccurrences\r\n", writer.toString());
    }

    /**
     * Method under test: {@link CSVRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender2() throws IOException {
        // Arrange
        CSVRenderer csvRenderer = new CSVRenderer(true);
        ArrayList<Match> matches = new ArrayList<>();
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer = new StringWriter();

        // Act
        csvRenderer.render(report, writer);

        // Assert
        assertEquals("tokens,occurrences\r\n", writer.toString());
    }

    /**
     * Method under test: {@link CSVRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender3() throws IOException {
        // Arrange
        CSVRenderer csvRenderer = new CSVRenderer('A');

        ArrayList<Match> matches = new ArrayList<>();
        Mark first = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer = new StringWriter();

        // Act
        csvRenderer.render(report, writer);

        // Assert
        assertEquals("linesAtokensAoccurrences\r\n1A3A1A2AC:\\var\\Bar.java\r\n", writer.toString());
    }

    /**
     * Method under test: {@link CSVRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender4() throws IOException {
        // Arrange
        CSVRenderer csvRenderer = new CSVRenderer('A');

        ArrayList<Match> matches = new ArrayList<>();
        Mark first = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        Mark first2 = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first2, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer = new StringWriter();

        // Act
        csvRenderer.render(report, writer);

        // Assert
        assertEquals("linesAtokensAoccurrences\r\n1A3A1A2AC:\\var\\Bar.java\r\n1A3A1A2AC:\\var\\Bar.java\r\n",
                writer.toString());
    }

    /**
     * Method under test: {@link CSVRenderer#CSVRenderer(char)}
     */
    @Test
    void testNewCSVRenderer2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CSVRenderer.lineCountPerFile
        //     CSVRenderer.separator

        // Arrange and Act
        new CSVRenderer('A');
    }

    /**
     * Method under test: {@link CSVRenderer#CSVRenderer(char, boolean)}
     */
    @Test
    void testNewCSVRenderer3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CSVRenderer.lineCountPerFile
        //     CSVRenderer.separator

        // Arrange and Act
        new CSVRenderer('A', true);

    }

    /**
     * Method under test: {@link CSVRenderer#CSVRenderer(boolean)}
     */
    @Test
    void testNewCSVRenderer4() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     CSVRenderer.lineCountPerFile
        //     CSVRenderer.separator

        // Arrange and Act
        new CSVRenderer(true);
    }
}
