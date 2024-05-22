case class Rating(value: Int)
case class Manager(name: String, rating: Rating)
case class Developper(name: String, manager: Manager)

val jane = Manager("Jane", Rating(10))
val john = Developper("John", jane)

case class Lens[Whole, Piece](
    get: Whole => Piece,
    set: Piece => Whole => Whole
) { self =>

  def update(whole: Whole)(f: Piece => Piece): Whole = {
    val oldPiece = get(whole)
    set(f(oldPiece))(whole)
  }

  def >>>[SubPiece](that: Lens[Piece, SubPiece]): Lens[Whole, SubPiece] =
    Lens(
      whole => that.get(self.get(whole)),
      subPiece => whole => self.set(that.set(subPiece)(self.get(whole)))(whole)
    )
}

val manager = Lens[Developper, Manager](
  developper => developper.manager,
  manager => developper => developper.copy(manager = manager)
)

val rating: Lens[Manager, Rating] =
  Lens(
    manager => manager.rating,
    rating => manager => manager.copy(rating = rating)
  )

val upvote: Lens[Rating, Int] =
  Lens(_.value, value => rating => rating.copy(value = value))

val promote = manager >>> rating >>> upvote

val newJohn = promote.update(john)(_ + 1)

println(newJohn)
