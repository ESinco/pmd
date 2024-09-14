package net.sourceforge.pmd.cpd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import net.sourceforge.pmd.lang.document.FileId;
import org.junit.jupiter.api.Test;

class SimpleRendererDiffblueTest {
    /**
     * Method under test: {@link SimpleRenderer#SimpleRenderer()}
     */
    @Test
    void testNewSimpleRenderer() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SimpleRenderer.separator
        //     SimpleRenderer.trimLeadingWhitespace

        // Arrange and Act
        new SimpleRenderer();
    }

    /**
     * Method under test: {@link SimpleRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender() throws IOException {
        // Arrange
        SimpleRenderer simpleRenderer = new SimpleRenderer("The Separator");
        ArrayList<Match> matches = new ArrayList<>();
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer0 = new StringWriter();

        // Act
        simpleRenderer.render(report, writer0);

        // Assert that nothing has changed
        assertEquals("", writer0.toString());
    }

    /**
     * Method under test: {@link SimpleRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender2() throws IOException {
        // Arrange
        SimpleRenderer simpleRenderer = new SimpleRenderer("The Separator");

        ArrayList<Match> matches = new ArrayList<>();
        Mark first = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer0 = new StringWriter();

        // Act
        simpleRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "Found a 1 line (3 tokens) duplication in the following files: \r\n"
                        + "Starting at line 2 of C:\\var\\Bar.java\r\n" + "\r\n" + "1_1_1_1_1_1_1_1_1_1_\n" + "\r\n",
                writer0.toString());
    }

    /**
     * Method under test: {@link SimpleRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender3() throws IOException {
        // Arrange
        SimpleRenderer simpleRenderer = new SimpleRenderer("The Separator");

        ArrayList<Match> matches = new ArrayList<>();
        Mark first = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        Mark first2 = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first2, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer0 = new StringWriter();

        // Act
        simpleRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "Found a 1 line (3 tokens) duplication in the following files: \r\n"
                        + "Starting at line 2 of C:\\var\\Bar.java\r\n" + "\r\n" + "1_1_1_1_1_1_1_1_1_1_\n" + "\r\n"
                        + "The Separator\r\n" + "Found a 1 line (3 tokens) duplication in the following files: \r\n"
                        + "Starting at line 2 of C:\\var\\Bar.java\r\n" + "\r\n" + "1_1_1_1_1_1_1_1_1_1_\n" + "\r\n",
                writer0.toString());
    }

    /**
     * Method under test: {@link SimpleRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender4() throws IOException {
        // Arrange
        SimpleRenderer simpleRenderer = new SimpleRenderer("The Separator");

        ArrayList<Match> matches = new ArrayList<>();
        Mark first = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 1, 1));
        matches.add(new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer0 = new StringWriter();

        // Act
        simpleRenderer.render(report, writer0);

        // Assert
        assertEquals(
                "Found a 1 line (3 tokens) duplication in the following files: \r\n"
                        + "Starting at line 1 of C:\\var\\Bar.java\r\n" + "\r\n" + "0_0_0_0_0_0_0_0_0_0_\n" + "\r\n",
                writer0.toString());
    }

    /**
     * Method under test: {@link SimpleRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender5() throws IOException {
        // Arrange
        SimpleRenderer simpleRenderer = new SimpleRenderer(true);

        ArrayList<Match> matches = new ArrayList<>();
        Mark first = new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1));
        matches.add(new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1))));
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer0 = new StringWriter();

        // Act
        simpleRenderer.render(report, writer0);

        // Assert
        assertEquals("Found a 1 line (3 tokens) duplication in the following files: \r\n"
                + "Starting at line 2 of C:\\var\\Bar.java\r\n" + "\r\n" + "1_1_1_1_1_1_1_1_1_1_\r\n", writer0.toString());
    }

    /**
     * Method under test: {@link SimpleRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender6() throws IOException {
        // Arrange
        SimpleRenderer simpleRenderer = new SimpleRenderer("The Separator");
        FileId fileId = mock(FileId.class);
        when(fileId.getAbsolutePath()).thenReturn("Absolute Path");
        Mark first = new Mark(new TokenEntry(fileId, 2, 1));
        Match match = new Match(3, first, new Mark(new TokenEntry(CpdTestUtils.BAR_FILE_ID, 2, 1)));

        ArrayList<Match> matches = new ArrayList<>();
        matches.add(match);
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();
        CPDReport report = CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>());
        StringWriter writer0 = new StringWriter();

        // Act
        simpleRenderer.render(report, writer0);

        // Assert
        verify(fileId).getAbsolutePath();
        assertEquals("Found a 1 line (3 tokens) duplication in the following files: \r\n"
                + "Starting at line 2 of Absolute Path\r\n" + "\r\n" + "1_1_1_1_1_1_1_1_1_1_\n" + "\r\n", writer0.toString());
    }

    /**
     * Method under test: {@link SimpleRenderer#SimpleRenderer(String)}
     */
    @Test
    void testNewSimpleRenderer2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SimpleRenderer.separator
        //     SimpleRenderer.trimLeadingWhitespace

        // Arrange and Act
        new SimpleRenderer("The Separator");
    }

    /**
     * Method under test: {@link SimpleRenderer#SimpleRenderer(boolean)}
     */
    @Test
    void testNewSimpleRenderer3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SimpleRenderer.separator
        //     SimpleRenderer.trimLeadingWhitespace

        // Arrange and Act
        new SimpleRenderer(true);
    }
}
