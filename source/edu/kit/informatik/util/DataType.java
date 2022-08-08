package edu.kit.informatik.util;

public enum DataType {

    STRING(""),
    INT(0) {
        @Override
        public Object cast(String input) {
            return Integer.parseInt(input);
        }
    },
    FLOAT((float) 0) {
        @Override
        public Object cast(String input) {
            return Float.parseFloat(input);
        }
    };

    private final Object typeObject;

    DataType(Object typeObject) {
        this.typeObject = typeObject;
    }

    public Object cast(String input) {
        return this.typeObject.getClass().cast(input);
    }

    public Class<?> typeClass() {
        return this.typeObject.getClass();
    }
}
