package edu.kit.informatik.ui.commands.input;



import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.parser.UtilParser;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Command: add keywords to
 * Add keywords to an existing venue or publication
 * @author uppyo
 * @version 1.0
 */
public class AddKeywordsTo extends Command {
    private static final String PATTERN = "^add keywords to";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter listKeywords = ScholarParameter.keywordParameter().useAsList().build();
    private final Parameter idOrVenue = ScholarParameter.stringParameter().
            useAsField(List.of(this.listKeywords)).build();
    private final Parameter id = ScholarParameter.idParameter().build();

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public AddKeywordsTo(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(this.idOrVenue);
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
        String inputIdentifier = (String) parameterDict.get(idOrVenue).get(0);
        Set<String> keywords = new HashSet<>();
        for (Object keyword: parameterDict.get(listKeywords)) {
            keywords.add((String) keyword);
        }
        if (UtilParser.matchesParameter(this.id, inputIdentifier)) {
            Publication publication;
            try {
                publication = databaseProvider.getPublicationDatabase().getPublication(inputIdentifier);
            } catch (IdentifierException e) {
                return new Result(false, e.getMessage());
            }
            publication.addKeywords(keywords);
        } else {
            Venue venue;
            try {
                venue = databaseProvider.getVenueDatabase().getVenue(inputIdentifier);
            } catch (IdentifierException e) {
                return new Result(false, e.getMessage());
            }
            venue.addKeywords(keywords);
        }
        return new Result(true);
    }
}
