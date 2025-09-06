package org.example.matrixcalculatorjfx

import javafx.fxml.FXML
import javafx.scene.control.TextArea
import javafx.scene.control.TextField
import net.objecthunter.exp4j.ExpressionBuilder

class BisectionController {

    @FXML
    lateinit var functionField: TextField

    @FXML
    lateinit var aField: TextField

    @FXML
    lateinit var bField: TextField

    @FXML
    lateinit var tolField: TextField

    @FXML
    lateinit var resultArea: TextArea

    @FXML
    fun onCalculate() {
        try {
            val fStr = functionField.text
            val a = aField.text.toDouble()
            val b = bField.text.toDouble()
            val tol = tolField.text.toDoubleOrNull() ?: 1E-6

            if (a >= b) {
                resultArea.text = "Erro: a deve ser menor que b."
                return
            }

            val solution = bisection(fStr, a, b, tol)
            resultArea.text = solution.joinToString("\n")
        } catch (e: Exception) {
            resultArea.text = "Erro: ${e.message}"
        }
    }

    private fun bisection(fStr: String, a: Double, b: Double, tol: Double): List<String> {
        val expr = ExpressionBuilder(fStr).variable("x").build()
        fun f(x: Double) = expr.setVariable("x", x).evaluate()

        val steps = mutableListOf<String>()

        if (f(a) * f(b) >= 0) {
            steps.add("O Teorema de Bolzano não garante raiz nesse intervalo.")
            return steps
        }

        var left = a
        var right = b
        var c = left
        var iteration = 1

        while ((right - left) / 2 > tol) {
            c = (left + right) / 2
            steps.add("Iteração $iteration: c=$c, f(c)=${f(c)}")
            if (f(c) == 0.0) break
            if (f(c) * f(left) < 0) right = c else left = c
            iteration++
        }

        steps.add("Raiz aproximada: $c")
        return steps
    }
}
