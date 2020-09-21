package games.gameOfFifteen

import board.Cell
import board.Direction
import board.createGameBoard
import games.game.Game

/*
 * Implement the Game of Fifteen (https://en.wikipedia.org/wiki/15_puzzle).
 * When you finish, you can play the game by executing 'PlayGameOfFifteen'.
 */
fun newGameOfFifteen(initializer: GameOfFifteenInitializer = RandomGameInitializer()): Game =
    GameOfFifteen(initializer)

private class GameOfFifteen(
    val initializer: GameOfFifteenInitializer
) : Game {

    private val gameBoard = createGameBoard<Int?>(4)
    private val result = (1..15).toList()
    private var zeroCell = Cell(4, 4)

    override fun initialize() {
        initializer.initialPermutation.withIndex().forEach { (index, value) ->
            gameBoard[Cell((index / 4) + 1, (index % 4) + 1)] = value
        }
        zeroCell = Cell(4, 4)
        gameBoard[zeroCell] = null
    }

    override fun canMove(): Boolean = true

    override fun hasWon(): Boolean =
        gameBoard.filter { it != null }.map(gameBoard::get) == result

    override fun processMove(direction: Direction) {
        with(gameBoard) {
            zeroCell.getNeighbour(direction.reversed())?.let { n ->
                this[zeroCell] = this[n]
                this[n] = null
                zeroCell = n
            }
        }
    }

    override fun get(i: Int, j: Int): Int? =
        gameBoard[gameBoard.getCell(i, j)]

}
