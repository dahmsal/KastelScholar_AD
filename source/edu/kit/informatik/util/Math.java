package edu.kit.informatik.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Collection of useful math operations
 * @author uppyo
 * @version 1.0
 */
public class Math {

    /**
     * Get the sum of all numbers of a list
     * @param integerList list of integers
     * @return sum of integers in list
     */
    public static int sumList(List<Integer> integerList) {
        int sum = 0;
        for (Integer value: integerList) {
            sum += value;
        }
        return sum;
    }

    /**
     * Calculate the jaccard-coefficient of two sets of variable datatype
     * @param set1 first input set
     * @param set2 second input set
     * @return similarity of both sets
     */
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
