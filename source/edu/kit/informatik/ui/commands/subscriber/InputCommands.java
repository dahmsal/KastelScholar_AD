package edu.kit.informatik.ui.commands.subscriber;


import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.*;
import edu.kit.informatik.ui.session.Session;

import java.util.ArrayList;
import java.util.List;

public class InputCommands implements Subscriber {
    private final Session session;
    private final DatabaseProvider databaseProvider;

    public InputCommands(Session session, DatabaseProvider databaseProvider) {
        this.session = session;
        this.databaseProvider = databaseProvider;
    }

    @Override
    public List<Command> subscribeToAll() {
        ArrayList<Command> commandList = new ArrayList<>();

        commandList.add(new AddArticleTo(this.databaseProvider));
        commandList.add(new AddAuthor(this.databaseProvider));
        commandList.add(new AddConference(this.databaseProvider));
        commandList.add(new AddJournal(this.databaseProvider));
        commandList.add(new AddKeywordsTo(this.databaseProvider));
        commandList.add(new AddSeries(this.databaseProvider));
        commandList.add(new Cites(this.databaseProvider));
        commandList.add(new WrittenBy(this.databaseProvider));
        commandList.add(new Quit(this.session));

        return commandList;
    }
}
