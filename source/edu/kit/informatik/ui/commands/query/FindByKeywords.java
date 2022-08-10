package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;

import java.util.*;

public class FindByKeywords extends Command {
    private static final String PATTERN = "^find by keywords";
    private final DatabaseProvider databaseProvider;
    private final Parameter listKeywords = ScholarParameter.keywordParameter().useAsList().build();
    private final List<Parameter> parameters;

    public FindByKeywords(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(listKeywords);
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
        List<String> keywords = new ArrayList<>();
        List<String> resultId;
        for (Object keyword: parameterDict.get(listKeywords)) {
            keywords.add((String) keyword);
        }
        resultId = databaseProvider.getPublicationDatabase().findByKeywords(keywords);
        if (resultId.isEmpty()) {
            return new Result(true);
        }
        return new Result(true, CreateOutput.getListOutput(resultId));
    }
}
