trait Animal

trait Dog extends Animal
trait Cat extends Animal

val medor: Dog = new Dog {}

//val animal: Animal = medor
trait Box[A]

//val dogBox: Box[Dog] = new Box[Dog] {}

//val animalBox: Box[Animal] = new Box[Dog] {}

//val jlist: java.util.List[Animal] = new java.util.ArrayList[Dog]()

trait House[+A] {
  // def setInhabitant(inhabitant: A): House[A] = new House[A] {}
  def setInhabitant[B >: A](inhabitant: B): House[B] = new House[B] {}
}

val dogHouse: House[Animal] = new House[Dog] {}
dogHouse.setInhabitant(new Cat {})
trait Vet[-A]:
  def heal(animal: A): Unit = println("Healed")
//val animalVet: Vet[Animal] = new Vet[Dog] {}
val dogVet: Vet[Dog] = new Vet[Animal] {}

dogVet.heal(medor)
