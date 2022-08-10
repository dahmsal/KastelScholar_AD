package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.venue.Journal;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.util.exception.IdentifierException;


import java.util.Dictionary;
import java.util.List;

/**
 * Command: add journal
 * Add a new journal to the session
 * @author uppyo
 * @version 1.0
 */
public class AddJournal extends Command {
    private static final String PATTERN = "^add journal";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter name = ScholarParameter.stringParameter().build();

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public AddJournal(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        // publisher has no relevance in the database
        Parameter publisher = ScholarParameter.stringParameter().build();
        this.parameters = List.of(this.name, publisher);
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
        String journalName = (String) parameterDict.get(this.name).get(0);
        Journal newJournal = new Journal(journalName);
        try {
            this.databaseProvider.getVenueDatabase().addVenue(newJournal);
        } catch (IdentifierException e) {
            return new Result(false, e.getMessage());
        }
        return new Result(true);
    }
}
