package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.commands.parameter.Parameter;

import java.util.regex.Pattern;

/**
 * Collection of utility parser functions
 * @author uppyo
 * @version 1.0
 */
public class UtilParser {

    /**
     * Check if a input matches a given parameter
     * @param parameter parameter object
     * @param input string input
     * @return match-result
     */
    public static boolean matchesParameter(Parameter parameter, String input) {
        Pattern pattern = Pattern.compile(parameter.getPattern());
        return pattern.matcher(input).matches();
    }
}
