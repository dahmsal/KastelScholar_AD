package edu.kit.informatik.ui.commands.subscriber;


import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.*;
import edu.kit.informatik.ui.session.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Subscribe all input-commands to the session
 * @author uppyo
 * @version 1.0
 */
public class InputCommands implements Subscriber {
    /**
     * Get the relevant objects from the session
     * @param session the session itself, required for the quit command
     * @param databaseProvider the databases of the session
     */

    @Override
    public  List<Command> subscribeAll(Session session, DatabaseProvider databaseProvider) {
        ArrayList<Command> commandList = new ArrayList<>();

        commandList.add(new AddArticleTo(databaseProvider));
        commandList.add(new AddAuthor(databaseProvider));
        commandList.add(new AddConference(databaseProvider));
        commandList.add(new AddJournal(databaseProvider));
        commandList.add(new AddKeywordsTo(databaseProvider));
        commandList.add(new AddSeries(databaseProvider));
        commandList.add(new Cites(databaseProvider));
        commandList.add(new WrittenBy(databaseProvider));
        commandList.add(new Quit(session));

        return commandList;
    }
}
