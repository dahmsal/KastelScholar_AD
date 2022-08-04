package edu.kit.informatik.ui.Parser;

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
}
