package edu.kit.informatik.ui.commands.parameter;

import edu.kit.informatik.util.DataType;

/**
 * Collection of common parameter-builders for the KastelScholar project
 * @author uppyo
 * @version 1.0
 */
public class ScholarParameter {

    /**
     * A identifier-parameter: only lowercase and numbers
     * @return parameter-builder
     */
    public static Parameter.ParameterBuilder idParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.IDENTIFIER);
    }

    /**
     * A name-parameter, used for human names: two only letter words
     * @return parameter-builder
     */
    public static  Parameter.ParameterBuilder nameParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.NAME);
    }

    /**
     * A basic string parameter: string without linebreak, comma or semicolon
     * @return parameter-builder
     */
    public static Parameter.ParameterBuilder stringParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.STRING);
    }

    /**
     * A parameter that can be parsed to int: only numbers
     * @return parameter-builder with DataType set to INT
     */
    public static Parameter.ParameterBuilder intParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.INTEGER).setType(DataType.INT);
    }

    /**
     * A keyword parameter: only lowercase strings without numbers
     * @return parameter-builder
     */
    public static Parameter.ParameterBuilder keywordParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.LOWER_WORD);
    }

    /**
     * A venue parameter pattern: series|journal + String
     * @return parameter-builder
     */
    public static Parameter.ParameterBuilder venueParameter() {
        return new Parameter.ParameterBuilder().
                specialPattern("((\\bseries\\b)|(\\bjournal\\b))+ [a-zA-Z\\d\\s]+");
    }

    /**
     * A parameter to match the two bib-styles: acm|apa
     * @return parameter-builder
     */
    public static Parameter.ParameterBuilder bibStyleParameter() {
        return new Parameter.ParameterBuilder().specialPattern("((\\bacm\\b)|(\\bapa\\b))");
    }
}
