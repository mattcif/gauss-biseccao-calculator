package org.example.matrixcalculatorjfx

object GaussSolver {

    fun eliminate(matrix: MutableList<MutableList<Double>>): List<Double> {
        val m = matrix.size
        val n = matrix[0].size

        for (i in 0 until m) {
            // Pivô máximo
            var maxRow = i
            for (k in i + 1 until m) {
                if (Math.abs(matrix[k][i]) > Math.abs(matrix[maxRow][i])) {
                    maxRow = k
                }
            }

            // Troca de linhas
            val temp = matrix[i]
            matrix[i] = matrix[maxRow]
            matrix[maxRow] = temp

            // Normaliza linha do pivô
            val divisor = matrix[i][i]
            for (j in i until n) {
                matrix[i][j] /= divisor
            }

            // Zerando coluna i nas outras linhas
            for (k in 0 until m) {
                if (k != i) {
                    val factor = matrix[k][i]
                    for (j in i until n) {
                        matrix[k][j] -= factor * matrix[i][j]
                    }
                }
            }
        }

        // Resultado = última coluna
        return matrix.map { it.last() }
    }
}
