package org.lateralgm.joshedit;

import java.awt.Color;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Class storing a syntax highlighting color profile.
 */
public class ColorProfile implements Iterable<ColorProfile.ColorProfileEntry> {

  /** The name as it appears to the user. This is considered pre-internationalized. */
  private final String name;
  private final Map<String, ColorProfileEntry> colors;

  private final Color lineNumberColor;
  private final Color lineNumberPanelColor;

  private final Color whitespaceColor;
  private final Color lineHighlightColor;
  private final Color matchingCharColor;
  private final Color noMatchingCharColor;
  private final Color backgroundColor;
  private final Color defaultFontColor;

  /** Builder to construct a new {@link ColorProfile}. */
  public static final class Builder {
    private String name;
    private final Map<String, ColorProfileEntry> colors;

    private Color lineNumberColor;
    private Color lineNumberPanelColor;

    private Color whitespaceColor;
    private Color lineHighlightColor;
    private Color matchingCharColor;
    private Color noMatchingCharColor;
    private Color backgroundColor;
    private Color defaultFontColor;

    /**
     * Construct with the name of the color profile.
     */
    public Builder() {
      this.name = ""; //$NON-NLS-1$
      this.colors = new HashMap<String, ColorProfile.ColorProfileEntry>();
    }

    /**
     * Construct with the name of the color profile.
     */
    public Builder(String name) {
      this.name = name;
      this.colors = new HashMap<String, ColorProfile.ColorProfileEntry>();
    }

    /**
     * Construct with the name of the color profile and the highlighting colors used by the profile.
     */
    public Builder(String name, Map<String, ColorProfileEntry> colors) {
      this.name = name;
      this.colors = colors;
    }

    /** Set the name of the new profile. */
    public final Builder setName(String name) {
      this.name = name;
      return this;
    }

    /** Add a color profile entry to this builder. */
    public final Builder addProfileEntry(String key, ColorProfileEntry entry) {
      colors.put(key, entry);
      return this;
    }

    /** Add a color profile entry to this builder. */
    public final Builder add(String key, ColorProfileEntry entry) {
      colors.put(key, entry);
      return this;
    }

    /** Set the color used to render line numbers. */
    public final Builder setLineNumberColor(Color lineNumberColor) {
      this.lineNumberColor = lineNumberColor;
      return this;
    }

    /** Set the color used to render line numbers. */
    public final Builder setLineNumberPanelColor(Color lineNumberPanelColor) {
      this.lineNumberPanelColor = lineNumberPanelColor;
      return this;
    }

    /** Set the color used to render the line number panel. */
    public final Builder setWhitespaceColor(Color whitespaceColor) {
      this.whitespaceColor = whitespaceColor;
      return this;
    }

    /** Set the color used to highlight the active line. */
    public final Builder setLineHighlightColor(Color lineHighlightColor) {
      this.lineHighlightColor = lineHighlightColor;
      return this;
    }

    /** Set the color used to mark matching brackets. */
    public final Builder setMatchingCharColor(Color matchingCharColor) {
      this.matchingCharColor = matchingCharColor;
      return this;
    }

    /** Set the color used to mark a bracket with no match. */
    public final Builder setNoMatchingCharColor(Color noMatchingCharColor) {
      this.noMatchingCharColor = noMatchingCharColor;
      return this;
    }

    /** Set the main text pane background color. */
    public final Builder setBackgroundColor(Color backgroundColor) {
      this.backgroundColor = backgroundColor;
      return this;
    }

    /** Set the default font color. */
    public final Builder setDefaultFontColor(Color defaultFontColor) {
      this.defaultFontColor = defaultFontColor;
      return this;
    }

    /** Return a new color profile built with the contents of this builder. */
    public ColorProfile build() {
      return new ColorProfile(name, colors, lineNumberColor, lineNumberPanelColor, whitespaceColor,
          lineHighlightColor, matchingCharColor, noMatchingCharColor, backgroundColor,
          defaultFontColor);
    }
  }

  private ColorProfile(String name, Map<String, ColorProfileEntry> colors, Color lineNumberColor,
      Color lineNumberPanelColor, Color whitespaceColor, Color lineHighlightColor,
      Color matchingCharColor, Color noMatchingCharColor, Color backgroundColor,
      Color defaultFontColor) {
    super();
    this.name = name;
    this.colors = Collections.unmodifiableMap(colors);
    this.lineNumberColor = lineNumberColor;
    this.lineNumberPanelColor = lineNumberPanelColor;
    this.whitespaceColor = whitespaceColor;
    this.lineHighlightColor = lineHighlightColor;
    this.matchingCharColor = matchingCharColor;
    this.noMatchingCharColor = noMatchingCharColor;
    this.backgroundColor = backgroundColor;
    this.defaultFontColor = defaultFontColor;
  }

