enum Color {
  case Red, Green, Blue
}

case class Ball(size: Int, color: Color)

val aColor: Color = Color.Red

aColor match {
  case Color.Red   => "red"
  case Color.Green => "green"
  case Color.Blue  => "blue"
}

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

txs.collect { case Some(x) => x }

txs.collect({ case Some(x) => x.size }).sum
txs.collect { case Some(x) => x.size }.sum
//txs.collect { case Some(x) => x.size }.sum

txs
  .collect({ case Some(x) => x })
  .groupBy(ball => ball.color)
  .view
  .mapValues(_.map(_.size).sum)
  .toMap

txs
  // .collect { case Some(x) => x }
  .flatten
  .groupMapReduce(_.color)(_.size)(_ + _)
