package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.Math;

import java.util.*;
import java.util.stream.Collectors;

public class Jaccard extends Command {
    private static final String PATTERN = "^jaccard";
    private final DatabaseProvider databaseProvider;
    private final Parameter listKeywords1 = ScholarParameter.keywordParameter().useAsList().hasSpaceDelimiter().build();
    private final Parameter listKeywords2 = ScholarParameter.keywordParameter().useAsList().build();
    private final List<Parameter> parameters;

    public Jaccard(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
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
