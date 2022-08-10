package edu.kit.informatik.ui.commands.input;

import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.session.Result;
import edu.kit.informatik.ui.session.Session;

import edu.kit.informatik.ui.commands.parameter.Parameter;

import java.util.Dictionary;
import java.util.List;


/**
 * The quit command quits the program and needs no parameters
 * @author uppyo
 * @version 1.0
 */
public class Quit extends Command {
    private static final String PATTERN = "quit";

    private final Session session;

    /**
     * Signals the session to quit command-processing
     * @param session current session
     */
    public Quit(final Session session) { this.session = session; }

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
        return List.of();
    }

    @Override
    public Result exec(Dictionary<Parameter, List<Object>> parameter) {
        this.session.quit();
        return new Result(true);
    }
}
