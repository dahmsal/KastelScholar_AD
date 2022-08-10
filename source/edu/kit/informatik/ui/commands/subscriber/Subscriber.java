package edu.kit.informatik.ui.commands.subscriber;

import edu.kit.informatik.ui.commands.Command;

import java.util.List;

public interface Subscriber {

    List<Command> subscribeToAll();
}
