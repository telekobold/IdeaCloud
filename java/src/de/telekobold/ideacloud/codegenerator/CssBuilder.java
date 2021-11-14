package de.telekobold.ideacloud.codegenerator;

/**
 * {@code CssBuilder} facilitates to generate nicely formatted CSS code with
 * little programming effort. {@code CssBuilder} cares for automatic indentation
 * and handles comments properly.
 * </p>
 * Indentation is increased whenever {@code CssBuilder} sees a line ending with
 * an opening '{' and and decreases again if a line ends with a '{'.
 * </p>
 * Methods can be called in method-chaining style since they return a {@code
 * CssBuilder} instance.
 * </p>
 * {@code CssBuilder} keeps an internal {@link StringBuffer} to record the
 * source code. The resulting code can be obtained through the
 * {@link #toString()} method.
 * </p>
 * Inspired by the class CBuilder.java from Dr. Volker Riediger (which generates
 * nicely formatted C code) from the Executable Models Project 2018
 * <https://www.uni-koblenz-landau.de/de/koblenz/fb4/aktuelles/mitteilungen/innovationspreis-emp>).
 * </p>
 * 
 * @author telekobold
 */
public class CssBuilder extends AbstractBuilder {

    /**
     * Indentation is only two chars for CSS
     */
    static final String INDENTATION = "  ";

    @Override
    public CssBuilder comment(String... lines) {
	return (CssBuilder) append("/* ").append(lines)
		.append(" */");
    }

    @Override
    public CssBuilder add(String... lines) {
	for (String line : lines) {
	    content.append("\n");
	    if (line == null) {
		line = "";
	    }
	    // Check if the current line should end a block
	    if (line.startsWith("}")) {
		// indent may be either 0 or 1 in the current usage of this program.
		indent = 0;
	    }
	    // Append an indentation
	    if (!line.isEmpty() && indent == 1) {
		content.append(INDENTATION);
	    }
	    content.append(line); // Append the content line.
	    // Check if the current line should start a block.
	    if (line.endsWith("{")) {
		indent = 1;
	    }
	}
	return this;
    }

    /**
     * Adds a CSS property-value pair (of the form</br>
     * {@code property: value;}
     * 
     * @param property the property to add to the property-value pair
     * @param value    the value to add to the property-value pair
     * @return this {@code AbstractBuilder}
     */
    public CssBuilder addPropValPair(String property, String value) {
	return (CssBuilder) add(property).append(": ")
		.append(value)
		.append(";");
    }

    /**
     * Adds a CSS rule specific for cloud tag generation defining the font size of
     * an {@code <a>} tag depending on the value of its data-weight attribute. The
     * generated rule looks like this:</br>
     * <code>ul.cloud a[data-weight="{@code <value>}"] {--size:
     * {@code <value>};}</code>
     * 
     * @param weight the wished data-weight attribute value
     * @return this {@code AbstractBuilder}
     */
    public CssBuilder addUlCloudRule(int weight) {
	String sweight = Integer.toString(weight);
	return (CssBuilder) add("ul.cloud a[data-weight=\"").append(sweight)
		.append("\"] { --size: ")
		.append(sweight)
		.append("; }");
    }

    /**
     * Adds {@code fromWeight - toWeight} CSS rules specific for cloud tag
     * generation defining the font size of {@code <a>} tags depending on the value
     * of their data-weight attributes. The generated rules look like this:</br>
     * <code>ul.cloud a[data-weight="{@code <fromWeight>}"] {--size:
     * {@code <fromWeight>};}</code></br>
     * <code>ul.cloud a[data-weight="{@code <fromWeight+1>}"] {--size:
     * {@code <fromWeight+1>};}</code></br>
     * <code>ul.cloud a[data-weight="{@code <fromWeight+2>}"] {--size:
     * {@code <fromWeight+2>};}</code></br>
     * ...</br>
     * <code>ul.cloud a[data-weight="{@code <toWeight>}"] {--size:
     * {@code <toWeight>};}</code></br>
     * 
     * @param fromWeight the value for the weight of the first rule to be generated.
     *                   Must be {@code <= toWeight}.
     * @param toWeight   the value for the wight of the last rule to be generated.
     * @return this {@code AbstractBuilder}
     * @throws IllegalArgumentException if {@code fromWeight} > {@code toWeight}
     */
    public CssBuilder addUlCloudRules(int fromWeight, int toWeight) {
	if (fromWeight > toWeight) {
	    throw new IllegalArgumentException("fromWeight must be <= toWeight!");
	}
	for (int i = fromWeight; i <= toWeight; i++) {
	    addUlCloudRule(i);
	}
	return this;
    }

}
