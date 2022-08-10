package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.Dictionary;
import java.util.List;

public class ListInvalidPublications extends Command {
    private static final String PATTERN = "^list invalid publications";
    private final DatabaseProvider databaseProvider;
    private final List<Parameter> parameters;

    public ListInvalidPublications(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of();
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
        List<String> publicationIdList = this.databaseProvider.getPublicationDatabase().findInvalid();
        if (publicationIdList.isEmpty()) {
            return new Result(true, UtilStrings.getEmptyString());
        }
        return new Result(true, CreateOutput.getListOutput(publicationIdList));
    }
}
