package edu.kit.informatik.ui.commands.subscriber;


import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.*;
import edu.kit.informatik.ui.session.Session;

import java.util.ArrayList;
import java.util.List;

public class InputCommands {
    private Session session;
    private DatabaseProvider databaseProvider;
    public InputCommands(Session session,DatabaseProvider databaseProvider) {
        this.session = session;
        this.databaseProvider = databaseProvider;
    }

    public List<Command> subscribeToAll() {
        ArrayList<Command> commandList = new ArrayList<>();

        commandList.add(new AddArticleTo(this.session, this.databaseProvider));
        commandList.add(new AddAuthor(this.session, this.databaseProvider));
        commandList.add(new AddConference(this.session, this.databaseProvider));
        commandList.add(new AddJournal(this.session, this.databaseProvider));
        commandList.add(new AddKeywordsTo(this.session, this.databaseProvider));
        commandList.add(new AddSeries(this.session, this.databaseProvider));
        commandList.add(new Cites(this.session, this.databaseProvider));
        commandList.add(new WrittenBy(this.session, this.databaseProvider));
        commandList.add(new Quit(this.session));

        return commandList;
    }
}
