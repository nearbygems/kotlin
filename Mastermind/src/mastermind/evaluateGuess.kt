package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
  val list = secret.toMutableList();
  var rightPosition = 0;
  var wrongPosition = 0;

  secret.indices.forEach {
    if (secret[it] == guess[it]) {
      rightPosition++
      list.remove(secret[it])
    }
  }

  secret.indices.forEach {
    if (secret[it] != guess[it] && guess[it] in list) {
      wrongPosition++
      list.remove(guess[it])
    }
  }

  return Evaluation(rightPosition, wrongPosition)
}