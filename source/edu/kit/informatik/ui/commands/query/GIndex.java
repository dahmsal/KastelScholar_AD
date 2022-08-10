package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.ObjectPair;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.Math;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Command: g-index
 * Get the g-index of a given author.
 * @author uppyo
 * @version 1.0
 */
public class GIndex extends Command {
    private static final String PATTERN = "^g-index";
    private final DatabaseProvider databaseProvider;
    private final Parameter author = ScholarParameter.nameParameter().build();
    private final List<Parameter> parameters;

    public GIndex(final DatabaseProvider databaseProvider) {
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
        String authorName = (String) parameterDict.get(author).get(0);
        Author givenAuthor;
        try {
            givenAuthor = this.databaseProvider.getAuthorDatabase().getAuthor(authorName);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        List<ObjectPair<Publication, Integer>> publicationCitation = new ArrayList<>();
        for (Publication publication
                : this.databaseProvider.getPublicationDatabase().findByAuthors(List.of(givenAuthor))) {
            int numberOfCitations = this.databaseProvider.getPublicationDatabase().numberOfCitations(publication);
            publicationCitation.add(new ObjectPair<>(publication, numberOfCitations));
        }
        Comparator<ObjectPair<Publication, Integer>> comparator
                = Comparator.comparing(ObjectPair::getSecond, Comparator.reverseOrder());
        publicationCitation.sort(comparator);
        int g = publicationCitation.size();
        while (g * g > Math.sumList(
                publicationCitation.subList(0, g).stream().map(ObjectPair::getSecond).collect(Collectors.toList()))
                && g > 0) {
            g--;
        }
        return new Result(true, String.valueOf(g));
    }
}
