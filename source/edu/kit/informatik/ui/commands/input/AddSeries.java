package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.Database;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.Pattern;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;

import java.util.List;

/**
 *
 */
public class AddSeries extends Command {
    private static final String PATTERN = "^add series";
    private final List<Parameter> parameters;
    private final Session session;
    private final Database database;

    public AddSeries(final Session session, final Database database) {
        this.session = session;
        this.database = database;
        Parameter series = new Parameter.ParameterBuilder().pattern(Pattern.STRING).build();
        this.parameters = List.of(series);
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
    public Result exec(List<String> parameters) {
        for (String param: parameters
        ) {
            System.out.println(param);
        }
        return new Result(true);
    }
}
