package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.data.objects.Author;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.parameter.ScholarParameter;
import edu.kit.informatik.ui.session.Result;


import edu.kit.informatik.ui.commands.parameter.Parameter;
import edu.kit.informatik.util.exception.IdentifierException;

import java.util.Dictionary;
import java.util.List;

/**
 * Command: add author
 * Add a new author to the Session
 * @author uppyo
 * @version 1.0
 */
public class AddAuthor extends Command {
    private static final String PATTERN = "^add author";
    private final List<Parameter> parameters;
    private final DatabaseProvider databaseProvider;
    private final Parameter name = ScholarParameter.nameParameter().build();

    /**
     * Get the database provider of the session
     * @param databaseProvider a provider of all databases
     */
    public AddAuthor(final DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
        this.parameters = List.of(name);
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
        Author newAuthor = new Author((String) parameterDict.get(this.name).get(0));
        try {
            databaseProvider.getAuthorDatabase().addAuthor(newAuthor);
        } catch (IdentifierException e) {
            return new Result(false, "author with same name already exists");
        }
        return new Result(true);
    }
}
