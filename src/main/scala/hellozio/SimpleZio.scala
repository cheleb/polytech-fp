package hellozio

import zio.*
import zio.json.*

import java.io.IOException

object SimpleZio extends ZIOAppDefault {

  val duce: Dude = Dude(1, "Dude", Some("dodo&lol.com"))

  override val run =
    Console.printLine("Hello ZIO!") *>
      Console.printLine(duce.toJson)

}
