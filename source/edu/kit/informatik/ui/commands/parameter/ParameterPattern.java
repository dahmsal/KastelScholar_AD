package edu.kit.informatik.ui.commands.parameter;

/**
 * A collection of useful regex patterns
 * @author uppyo
 * @version 1.0
 */
public enum ParameterPattern {
    /**
     * A generic String-pattern, can be string with whitespace and numbers.
     * Does not match "," or ";"
     */
    STRING("[a-zA-Z\\d\\s]+"),
    /**
     * A pattern for single lowercase words
     */
    LOWER_WORD("[a-z]+"),
    /**
     * A generic Integer pattern, can be any Integer without whitespace or ","
     */
    INTEGER("[\\d]+"),
    /**
     * A pattern for names. Names have a first and a last name as Strings
     */
    NAME("[a-zA-Z]+ [a-zA-Z]+"),
    /**
     * A pattern for identifiers, only lowercase and numbers without spaces
     */
    IDENTIFIER("[a-z\\d]+");

    private final String pattern;

    /**
     * Every enum entry has a associated parameter pattern
     * @param pattern associated parameter pattern
     */
    ParameterPattern(final String pattern) {
        this.pattern = pattern;
    }

    /**
     * get the regex-patten for a given pattern
     * @return regex as String
     */
    public String getPattern() {
        return this.pattern;
    }
}
