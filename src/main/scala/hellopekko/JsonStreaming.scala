package hellopekko

import org.apache.pekko.stream.*
import org.apache.pekko.stream.scaladsl.*
import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.BaseStream
import org.apache.pekko.stream.connectors.json.scaladsl.JsonReader
import java.nio.file.FileSystem
import java.nio.file.FileSystems
import java.nio.file.Path

object JsonStreaming extends App {

  given as: ActorSystem = ActorSystem("HelloPekkoWorld")

  val g = RunnableGraph.fromGraph(GraphDSL.create() {
    implicit builder: GraphDSL.Builder[NotUsed] =>
      import GraphDSL.Implicits._

      val in =
        FileIO.fromPath(
          Paths.get("testdata", "sample.json")
        )

      val out = Sink.foreach(println)

      val jsonFlow =
        JsonReader.select("$.email").map(_.decodeString("UTF-8"))


      // format: off
      in ~> jsonFlow ~> out
      // format: on
      ClosedShape
  })

  g.run()

  as.terminate()
}
