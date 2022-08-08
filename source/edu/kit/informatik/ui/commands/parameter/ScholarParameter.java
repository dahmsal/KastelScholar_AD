package edu.kit.informatik.ui.commands.parameter;

import edu.kit.informatik.util.DataType;

public class ScholarParameter {

    public static Parameter.ParameterBuilder idParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.IDENTIFIER);
    }

    public static  Parameter.ParameterBuilder nameParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.NAME);
    }

    public static Parameter.ParameterBuilder stringParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.STRING);
    }

    public static Parameter.ParameterBuilder intParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.INTEGER).setType(DataType.INT);
    }

    public static Parameter.ParameterBuilder keywordParameter() {
        return new Parameter.ParameterBuilder().pattern(ParameterPattern.LOWER_WORD);
    }

    public static Parameter.ParameterBuilder venueParameter() {
        return new Parameter.ParameterBuilder().
                specialPattern("((\\bseries\\b)|(\\bjournal\\b))+ [a-zA-Z\\d\\s]+");
    }
}
