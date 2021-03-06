package seedu.address.logic.commands;

import seedu.address.commons.core.EventsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.commons.events.BaseEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.storage.Storage;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {
    protected Model model;
    protected Storage storage;
    protected CommandHistory history;
    protected UndoRedoStack undoRedoStack;
    protected EventsCenter eventsCenter = EventsCenter.getInstance();

    private String commandText;

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param displaySize used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(int displaySize) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, displaySize);
    }

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public abstract CommandResult execute() throws CommandException;

    /**
     * Provides any needed dependencies to the command.
     * Commands making use of any of these should override this method to gain
     * access to the dependencies.
     */
    public void setData(Model model, CommandHistory history, UndoRedoStack undoRedoStack) {
        this.model = model;
    }

    //@@author low5545
    /**
     * Provides {@code Storage} dependency to the command.
     * Commands making use of {@code Storage} should override this method to gain access.
     */
    public void setStorage(Storage storage) {
        // intentionally left empty
    }

    //=========== Support for getter/setter of commandBox text ========================================================

    final String getCommandText() {
        return commandText;
    }

    /**
     * Store the raw user input {@code commandText} in the command for future reference
     */
    public final void setCommandText(String commandText) {
        this.commandText = commandText;
    }
    //@@author

    protected void raise(BaseEvent event) {
        eventsCenter.post(event);
    }
}
