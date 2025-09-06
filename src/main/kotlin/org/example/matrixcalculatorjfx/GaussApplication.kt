package org.example.matrixcalculatorjfx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class GaussApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(GaussApplication::class.java.getResource("gauss_layout.fxml"))
        val scene = Scene(fxmlLoader.load(), 800.0, 600.0)
        stage.title = "Calculadora Gauss-Jordan"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(GaussApplication::class.java)
}
