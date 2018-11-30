package it.unibo.pps17.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainStageController {

    @FXML
    public Button startButton;

    @FXML
    public Button resetButton;

    @FXML
    public Button nextButton;

    @FXML
    public Label generation;

    @FXML
    public Label aliveCells;

    public static String MAIN_LAYOUT = "mainStage.fxml";


    /**
     * Initializes the layout.
     */
    @FXML
    public void initialize() {
        startButton.setOnAction(event -> {
            generation.setText("Label updated");
        });
    }

}
