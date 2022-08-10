package edu.kit.informatik.util;

import edu.kit.informatik.util.strings.UtilStrings;

/**
 * Description of basic data types
 */
public enum DataType {
    /**
     * String-Type returns empty string
     */
    STRING(UtilStrings.getEmptyString()),
    /**
     * int-type returns Integer object
     */
    INT(0) {
        @Override
        public Object cast(String input) {
            return Integer.parseInt(input);
        }
    };

    private final Object typeObject;

    DataType(Object typeObject) {
        this.typeObject = typeObject;
    }

    /**
     * Typecast an input to the given data-type
     * @param input string object to be casted
     * @return Object casted object
     */
    public Object cast(String input) {
        return this.typeObject.getClass().cast(input);
    }

}
