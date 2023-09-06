def adderMethod(x: Int, y: Int): Int = x + y

val adder: (Int, Int) => Int = (x, y) => x + y

adder(10, 2)

def curry[A, B, C](f: (A, B) => C): A => B => C =
  a => b => f(a, b)

def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)

val cc = curry(adder)
cc(10)(2)

val ucc = uncurry(cc)
ucc(10, 2)

val dd = adderMethod(10, _)
