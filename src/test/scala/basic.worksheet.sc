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

// Sealed trait and case class
