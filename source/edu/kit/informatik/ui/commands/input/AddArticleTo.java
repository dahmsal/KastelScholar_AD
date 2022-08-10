package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Dictionary;
import java.util.List;

/**
 * Command: add article to
 * Add a new publication to a existing venue
 * @author uppyo
 * @version 1.0
 */
public class AddArticleTo extends Command {
    private static final String PATTERN = "^add article to";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter id = ScholarParameter.idParameter().build();
    private final Parameter year = ScholarParameter.intParameter().build();
    private final Parameter title = ScholarParameter.stringParameter().build();
    private final Parameter venue = ScholarParameter.venueParameter()
            .useAsField(List.of(id, year, title)).build();

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public AddArticleTo(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(venue);
    }


    @Override
    public String getPattern() {
        return PATTERN;
    }

    /**
     * Add article to series has a venue parameter as field with data used to create a publication
     * @return venue parameter-field (venue:id,year,title)
     */
    @Override
    public List<Parameter> getParams() {
        return this.parameters;
    }

    @Override
    public Result exec(Dictionary<Parameter, List<Object>> parameterDict) {
        String venueName = (String) parameterDict.get(this.venue).get(0);
        Venue existingVenue;
        try {
            existingVenue = databaseProvider.getVenueDatabase().getVenue(venueName);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        String id = (String) parameterDict.get(this.id).get(0);
        int year = (int) parameterDict.get(this.year).get(0);
        String title = (String) parameterDict.get(this.title).get(0);
        Publication newPublication = new Publication(existingVenue, id, year, title);
        try {
            this.databaseProvider.getPublicationDatabase().addPublication(newPublication);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
