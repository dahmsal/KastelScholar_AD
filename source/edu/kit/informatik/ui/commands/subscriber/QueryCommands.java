package edu.kit.informatik.ui.commands.subscriber;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.query.*;
import edu.kit.informatik.ui.session.Session;


import java.util.ArrayList;
import java.util.List;

/**
 * Subscribe all query-commands to the session
 * @author uppyo
 * @version 1.0
 */
public class QueryCommands implements Subscriber {

    @Override
    public List<Command> subscribeAll(Session session, DatabaseProvider databaseProvider) {
        ArrayList<Command> commandList = new ArrayList<>();

        commandList.add(new AllPublications(databaseProvider));
        commandList.add(new CoAuthorsOf(databaseProvider));
        commandList.add(new FindByKeywords(databaseProvider));
        commandList.add(new ForeignCitationsOf(databaseProvider));
        commandList.add(new GIndex(databaseProvider));
        commandList.add(new InProceedings(databaseProvider));
        commandList.add(new Jaccard());
        commandList.add(new ListInvalidPublications(databaseProvider));
        commandList.add(new PrintBibliography(databaseProvider));
        commandList.add(new PublicationsBy(databaseProvider));
        commandList.add(new Similarity(databaseProvider));

        return commandList;
    }

}
