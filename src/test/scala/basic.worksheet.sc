trait Color // interface Color

case object Red extends Color
case object Green extends Color
case object Blue extends Color
case class Other(s: String) extends Color

case class Ball(size: Int, color: Color)

val aColor: Color = Other("FFFFFF")

aColor match {
  case Red      => "red"
  case Green    => "green"
  case Blue     => "blue"
  case Other(s) => s
}

val redBall = Ball(1, Red)

redBall match {
  case Ball(size, Red) => s"Red ball of size $size"
  case Ball(size, _)   => s"Other ball of size $size"
}

// Option

def divide(x: Int, y: Int): Int = x / y
def divideOption(x: Int, y: Int): Option[Int] =
  if y == 0 then None else Some(x / y)

val anResult = divide(1, 1)

sealed trait MyOption[+A]

case class MySome[+A](value: A) extends MyOption[A]:
  def get: A = value
  def map[B](f: A => B): MyOption[B] = MySome(f(value))
  def flatMap[B](f: A => MyOption[B]): MyOption[B] = f(this.get)

case object MyNone extends MyOption[Nothing]

val o1 = Option(2)
o1.flatMap(x => divideOption(1, x)).flatMap(x => divideOption(1, x))

def fill(x: Int, color: Color): Option[Ball] = x match
  case 0 => None
  case _ => Some(Ball(x, color))

val txs = List(
  fill(0, Blue),
  fill(1, Green),
  fill(2, Red),
  fill(3, Blue),
  fill(3, Blue)
)

txs.collect({ case Some(x) => x.size }).sum
txs.collect { case Some(x) => x.size }.sum

txs
  .collect({ case Some(x) => x })
  .groupBy(_.color)
  .view
  .mapValues(_.map(_.size).sum)
  .toMap

txs
  .collect({ case Some(x) => x })
  .groupMapReduce(_.color)(_.size)(_ + _)

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
