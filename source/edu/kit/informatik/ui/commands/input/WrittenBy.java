package edu.kit.informatik.ui.commands.input;


import edu.kit.informatik.data.Database;
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
public class WrittenBy extends Command {
    private static final String PATTERN = "^add series";
    private final List<Parameter> parameters;
    private final Session session;
    private final Database database;

    public WrittenBy(final Session session, final Database database) {
        this.session = session;
        this.database = database;
        Parameter id = new Parameter.ParameterBuilder().pattern(ParameterPattern.IDENTIFIER).build();
        Parameter listAuthor = new Parameter.ParameterBuilder().pattern(ParameterPattern.NAME).useAsList().build();
        this.parameters = List.of(id, listAuthor);
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
