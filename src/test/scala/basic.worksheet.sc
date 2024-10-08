sealed trait Color // interface Color

case object Red extends Color
case object Green extends Color
case object Blue extends Color
case class Other(s: String) extends Color

case class Ball(size: Int, color: Color)

val aColor: Color = Red

aColor match {
  case Red      => "red"
  case Green    => "green" // Try to comment this line
  case Blue     => "blue"
  case Other(s) => s
}

val redBall = Ball(1, Red)

val blueBall = Ball(1, Blue)

redBall match {
  case Ball(size, Red)   => s"Red ball of size $size"
  case Ball(size, Green) => s"Green ball of size $size"
  case Ball(size, _)     => s"Other ball of size $size"
}

val o1 = Some(1)
val o2 = Some(2)
val o3 = Some(3)

o1.flatMap(i1 => o2.map(i2 => i1 + i2))
  .flatMap(sum1 => o3.map(i3 => sum1 + i3))

//  .map(count => "😱" * count)

//
// "😱😱😱😱😱😱"
//

o1.flatMap { i1 =>
  o2.flatMap { i2 =>
    o3.map { i3 =>
      i1 + i2 + i3
    }
  }
}

//.map(count => "😱" * count)
//
// "😱😱😱😱😱😱"
//

for {
  i1 <- o1
  i2 <- o2
  i3 <- o3
} yield i1 + i2 + i3

/*
 for {
   i1 <- Some(1)
   i2 <- Some(2)
   i3 <- Some(3)
 } yield i1 + i2 + i3

 scalac -Vprint:parser,typer forcomp.scala
 */
// Sealed trait and case class
