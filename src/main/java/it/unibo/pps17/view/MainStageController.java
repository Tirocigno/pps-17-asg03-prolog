package it.unibo.pps17.view;

import it.unibo.pps17.controller.GuiController;
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

    private GuiController controller;


    /**
     * Initializes the layout.
     */
    @FXML
    public void initialize() {
        graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.BLUE);
    }

    /**
     * Set the controller inside the object.
     *
     * @param guiController a reference to a controller object.
     */
    public void setGuiController(final GuiController guiController) {
        this.controller = guiController;
    }

    public void start() {
        this.startButton.setDisable(true);
        this.nextButton.setDisable(false);
        this.resetButton.setDisable(false);
    }

    public void next() {
        controller.computeNextGeneration();
    }

    public void reset() {
        this.startButton.setDisable(false);
        this.nextButton.setDisable(true);
        this.resetButton.setDisable(true);
        controller.resetGameEngine();
    }

    public void updateGeneration(final String generationCount) {
        this.updateLabel(this.generation, generationCount);
    }

    public void updateAliveCellsLabel(final String aliveCellsCount) {
        this.updateLabel(this.aliveCells, aliveCellsCount);
    }

    public void initializeBoard(final int boardXDimension, final int boardYDimension) {
        this.computeOffset(boardXDimension, boardYDimension);
    }


    private void updateLabel(final Label l, final String textToSet) {
        l.setText(textToSet);
    }


    private void computeOffset(int boardXDimension, int yboardDimension) {
        this.drawingOffset = Integer.max((int) canvas.getWidth() / boardXDimension,
                (int) canvas.getHeight() / yboardDimension);
    }

    private void displayCellOnCanvas(int posX, int posY) {
        graphicsContext.fillRect(posX * drawingOffset, posY * drawingOffset, drawingOffset, drawingOffset);
    }

}
