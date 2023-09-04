trait Animal

trait Dog extends Animal
trait Cat extends Animal

val medor: Dog = new Dog {}

trait House[+A]:
//  def setInhabitant(inhabitant: A): House[A] = new House[A] {}
  def setInhabitant[B >: A](inhabitant: B): House[B] = new House[B] {}

val dogHouse: House[Animal] = new House[Dog] {}
val animalHouse: House[Animal] = dogHouse.setInhabitant(new Cat {})

trait Vet[-A]:
  def heal(animal: A): Unit = println("Healed")

val animalVet: Vet[Dog] = new Vet[Animal] {}

animalVet.heal(medor)
