package lt.manolesiai.automatization

import java.io.File
import java.nio.charset.Charset

import com.github.tototoshi.csv.CSVReader

import scala.collection.immutable
import scalaz._
import Scalaz._
import implicits._

object Omniva {
  case class Line(paid: Eur, names: Seq[String])

  def main(file: File) = {
    val csv = CSVReader.open(file, "ISO-8859-13")
    val x = csv.iterator.zipWithIndex.map { case (parts, idx) =>
      val lParts = parts.liftValidation
      val costV = lParts(3).flatMap { s =>
        s.parseInt.leftMap(e => s"Can't parse '$s' as int: $e")
      }.map(Eur.fromCents).toValidationNel
      val namesV = lParts(5).flatMap {
        case s if s.trim.isEmpty => s"Names field is empty!".failure
        case s => s.split("\s").success
      }.toValidationNel
      val resultV = (costV |@| namesV) { (cost, names) =>
        2
      }
      resultV.leftMap(_.map(err => s"At line ${idx + 1}: $err"))
    }.toVector
    val y = Traverse.sequence(x)
  }
}
