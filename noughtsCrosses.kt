import java.lang.NumberFormatException

fun gameLogic(grid: MutableList<MutableList<Char>>): String {

    var convertThreeCharsToString = ""
    var gridDiagonalColumnIndex = 0
    var gameResult = ""

    //Rows
    for (j in 0..2) {
        for (i in 0..2) {
            convertThreeCharsToString += grid[j][i]
            if (convertThreeCharsToString == "OOO" || convertThreeCharsToString == "XXX") {
                gameResult = "${convertThreeCharsToString[0]} wins."
                break
            }
        }
        convertThreeCharsToString = ""
    }

    //Columns
    for (j in 0..2) {
        for (i in 0..2) {
            convertThreeCharsToString += grid[i][j]
            if (convertThreeCharsToString == "OOO" || convertThreeCharsToString == "XXX") {
                gameResult = "${convertThreeCharsToString[0]} wins."
                break
            }
        }
        convertThreeCharsToString = ""
    }


    // Diagonals
    for (i in 0..2){
        convertThreeCharsToString += grid[i][i]
        if (convertThreeCharsToString == "XXX" || convertThreeCharsToString == "OOO"){
            gameResult = "${convertThreeCharsToString[0]} wins."
            break
        }
    }
    convertThreeCharsToString = ""
    for (i in 2 downTo 0){
        convertThreeCharsToString += grid[i][gridDiagonalColumnIndex]
        gridDiagonalColumnIndex += 1
        if (convertThreeCharsToString == "XXX" || convertThreeCharsToString == "OOO"){
            gameResult = "${convertThreeCharsToString[0]} wins."
            break
        }
    }
    return gameResult
}

fun printTheGameGrid(grid: MutableList<MutableList<Char>>) {
    println("---------")
    for (i in 0..2){
        print("| ")
        for (j in 0..2){
            if (grid[i][j] == '_') {
                grid[i][j] = ' '
            }
            print("${grid[i][j]} ")
        }
        print("|")
        println()
    }
    println("---------")
}

fun userInputToGameGrid (grid: MutableList<MutableList<Char>>, drawGridSymbols: String): MutableList<MutableList<Char>> {
    var counter =0
    for (i in 0..2) {
        for (j in 0..2) {
            grid[i][j] = drawGridSymbols[counter]
            counter += 1
        }
    }
    return grid
}

fun changeTurn(flag: Int): Char{
    var turn = 'X'
    if (flag % 2 == 0){
        turn ='O'
    }
    return turn
}

fun main() {
    var gameTurnSwitch = 1
    var numberOfMoves = 1
    var makeAMove: List<Int>
    val grid = MutableList(3) { MutableList(3) { '_' } }
    val drawGridSymbols = "_________"
    val turnUserInputToGameGrid = userInputToGameGrid(grid, drawGridSymbols)
    if (gameLogic(turnUserInputToGameGrid) != "") {
        print(gameLogic(turnUserInputToGameGrid))
    }
    printTheGameGrid(turnUserInputToGameGrid)
    while (true) {
        try {
            makeAMove = readln().split(" ").map { it.toInt() }
            when {
                makeAMove[0] > 3 || makeAMove[0] == 0 -> println("Coordinates should be from 1 to 3!")
                makeAMove[1] > 3 || makeAMove[1] == 0 -> println("Coordinates should be from 1 to 3!")
                turnUserInputToGameGrid[makeAMove[0] - 1][makeAMove[1] - 1] != ' ' -> println("This cell is occupied! Choose another one!")
                numberOfMoves == 9 -> {
                    turnUserInputToGameGrid[makeAMove[0] - 1][makeAMove[1] - 1] = changeTurn(gameTurnSwitch)
                    if (gameLogic(turnUserInputToGameGrid) != "Draw") {
                        printTheGameGrid(turnUserInputToGameGrid)
                        print(gameLogic(turnUserInputToGameGrid))
                        break
                    } else {
                        print("Draw")
                        break
                    }
                }
                else -> {
                    numberOfMoves += 1
                    turnUserInputToGameGrid[makeAMove[0] - 1][makeAMove[1] - 1] = changeTurn(gameTurnSwitch)
                    if (gameLogic(turnUserInputToGameGrid) == "") {
                        printTheGameGrid(turnUserInputToGameGrid)
                        gameTurnSwitch += 1
                    } else {
                        printTheGameGrid(turnUserInputToGameGrid)
                        print(gameLogic(turnUserInputToGameGrid))
                        break
                    }
                }
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
            }
    }
}
