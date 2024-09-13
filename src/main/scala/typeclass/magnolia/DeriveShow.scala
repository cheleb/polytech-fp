package typeclass.magnolia

import magnolia1.AutoDerivation
import typeclass.Show
import magnolia1.SealedTrait
import magnolia1.CaseClass

import scala.deriving.Mirror

object DeriveShow extends AutoDerivation[Show] {

  override def join[T](caseClass: CaseClass[Show, T]): Show[T] =
    new Show[T] {
      override def show(a: T): String =
        caseClass.parameters
          .map { p => p.typeclass.show(p.deref(a)) }
          .mkString(" || ")
    }

  override def split[T](sealedTrait: SealedTrait[Show, T]): Show[T] =
    new Show[T] {
      override def show(a: T): String =
        sealedTrait.choose(a) { sub =>
          sub.typeclass.show(sub.cast(a))
        }
    }

}

extension [A](a: Show.type) {
  inline def derived(using Mirror.Of[A]) = DeriveShow.derived
}
