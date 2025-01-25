package boblet;
import java.io.IOException;

import boblet.command.Command;
import boblet.exception.BobletException;
import boblet.util.Parser;
import boblet.util.Storage;
import boblet.util.TaskList;
import boblet.util.Ui;

public class Boblet {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Boblet(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (IOException | BobletException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BobletException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Boblet("data/tasks.txt").run();
    }
}
