package org.example.matrixcalculatorjfx

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import javafx.scene.control.Button
import javafx.scene.layout.Pane

class HomeController {

    @FXML
    fun goToGauss() {
        loadScene("/org/example/matrixcalculatorjfx/gauss_layout.fxml", "Eliminação de Gauss")
    }

    @FXML
    fun goToBisection() {
        loadScene("/org/example/matrixcalculatorjfx/bisection_layout.fxml", "Método da Bissecção")
    }

    private fun loadScene(fxmlPath: String, title: String) {
        val stage = Stage()
        val pane: Pane = FXMLLoader.load(javaClass.getResource(fxmlPath))
        stage.scene = Scene(pane)
        stage.title = title
        stage.show()
    }
}
