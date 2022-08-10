package edu.kit.informatik.ui.session;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.subscriber.Subscriber;
import edu.kit.informatik.ui.parser.CommandParser;
import edu.kit.informatik.ui.parser.ParameterParser;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.util.ObjectPair;
import edu.kit.informatik.util.exception.InputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Environment for an instance of the KasteSholar project. Handles user in- and outputs.
 * @author uppyo
 * @version 1.0
 */
public class Session {
    private static final String ERROR = "Error, ";
    private static final String INPUT_EXCEPTION = "InputException:";

    private boolean running;
    private final List<Command> commandList = new ArrayList<>();

    /**
     * Initialisation of databases and commands via subscriber
     * @param subscribers command-subscribers to subscribe commands to session
     */
    public Session(List<Subscriber> subscribers) {
        this.running = true;
        DatabaseProvider databaseProvider = new DatabaseProvider();
        for (Subscriber subscriber: subscribers) {
            this.commandList.addAll(subscriber.subscribeAll(this, databaseProvider));
        }
    }

    /**
     * Quit-command to end session
     */
    public void quit() {
        this.running = false;
    }

    /**
     * Run the session and parse user-input until quit is called
     */
    public void runSession() {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                Result result = processCommand(scanner.nextLine());
                if (result.getResultMessage() != null) {
                    if (result.isSuccess()) {
                        System.out.println(result.getResultMessage());
                    } else {
                        System.out.println(ERROR + result.getResultMessage());
                    }
                }
            } catch (InputException exception) {
                System.out.println(INPUT_EXCEPTION + exception.getMessage());
            }
        }
        while (this.running);
    }

    private Result processCommand(String input) throws InputException {
        ObjectPair<Command, String> parseResult =  CommandParser.parseCommand(input, this.commandList);
        Command currentCommand = parseResult.getFirst();
        String paramString = parseResult.getSecond();
        return currentCommand.exec(ParameterParser.parseArguments(currentCommand.getParams(), paramString));
    }

}
