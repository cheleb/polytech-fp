package tree

import munit.FunSuite

import Tree.*

class TreeSpec extends FunSuite {
  test("Apply") {

    val tree = Tree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    assertEquals(
      tree,
      Branch(
        Branch(
          Branch(Leaf(1), Leaf(2)),
          Branch(Leaf(3), Branch(Leaf(4), Leaf(5)))
        ),
        Branch(
          Branch(Leaf(6), Leaf(7)),
          Branch(Leaf(8), Branch(Leaf(9), Leaf(10)))
        )
      )
    )
  }

  test("size") {
    val tree = Branch(Branch(Leaf(1), Leaf(2)), Leaf(3))
    assertEquals(tree.size, 5)
  }
}
