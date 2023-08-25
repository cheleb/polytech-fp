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

ioInt
  .flatMap {
    case 10 => IO(() => println("Hello, world!" * 10))
    case _  => IO(() => println("Hello, world!"))
  }
