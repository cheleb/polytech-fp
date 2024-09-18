package chess

package chess

import scala.annotation.tailrec
import scala.collection.mutable

object ChessCodePadSolution extends App {

  // Step 1
  case class Cell(x: Int, y: Int)

  def isValid(cell: Cell): Boolean =
    cell.x >= 0 && cell.y >= 0

  def moves(cell: Cell): List[Cell] =
    List(
      Cell(cell.x + 1, cell.y + 2),
      Cell(cell.x + 2, cell.y + 1),
      Cell(cell.x - 1, cell.y + 2),
      Cell(cell.x - 2, cell.y + 1),
      Cell(cell.x + 1, cell.y - 2),
      Cell(cell.x + 2, cell.y - 1),
      Cell(cell.x - 1, cell.y - 2),
      Cell(cell.x - 2, cell.y - 1)
    ).filter(isValid)

  // Step 2
  {
    @tailrec
    def countMovesIteration(from: List[Cell], to: Cell, iteration: Int): Int =
      if (from.contains(to)) {
        iteration
      } else {
        countMovesIteration(from.flatMap(moves), to, iteration + 1)
      }

    def countMoves(from: Cell, to: Cell): Int =
      countMovesIteration(List(from), to, 0)

    println(countMoves(Cell(1, 1), Cell(3, 3))) // 4
  }
  // Step 3
  {
    @tailrec
    def countMovesIterationOpti(
        from: List[Cell],
        to: Cell,
        iteration: Int,
        visited: List[Cell]
    ): Int =
      if (from.contains(to)) {
        iteration
      } else {
        val moveTo = from.flatMap(moves)
        countMovesIterationOpti(
          moveTo
            .filter(!visited.contains(_)),
          to,
          iteration + 1,
          visited ++ moveTo
        )
      }

    def countMovesOpti(from: Cell, to: Cell): Int =
      countMovesIterationOpti(List(from), to, 0, List())

    println(countMovesOpti(Cell(1, 1), Cell(3, 3)))
  }
  // Step 3.1
  {
    @tailrec
    def countMovesIterationOpti2(
        from: List[Cell],
        to: Cell,
        iteration: Int,
        visited: Set[Cell]
    ): Int =
      if (from.contains(to)) {
        iteration
      } else {
        val moveTo = from.flatMap(moves)
        countMovesIterationOpti2(
          moveTo
            .filterNot(visited),
          to,
          iteration + 1,
          visited ++ moveTo
        )
      }

    def countMovesOpti2(from: Cell, to: Cell): Int =
      countMovesIterationOpti2(List(from), to, 0, Set())

    println("Step3: " + countMovesOpti2(Cell(1, 1), Cell(3, 3)))
  }

  // Step 4
  {
    def addMoves(in: List[Cell]): List[List[Cell]] =
      moves(in.last).map(m => in :+ m)

    def findPathIteration(paths: List[List[Cell]], to: Cell): List[Cell] =
      paths
        .find(_.contains(to))
        .getOrElse(findPathIteration(paths.flatMap(addMoves), to))

    def findPath(from: Cell, to: Cell): List[Cell] =
      if (from == to)
        List(from)
      else
        findPathIteration(addMoves(List(from)), to)

    println(
      findPath(Cell(1, 1), Cell(3, 3))
    ) // List(Cell(1,1), Cell(2,3), Cell(3,5), Cell(5,4), Cell(3,3))
  }

  // Step 5: A* algorithm
  {

    def findPath(from: Cell, to: Cell): List[Cell] = {
      val queue = mutable.Queue[Cell](from)
      val visited = mutable.Set[Cell]()
      val cellToParent = mutable.Map[Cell, Cell]()

      var found = Option.empty[Cell]

      while (queue.nonEmpty && found.isEmpty) {
        val cell = queue.dequeue()
        moves(cell).foreach { next =>
          if (visited.add(next)) {
            queue.enqueue(next)
            visited.add(next)
            cellToParent.put(next, cell)
            if (next == to) {
              found = Some(next)
            }
          }
        }
      }
      found match {
        case Some(cell) =>
          val path = mutable.ListBuffer[Cell]()
          var current = cell
          while (current != from) {
            path.prepend(current)
            current = cellToParent(current)
          }
          path.prepend(from)
          path.toList
        case None =>
          List()
      }
    }

    println(
      findPath(Cell(1, 1), Cell(3, 3))
    )

  }
}
