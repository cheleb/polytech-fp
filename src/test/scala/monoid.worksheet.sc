trait Monoid[A]:
  def combine(a1: A, a2: A): A
  def empty: A

val stringMonoid: Monoid[String] = new:
  def combine(a1: String, a2: String) = a1 + a2
  val empty = ""

def listMonoid[A]: Monoid[List[A]] = new:
  def combine(a1: List[A], a2: List[A]) = a1 ++ a2
  val empty = Nil

def firstOptionMonoid[A]: Monoid[Option[A]] = new:
  def combine(x: Option[A], y: Option[A]) = x orElse y
  val empty = None

def dual[A](m: Monoid[A]): Monoid[A] = new:
  def combine(x: A, y: A) = m.combine(y, x)
  val empty = m.empty

def endoMonoid[A]: Monoid[A => A] = new:
  def combine(f: A => A, g: A => A): A => A = f andThen g
  val empty: A => A = identity

def foldMap[A, B](as: List[A], m: Monoid[B])(f: A => B): B =
  as.foldLeft(m.empty)((b, a) => m.combine(b, f(a)))

def foldRight[A, B](as: List[A])(acc: B)(f: (A, B) => B): B =
  foldMap(as, dual(endoMonoid))(f.curried)(acc)

foldRight(List("ab", "cd", "ef"))("")(_ + _)

trait Foldable[F[_]]:
//  import Monoid.{endoMonoid, dual}

  extension [A](as: F[A])
    def foldRight[B](acc: B)(f: (A, B) => B): B =
      as.foldMap(f.curried)(using dual(endoMonoid[B]))(acc)

    def foldLeft[B](acc: B)(f: (B, A) => B): B =
      as.foldMap(a => b => f(b, a))(using endoMonoid[B])(acc)

    def foldMap[B](f: A => B)(using mb: Monoid[B]): B =
      as.foldRight(mb.empty)((a, b) => mb.combine(f(a), b))

    def combineAll(using ma: Monoid[A]): A =
      as.foldLeft(ma.empty)(ma.combine)

given Foldable[List] with
  extension [A](as: List[A])
    override def foldRight[B](acc: B)(f: (A, B) => B) =
      as.foldRight(acc)(f)
    override def foldLeft[B](acc: B)(f: (B, A) => B) =
      as.foldLeft(acc)(f)
