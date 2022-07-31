package edu.kit.informatik.ui.session;

import edu.kit.informatik.data.Database;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.AddAuthor;
import edu.kit.informatik.ui.commands.input.AddJournal;
import edu.kit.informatik.ui.commands.input.Quit;
import edu.kit.informatik.ui.commands.parameter.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Session {
    private boolean running;
    private List<Command> commandList = new ArrayList<Command>()

    {
    };

    public void quit() {
        this.running = false;
    }

    public Session () {
        this.running = true;
        this.commandList.add(new Quit(this));
        this.commandList.add(new AddAuthor(this, new Database()));
        this.commandList.add(new AddJournal(this, new Database()));
    }

    public void runSession() {
        do {
            Scanner scanner = new Scanner(System.in);
            try {
                Result result = processCommand(scanner.nextLine());
                if (result.getResultMessage() != null) {
                    System.out.println(result.getResultMessage());
                }
            } catch (InputException exception) {
                System.out.println("InputException:" + exception.getMessage());
            }
        }
        while (this.running);
    }

    private Result processCommand(String input) throws InputException {
        for (Command command: this.commandList) {
            Pattern pattern = Pattern.compile(command.getPattern());
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                String paramInput = matcher.replaceFirst("");
                return command.exec(parseParameters(paramInput.trim(), command));
            }
        }
        throw new InputException("not a valid command");
    }

    private  List<String> parseParameters(String paramInput, Command command) throws InputException {
        Scanner scanner = new Scanner(paramInput);
        scanner.useDelimiter(",");
        List<String> parameters = new ArrayList<String>();
        for (Parameter param: command.getParams()) {
            if (!scanner.hasNext()) {
                throw new InputException("invalid number of args");
            }
            String nextParam = scanner.next();
            Pattern pattern = Pattern.compile(param.getPattern());
            Matcher matcher = pattern.matcher(nextParam);
            if (param.isAsList()) {
                if (matcher.find()) {
                    do {
                        parameters.add(matcher.group());
                    }
                    while (matcher.find());
                }
                else {
                    throw new InputException("invalid number of args");
                }
            }
            else if (matcher.matches())
                parameters.add(nextParam.trim());
            else {
                throw new InputException("invalid arg");
            }
        }
        if (scanner.hasNext()) {
            throw new InputException("invalid number of args");
        }
        return parameters;
    }

}
