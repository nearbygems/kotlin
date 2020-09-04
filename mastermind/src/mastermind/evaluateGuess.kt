package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {

  val rightPosition = secret.zip(guess).count { it.first == it.second }

  val commonLetters = "ABCDEF".sumBy { ch -> secret.count { it == ch }.coerceAtMost(guess.count { it == ch }) }

  return Evaluation(rightPosition, commonLetters - rightPosition)
}