package ideaCloud;

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

	/**
	 * Appends a block comment ({@code <!-- comment -->}) to this
	 * {@code HtmlBuilder}.
	 *
	 * @param lines the text of the comment lines.
	 * @return this {@code HtmlBuilder}
	 */
	public HtmlBuilder blockComment(String... lines) {
		for (String line : lines) {
			if (line.contains("-->")) {
				throw new IllegalArgumentException("block comments must not contain the comment end tag -->");
			}
		}
		return append("<!--").add(lines).append("-->");
	}

	/**
	 * Appends one or more single line comments to this {@code HtmlBuilder}
	 *
	 * @param lines the text of the comment lines
	 * @return this {@code HtmlBuilder}
	 */
	public HtmlBuilder comment(String... lines) {
		return add("<!--", lines, "-->");
	}

	/**
	 * Adds an empty line.
	 *
	 * @return this CBuilder
	 */
	public CBuilder nl() {
		return append("");
	}

	/**
	 * Appends one or more lines to this {@code HtmlBuilder}.
	 *
	 * @param lines the text of the lines
	 * @return this {@code HtmlBuilder}
	 */
	public HtmlBuilder append(String... lines) {
		return add(null, lines);
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
	private HtmlBuilder add(String commentPrefix, String... lines) {
		for (String line : lines) {
			if (line == null) {
				line = "";
			}
			// check if the current line should end a C-block
			if (commentPrefix == null && line.startsWith("}")) {
				endBlock();
			}
			// append the right amount of indent
			if (!line.isEmpty() || commentPrefix != null) {
				for (int i = 0; i < indent; ++i) {
					content.append(INDENTATION);
				}
			}
			// append the comment prefix inside comments
			if (commentPrefix != null) {
				content.append(commentPrefix);
				if (!line.isEmpty()) {
					content.append(" ");
				}
			}
			// append the content line
			content.append(line).append("\n");
			// check if the current line should start a block
			if (commentPrefix == null && line.endsWith("{")) {
				beginBlock();
			}
		}
		return this;
	}

	/**
	 * Increments the indentation counter manually. Needed for example if a call of
	 * the append function is called with a line containing a <code>{</code> that
	 * should begin a block, but <code>{</code> is not the last character. This can
	 * happen, for example, when a line ends with a single line comment.
	 */
	public CBuilder beginBlock() {
		indent++;
		return this;
	}

	/**
	 * Decrements the indentation counter manually. Needed for example if a call of
	 * the append function is called with a line containing a <code>}</code> that
	 * should end a block, but is not the first character of that line.
	 */
	public CBuilder endBlock() {
		if (indent <= 0) {
			throw new IllegalStateException("endBlock called too often");
		}
		indent--;
		return this;
	}

	/**
	 * returns the content of this CBuilder
	 */
	@Override
	public String toString() {
		if (indent > 0) {
			throw new IllegalStateException(indent + " missing endBlock calls");
		}
		return content.toString();
	}
}