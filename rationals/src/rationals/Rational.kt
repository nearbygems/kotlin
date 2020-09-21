package rationals

import java.math.BigInteger
import java.math.BigInteger.ONE
import java.math.BigInteger.ZERO

class Rational(n: BigInteger, d: BigInteger) : Comparable<Rational> {
    private val n: BigInteger
    private val d: BigInteger

    init {
        require(d != ZERO) { "denominator is `0`" }

        val sgn = d.signum().toBigInteger()
        val gcd = gcd(n.abs(), d.abs())

        this.n = sgn * n / gcd
        this.d = sgn * d / gcd
    }

    operator fun plus(that: Rational) = Rational(
        (this.n * that.d) + (that.n * this.d),
        this.d * that.d
    )

    operator fun minus(that: Rational) = Rational(
        (this.n * that.d) - (that.n * this.d),
        this.d * that.d
    )

    operator fun times(that: Rational) = Rational(
        this.n * that.n,
        this.d * that.d
    )

    operator fun div(that: Rational) = Rational(
        this.n * that.d,
        this.d * that.n
    )

    operator fun unaryMinus() = Rational(-this.n, this.d)

    @Suppress("PARAMETER_NAME_CHANGED_ON_OVERRIDE")
    override operator fun compareTo(that: Rational): Int =
        (this.n * that.d).compareTo(that.n * this.d)

    override fun toString(): String =
        if (d != ONE) "$n/$d"
        else "$n"

    override fun equals(other: Any?): Boolean = when {
        this === other -> true
        other !is Rational -> false
        n != other.n -> false
        d != other.d -> false
        else -> true
    }

    override fun hashCode(): Int {
        var result = n.hashCode()
        result = 31 * result + d.hashCode()
        return result
    }

}

private fun gcd(a: BigInteger, b: BigInteger): BigInteger =
    if (a % b == ZERO) b
    else gcd(b, a % b)

fun String.toRational(): Rational {
    val (n, d) = try {
        if ("/" in this) split("/").map(::BigInteger)
        else listOf(BigInteger(this), ONE)
    } catch (e: Exception) {
        throw IllegalArgumentException("Expecting 'n/d' or 'n', but got $this", e)
    }

    return Rational(n, d)
}

infix fun BigInteger.divBy(that: BigInteger) = Rational(this, that)

infix fun Long.divBy(that: Long) =
    this.toBigInteger() divBy that.toBigInteger()

infix fun Int.divBy(that: Int) =
    this.toBigInteger() divBy that.toBigInteger()