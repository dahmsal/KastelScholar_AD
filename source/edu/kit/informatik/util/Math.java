package edu.kit.informatik.util;

import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Math {

    public static int sumList(List<Integer> integerList) {
        int sum = 0;
        for (Integer value: integerList) {
            sum += value;
        }
        return sum;
    }

    public static float jaccard(Set<?> set1, Set<?> set2) {
        List<Object> commonElements = set1.stream().filter(set2::contains).collect(Collectors.toList());
        Set<Object> allElements = new HashSet<>();
        allElements.addAll(set1);
        allElements.addAll(set2);
        float jaccard;
        if (allElements.size() == 0) {
            jaccard = 1;
            return jaccard;
        }
        jaccard = (float) commonElements.size() / (float) allElements.size();
        return jaccard;
    }
}
