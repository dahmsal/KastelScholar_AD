package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Command: coauthors of
 * Returns all co-authors of a given author
 * @author uppyo
 * @version 1.0
 */
public class CoAuthorsOf extends Command {
    private static final String PATTERN = "^coauthors of";
    private final DatabaseProvider databaseProvider;
    private final Parameter author = ScholarParameter.nameParameter().build();
    private final List<Parameter> parameters;

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public CoAuthorsOf(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(author);
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
        String nameString = (String) parameterDict.get(this.author).get(0);
        Author givenAuthor;
        try {
            givenAuthor = databaseProvider.getAuthorDatabase().getAuthor(nameString);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        Set<Author> resultsSet = databaseProvider.getPublicationDatabase().findCoAuthors(givenAuthor);
        if (resultsSet.isEmpty()) {
            return new Result(true);
        }
        resultsSet.remove(givenAuthor);
        List<String> resultNames = resultsSet.stream().map(Author::getId).collect(Collectors.toList());
        return new Result(true, CreateOutput.getListOutput(resultNames));
    }
}
