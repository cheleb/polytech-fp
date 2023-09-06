def addMethod(x: Int, y: Int): Int = x + y

// Function definition
val addFunction = (x: Int, y: Int) => x + y

addMethod(1, 1)
addFunction(1, 1)

val addOne: Int => Int = x => x + 1
val multTwo: Int => Int = x => x * 2

val h: Int => Int = addOne andThen multTwo
val i: Int => Int = addOne compose multTwo

h(1)
i(1)

def adder_v1(x: Int, y: Int) = x + y

def adder(x: Int)(y: Int): Int = x + y

val addTwo = adder(2)

addTwo(1)
addTwo(5)

// HOF
List(1, 2, 3) map h
