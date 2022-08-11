package edu.kit.informatik;

import edu.kit.informatik.ui.commands.subscriber.InputCommands;
import edu.kit.informatik.ui.commands.subscriber.QueryCommands;
import edu.kit.informatik.ui.session.Session;

import java.util.List;

/**
 * KastelScholar is a literature management program. This was created as part of the final programming assigment for
 * the lecture "Programmieren" at KIT.
 * @author uppyo
 * @version 0.99
 */
public final class Main {

    private Main() { }

    /**
     * No inline args are used. Initialise Commands and Session and subscribe commands.
     * @param args none
     */
    public static void main(String[] args) {
        InputCommands inputCommands = new InputCommands();
        QueryCommands queryCommands = new QueryCommands();
        Session session = new Session(List.of(inputCommands, queryCommands));
        session.runSession();
    }
}
