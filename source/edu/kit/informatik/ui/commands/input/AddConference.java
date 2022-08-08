package edu.kit.informatik.ui.commands.input;


import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.venue.Series;
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
public class AddConference extends Command {
    private static final String PATTERN = "^add conference";
    private final List<Parameter> parameters;
    private final Session session;
    private final DatabaseProvider databaseProvider;
    private final Parameter series = ScholarParameter.stringParameter().build();
    private final Parameter year = ScholarParameter.intParameter().build();
    private final Parameter location = ScholarParameter.stringParameter().build();

    public AddConference(final Session session, final DatabaseProvider databaseProvider) {
        this.session = session;
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(this.series, this.year, this.location);
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
        String seriesName = (String) parameterDict.get(this.series).get(0);
        int conferenceYear = (int) parameterDict.get(this.year).get(0);
        String conferenceLocation = (String) parameterDict.get(this.location).get(0);
        Series seriesObj;
        try {
            seriesObj = this.databaseProvider.getVenueDatabase().getSeries(seriesName);
        } catch (IdentifierException e) {
            return new Result(false, "no series with given name exists");
        }
        try {
            seriesObj.addConference(conferenceYear, conferenceLocation);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
