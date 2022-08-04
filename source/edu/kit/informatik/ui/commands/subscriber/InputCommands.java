package edu.kit.informatik.ui.commands.subscriber;

import edu.kit.informatik.data.Database;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.input.*;
import edu.kit.informatik.ui.session.Session;
import edu.kit.informatik.util.DataType;

import java.util.ArrayList;
import java.util.List;

public class InputCommands {
    private Session session;
    private Database database;

    public InputCommands(Session session, Database database) {
        this.session = session;
        this.database = database;
    }

    public List<Command> subscribeToAll() {
        ArrayList<Command> commandList = new ArrayList<>();

        commandList.add(new AddArticleTo(this.session, this.database));
        commandList.add(new AddAuthor(this.session, this.database));
        commandList.add(new AddConference(this.session, this.database));
        commandList.add(new AddJournal(this.session, this.database));
        commandList.add(new AddKeywordsTo(this.session, this.database));
        commandList.add(new AddSeries(this.session, this.database));
        commandList.add(new Cites(this.session, this.database));
        commandList.add(new WrittenBy(this.session, this.database));
        commandList.add(new Quit(this.session));

        return commandList;
    }
}
