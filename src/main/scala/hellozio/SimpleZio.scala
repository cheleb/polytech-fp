package hellozio

import zio.*

object SimpleZio extends ZIOAppDefault {

  val program: Task[String] = ZIO.succeed("Hello ZIO!")
  override def run: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] = ???

}
