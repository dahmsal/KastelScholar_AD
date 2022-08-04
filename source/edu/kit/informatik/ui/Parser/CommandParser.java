package edu.kit.informatik.ui.Parser;

import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.util.exception.InputException;
import edu.kit.informatik.util.ObjectPair;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    public static ObjectPair<Command, String> parseCommand(String inputString, List<Command> commandList) throws InputException {
        for (Command command: commandList) {
            Pattern pattern = Pattern.compile(command.getPattern());
            Matcher matcher = pattern.matcher(inputString);
            if (matcher.find()) {
                String paramInput = matcher.replaceFirst("");
                return new ObjectPair<Command, String>(command, paramInput.trim());
            }
        }
        throw new InputException("not a valid command");
    }
}
