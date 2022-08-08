package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.output.CreateListOutput;
import edu.kit.informatik.ui.session.Result;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class AllPublications extends Command {
    private static final String PATTERN = "all publications";
    private final DatabaseProvider databaseProvider;
    private final List<Parameter> parameters;

    public AllPublications(final DatabaseProvider databaseProvider) {
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
    public Result exec(Dictionary<Parameter, List<Object>> parameters) {
        ArrayList<String> publicationIdList = new ArrayList<>();
        for (Publication publication: this.databaseProvider.getPublicationDatabase().getAllPublications()) {
            publicationIdList.add(publication.getId());
        }
        return new Result(true, CreateListOutput.getListOutput(publicationIdList));
    }
}
