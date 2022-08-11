package edu.kit.informatik.ui.parser;

import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.ObjectPair;
import edu.kit.informatik.util.exception.messages.ParserExceptionMessage;
import edu.kit.informatik.util.strings.UtilStrings;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse commands against their patterns
 * @author uppyo
 * @version 1.0
 */
public final class CommandParser {

    private CommandParser() { }

    /**
     * Match input-command against all command-patterns
     * @param inputString User-Input string
     * @param commandList list of all commands subscribed to the session
     * @return Tuple of Command an user-input parameters as ObjectPair
     * @throws InputException if the command could not be parsed
     */
    public static ObjectPair<Command, String> parseCommand(String inputString, List<Command> commandList)
            throws InputException {
        for (Command command: commandList) {
            Pattern pattern = Pattern.compile(command.getPattern());
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.find()) {
                String paramInput = matcher.replaceFirst(UtilStrings.getEmptyString());
                return new ObjectPair<>(command, paramInput.trim());
            }
        }
        throw new InputException(ParserExceptionMessage.getNoCommand());
    }
}
