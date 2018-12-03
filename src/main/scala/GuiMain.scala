import it.unibo.pps17.controller.GuiController
import it.unibo.pps17.view.MainStageController
import javafx.application.{Application, Platform}
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class GuiMain extends Application {

  private val TITLE = "Game of life"

  override def start(primaryStage: Stage): Unit = {
    val loader = new FXMLLoader(classOf[MainStageController].getResource(MainStageController.MAIN_LAYOUT))
    val root: Parent = loader.load()
    primaryStage.setTitle(TITLE)
    primaryStage.setResizable(false)
    primaryStage.setScene(new Scene(root))
    primaryStage.setOnCloseRequest(_ => {
      Platform.exit()
      System.exit(0)
    })
    GuiController().setViewController(loader.getController())
    primaryStage.show()
  }

}

object GuiMain extends App {
  Application.launch(classOf[GuiMain], args: _*)
}
