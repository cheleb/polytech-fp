package hellozio

import zio.*
import java.io.IOException

object SimpleZio extends ZIOAppDefault {

  // val io = ZIO.sleep(2.seconds)
  //   *> ZIO.fail("fail")

  def goodbye(name: String): IO[IOException, Unit] =
    Console.printLine(s"Goodbye $name!")

  def hello = Console.printLine("Hello ZIO!")

  val program: ZIO[Any, IOException, String] = for {
    _ <- hello
    name <- Console.readLine("What is your name? ")
    _ <- goodbye(name)
  } yield name
  override val run: ZIO[Environment & (ZIOAppArgs & Scope), Any, Any] =
    program
}
