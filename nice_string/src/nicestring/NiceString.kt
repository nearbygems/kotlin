package nicestring

fun String.isNice(): Boolean = (this.isBu() + this.containsThreeVowels() + this.hasDouble()) > 1

fun String.isBu(): Int = when {
  contains("bu") -> 0
  contains("ba") -> 0
  contains("be") -> 0
  else -> 1
}

fun String.containsThreeVowels() = if (this.countVowels() > 2) 1 else 0

fun String.countVowels(): Int = this.count() { it in arrayOf('a', 'e', 'i', 'o', 'u') }

fun String.hasDouble(): Int {
  for (i in 1 until this.length) {
    if (this[i] == this[i - 1]) return 1
  }
  return 0;
}