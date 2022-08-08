package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterPattern;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;

import java.util.Dictionary;
import java.util.List;

/**
 *
 */
public class AddJournal extends Command {
    private static final String PATTERN = "^add journal";
    private final List<Parameter> parameters;
    private final Session session;
    private final DatabaseProvider database;

    public AddJournal(final Session session, final DatabaseProvider database) {
        this.session = session;
        this.database = database;
        Parameter journal = new Parameter.ParameterBuilder().pattern(ParameterPattern.STRING).build();
        Parameter publisher = new Parameter.ParameterBuilder().pattern(ParameterPattern.STRING).build();
        this.parameters = List.of(journal, publisher);
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
        for (Parameter parameter: this.parameters
        ) {
            System.out.println(parameterDict.get(parameter).toString());
        }
        return new Result(true);
    }
}
