package it.unibo.pps17.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

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

    @FXML
    public Canvas canvas;

    public static String MAIN_LAYOUT = "mainStage.fxml";

    private GraphicsContext graphicsContext;

    private int drawingOffset = 0;


    /**
     * Initializes the layout.
     */
    @FXML
    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);
    }


    public void computeOffset(int boardXDimension, int yboardDimension) {
        this.drawingOffset = Integer.max((int) canvas.getWidth() / boardXDimension,
                (int) canvas.getHeight() / yboardDimension);
    }

    private void displayCellOnCanvas(int posX, int posY) {
        graphicsContext.fillRect(posX * drawingOffset, posY * drawingOffset, drawingOffset, drawingOffset);
    }

}
