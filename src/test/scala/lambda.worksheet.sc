def addMethod(x: Int, y: Int): Int = x + y

// Function definition
val addFunction: (Int, Int) => Int = (x: Int, y: Int) => x + y

addMethod(1, 1)
addFunction(1, 1)

val f: Int => Int = x => x + 1
val g: Int => Int = x => x * 2

val ça_puis_ça: Int => Int = f andThen g
val ça_sur_ça: Int => Int = f ° g

ça_puis_ça(1)
ça_sur_ça(1)

extension [A](f: A => A) def °(g: A => A) = f compose g

def adder(x: Int, y: Int) = x + y

def adderCurryfied(x: Int)(y: Int): Int = x + y

val addTwo = adderCurryfied(2)

addTwo(1)
addTwo(5)

// HOF
List(1, 2, 3) map ça_puis_ça
