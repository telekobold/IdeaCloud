package ideaCloud;

import java.util.ArrayList;
import java.util.List;

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
 * @author telekobold
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
	indentationIncreaseTags.add("<html>");
	indentationIncreaseTags.add("<head>");
	indentationIncreaseTags.add("<body>");
	indentationIncreaseTags.add("<header>");
	indentationIncreaseTags.add("<main>");
	indentationIncreaseTags.add("<ul>");

	indentationDecreaseTags.add("<\\html>");
	indentationDecreaseTags.add("<\\head>");
	indentationDecreaseTags.add("<\\body>");
	indentationDecreaseTags.add("<\\header>");
	indentationDecreaseTags.add("<\\main>");
	indentationDecreaseTags.add("<\\ul>");
    }

    /**
     * Appends one or more single line comments to this {@code HtmlBuilder}
     *
     * @param lines the text of the comment lines
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder comment(String... lines) {
	return add("<!--").add(lines)
		.add("-->");
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
     * Adds one or more lines of text to this {@code HtmlBuilder}.
     *
     * @param commentPrefix the prefix for each of the lines inside comments
     * @param lines         the text of the lines
     * @return this {@code HtmlBuilder}
     */
    public HtmlBuilder add(String... lines) {
	for (String line : lines) {
	    if (line == null) {
		line = "";
	    }
	    // check if the current line should end a block
	    for (String s : indentationDecreaseTags) {
		if (line.startsWith(s)) {
		    endBlock();
		}
	    }
	    // append the right amount of indent
	    if (!line.isEmpty()) {
		for (int i = 0; i < indent; ++i) {
		    content.append(INDENTATION);
		}
	    }
	    // append the content line
	    content.append(line)
		    .append("\n");
	    // check if the current line should start a block
	    for (String s : indentationIncreaseTags) {
		if (line.startsWith(s)) {
		    beginBlock();
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