package edu.kit.informatik.ui.commands.input;


import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.venue.Series;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Dictionary;
import java.util.List;

/**
 * Command: add series
 * Add a new series to the session
 * @author uppyo
 * @version 1.0
 */
public class AddSeries extends Command {
    private static final String PATTERN = "^add series";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter name = ScholarParameter.stringParameter().build();

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public AddSeries(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(name);
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
