package edu.kit.informatik.ui.output;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import edu.kit.informatik.util.strings.UtilStrings;

public class CreateListOutput {

    public static String getListOutput(ArrayList<String> outputElements) {
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
}