package edu.kit.informatik.ui.commands.query;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Publication;
import edu.kit.informatik.data.objects.venue.Series;
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

/**
 * Command: in proceedings
 * Get all publications of a conference from a series
 * @author uppyo
 * @version 1.0
 */
public class InProceedings extends Command {
    private static final String PATTERN = "^in proceedings";
    private final DatabaseProvider databaseProvider;
    private final Parameter series = ScholarParameter.stringParameter().build();
    private final Parameter year = ScholarParameter.intParameter().build();
    private final List<Parameter> parameters;

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public InProceedings(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(series, year);
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
        String seriesName = (String) parameterDict.get(series).get(0);
        int seriesYear = (Integer) parameterDict.get(year).get(0);
        Series series;
        try {
            series = this.databaseProvider.getVenueDatabase().getSeries(seriesName);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        List<Publication> publicationInSeries = this.databaseProvider.getPublicationDatabase().findByVenue(series);
        List<String> resultID = new ArrayList<>();
        for (Publication publication:publicationInSeries) {
            if (publication.getYear() == seriesYear) {
                resultID.add(publication.getId());
            }
        }
        if (resultID.isEmpty()) {
            return new Result(true, UtilStrings.getEmptyString());
        }
        return new Result(true, CreateOutput.getListOutput(resultID));
    }
}
