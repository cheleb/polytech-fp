type Thunk[+A] = () => A

case class IO[A](unsafeRun: Thunk[A]) {

  def map[B](f: A => B): IO[B] = IO(() => f(unsafeRun()))

  def flatMap[B](f: A => IO[B]): IO[B] = IO(() => f(unsafeRun()).unsafeRun())

  override def toString(): String = s"IO(...)"
}

val io = IO(() => println("Hello, world!"))

io.unsafeRun()

val ioInt = IO(() => 10)

ioInt.unsafeRun()

(for {
  i <- IO(() => 10)
  str <- IO(() => s"Hello, world! $i")
} yield str).unsafeRun()
