package edu.kit.informatik.ui.commands;

import edu.kit.informatik.ui.commands.parameter.Parameter;

import java.util.Dictionary;
import java.util.List;

import edu.kit.informatik.ui.session.Result;

/**
 * Description of a command
 * A command must have a regex-pattern and (optional) parameters and an execution-method
 * @author uppyo
 * @version 1.0
 */
public abstract class Command {

    /**
     * get the regex-pattern of the command
     * @return a regex-pattern
     */
    public abstract String getPattern();

    /**
     * get a list of parameters
     * @return parameters in correct order, is empty if the command has no parameters
     */
    public abstract List<Parameter> getParams();

    /**
     * Execute the command and return the result of the execution
     * @param parameters a dictionary of parsed parameters
     * @return result of execution
     */
    public abstract Result exec(Dictionary<Parameter, List<Object>> parameters);

}
