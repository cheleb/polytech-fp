package typeclass

import demo.Dude

trait Show[A] {
  def show(a: A): String
}

object Show {

  given Show[Int] with {
    def show(a: Int): String = a.toString
  }

  given Show[String] with {
    def show(a: String): String = a
  }

  given [A](using s: Show[A]): Show[Option[A]] with {
    def show(a: Option[A]): String = a match {
      case Some(a) => s.show(a)
      case None    => "None"
    }
  }

}

extension [A](a: A)(using s: Show[A]) {
  def show: String = s.show(a)
}
