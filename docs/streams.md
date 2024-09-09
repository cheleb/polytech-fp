# Scala Streams

Streams are lazy lists. They are evaluated on demand, and they can be infinite.

## Streams library

### Scala standard library streams

Scala standard library provided a `Stream`  class that can be used to create streams, deprecated in favour of `LazyList`.

```scala

val stream = LazyList(1 to 1000) // 
 
```


### Akka / Pekko Streams

https://pekko.apache.org/docs/pekko/current/stream/index.html


Akka Streams is a library that provides a high-level API for stream processing. It is built on top of Akka actors and provides a DSL for defining stream processing pipelines.

```scala
val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
  import GraphDSL.Implicits._
  val in = Source(1 to 10)

  val out = Sink.ignore

  val bcast = builder.add(Broadcast[Int](2))
  val merge = builder.add(Merge[Int](2))

  val f1, f2, f3, f4 = Flow[Int].map(_ + 10)

  in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out
              bcast ~> f4 ~> merge
  ClosedShape
})

```


### Typelevel FS2

* https://fs2.io/
* https://blog.rockthejvm.com/fs2/


FS2 is a library for functional streams in Scala. It provides a high-level API for defining and composing streams of data. FS2 is built on top of Cats and Cats Effect, and it provides a purely functional API for working with streams.

```scala

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

```


### ZIO Streams

* https://zio.dev/docs/datatypes/datatypes_streams
* https://blog.rockthejvm.com/zio-streams/


ZIO Streams is a library for functional streams in Scala. It provides a high-level API for defining and composing streams of data. ZIO Streams is built on top of ZIO, and it provides a purely functional API for working with streams.

```scala
object ZIOStreamJson extends ZIOAppDefault {

  override def run: ZIO[Any & (ZIOAppArgs & Scope), Any, Any] =
    ZStream
      .fromFileName("testdata/sample.json")
      .via(ZPipeline.utf8Decode)
      .via(ZPipeline.splitLines)
      .map(json => json.fromJson[Dude])
      .collectRight
      .map(_.name)
      .foreach(line => ZIO.attempt(println(line)))

}
```