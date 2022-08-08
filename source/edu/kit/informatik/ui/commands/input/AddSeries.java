package edu.kit.informatik.ui.commands.input;


import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.venue.Series;
import edu.kit.informatik.data.objects.venue.Venue;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterPattern;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Dictionary;
import java.util.List;

/**
 *
 */
public class AddSeries extends Command {
    private static final String PATTERN = "^add series";
    private final List<Parameter> parameters;
    private final Session session;
    private final DatabaseProvider databaseProvider;
    private final Parameter name = ScholarParameter.stringParameter().build();

    public AddSeries(final Session session, final DatabaseProvider databaseProvider) {
        this.session = session;
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(name);
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
        String name = (String) parameterDict.get(this.name).get(0);
        Series newSeries = new Series(name);
        try {
            databaseProvider.getVenueDatabase().addVenue(newSeries);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
