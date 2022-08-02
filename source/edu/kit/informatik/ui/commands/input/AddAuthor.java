package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.Database;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.ParameterPattern;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;

import edu.kit.informatik.ui.commands.parameter.Parameter;

import java.util.Dictionary;
import java.util.List;

/**
 *
 */
public class AddAuthor extends Command {
    private static final String PATTERN = "^add author";
    private final List<Parameter> parameters;
    private final Session session;
    private final Database database;

    public AddAuthor(final Session session, final Database database) {
        this.session = session;
        this.database = database;
        Parameter author = new Parameter.ParameterBuilder().pattern(ParameterPattern.NAME).useAsList().build();
        this.parameters = List.of(author);
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
    public Result exec(Dictionary<Parameter, Object> parameterDict) {
        for (Parameter parameter: this.parameters
        ) {
            System.out.println(parameterDict.get(parameter).toString());
        }
        return new Result(true);
    }
}
