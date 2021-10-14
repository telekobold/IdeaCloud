package ideaCloud;

/*
 * Taken from
 * https://stackoverflow.com/questions/50224036/what-is-a-triple-in-java/50224161
 * derived from
 * https://github.com/apache/commons-lang/blob/master/src/main/java/org/apache/commons/lang3/tuple/Triple.java
 */
public class Triple<T, U, V> {

    private final T first;
    private final U second;
    private final V third;

    public Triple(T first, U second, V third) {
	this.first = first;
	this.second = second;
	this.third = third;
    }

    public T getFirst() {
	return first;
    }

    public U getSecond() {
	return second;
    }

    public V getThird() {
	return third;
    }
}
