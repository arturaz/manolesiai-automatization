import scalaz.Validation

/**
 * Created by arturas on 15.2.16.
 */
package object implicits {
  implicit class PFExts[A, B](val pf: PartialFunction[A, B]) extends AnyVal {
    def liftValidation: A => Validation[String, B] = {
      val lifted = pf.lift
      a => lifted(a) match {
        case Some(v) => Validation.success(v)
        case None => Validation.failure(s"Can't find '$a'")
      }
    }
  }
}
