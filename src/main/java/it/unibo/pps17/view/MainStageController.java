package it.unibo.pps17.view;

import it.unibo.pps17.controller.GuiController;
import it.unibo.pps17.core.prolog.wrapper.Position;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.util.List;

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
    private static String RESET_MESSAGE = "";
    private static String START_MESSAGE = "0";
    private static int BORDER_DEPTH = 1;

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
        this.updateBothLabels(START_MESSAGE);
        controller.startGame();
    }

    public void next() {
        controller.computeNextGeneration();
    }

    public void reset() {
        this.startButton.setDisable(false);
        this.nextButton.setDisable(true);
        this.resetButton.setDisable(true);
        controller.resetGameEngine();
        this.resetCanvas();
        this.updateBothLabels(RESET_MESSAGE);

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

    public void displayAliveCells(final List<Position> aliveCells) {
        this.resetCanvas();
        aliveCells.forEach(cell -> displayCellOnCanvas(cell.x(), cell.y()));
    }


    private void updateLabel(final Label l, final String textToSet) {
        l.setText(textToSet);
    }


    private void computeOffset(int boardXDimension, int boardYDimension) {
        this.drawingOffset = Integer.max((int) canvas.getWidth() / boardXDimension,
                (int) canvas.getHeight() / boardYDimension);
    }

    private void displayCellOnCanvas(int posX, int posY) {
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.fillRect(posX * drawingOffset, posY * drawingOffset, drawingOffset, drawingOffset);
        paintCellBoarder(posX, posY);
    }

    private void resetCanvas() {
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    private void paintCellBoarder(int posX, int posY) {
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(posX * drawingOffset, posY * drawingOffset, drawingOffset, BORDER_DEPTH);
        graphicsContext.fillRect(posX * drawingOffset, (posY + 1) * drawingOffset - BORDER_DEPTH, drawingOffset, BORDER_DEPTH);
        graphicsContext.fillRect(posX * drawingOffset, posY * drawingOffset, BORDER_DEPTH, drawingOffset);
        graphicsContext.fillRect((posX + 1) * drawingOffset - BORDER_DEPTH, posY * drawingOffset, BORDER_DEPTH, drawingOffset);

    }

    private void updateBothLabels(final String message) {
        this.updateLabel(this.aliveCells, message);
        this.updateLabel(this.generation, message);
    }

}