  /** Construct, copying from another ColorProfile. */
  public ColorProfile(String name, ColorProfile otherProfile) {
    this.name = name;
    this.colors = Collections.unmodifiableMap(otherProfile.colors);
    this.lineNumberColor = otherProfile.lineNumberColor;
    this.lineNumberPanelColor = otherProfile.lineNumberPanelColor;
    this.whitespaceColor = otherProfile.whitespaceColor;
    this.lineHighlightColor = otherProfile.lineHighlightColor;
    this.matchingCharColor = otherProfile.matchingCharColor;
    this.noMatchingCharColor = otherProfile.noMatchingCharColor;
    this.backgroundColor = otherProfile.backgroundColor;
    this.defaultFontColor = otherProfile.defaultFontColor;
  }

  /** Create a new {@link ColorProfile} builder. */
  public static Builder newBuilder() {
    return new Builder();
  }

  /** Create a new {@link ColorProfile} builder with a profile name. */
  public static Builder newBuilder(String name) {
    return new Builder(name);
  }

  /** Create a new {@link ColorProfile} builder with a profile name and entry set. */
  public static Builder newBuilder(String name, Map<String, ColorProfileEntry> colors) {
    return new Builder(name, colors);
  }

  /** Get the color used to render line numbers. */
  public Color getLineNumberColor() {
    return lineNumberColor;
  }

  /** Get the color used to render line numbers. */
  public Color getLineNumberPanelColor() {
    return lineNumberPanelColor;
  }

  /** Get the color used to render the line number panel. */
  public Color getWhitespaceColor() {
    return whitespaceColor;
  }

  /** Get the color used to highlight the active line. */
  public Color getLineHighlightColor() {
    return lineHighlightColor;
  }

  /** Get the color used to mark matching brackets. */
  public Color getMatchingCharColor() {
    return matchingCharColor;
  }

  /** Get the color used to mark a bracket with no match. */
  public Color getNoMatchingCharColor() {
    return noMatchingCharColor;
  }

  /** Get the main text pane background color. */
  public Color getBackgroundColor() {
    return backgroundColor;
  }

  /** Get the default font color. */
  public Color getDefaultFontColor() {
    return defaultFontColor;
  }

  /** An entry in a {@link ColorProfile}. */
  public static class ColorProfileEntry {
    /** The name as it appears to the user. This should be internationalized. */
    public String nlsName;
    /** The color of the font used to depict members of this entry. */
    public Color color;
    /** The font style, such as Font.BOLD or Font.ITALICS. */
    public int fontStyle;

    /** Construct completely. */
    public ColorProfileEntry(String nlsName, Color color, int transform) {
      super();
      this.nlsName = nlsName;
      this.color = color;
      this.fontStyle = transform;
    }

  }

  /**
   * Create an entry from basic required information in its rawest form.
   *
   * @param nlsKey
   *        The internationalization key of this entry, to be looked up in the HighlightBlocks
   *        namespace. Example: COMMENT -> HighlightBlocks.COMMENT -> Comment.
   * @param r
   *        The red channel of the color.
   * @param g
   *        The green channel of the color.
   * @param b
   *        The blue channel of the color.
   * @param transform
   *        Font transformation and styling to apply, such as Font.BOLD or Font.ITALICS.
   */
  public static ColorProfileEntry makeEntry(String nlsKey, int r, int g, int b, int transform) {
    return new ColorProfileEntry(translate(nlsKey), new Color(r, g, b), transform);
  }

  /**
   * Create an entry from basic required information in its (nearly) rawest form.
   *
   * @param nlsKey
   *        The internationalization key of this entry, to be looked up in the HighlightBlocks
   *        namespace. Example: COMMENT -> HighlightBlocks.COMMENT -> Comment.
   * @param color
   *        The color used to depict members of the entry.
   * @param transform
   *        Font transformation and styling to apply, such as Font.BOLD or Font.ITALICS.
   */
  public static ColorProfileEntry makeEntry(String nlsKey, Color color, int transform) {
    return new ColorProfileEntry(translate(nlsKey), color, transform);
  }

  /** Get the name of this Color Profile */
  public String getName() {
    return name;
  }

  /** Look up an entry by its key name. */
  public ColorProfileEntry get(String key) {
    return colors.get(key);
  }

  /** Retrieve a set of all entry key names. */
  public Set<String> keySet() {
    return colors.keySet();
  }

  /** Retrieve a set of all entries. */
  public Collection<ColorProfileEntry> values() {
    return colors.values();
  }

  /** Retrieve a set of map entries to all color entries by their key name. */
  public Set<Entry<String, ColorProfileEntry>> entrySet() {
    return colors.entrySet();
  }

  /** Retrieve the map used by this profile, for copying purposes. */
  public Map<String, ColorProfileEntry> getMap() {
    return colors;
  }

  @Override
  public Iterator<ColorProfileEntry> iterator() {
    return colors.values().iterator();
  }

  // Private helpers

  private static String translate(String key) {
    return Runner.editorInterface.getString("HighlightBlocks." + key); //$NON-NLS-1$
  }
}
