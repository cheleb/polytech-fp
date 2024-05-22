package hellozio

import zio.*

object Zozo extends App {

  val run2222: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] =
    Console.printLine("Hello ZIO!").unit
}
