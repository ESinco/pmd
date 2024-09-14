package net.sourceforge.pmd.cpd;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import net.sourceforge.pmd.lang.document.FileId;
import org.junit.jupiter.api.Test;

class XMLRendererDiffblueTest {
    /**
     * Method under test: {@link XMLRenderer#setEncoding(String)}
     */
    @Test
    void testSetEncoding() {
        // Arrange
        XMLRenderer xmlRenderer = new XMLRenderer();

        // Act
        xmlRenderer.setEncoding("UTF-8");

        // Assert
        assertEquals("UTF-8", xmlRenderer.getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#setEncoding(String)}
     */
    @Test
    void testSetEncoding2() {
        // Arrange
        XMLRenderer xmlRenderer = new XMLRenderer();

        // Act
        xmlRenderer.setEncoding(null);

        // Assert
        String expectedEncoding = System.getProperty("sun.jnu.encoding");
        assertEquals(expectedEncoding, xmlRenderer.getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#getEncoding()}
     */
    @Test
    void testGetEncoding() {
        // Arrange, Act and Assert
        assertEquals(System.getProperty("sun.jnu.encoding"), (new XMLRenderer()).getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#render(CPDReport, Writer)}
     */
    @Test
    void testRender() throws IOException {
        // Arrange
        XMLRenderer xmlRenderer = new XMLRenderer();
        ArrayList<Match> matches = new ArrayList<>();
        HashMap<FileId, Integer> numTokensPerFile = new HashMap<>();

        // Act and Assert
        assertThrows(IllegalStateException.class,
                () -> xmlRenderer.render(CpdTestUtils.makeReport(matches, numTokensPerFile, new ArrayList<>()), null));
    }

    /**
     * Method under test: {@link XMLRenderer#XMLRenderer()}
     */
    @Test
    void testNewXMLRenderer() {
        // Arrange, Act and Assert
        String expectedEncoding = System.getProperty("sun.jnu.encoding");
        assertEquals(expectedEncoding, (new XMLRenderer()).getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#XMLRenderer(String)}
     */
    @Test
    void testNewXMLRenderer2() {
        // Arrange, Act and Assert
        assertEquals("UTF-8", (new XMLRenderer("UTF-8")).getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#XMLRenderer(String)}
     */
    @Test
    void testNewXMLRenderer3() {
        // Arrange, Act and Assert
        String expectedEncoding = System.getProperty("sun.jnu.encoding");
        assertEquals(expectedEncoding, (new XMLRenderer(null)).getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#XMLRenderer(String, boolean)}
     */
    @Test
    void testNewXMLRenderer4() {
        // Arrange, Act and Assert
        assertEquals("UTF-8", (new XMLRenderer("UTF-8", true)).getEncoding());
    }

    /**
     * Method under test: {@link XMLRenderer#XMLRenderer(String, boolean)}
     */
    @Test
    void testNewXMLRenderer5() {
        // Arrange, Act and Assert
        String expectedEncoding = System.getProperty("sun.jnu.encoding");
        assertEquals(expectedEncoding, (new XMLRenderer(null, true)).getEncoding());
    }
}
