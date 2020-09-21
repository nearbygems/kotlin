package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    val visited = mutableSetOf<Int>()
    val normalizedPermutation = permutation.map {
        it - (permutation.min()!!)
    }

    fun nextCycleLength(startIndex: Int): Int {
        var currentIndex = startIndex
        var currentCycleLength = 0

        while (currentIndex !in visited) {
            visited += currentIndex
            currentCycleLength += 1
            currentIndex = normalizedPermutation[currentIndex]
        }

        return currentCycleLength
    }

    return permutation.indices
        .map(::nextCycleLength)
        .filter { it > 1 }
        .map { (it - 1) % 2 }
        .sum() % 2 == 0
}