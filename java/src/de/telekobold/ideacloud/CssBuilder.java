package de.telekobold.ideacloud;

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
 * The generated cloud tag CSS code is derived from the <a href=
 * "https://dev.to/alvaromontoro/create-a-tag-cloud-with-html-and-css-1e90">cloud
 * tag generation tutorial</a> from Alvaro Montoro.
 * </p>
 * 
 * @author Michael Merz <mail@telekobold.de>
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
	    if (!line.isEmpty()) {
		if (indent == 1) {
		    content.append(INDENTATION);
		}
	    }
	    // Check if the current line should start a block.
	    if (line.endsWith("{")) {
		indent = 1;
	    }
	}
	return this;
    }

}
