package edu.kit.informatik.ui.session;

import edu.kit.informatik.data.Database;
import edu.kit.informatik.ui.Parser.CommandParser;
import edu.kit.informatik.ui.Parser.ParameterParser;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.AddAuthor;
import edu.kit.informatik.ui.commands.input.AddJournal;
import edu.kit.informatik.ui.commands.input.AddKeywordsTo;
import edu.kit.informatik.ui.commands.input.Quit;
import edu.kit.informatik.util.ObjectPair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        this.commandList.add(new AddKeywordsTo(this, new Database()));
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
        ObjectPair<Command, String> parseResult =  CommandParser.parseCommand(input, this.commandList);
        Command currentCommand = parseResult.getFirst();
        String paramString = parseResult.getSecond();
        return currentCommand.exec(ParameterParser.parseArguments(currentCommand.getParams(), paramString));
    }

}
