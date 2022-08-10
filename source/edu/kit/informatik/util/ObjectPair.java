package edu.kit.informatik.util;

/**
 * Create a tuple of objects of different types
 * @param <A> first object-type of the tuple
 * @param <B> second object-type of the tuple
 * @author uppyo
 * @version 1.0
 */
public class ObjectPair<A, B> {
    private final A object1;
    private final B object2;

    /**
     * Add Objects to pair, the pair is immutable after initialisation
     * @param object1 first object of type A
     * @param object2 second object of type B
     */
    public ObjectPair(A object1, B object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    /**
     * Get the first object of the pair
     * @return Object of type A
     */
    public A getFirst() {
        return this.object1;
    }

    /**
     * Get the second object of the pair
     * @return Object of type B
     */
    public B getSecond() {
        return this.object2;
    }

}
