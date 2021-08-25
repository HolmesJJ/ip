package duke;

import duke.listener.Message;
import duke.storage.Storage;
import duke.task.Operation;
import duke.ui.Ui;

/**
 * The project aims to build a product named Duke,
 * a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke implements Message {
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(this);
    }

    /**
     * Runs Duke program.
     */
    public void run() {
        storage.loadTasks();
        ui.logo();
        ui.greet();
        String command = ui.readCommand();
        Operation operation = storage.getOperation(command);
        while (true) {
            if (operation == Operation.BYE) {
                ui.bye();
                break;
            } else if (operation == Operation.LIST) {
                storage.listTasks();
            } else if (operation == Operation.DONE) {
                storage.completeTask(command);
            } else if (operation == Operation.DELETE) {
                storage.deleteTask(command);
            } else if (operation == Operation.CLEAR) {
                storage.clearTasks();
            } else if (operation == Operation.FIND) {
                storage.findTasks(command);
            } else if (operation == Operation.TODO
                || operation == Operation.DEADLINE
                || operation == Operation.EVENT) {
                storage.addTask(command);
            }
            storage.saveTasksToFile();
            command = ui.readCommand();
            operation = storage.getOperation(command);
        }
    }

    /**
     * Runs entry point for the Duke program.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Displays messages.
     */
    @Override
    public void show(String... messages) {
        ui.show(messages);
    }
}
