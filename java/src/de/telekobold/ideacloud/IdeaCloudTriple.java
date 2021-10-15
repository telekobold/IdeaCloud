package de.telekobold.ideacloud;

/**
 * Derived from
 * {@link https://stackoverflow.com/questions/50224036/what-is-a-triple-in-java/50224161}
 * which is again derived from
 * {@link https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/tuple/Triple.java}
 * 
 * @author Michael Merz <mail@telekobold.de>
 */
public class IdeaCloudTriple<T, U, V> {

    private final String tagName;
    private final Integer weight;
    private final String deadline;

    public IdeaCloudTriple(String tagName, Integer weight, String deadline) {
	this.tagName = tagName;
	// check if the given weight is a positive value between 1 and 15
	if (weight >= 1 && weight <= 15) {
	    this.weight = weight;
	} else {
	    throw new IllegalArgumentException("weight must be a value between 1 and 15!");
	}
	// check if the given deadline is a valid date
	if (deadline == null) {
	    this.deadline = "";
	} else if (deadline.matches("[0-9]{4}-[0-1][0-9]-[0-3][0-9]")) {
	    this.deadline = deadline;
	} else {
	    throw new IllegalArgumentException("deadline must have the form yyyy-mm-dd!");
	}
    }

    public String getTagName() {
	return tagName;
    }

    public Integer getWeight() {
	return weight;
    }

    public String getDeadline() {
	return deadline;
    }
}
