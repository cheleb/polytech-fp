package demo

import typeclass.*
import typeclass.magnolia.*
case class Dog(name: String) derives Show
case class Dude(
    id: Int,
    name: String,
    email: Option[String],
    pet: Option[Dude] = None
) derives Show

object ShowDemo extends App {

  val i = 42

  val dude = Dude(1, "Dude", Some("zozo&lol.com"))

  println(i.show)

  println(dude.show)

}
