import monocle.PTraversal
import monocle.Focus
import monocle.syntax.all._

case class Address(streetNumber: Int, streetName: String)

case class User(name: String, address: Address)

val user = User("Anna", Address(12, "high street"))

user.focus(_.name).replace("Bob")
// res: User = User("Bob", Address(12, "high street"))

user.focus(_.address.streetName).modify(_.toUpperCase)
// res: User = User("Anna", Address(12, "HIGH STREET"))

user.focus(_.address.streetNumber).get

case class Lecturer(firstName: String, lastName: String, salary: Int)
case class Department(budget: Int, lecturers: List[Lecturer])
case class University(name: String, departments: Map[String, Department])

def uni(x: Int) = University(
  "oxford",
  Map(
    "Computer Science" -> Department(
      45,
      List(
        Lecturer("john", "doe", 10 * x),
        Lecturer("robert", "johnson", 16 * x)
      )
    ),
    "History" -> Department(
      30,
      List(
        Lecturer("arnold", "stones", 20 * x)
      )
    )
  )
)

val poor = uni(1)
val rich = uni(100)

val physics = Department(
  36,
  List(
    Lecturer("daniel", "jones", 12),
    Lecturer("roger", "smith", 14)
  )
)

val zozo: PTraversal[University, University, Int, Int] = Focus[University](
  _.departments.at("Computer Science").some.lecturers.each.salary
)
zozo.lastOption(poor)

zozo.lastOption(rich)
