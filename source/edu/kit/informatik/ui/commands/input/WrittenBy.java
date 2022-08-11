package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.ParameterException;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;


/**
 * Command: written by
 * Add a list of authors to a existing publication
 * @author uppyo
 * @version 1.0
 */
public class WrittenBy extends Command {

    private static final String PATTERN = "^written by";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter id = ScholarParameter.idParameter().build();
    private final Parameter listAuthor = ScholarParameter.nameParameter().useAsList().build();

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public WrittenBy(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(id, listAuthor);
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
        String publicationID = (String) parameterDict.get(this.id).get(0);
        List<Author> authors = new ArrayList<>();
        Publication publication;
        try {
            for (Object author: parameterDict.get(listAuthor)) {
                authors.add(databaseProvider.getAuthorDatabase().getAuthor((String) author));
            }
            publication = databaseProvider.getPublicationDatabase().getPublication(publicationID);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        try {
            publication.addAuthors(authors);
        } catch (ParameterException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
