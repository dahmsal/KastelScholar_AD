package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.output.CreateOutput;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Command: foreign citations of
 * Find all foreign citations of an author, all citations of publications where none of the co-authors of the author
 * were involved.
 * @author uppyo
 * @version 1.0
 */
public class ForeignCitationsOf extends Command {
    private static final String PATTERN = "^foreign citations of";
    private final DatabaseProvider databaseProvider;
    private final Parameter author = ScholarParameter.nameParameter().build();
    private final List<Parameter> parameters;

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public ForeignCitationsOf(final DatabaseProvider databaseProvider) {
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
        Author givenAuthor;
        String authorName = (String) parameterDict.get(author).get(0);
        try {
            givenAuthor = databaseProvider.getAuthorDatabase().getAuthor(authorName);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        Set<Author> coAuthors = databaseProvider.getPublicationDatabase().findCoAuthors(givenAuthor);
        coAuthors.add(givenAuthor);
        Set<Publication> resultSet = new HashSet<>();
        for (Publication publication:databaseProvider.getPublicationDatabase().findByAuthors(List.of(givenAuthor))) {
            for (Publication citation: publication.getCitations()) {
                if (citation.getAuthors().stream().noneMatch(coAuthors::contains) && citation.isValid()) {
                    resultSet.add(citation);
                }
            }
        }
        if (resultSet.isEmpty()) {
            return new Result(true);
        }
        List<String> resultIDs = resultSet.stream().map(Publication::getId).collect(Collectors.toList());
        return new Result(true, CreateOutput.getListOutput(resultIDs));
    }
}
