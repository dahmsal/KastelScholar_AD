package edu.kit.informatik.ui.commands.parameter;

/**
 * A parameter of type author always has a first and a last name as Strings
 * @author uppyo
 * @version 1.0
 */
public class AuthorParam extends Parameter {
    private static final String REGEX_AUTHOR = "[a-zA-Z]+ [a-zA-Z]+";

    @Override
    public String getPattern() {
        return REGEX_AUTHOR;
    }


}
