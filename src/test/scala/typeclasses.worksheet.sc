trait SemiGroup[T]:
  extension (x: T) def combine(y: T): T

trait Monoid[T] extends SemiGroup[T]:
  def unit: T

given Monoid[String] with
  extension (x: String) def combine(y: String): String = x.concat(y)
  def unit: String = ""

def combineAll[T: Monoid](xs: List[T]): T =
  xs.foldLeft(summon[Monoid[T]].unit)(_.combine(_))

object Monoid:
  def apply[T](using m: Monoid[T]) = m
  def combineAll2[T: Monoid](xs: List[T]): T =
    xs.foldLeft(Monoid[T].unit)(_.combine(_))

combineAll(List("a", "b", "c"))
