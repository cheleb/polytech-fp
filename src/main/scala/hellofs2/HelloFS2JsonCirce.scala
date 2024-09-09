package hellofs2

import cats.effect.*
import fs2.data.json.jq
import fs2.data.json.jq.JqParser
import fs2.io.file.Files
import fs2.io.file.Path
import fs2.data.json.selector._
import fs2.data.json.circe._

import java.nio.file.Paths
import fs2.Stream
import fs2.data.json.JsonException
import io.circe.syntax.*
import io.circe.parser.decode

object HelloFS2JsonCirce extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    for {
      timed <- Files[IO]
        .readUtf8(
          Path.fromNioPath(Paths.get("testdata", "sample.json"))
        )
        .through(fs2.text.lines)
        .map { line =>
          // rule #2: values must be encoded on single lines
          decode[Dude](line)
        }
        .evalMap(
          _.fold(
            e => IO.println(s"Failed to decode: $e"),
            d => IO.println(d.email)
          )
        )
        // finally run all the things
        .compile
        .drain
        .timed
      _ <- IO.println(s"Procesed in ${timed._1.toMillis}ms")
    } yield ExitCode.Success
}
