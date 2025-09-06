package org.example.matrixcalculatorjfx

import javafx.fxml.FXML
import javafx.scene.control.*
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

    // Lista de listas de TextField que representa a matriz aumentada
    private var matrixFields: MutableList<MutableList<TextField>> = mutableListOf()

    @FXML
    fun onGenerate() {
        val m = rowsField.text.toIntOrNull() ?: 3
        val n = colsField.text.toIntOrNull() ?: 3

        grid.children.clear()
        matrixFields.clear()

        // Cria a matriz aumentada dinamicamente
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
            val tfB = TextField()
            tfB.promptText = "b${i + 1}"
            tfB.maxWidth = 60.0
            grid.add(tfB, n, i)
            rowFields.add(tfB)

            matrixFields.add(rowFields)
        }

        resultLabel.text = "Resultado:"
    }

    @FXML
    fun onSolve() {
        try {
            if (matrixFields.isEmpty()) {
                resultLabel.text = "Primeiro gere a matriz!"
                return
            }

            val m = matrixFields.size
            val n = matrixFields[0].size - 1  // número de colunas sem o vetor b

            val matrix = matrixFields.map { row ->
                row.map { it.text.toDoubleOrNull() ?: 0.0 }.toMutableList()
            }.toMutableList()

            if (m != n) {
                resultLabel.text = "Atenção: sistema não quadrado, resultado não garantido!"
            }

            val solution = GaussSolver.eliminate(matrix)

            resultLabel.text = "Resultado: " +
                    solution.mapIndexed { idx, v -> "x${idx + 1}=${"%.2f".format(v)}" }
                        .joinToString(", ")

        } catch (e: Exception) {
            resultLabel.text = "Erro nos dados"
        }
    }
}
