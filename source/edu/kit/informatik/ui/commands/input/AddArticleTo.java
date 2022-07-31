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
public class AddArticleTo extends Command {
    private static final String PATTERN = "^add article to";
    private final List<Parameter> parameters;
    private final Session session;
    private final Database database;

    public AddArticleTo(final Session session, final Database database) {
        this.session = session;
        this.database = database;
        Parameter venue = new Parameter.ParameterBuilder().pattern(Pattern.STRING).build();
        Parameter doubleDot = new Parameter.ParameterBuilder().specialPattern(":").build();
        Parameter id = new Parameter.ParameterBuilder().pattern(Pattern.IDENTIFIER).build();
        Parameter year = new Parameter.ParameterBuilder().pattern(Pattern.INTEGER).build();
        Parameter title = new Parameter.ParameterBuilder().pattern(Pattern.STRING).build();
        this.parameters = List.of(venue, doubleDot, id, year, title);
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
