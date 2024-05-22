package session02

class TreeSpec extends munit.FunSuite {

  test("Map int tree to item tree".ignore) {
    val tree = Tree(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val expected = Tree(
      "item_1",
      "item_2",
      "item_3",
      "item_4",
      "item_5",
      "item_6",
      "item_7",
      "item_8",
      "item_9",
      "item_10"
    )
    assertEquals(???, expected)
  }

  test("Big tree sizes") {
    val tree = Tree((1 to 10).toList*)
    assertEquals(tree.size, 10)
  }

  test("Big tree size tailrec") {
    val tree = Tree((1 to 10).toList*)
    assertEquals(tree.size_t, 10)
  }

}
