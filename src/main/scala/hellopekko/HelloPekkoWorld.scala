package hellopekko

import org.apache.pekko.stream.*
import org.apache.pekko.stream.scaladsl.*
import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem

object HelloPekkoWorld extends App {
  println("Hello Pekko World!")

  given as: ActorSystem = ActorSystem("HelloPekkoWorld")

  val g = RunnableGraph.fromGraph(GraphDSL.create() {
    implicit builder: GraphDSL.Builder[NotUsed] =>
      import GraphDSL.Implicits._
      val in = Source(1 to 10)

      val out = Sink.foreach(println)

      val bcast = builder.add(Broadcast[Int](2))
      val merge = builder.add(Merge[Int](2))

      val f1, f2, f3, f4 = Flow[Int].map(_ + 10)

      // format: off
      in ~> f1 ~> bcast  ~>  f2  ~> merge ~> f3 ~> out
                  bcast  ~>  f4  ~> merge
      // format: on
      ClosedShape
  })

  g.run()

  as.terminate()
}
