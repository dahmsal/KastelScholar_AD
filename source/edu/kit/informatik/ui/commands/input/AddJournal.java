package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.venue.Journal;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterPattern;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.exception.IdentifierException;
import edu.kit.informatik.util.exception.InputException;

import java.util.Dictionary;
import java.util.List;

/**
 *
 */
public class AddJournal extends Command {
    private static final String PATTERN = "^add journal";
    private final List<Parameter> parameters;
    private final Session session;
    private final DatabaseProvider databaseProvider;
    private final Parameter name = ScholarParameter.stringParameter().build();
    private final Parameter publisher = ScholarParameter.stringParameter().build();

    public AddJournal(final Session session, final DatabaseProvider databaseProvider) {
        this.session = session;
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(this.name, this.publisher);
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
        String journalName = (String) parameterDict.get(this.name).get(0);
        String journalPublisher = (String) parameterDict.get(this.publisher).get(0);
        Journal newJournal = new Journal(journalName, journalPublisher);
        try {
            this.databaseProvider.getVenueDatabase().addVenue(newJournal);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
