package edu.kit.informatik.ui.commands.subscriber;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.session.Session;

import java.util.List;

/**
 * Description of the interface between the subscribers and the session.
 * @author uppyo
 * @version 1.0
 */
public interface Subscriber {
    /**
     * Subscribe all commands to the session
     * @return the list of commands which have been added
     */
    List<Command> subscribeAll(Session session, DatabaseProvider databaseProvider);
}
