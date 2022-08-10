package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.Math;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class Similarity extends Command {
    private static final String PATTERN = "similarity";
    private final DatabaseProvider databaseProvider;
    private final Parameter id1 = ScholarParameter.idParameter().build();
    private final Parameter id2 = ScholarParameter.idParameter().build();
    private final List<Parameter> parameters;

    public Similarity(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(id1, id2);
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
        String pubId1 = (String) parameterDict.get(id1).get(0);
        String pubId2 = (String) parameterDict.get(id2).get(0);
        Publication publication1;
        Publication publication2;
        try {
            publication1 = this.databaseProvider.getPublicationDatabase().getPublication(pubId1);
            publication2 = this.databaseProvider.getPublicationDatabase().getPublication(pubId2);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        float jaccard = Math.jaccard(publication1.getKeywords(), publication2.getKeywords());
        return new Result(true, CreateOutput.getCutFloat(jaccard));
    }
}
