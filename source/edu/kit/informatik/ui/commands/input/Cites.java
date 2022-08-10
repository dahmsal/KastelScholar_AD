package edu.kit.informatik.ui.commands.input;



import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;

import java.security.InvalidParameterException;
import java.util.Dictionary;
import java.util.List;

/**
 *
 */
public class Cites extends Command {
    private static final String PATTERN = "^cites";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter id1 = ScholarParameter.idParameter().build();
    private final Parameter id2 = ScholarParameter.idParameter().build();

    public Cites(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(this.id1, this.id2);
    }


    @Override
    public String getPattern() {
        return PATTERN;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Parameter> getParams() {
        return this.parameters;
    }

    @Override
    public Result exec(Dictionary<Parameter, List<Object>> parameterDict) {
        String publicationId1 = (String) parameterDict.get(this.id1).get(0);
        String publicationId2 = (String) parameterDict.get(this.id2).get(0);
        Publication publication1;
        Publication publication2;
        try {
            publication1 = databaseProvider.getPublicationDatabase().getPublication(publicationId1);
            publication2 = databaseProvider.getPublicationDatabase().getPublication(publicationId2);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        try {
            publication1.addCitation(publication2);
        } catch (InvalidParameterException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
