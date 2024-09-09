package hellofs2

import cats.effect.*
import fs2.data.json.jq
import fs2.data.json.jq.JqParser
import fs2.io.file.Files
import fs2.io.file.Path
import fs2.data.json.{jq, render, tokens}

import java.nio.file.Paths
//import fs2.io.

object HelloFS2Json extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =

    val queryCompiler = jq.Compiler[IO]
    for {
      // first parse the provided query
      query <- JqParser.parse[IO](".name")
      // then compile it
      compiled <- queryCompiler.compile(query)
      timed <- Files[IO]
        .readUtf8(
          Path.fromNioPath(Paths.get("testdata", "sample.json"))
        )
        // parse the input as json
        .through(tokens)
        // execute the compiled query on the input
        .through(compiled)
        // render the query result
        .through(render.prettyPrint())
        // encode the result
        .through(fs2.text.utf8.encode[IO])
        // and save it to the output
        .through(fs2.io.stdout[IO])
        // finally run all the things
        .compile
        .drain
        .timed
      _ <- IO.println(s"Procesed in ${timed._1.toMillis}ms")
    } yield ExitCode.Success
}
