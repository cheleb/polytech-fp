package session02

import scala.annotation.tailrec

enum Tree[+A]:
  case Leaf(value: A)
  case Branch(left: Tree[A], value: A, right: Tree[A])
  case Empty

extension [A](tree: Tree[A])
  def map[B](f: A => B): Tree[B] = tree match
    case Tree.Empty       => Tree.Empty
    case Tree.Leaf(value) => Tree.Leaf(f(value))
    case Tree.Branch(left, value, right) =>
      Tree.Branch(left.map(f), f(value), right.map(f))

  def sum(using num: Numeric[A]): A = tree match
    case Tree.Empty       => num.zero
    case Tree.Leaf(value) => value
    case Tree.Branch(left, value, right) =>
      num.plus(left.sum, num.plus(value, right.sum))

  //  @tailrec
  def size: Int = tree match
    case Tree.Empty           => 0
    case Tree.Leaf(_)         => 1
    case Tree.Branch(l, _, r) => 1 + l.size + r.size

  def size_t: Int = {
    @tailrec
    def inner_size(l: List[Tree[A]], acc: Int): Int =
      l match {
        case Tree.Empty :: ls           => inner_size(ls, acc)
        case Nil                        => acc
        case Tree.Leaf(v) :: ls         => inner_size(ls, acc + 1)
        case Tree.Branch(a, _, b) :: ls => inner_size(a :: b :: ls, acc + 1)
      }
    inner_size(List(tree), 0)
  }

object Tree:
  def apply[A](values: A*): Tree[A] = {
    if (values.isEmpty)
      Empty
    else if (values.length == 1) Leaf(values.head)
    else {
      val mid = values.length / 2
      Branch(
        Tree(values.slice(0, mid)*),
        values(mid),
        Tree(values.slice(mid + 1, values.length)*)
      )
    }
  }
