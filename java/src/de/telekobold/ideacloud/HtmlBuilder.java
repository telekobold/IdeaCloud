package de.telekobold.ideacloud;

import java.util.ArrayList;
import java.util.List;
// import org.apache.commons.lang3.tuple.Triple;

/**
 * {@code HtmlBuilder} facilitates to generate nicely formatted HTML code with
 * little programming effort. {@code HtmlBuilder} cares for automatic
 * indentation and handles comments properly.
 * </p>
 * Indentation is increased whenever {@code HtmlBuilder} sees a line ending with
 * an opening tag from a defined list (e.g. {@code <html>} and {@code <head>})
 * and decreases again if a line is equal to an opening tag from a defined list
 * (e.g. {@code </html>} and {@code </head>}).
 * </p>
 * Methods can be called in method-chaining style since they return a
 * {@code HtmlBuilder} instance.
 * </p>
 * {@code HtmlBuilder} keeps an internal {@link StringBuffer} to record the
 * source code. The resulting code can be obtained through the
 * {@link #toString()} method.
 * </p>
 * Inspired by the class CBuilder.java from Dr. Volker Riediger (which generates
 * nicely formatted C code) from the Executable Models Project 2018
 * <https://www.uni-koblenz-landau.de/de/koblenz/fb4/aktuelles/mitteilungen/innovationspreis-emp>).
 * </p>
 * 
 * @author Michael Merz <mail@telekobold.de>
 */

public class HtmlBuilder {

    private static final String INDENTATION = "    ";
    private int indent = 0;
    private StringBuilder content = new StringBuilder();
    private final List<String> indentationIncreaseTags = new ArrayList<>();
    private final List<String> indentationDecreaseTags = new ArrayList<>();

    public HtmlBuilder() {
	initialize();
    }

    // To be supplemented by further tags (e.g. <nav>, <footer>) if needed.
    public void initialize() {
	// The trailing ">" must not be mentioned here since attributes could follow
	// before the ">".
	indentationIncreaseTags.add("<html");
	indentationIncreaseTags.add("<head");
	indentationIncreaseTags.add("<body");
	indentationIncreaseTags.add("<header");
	indentationIncreaseTags.add("<main");
	indentationIncreaseTags.add("<ul");

	indentationDecreaseTags.add("</html>");
	indentationDecreaseTags.add("</head>");
	indentationDecreaseTags.add("</body>");
	indentationDecreaseTags.add("</header>");
	indentationDecreaseTags.add("</main>");
	indentationDecreaseTags.add("</ul>");
    }

    /**
     * Appends one or more single line comments to this {@code HtmlBuilder}
     *
     * @param lines the text of the comment lines
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder comment(String... lines) {
	return append("<!-- ").append(lines)
		.append(" -->");
    }

    /**
     * Adds an empty line.
     *
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder nl() {
	return add("");
    }

    /**
     * Clears this {@code HtmlBuilder}, i.e. all code is lost.
     */
    public void clear() {
	indent = 0;
	content = new StringBuilder();
    }

    /**
     * Appends one or more lines to this HtmlBuilder without newlines.
     * 
     * @param lines the lines to be added
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder append(String... lines) {
	for (String line : lines) {
	    content.append(line);
	}
	return this;
    }

    /**
     * Adds one or more lines to this HtmlBuilder where a newline is added before
     * each line.
     * 
     * @param lines the lines to be added
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder add(String... lines) {
	for (String line : lines) {
	    content.append("\n");
	    if (line == null) {
		line = "";
	    }
	    // check if the current line should end a block
	    for (String s : indentationDecreaseTags) {
		if (line.startsWith(s)) {
		    endBlock();
		    break;
		}
	    }
	    // append the right amount of indent
	    if (!line.isEmpty()) {
		for (int i = 0; i < indent; ++i) {
		    content.append(INDENTATION);
		}
	    }
	    // append the content line
	    content.append(line);

	    // check if the current line should start a block
	    for (String s : indentationIncreaseTags) {
		if (line.startsWith(s)) {
		    beginBlock();
		    break; // Otherwise, beginBlock() would be called two times, e.g. for "<header". since
			   // the startsWith check matches "<head" as well as "<header".
		}
	    }
	}
	return this;
    }

    /**
     * Increments the indentation counter manually. Needed for example if a call of
     * the {@link add} function is called with a line containing an element from
     * {@code indentationIncreaseTags} that should begin a block, but this element
     * is not the last character. This can happen, for example, when a line ends
     * with a single line comment.
     * 
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder beginBlock() {
	indent++;
	return this;
    }

    /**
     * Decrements the indentation counter manually. Needed for example if a call of
     * the {@link add} function is called with a line containing an element from
     * {@code indentationDecreaseTags} that should end a block, but this element is
     * not the first character of that line.
     * 
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder endBlock() {
	if (indent <= 0) {
	    throw new IllegalStateException("endBlock called too often");
	}
	indent--;
	return this;
    }

    /**
     * @return the content of this {@code HtmlBuilder}
     */
    @Override
    public String toString() {
	if (indent > 0) {
	    throw new IllegalStateException(indent + " missing endBlock calls");
	}
	return content.toString();
    }
}