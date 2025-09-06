package org.example.matrixcalculatorjfx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainApp : Application() {
    override fun start(primaryStage: Stage) {
        val root = FXMLLoader.load<javafx.scene.layout.Pane>(
            javaClass.getResource("/org/example/matrixcalculatorjfx/home.fxml")
        )
        primaryStage.scene = Scene(root)
        primaryStage.title = "Calculadora de Métodos Numéricos"
        primaryStage.show()
    }
}

fun main() {
    Application.launch(MainApp::class.java)
}
