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

    public IdeaCloudTriple(String first, Integer second, String third) {
	this.tagName = first;
	if (second >= 1) {
	    this.weight = second;
	} else {
	    throw new IllegalArgumentException("weight must be positive!");
	}
	this.deadline = third;
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
