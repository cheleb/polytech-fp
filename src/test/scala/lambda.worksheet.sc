def addMethod(x: Int, y: Int): Int = x + y

// Function definition
val addFunction = (x: Int, y: Int) => x + y

addMethod(1, 1)
addFunction(1, 1)

extension [A](self: A)
  def yo: String = s"Yo, $self!"
  def yoopi: String = s"Yooppi, $self!"
  def pipe[B](f: A => B): B = f(self)
  def tap[B](f: A => Unit): A =
    f(self)
    self

"zozo"
  .tap(println)
  .pipe(_.yoopi)

val f: Int => Int = x => x + 1
val g: Int => Int = x => x * 2

val h: Int => Int = f andThen g
val i: Int => Int = f compose g

h(1)
i(1)
