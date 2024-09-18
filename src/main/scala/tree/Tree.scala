package tree

import scala.annotation.tailrec

enum Tree[+A]:
  case Leaf(value: A)
  case Branch(left: Tree[A], right: Tree[A])

  def size: Int = this match
    case Leaf(_)      => 1
    case Branch(l, r) => 1 + l.size + r.size

  def size(t: Tree[Int]): Int = {
    @tailrec
    def inner_size(l: List[Tree[Int]], acc: Int): Int =
      l match {
        case Nil                => acc
        case Leaf(v) :: ls      => inner_size(ls, acc + 1)
        case Branch(a, b) :: ls => inner_size(a :: b :: ls, acc + 1)
      }
    inner_size(List(t), 0)
  }

object Tree:
  def apply[A](as: A*): Tree[A] =
    if as.isEmpty then
      throw new IllegalArgumentException("Cannot create empty tree")
    else if as.length == 1 then Leaf(as.head)
    else Branch(apply(as.take(as.length / 2)*), apply(as.drop(as.length / 2)*))
