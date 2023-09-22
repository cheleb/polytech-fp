trait Color // interface Color

case object Red extends Color
case object Green extends Color
case object Blue extends Color
case class Other(s: String) extends Color

case class Ball(size: Int, color: Color)

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
