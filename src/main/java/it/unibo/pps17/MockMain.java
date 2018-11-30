package it.unibo.pps17;

import it.unibo.pps17.view.MainStageController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MockMain extends Application {

    private static String TITLE = "Game of life";

    @Override
    public void start(Stage primaryStage) throws Exception {
        final FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainStageController.class.getResource(MainStageController.MAIN_LAYOUT));
        final Parent root = loader.load();
        primaryStage.setTitle(TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}
