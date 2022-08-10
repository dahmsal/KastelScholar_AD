package edu.kit.informatik.ui.commands.subscriber;

import edu.kit.informatik.data.DatabaseProvider;
import edu.kit.informatik.ui.commands.Command;
import edu.kit.informatik.ui.commands.query.*;


import java.util.ArrayList;
import java.util.List;

public class QueryCommands implements Subscriber {

    private final DatabaseProvider databaseProvider;

    public QueryCommands(DatabaseProvider databaseProvider) {
        this.databaseProvider = databaseProvider;
    }

    @Override
    public List<Command> subscribeToAll() {
        ArrayList<Command> commandList = new ArrayList<>();

        commandList.add(new AllPublications(this.databaseProvider));
        commandList.add(new CoAuthorsOf(this.databaseProvider));
        commandList.add(new FindByKeywords(this.databaseProvider));
        commandList.add(new ForeignCitationsOf(this.databaseProvider));
        commandList.add(new GIndex(this.databaseProvider));
        commandList.add(new InProceedings(this.databaseProvider));
        commandList.add(new Jaccard(this.databaseProvider));
        commandList.add(new ListInvalidPublications(this.databaseProvider));
        commandList.add(new PrintBibliography(this.databaseProvider));
        commandList.add(new PublicationsBy(this.databaseProvider));
        commandList.add(new Similarity(this.databaseProvider));

        return commandList;
    }

}
