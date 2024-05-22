package session02

import scala.annotation.tailrec

enum Tree[+A]:
  case Leaf(value: A)
  case Branch(left: Tree[A], value: A, right: Tree[A])
  case Empty

extension [A](tree: Tree[A])
  def map[B](f: A => B): Tree[B] = ???
  def sum(using num: Numeric[A]): A = ???
  //  @tailrec
  def size: Int = ???
  def size_t: Int = ???

object Tree:
  def apply[A](values: A*): Tree[A] = ???
