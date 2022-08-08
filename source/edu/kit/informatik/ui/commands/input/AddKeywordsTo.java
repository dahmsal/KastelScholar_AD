package edu.kit.informatik.ui.commands.input;



import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.ui.commands.parameter.ParameterPattern;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

/**
 *
 */
public class AddKeywordsTo extends Command {
    private static final String PATTERN = "^add keywords to";
    private final List<Parameter> parameters;
    private final Session session;
    private final DatabaseProvider databaseProvider;
    private final Parameter listKeywords = new Parameter.ParameterBuilder()
            .pattern(ParameterPattern.LOWER_WORD).useAsList().build();
    private final Parameter idOrVenue = new Parameter.ParameterBuilder()
            .pattern(ParameterPattern.STRING).useAsField(List.of(listKeywords)).build();

    public AddKeywordsTo(final Session session, final DatabaseProvider databaseProvider) {
        this.session = session;
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(this.idOrVenue);
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
            System.out.println(parameter.getPattern());
            System.out.println(parameterDict.get(parameter).toString());
        }
        return new Result(true);
    }
}
