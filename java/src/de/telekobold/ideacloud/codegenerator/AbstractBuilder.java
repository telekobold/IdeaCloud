package de.telekobold.ideacloud.codegenerator;

/**
 * {@code AbstractBuilder} facilitates to generate nicely formatted code with
 * little programming effort. {@code AbstractBuilder} cares for automatic
 * indentation and handles comments properly.
 * </p>
 * Methods can be called in method-chaining style since they return a
 * {@code AbstractBuilder} instance.
 * </p>
 * {@code AbstractBuilder} keeps an internal {@link StringBuffer} to record the
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
public abstract class AbstractBuilder {

    static final String INDENTATION = "    ";
    int indent = 0;
    StringBuilder content = new StringBuilder();

    /**
     * Appends one or more single line comments to this {@code AbstractBuilder}
     *
     * @param lines the text of the comment lines
     * @return this {@code AbstractBuilder}
     */
    public abstract AbstractBuilder comment(String... lines);

    /**
     * Adds an empty line.
     *
     * @return this {@code AbstractBuilder}
     */
    public AbstractBuilder nl() {
	return add("");
    }

    /**
     * Clears this {@code AbstractBuilder}, i.e. all code is lost.
     */
    public void clear() {
	indent = 0;
	content = new StringBuilder();
    }

    /**
     * Appends one or more lines to this {@code AbstractBuilder} without newlines.
     * 
     * @param lines the lines to be added
     * @return this {@code AbstractBuilder}
     */
    public AbstractBuilder append(String... lines) {
	for (String line : lines) {
	    content.append(line);
	}
	return this;
    }

    /**
     * Adds one or more lines to this {@code AbstractBuilder} where a newline is
     * added before each line.
     * 
     * @param lines the lines to be added
     * @return this {@code AbstractBuilder}
     */
    public abstract AbstractBuilder add(String... lines);

    /**
     * @return the content of this {@code AbstractBuilder}
     */
    @Override
    public String toString() {
	if (indent > 0) {
	    throw new IllegalStateException(indent + " missing endBlock calls");
	}
	return content.toString();
    }

}
