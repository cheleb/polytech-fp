import session02.*

val tree = Tree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

def toString[A](tree: Tree[A]): String = tree match
  case Tree.Empty       => "()"
  case Tree.Leaf(value) => s"$value"
  case Tree.Branch(left, value, right) =>
    s"${toString(left)} $value ${toString(right)}"

toString(tree)

def depth[A](tree: Tree[A]): Int = tree match
  case Tree.Empty   => 0
  case Tree.Leaf(_) => 1
  case Tree.Branch(left, _, right) =>
    1 + (depth(left) max depth(right))

depth(tree)

def drawTree[A](tree: Tree[A]) = {
  def draw(tree: Tree[A], prefix: String, isLeft: Boolean): String = tree match
    case Tree.Empty =>
      s"${prefix}+- ()\n"
    case Tree.Leaf(value) =>
      s"${prefix}+- $value\n"
    case Tree.Branch(left, value, right) =>
      val nextPrefix = prefix + (if (isLeft) "|  " else "   ")
      val nextPrefix2 = prefix + (if (isLeft) "|  " else "   ")
      s"${prefix}+- $value\n" +
        draw(left, nextPrefix, true) +
        draw(right, nextPrefix2, false)

  draw(tree, "", true)
}

"\n" + drawTree(tree)

tree.map(_.toDouble).sum

tree.sum
