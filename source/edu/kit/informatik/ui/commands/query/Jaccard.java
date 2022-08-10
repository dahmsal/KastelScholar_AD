package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.Math;

import java.util.*;

/**
 * Command: jaccard
 * Get the jaccard-index (a measure for similarity between two sets) for two sets of keywords
 * @author uppyo
 * @version 1.0
 */
public class Jaccard extends Command {
    private static final String PATTERN = "^jaccard";
    private final Parameter listKeywords1 = ScholarParameter.keywordParameter().useAsList().hasSpaceDelimiter().build();
    private final Parameter listKeywords2 = ScholarParameter.keywordParameter().useAsList().build();
    private final List<Parameter> parameters;

    /**
     * The jaccard-command requires no data from the session
     */
    public Jaccard() {
        this.parameters = List.of(listKeywords1, listKeywords2);
    }

    @Override
    public String getPattern() {
        return PATTERN;
    }

    @Override
    public List<Parameter> getParams() {
        return this.parameters;
    }

    @Override
    public Result exec(Dictionary<Parameter, List<Object>> parameterDict) {
        Set<String> keywordsSet1 = new HashSet<>();
        Set<String> keywordsSet2 = new HashSet<>();
        for (Object keyword: parameterDict.get(listKeywords1)) {
            keywordsSet1.add((String) keyword);
        }
        for (Object keyword: parameterDict.get(listKeywords2)) {
            keywordsSet2.add((String) keyword);
        }
        float jaccard = Math.jaccard(keywordsSet1, keywordsSet2);
        return new Result(true, CreateOutput.getCutFloat(jaccard));
    }
}
