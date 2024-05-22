package hellozio

import zio.ZIOAppDefault
import zio.Scope
import zio.ZIO
import zio.ZIOAppArgs
import zio.stream.ZStream
import zio.stream.ZPipeline
import zio.json.*
import java.io.IOException

object ZIOStreamJson extends ZIOAppDefault {

  override def run: ZIO[Any, Throwable, Unit] =
    ZStream
      .fromFileName("testdata/sample.json")
      .via(ZPipeline.utf8Decode)
      .via(ZPipeline.splitLines)
      .map(json => json.fromJson[Dude])
      .collectRight
      .map(_.name)
      .foreach(line => ZIO.attempt(println(line)))

}
