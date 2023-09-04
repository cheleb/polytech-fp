trait Color // interface Color

object Color {
  case object Red extends Color
  case object Green extends Color
  case object Blue extends Color
}

case class Ball(size: Int, color: Color)

val aColor: Color = Color.Red

aColor match {
  case Color.Red   => "red"
  case Color.Green => "green"
  case Color.Blue  => "blue"
}

// Enum

val anOption: Option[Int] = Some(1)

def fill(x: Int, color: Color): Option[Ball] = x match
  case 0 => None
  case _ => Some(Ball(x, color))

val txs = List(
  fill(0, Color.Blue),
  fill(1, Color.Green),
  fill(2, Color.Red),
  fill(3, Color.Blue),
  fill(3, Color.Blue)
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
