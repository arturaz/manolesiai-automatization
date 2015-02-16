package lt.manolesiai.automatization

case class Eur(wholes: Int, cents: Int) {
  def +(e: Eur) = {
    val addedCents = cents + e.cents
    Eur(wholes + e.wholes + addedCents / 100, cents + addedCents % 100)
  }

  override def toString = s"$wholes.$cents"
}

object Eur {
  def fromCents(cents: Int) = Eur(cents / 100, cents % 100)
}
