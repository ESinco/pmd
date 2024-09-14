package net.sourceforge.pmd.cpd;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.color.ColorSpace;
import java.awt.image.ColorModel;
import java.beans.PropertyChangeListener;
import java.util.Locale;
import java.util.MissingResourceException;
import javax.accessibility.AccessibleContext;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;

class GridBagHelperDiffblueTest {
    /**
     * Method under test: {@link GridBagHelper#add(Component)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAdd() throws HeadlessException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.awt.HeadlessException
        //       at java.desktop/java.applet.Applet.<init>(Applet.java:81)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        GridBagHelper gridBagHelper = new GridBagHelper(new Container(), new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Act
        gridBagHelper.add(new Applet());
    }

    /**
     * Method under test: {@link GridBagHelper#add(Component, int)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAdd2() throws HeadlessException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.awt.HeadlessException
        //       at java.desktop/java.applet.Applet.<init>(Applet.java:81)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        GridBagHelper gridBagHelper = new GridBagHelper(new Container(), new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Act
        gridBagHelper.add(new Applet(), 1);
    }

    /**
     * Method under test: {@link GridBagHelper#nextRow()}
     */
    @Test
    void testNextRow() {
        // Arrange
        GridBagHelper gridBagHelper = new GridBagHelper(new Container(), new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Act
        gridBagHelper.nextRow();

        // Assert
        assertEquals(1, gridBagHelper.y);
    }

    /**
     * Method under test: {@link GridBagHelper#nextRow()}
     */
    @Test
    void testNextRow2() {
        // Arrange
        Container container = new Container();
        container.addPropertyChangeListener(mock(PropertyChangeListener.class));
        GridBagHelper gridBagHelper = new GridBagHelper(container, new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Act
        gridBagHelper.nextRow();

        // Assert
        assertEquals(1, gridBagHelper.y);
    }

    /**
     * Method under test: {@link GridBagHelper#addLabel(String)}
     */
    @Test
    void testAddLabel() throws MissingResourceException {
        // Arrange
        GridBagHelper gridBagHelper = new GridBagHelper(new Container(), new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Act
        gridBagHelper.addLabel("Label");

        // Assert
        Container container = gridBagHelper.container;
        Component[] components = container.getComponents();
        Component component = components[0];
        Locale locale = component.getLocale();
        assertEquals("", locale.getDisplayScript());
        assertEquals("", locale.getDisplayVariant());
        assertEquals("", locale.getScript());
        assertEquals("", locale.getVariant());
        assertEquals("BR", locale.getCountry());
        assertEquals("BRA", locale.getISO3Country());
        assertEquals("Brasil", locale.getDisplayCountry());
        Font font = component.getFont();
        assertEquals("Dialog", font.getFamily());
        assertEquals("Dialog", font.getName());
        assertEquals("Dialog.bold", font.getFontName());
        assertEquals("Dialog.bold", font.getPSName());
        AccessibleContext accessibleContext = component.getAccessibleContext();
        assertEquals("Label", accessibleContext.getAccessibleName());
        assertEquals("por", locale.getISO3Language());
        assertEquals("português (Brasil)", locale.getDisplayName());
        assertEquals("português", locale.getDisplayLanguage());
        assertEquals("pt", locale.getLanguage());
        assertNull(accessibleContext.getAccessibleIcon());
        assertNull(component.getFocusCycleRootAncestor());
        assertNull(component.getGraphics());
        assertNull(component.getGraphicsConfiguration());
        assertNull(component.getDropTarget());
        assertNull(component.getInputContext());
        assertNull(component.getInputMethodRequests());
        assertNull(component.getName());
        assertNull(accessibleContext.getAccessibleDescription());
        assertNull(accessibleContext.getAccessibleParent());
        assertNull(accessibleContext.getAccessibleAction());
        assertNull(accessibleContext.getAccessibleEditableText());
        assertNull(accessibleContext.getAccessibleSelection());
        assertNull(accessibleContext.getAccessibleTable());
        assertNull(accessibleContext.getAccessibleText());
        assertNull(accessibleContext.getAccessibleValue());
        Color background = component.getBackground();
        assertEquals(-1118482, background.getRGB());
        Color foreground = component.getForeground();
        assertEquals(-13421773, foreground.getRGB());
        assertEquals(0, component.getHeight());
        assertEquals(0, component.getWidth());
        assertEquals(0, component.getX());
        assertEquals(0, component.getY());
        assertEquals(0, font.getMissingGlyphCode());
        assertEquals(0, component.getComponentListeners().length);
        assertEquals(0, component.getFocusListeners().length);
        assertEquals(0, component.getHierarchyBoundsListeners().length);
        assertEquals(0, component.getHierarchyListeners().length);
        assertEquals(0, component.getInputMethodListeners().length);
        assertEquals(0, component.getKeyListeners().length);
        assertEquals(0, component.getMouseListeners().length);
        assertEquals(0, component.getMouseMotionListeners().length);
        assertEquals(0, component.getMouseWheelListeners().length);
        GridBagConstraints gridBagConstraints = gridBagHelper.c;
        assertEquals(0, gridBagConstraints.gridx);
        assertEquals(0, gridBagConstraints.gridy);
        assertEquals(0.0f, component.getAlignmentX());
        assertEquals(0.0f, font.getItalicAngle());
        assertEquals(0.5f, component.getAlignmentY());
        assertEquals(1, background.getTransparency());
        assertEquals(1, foreground.getTransparency());
        assertEquals(1, container.getComponentCount());
        assertEquals(1, font.getStyle());
        assertEquals(1, component.getPropertyChangeListeners().length);
        assertEquals(1, components.length);
        assertEquals(1, gridBagHelper.x);
        assertEquals(10.0d, gridBagConstraints.weightx);
        assertEquals(12, font.getSize());
        assertEquals(12.0f, font.getSize2D());
        Dimension maximumSize = component.getMaximumSize();
        assertEquals(16.0d, maximumSize.getHeight());
        Dimension minimumSize = container.getMinimumSize();
        assertEquals(20, minimumSize.height);
        assertEquals(20.0d, minimumSize.getHeight());
        assertEquals(22, font.getAvailableAttributes().length);
        assertEquals(238, background.getBlue());
        assertEquals(238, background.getGreen());
        assertEquals(238, background.getRed());
        assertEquals(255, background.getAlpha());
        assertEquals(255, foreground.getAlpha());
        assertEquals(31, maximumSize.width);
        assertEquals(31.0d, maximumSize.getWidth());
        assertEquals(35, minimumSize.width);
        assertEquals(35.0d, minimumSize.getWidth());
        assertEquals(4547, font.getNumGlyphs());
        assertEquals(51, foreground.getBlue());
        assertEquals(51, foreground.getGreen());
        assertEquals(51, foreground.getRed());
        assertEquals(Component.BaselineResizeBehavior.CENTER_OFFSET, component.getBaselineResizeBehavior());
        assertFalse(component.getIgnoreRepaint());
        assertFalse(component.hasFocus());
        assertFalse(component.isCursorSet());
        assertFalse(component.isDisplayable());
        assertFalse(component.isDoubleBuffered());
        assertFalse(component.isFocusOwner());
        assertFalse(component.isLightweight());
        assertFalse(component.isMaximumSizeSet());
        assertFalse(component.isMinimumSizeSet());
        assertFalse(component.isOpaque());
        assertFalse(component.isPreferredSizeSet());
        assertFalse(component.isShowing());
        assertFalse(component.isValid());
        assertFalse(font.hasLayoutAttributes());
        assertFalse(font.hasUniformLineMetrics());
        assertFalse(font.isItalic());
        assertFalse(font.isPlain());
        assertFalse(font.isTransformed());
        assertFalse(locale.hasExtensions());
        assertTrue(component.getFocusTraversalKeysEnabled());
        assertTrue(component.isBackgroundSet());
        assertTrue(component.isEnabled());
        assertTrue(component.isFocusable());
        assertTrue(component.isFontSet());
        assertTrue(component.isForegroundSet());
        assertTrue(component.isVisible());
        assertTrue(font.isBold());
        Rectangle boundsResult = container.bounds();
        assertEquals(boundsResult, component.bounds());
        assertEquals(boundsResult, component.getBounds());
        assertEquals(boundsResult, container.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds2D());
        assertEquals(boundsResult, boundsResult.getFrame());
        assertEquals(maximumSize, component.getMinimumSize());
        assertEquals(maximumSize, component.getPreferredSize());
        assertEquals(maximumSize, maximumSize.getSize());
        Dimension size = container.getSize();
        assertEquals(size, component.getSize());
        assertEquals(size, component.size());
        assertEquals(size, container.size());
        assertEquals(size, size.getSize());
        assertEquals(size, boundsResult.getSize());
        assertEquals(minimumSize, container.getPreferredSize());
        assertEquals(minimumSize, minimumSize.getSize());
        assertEquals(Short.SIZE, maximumSize.height);
        assertSame(accessibleContext, accessibleContext.getAccessibleComponent());
        Container expectedParent = gridBagHelper.container;
        assertSame(expectedParent, component.getParent());
    }

    /**
     * Method under test: {@link GridBagHelper#addLabel(String)}
     */
    @Test
    void testAddLabel2() throws MissingResourceException {
        // Arrange
        Container container = new Container();
        container.addPropertyChangeListener(mock(PropertyChangeListener.class));
        GridBagHelper gridBagHelper = new GridBagHelper(container, new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Act
        gridBagHelper.addLabel("Label");

        // Assert
        Container container2 = gridBagHelper.container;
        Component[] components = container2.getComponents();
        Component component = components[0];
        Locale locale = component.getLocale();
        assertEquals("", locale.getDisplayScript());
        assertEquals("", locale.getDisplayVariant());
        assertEquals("", locale.getScript());
        assertEquals("", locale.getVariant());
        assertEquals("BR", locale.getCountry());
        assertEquals("BRA", locale.getISO3Country());
        assertEquals("Brasil", locale.getDisplayCountry());
        Font font = component.getFont();
        assertEquals("Dialog", font.getFamily());
        assertEquals("Dialog", font.getName());
        assertEquals("Dialog.bold", font.getFontName());
        assertEquals("Dialog.bold", font.getPSName());
        AccessibleContext accessibleContext = component.getAccessibleContext();
        assertEquals("Label", accessibleContext.getAccessibleName());
        assertEquals("por", locale.getISO3Language());
        assertEquals("português (Brasil)", locale.getDisplayName());
        assertEquals("português", locale.getDisplayLanguage());
        assertEquals("pt", locale.getLanguage());
        assertNull(accessibleContext.getAccessibleIcon());
        assertNull(component.getFocusCycleRootAncestor());
        assertNull(component.getGraphics());
        assertNull(component.getGraphicsConfiguration());
        assertNull(component.getDropTarget());
        assertNull(component.getInputContext());
        assertNull(component.getInputMethodRequests());
        assertNull(component.getName());
        assertNull(accessibleContext.getAccessibleDescription());
        assertNull(accessibleContext.getAccessibleParent());
        assertNull(accessibleContext.getAccessibleAction());
        assertNull(accessibleContext.getAccessibleEditableText());
        assertNull(accessibleContext.getAccessibleSelection());
        assertNull(accessibleContext.getAccessibleTable());
        assertNull(accessibleContext.getAccessibleText());
        assertNull(accessibleContext.getAccessibleValue());
        Color background = component.getBackground();
        assertEquals(-1118482, background.getRGB());
        Color foreground = component.getForeground();
        assertEquals(-13421773, foreground.getRGB());
        assertEquals(0, component.getHeight());
        assertEquals(0, component.getWidth());
        assertEquals(0, component.getX());
        assertEquals(0, component.getY());
        assertEquals(0, font.getMissingGlyphCode());
        assertEquals(0, component.getComponentListeners().length);
        assertEquals(0, component.getFocusListeners().length);
        assertEquals(0, component.getHierarchyBoundsListeners().length);
        assertEquals(0, component.getHierarchyListeners().length);
        assertEquals(0, component.getInputMethodListeners().length);
        assertEquals(0, component.getKeyListeners().length);
        assertEquals(0, component.getMouseListeners().length);
        assertEquals(0, component.getMouseMotionListeners().length);
        assertEquals(0, component.getMouseWheelListeners().length);
        GridBagConstraints gridBagConstraints = gridBagHelper.c;
        assertEquals(0, gridBagConstraints.gridx);
        assertEquals(0, gridBagConstraints.gridy);
        assertEquals(0.0f, component.getAlignmentX());
        assertEquals(0.0f, font.getItalicAngle());
        assertEquals(0.5f, component.getAlignmentY());
        assertEquals(1, background.getTransparency());
        assertEquals(1, foreground.getTransparency());
        assertEquals(1, container2.getComponentCount());
        assertEquals(1, font.getStyle());
        assertEquals(1, component.getPropertyChangeListeners().length);
        assertEquals(1, components.length);
        assertEquals(1, gridBagHelper.x);
        assertEquals(10.0d, gridBagConstraints.weightx);
        assertEquals(12, font.getSize());
        assertEquals(12.0f, font.getSize2D());
        Dimension maximumSize = component.getMaximumSize();
        assertEquals(16.0d, maximumSize.getHeight());
        Dimension minimumSize = container2.getMinimumSize();
        assertEquals(20, minimumSize.height);
        assertEquals(20.0d, minimumSize.getHeight());
        assertEquals(22, font.getAvailableAttributes().length);
        assertEquals(238, background.getBlue());
        assertEquals(238, background.getGreen());
        assertEquals(238, background.getRed());
        assertEquals(255, background.getAlpha());
        assertEquals(255, foreground.getAlpha());
        assertEquals(31, maximumSize.width);
        assertEquals(31.0d, maximumSize.getWidth());
        assertEquals(35, minimumSize.width);
        assertEquals(35.0d, minimumSize.getWidth());
        assertEquals(4547, font.getNumGlyphs());
        assertEquals(51, foreground.getBlue());
        assertEquals(51, foreground.getGreen());
        assertEquals(51, foreground.getRed());
        assertEquals(Component.BaselineResizeBehavior.CENTER_OFFSET, component.getBaselineResizeBehavior());
        assertFalse(component.getIgnoreRepaint());
        assertFalse(component.hasFocus());
        assertFalse(component.isCursorSet());
        assertFalse(component.isDisplayable());
        assertFalse(component.isDoubleBuffered());
        assertFalse(component.isFocusOwner());
        assertFalse(component.isLightweight());
        assertFalse(component.isMaximumSizeSet());
        assertFalse(component.isMinimumSizeSet());
        assertFalse(component.isOpaque());
        assertFalse(component.isPreferredSizeSet());
        assertFalse(component.isShowing());
        assertFalse(component.isValid());
        assertFalse(font.hasLayoutAttributes());
        assertFalse(font.hasUniformLineMetrics());
        assertFalse(font.isItalic());
        assertFalse(font.isPlain());
        assertFalse(font.isTransformed());
        assertFalse(locale.hasExtensions());
        assertTrue(component.getFocusTraversalKeysEnabled());
        assertTrue(component.isBackgroundSet());
        assertTrue(component.isEnabled());
        assertTrue(component.isFocusable());
        assertTrue(component.isFontSet());
        assertTrue(component.isForegroundSet());
        assertTrue(component.isVisible());
        assertTrue(font.isBold());
        Rectangle boundsResult = container2.bounds();
        assertEquals(boundsResult, component.bounds());
        assertEquals(boundsResult, component.getBounds());
        assertEquals(boundsResult, container2.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds2D());
        assertEquals(boundsResult, boundsResult.getFrame());
        assertEquals(maximumSize, component.getMinimumSize());
        assertEquals(maximumSize, component.getPreferredSize());
        assertEquals(maximumSize, maximumSize.getSize());
        Dimension size = container2.getSize();
        assertEquals(size, component.getSize());
        assertEquals(size, component.size());
        assertEquals(size, container2.size());
        assertEquals(size, size.getSize());
        assertEquals(size, boundsResult.getSize());
        assertEquals(minimumSize, container2.getPreferredSize());
        assertEquals(minimumSize, minimumSize.getSize());
        assertEquals(Short.SIZE, maximumSize.height);
        assertSame(accessibleContext, accessibleContext.getAccessibleComponent());
        Container expectedParent = gridBagHelper.container;
        assertSame(expectedParent, component.getParent());
    }

    /**
     * Method under test: {@link GridBagHelper#GridBagHelper(Container, double[])}
     */
    @Test
    void testNewGridBagHelper() {
        // Arrange and Act
        GridBagHelper actualGridBagHelper = new GridBagHelper(new Container(), new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Assert
        Container container = actualGridBagHelper.container;
        Cursor cursor = container.getCursor();
        assertEquals("Cursor Padrão", cursor.getName());
        GridBagLayout gridBagLayout = actualGridBagHelper.gridbag;
        assertNull(gridBagLayout.columnWeights);
        assertNull(gridBagLayout.rowWeights);
        assertNull(gridBagLayout.columnWidths);
        assertNull(gridBagLayout.rowHeights);
        assertNull(container.getBackground());
        assertNull(container.getForeground());
        assertNull(container.getFocusCycleRootAncestor());
        assertNull(container.getParent());
        assertNull(container.getFocusTraversalPolicy());
        assertNull(container.getFont());
        assertNull(container.getGraphics());
        assertNull(container.getGraphicsConfiguration());
        assertNull(container.getDropTarget());
        assertNull(container.getInputContext());
        assertNull(container.getInputMethodRequests());
        assertNull(container.getName());
        assertNull(container.getAccessibleContext());
        GridBagConstraints gridBagConstraints = actualGridBagHelper.c;
        assertEquals(-1, gridBagConstraints.gridx);
        assertEquals(-1, gridBagConstraints.gridy);
        assertEquals(0, container.getHeight());
        assertEquals(0, container.getWidth());
        assertEquals(0, container.getX());
        assertEquals(0, container.getY());
        assertEquals(0, container.getComponentCount());
        assertEquals(0, cursor.getType());
        assertEquals(0, container.getComponentListeners().length);
        assertEquals(0, container.getFocusListeners().length);
        assertEquals(0, container.getHierarchyBoundsListeners().length);
        assertEquals(0, container.getHierarchyListeners().length);
        assertEquals(0, container.getInputMethodListeners().length);
        assertEquals(0, container.getKeyListeners().length);
        assertEquals(0, container.getMouseListeners().length);
        assertEquals(0, container.getMouseMotionListeners().length);
        assertEquals(0, container.getMouseWheelListeners().length);
        assertEquals(0, container.getPropertyChangeListeners().length);
        assertEquals(0, container.getComponents().length);
        assertEquals(0, container.getContainerListeners().length);
        double[][] layoutWeights = gridBagLayout.getLayoutWeights();
        assertEquals(0, (layoutWeights[0]).length);
        assertEquals(0, (layoutWeights[1]).length);
        int[][] layoutDimensions = gridBagLayout.getLayoutDimensions();
        assertEquals(0, (layoutDimensions[0]).length);
        assertEquals(0, (layoutDimensions[1]).length);
        Dimension minimumSize = container.getMinimumSize();
        assertEquals(0, minimumSize.height);
        assertEquals(0, minimumSize.width);
        assertEquals(0, gridBagConstraints.ipadx);
        assertEquals(0, gridBagConstraints.ipady);
        Insets insets = container.getInsets();
        assertEquals(0, insets.bottom);
        assertEquals(0, insets.left);
        assertEquals(0, insets.right);
        assertEquals(0, insets.top);
        Point location = container.getLocation();
        assertEquals(0, location.x);
        assertEquals(0, location.y);
        Rectangle boundsResult = container.bounds();
        assertEquals(0, boundsResult.height);
        assertEquals(0, boundsResult.width);
        assertEquals(0, boundsResult.x);
        assertEquals(0, boundsResult.y);
        assertEquals(0, actualGridBagHelper.x);
        assertEquals(0, actualGridBagHelper.y);
        assertEquals(0.0d, minimumSize.getHeight());
        assertEquals(0.0d, minimumSize.getWidth());
        assertEquals(0.0d, location.getX());
        assertEquals(0.0d, location.getY());
        assertEquals(0.0d, boundsResult.getHeight());
        assertEquals(0.0d, boundsResult.getWidth());
        assertEquals(0.0d, boundsResult.getX());
        assertEquals(0.0d, boundsResult.getY());
        assertEquals(0.0d, boundsResult.getCenterX());
        assertEquals(0.0d, boundsResult.getCenterY());
        assertEquals(0.0d, boundsResult.getMaxX());
        assertEquals(0.0d, boundsResult.getMaxY());
        assertEquals(0.0d, boundsResult.getMinX());
        assertEquals(0.0d, boundsResult.getMinY());
        assertEquals(0.0d, gridBagConstraints.weightx);
        assertEquals(0.0d, gridBagConstraints.weighty);
        assertEquals(0.5f, container.getAlignmentX());
        assertEquals(0.5f, container.getAlignmentY());
        Toolkit toolkit = container.getToolkit();
        assertEquals(1, toolkit.getAWTEventListeners().length);
        assertEquals(1, toolkit.getPropertyChangeListeners().length);
        assertEquals(1, gridBagConstraints.gridheight);
        assertEquals(1, gridBagConstraints.gridwidth);
        assertEquals(13, gridBagConstraints.anchor);
        assertEquals(2, layoutDimensions.length);
        assertEquals(2, layoutWeights.length);
        assertEquals(2, gridBagConstraints.fill);
        Insets insets2 = gridBagConstraints.insets;
        assertEquals(2, insets2.bottom);
        assertEquals(2, insets2.left);
        assertEquals(2, insets2.right);
        assertEquals(2, insets2.top);
        Dimension maximumSize = container.getMaximumSize();
        assertEquals(2.147483647E9d, maximumSize.getHeight());
        assertEquals(2.147483647E9d, maximumSize.getWidth());
        ColorModel colorModel = container.getColorModel();
        ColorSpace colorSpace = colorModel.getColorSpace();
        assertEquals(3, colorSpace.getNumComponents());
        assertEquals(3, colorModel.getNumColorComponents());
        assertEquals(3, colorModel.getTransferType());
        assertEquals(3, colorModel.getTransparency());
        assertEquals(4, colorModel.getNumComponents());
        assertEquals(4, actualGridBagHelper.labelAlignment);
        assertEquals(5, colorSpace.getType());
        assertEquals(Component.BaselineResizeBehavior.OTHER, container.getBaselineResizeBehavior());
        assertFalse(container.getIgnoreRepaint());
        assertFalse(container.hasFocus());
        assertFalse(container.isBackgroundSet());
        assertFalse(container.isCursorSet());
        assertFalse(container.isDisplayable());
        assertFalse(container.isDoubleBuffered());
        assertFalse(container.isFocusOwner());
        assertFalse(container.isFontSet());
        assertFalse(container.isForegroundSet());
        assertFalse(container.isLightweight());
        assertFalse(container.isMaximumSizeSet());
        assertFalse(container.isMinimumSizeSet());
        assertFalse(container.isOpaque());
        assertFalse(container.isPreferredSizeSet());
        assertFalse(container.isShowing());
        assertFalse(container.isValid());
        assertFalse(container.isFocusCycleRoot());
        assertFalse(container.isFocusTraversalPolicyProvider());
        assertFalse(container.isFocusTraversalPolicySet());
        assertFalse(container.isValidateRoot());
        assertFalse(toolkit.isAlwaysOnTopSupported());
        assertFalse(colorModel.isAlphaPremultiplied());
        assertTrue(container.getFocusTraversalKeysEnabled());
        assertTrue(container.isEnabled());
        assertTrue(container.isFocusable());
        assertTrue(container.isVisible());
        ComponentOrientation componentOrientation = container.getComponentOrientation();
        assertTrue(componentOrientation.isHorizontal());
        assertTrue(componentOrientation.isLeftToRight());
        assertTrue(boundsResult.isEmpty());
        assertTrue(colorSpace.isCS_sRGB());
        assertTrue(colorModel.hasAlpha());
        assertEquals(boundsResult, container.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds2D());
        assertEquals(boundsResult, boundsResult.getFrame());
        assertEquals(location, gridBagLayout.getLayoutOrigin());
        assertEquals(location, location.getLocation());
        assertEquals(location, boundsResult.getLocation());
        assertEquals(maximumSize, maximumSize.getSize());
        assertEquals(minimumSize, container.getSize());
        assertEquals(minimumSize, container.size());
        assertEquals(minimumSize, container.getPreferredSize());
        assertEquals(minimumSize, minimumSize.getSize());
        assertEquals(minimumSize, boundsResult.getSize());
        assertEquals(Integer.MAX_VALUE, maximumSize.height);
        assertEquals(Integer.MAX_VALUE, maximumSize.width);
        assertEquals(Integer.SIZE, colorModel.getPixelSize());
        GridBagLayout expectedLayout = actualGridBagHelper.gridbag;
        assertSame(expectedLayout, container.getLayout());
        assertArrayEquals(new double[]{10.0d, 0.5d, 10.0d, 0.5d}, actualGridBagHelper.weights, 0.0);
        assertArrayEquals(new int[]{8, 8, 8, 8}, colorModel.getComponentSize());
    }

    /**
     * Method under test: {@link GridBagHelper#GridBagHelper(Container, double[])}
     */
    @Test
    void testNewGridBagHelper2() {
        // Arrange
        Container container = new Container();
        container.addPropertyChangeListener(mock(PropertyChangeListener.class));

        // Act
        GridBagHelper actualGridBagHelper = new GridBagHelper(container, new double[]{10.0d, 0.5d, 10.0d, 0.5d});

        // Assert
        Container container2 = actualGridBagHelper.container;
        Cursor cursor = container2.getCursor();
        assertEquals("Cursor Padrão", cursor.getName());
        GridBagLayout gridBagLayout = actualGridBagHelper.gridbag;
        assertNull(gridBagLayout.columnWeights);
        assertNull(gridBagLayout.rowWeights);
        assertNull(gridBagLayout.columnWidths);
        assertNull(gridBagLayout.rowHeights);
        assertNull(container2.getBackground());
        assertNull(container2.getForeground());
        assertNull(container2.getFocusCycleRootAncestor());
        assertNull(container2.getParent());
        assertNull(container2.getFocusTraversalPolicy());
        assertNull(container2.getFont());
        assertNull(container2.getGraphics());
        assertNull(container2.getGraphicsConfiguration());
        assertNull(container2.getDropTarget());
        assertNull(container2.getInputContext());
        assertNull(container2.getInputMethodRequests());
        assertNull(container2.getName());
        assertNull(container2.getAccessibleContext());
        GridBagConstraints gridBagConstraints = actualGridBagHelper.c;
        assertEquals(-1, gridBagConstraints.gridx);
        assertEquals(-1, gridBagConstraints.gridy);
        assertEquals(0, container2.getHeight());
        assertEquals(0, container2.getWidth());
        assertEquals(0, container2.getX());
        assertEquals(0, container2.getY());
        assertEquals(0, container2.getComponentCount());
        assertEquals(0, cursor.getType());
        assertEquals(0, container2.getComponentListeners().length);
        assertEquals(0, container2.getFocusListeners().length);
        assertEquals(0, container2.getHierarchyBoundsListeners().length);
        assertEquals(0, container2.getHierarchyListeners().length);
        assertEquals(0, container2.getInputMethodListeners().length);
        assertEquals(0, container2.getKeyListeners().length);
        assertEquals(0, container2.getMouseListeners().length);
        assertEquals(0, container2.getMouseMotionListeners().length);
        assertEquals(0, container2.getMouseWheelListeners().length);
        assertEquals(0, container2.getComponents().length);
        assertEquals(0, container2.getContainerListeners().length);
        double[][] layoutWeights = gridBagLayout.getLayoutWeights();
        assertEquals(0, (layoutWeights[0]).length);
        assertEquals(0, (layoutWeights[1]).length);
        int[][] layoutDimensions = gridBagLayout.getLayoutDimensions();
        assertEquals(0, (layoutDimensions[0]).length);
        assertEquals(0, (layoutDimensions[1]).length);
        Dimension minimumSize = container2.getMinimumSize();
        assertEquals(0, minimumSize.height);
        assertEquals(0, minimumSize.width);
        assertEquals(0, gridBagConstraints.ipadx);
        assertEquals(0, gridBagConstraints.ipady);
        Insets insets = container2.getInsets();
        assertEquals(0, insets.bottom);
        assertEquals(0, insets.left);
        assertEquals(0, insets.right);
        assertEquals(0, insets.top);
        Point location = container2.getLocation();
        assertEquals(0, location.x);
        assertEquals(0, location.y);
        Rectangle boundsResult = container2.bounds();
        assertEquals(0, boundsResult.height);
        assertEquals(0, boundsResult.width);
        assertEquals(0, boundsResult.x);
        assertEquals(0, boundsResult.y);
        assertEquals(0, actualGridBagHelper.x);
        assertEquals(0, actualGridBagHelper.y);
        assertEquals(0.0d, minimumSize.getHeight());
        assertEquals(0.0d, minimumSize.getWidth());
        assertEquals(0.0d, location.getX());
        assertEquals(0.0d, location.getY());
        assertEquals(0.0d, boundsResult.getHeight());
        assertEquals(0.0d, boundsResult.getWidth());
        assertEquals(0.0d, boundsResult.getX());
        assertEquals(0.0d, boundsResult.getY());
        assertEquals(0.0d, boundsResult.getCenterX());
        assertEquals(0.0d, boundsResult.getCenterY());
        assertEquals(0.0d, boundsResult.getMaxX());
        assertEquals(0.0d, boundsResult.getMaxY());
        assertEquals(0.0d, boundsResult.getMinX());
        assertEquals(0.0d, boundsResult.getMinY());
        assertEquals(0.0d, gridBagConstraints.weightx);
        assertEquals(0.0d, gridBagConstraints.weighty);
        assertEquals(0.5f, container2.getAlignmentX());
        assertEquals(0.5f, container2.getAlignmentY());
        assertEquals(1, container2.getPropertyChangeListeners().length);
        Toolkit toolkit = container2.getToolkit();
        assertEquals(1, toolkit.getAWTEventListeners().length);
        assertEquals(1, toolkit.getPropertyChangeListeners().length);
        assertEquals(1, gridBagConstraints.gridheight);
        assertEquals(1, gridBagConstraints.gridwidth);
        assertEquals(13, gridBagConstraints.anchor);
        assertEquals(2, layoutDimensions.length);
        assertEquals(2, layoutWeights.length);
        assertEquals(2, gridBagConstraints.fill);
        Insets insets2 = gridBagConstraints.insets;
        assertEquals(2, insets2.bottom);
        assertEquals(2, insets2.left);
        assertEquals(2, insets2.right);
        assertEquals(2, insets2.top);
        Dimension maximumSize = container2.getMaximumSize();
        assertEquals(2.147483647E9d, maximumSize.getHeight());
        assertEquals(2.147483647E9d, maximumSize.getWidth());
        ColorModel colorModel = container2.getColorModel();
        ColorSpace colorSpace = colorModel.getColorSpace();
        assertEquals(3, colorSpace.getNumComponents());
        assertEquals(3, colorModel.getNumColorComponents());
        assertEquals(3, colorModel.getTransferType());
        assertEquals(3, colorModel.getTransparency());
        assertEquals(4, colorModel.getNumComponents());
        assertEquals(4, actualGridBagHelper.labelAlignment);
        assertEquals(5, colorSpace.getType());
        assertEquals(Component.BaselineResizeBehavior.OTHER, container2.getBaselineResizeBehavior());
        assertFalse(container2.getIgnoreRepaint());
        assertFalse(container2.hasFocus());
        assertFalse(container2.isBackgroundSet());
        assertFalse(container2.isCursorSet());
        assertFalse(container2.isDisplayable());
        assertFalse(container2.isDoubleBuffered());
        assertFalse(container2.isFocusOwner());
        assertFalse(container2.isFontSet());
        assertFalse(container2.isForegroundSet());
        assertFalse(container2.isLightweight());
        assertFalse(container2.isMaximumSizeSet());
        assertFalse(container2.isMinimumSizeSet());
        assertFalse(container2.isOpaque());
        assertFalse(container2.isPreferredSizeSet());
        assertFalse(container2.isShowing());
        assertFalse(container2.isValid());
        assertFalse(container2.isFocusCycleRoot());
        assertFalse(container2.isFocusTraversalPolicyProvider());
        assertFalse(container2.isFocusTraversalPolicySet());
        assertFalse(container2.isValidateRoot());
        assertFalse(toolkit.isAlwaysOnTopSupported());
        assertFalse(colorModel.isAlphaPremultiplied());
        assertTrue(container2.getFocusTraversalKeysEnabled());
        assertTrue(container2.isEnabled());
        assertTrue(container2.isFocusable());
        assertTrue(container2.isVisible());
        ComponentOrientation componentOrientation = container2.getComponentOrientation();
        assertTrue(componentOrientation.isHorizontal());
        assertTrue(componentOrientation.isLeftToRight());
        assertTrue(boundsResult.isEmpty());
        assertTrue(colorSpace.isCS_sRGB());
        assertTrue(colorModel.hasAlpha());
        assertEquals(boundsResult, container2.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds());
        assertEquals(boundsResult, boundsResult.getBounds2D());
        assertEquals(boundsResult, boundsResult.getFrame());
        assertEquals(location, gridBagLayout.getLayoutOrigin());
        assertEquals(location, location.getLocation());
        assertEquals(location, boundsResult.getLocation());
        assertEquals(maximumSize, maximumSize.getSize());
        assertEquals(minimumSize, container2.getSize());
        assertEquals(minimumSize, container2.size());
        assertEquals(minimumSize, container2.getPreferredSize());
        assertEquals(minimumSize, minimumSize.getSize());
        assertEquals(minimumSize, boundsResult.getSize());
        assertEquals(Integer.MAX_VALUE, maximumSize.height);
        assertEquals(Integer.MAX_VALUE, maximumSize.width);
        assertEquals(Integer.SIZE, colorModel.getPixelSize());
        GridBagLayout expectedLayout = actualGridBagHelper.gridbag;
        assertSame(expectedLayout, container2.getLayout());
        assertArrayEquals(new double[]{10.0d, 0.5d, 10.0d, 0.5d}, actualGridBagHelper.weights, 0.0);
        assertArrayEquals(new int[]{8, 8, 8, 8}, colorModel.getComponentSize());
    }
}
