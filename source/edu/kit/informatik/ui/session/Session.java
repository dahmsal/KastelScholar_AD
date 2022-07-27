package edu.kit.informatik.ui.session;

import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.Quit;

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
                System.out.println("InputException");
            }
        }
        while (this.running);
    }

    private Result processCommand(String input) throws InputException {
        for (Command command: this.commandList) {
            Pattern pattern = Pattern.compile(command.getPattern());
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                return command.exec(matcher);
            }
        }
        throw new InputException("not a valid command");
    }

}
