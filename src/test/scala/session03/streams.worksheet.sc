val range = 1 to 1000
range.to(LazyList).sum
range.sum

val stream = LazyList.from(1 to 1000)
//stream.reduce(_ + _)
