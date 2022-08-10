package edu.kit.informatik.ui.output;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.kit.informatik.util.strings.UtilStrings;

/**
 * Create outputs for the console
 * @author uppyo
 * @version 1.0
 */
public class CreateOutput {

    private static final int CHAR_LENGTH = 5;
    private static final String CHAR_FORMAT = "%.4f";

    /**
     * Create an ordered output for list of elements
     * @param outputElements list of string elements
     * @return a ordered list output with linebreaks
     */
    public static String getListOutput(List<String> outputElements) {
        if (outputElements.isEmpty()) {
            return UtilStrings.getEmptyString();
        }
        Stream<String> outputStream = outputElements.stream().sorted();
        List<String> outputSorted = outputStream.collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (String output: outputSorted) {
            stringBuilder.append(output);
            stringBuilder.append(UtilStrings.getLinebreak());
        }
        int trailingLinebreak = stringBuilder.lastIndexOf(UtilStrings.getLinebreak());
        stringBuilder.deleteCharAt(trailingLinebreak);
        return stringBuilder.toString();
    }

    /**
     * Get non-rounded float with 3 decimals
     * @param value float value to parse
     * @return string of the cut float
     */
    public static String getCutFloat(float value) {
        return String.format(CHAR_FORMAT, value).substring(0, CHAR_LENGTH);
    }
}
