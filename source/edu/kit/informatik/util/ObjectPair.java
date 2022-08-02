package edu.kit.informatik.util;

public class ObjectPair<A, B> {
    private final A object1;
    private final B object2;

    public ObjectPair(A object1, B object2) {
        this.object1 = object1;
        this.object2 = object2;
    }

    public A getFirst() {
        return this.object1;
    }

    public B getSecond() {
        return this.object2;
    }

}
