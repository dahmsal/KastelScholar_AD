package edu.kit.informatik.ui.commands.input;



import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;

import java.security.InvalidParameterException;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
public class WrittenBy extends Command {

    private static final String PATTERN = "^written by";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter id = ScholarParameter.idParameter().build();
    private final Parameter listAuthor = ScholarParameter.nameParameter().useAsList().build();

    public WrittenBy(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(id, listAuthor);
    }

    @Override
    public String getPattern() {
        return PATTERN;
    }

    /**
     * The quit-command has no parameters
     * @return empty list
     */
    @Override
    public List<Parameter> getParams() {
        return this.parameters;
    }

    @Override
    public Result exec(Dictionary<Parameter, List<Object>> parameterDict) {
        String publicationID = (String) parameterDict.get(this.id).get(0);
        Set<Author> authors = new HashSet<>();
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
        } catch (InvalidParameterException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
