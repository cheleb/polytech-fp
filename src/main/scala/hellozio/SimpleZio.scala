package hellozio

import zio.*
import java.io.IOException

object SimpleZio extends ZIOAppDefault {

  val io = ZIO.sleep(2.seconds)
    *> ZIO.fail("fail")

  val program =
    for {
      _ <- (ZIO.sleep(3.seconds) *> Console
        .printLine("Hello ZIO!")
        .retry(Schedule.recurs(10))
        .timeout(1.second))
        .zipPar(io)
    } yield ()
  override def run: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] = program

}
