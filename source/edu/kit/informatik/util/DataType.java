package edu.kit.informatik.util;

public enum DataType {

    STRING(""),
    INT(0),
    FLOAT((float) 0);

    private final Object typeObject;

    DataType(Object typeObject) {
        this.typeObject = typeObject;
    }

    public Object cast(Object input) {
        return this.typeObject.getClass().cast(input);
    }

    public Class<?> typeClass() {
        return this.typeObject.getClass();
    }
}
