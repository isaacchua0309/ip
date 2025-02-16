package boblet.ui;
import boblet.Boblet;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    @FXML
    private Button helpButton;


    private Boblet boblet;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBoblet(Boblet d) {
        boblet = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = boblet.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    @FXML
    private void showHelp() {
        String helpMessage = """
                Welcome to Boblet - Your Personal Task Assistant!

                Here are the available commands:
                1. list - Show all tasks
                2. todo <description> - Add a new Todo task
                3. deadline <description> /by <date/time> - Add a Deadline task
                4. event <description> /at <date/time> - Add an Event task
                5. delete <task number> - Delete a task
                6. done <task number> - Mark a task as done
                7. find <keyword> - Find tasks containing the keyword
                8. showdate <yyyy-MM-dd> - Show tasks on a specific date
                9. bye - Exit the application

                Dates and times can be in various formats, e.g.:
                - yyyy-MM-dd HH:mm  -> 2025-02-01 14:00
                - d/M/yyyy HHmm     -> 1/2/2025 1400
                - MMM dd yyyy, hh:mm a -> Feb 01 2025, 02:00 PM

                Have fun using Boblet! 😊
                """;

        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(
            javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Boblet Commands");
        alert.setHeaderText("Available Commands");
        alert.setContentText(helpMessage);
        alert.showAndWait();
    }

    @FXML
    private void toggleTheme() {
        String lightTheme = "-fx-background-color: linear-gradient(to bottom, #ffffff, #e0f7fa);";
        String darkTheme = "-fx-background-color: linear-gradient(to bottom, #263238, #37474f);";

        if (dialogContainer.getScene().getRoot().getStyle().equals(lightTheme)) {
            dialogContainer.getScene().getRoot().setStyle(darkTheme);
            helpButton.setStyle("-fx-background-color: #607d8b; -fx-text-fill: white; -fx-border-radius: 15;");
            sendButton.setStyle("-fx-background-color: #546e7a; -fx-text-fill: white; -fx-border-radius: 20;");
            userInput.setStyle(
                "-fx-background-color:#90a4ae;-fx-border-color:#455a64; -fx-border-radius:20;-fx-text-fill: #ffffff;");
        } else {
            dialogContainer.getScene().getRoot().setStyle(lightTheme);
            helpButton.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-border-radius: 15;");
            sendButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #0288d1, #01579b);"
                + "-fx-text-fill: white;"
                + "-fx-border-radius: 20;"
            );

            userInput.setStyle(
                "-fx-background-color:#e3f2fd;-fx-border-color:#0288d1;-fx-border-radius:20;-fx-text-fill:#004d40;");
        }
    }

}
