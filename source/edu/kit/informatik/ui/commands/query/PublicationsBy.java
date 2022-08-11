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
import edu.kit.informatik.util.strings.UtilStrings;


import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Command: publications by
 * Returns all publications by a given list of authors
 * @author uppyo
 * @version 1.0
 */
public class PublicationsBy extends Command {
    private static final String PATTERN = "^publications by";
    private final DatabaseProvider databaseProvider;
    private final Parameter listAuthors = ScholarParameter.nameParameter().useAsList().build();
    private final List<Parameter> parameters;

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public PublicationsBy(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(listAuthors);
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
        List<Author> authors = new ArrayList<>();
        try {
            for (Object name:parameterDict.get(listAuthors)) {
                authors.add(this.databaseProvider.getAuthorDatabase().getAuthor((String) name));
            }
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        Set<Publication> publications = this.databaseProvider.getPublicationDatabase().findByAuthors(authors);
        if (publications.isEmpty()) {
            return new Result(true, UtilStrings.getEmptyString());
        }
        return new Result(true,
                CreateOutput.getListOutput(publications.stream().map(Publication::getId).collect(Collectors.toList())));
    }
}
