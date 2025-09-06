package org.example.matrixcalculatorjfx

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.GridPane

class GaussController {

    @FXML
    lateinit var rowsField: TextField

    @FXML
    lateinit var colsField: TextField

    @FXML
    lateinit var grid: GridPane

    @FXML
    lateinit var resultLabel: Label

    // Armazena todos os campos da matriz (coeficientes + b)
    private var matrixFields: MutableList<MutableList<TextField>> = mutableListOf()

    @FXML
    fun onGenerate() {
        val m = rowsField.text.toIntOrNull() ?: 2
        val n = colsField.text.toIntOrNull() ?: 2

        grid.children.clear()
        matrixFields.clear()

        // Cria os TextFields dinamicamente
        for (i in 0 until m) {
            val rowFields = mutableListOf<TextField>()
            for (j in 0 until n) {
                val tf = TextField()
                tf.promptText = "a${i + 1}${j + 1}"
                tf.maxWidth = 60.0
                grid.add(tf, j, i)
                rowFields.add(tf)
            }
            // Coluna do vetor b
            val bField = TextField()
            bField.promptText = "b${i + 1}"
            bField.maxWidth = 60.0
            grid.add(bField, n, i)
            rowFields.add(bField)

            matrixFields.add(rowFields)
        }

        resultLabel.text = "Resultado:"
    }

    @FXML
    fun onSolve() {
        try {
            val m = matrixFields.size
            val n = matrixFields[0].size

            // Cria matriz aumentada (coeficientes + b)
            val augmentedMatrix = matrixFields.map { row ->
                row.map { it.text.toDoubleOrNull() ?: 0.0 }.toMutableList()
            }.toMutableList()

            val solution = GaussSolver.eliminate(augmentedMatrix)

            resultLabel.text = "Resultado: " +
                    solution.mapIndexed { idx, v -> "x${idx + 1}=${"%.2f".format(v)}" }
                        .joinToString(", ")

        } catch (e: Exception) {
            resultLabel.text = "Erro nos dados"
        }
    }
}
