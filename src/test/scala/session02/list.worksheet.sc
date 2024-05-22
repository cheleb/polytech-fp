enum List[+A]:
  case Nil
  case Cons(head: A, tail: List[A])

object List {
  def apply[A](as: A*): List[A] =
    if as.isEmpty then Nil
    else Cons(as.head, apply(as.tail*))

  extension [A](l: List[A])
    def map[B](f: A => B): List[B] = l match
      case List.Nil              => List.Nil
      case List.Cons(head, tail) => List.Cons(f(head), tail.map(f))

    @annotation.tailrec
    def foldLeft[B](acc: B, f: (B, A) => B): B =
      l match
        case List.Nil              => acc
        case List.Cons(head, tail) => tail.foldLeft(f(acc, head), f)
    def foldRight[B](z: B, f: (A, B) => B): B = l match
      case Nil         => z
      case Cons(x, xs) => f(x, xs.foldRight(z, f))

    def map2[B](f: A => B): List[B] =
      l.foldRight(Nil, (a, b) => Cons(f(a), b))
}

List(1, 2, 3, 4)
// .filter(_ % 2 == 0)
  .map2(a => a * 2)
//.foldLeft(0, _ + _)
  .foldRight(0, _ + _)
