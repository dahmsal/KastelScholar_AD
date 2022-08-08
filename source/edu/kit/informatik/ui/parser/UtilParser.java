package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.commands.parameter.Parameter;

import java.util.List;
import java.util.regex.Pattern;

public class UtilParser {

    public static String findMatchFromList(List<String> patterns, String input) {
        for (String pattern: patterns) {
            Pattern compiledPattern = Pattern.compile(pattern);
            if (compiledPattern.matcher(input).find()) {
                return pattern;
            }
        }
        return null;
    }

    public static boolean matchesParameter(Parameter parameter, String input) {
        Pattern pattern = Pattern.compile(parameter.getPattern());
        return pattern.matcher(input).matches();
    }
}
