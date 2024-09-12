package implicits

import demo.Dude

object ImplicitDemo1 extends App {

  def simple(using x: Int): Int = x

  given Int = 42

  println(simple)
}
